class DamageHero(name: String, healthbar: Int) : Hero(name, healthbar) {

    private var swordMultiSpinCooldown = 0
    private var thunderSwordCooldown = 0

    /**
     *  Führt die Fähigkeiten vom BossMinion random selber aus.
     *
     *  @param mutableList hier wird eine Liste von Anti Helden übergeben.
     */
    fun damageHeroAttack(mutableList: MutableList<AntiHero>) {

        val listOfAttack = listOf("swordSpin", "knopStrike", "swordMultiSpin", "thunderSword")
        var antiHero: AntiHero? = null

        if (mutableList.size == 1) {
            antiHero = mutableList[0]
            println("Der Anti Held steht alleine da, schnell greif ihn an.")
            println()
        }

        while (antiHero == null) {
            try {
                if (mutableList.size > 1) {
                    println(
                        "Der Anti Held ist nicht mehr alleine. Wähle weise wen du angreifen möchtest.\n" +
                                "1 = ${mutableList[0].name} 2 = ${mutableList[1].name}"
                    )
                    val choice = readln().toInt()
                    if (choice in 1..mutableList.size) {
                        antiHero = mutableList[choice - 1]
                    } else {
                        println("Wenn möchtest du da bitte angreifen? Bitte gib 1 oder 2 ein.")
                    }
                }
            } catch (e: NumberFormatException) {
                println("Der eingegebene Gegner ist nicht auf dem Feld. Bitte wähle erneut.")
            } catch (e: IndexOutOfBoundsException) {
                println("Der eingegebene Gegner ist nicht auf dem Feld. Bitte wähle erneut.")
            }
        }

        println(
            "$name ist an der reihe.\n" +
                    "Du kannst in dieser Runde ${BOLD}$listOfAttack${RESET} nutzen.\n" +
                    "Wähle eine Fähigkeit von 1 - 4."
        )

        var attackNumber: Int
        var usedAttack: String

        while (true) {
            try {
                attackNumber = readln().toInt()
                if (attackNumber !in 1..4) {
                    println(
                        "Hey diese Fähigkeit übersteigt dein Level.\n" +
                                "Bitte wähle eine andere."
                    )
                    continue
                }
                usedAttack = listOfAttack[attackNumber - 1]
                if ((usedAttack == "swordMultiSpin" && swordMultiSpinCooldown > 0) ||
                    (usedAttack == "thunderSword" && thunderSwordCooldown > 0)
                ) {
                    println("$usedAttack hat noch Cooldown such dir schnell eine andere Fähigkeit aus.")
                    continue
                }
                break
            } catch (e: NumberFormatException) {
                println(
                    "Hey diese Fähigkeit übersteigt dein Level.\n" +
                            "Bitte wähle eine andere."
                )
            }
        }

        if (swordMultiSpinCooldown >= 0) {
            swordMultiSpinCooldown--
        }
        if (thunderSwordCooldown >= 0) {
            thunderSwordCooldown--
        }

        when (usedAttack) {
            "swordSpin" -> {
                swordSpin(antiHero)


            }

            "knopStrike" -> {
                knopStrike(antiHero)

            }

            "swordMultiSpin" -> {
                swordMultiSpin(mutableList)
                swordMultiSpinCooldown = 3
            }

            "thunderSword" -> {
                thunderSword(antiHero)
                thunderSwordCooldown = 5
            }
        }
    }

    /**
     *  Schleuder das Schwert in richtung des Anti Helden dieser wird mehrfach von der rotierenden
     *  Klinge getroffen und erleidet 15 % seiner gesamten Lebensenergie an Schaden.
     */
    private fun swordSpin(antiHero: AntiHero) {
        val damage = antiHero.maxHealth * damageMultiplier * 0.15
        println(
            "$name schleudert sein schwert auf ${antiHero.name} und zieht ihm 15 % seiner Lebensenergie ab.\n" +
                    "${antiHero.name} hat ${RED}$damage${RESET} Schaden erlitten."
        )
        val useSwordSpin = antiHero.healthbar - damage
        antiHero.healthbar = useSwordSpin.toInt()
    }

    /**
     *  Schlägt mit dem Griff seines Schwertes fest zu und fügt dem Anti Helden 10 % seiner gesamten Lebensenergie
     *  an Schaden zu.
     */
    private fun knopStrike(antiHero: AntiHero) {
        val damage = antiHero.maxHealth * damageMultiplier * 0.1
        println(
            "$name wirft sein Schwert in die luft und fängt es an der Klinge und haut mit dem Griff\n" +
                    "fest auf den Kopf von ${antiHero.name}.\n" +
                    "${antiHero.name} hat ${RED}$damage${RESET} Schaden erlitten."
        )
        val useKnopStrike = antiHero.healthbar - damage
        antiHero.healthbar = useKnopStrike.toInt()
    }

    /**
     *  Wirft ein kreisendes Schwert auf alle Anti Helden und fügt 15 % der gesamten Lebensenergie an Schaden zu.
     *  Nachdem diese Fähigkeit ausgeführt wurde, hat sie 3 Runden Cooldown.
     *
     *  @param list bekommt eine Liste von Anti Helden übergeben.
     */
    private fun swordMultiSpin(list: List<AntiHero>) {
        println(
            "$name wirft sein Schwert in kreisenden Bewegungen und trift jeden Anti Helden.\n" +
                    "Jeder Anti Held erleidet 15 % Schaden."
        )
        for (antiHero in list) {
            val damage = antiHero.maxHealth * damageMultiplier * 0.15
            val useSwordMultiSpin = antiHero.healthbar - damage
            antiHero.healthbar = useSwordMultiSpin.toInt()
            println("${antiHero.name} erleidet ${RED}$damage${RESET} Schaden.")
        }
    }

    /**
     *  Blitze kommen aus dem Himmel geflogen. Diese treffen einen Anti Held mit einem Schaden von 30 %
     *  der gesamten Lebensenergie des AntiHelden.
     *  Nachdem diese Fähigkeit ausgeführt wurde, hat sie 5 Runden Cooldown.
     *
     *  @param antiHero erhält einen Anti Helden aus der antiHeroList.
     */
    private fun thunderSword(antiHero: AntiHero) {
        val damage = antiHero.maxHealth * damageMultiplier * 0.30
        println(
            "$name hält sein Schwert in die Luft und murmelt etwas.\n" +
                    "Plötzlich fängt es an zu Donnern und Blitze schlagen auf ${antiHero.name} ein.\n" +
                    "Er hat ${RED}$damage${RESET} Schaden erlitten."
        )
        val useThunderSword = antiHero.healthbar - damage
        antiHero.healthbar = useThunderSword.toInt()
    }
}
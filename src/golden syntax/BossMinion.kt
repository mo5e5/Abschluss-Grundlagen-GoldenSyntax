import kotlin.random.Random

class BossMinion(name: String, healthbar: Int) : AntiHero(name, healthbar) {

    var drawAttentionHitCooldown = 0
    var shieldCooldown = 0
    var hailBlowCooldown = 0

    /**
     *  Führt die Fähigkeiten vom BossMinion random selber aus.
     *
     *  @param mutableList hier wird eine Liste von Helden übergeben.
     */
    fun bossMinionAttack(mutableList: MutableList<Hero>) {
        var attackNumber = Random.nextInt(0, 4)
        val radomHero = mutableList.random()
        if (shieldCooldown >= 0) {
            shieldCooldown--
        }
        if (hailBlowCooldown >= 0) {
            hailBlowCooldown--
        }
        if (drawAttentionHitCooldown >= 0) {
            drawAttentionHitCooldown--
        }
        if (drawAttentionHitCooldown >= 0 && shieldCooldown >= 0 && hailBlowCooldown >= 0) {
            val listOfAttack = listOf(3)
            attackNumber = listOfAttack.random()
        } else if (drawAttentionHitCooldown >= 0 && shieldCooldown >= 0) {
            val listOfAttack = listOf(2, 3)
            attackNumber = listOfAttack.random()
        } else if (shieldCooldown >= 0 && hailBlowCooldown >= 0) {
            val listOfAttack = listOf(0, 2)
            attackNumber = listOfAttack.random()
        } else if (drawAttentionHitCooldown >= 0) {
            val listOfAttack = listOf(1, 2, 3)
            attackNumber = listOfAttack.random()
        } else if (shieldCooldown >= 0) {
            val listOfAttack = listOf(0, 2, 3)
            attackNumber = listOfAttack.random()
        } else if (hailBlowCooldown >= 4) {
            val listOfAttack = listOf(0, 1, 2)
            attackNumber = listOfAttack.random()
        }
        when (attackNumber) {
            0 -> {
                drawAttentionHit(mutableList)
                drawAttentionHitCooldown = 1
            }

            1 -> {
                shield()
                shieldCooldown = 2
            }

            2 -> {
                punsh(radomHero)
            }

            3 -> {
                hailBlow(mutableList)
                hailBlowCooldown = 4
            }
        }
    }

    /**
     *  Zieht die Aufmerksamkeit aller Helden auf sich und fügt allen Helden Schaden zu.
     *  Jeder Held erleidet 2,5 % Schaden seiner gesamten Lebensenergie.
     *  Nachdem diese Fähigkeit ausgeführt wurde, hat sie 1 Runden Cooldown.
     *
     *  @param list bekommt eine Liste von Helden übergeben.
     */
    fun drawAttentionHit(list: List<Hero>) {
        println(
            "$name blinzelt mit den Wimpern.\n" +
                    "Keiner der Helden kann diesem Wimpernaufschlag wiederstehen.\n" +
                    "Jeder Held erleidet 0.025 % Schaden."
        )
        for (hero in list) {
            val useDrawAttentionHit = hero.healthbar - hero.maxHealth * 0.025
            hero.healthbar = useDrawAttentionHit.toInt()
            println("${hero.name} hat nun noch ${hero.healthbar}.")
        }

    }

    /**
     *  Ein Lebensenergie Boost der 50 % der gesamten Lebensenergie beträgt.
     *  Nachdem diese Fähigkeit ausgeführt wurde, hat sie 2 Runden Cooldown.
     */
    fun shield() {
        println(
            "$name hübscht sich auf.\n" +
                    "Dank einer Tonne Schminke hat $name nun ${maxHealth * 0.5} Lebenspunkte mehr."
        )
        val useShield = healthbar + maxHealth * 0.5
        healthbar = useShield.toInt()
        println("$name hat nun $healthbar Lebenspunkte.")
    }

    /**
     *  Ein kraftvoller Schlag der einem Helden 5 % seiner gesamten Lebensenergie abzieht.
     *
     *  @param hero erhält einen random Helden aus der Helden Liste.
     */
    fun punsh(hero: Hero) {
        println(
            "$name haut kräftig zu und zieht ${hero.name} 5 % seiner maximalen Lebenspunkte ab.\n" +
                    "${hero.name} hatte ${hero.healthbar} Lebenspunkte.\n" +
                    "Er hat ${hero.maxHealth * 0.05} Schaden erlitten."
        )
        val usePunsh = hero.healthbar - hero.maxHealth * 0.05
        hero.healthbar = usePunsh.toInt()
        println("${hero.name} hat nun noch ${hero.healthbar}.")
    }

    /**
     *  Ein Hagel Regen der allen Helden 10 % ihrer gesamten Lebensenergie an Schaden zu fügt.
     *  Nachdem diese Fähigkeit ausgeführt wurde, hat sie 4 Runden Cooldown.
     *
     *  @param list bekommt eine Liste von Helden übergeben.
     */
    fun hailBlow(list: List<Hero>) {
        println(
            "$name lässt es vom Himmel Hageln und fügt jedem Helden 10 % seiner\n" +
                    "gesamten Lebenspunkte als Schaden zu."
        )
        for (hero in list) {
            val useHailBlow = hero.healthbar - hero.maxHealth * 0.1
            hero.healthbar = useHailBlow.toInt()
            println("${hero.name} hat nun noch ${hero.healthbar}.")
        }
    }
}
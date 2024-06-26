open class Hero(name: String, healthbar: Int) : Character(name, healthbar) {

    var damageMultiplier = 1.0
    private var healItemUses = 3
    private var healTeamItemUses = 2
    private var buffItemUses = 1

    /**
     * Greift auf den MagicBag zu und lässt einen Helden ein Item nutzen.
     *
     * @param list bekommt eine Liste von Helden übergeben.
     * @param magicBag bekommt den gefühlten MagicBag übergeben.
     */
    open fun useMagicBag(list: MutableList<Hero>, magicBag: MagicBag): String {

        val listOfItems = listOf("heallItem", "healItemTeam", "buffItem")
        var hero: Hero? = null

        println("Möchtest du deinen Magic Bag nutzen? (y = yes)")
        val userInput = readln().lowercase()

        if (userInput == "y") {
            while (hero == null) {
                try {
                    if (list.isNotEmpty()) {
                        println(
                            "Wer soll in den Magic Bag greifen und diese Runde keine Fähigkeit nutzen?\n" +
                                    "1 = ${list[0].name} 2 = ${list[1].name} 3 = ${list[2].name}"
                        )
                        val choice = readln().toInt()
                        if (choice in 1..list.size) {
                            hero = list[choice - 1]
                        } else {
                            println("Wenn suchst du? Der Held ist nicht verfügbar. Bitte gib eine Zahl zwischen 1 und 3 ein.")
                        }
                    }
                } catch (e: NumberFormatException) {
                    println("Der ausgewählte Held ist nicht verfügbar. Bitte gib eine Zahl ein.")
                } catch (e: IndexOutOfBoundsException) {
                    println("Der ausgewählte Held ist nicht verfügbar. Bitte gib eine Zahl zwischen 1 und 3 ein.")
                }
            }

            println(
                "${hero.name} greift in den Magischen Beutel.\n" +
                        "Nach kurzer Zeit des wühlens findet er ..."
            )
            println()

            println(
                "Du kannst in dieser Runde ${BOLD}$listOfItems${RESET} nutzen.\n" +
                        "Wähle eine Item von 1 - 3."
            )

            var tryUseBag: Int
            var useBag: String

            while (true) {
                try {
                    tryUseBag = readln().toInt()
                    if (tryUseBag !in 1..3) {
                        println(
                            "Das Item ist nicht im Beutel.\n" +
                                    "Greif nochmal rein."
                        )
                        continue
                    }
                    useBag = listOfItems[tryUseBag - 1]
                    if ((useBag == "heallItem" && healItemUses == 0) ||
                        (useBag == "healItemTeam" && healTeamItemUses == 0) ||
                        (useBag == "buffItem" && buffItemUses == 0)
                    ) {
                        println(
                            "$useBag ist schon aufgebraucht.\n" +
                                    "Versuche ein anderes."
                        )
                        continue
                    }
                    break
                } catch (e: NumberFormatException) {
                    println("Diese Items sind nicht in dieser Welt. Bitte gib eine Zahl zwischen 1 und 3 ein.")
                }

            }

            when (useBag) {
                "heallItem" -> {
                    magicBag.heallItem(hero)
                    healItemUses--


                }

                "healItemTeam" -> {
                    magicBag.healItemTeam(list)
                    healTeamItemUses--

                }

                "buffItem" -> {
                    magicBag.buffItem(hero)
                    buffItemUses--
                }
            }
            return hero.name
        }
        return ""
    }
}


/**
 *  Gibt eine Information aus, wenn ein Character keine Lebensenergie mehr hat.
 *
 *  @param character bekommt einen beliebigen Character übergeben.
 */
fun charakterStatusCheck(character: Character) {
    if (character.healthbar <= 0) {
        println("${character.name} hat keine Lebensenergie mehr. Jeder weitere Treffer macht die Blutlache nur noch größer.")
        return
    }
}

/**
 *  Überprüft ob hero und antiHero Lebensenergie haben. Wenn Ja, werden diese wiedergeben, wenn Nein wird der
 *  hero oder antiHero nicht mehr ausgegeben.
 *
 *  @param listGood wird eine Liste vom Typ hero übergeben.
 */
fun lifeAndDeath(listGood: MutableList<Hero>) {
    println("Helden und deren Lebensenergie.")
    println()
    listGood.removeIf { it.healthbar <= 0 }
    for (hero in listGood) {
        if (hero.healthbar >= 0) {
            println("${hero.name} hat ${hero.healthbar} Lebensenergie.")
        }
    }
    println(
        """
        
    """.trimIndent()
    )
    println("Anti Helden und deren Lebensenergie.")
    println()
    ANTIHEROLIST.removeIf { it.healthbar <= 0 }
    for (antiHero in ANTIHEROLIST) {
        if (antiHero.healthbar >= 0) {
            println("${antiHero.name} ${antiHero.healthbar} Lebensenergie.")
        }
    }
    println(
        """
        
    """.trimIndent()
    )
}

val RED = "\u001B[31m"
val GREEN = "\u001B[32m"
val BLACK = "\u001B[30m"
val BOLD = "\u001B[1m"
val BACKGROUNDWHIGHT = "\u001B[47m"
val BACKGROUNBLACK = "\u001B[40m"
val RESET = "\u001B[0m"
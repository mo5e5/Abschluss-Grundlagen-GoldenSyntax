class Boss(name: String, healthbar: Int) : AntiHero(name, healthbar) {

    val bossActionList = mutableListOf(
        bossMinion(list = antiHeroList),
        shield(),
        lifeRegenaration(),
        stomp(hero = Hero("", 0)),
        crush(hero = Hero("", 0)),
        curse(list = heroList),
        digest(list = heroList)
    )

    /**
     *  Ruft den BossMinion zur Unterstützung hinzu.
     */
    private fun bossMinion(list: MutableList<AntiHero>) {
        if (!antiHeroList.contains(siphi)) {
            println(
                "$name beschwört mit all seiner macht Siphi\n" +
                        "um ihm im Kampf gegen die Helden zu helfen."
            )
            antiHeroList.add(siphi)
        }
    }

    /**
     *  Ein Schild der 50 % der gesamten Lebensenergie beträgt. Er hält bis er zerstört wird.
     *  Hat nach seiner zerstörung 3 Runden Cooldown.
     */
    private fun shield() {
        val useShield = elegaius.healthbar + elegaius.maxHealth * 0.5
        healthbar = useShield.toInt()
    }

    /**
     *  Regeneriert 15 % seiner gesamten Lebensenergie.
     */
    private fun lifeRegenaration() {
        val useLifeRegenaration = elegaius.healthbar + elegaius.maxHealth * 0.15
        healthbar = useLifeRegenaration.toInt()
    }

    /**
     *  Stampft auf einem Helden herum was dem Helden 10 % seiner gesamten Lebensenergie an Schaden
     *  zufügt.
     */
    private fun stomp(hero: Hero) {
        val useStomp = hero.healthbar - hero.maxHealth * 0.1
        hero.healthbar = useStomp.toInt()
    }

    /**
     *  Zerquetscht einen Helden was diesem 15 % seiner gesamten Lebensenergie an Schaden
     *  zufügt.
     */
    private fun crush(hero: Hero) {
        val useCrush = hero.healthbar - hero.maxHealth * 0.15
        hero.healthbar = useCrush.toInt()
    }

    /**
     *  Verflucht alle Helden die daraufhin für 3 Runden nur 80 % ihres normalen Schadens an ihm machen.
     */
    private fun curse() {
    }

    /**
     *  Bespuckt alle Helden mit Magensäure. Die Helden erleiden für 2 Runden 5 % ihrer
     *  gesamten Lebensenergie an Schaden.
     */
    private fun digest() {
    }
}
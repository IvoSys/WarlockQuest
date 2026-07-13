public class SpellViciousSeed extends Spell {

    public SpellViciousSeed() {
        name = "Üble Saat";
        desc = "Pflanzt einen Keim in einem Gegner ein, der nach " + dur + " Runden \noder mit Tod des Gegners aufplatzt und bei allen Gegnern Schaden verursacht.";
        formula = Story.formulaViciousSeed;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.carriesVSeed = true;
            target.counterVSeed = dur;
            System.out.printf("Maleficarius pflanzt einen Keim des Übels in %s, der in %d Runden aufspringen wird. \n", target.name, dur);
        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterVSeed--;
        if (e.counterVSeed <= 0) {
            explode(e);
        } else
            System.out.println("Die üble Saat in " + e.name + " keimt …");
    }

    @Override
    public void explode(Enemy e) {
        e.carriesVSeed = false;
        System.out.println("Der Keim des Übels in " + e.name + " platzt auf.");
        for (Enemy f : Player.room.encounter.enemyTeam) {
            if (!f.ko)
                f.applyDmg(str);
        }
    }


}

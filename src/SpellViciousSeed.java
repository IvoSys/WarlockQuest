public class SpellViciousSeed extends Spell {

    public SpellViciousSeed() {
        name = "Üble Saat";
        desc = "Pflanzt einen Keim in einem Gegner ein, der nach " + dur + " Runden \noder mit Tod des Gegners aufplatzt und bei allen Gegnern Schaden verursacht.";
        formula = Story.formulaViciousSeed;
        textWhenCast = "";
        textWhenLearned = "";
        str = 10;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        target.carriesVSeed = true;
        target.counterVSeed = dur;
        System.out.println(textWhenCast);
    }

    @Override
    public void tick(Enemy e) {
        e.counterVSeed--;
        if (e.counterVSeed <= 0) {
            explode();
        } else
            System.out.println("Die üble Saat keimt weiter …");
    }

    @Override
    public void explode() {
        System.out.println("Ein Keim des Übels platzt auf.");
        for (Enemy e : Player.room.encounter.enemyTeam) {
            e.applyDmg(str);
        }
    }


}

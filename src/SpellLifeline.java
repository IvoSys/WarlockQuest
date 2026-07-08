public class SpellLifeline extends Spell {

    public SpellLifeline() {
        name = "Lebenslinie";
        desc = "Spannt ein magisches Band zwischen deinem Dämon und einem Gegner. \nIn den folgenden Runden geht vor jeder Aktion dieses Gegners \nLebenskraft von ihm auf deinen Dämon über.";
        formula = Story.formulaLifeline;
        textWhenCast = "";
        textWhenLearned = "";
        str = 10;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    @Override
    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        target.lifelined = true;
        target.counterlifeline = dur;

        System.out.println(textWhenCast);
    }

    @Override
    public void tick(Enemy e) {
        int hpRestored = str;
        e.hp -= str;
        if (e.hp <= 0) {
            hpRestored += e.hp;
            e.hp = e.hpMax;
            e.ko = true;
            e.lifelined = false;
        }
        e.counterlifeline--;
        if (e.counterlifeline == 0) {
            e.lifelined = false;
        }
        Player.activeDemon.applyHeal(hpRestored);

    }

}

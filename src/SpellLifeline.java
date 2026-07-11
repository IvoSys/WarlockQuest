public class SpellLifeline extends Spell {

    public SpellLifeline() {
        name = "Lebenslinie";
        desc = "Spannt ein magisches Band zwischen deinem Dämon und einem Gegner. \nIn den folgenden Runden geht vor jeder Aktion dieses Gegners \nLebenskraft von ihm auf deinen Dämon über.";
        formula = Story.formulaLifeline;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    @Override
    public void cast(int enemyIndex) {
        if (Player.applyMpCost(mpCost)) {

            Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

            target.lifelined = true;
            target.counterlifeline = dur;

            System.out.printf("Maleficarius knüpft ein magisches Band zwischen %s und %s. \n", target.name, Player.activeDemon.name);
        }
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

        System.out.printf("Über die Lebenslinie fließen %d HP von %s zu %s. \n", hpRestored, e.name, Player.activeDemon.name);
        if (!e.lifelined)
            System.out.println("Die Lebenslinie erlischt.");
        if (e.ko) {
            System.out.println(e.name + " überlebt den Lebensentzug nicht.");
            if (e.carriesVSeed) {
                WorldBuilder.viciousSeed.explode(e);
            }
        }
    }

}

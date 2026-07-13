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

            System.out.printf("Maleficarius knüpft ein magisches Band zwischen %s und %s. \n", target.name, Battle.demon.name);
        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        int hpRestored = str;
        e.hp -= str;
        if (e.hp <= 0) {
            hpRestored += e.hp;
            System.out.println(e.name + " überlebt den Lebensentzug nicht.");
            e.die();
        }
        e.counterlifeline--;
        if (e.counterlifeline == 0) {
            e.lifelined = false;
        }
        Battle.demon.applyHeal(hpRestored);

        System.out.printf("Über die Lebenslinie fließen %d HP von %s zu %s. \n", hpRestored, e.name, Battle.demon.name);
        if (!e.ko && !e.lifelined) {
            System.out.println("Die Lebenslinie erlischt.");
        }

    }

}

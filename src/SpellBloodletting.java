public class SpellBloodletting extends Spell{

    public SpellBloodletting() {
        name = "Aderlass";
        desc = "Opfert Lebenspunkte deines Dämon, um deine Manareserven aufzufüllen.";
        formula = Story.formulaBloodletting;
        textWhenLearned = "";
        str = Player.spellpower;
        mpCost = 0;
        aoe = false;
        onDemon = true;
    }

    @Override
    public void cast() {
        int mpRestored = str;
        Battle.demon.hp -= str;
        if (Battle.demon.hp <= 0) {
            mpRestored += Battle.demon.hp;
            Battle.demon.hp = 0;
            Battle.demon.ko = true;
        }
        Player.mp += mpRestored;
        if (Player.mp > Player.mpMax) {
            Player.mp = Player.mpMax;
        }
        System.out.println("Maleficarius opfert " + mpRestored + " HP seines Dämons und füllt seinen Manavorrat um denselben Wert auf.");
        if (Battle.demon.ko) {
            System.out.println(Battle.demon.name + " übelebt den Lebensentzug nicht.");
        }
    }

}

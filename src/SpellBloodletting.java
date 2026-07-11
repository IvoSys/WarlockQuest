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
        Player.activeDemon.hp -= str;
        if (Player.activeDemon.hp <= 0) {
            mpRestored += Player.activeDemon.hp;
            Player.activeDemon.hp = 0;
            Player.activeDemon.ko = true;
        }
        Player.mp += mpRestored;
        if (Player.mp > Player.mpMax) {
            Player.mp = Player.mpMax;
        }
        System.out.println("Maleficarius opfert " + mpRestored + " HP seines Dämons und füllt seinen Manavorrat um denselben Wert auf.");
        if (Player.activeDemon.ko) {
            System.out.println(Player.activeDemon.name + " übelebt den Lebensentzug nicht.");
        }
    }

}

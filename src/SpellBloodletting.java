public class SpellBloodletting extends Spell{

    public SpellBloodletting() {
        name = "Aderlass";
        desc = "Opfert Lebenspunkte deines Dämon, um deine Manareserven aufzufüllen.";
        formula = Story.formulaBloodletting;
        textWhenCast = "";
        textWhenLearned = "";
        str = 10;
        mpCost = 10;
        aoe = false;
    }

    @Override
    public void cast() {
        int mpRestored = str;
        Player.activeDemon.hp =- str;
        if (Player.activeDemon.hp < 0) {
            mpRestored =+ Player.activeDemon.hp;
            Player.activeDemon.hp = 0;
            Player.activeDemon.ko = true;
        }
        Player.mp += mpRestored;
        if (Player.mp > Player.mpMax) {
            Player.mp = Player.mpMax;
        }
        System.out.println(textWhenCast);
    }

}

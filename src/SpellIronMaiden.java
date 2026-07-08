public class SpellIronMaiden extends Spell {

    public SpellIronMaiden() {
        name = "Eiserne Jungfrau";
        desc = "Verflucht alle Gegner, sodass sie sich selbst verletzen, wenn sie deinem Dämon Schaden zufügen.";
        formula = Story.formulaIronMaiden;
        textWhenCast = "";
        textWhenLearned = "";
        str = 10;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

}

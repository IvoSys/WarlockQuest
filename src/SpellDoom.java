public class SpellDoom extends Spell {

    public SpellDoom() {
        name = "Untergang";
        desc = "Verflucht einen Gegner, sodass er über mehrere Runden hinweg stetig ansteigenden Schaden erleidet.";
        formula = Story.formulaDoom;
        textWhenCast = "";
        textWhenLearned = "";
        str = 10;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }



}

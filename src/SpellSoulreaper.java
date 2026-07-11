public class SpellSoulreaper extends Spell {

    public SpellSoulreaper() {
        name = "Seelendieb";
        desc = "Stiehlt die Seele eines geschwächten Feindes. \nAuf einen Gegner mit unter 20 % HP angewendet, \nstirbt dessen Körper sofort und seine Seele füllt deinen Manavorrat.";
        formula = Story.formulaSoulreaper;
        textWhenLearned = "";
        str = Player.spellpower;
        mpCost = 10;
        aoe = false;
    }

    @Override
    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (target.hp <= target.hpMax * 0.2f ) {
            target.hp = 0;
            target.ko = true;
            target.lifelined = false;
            Player.mp += str;
            if (Player.mp > Player.mpMax) {
                Player.mp = Player.mpMax;
            }
            System.out.println("Ein blaues Band spannt sich zwischen deinem Finger und der Brust des Gegners. \n" + target.name + " fällt leblos zu Boden und Maleficarius fühlt sich erfrischt.");
        } else {
            System.out.println("Der Zauber zeigt keine Wirkung.");
        }
    }

}

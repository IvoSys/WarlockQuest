import java.util.Random;

public abstract class Enemy {

    Random rnd = new Random();

    String name;
    String weapon;
    int hp;
    int hpMax;
    int power;
    boolean hasPotion;
    int potionStr;
    boolean ko = false;

    int numOptions;

    boolean lifelined = false;
    int counterlifeline;

    boolean carriesVSeed = false;
    int counterVSeed;

    // Konstruktor
    public Enemy() {}

    //Setter
    public void applyDmg(int dmg){
        boolean obliterated = false;

        hp -= dmg;
        //if (hp <= -10)
        //    obliterated = true;
        if (hp < 0) {
            hp = 0;
            ko = true;
            lifelined = false;
            carriesVSeed = false;
        }
        System.out.printf("%s erhält %d Schaden", name, dmg);
        //if (obliterated) {
        //    System.out.println(" und " + Player.activeDemon.obliterated);
        //} else if
        if (ko)
            System.out.println(" und stirbt.");
        else
            System.out.println(".");
    }

    public void applyHeal(int heal){
        hp += heal;
        if (hp > hpMax) {
            hp = hpMax;
        }
        System.out.printf("%s wird um %d HP geheilt", name, heal);
        if (hp == hpMax)
            System.out.println(" und strotzt vor Energie.");
        else
            System.out.println(".");
    }

    public void drinkPotion(){
        if (hasPotion) {
            hp += potionStr;
            hasPotion = false;
            System.out.printf("%s trinkt einen Heiltrank und", name);
            if (hp > hpMax) {
                hp = hpMax;
                System.out.println(" ist wieder kerngesund.");
            } else
                System.out.printf(" heilt sich um %d HP. \n", potionStr);
        } else {
            System.out.printf("%s hat keinen Heiltrank mehr.", name);
        }
    }


    public int attack(){
        int dmg = rnd.nextInt(11) + power;
        System.out.printf("%s greift mit dem %s an. \n", name, weapon);
        return dmg;
    }

    public void ability1(){}

    public void ability2(){}

    public void ability3(){}


}

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
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
            ko = true;
            lifelined = false;
            carriesVSeed = false;
        }
        System.out.printf("%s erhält %d Schaden", name, dmg);
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

    public int applyLifelineDmgReturnHeal(int dmg){
        int heal = dmg;
        hp -= dmg;
        if (hp <= 0) {
            heal += hp;
            hp = 0;
            ko = true;
        }
        System.out.println("Während die Haut des Gegners welk wird und bricht, \n wird dein Dämon immer kräftiger.");
        return heal;
    }

    public void checkLifelined(){
        if (lifelined) {
            Player.activeDemon.applyHeal(applyLifelineDmgReturnHeal(Player.spellpower));
            counterlifeline--;
            if (counterlifeline == 0) {
                lifelined = false;
            }
        }
    }

    public void checkVSeed(){
        if (carriesVSeed) {
            counterVSeed--;
            if (counterVSeed == 0) {

            }

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

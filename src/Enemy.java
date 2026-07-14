import java.util.Random;

public abstract class Enemy {

    Random rnd = new Random();

    String name;
    String weapon;
    int hp;
    int hpMax;
    int power;
    int dex;
    boolean meele = true;
    boolean hasPotion;
    int potionStr;
    boolean ko = false;

    int numOptions;

    boolean doomed = false;
    int counterDoom;

    boolean lifelined = false;
    int counterlifeline;

    boolean inIronMaiden = false;
    int counterIronMaiden;

    boolean carriesVSeed = false;
    int counterVSeed;


    // Konstruktor
    public Enemy() {}

    //Setter
    public void applyDmgEvade(int dmg){
        if (Battle.rnd.nextInt(100) < dex)
            System.out.println(name + " weicht aus!");
        else {
            hp -= dmg;
            System.out.printf("%s erhält %d Schaden", name, dmg);
            if (hp > 0) {
                System.out.println(".");
            } else {
                hp = 0;
                System.out.println(" und stirbt.");
                die();
            }
        }
    }

    public void applyDmg(int dmg){
        hp -= dmg;
        System.out.printf("%s erhält %d Schaden", name, dmg);
        if (hp > 0) {
            System.out.println(".");
        } else {
            hp = 0;
            System.out.println(" und stirbt.");
            die();
        }
    }

    public void die(){
        hp = 0;
        ko = true;
        doomed = false;
        lifelined = false;
        inIronMaiden = false;
        if (carriesVSeed)
            WorldBuilder.viciousSeed.explode(this);
    }

    public void applyMaiden(int dmg){
        System.out.printf("%s verletzt sich durch den Fluch \"%s\" selbst: ", name, WorldBuilder.ironMaiden.name);
        applyDmg(dmg);
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
        System.out.printf("%s greift mit dem %s an – ", name, weapon);
        if (inIronMaiden)
            applyMaiden(dmg);
        if (Battle.demon == WorldBuilder.dem02 && WorldBuilder.dem02.isBlazing && meele) {
            applyDmg(WorldBuilder.dem02.power / 2);
            System.out.printf("%s verbrennt sich und erleidet %d Schaden. \n", name, WorldBuilder.dem02.power / 2);
        }
        return dmg;
    }

    public void ability1(){}

    public void ability2(){}

    public void ability3(){}

}

class Soldier extends Enemy{

    public Soldier(String name, String weapon, int hp, int power, int dex, boolean hasPotion) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.hasPotion = hasPotion;
        potionStr = 40;
    }


    public int attack(){
        return (super.attack());
    }

    public void ability1() {
    }

    public void ability2() {

    }

}

class Knight extends Enemy{

    public Knight() {
    }

    public int attack(){
        return (super.attack());
    }

    public void ability1() {
        int dmg = power;

    }

    public void ability2() {

    }

}

class Rogue extends Enemy{

    public Rogue() {
    }

    public int attack(){
        return (super.attack());
    }

    public void ability1() {
        int dmg = power;

    }

    public void ability2() {
        int dmg = power;

    }

}

class Mage extends Enemy{

    public Mage() {
    }

    public int attack(){
        return (super.attack());
    }

    public void ability1() {
    }

    public void ability2() {
        int dmg = power;

    }

}


class Cleric extends Enemy {

    public Cleric() {
    }

    public int attack(){
        return (super.attack());
    }

    public void ability1() {
    }

    public void ability2() {
        int dmg = power;

    }

}







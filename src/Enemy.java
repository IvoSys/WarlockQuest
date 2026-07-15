import java.util.Random;

public abstract class Enemy {

    Random rnd = new Random();

    String name;
    String weapon;
    int hp;
    int hpMax;
    int power;
    int dex;
    boolean meele;
    boolean hasPotion;
    int potionStr;
    boolean ko = false;

    int numOptions;

    boolean bitesOn = false;

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
        bitesOn = false;
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
        int dmg = rnd.nextInt(6) + power;
        System.out.printf("%s greift mit %s an – ", name, weapon);
        if (inIronMaiden) {
            System.out.printf("\"%s\" wirkt: ", WorldBuilder.ironMaiden.name);
            applyMaiden(dmg);
        }
        if (Battle.demon == WorldBuilder.dem02 && WorldBuilder.dem02.isBlazing && meele) {
            System.out.printf("%s ist in Flammen gehüllt: ", WorldBuilder.dem02.name);
            applyDmg(WorldBuilder.dem02.power / 2);
        }
        return dmg;
    }

    public void ability1(){}

    public void ability2(){}

    public void ability3(){}

}




// ========== EBENE 0: nur attack() ==========

// ========== Wache ==========
class Guard extends Enemy{

    public Guard(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        numOptions = 1;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}

// ========== Wachhund ==========
class Watchdog extends Enemy{

    public Watchdog(String name) {
        numOptions = 1;
        this.name = name;
        weapon = "seinen Zähnen";
        hp = 40;
        hpMax = hp;
        power = 5;
        dex = 10;
    }

    public int attack(){
        return (super.attack());
    }
}


// ========== EBENE 1: +1 Fähigkeit ==========

// ========== Soldat ==========
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

// ========== Schütze ==========
class Archer extends Enemy{

    public Archer() {
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

// ========== Zauberlehrling ==========
class Apprentice extends Enemy{

    public Apprentice() {
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

// ========== Priester ==========
class Novice extends Enemy {

    public Novice() {
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



// ========== EBENE 2: +2 Fähigkeiten ==========

// ========== Ritter ==========
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

// ========== Waldläufer ==========
class Ranger extends Enemy{

    public Ranger(String name, String weapon) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}

// ========== Magier ==========
class Mage extends Enemy{

    public Mage(String name, String weapon) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}

// ========== Kleriker ==========
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


// ========== EBENE 3: BOSS ==========
class Boss01 extends Enemy{

    public Boss01(String name, String weapon) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}

class Boss02 extends Enemy{

    public Boss02(String name, String weapon) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}

class Boss03 extends Enemy{

    public Boss03(String name, String weapon) {
        numOptions = 1;
        this.name = name;
        this.weapon = weapon;
        hp = 60;
        hpMax = hp;
        power = 5;
        dex = 5;
    }

    public int attack(){
        return (super.attack());
    }
}




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
    int potions;
    int potionStr;

    boolean ko = false;

    boolean bitesOn = false;

    boolean doomed = false;
    int counterDoom;

    boolean lifelined = false;
    int counterlifeline;

    boolean inIronMaiden = false;
    int counterIronMaiden;

    boolean carriesVSeed = false;
    int counterVSeed;


    // Setter und Klassen-Methoden
    //region

    // Konstruktor
    public Enemy() {}

    //Dmg-Setter
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

    public void applyMaidenDmg(int dmg){
        System.out.printf("%s verletzt sich durch den Fluch \"%s\" selbst: ", name, WorldBuilder.ironMaiden.name);
        applyDmg(dmg);
    }

    //Heilung
    public void drinkPotion(){
        if (potions > 0) {
            hp += potionStr;
            potions -= 1;
            System.out.printf("%s trinkt einen Heiltrank und", name);
            if (hp >= hpMax) {
                hp = hpMax;
                System.out.println(" ist wieder kerngesund.");
            } else
                System.out.printf(" heilt sich um %d HP. \n", potionStr);
        } else {
            System.out.printf("%s hat keinen Heiltrank mehr.", name);
        }
    }

    public void applyHeal(int heal){
        hp += heal;
        if (hp > hpMax) {
            hp = hpMax;
        }
        System.out.printf("%s wird um %d HP geheilt", name, heal);
        if (hp == hpMax)
            System.out.println(" und ist wieder kerngesund.");
        else
            System.out.println(".");
    }

    public void applyRevive(int heal){
        if (ko) {
            ko = false;
            hp = heal;
        } else {
            System.out.println(name + " ist am Leben und kann nicht wiederbelebt werden.");
        }

    }

    //Tod
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

    //Standard-Angriff
    public int attack(){
        int dmg = rnd.nextInt(6) + power;
        if (inIronMaiden) {
            System.out.printf("\"%s\" wirkt: ", WorldBuilder.ironMaiden.name);
            applyMaidenDmg(dmg);
        }
        if (Battle.demon == WorldBuilder.dem02 && WorldBuilder.dem02.isBlazing && meele) {
            System.out.printf("%s ist in Flammen gehüllt: ", WorldBuilder.dem02.name);
            applyDmg(WorldBuilder.dem02.power / 2);
        }
        return dmg;
    }

    public int heal() {
        return rnd.nextInt(6) + power * 2;
    }

    public int revive() {
        return rnd.nextInt(6) + power;
    }

//endregion
}


class Guard extends Enemy{

    public Guard(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}


class Watchdog extends Enemy{

    public Watchdog(String name, int hp, int power, int dex) {
        this.name = name;
        weapon = "einem Biss";
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        potions = 0;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}


class Soldier extends Enemy{

    public Soldier(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }

}


class Archer extends Enemy{

    public Archer(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = false;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }

}


class Apprentice extends Enemy{

    public Apprentice(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = false;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }

}


class Novice extends Enemy {

    public Novice(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return super.attack();
    }

    @Override
    public int heal() {
        System.out.printf("%s wirkt einen Heilzauber – ", name);
        return super.heal();
    }

}


class Knight extends Enemy{

    int block;

    public Knight(String name, String weapon, int hp, int power, int dex, int block, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.block = block;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    //applyDmgEvade-Setter: Ausweichen wird zu Parieren und Kontern.
    @Override
    public void applyDmgEvade(int dmg){
        if (Battle.rnd.nextInt(100) < (dex + block)) {     // Ritter ist nicht schnell, hat aber eine solide Chance zum Blocken und Parieren.
            if (Battle.demon.melee) {
                System.out.println(name + " pariert den Schlag und kontert!");
                Battle.counterAtk = true;
            } else
                System.out.println(name + " blockt den Angriff mit dem Schild.");
        }
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

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }

}

// ========== Waldläufer ==========
class Ranger extends Enemy{

    public Ranger(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = false;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}

// ========== Magier ==========
class Mage extends Enemy{

    public Mage(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = false;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}

// ========== Kleriker ==========
class Cleric extends Enemy {

    public Cleric(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }

    @Override
    public int heal() {
        return super.heal();
    }

    @Override
    public int revive() {
        return super.revive();
    }

    public void breakCurse() {
    }

}


// ========== EBENE 3: BOSS ==========
class Boss01 extends Enemy{

    public Boss01(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}

class Boss02 extends Enemy{

    public Boss02(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}

class Boss03 extends Enemy{

    public Boss03(String name, String weapon, int hp, int power, int dex, int potions) {
        this.name = name;
        this.weapon = weapon;
        meele = true;
        this.hp = hp;
        hpMax = hp;
        this.power = power;
        this.dex = dex;
        this.potions = potions;
        potionStr = hpMax / 2;
    }

    @Override
    public int attack(){
        System.out.printf("%s greift mit %s an – ", name, weapon);
        return (super.attack());
    }
}




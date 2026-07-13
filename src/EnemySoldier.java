public class EnemySoldier extends Enemy{

    public EnemySoldier(String name, String weapon, int hp, int power, int dex, boolean hasPotion) {
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

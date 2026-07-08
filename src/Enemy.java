public abstract class Enemy {

    String job;
    String name;
    int hp;
    int hpMax;
    int str;
    boolean ko = false;
    boolean hasPotion;
    int potionStr = 50;

    int numOptions = 2;

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
        }
    }

    public void applyHeal(int heal){
        hp += heal;
        if (hp > hpMax) {
            hp = hpMax;
        }
    }

    public int applyLifelineDmgReturnHeal(int dmg){
        int heal = dmg;
        hp -= dmg;
        if (hp <= 0) {
            heal =+ hp;
            hp = 0;
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

    public int attack(){
        int dmg = str;
        System.out.printf("%s %s greift an und verursacht %d Schaden. \n", job, name, dmg);
        return dmg;
    }

    public void ability1(){}

    public void ability2(){}

    public void ability3(){}


}

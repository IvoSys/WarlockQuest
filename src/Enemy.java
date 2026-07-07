public abstract class Enemy {

    String job;
    String name;
    int hp;
    int hpMax;
    int str;
    boolean ko = false;
    boolean lifelined = false;
    int counterLifeline;

    // Konstruktor
    public Enemy() {}

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    public int applyLifelineDmg(int dmg){
        int heal = dmg;
        hp -= dmg;
        if (hp < 0) {
            heal =+ hp;
            hp = 0;
        }
        return heal;
    }

    public void checkLifelined(){
        if (lifelined) {
            Player.activeDemon.applyHeal(applyLifelineDmg(Player.spellpower));
            counterLifeline--;
            if (counterLifeline == 0) {
                lifelined = false;
            }
        }
    }





    public abstract int attack();
    // greift an, danach
    // if (lifelined)
    //    applyDmg(int);
    //

    public abstract int ability();

    public abstract void utility();


}

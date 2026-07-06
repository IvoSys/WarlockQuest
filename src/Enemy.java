public abstract class Enemy {

    String name;
    int hp;
    int hpMax;
    int str;
    boolean ko = false;

    // Konstruktor

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    public abstract int attack();

    public abstract int ability();

}

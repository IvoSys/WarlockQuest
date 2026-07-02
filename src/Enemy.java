public class Enemy {

    String name;
    int hp;
    int maxHp;
    boolean ko = false;

    // Konstruktor

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    //Angriff

    //Spezialangriff
}

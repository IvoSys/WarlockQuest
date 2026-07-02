public class Demon {

    String shortName;
    String trueName;
    String bound;
    int hp;
    int maxHp;
    boolean ko = false;

    public Demon (String shortName, String trueName, int hp, String bound) {
        this.shortName = shortName;
        this.trueName = trueName;
        this.hp = hp;
        this.maxHp = hp;
        this.bound = bound;
    }

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    //Angriff

    //Spezialangriff


}

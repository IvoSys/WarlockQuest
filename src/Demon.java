public class Demon {

    String shortName;
    String trueName;
    int hp;
    int maxHp;
    boolean ko = false;

    public Demon (String shortName, String trueName, int hp) {
        this.shortName = shortName;
        this.trueName = trueName;
        this.hp = hp;
        this.maxHp = hp;
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

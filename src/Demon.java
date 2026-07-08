public abstract class Demon {

    String name;
    String trueName;
    String textWhenSummoned;
    String textWhenBound;

    int hp;
    int hpMax;
    int power = 10;

    boolean ko = false;

    public Demon () {}

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
        if (hp == 0)
            ko = true;
    }

    public void applyHeal(int heal){
        if (!ko) {
            hp += heal;
            if (hp > hpMax)
                hp = hpMax;
        }
    }


    public abstract int attack();

    public abstract int ability1();

    public abstract int ability2();

    public static void summon(int index){
        Player.activeDemon = Player.team.get(index);
        System.out.println(Player.activeDemon.textWhenSummoned);
    }


}

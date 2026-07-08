public abstract class Demon {

    String name;
    String trueName;
    String desc;
    String textWhenSummoned;
    String textWhenBound;

    int hp;
    int hpMax;
    int power;
    int dex;

    boolean ko = false;

    String attackName;
    String attackDesc;
    String ability1Name;
    String ability1Desc;
    String ability2Name;
    String ability2Desc;

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
        hp += heal;
        if (hp > hpMax)
            hp = hpMax;
    }

    public static void showDemons (){
        System.out.println("=============DÄMONEN==============");
        if (Player.team.isEmpty())
            System.out.println("Du hast noch keine Dämonen gebunden.");
        else
            for (Demon d : Player.team) {
                System.out.print(d.name.toUpperCase());
                System.out.printf("\t\t\t(%d HP)", d.hpMax);
                System.out.println(d.desc);
                System.out.println("-----------------------------------");
                System.out.println("Angriff: " + d.attackName);
                System.out.println(d.attackDesc);
                System.out.println("Fähigkeit 1: " + d.ability1Name);
                System.out.println(d.ability1Desc);
                System.out.println("Fähigkeit 2: " + d.ability2Name);
                System.out.println(d.ability2Desc);
                System.out.println();
            }
        System.out.println("==================================");
    }

    public abstract int attack();

    public abstract int ability1();

    public abstract int ability2();

    public static void summon(int index){
        Player.activeDemon = Player.team.get(index);
        System.out.println(Player.activeDemon.textWhenSummoned);
    }


}

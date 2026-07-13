public abstract class Demon {

    String name;
    String trueName;
    String desc;
    String textWhenSummoned;
    String textWhenBound;
    String obliterated;

    int hp;
    int hpMax;
    int hpBase;
    int power;
    int powerBase;
    int dex;
    int level;

    boolean ko = false;

    String attackName;
    String attackDesc;
    String aoeAttackName;
    String aoeAttackDesc;
    String aoeAttackBattleDesc;
    String selfBuffName;
    String selfBuffDesc;


    public Demon () {}

    public static void showDemons (){
        System.out.println("=============DÄMONEN==============\n");
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
                System.out.println("Fähigkeit 1: " + d.aoeAttackName);
                System.out.println(d.aoeAttackDesc);
                System.out.println("Fähigkeit 2: " + d.selfBuffName);
                System.out.println(d.selfBuffDesc);
                System.out.println();
            }
        System.out.println("==================================");
    }

    public void toPlayerLevel(){
        level = Player.level;

        switch (level) {
            case 1: hpMax = hpBase; power = powerBase; break;
            case 2: hpMax = (int) (hpBase * 1.3f); hp = hpMax; power = (int) (powerBase * 1.3f); break;
            case 3: hpMax = (int) (hpBase * 1.6f); hp = hpMax; power = (int) (powerBase * 1.6f); break;
            case 4: hpMax = hpBase * 2; hp = hpMax; power = powerBase * 2; break;
            case 5: hpMax = (int) (hpBase * 2.5f); hp = hpMax; power = (int) (powerBase * 2.5f); break;
            default: level = 5; hpMax = (int) (hpBase * 2.5f); power = (int) (powerBase * 2.5f); break;
        }
    }

    public static void summon(int index){
        Battle.demon = Player.team.get(index);
        System.out.println(Battle.demon.textWhenSummoned);
    }


    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
        if (hp == 0) {
            ko = true;
        }
    }

    public void applyHeal(int heal){
        hp += heal;
        if (hp > hpMax)
            hp = hpMax;
    }


    //Angriffe
    public abstract int attack();

    public abstract int aoeAttack();

    public abstract void selfBuff();




}

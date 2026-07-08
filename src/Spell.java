public abstract class Spell {

    protected String name;
    protected String desc;
    protected String formula;
    protected String textWhenCast;
    protected String textWhenLearned;
    protected int str;
    protected int dur;
    protected int mpCost;
    protected boolean aoe;

    public Spell() {}

    public static int pickTarget(){
        int counter = 1;
        int enemyIndex;
        for (Enemy e : Player.room.encounter.enemyTeam) {
            System.out.printf("[%d] %s %s \t (%d/%d HP) \n", counter, e.job, e.name, e.hp, e.hpMax);
            counter++;
        }
        enemyIndex = Battle.sc.nextInt() - 1;
        return enemyIndex;
    }

    public void cast(int enemyIndex) {      // Single target
    }

    public void cast() {                    // AOE
    }

    public void tick(Enemy e) {             // Verzögerte Wirkung setzt bei betroffenem Gegner ein
    }

    public void explode() {

    }

}

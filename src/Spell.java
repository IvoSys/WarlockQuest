public abstract class Spell {

    protected String name;
    protected String desc;
    protected String formula;
    protected String textWhenLearned;
    protected int str;
    protected int dur;
    protected int mpCost;
    protected boolean aoe;
    protected boolean onDemon = false;

    public Spell() {}

    public static int pickTarget(){
        int counter = 1;
        int enemyIndex;
        for (Enemy e : Player.room.encounter.enemyTeam) {
            System.out.printf("[%d] %s \t (%d/%d HP) \n", counter, e.name, e.hp, e.hpMax);
            counter++;
        }
        enemyIndex = Battle.sc.nextInt() - 1;
        return enemyIndex;
    }

    public void cast(int enemyIndex) { }     // Single target

    public void cast() { }                   // AOE    }

    public void tick(Enemy e) { }             // Verzögerte Wirkung setzt bei betroffenem Gegner ein

    public void ironMaiden(Enemy e) { }

    public void explode(Enemy e) { }

}

class Bloodletting extends Spell{

    public Bloodletting() {
        name = "Aderlass";
        desc = "Opfert Lebenspunkte deines Dämon, um deine Manareserven aufzufüllen.";
        formula = Story.formulaBloodletting;
        textWhenLearned = "";
        str = Player.spellpower * 2;
        mpCost = 0;
        aoe = false;
        onDemon = true;
    }

    @Override
    public void cast() {
        int mpRestored = str;
        Battle.demon.hp -= str;
        if (Battle.demon.hp <= 0) {
            mpRestored += Battle.demon.hp;
            Battle.demon.hp = 0;
            Battle.demon.ko = true;
        }
        Player.mp += mpRestored;
        if (Player.mp > Player.mpMax) {
            Player.mp = Player.mpMax;
        }
        System.out.println("Maleficarius opfert " + mpRestored + " HP seines Dämons und füllt seinen Manavorrat um denselben Wert auf.");
        if (Battle.demon.ko) {
            System.out.println(Battle.demon.name + " übelebt den Lebensentzug nicht.");
        }
    }
}


class Doom extends Spell {

    public Doom() {
        name = "Untergang";
        desc = "Verflucht einen Gegner, sodass er über mehrere Runden hinweg stetig ansteigenden Schaden erleidet.";
        formula = Story.formulaDoom;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 4;
        mpCost = 20;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.doomed = true;
            target.counterDoom = dur;
            System.out.printf("Maleficarius belegt %s mit dem Fluch \"%s\". \n", target.name, name);

        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterDoom--;
        System.out.printf("Der Fluch \"%s\" wirkt: ", name);
        e.applyDmg(str * (dur - e.counterDoom));        // Tick 1: 10; Tick 2: 20; Tick 3: 30; Tick 4: 40
        if (e.counterDoom <= 0) {
            e.doomed = false;
            if (!e.ko)
                System.out.printf("%s hat den Zauber \"%s\" überlebt. \n", e.name, name);
        }
    }
}


class IronMaiden extends Spell {

    public IronMaiden() {
        name = "Eiserne Jungfrau";
        desc = "Verflucht alle Gegner, sodass sie sich selbst verletzen, wenn sie deinem Dämon Schaden zufügen.";
        formula = Story.formulaIronMaiden;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 5;
        mpCost = 10;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.inIronMaiden = true;
            target.counterIronMaiden = dur;
            System.out.printf("Maleficarius belegt %s mit dem Fluch \"%s\". \n", target.name, name);

        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterIronMaiden--;
        if (e.counterIronMaiden <= 0) {
            e.inIronMaiden = false;
            if (!e.ko)
                System.out.printf("Der Fluch \"%s\" auf %s klingt ab. \n", name, e.name);
        }
    }
}


class Lifeline extends Spell {

    public Lifeline() {
        name = "Lebenslinie";
        desc = "Spannt ein magisches Band zwischen deinem Dämon und einem Gegner. \nIn den folgenden Runden geht vor jeder Aktion dieses Gegners \nLebenskraft von ihm auf deinen Dämon über.";
        formula = Story.formulaLifeline;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    @Override
    public void cast(int enemyIndex) {
        if (Player.applyMpCost(mpCost)) {
            Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

            target.lifelined = true;
            target.counterlifeline = dur;

            System.out.printf("Maleficarius knüpft ein magisches Band zwischen %s und %s. \n", target.name, Battle.demon.name);
        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        int hpRestored = str;
        e.hp -= str;
        if (e.hp <= 0) {
            hpRestored += e.hp;
            System.out.println(e.name + " überlebt den Lebensentzug nicht.");
            e.die();
        }
        e.counterlifeline--;
        if (e.counterlifeline == 0) {
            e.lifelined = false;
        }
        Battle.demon.applyHeal(hpRestored);

        System.out.printf("Über die Lebenslinie fließen %d HP von %s zu %s. \n", hpRestored, e.name, Battle.demon.name);
        if (!e.ko && !e.lifelined) {
            System.out.println("Die Lebenslinie erlischt.");
        }

    }

}

class RaiseUndead extends Spell {
    // Code
}


class Soulreaper extends Spell {

    public Soulreaper() {
        name = "Seelendieb";
        desc = "Stiehlt die Seele eines geschwächten Feindes. \nAuf einen Gegner mit unter 20 % HP angewendet, \nstirbt dessen Körper sofort und seine Seele füllt deinen Manavorrat.";
        formula = Story.formulaSoulreaper;
        textWhenLearned = "";
        str = Player.spellpower * 2;
        mpCost = 0;
        aoe = false;
    }

    @Override
    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            if (target.hp <= target.hpMax * 0.2f) {
                System.out.println("Ein blaues Band spannt sich zwischen deinem Finger und der Brust des Gegners. \n" + target.name + " fällt leblos zu Boden und Maleficarius fühlt sich um " + str + " MP erfrischt.");
                Player.mp += str;
                if (Player.mp > Player.mpMax) {
                    Player.mp = Player.mpMax;
                }
                target.die();
            } else {
                System.out.println("Der Zauber zeigt keine Wirkung.");
            }

        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }
}

class ViciousSeed extends Spell {

    public ViciousSeed() {
        name = "Üble Saat";
        desc = "Pflanzt einen Keim in einem Gegner ein, der nach " + dur + " Runden \noder mit Tod des Gegners aufplatzt und bei allen Gegnern Schaden verursacht.";
        formula = Story.formulaViciousSeed;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.carriesVSeed = true;
            target.counterVSeed = dur;
            System.out.printf("Maleficarius pflanzt einen Keim des Übels in %s, der in %d Runden aufspringen wird. \n", target.name, dur);
        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterVSeed--;
        if (e.counterVSeed <= 0) {
            explode(e);
        } else
            System.out.println("Die üble Saat in " + e.name + " keimt …");
    }

    @Override
    public void explode(Enemy e) {
        e.carriesVSeed = false;
        System.out.println("Der Keim des Übels in " + e.name + " platzt auf.");
        for (Enemy f : Player.room.encounter.enemyTeam) {
            if (!f.ko)
                f.applyDmg(str);
        }
    }


}

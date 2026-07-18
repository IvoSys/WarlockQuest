import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Battle {

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();

    static Demon demon;
    static Encounter encounter = WorldBuilder.encStandard;       // wird durch Worldbuilder überschrieben
    static ArrayList<Enemy> enemyTeam = encounter.enemyTeam;

    static boolean isPlayerTurn;
    static boolean malActed;
    static int counter;
    static int pick;
    static int pickedTarget;
    static boolean inputValid = false;

    static boolean hit;
    static boolean counterAtk;


    public static void fight() {

        encounterIntro();
        ifNoDemons();
        forcePickDemon();
        do {                                                        //Äußerer Battle Loop – wiederholt nach Prüfung auf Niederlage
            initiative();
            do {                                                    //Innerer Battle Loop – wiederholt nach Gegner-Zug, solange Dämon nicht K.O.
                System.out.println("Maleficarius ist am Zug: \n");
                malActed = false;
                while (isPlayerTurn) {
                    battleMenu();
                    try {
                        System.out.print("> ");
                        pick = sc.nextInt();
                        sc.next();
                        switch (pick) {
                            case 1:                                 // Single-Target Attack
                                printEnemies();
                                pickTargetDemonAttack();
                                break;
                            case 2:                                 // AOE Attack
                                System.out.println(demon.aoeAttackBattleDesc);
                                for (Enemy e : enemyTeam)
                                    if (!e.ko)
                                        e.applyDmgEvade(demon.aoeAttack());
                                isPlayerTurn = false;
                                Control.enterToContinue();
                                break;
                            case 3:                                 // Demon Selfbuff
                                demon.selfBuff();
                                isPlayerTurn = false;
                                Control.enterToContinue();
                                break;
                            case 4:                                 // Spell
                                if (!malActed) {
                                    printSpellbook();
                                    inputValid = false;
                                    while (!inputValid) {
                                        try {
                                            System.out.print("> ");
                                            pick = sc.nextInt();
                                            if (pick == 0) {
                                                break;
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                            sc.next();
                                        }
                                        Spell pickedSpell = Player.spellbook.get(pick - 1); // Zielauswahl Zauber
                                        if (!pickedSpell.aoe && !pickedSpell.onDemon) {
                                            printEnemies();
                                            pickSpellTarget(pickedSpell);
                                        } else {
                                            pickedSpell.cast();
                                            malActed = true;
                                            Control.enterToContinue();
                                        }
                                    }
                                } else {
                                    System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                                    Control.enterToContinue();
                                }
                                break;
                            case 5:                                 // Summon
                                if (!malActed) {
                                    printDemons();
                                    pickDemon();
                                } else {
                                    System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                                    Control.enterToContinue();
                                }
                                break;
                            default:                                // Default für Battle Menu
                                System.out.println("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und 5. \n");
                                break;
                        }                                           // Ende Switch-Case mit Spieleraktionen
                    } catch (InputMismatchException e) {
                        System.out.println("Ungültige Eingabe. Gib eine Ziffer ein. \n");
                        sc.next();
                    }
                    checkVictory();                                 // Spieler hat Aktion ausgeführt, Prüfung auf Sieg, Flüche bei toten Gegnern aufösen
                    if (demon.ko) {                                 // Dämon könnte durch Zauber wie Aderlass sterben, ggf. Lifeline auflösen und Prüfung auf Niederlage:
                        for (Enemy e : enemyTeam)
                            e.lifelined = false;
                        if (!encounter.beaten)
                            checkDefeat();
                    }
                }                                                   // Ende while(isPlayerTurn)
                while (!isPlayerTurn && !encounter.beaten) {        // GEGNERZUG
                    if (encounter.teamNamePlural)
                        System.out.println(encounter.teamName + " sind am Zug:\n");
                    else
                        System.out.println(encounter.teamName + " ist am Zug:\n");
                    for (Enemy e : enemyTeam) {
                        if (e.lifelined)                                        // Vor Aktion Zaubereffekte abhandeln, könnten dmg verursachen
                            WorldBuilder.lifeline.tick(e);
                        if (e.doomed)
                            WorldBuilder.doom.tick(e);
                        if (e.carriesVSeed)
                            WorldBuilder.viciousSeed.tick(e);
                        if (e.inIronMaiden)
                            WorldBuilder.ironMaiden.tick(e);
                        hit = false;
                        if (!e.ko) {                                            // Aktion
                            switch (e) {
                                case Guard guard -> guardTurn(e);
                                case Watchdog watchdog -> watchdogTurn(e);
                                case Soldier soldier -> soldierTurn(e);
                                case Archer archer -> archerTurn(e);
                                case Apprentice apprentice -> apprenticeTurn(e);
                                case Novice novice -> noviceTurn(e);
                                case Knight knight -> knightTurn(e);
                                case Ranger ranger -> rangerTurn(e);
                                case Mage mage -> mageTurn(e);
                                case Cleric cleric -> clericTurn(e);
                                case Boss01 boss01 -> boss01Turn(e);
                                case Boss02 boss02 -> boss02Turn(e);
                                case Boss03 boss03 -> boss03Turn(e);
                                default -> defaultTurn(e);
                            }
                        }                                                        // Ende Aktion einzelner Gegner
                        if (demon.ko)                                            // Wenn Dämon besiegt wurde, sollte eventuelle Lifeline auf Gegnern beendet werden.
                            for (Enemy f : enemyTeam) {
                                if (f.lifelined) {
                                    f.lifelined = false;
                                    System.out.printf("Die Lebenslinie zwischen %s und %s reißt.", f.name, demon.name);
                                }
                            }
                        if (!e.ko)
                            Control.enterToContinue();                          // mit If-Bedingung, sonst müsste man nach ausgefallener Handlung eines besiegten Gegners trotzdem Eingabetaste drücken.

                    } // Ende Aktion Gegnerteam
                    isPlayerTurn = true;
                }
            } while (!demon.ko);                                    // Ende innerer Battle-Loop. Wiederholt, solange Dämon am Leben
            checkDefeat();                                          // Dämon besiegt, Prüfung auf Niederlage
            forcePickDemon();                                       // Spieler nicht besiegt, neuen Dämon beschwören
        } while (!encounter.beaten);                                // Solange Kampf nicht gewonnen, wieder nach oben
        battleWon();                                                // Kampf gewonnen
    }

    public static void encounterIntro(){
        System.out.println("\n" + encounter.intro + "\n");

        if (encounter.teamNamePlural)
            System.out.println(encounter.teamName + " greifen an:");
        else
            System.out.println(encounter.teamName + " greift an:");
        for (Enemy e : enemyTeam)
            System.out.printf("%s \t (%d/%d HP) \n", e.name, e.hp, e.hpMax);
        System.out.println();
        Control.enterToContinue();
    }

    public static void ifNoDemons(){
        if (Player.team.isEmpty()){
            System.out.println("Maleficarius besitzt keine Dämonen.");
            System.out.println("Dies ist dein Ende.\n");
            ASCII.BattleLost();
            System.exit(0);
        }
    }

    public static void forcePickDemon(){
        int counter = 1;
        int pick;
        boolean inputValid = false;

        System.out.println("Wähle einen Dämon zum Beschwören:");
        for (Demon d : Player.team) {
            System.out.printf("[%d] %s \t", counter, d.name);
            if (d.ko) {
                System.out.println(">> BESIEGT <<");
            }
            else {
                System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
            }
            counter++;
        }

        while (!inputValid) {
            try {
                System.out.print("> ");
                pick = sc.nextInt();

                if (pick < 1 || pick > Player.team.size()) {
                    System.out.printf("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und %d. \n", Player.team.size());
                } else if (Player.team.get(pick - 1).ko) {
                    System.out.printf("%s wurde bereits besiegt, beschwöre einen anderen Dämon. \n", Player.team.get(pick - 1).name);
                } else {
                    Demon.summon(pick - 1);
                    inputValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                sc.next();
            }
        }
    }

    public static void initiative(){
        int sumDex = 0;
        for (Enemy e : Battle.enemyTeam)
            sumDex += e.dex;
        int enemyTeamDex = sumDex / enemyTeam.size();
        int initEnemies = rnd.nextInt(20) + enemyTeamDex;
        int initDemon = rnd.nextInt(20) + demon.dex;

        if (initDemon >= initEnemies) {
            isPlayerTurn = true;
            System.out.println(demon.name + " beginnt.");
        } else {
            isPlayerTurn = false;
            if (encounter.teamNamePlural)
                System.out.println(encounter.teamName + " beginnen.");
            else
                System.out.println(encounter.teamName + " beginnt.");
        }
        Control.enterToContinue();
    }

    public static void battleMenu(){
        System.out.println(encounter.teamName.toUpperCase());
        for (Enemy e : enemyTeam) {
            if (!e.ko) {
                System.out.printf("%s\t (%d/%d HP)", e.name, e.hp, e.hpMax);
            } else {
                System.out.printf("%s\t>> BESIEGT <<", e.name);
            }
            if (e.lifelined)
                System.out.printf(" – Lebenslinie (%d)", e.counterlifeline);
            if (e.carriesVSeed)
                System.out.printf(" – Üble Saat (%d)", e.counterVSeed);
            System.out.println();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.printf("%s \t\t\t(%d/%d HP)\n", demon.name.toUpperCase(), demon.hp, demon.hpMax);
        System.out.print("[1] " + demon.attackName);
        if (demon == WorldBuilder.dem01 && WorldBuilder.dem01.roar > 0)
            System.out.printf(" (+%d)\n", WorldBuilder.dem01.roar);
        else
            System.out.println();
        if (demon == WorldBuilder.dem02 && WorldBuilder.dem02.isBlazing)
            System.out.println("[2] " + WorldBuilder.dem02.aoeAttackNameBlazing);
        else
            System.out.println("[2] " + demon.aoeAttackName);
        System.out.println("[3] " + demon.selfBuffName);
        if (!malActed) {
            System.out.println("-----------------------------------");
            System.out.printf("[4] Zauber \t\t\t(%d/%d MP)\n", Player.mp, Player.mpMax);
            System.out.println("[5] Beschwören");
        }

    }

    public static void printEnemies(){
        counter = 1;
        for (Enemy e : enemyTeam) {
            System.out.printf("[%d] %s \t", counter, e.name);
            counter++;
            if (!e.ko)
                System.out.printf(" (%d/%d HP)", e.hp, e.hpMax);
            else
                System.out.print(">> BESIEGT <<");
            if (e.lifelined)
                System.out.printf(" – Lebenslinie (%d)", e.counterlifeline);
            if (e.carriesVSeed)
                System.out.printf(" – Üble Saat (%d)", e.counterVSeed);
            System.out.println();
        }
    }

    public static void printSpellbook() {
        boolean empty = true;
        counter = 1;
        System.out.println("=============ZAUBERBUCH============");
        for (Spell s : Player.spellbook) {
            System.out.printf("[%d] %s \t(%d Mana", counter, s.name, s.mpCost);
            if (s.aoe)
                System.out.print(", Flächenwirkung");
            if (s.dur != 0)
                System.out.printf(", %d Runden", s.dur);
            System.out.println(")");
            counter++;
            empty = false;
        }
        if (empty) {
            System.out.println("Du beherrschst noch keinen Zauber.");
        }
        System.out.println("Wähle einen Zauber. (Zurück: 0)");
    }

    public static void printDemons() {
        counter = 1;
        System.out.println("=============Dämonen============");
        for (Demon d : Player.team) {
            System.out.printf("[%d] %s \t", counter, d.name);
            if (d.ko) {
                System.out.println(">> BESIEGT <<");
            } else {
                System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
            }
            counter++;
        }
        System.out.println("Wähle einen Dämon. (Zurück: 0)");
    }

    public static void pickTargetDemonAttack(){
        inputValid = false;
        while (!inputValid) {
            System.out.println("Wähle ein Ziel. (Zurück: 0)");
            try {
                System.out.print("> ");
                pickedTarget = sc.nextInt();
                if (pickedTarget < 0 || pickedTarget > enemyTeam.size()) {
                    System.out.printf("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und %d oder die 0. \n", enemyTeam.size());
                } else if (pickedTarget == 0) {
                    break;
                } else if (enemyTeam.get(pickedTarget - 1).ko) {
                    System.out.printf("%s wurde bereits besiegt, wähle einen anderen Gegner. \n", enemyTeam.get(pickedTarget - 1).name);
                } else {
                    enemyTeam.get(pickedTarget - 1).applyDmgEvade(demon.attack());
                    inputValid = true;
                    isPlayerTurn = false;
                    if (counterAtk) {
                        demon.applyDmg(enemyTeam.get(pickedTarget - 1).attack());
                        counterAtk = false;
                    }
                    Control.enterToContinue();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                sc.next();
            }
        }
    }

    public static void pickSpellTarget(Spell pickedSpell){
        inputValid = false;
        while (!inputValid) {
            System.out.println("Wähle ein Ziel. (Zurück: 0)");
            try {
                System.out.print("> ");
                pickedTarget = sc.nextInt();
                if (pickedTarget < 0 || pickedTarget > enemyTeam.size()) {
                    System.out.printf("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und %d oder die 0. \n", enemyTeam.size());
                } else if (pickedTarget == 0) {
                    break;
                }
                pickedSpell.cast(pickedTarget - 1);
                inputValid = true;
                malActed = true;
                Control.enterToContinue();
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                sc.next();
            }
        }
    }

    public static void pickDemon(){
        inputValid = false;
        while (!inputValid) {
            try {
                System.out.print("> ");
                pick = sc.nextInt();
                if (pick == 0) {
                    break;
                } else if (pick < 0 || pick > Player.team.size()) {
                    System.out.printf("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und %d oder die 0. \n", Player.team.size());
                } else if (Player.team.get(pick - 1).ko) {
                    System.out.printf("%s wurde bereits besiegt, beschwöre einen anderen Dämon. \n", Player.team.get(pick - 1).name);
                } else {
                    Demon.summon(pick - 1);
                    inputValid = true;
                    isPlayerTurn = false;
                    Control.enterToContinue();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                sc.next();
            }
        }
    }

    public static void checkDefeat(){
        Player.counterKO = 0;
        for (Demon d : Player.team) {
            if (d.ko)
                Player.counterKO++;
        }
        if (Player.counterKO >= Player.team.size()) {
            System.out.println("\nMaleficarius hat keine kampffähigen Dämonen mehr.");
            System.out.println("Dies ist dein Ende.\n");
            ASCII.BattleLost();
            System.exit(0);
        }
    }

    public static void checkVictory(){
        encounter.counterKO = 0;
        for (Enemy e : enemyTeam) {
            if (e.ko) {
                encounter.counterKO++;
                e.die();
            }
        }
        if (encounter.counterKO >= enemyTeam.size()) {
            encounter.beaten = true;
            battleWon();
        }
    }

    public static void battleWon(){
        ASCII.BattleWon();
        if (encounter.teamNamePlural)
            System.out.println(encounter.teamName + " wurden besiegt!");
        else
            System.out.println(encounter.teamName + " wurde besiegt!");
        Player.room.encounterBeaten = true;
        Control.enterToContinue();
        System.out.println();
        System.out.println(Player.room.encounter.outro);
        System.out.println();
        if (Player.room.encounter.rewardItem != null) {
            Item.obtainItem(Player.room.encounter.rewardItem);
            Player.room.encounter.rewardItem = null;
        }
        for (Demon d : Player.team) {
            d.ko = false;
            if (d.hp == 0) {
                d.hp = 1;
            }
        }
        WarlockQuest.gameLoop();
    }


    //Turn-Methoden für Klassen
    public static void guardTurn(Enemy e) {
        if ((e.hp <= e.hpMax * 0.5f) && (e.potions > 0)) {
            e.drinkPotion();
        } else if (!demon.ko) {
            demon.applyDmgEvade(e.attack());
        }
    }

    public static void watchdogTurn(Enemy e) {
            if (!Battle.demon.ko) {
                if (!e.bitesOn) {
                    hit = demon.applyDmgEvade(e.attack());
                    if (hit) {
                        e.bitesOn = true;
                        System.out.println(e.name + " beißt sich fest.");
                    }
                } else {
                    System.out.printf("%s hat sich in %s festgebissen und trifft automatisch. \n", e.name, demon.name);
                    demon.applyDmg(e.attack());
                }
            }
    }

    public static void soldierTurn(Enemy e) {
        if ((e.hp <= e.hpMax * 0.5f) && (e.potions > 0)) {
            e.drinkPotion();
        } else if (!demon.ko){
            demon.applyDmgEvade(e.attack());
        }
    }

    public static void archerTurn(Enemy e) {
        if ((e.hp <= e.hpMax * 0.5f) && (e.potions > 0)) {
            e.drinkPotion();
        } else if (!demon.ko){
            demon.applyDmgEvade(e.attack());
        }
    }

    public static void apprenticeTurn(Enemy e) {

    }

    public static void noviceTurn(Enemy e) {
        Enemy toHeal = null;
        for (Enemy f : enemyTeam) {
            if (toHeal == null) {
                if (f.hp <= f.hpMax * 0.6f)
                    toHeal = f;
            } else if (f.hp < toHeal.hp) {
                toHeal = f;
            }
        }
        if (toHeal != null) {
            toHeal.applyHeal(e.heal());
        } else if (!demon.ko){
            demon.applyDmgEvade(e.attack());
        }

    }

    public static void knightTurn(Enemy e) {

    }

    public static void rangerTurn(Enemy e) {

    }

    public static void mageTurn(Enemy e) {

    }

    public static void clericTurn(Enemy e) {

    }

    public static void boss01Turn(Enemy e) {

    }

    public static void boss02Turn(Enemy e) {

    }

    public static void boss03Turn(Enemy e) {

    }

    public static void defaultTurn(Enemy e) {
        System.out.println("DEBUG: " + e.name + " nutzt defaultTurn().");
        demon.applyDmgEvade(e.attack());
        /* Aktion auswürfeln: Gegner-Attribut "int num" kennzeichnet Anzahl seiner Aktionen, "pick = rnd(bound: num)" lässt im richtigen Bereich würfeln:
        else if (pick == 1) {
                e.ability1();
            } else if (pick == 2) {
                e.ability2();
            } else if (pick == 3) {
                e.ability3();
            } else
                System.out.println("DEBUG: Fehler beim Auswürfeln der Gegneraktion."); */
    }

}





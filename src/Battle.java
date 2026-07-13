import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Battle {

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();

    static Demon demon;
    static Encounter encounter = WorldBuilder.enc221TEST;       // wird durch Worldbuilder überschrieben
    static ArrayList<Enemy> enemyTeam = encounter.enemyTeam;

    static boolean isPlayerTurn;
    static boolean malActed;
    static int counter;
    static int pick;
    static int pickedTarget;
    static boolean inputValid = false;


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
                        switch (pick) {
                            case 1:                                 // Single-Target Attack
                                printEnemies();
                                pickTargetDemonAttack();
                                break;
                            case 2:                                 // AOE Attack
                                System.out.println(demon.aoeAttackBattleDesc);
                                for (Enemy e : enemyTeam)
                                    e.applyDmg(demon.aoeAttack());
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
                                    try {
                                        System.out.print("> ");
                                        pick = sc.nextInt();
                                        if (pick == 0) {
                                            break;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                        sc.next();
                                    }                               // Zielauswahl Zauber
                                    Spell pickedSpell = Player.spellbook.get(pick - 1);
                                    if (!pickedSpell.aoe && !pickedSpell.onDemon) {
                                        printEnemies();
                                        pickTargetSpell(pickedSpell);
                                    } else {
                                        pickedSpell.cast();
                                        malActed = true;
                                        Control.enterToContinue();
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

    //GEGNER AM ZUG
                while (!isPlayerTurn && !encounter.beaten) {
                    enemyTurn();
                } // Ende while(!isPlayerTurn && !encounter.beaten)

            } while (!demon.ko);  // Ende innerer Battle-Loop. Wiederholt, solange Dämon am Leben

            // Dämon besiegt, Prüfung auf Niederlage
            checkDefeat();

            // Spieler nicht besiegt, neuen Dämon beschwören
            forcePickDemon();

        // Solange Kampf nicht gewonnen, wieder nach oben
        } while (!encounter.beaten);

        //Kampf gewonnen
        battleWon();
    }

    public static void encounterIntro(){
        System.out.println("\n" + encounter.intro + "\n");

        System.out.println(encounter.name + " greifen an:");
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
        if (true) {
            isPlayerTurn = true;
            System.out.println(demon.name + " beginnt.");
        } else {
            isPlayerTurn = false;
            System.out.println(encounter.name + " beginnen.");
        }
        Control.enterToContinue();
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
        if (encounter.counterKO >= enemyTeam.size())
            encounter.beaten = true;
    }

    public static void battleMenu(){                 // gibt int für switch (pick) aus
        System.out.println(encounter.name.toUpperCase());
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
        System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
        System.out.printf("%s \t(%d/%d HP)\n", demon.name.toUpperCase(), demon.hp, demon.hpMax);
        System.out.print("[1] " + demon.attackName);
        if (demon == WorldBuilder.dem01 && WorldBuilder.dem01.roar > 0)
            System.out.printf(" (+%d)\n", WorldBuilder.dem01.roar);
        else
            System.out.println();
        System.out.println("[2] " + demon.aoeAttackName);
        System.out.println("[3] " + demon.selfBuffName);
        if (!malActed) {
            System.out.println("----------------------------------------------");
            System.out.printf("[4] Zauber \t(%d/%d MP)\n", Player.mp, Player.mpMax);
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
                } else {
                    enemyTeam.get(pickedTarget - 1).applyDmg(demon.attack());
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

    public static void pickTargetSpell(Spell pickedSpell){
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
                    System.out.printf("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und %d. \n", Player.team.size());
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

    public static void enemyTurn() {
        System.out.println(encounter.name + " sind am Zug:\n");
        for (Enemy e : enemyTeam) {

            if (e.lifelined)                         // Vor Aktion Zaubereffekte abhandeln, könnten dmg verursachen
                WorldBuilder.lifeline.tick(e);
            if (e.doomed)
                WorldBuilder.doom.tick(e);
            if (e.carriesVSeed)
                WorldBuilder.viciousSeed.tick(e);
            if (e.inIronMaiden)
                WorldBuilder.ironMaiden.tick(e);

            if (!e.ko) {                                // Aktion, erst prüfen, ob K.O.
                //weitere If-Verzweigung je nach Enemy-Subklasse?
                if ((e.hp <= e.hpMax * 0.3f) && (e.hasPotion)) {    // Wenn schwer verletzt und Potion vorhanden, dann Potion statt Angriff
                    e.drinkPotion();
                } else {
                    pick = rnd.nextInt(e.numOptions);          // Zufallszahlenbereich entspricht Anzahl seiner Optionen
                    if (pick == 0) {
                        demon.applyDmg(e.attack());                            // Flavor dazu: Kampfschreie, Schadensbeschreibungen etc.
                    } else if (pick == 1) {
                        e.ability1();
                    } else if (pick == 2) {
                        e.ability2();
                    } else if (pick == 3) {
                        e.ability3();
                    } else
                        System.out.println("DEBUG: Fehler beim Auswürfeln der Gegneraktion.");

                }
            } // Ende Aktion einzelner Gegner
            if (demon.ko)                           //Wenn Dämon besiegt wurde, sollte eventuelle Lifeline auf Gegnern beendet werden.
                for (Enemy f : enemyTeam) {
                    if (f.lifelined) {
                        f.lifelined = false;
                        System.out.printf("Die Lebenslinie zwischen %s und %s reißt.", f.name, demon.name);
                    }
                }
            if (!e.ko)
                Control.enterToContinue();      // mit If-Bedingung, sonst müsste man nach ausgefallener Handlung eines besiegten Gegners trotzdem Eingabetaste drücken.

        } // Ende Aktion Gegnerteam
        isPlayerTurn = true;
    }

    public static void battleWon(){
        ASCII.BattleWon();
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
    }


}





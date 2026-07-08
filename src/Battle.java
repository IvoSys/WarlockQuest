import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    static Scanner sc = new Scanner(System.in);

    public static void fight(Encounter encounter) {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        boolean isPlayerTurn;
        boolean malActed;
        int dmg;
        int pick;
        int pickTarget;

        //Encounter-Intro
        System.out.println("\n" + encounter.intro + "\n");

        //Gegner-Team aus übergebenem Encounter ziehen
        ArrayList<Enemy> enemyTeam = encounter.enemyTeam;
        Enemy enemy1 = enemyTeam.get(0);
        Enemy enemy2 = enemyTeam.get(1);
        Enemy enemy3 = enemyTeam.get(2);
        Enemy activeEnemy;

        //Gegner anzeigen
        System.out.println("Gegner greifen an:");
        for (Enemy e : enemyTeam)
            System.out.printf("%s %s \t (%d/%d HP) \n", e.job, e.name, e.hp, e.hpMax);
        System.out.println();

        //Spieler beseitzt keine Dämonen
        if (Player.team.isEmpty()){
            System.out.println("Maleficarius besitzt keine Dämonen.");
            System.out.println("Dies ist dein Ende.\n");
            ASCII.BattleLost();
            System.exit(0);
        }

        //Spieler wählt Dämon
        chooseDemon();

        //Äußerer Battle Loop
        do {            // wiederholt nach Gewinn/Verlust-Prüfung, wenn Kampf weitergeht
            //Klären, welche Partei beginnt, oder immer Dämon anfangen lassen
            if (true) {
                isPlayerTurn = true;
                System.out.println(Player.activeDemon.name + " beginnt.");
            } else {
                isPlayerTurn = false;
                System.out.println(encounter.enemyTeamName + " beginnen.");
            }

            //Innerer Battle Loop
            do {        // wiederholt nach Gegner-Zug, solange Dämon nicht K.O.
                malActed = false;
                while (isPlayerTurn) {
                    System.out.println("Du bist dran.\n");

                    System.out.println("GEGNER");
                    for (Enemy e : enemyTeam) {
                        System.out.printf("%s %s\t (%d/%d HP)", e.job, e.name, e.hp, e.hpMax);
                        if (e.lifelined)
                            System.out.printf("\tLebenslinie (%d)", e.counterlifeline);
                        if (e.carriesVSeed)
                            System.out.printf("\tÜble Saat (%d)", e.counterVSeed);
                        System.out.println();
                    }
                    System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

                    System.out.printf("%d/%d HP \t\t%d/%d MP \n", Player.activeDemon.hp, Player.activeDemon.hpMax, Player.mp, Player.mpMax);
                    System.out.println(Player.activeDemon.name.toUpperCase());
                    System.out.println("[1] " + Player.activeDemon.attackName);
                    System.out.println("[2] " + Player.activeDemon.ability1Name);
                    System.out.println("[3] " + Player.activeDemon.ability2Name);
                    System.out.println("------------------------------------");
                    System.out.println("[4] Zauber");
                    //System.out.println("[X] Trank");
                    System.out.println("[5] Beschwören");
                    System.out.println("\n> ");
                    pick = sc.nextInt();
                    switch (pick) {
                        case 1:
                            Player.activeDemon.attack();
                            isPlayerTurn = false;
                            break;
                        case 2:
                            Player.activeDemon.ability1();
                            isPlayerTurn = false;
                            break;
                        case 3:
                            Player.activeDemon.ability2();
                            isPlayerTurn = false;
                            break;
                        case 4:
                            if (!malActed) {
                                boolean empty = true;
                                int counter = 1;
                                for (Spell s : Player.spellbook) {
                                    System.out.printf("[%d] %s \n", counter, s.name);
                                    System.out.printf("     Mana: %d, \tStärke: %d ", s.mpCost, s.str);
                                    if (s.aoe)
                                        System.out.print("\tFlächenwirkung");
                                    if (s.dur != 0)
                                        System.out.printf("\tDauer: %d Runden", s.dur);
                                    counter++;
                                    empty = false;
                                }
                                if (empty) {
                                    System.out.println("Du beherrschst noch keinen Zauber.");
                                }
                                System.out.println("[0] Zurück");
                                System.out.println("\n> ");
                                pick = sc.nextInt() - 1;
                                if (pick == -1) {
                                    break;
                                }
                                //Zielauswahl, Unterscheidung Flächenschaden.
                                if (!Player.spellbook.get(pick).aoe) {
                                    System.out.println("Wähle ein Ziel:");
                                    counter = 1;
                                    for (Enemy e : enemyTeam) {
                                        System.out.printf("[%d] %s \t", counter, e.name);
                                        counter++;
                                        if (!e.ko)
                                            System.out.printf(" (%d/%d HP) \n", e.hp, e.hpMax);
                                        else
                                            System.out.println(">> BESIEGT <<");
                                    }
                                    System.out.print("> ");
                                    pickTarget = sc.nextInt();
                                    Player.spellbook.get(pick).cast(pickTarget);
                                } else {
                                    Player.spellbook.get(pick).cast();
                                }
                                malActed = true;
                            } else
                                System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                            break;
                        case 5:
                            if (!malActed) {
                                chooseDemon();
                                isPlayerTurn = false;
                                break;
                            } else
                                System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                            break;
                        default:
                            System.out.println("Ungültige Eingabe");
                            break;
                    } //Ende Switch-Case mit Spieleraktionen

                    //Spieler hat Aktion ausgeführt
                    //Prüfung auf Sieg
                    encounter.counterKO = 0;
                    for (Enemy e : enemyTeam) {
                        if (e.ko)
                            encounter.counterKO++;
                    }
                    if (encounter.counterKO >= enemyTeam.size())
                        encounter.beaten = true;

                } //Ende while(isPlayerTurn)

                //Gegner am Zug
                while (!isPlayerTurn && !encounter.beaten) {
                    for (Enemy e : enemyTeam) {
                        e.checkLifelined();                         // Vor Aktion Zaubereffekte abhandeln, könnten dmg verursachen
                        //if (e.lifelined) {                        // e.checkLifelined() eleganter als IF-Verzweigung?
                        //    WorldBuilder.lifeline.tick(e);
                        //}
                        if (e.carriesVSeed) {
                            WorldBuilder.viciousSeed.tick(e);
                        }
                        if (!e.ko) {                                // Aktion, erst prüfen, ob K.O.
                            if ((e.hp <= e.hpMax * 0.3f) && (e.hasPotion)) {    // Wenn schwer verletzt und Potion vorhanden, dann Potion statt Angriff
                                e.applyHeal(e.potionStr);
                                e.hasPotion = false;
                                System.out.printf("%s %s trinkt eine Potion und heilt sich um %d HP. \n", e.job, e.name, e.potionStr);
                            } else {
                                pick = rnd.nextInt(e.numOptions);          // Zufallszahlenbereich entspricht Anzahl seiner Optionen
                                if (pick == 0) {
                                    Player.activeDemon.applyDmg(e.attack());                            // Flavor dazu: Kampfschreie, Schadensbeschreibungen etc.
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
                    } // Ende Aktion Gegnerteam
                    isPlayerTurn = true;
                } // Ende while(!isPlayerTurn && !encounter.beaten)

            } while (!Player.activeDemon.ko);       //Dämon wieder am Zug, sofern nicht besiegt

            // Dämon besiegt,
            // Prüfung auf Niederlage
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

            // Spieler nicht besiegt, neuen Dämon beschwören
            chooseDemon();

        // Solange Kampf nicht gewonnen, wieder nach oben
        // Wenn Kampf gewonnen, wird Schleife durchbrochen
        } while (!encounter.beaten);


        //Kampf gewonnen
        ASCII.BattleWon();
        Player.room.encounterBeaten = true;
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

    public static void chooseDemon() {
        int count = 1;
        int pick;

        for (Demon d : Player.team) {
            System.out.printf("[%d] %s \t", count, d.name);
            if (d.ko)
                System.out.println(">> BESIEGT <<");
            else
                System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
        }
        System.out.println("Wähle einen Dämon zum Beschwören:");
        do {
            System.out.print("> ");
            pick = sc.nextInt();
            if (!Player.team.get(pick - 1).ko) {
                Demon.summon(pick - 1);
            } else {
                System.out.printf("%s ist besiegt, beschwöre einen anderen Dämon.", Player.team.get(pick - 1).name);
            }
        } while (Player.team.get(pick - 1).ko);
    }

}



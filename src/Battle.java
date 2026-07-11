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
        int counter;
        int pick;
        int pickTarget;

        //Encounter-Intro
        System.out.println("\n" + encounter.intro + "\n");

        //Gegner-Team aus übergebenem Encounter ziehen
        ArrayList<Enemy> enemyTeam = encounter.enemyTeam;

        //Gegner anzeigen
        System.out.println("Gegner greifen an:");
        for (Enemy e : enemyTeam)
            System.out.printf("%s \t (%d/%d HP) \n", e.name, e.hp, e.hpMax);
        System.out.println();
        Control.enterToContinue();

        //Spieler besitzt keine Dämonen
        if (Player.team.isEmpty()){
            System.out.println("Maleficarius besitzt keine Dämonen.");
            System.out.println("Dies ist dein Ende.\n");
            ASCII.BattleLost();
            System.exit(0);
        }

        //Spieler wählt Dämon
        chooseDemon();

    //Äußerer Battle Loop – wiederholt nach Prüfung auf Niederlage
        do {

            //Klären, welche Partei beginnt, oder immer Dämon anfangen lassen
            if (true) {
                isPlayerTurn = true;
                System.out.println(Player.activeDemon.name + " beginnt.");
            } else {
                isPlayerTurn = false;
                System.out.println(encounter.enemyTeamName + " beginnen.");
            }
            Control.enterToContinue();

    //Innerer Battle Loop – wiederholt nach Gegner-Zug, solange Dämon nicht K.O.
            do {
                malActed = false;
                while (isPlayerTurn) {
                    System.out.println("Du bist dran.\n");

                    System.out.println("GEGNER");
                    counter = 1;
                    for (Enemy e : enemyTeam) {
                        System.out.printf("[%d] %s\t (%d/%d HP)", counter, e.name, e.hp, e.hpMax);
                        if (e.lifelined)
                            System.out.printf("\t, Lebenslinie (%d)", e.counterlifeline);
                        if (e.carriesVSeed)
                            System.out.printf("\t, Üble Saat (%d)", e.counterVSeed);
                        System.out.println();
                        counter++;
                    }
                    System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

                    System.out.printf("%d/%d HP \t\t%d/%d MP \n", Player.activeDemon.hp, Player.activeDemon.hpMax, Player.mp, Player.mpMax);
                    System.out.println(Player.activeDemon.name.toUpperCase());
                    System.out.println("[1] " + Player.activeDemon.attackName + "\t(trifft einen Gegner)");
                    System.out.println("[2] " + Player.activeDemon.aoeAttackName + "\t(trifft alle Gegner)");
                    System.out.println("[3] " + Player.activeDemon.selfBuffName + "\t(wirkt auf Dämon)");
                    System.out.println("------------------------------------");
                    System.out.println("[4] Zauber");
                    System.out.println("[5] Beschwören");
                    System.out.print("> ");
                    pick = sc.nextInt();
                    switch (pick) {
                        case 1:                             // Immer Single Target
                            counter = 1;
                            for (Enemy e : enemyTeam) {
                                System.out.printf("[%d] %s\t (%d/%d HP)", counter, e.name, e.hp, e.hpMax);
                                if (e.lifelined)
                                    System.out.printf("\tLebenslinie (%d)", e.counterlifeline);
                                if (e.carriesVSeed)
                                    System.out.printf("\tÜble Saat (%d)", e.counterVSeed);
                                System.out.println();
                                counter++;
                            }
                            System.out.print("Wähle ein Ziel.\n> ");
                            pickTarget = sc.nextInt();
                            enemyTeam.get(pickTarget).applyDmg(Player.activeDemon.attack()); // Ziel 3 gewählt und Index out of Bounds erhalten
                            isPlayerTurn = false;
                            Control.enterToContinue();
                            break;
                        case 2:
                            System.out.println(Player.activeDemon.aoeAttackBattleDesc);
                            for (Enemy e : enemyTeam)
                                e.applyDmg(Player.activeDemon.aoeAttack());
                            isPlayerTurn = false;
                            Control.enterToContinue();
                            break;
                        case 3:
                            Player.activeDemon.selfBuff();
                            isPlayerTurn = false;
                            Control.enterToContinue();
                            break;
                        case 4:
                            if (!malActed) {
                                boolean empty = true;
                                counter = 1;
                                for (Spell s : Player.spellbook) {
                                    System.out.printf("[%d] %s \n", counter, s.name);
                                    System.out.printf("    Mana: %d, \tStärke: %d \n", s.mpCost, s.str);
                                    if (s.aoe)
                                        System.out.print("\tFlächenwirkung \n");
                                    if (s.dur != 0)
                                        System.out.printf("\tDauer: %d Runden \n", s.dur);
                                    counter++;
                                    empty = false;
                                }
                                if (empty) {
                                    System.out.println("Du beherrschst noch keinen Zauber.");
                                }
                                System.out.println("[0] Zurück");
                                System.out.print("> ");
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
                                        if (e.lifelined)
                                            System.out.printf("\t, Lebenslinie (%d)", e.counterlifeline);
                                        if (e.carriesVSeed)
                                            System.out.printf("\t, Üble Saat (%d)", e.counterVSeed);
                                    }
                                    System.out.print("> ");
                                    pickTarget = sc.nextInt() - 1;
                                    Player.spellbook.get(pick).cast(pickTarget);
                                } else {
                                    Player.spellbook.get(pick).cast();
                                }
                                malActed = true;
                            } else
                                System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                            Control.enterToContinue();
                            break;
                        case 5:
                            if (!malActed) {
                                counter = 1;
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
                                System.out.println("[0] Zurück");
                                do {
                                    System.out.print("> ");
                                    pick = sc.nextInt();
                                    if (pick == -1) {
                                        break;
                                    } else if (!Player.team.get(pick - 1).ko) {
                                        Demon.summon(pick - 1);
                                    } else {
                                        System.out.printf("%s ist besiegt, beschwöre einen anderen Dämon.", Player.team.get(pick - 1).name);
                                    }
                                } while (Player.team.get(pick - 1).ko);
                                isPlayerTurn = false;
                            } else {
                                System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                            }
                            Control.enterToContinue();
                            break;
                        default:
                            System.out.println("Ungültige Eingabe");
                            Control.enterToContinue();
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

    //GEGNER AM ZUG
                while (!isPlayerTurn && !encounter.beaten) {
                    for (Enemy e : enemyTeam) {
                        if (e.lifelined)                         // Vor Aktion Zaubereffekte abhandeln, könnten dmg verursachen
                            WorldBuilder.lifeline.tick(e);
                        if (e.carriesVSeed) {
                            WorldBuilder.viciousSeed.tick(e);
                        }
                        if (!e.ko) {                                // Aktion, erst prüfen, ob K.O.
                                                                    //weitere If-Verzweigung je nach Enemy-Subklasse?
                            if ((e.hp <= e.hpMax * 0.3f) && (e.hasPotion)) {    // Wenn schwer verletzt und Potion vorhanden, dann Potion statt Angriff
                                e.applyHeal(e.potionStr);
                                e.hasPotion = false;
                                System.out.printf("%s trinkt eine Potion und heilt sich um %d HP. \n", e.name, e.potionStr);
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
                        Control.enterToContinue();
                    } // Ende Aktion Gegnerteam
                    isPlayerTurn = true;
                } // Ende while(!isPlayerTurn && !encounter.beaten)

            } while (!Player.activeDemon.ko);  // Ende innerer Battle-Loop. Wiederholt, solange Dämon am Leben

            // Dämon besiegt, Prüfung auf Niederlage
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
        } while (!encounter.beaten);

        //Kampf gewonnen
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

    public static void chooseDemon(){
        int counter = 1;
        int pick;

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
        do {
            System.out.print("> ");
            pick = sc.nextInt();
            if (!Player.team.get(pick - 1).ko) {
                Demon.summon(pick - 1);
            } else {
                System.out.printf("%s wurde bereits besiegt, beschwöre einen anderen Dämon.", Player.team.get(pick - 1).name);
            }
        } while (Player.team.get(pick - 1).ko);
    }

}





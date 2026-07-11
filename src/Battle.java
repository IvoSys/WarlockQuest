import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Battle {

    static Scanner sc = new Scanner(System.in);

    public static void fight(Encounter encounter) {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        boolean isPlayerTurn;
        boolean malActed;
        int counter;
        int pick;
        int pickedTarget;
        boolean inputValid = false;

        //Encounter-Intro
        System.out.println("\n" + encounter.intro + "\n");

        //Gegner-Team aus übergebenem Encounter ziehen
        ArrayList<Enemy> enemyTeam = encounter.enemyTeam;

        //Gegner anzeigen
        System.out.println(encounter.name + " greifen an:");
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
                System.out.println(encounter.name + " beginnen.");
            }
            Control.enterToContinue();

    //Innerer Battle Loop – wiederholt nach Gegner-Zug, solange Dämon nicht K.O.
            do {
                System.out.println("Maleficarius ist am Zug: \n");
                malActed = false;
                while (isPlayerTurn) {
                    System.out.println(encounter.name.toUpperCase());
                    counter = 1;
                    for (Enemy e : enemyTeam) {
                        if (!e.ko) {
                            System.out.printf("%s\t (%d/%d HP)", e.name, e.hp, e.hpMax);
                        } else {
                            System.out.printf("%s\t>> BESIEGT <<", e.name);
                        }
                        if (e.lifelined)
                            System.out.printf("\t, Lebenslinie (%d)", e.counterlifeline);
                        if (e.carriesVSeed)
                            System.out.printf("\t, Üble Saat (%d)", e.counterVSeed);
                        System.out.println();
                    }
                    System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    System.out.printf("%s \t(%d/%d HP)\n", Player.activeDemon.name.toUpperCase(), Player.activeDemon.hp, Player.activeDemon.hpMax);
                    System.out.print("[1] " + Player.activeDemon.attackName);
                    if (Player.activeDemon == WorldBuilder.dem01 && WorldBuilder.dem01.roar > 0)
                        System.out.printf(" (+%d)\n", WorldBuilder.dem01.roar);
                    else
                        System.out.println();
                    System.out.println("[2] " + Player.activeDemon.aoeAttackName);
                    System.out.println("[3] " + Player.activeDemon.selfBuffName);
                    if (!malActed) {
                        System.out.println("----------------------------------------------");
                        System.out.printf("[4] Zauber \t(%d/%d MP)\n", Player.mp, Player.mpMax);
                        System.out.println("[5] Beschwören");
                    }

                    try {
                        System.out.print("> ");
                        pick = sc.nextInt();
                        switch (pick) {
                            case 1:                             // Immer Single Target
                                counter = 1;
                                for (Enemy e : enemyTeam) {
                                    System.out.printf("[%d] %s\t (%d/%d HP)", counter, e.name, e.hp, e.hpMax);
                                    if (e.lifelined)
                                        System.out.printf("\t\tLebenslinie (%d)", e.counterlifeline);
                                    if (e.carriesVSeed)
                                        System.out.printf("\t\tÜble Saat (%d)", e.counterVSeed);
                                    System.out.println();
                                    counter++;
                                }

                                inputValid = false;
                                while (!inputValid) {
                                    try {
                                        System.out.print("Wähle ein Ziel.\n> ");
                                        pickedTarget = sc.nextInt();
                                        enemyTeam.get(pickedTarget - 1).applyDmg(Player.activeDemon.attack());
                                        inputValid = true;
                                        isPlayerTurn = false;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                        sc.next();
                                    } catch (IndexOutOfBoundsException e) {
                                        System.out.printf("Ungültige Eingabe. Gib eine Ziffer zwischen 1 und %d ein. \n", enemyTeam.size());
                                        sc.next();
                                    }
                                }
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
                                    System.out.println("=============ZAUBERBUCH============\n");
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
                                    System.out.println("[0] Zurück");
                                    try {
                                        System.out.print("> ");
                                        pick = sc.nextInt() - 1;
                                        if (pick == -1) {
                                            break;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                        sc.next();
                                    }
                                    //Zielauswahl, Unterscheidung Flächenschaden.
                                    Spell pickedSpell = Player.spellbook.get(pick);
                                    if (!pickedSpell.aoe && !pickedSpell.onDemon) {
                                        inputValid = false;
                                        while (!inputValid) {
                                            System.out.println("Wähle ein Ziel:");
                                            counter = 1;
                                            for (Enemy e : enemyTeam) {
                                                System.out.printf("[%d] %s \t", counter, e.name);
                                                counter++;
                                                if (!e.ko)
                                                    System.out.printf(" (%d/%d HP)", e.hp, e.hpMax);
                                                else
                                                    System.out.print(">> BESIEGT <<");
                                                if (e.lifelined)
                                                    System.out.printf("\t, Lebenslinie (%d)", e.counterlifeline);
                                                if (e.carriesVSeed)
                                                    System.out.printf("\t, Üble Saat (%d)", e.counterVSeed);
                                                System.out.println();
                                            }
                                            try {
                                                System.out.print("> ");
                                                pickedTarget = sc.nextInt() - 1;
                                                pickedSpell.cast(pickedTarget);
                                                inputValid = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                                sc.next();
                                            }
                                        }
                                    } else {
                                        pickedSpell.cast();
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
                                        } else {
                                            System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
                                        }
                                        counter++;
                                    }
                                    System.out.println("[0] Zurück");

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
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Ungültige Eingabe. Gib eine Ziffer ein.");
                                            sc.next();
                                        }
                                    }
                                } else {
                                    System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                                }
                                Control.enterToContinue();
                                break;
                            default:
                                System.out.println("Ungültige Eingabe. Wähle eine Zahl zwischen 1 und 5. \n");
                                Control.enterToContinue();
                                break;
                        } //Ende Switch-Case mit Spieleraktionen
                    } catch (InputMismatchException e) {
                        System.out.println("Ungültige Eingabe. Gib eine Ziffer ein. \n");
                        sc.next();
                        Control.enterToContinue();
                    }

                    //Spieler hat Aktion ausgeführt
                    //wenn Gegner gestorben sind, Lifeline auflösen oder ViciousSeed triggern
                    //und Prüfung auf Sieg
                    encounter.counterKO = 0;
                    for (Enemy e : enemyTeam) {
                        if (e.ko) {
                            encounter.counterKO++;
                            if (e.lifelined) {
                                e.lifelined = false;
                                System.out.printf("Die Lebenslinie zwischen %s und %s reißt.", e.name, Player.activeDemon.name);
                            }
                            if (e.carriesVSeed) {
                                WorldBuilder.viciousSeed.explode(e);
                            }
                        }

                    }
                    if (encounter.counterKO >= enemyTeam.size())
                        encounter.beaten = true;

                    //Dämon könnte durch Zauber wie Aderlass sterben,
                    //ggf. Lifeline auflösen und Prüfung auf Niederlage:
                    if (Player.activeDemon.ko) {
                        for (Enemy e : enemyTeam)
                            e.lifelined = false;
                        if (!encounter.beaten)
                            checkDefeat();
                    }

                } //Ende while(isPlayerTurn)

    //GEGNER AM ZUG
                while (!isPlayerTurn && !encounter.beaten) {
                    System.out.println(encounter.name + " sind am Zug.");
                    for (Enemy e : enemyTeam) {
                        if (e.lifelined)                         // Vor Aktion Zaubereffekte abhandeln, könnten dmg verursachen
                            WorldBuilder.lifeline.tick(e);
                        if (e.carriesVSeed) {
                            WorldBuilder.viciousSeed.tick(e);
                        }
                        if (!e.ko) {                                // Aktion, erst prüfen, ob K.O.
                                                                    //weitere If-Verzweigung je nach Enemy-Subklasse?
                            if ((e.hp <= e.hpMax * 0.3f) && (e.hasPotion)) {    // Wenn schwer verletzt und Potion vorhanden, dann Potion statt Angriff
                                e.drinkPotion();
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
                        if (Player.activeDemon.ko)
                            for (Enemy f : enemyTeam) {
                                if (f.lifelined) {
                                    f.lifelined = false;
                                    System.out.printf("Die Lebenslinie zwischen %s und %s reißt.", f.name, Player.activeDemon.name);
                                }
                            }
                        if (!e.ko)
                            Control.enterToContinue();      // mit If-Bedingung, sonst müsste man nach ausgefallener Handlung eines besiegten Gegners trotzdem Eingabetaste drücken.

                    } // Ende Aktion Gegnerteam
                    isPlayerTurn = true;
                } // Ende while(!isPlayerTurn && !encounter.beaten)

            } while (!Player.activeDemon.ko);  // Ende innerer Battle-Loop. Wiederholt, solange Dämon am Leben

            // Dämon besiegt, Prüfung auf Niederlage
            checkDefeat();

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
                sc.next();              // Puffer leeren
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


}





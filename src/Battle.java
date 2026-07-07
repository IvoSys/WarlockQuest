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
        System.out.println(encounter.intro);

        //Gegner-Team aus übergebenem Encounter ziehen
        ArrayList<Enemy> enemyTeam = encounter.enemyTeam;

        //Gegner anzeigen
        System.out.println("Gegner greifen an:");
        for (Enemy e : enemyTeam)
            System.out.printf("%s \t (%d/%d HP) \n", e.name, e.hp, e.hpMax);

        //Spieler wählt Dämon
        chooseDemon();

        //Äußerer Battle Loop
        do {
            //Klären, welche Partei beginnt, oder immer Dämon anfangen lassen
            if (true) {
                isPlayerTurn = true;
                System.out.println(Player.activeDemon.name + " beginnt.");
            } else {
                isPlayerTurn = false;
                System.out.println(encounter.enemyTeamName + " beginnen.");
            }

            //Innerer Battle Loop
            do {
                while (isPlayerTurn) {
                    malActed = false;
                    System.out.println("Du bist dran.");

                    System.out.printf("%d/%d HP \t\t%d/%d MP \n", Player.activeDemon.hp, Player.activeDemon.hpMax, Player.mp, Player.mpMax);
                    System.out.println(Player.activeDemon.name.toUpperCase());
                    System.out.println("[1] " /*+ /*Player.activeDemon.attack().name*/);
                    System.out.println("[2] " /*+ /*Player.activeDemon.ability1().name*/);
                    System.out.println("[3] " /*+ /*Player.activeDemon.ability2().name*/);
                    System.out.println("------------------------------------");
                    System.out.println("[4] Zauber");
                    System.out.println("[5] Trank");
                    System.out.println("[6] Beschwören");
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
                                    System.out.printf("     Mana: %d, \tStärke: %d \t", s.mpCost, s.str);
                                    if (s.aoe)
                                        System.out.print("Flächenwirkung");
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
                                boolean empty = true;
                                int counter = 1;
                                for (Potion p : Player.potions) {
                                    System.out.printf("[%d] %s [%d in Besitz] \n", counter, p.name, p.num);
                                    counter++;
                                    empty = false;
                                }
                                if (empty) {
                                    System.out.println("Du besitzt keine Tränke.");
                                }
                                System.out.println("[0] Zurück");
                                System.out.println("\n> ");
                                pick = sc.nextInt() - 1;
                                if (pick == -1) {
                                    break;
                                }
                                Player.potions.get(pick).drink();
                                Player.potions.get(pick).num--;
                                if (Player.potions.get(pick).num == 0)
                                    Player.potions.remove(pick);
                                malActed = true;
                            } else
                                System.out.println("Maleficarius hat diese Runde schon gehandelt.");
                            break;
                        case 6:
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
                    }

                    //Prüfung auf Sieg
                    encounter.counterKO = 0;
                    for (Enemy e : enemyTeam) {
                        if (e.ko)
                            encounter.counterKO++;
                    }
                    if (encounter.counterKO >= enemyTeam.size())
                        encounter.beaten = true;
                }

                while (!isPlayerTurn && !encounter.beaten) {
                    for (Enemy e : enemyTeam) {
                        if (!e.ko) {
                            //e greift an, sagt Dinge
                        }

                    }


                }
            } while (!Player.activeDemon.ko);
            //Prüfung auf Niederlage
            Player.counterKO = 0;
            for (Demon d : Player.team) {
                if (d.ko)
                    Player.counterKO++;
            }

        } while (Player.counterKO < Player.team.size() && !encounter.beaten);

        //Kampf gewonnen
        Player.room.encounterBeaten = true;
        System.out.println(Player.room.encounter.outro);
        if (Player.room.encounter.rewardItem != null) {
            Item.obtainItem(Player.room.encounter.rewardItem);
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



import java.util.ArrayList;

public class Player {

    static int mp = 50;
    static int mpMax = 50;
    static int spellpower = 10;
    static int level = 1;

    static ArrayList<Item> inv = new ArrayList<>();
    static ArrayList<Spell> spellbook = new ArrayList<>();
    static ArrayList<Demon> team = new ArrayList<>();
    static int counterKO;

    //Position
    static int curX = 0;
    static int curY = 0;
    static int curZ = 0;
    static Room room = WorldBuilder.castle[curX][curY][curZ];
    static boolean moved = true;


    public static void move(String input) {
        if (input.contains("nord") || input.equals("n") && room.north) {
            curY++;
            moved = true;
            System.out.println("Du gehst nach Norden.");
        } else if (input.contains("ost") || input.equals("o") && room.east) {
            curX++;
            moved = true;
            System.out.println("Du gehst nach Osten.");
        } else if (input.contains("süd") || input.equals("s") && room.south) {
            curY--;
            moved = true;
            System.out.println("Du gehst nach Süden.");
        } else if (input.contains("west") || input.equals("w") && room.west) {
            curX--;
            moved = true;
            System.out.println("Du gehst nach Westen.");
        } else if (input.contains("hoch") || input.equals("h") || input.contains("oben") && room.up) {
            curZ++;
            moved = true;
            System.out.println("Du gehst nach oben.");
        } else if (input.contains("runter") || input.equals("r") || input.contains("unten") && room.down) {
            curZ--;
            moved = true;
            System.out.println("Du gehst nach unten.");
        } else {
            System.out.println("Hier geht es nicht weiter.");
        }
    }

    public static void daimon() {                               //Zu nah an "Universalskript"?
        System.out.println(room.daimon);                        //Daimon-Kommentar für diesen Raum
        if ((room.puzzleID == -1) && (room.reward != null)) {   //In bestimmten Räumen führt Daimon spezielle Methoden aus
            Story.getKey000();
        }
    }

    public static boolean applyMpCost(int mpCost){

        if (mp - mpCost >= 0) {
            mp -= mpCost;
            return true;
        } else {
            return false;
        }

    }

    public static void showInv() {
        System.out.println("=============INVENTAR==============");
        for (Item i : inv) {
            if ((i instanceof Ingredience) || (i instanceof Potion))
                continue;
            System.out.printf("· %s ", i.name);
            if (i.num > 1)
                System.out.printf("[%d] \n", i.num);
            else
                System.out.print("\n");
        }
        System.out.println("===================================");
    }

    public static void showIngredients() {
        boolean empty = true;
        System.out.println("==========ALCHEMIEZUTATEN==========");
        for (Item i : inv) {
            if (i instanceof Ingredience) {
                System.out.printf("· %s [%d] \n", i.name, i.num);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("Keine einzige Zutat.");
        }
        System.out.println("===================================");
    }

    public static void showSpells() {
        boolean empty = true;
        System.out.println("=============ZAUBERBUCH============\n");
        for (Spell i : spellbook) {
            System.out.printf("%s \n", i.name.toUpperCase());
            System.out.printf("Manakosten: %d \tStärke: %d\n", i.mpCost, i.str);
            if (i.dur != 0)
                System.out.printf("Dauer: %d Runden\t\t", i.dur);
            if (i.aoe)
                System.out.print("Flächenwirkung ");
            else
                System.out.println();
            System.out.printf("%s \n\n", i.desc);
            empty = false;
        }
        if (empty) {
            System.out.println("Kein einziger Zauber.");
        }
        System.out.println("===================================");
    }

    public static void showPotions() {
        boolean empty = true;
        System.out.println("===============TRÄNKE===============");
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· \t%s \n", i.name);
                System.out.printf("\t%s \n", i.desc);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("Kein einziger Trank.");
        }
        System.out.println("====================================");
    }

    public static void checkItem(String input) {
        boolean found = false;
        for (Item i : inv) {
            if (input.contains(i.name.toLowerCase())) {
                System.out.println(i.desc);
                if (i == WorldBuilder.bagAlche)
                    showIngredients();
                else if (i == WorldBuilder.bagPotions)
                    showPotions();
                else if (i == WorldBuilder.bookSpells)
                    showSpells();

                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("So etwas habe ich nicht.");
    }

    public static void combineItems(String input1, String input2) {
        if (input1.equals(input2)) {                // Abbruch, wenn zweimal gleiches Item eingegeben wurde
            System.out.println("Das funktioniert nur mit zwei unterschiedlichen Gegenständen.");
        } else {
            Item item1 = null;
            Item item2 = null;

            for (Item i : inv) {                                // Inventar durchgeben,
                if (input1.equals(i.name.toLowerCase())) {      // wenn input mit Namen eines Items genau übereinstimmt
                    item1 = i;                                  // Item in Wegwerfvariable speichern
                }
                if (input2.equals(i.name.toLowerCase())) {      // für zweiten Input auch
                    item2 = i;
                }
            }
            // Abbruch, wenn eine oder beide Variablen nicht befüllt wurden, d.h. die angegebenen Items nicht im Inventar sind
            if (item1 == null && item2 == null)
                System.out.println("Ich habe nichts davon.");
            else if (item1 == null)
                System.out.println("Den ersten Gegenstand hast du nicht.");
            else if (item2 == null)
                System.out.println("Den zweiten Gegenstand hast du nicht.");
            else {

                // Items sind im Inventar
                // Abbruch, wenn IDs unterschiedlich oder 0 sind – diese Items können nicht kombiniert werden können
                if ((item1.combiID != item2.combiID) || item1.combiID == 0) {
                    System.out.println("Diese beiden Gegenstände lassen sich nicht kombinieren.");

                    // Items sind im Inventar vorhanden und können kombiniert werden.
                } else {

                    // Items erzeugen und Zutaten verbrauchen
                    if (item1.combiID == 1) {               // 2x id 1 -> Heiltrank (id 11)
                        Item.obtainItem(WorldBuilder.potHealth1);
                    } else if (item1.combiID == 2) {        // 2x id 2 -> Manatrank (id 22)
                        Item.obtainItem(WorldBuilder.potMana1);

                        boolean successConsumeItem1 = Item.consumeItem(item1);
                        boolean successConsumeItem2 = Item.consumeItem(item2);

                        // Meldung bei Fehler, sicherheitshalber
                        if (!successConsumeItem1)
                            System.out.println("DEBUG: Erstes Item konnte nicht verbraucht werden.");
                        if (!successConsumeItem2)
                            System.out.println("DEBUG: Zweites Item konnte nicht verbraucht werden.");
                    }
                }
            }
        }
    }

    public static void showStatus() {
        boolean first = true;
        System.out.printf("Maleficarius: \t%d/%d MP \n", mp, mpMax);
        for (Demon i : team) {
            if (first) {
                System.out.println("----------------------------");
            }
            System.out.printf("%s: \t%d/%d HP \n", i.name, i.hp, i.hpMax);
            first = false;
        }
        if (first) {
            System.out.println("Keine Dämonen gebunden");
        }

    }

    public static boolean levelUp() {
        level++;
        boolean alreadyMax = false;

        switch (level) {
            case 1: mpMax = 50; spellpower = 10; break;
            case 2: mpMax = 65; mp = mpMax; spellpower = 13; break;
            case 3: mpMax = 80; mp = mpMax; spellpower = 16; break;
            case 4: mpMax = 100; mp = mpMax; spellpower = 20; break;
            case 5: mpMax = 125; mp = mpMax; spellpower = 25; break;
            default: level = 5; mpMax = 125; spellpower = 25; alreadyMax = true; break;
        }

        for (Demon d : team) {
            d.toPlayerLevel();
        }

        if (alreadyMax) {
            System.out.println("Maleficarius kann nicht mehr stärker werden.");
        } else {
            System.out.println("Maleficarius und seine Dämonen steigen auf Stufe " + level + " auf.");
        }

        return !alreadyMax;

    }

    //Funktioniert noch nicht richtig!!!
    public static void cleanUpInv() {
        boolean merge;
        // Schleife von hinten nach vorne durchlaufen, damit keine Fehler entstehen,
        // wenn sich die Indexe durch Löschen eines Eintrags verschieben
        for (int i = Player.inv.size() - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (Player.inv.get(i).name.equals(Player.inv.get(j).name)) {
                    Player.inv.get(j).num += Player.inv.get(i).num;
                    Player.inv.remove(i);
                    break;
                }
            }
        }
        System.out.println("Gegenstände aufgeräumt.");
    }

    public static void cheatAllDemsAndSpells() {
        for (Demon d : WorldBuilder.freeDem)
            if (!Player.team.contains(d))
                Player.team.add(d);
        for (Spell s : WorldBuilder.freeSpells)
            if (!Player.spellbook.contains(s))
                Player.spellbook.add(s);
        System.out.println("Und das mit Recht; denn alles was entsteht, \nIst wert, dass es zugrunde geht.");
    }

}

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
        if ((input.contains("nord") || input.equals("n")) && room.north) {
            curY++;
            moved = true;
            System.out.println("Du gehst nach Norden.");
        } else if ((input.contains("ost") || input.equals("o")) && room.east) {
            curX++;
            moved = true;
            System.out.println("Du gehst nach Osten.");
        } else if ((input.contains("süd") || input.equals("s")) && room.south) {
            curY--;
            moved = true;
            System.out.println("Du gehst nach Süden.");
        } else if ((input.contains("west") || input.equals("w")) && room.west) {
            curX--;
            moved = true;
            System.out.println("Du gehst nach Westen.");
        } else if ((input.contains("hoch") || input.equals("h") || input.contains("oben")) && room.up) {
            curZ++;
            moved = true;
            System.out.println("Du gehst nach oben.");
        } else if ((input.contains("runter") || input.equals("r") || input.contains("unten")) && room.down) {
            curZ--;
            moved = true;
            System.out.println("Du gehst nach unten.");
        } else {
            System.out.println("Hier geht es nicht weiter.");
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
        System.out.println("================= INVENTAR =================");
        for (Item i : inv) {
            if ((i instanceof Ingredience) || (i instanceof Potion))
                continue;
            System.out.printf("· %s ", i.name);
            if (i.num > 1)
                System.out.printf("[%d] \n", i.num);
            else
                System.out.print("\n");
        }
        System.out.println();

        System.out.println("------------------ TRÄNKE ------------------");
        System.out.println("Verwende diese Tränke AUSSERHALB von Kämpfen:");
        boolean noPotion = true;
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· \t%s \n", i.name);
                System.out.printf("\t%s \n", i.desc);
                noPotion = false;
            }
        }
        if (noPotion) {
            System.out.println("[Kein einziger Trank]");
        }
        System.out.println();

        System.out.println("-------------- ALCHEMIEZUTATEN -------------");
        System.out.println("Braue Tränke aus diesen Zutaten:");
        boolean noIngred = true;
        for (Item i : inv) {
            if (i instanceof Ingredience) {
                System.out.printf("· %s [%d] \n", i.name, i.num);
                noIngred = false;
            }
        }
        if (noIngred) {
            System.out.println("[Keine einzige Zutat]");
        }
        System.out.println();
        System.out.println("============================================");
    }

    public static void showIngredients() {
        boolean noIngred = true;
        System.out.println("========== ALCHEMIEZUTATEN ==========");
        System.out.println("Braue Tränke aus diesen Zutaten:");
        for (Item i : inv) {
            if (i instanceof Ingredience) {
                System.out.printf("· %s [%d] \n", i.name, i.num);
                noIngred = false;
            }
        }
        if (noIngred) {
            System.out.println("Keine einzige Zutat.");
        }
        System.out.println("=====================================");
    }

    public static void showSpells() {
        boolean empty = true;
        System.out.println("============= ZAUBERBUCH ============");
        System.out.println("Wirke diese Zauber IN KÄMPFEN gegen MP:\n");
        for (Spell i : spellbook) {
            System.out.printf("%s \n", i.name.toUpperCase());
            System.out.printf("Manakosten: %d ", i.mpCost);
            if (i.dur != 0)
                System.out.printf("\tDauer: %d Runden\n", i.dur);
            else
                System.out.println();
            System.out.printf("%s \n\n", i.desc);
            empty = false;
        }
        if (empty) {
            System.out.println("Kein einziger Zauber.");
        }
        System.out.println("=====================================");
    }

    public static void showPotions() {
        boolean noPotion = true;
        System.out.println("=============== TRÄNKE ===============");
        System.out.println("Verwende diese Tränke AUSSERHALB von Kämpfen:");
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· \t%s \n", i.name);
                System.out.printf("\t%s \n", i.desc);
                noPotion = false;
            }
        }
        if (noPotion) {
            System.out.println("Kein einziger Trank.");
        }
        System.out.println("======================================");
    }

    public static void examine(String input) {
        boolean inInv = false;
        boolean inRoom = false;

        for (Item i : inv) {
            if (input.contains(i.name.toLowerCase())) {
                System.out.println(i.desc);
                if (i == WorldBuilder.bagAlche)
                    showIngredients();
                else if (i == WorldBuilder.bagPotions)
                    showPotions();
                else if (i == WorldBuilder.bookSpells)
                    showSpells();

                inInv = true;
                break;
            }
        }

        if (!inInv) {
            for (RoomObject o : room.objects){
                if (input.equals(o.name.toLowerCase()) || input.equals(o.name2.toLowerCase()) || input.equals(o.name3.toLowerCase()) || input.equals(o.name4.toLowerCase())) {
                    if (o.loot != null)
                        System.out.println(o.descFull);
                    else
                        System.out.println(o.descEmpty);

                    inRoom = true;
                    break;
                }
            }
        }
        if (!inInv && !inRoom)
             System.out.println("Nicht zu finden.");
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

    //Funktioniert nicht richtig!!!
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

import java.util.ArrayList;

public class Player {

    static String name = "Maleficarius";

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
    static Room room = BuildCastle.castle[curX][curY][curZ];
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

    public static void examine(String input) {
        boolean inInv = false;
        boolean inRoomLoot = false;
        boolean inRoomObjects = false;

        for (Item i : inv) {
            if (input.contains(i.name.toLowerCase())) {
                System.out.println(i.desc);
                if (i == BuildItems.belongings)
                    showCoreItems();
                else if (i == BuildItems.bagAlche)
                    showIngredients();
                else if (i == BuildItems.bagPotions)
                    showPotions();
                else if (i == BuildItems.bookCodex)
                    showDemons();
                else if (i == BuildItems.bookSpells)
                    showSpells();

                inInv = true;
                break;
            }
        }

        if (!inInv) {
            for (Item i : Player.room.loot) {
                if (input.contains(i.nameVague.toLowerCase()) || input.contains(i.name.toLowerCase())) {
                    System.out.println(i.desc);
                    inRoomLoot = true;
                    break;
                }
            }
            if (!inRoomLoot) {
                for (RoomObject o : room.objects) {
                    if (o.loot != null && (input.equals(o.loot.name.toLowerCase()) || input.equals(o.loot.nameVague.toLowerCase()))) {
                        System.out.println(o.loot.desc);
                        inRoomLoot = true;
                        break;
                    }
                }
            }
        }

        if (!inInv && !inRoomLoot) {
            for (RoomObject o : room.objects){
                if (input.equals(o.name.toLowerCase()) || input.equals(o.name2.toLowerCase()) || input.equals(o.name3.toLowerCase()) || input.equals(o.name4.toLowerCase())) {
                    if (o.loot != null)
                        System.out.println(o.descFull);
                    else
                        System.out.println(o.descEmpty);

                    inRoomObjects = true;
                    break;
                }
            }
        }

        if (!inInv && !inRoomLoot && !inRoomObjects)
            System.out.println("Nicht zu finden.");
    }

    public static void loot(String input){
        boolean received = false;
        boolean lootExists = false;

        if (!room.loot.isEmpty()) {                      // In Raum suchen
            for (Item i : room.loot) {
                if (input.contains(i.name.toLowerCase()) || input.contains(i.nameVague.toLowerCase())) {
                    if (i instanceof Spellscroll) {
                        System.out.println("Du erhältst die " + i.name + ".");
                        Spell.learnOnTake(((Spellscroll) i).nameSpell);
                    } else {
                        inv.add(i);
                        System.out.printf("Du nimmst an dich: %s \n", i.name);
                    }
                    room.loot.remove(i);
                    received = true;
                    break;
                }
            }
        }

        if (!room.objects.isEmpty()){        // In Raumobjekten suchen
            for (RoomObject o : room.objects){
                if (o.loot != null && (o.loot.name.toLowerCase().equals(input) || o.loot.nameVague.toLowerCase().equals(input) )) {
                    if (o.loot instanceof Spellscroll) {
                        System.out.println("Du erhältst die " + o.loot.name + ".");
                        Spell.learnOnTake(((Spellscroll) o.loot).nameSpell);
                    } else {
                        inv.add(o.loot);
                        System.out.printf("Du nimmst an dich: %s \n", o.loot.name);
                    }
                    o.loot = null;
                    received = true;
                    break;
                }
            }
        }

        if (!received) {       // Wenn nichts erhalten, Raumobjekte prüfen und tryLoot ausgeben
            for (RoomObject o : room.objects) {
                if (input.equals(o.name.toLowerCase()) || input.equals(o.name2.toLowerCase()) || input.equals(o.name3.toLowerCase()) || input.equals(o.name4.toLowerCase())) {
                    System.out.println(o.tryLoot);
                    received = true;
                    break;
                }
            }
        }

        if (!received) {                            // Wenn immer noch nichts erhalten,
            if (!room.loot.isEmpty()) {             // prüfen, ob grundsätzlich Loot vorhanden
                lootExists = true;
            } else {
                for (RoomObject o : room.objects) {
                    if (o.loot != null) {
                        lootExists = true;
                        break;
                    }
                }
            }
        }

        if (!received && !lootExists)                     // Wenn gar kein Loot vorhanden, Hinweis ausgeben.
            System.out.println("Hier gibt es nichts zu holen.");
        else if (!received)                               // Wenn Loot vorhanden, aber nichts erhalten wurde
            System.out.println("Leider nicht.");

    }

    public static void showInv() {
        System.out.println("================== INVENTAR ==================");
        for (Item i : inv) {
            if ((i instanceof Ingredience) || (i instanceof Potion) || (i instanceof CoreItem))
                continue;
            System.out.printf("· %s ", i.name);
            if (i.num > 1)
                System.out.printf("[%d] \n", i.num);
            else
                System.out.print("\n");
        }
        System.out.println();

        System.out.println("------------------- TRÄNKE -------------------");
        System.out.println("Verwende diese Tränke AUSSERHALB von Kämpfen:");
        boolean noPotion = true;
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· %s [%d]\n", i.name, i.num);
                System.out.printf("  %s \n", i.desc);
                noPotion = false;
            }
        }
        if (noPotion) {
            System.out.println("[Kein einziger Trank]");
        }
        System.out.println();

        System.out.println("--------------- ALCHEMIEZUTATEN --------------");
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
        System.out.println("==============================================");
    }

    public static void showDemons() {
        System.out.println("==================== CODEX INFERNALIS ====================");
        if (Player.team.isEmpty())
            System.out.println("Du hast noch keine Dämonen gebunden. \n");
        else {
            boolean first = true;
            for (Demon d : Player.team) {
                if (first) {
                    System.out.println("Das Nachschlagewerk zu den dir dienenden Dämonen.\n");
                } else {
                    System.out.println("------------------------------------------------------\n");
                }
                System.out.print(d.name.toUpperCase());
                System.out.println(", " + d.otherNames);
                System.out.printf("Leben: %d/%d \t\tMacht: %d \t\tGewandheit: %d\n", d.hp, d.hpMax, d.power, d.dex);
                System.out.println(d.desc);
                System.out.println();
                System.out.println("1. " + d.attackName);
                System.out.println(d.attackDesc);
                System.out.println("2. " + d.aoeAttackName);
                System.out.println(d.aoeAttackDesc);
                System.out.println("3. " + d.selfBuffName);
                System.out.println(d.selfBuffDesc);
                first = false;
            }
        }
        System.out.println("==========================================================");
    }

    public static void showSpells() {
        boolean empty = true;
        System.out.println("======================= ZAUBERBUCH =======================");
        System.out.println("Wirke diese Zauber IM KAMPF gegen MP: \n");
        for (Spell i : spellbook) {
            System.out.printf("%s \n", i.name.toUpperCase());
            System.out.printf("Manakosten: %d ", i.mpCost);
            if (i.dur != 0)
                System.out.printf("\t\tDauer: %d Runden\n", i.dur);
            else
                System.out.println();
            System.out.printf("%s \n\n", i.desc);
            empty = false;
        }
        if (empty) {
            System.out.println("Du hast noch keinen Zauber gelernt.");
        }
        System.out.println("==========================================================");
    }

    public static void showIngredients() {
        boolean noIngred = true;
        System.out.println("=============== ALCHEMIEZUTATEN ==============");
        System.out.println("Braue Tränke aus diesen Zutaten:");
        for (Item i : inv) {
            if (i instanceof Ingredience) {
                System.out.printf("· %s [%d] \n", i.name, i.num);
                noIngred = false;
            }
        }
        if (noIngred) {
            System.out.println("[Keine einzige Zutat]");
        }
        System.out.println("==============================================");
    }

    public static void showPotions() {
        boolean noPotion = true;
        System.out.println("=================== TRÄNKE ===================");
        System.out.println("Verwende diese Tränke AUSSERHALB von Kämpfen:");
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· %s [%d]\n", i.name, i.num);
                System.out.printf("  %s \n", i.desc);
                noPotion = false;
            }
        }
        if (noPotion) {
            System.out.println("[Kein einziger Trank]");
        }
        System.out.println("==============================================");
    }

    public static void showCoreItems() {
        for (Item i : inv) {
            if (i instanceof CoreItem) {
                System.out.printf("· %s \n", i.name);
            }
        }
        System.out.println("\nSieh dir deine Besitztümer jederzeit genauer an.");
    }

    public static void showStatus() {
        System.out.println("=============== STATUS ===============");
        System.out.printf("%s: \t%d/%d MP \n", name, mp, mpMax);
        System.out.printf("Hexenmeister der Stufe %d \n", level);
        System.out.printf("Zaubermacht: %d \n", spellpower);
        System.out.println("--------------------------------------");
        boolean noDemon = true;
        for (Demon i : team) {
            System.out.printf("%s: \t%d/%d HP \n", i.name, i.hp, i.hpMax);
            noDemon = false;
        }
        if (noDemon) {
            System.out.println("Keine Dämonen gebunden");
        }
        System.out.println("--------------------------------------");
        boolean noPotion = true;
        for (Item i : inv) {
            if (i instanceof Potion) {
                System.out.printf("· %s [%d]\n", i.name, i.num);
                noPotion = false;
            }
        }
        if (noPotion) {
            System.out.println("Keine Tränke in Besitz");
        }
        System.out.println("======================================");
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
            System.out.println(name + " kann nicht mehr stärker werden.");
        } else {
            System.out.println(name + " und seine Dämonen steigen auf Stufe " + level + " auf.");
        }

        return !alreadyMax;

    }

    public static boolean applyMpCost(int mpCost){
        if (mp - mpCost >= 0) {
            mp -= mpCost;
            return true;
        } else {
            return false;
        }
    }


    //CleanUpInv() funktioniert nicht richtig!!!
    /*public static void cleanUpInv() {
        boolean merge;
        // Schleife von hinten nach vorne durchlaufen, damit keine Fehler entstehen,
        // wenn sich die Indexe durch Löschen eines Eintrags verschieben
        for (int i = inv.size() - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (inv.get(i).name.equals(inv.get(j).name)) {
                    inv.get(j).num += inv.get(i).num;
                    inv.remove(i);
                    break;
                }
            }
        }
        System.out.println("Gegenstände aufgeräumt.");
    }

     */

    public static void cheatAllDemsAndSpells() {
        for (Demon d : BuildItems.freeDem)
            if (!team.contains(d))
                team.add(d);
        for (Spell s : BuildItems.freeSpells)
            if (!spellbook.contains(s))
                spellbook.add(s);
        System.out.println("Und das mit Recht; denn alles was entsteht, \nIst wert, dass es zugrunde geht.");
    }

}

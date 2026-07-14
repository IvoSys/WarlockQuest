public abstract class Item {

    protected String name;
    protected String nameVague;
    protected String namePlural;
    protected String desc;
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int puzzleID = 0;                    //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 0 für neutral, room000 stattdessen -1
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Zwei Items mit gleicher combiID können kombiniert werden, Items mit combiID = 0 können nicht kombiniert werden, daher Standardwert.


    public static boolean consumeItem(Item itemConsumed) {
        boolean success = false;

        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            Player.inv.get(i).num--;
            System.out.printf("%s verbraucht", Player.inv.get(i).name);
            success = true;
            if  (Player.inv.get(i).num <= 0) {
                Player.inv.remove(i);
                System.out.println(", Vorrat erschöpft.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
        return success;
    }

    public static boolean consumeItemMult(Item itemConsumed, int numConsumed) {
        boolean success = false;

        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            if (Player.inv.get(i).num >= numConsumed) {
                Player.inv.get(i).num -= numConsumed;
                success = true;
                System.out.printf("%d %s verbraucht", numConsumed, Player.inv.get(i).namePlural);
            } else {
                System.out.printf("Du hast nicht genug %s. \n", itemConsumed.namePlural);
            }
            if  (Player.inv.get(i).num <= 0) {
                Player.inv.remove(i);
                System.out.println(", Vorrat erschöpft.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
        return success;
    }

    public static void obtainItem(Item itemObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num++;
        } else {
            Player.inv.add(itemObtained);
        }
        System.out.printf("%s erhalten \n", itemObtained.name);
    }

    public static void obtainItemMult(Item itemObtained, int numObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num += numObtained;
        } else {
            Player.inv.add(itemObtained);
            Player.inv.getLast().num = numObtained;
        }
        System.out.printf("%d %s erhalten \n", numObtained, itemObtained.namePlural);
    }

}

/*
Je zwei unterschiedliche Items mit gleicher combiID =! 0 ergeben

404 reserviert für Kombinieren-Methode


ID      ZUTATEN                             PRODUKT         ID
1       rote Zutaten                        Heiltrank       11
2       blaue Zutaten                       Manatrank       22
3       gelbe
4       blaue
5       schwarze                            Level-up-Trank  55
6
7
8
9
10      weiße (Verstärker)                  starker Heil-/Manatrank 110/220
        1x weiß + Trank                     mächtiger Heil-/Manatrank 1100/2200

12
13
14
15
16
17
18
19
20

 */



class Evocation extends Item {

    String demonShortName;

    public Evocation(String scrollTitle, String nameVague, String desc, String demonShortName) {
        name = scrollTitle;                     //Name der Beschwörungsformel
        this.nameVague = nameVague;
        this.desc = desc;                       //Text mit Rätsel zum trueName des Dämonen
        this.demonShortName = demonShortName;   //Kann mit name-Variablen des Dämonen abgeglichen werden
    }


    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().trim();                     // Nutzer gibt ein, was er für wahren Namen eines Dämonen hält.
        for (int i = 0; i < WorldBuilder.freeDem.size(); i++) {                     // Liste durchgehen und
            if (WorldBuilder.freeDem.get(i).trueName.equals(WarlockQuest.input)) {  // Dämon finden, dessen trueName mit der Eingabe übereinstimmt.
                Demon toBind = WorldBuilder.freeDem.get(i);                         // Treffer für Lesbarkeit in temp-Variablen speichern.

                if (Player.team.contains(toBind)) {                                 // Prüfen, ob dieser Dämon bereits gebunden wurde.
                    System.out.println("Diesen Dämon hast du bereits gebunden.");

                } else {                                                            // Wenn nicht, dann
                    for (Item j : Player.inv) {                                     // Inventar durchgehen und prüfen,
                        if (j.name.contains("Beschwörungsformel")) {
                            if (((Evocation) j).demonShortName.equals(toBind.name)) { // ob Beschwörungsformel für diesen Dämon in Besitz ist
                                Player.inv.remove(j);                                   // Wenn ja, kann Formel vernichtet und Dämon gebunden werden.
                                System.out.println("Die Beschwörungsformel verbrennt unter deinen Fingern.");
                                toBind.toPlayerLevel();                                 // Dämon-Stufe anpassen und …
                                Player.team.add(toBind);                                // endlich ins Team aufnehmen!
                                System.out.println(toBind.textWhenBound);
                                //WorldBuilder.freeDem.remove(j);                       // Eintrag nicht löschen, damit später noch abgeglichen werden kann für "bereits gebunden".
                                success = true;                                         // Den Prozess als erfolgreich kennzeichnen und Schleife abbrechen.
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (!success)                   // Wenn das Ganze nicht funktioniert hat, anderen Text ausgeben
            System.out.println("Hämisches Gelächter hämmert in deinem Schädel hin und her wie der Klöppel eine Glocke. \nDieses Mal hast du keinen neuen Diener erhalten.");
    }

}

class Ingredience extends Item {

    public Ingredience(String name, String nameVague, String namePlural, String desc, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.namePlural = namePlural;
        this.desc = desc;
        this.combiID = combiID;

    }
}

class Key extends Item {

    public Key(String name, String nameVague, String desc, int puzzleID, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.puzzleID = puzzleID;
        this.combiID = combiID;
    }
}

abstract class Potion extends Item{

    protected int str;

    public Potion() {}

    public static void drink(String trank) {
        for (Item i : Player.inv) {
            if (i instanceof Potion && i.name.equals(trank)) {
                if (((Potion) i).effect())
                    Item.consumeItem(i);
                break;
            }
        }
    }

    public abstract boolean effect();

}

class Healthpotion extends Potion{

    public Healthpotion(String name, String nameVague, String desc, int str, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public boolean effect() {
        int count = 1;
        int pick;
        boolean success = false;

        if (Player.team.isEmpty()) {
            System.out.println("Du hast keine Dämonen, die du heilen könntest.");
        } else {

            for (Demon d : Player.team) {
                System.out.printf("[%d] %s \t", count, d.name);
                if (d.ko)
                    System.out.println(">> BESIEGT <<");
                else
                    System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
            }
            System.out.println("Welchem Dämon willst du den Trank einflößen?");
            System.out.print("> ");
            pick = WarlockQuest.sc.nextInt();

            Demon healedDemon = Player.team.get(pick - 1);

            if (healedDemon.hp == healedDemon.hpMax) {
                System.out.printf("%s ist nicht verletzt", healedDemon.name);
            } else {
                healedDemon.hp += str;
                healedDemon.ko = false;             // falls Heiltränke wiederbeleben können sollen, sonst stattdessen in if-Bedingung aufnehmen.
                success = true;
                if (healedDemon.hp > healedDemon.hpMax) {
                    healedDemon.hp = healedDemon.hpMax;
                }
            }
        }
        return success;
    }
}

class Levelpotion extends Potion {

    public Levelpotion(String name, String nameVague, String desc, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.combiID = combiID;
    }

    public boolean effect() {

        return Player.levelUp();

    }
}

class Manapotion extends Potion{

    public Manapotion(String name, String nameVague, String desc, int str, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public boolean effect() {
        boolean sucess = false;

        if (Player.mp == Player.mpMax) {
            System.out.println("Maleficarius ist nicht erschöpft.");
        } else {
            Player.mp += str;
            sucess = true;
            if (Player.mp > Player.mpMax) {
                Player.mp = Player.mpMax;
            }
        }
        return sucess;
    }
}

class Spellscroll extends Item {

    String nameSpell;

    public Spellscroll(String name, String nameVague, String desc, String nameSpell) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.nameSpell = nameSpell;
    }

    public static void learn() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Zauber zu erlernen. \nGib die Zauberformel ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().trim();                     // Nutzer gibt ein, was er für Formel eines Zaubers hält.
        for (int i = 0; i < WorldBuilder.freeSpells.size(); i++) {                  // Liste durchgehen und
            if (WorldBuilder.freeSpells.get(i).formula.equals(WarlockQuest.input)) { // Zauber finden, dessen formula mit der Eingabe übereinstimmt.
                Spell toLearn = WorldBuilder.freeSpells.get(i);                     // Treffer für Lesbarkeit in temp-Variablen speichern.

                if (Player.spellbook.contains(toLearn)) {                           // Prüfen, ob dieser Zauber bereits erlernt wurde.
                    System.out.println("Diesen Zauber beherrschst du bereits.");

                } else {                                                            // Wenn nicht, dann
                    for (Item j : Player.inv) {                                     // Inventar durchgehen und prüfen,
                        if (j.name.contains("Zauberschriftrolle")) {
                            if (((Spellscroll) j).nameSpell.equals(toLearn.name)) {       // ob Schriftrolle für diesen Zauber in Besitz ist
                                Player.inv.remove(j);                                   // Wenn ja, kann Formel vernichtet und Zauber erlernt werden.
                                System.out.println("Die Zauberschriftrolle verbrennt unter deinen Fingern.");
                                Player.spellbook.add(toLearn);
                                System.out.println(toLearn.textWhenLearned);
                                //WorldBuilder.freeSpells.remove(i);                    // Eintrag nicht löschen, damit später noch abgeglichen werden kann für "beherrschst du bereits".
                                success = true;                                         // Den Prozess als erfolgreich kennzeichnen und Schleife abbrechen.
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (!success)                       // Wenn das Ganze nicht funktioniert hat, anderen Text ausgeben
            System.out.println("Dir raucht der Kopf vor Anstrengung. \nDiesen Zauber verstehst du einfach nicht!");
    }

}


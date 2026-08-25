public abstract class Item {

    protected String name;
    protected String desc;
    protected String nameVague;
    protected String descInRoom;           // Room.describe() geht Loot-Liste des Raumes durch und hängt für jedes Item diese Variable an die Raumbeschreibung an. Wurde Item bereits gelootet, wird es nicht mehr erwähnt!
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int ID = 0;                  // ID für Schlüssel und Rätselitems, wird mit der ID des Raums abgeglichen, 0 für neutral, room000 stattdessen -1
    protected int combiID = 0;             // ID zum Kombinieren zweier Items. Zwei Items mit gleicher combiID können kombiniert werden, Items mit combiID  0 können nicht kombiniert werden, daher Standardwert.
                                           // Negative combiID für Bündel – mit "u.(name)" verwenden, um auszupacken. Bündel wird gelöscht, Inhalt kommt ins Inventar.


    public static void consume(Item itemConsumed) {
        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            Player.inv.get(i).num--;
            System.out.printf("%s verbraucht", Player.inv.get(i).name);
            if  (Player.inv.get(i).num <= 0) {
                Player.inv.remove(i);
                //System.out.println(", Vorrat erschöpft");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
    }

    public static boolean consumeMult(Item itemConsumed, int numConsumed) {
        boolean success = false;

        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            if (Player.inv.get(i).num >= numConsumed) {
                Player.inv.get(i).num -= numConsumed;
                success = true;
                System.out.printf("%dx %s verbraucht", numConsumed, Player.inv.get(i).name);
            } else {
                System.out.printf("Nicht ausreichend vorhanden: %s \n", itemConsumed.name);
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

    public static void obtain(Item itemObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num++;
        } else {
            Player.inv.add(itemObtained);
        }
        System.out.printf("%s erhalten \n", itemObtained.name);
    }

    public static void obtainMult(Item itemObtained, int numObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num += numObtained;
        } else {
            Player.inv.add(itemObtained);
            Player.inv.getLast().num = numObtained;
        }
        System.out.printf("%dx %s erhalten \n", numObtained, itemObtained.name);
    }

    public static void unpack(String input){
        Item toUnpack = null;
        int id = 0;

        for (Item i : Player.inv) {
            if (i.name.toLowerCase().equals(input) || i.nameVague.toLowerCase().equals(input)) {
                toUnpack = i;
                id = i.combiID;
            }
        }

        switch (id) {
            case -1:
                obtain(BuildItems.evoc01);
                obtain(BuildItems.scrollViciousSeed);
                Player.inv.remove(toUnpack);
                System.out.println("\nSchriftrollen! … Zwei von etlichen mehr. Die Wachen müssen einen Großteil deiner Pergamente und Trankzutaten gestohlen haben. \nOb diese Proleten überhaupt lesen können? Hoffentlich treiben sie mit deinen wertvollen Formeln keinen Mumpitz.");
                break;
                    /*case -2:
                        obtainItem();
                        obtainItem();
                        System.out.println("");
                        break;
                    case -3:
                        obtainItem();
                        obtainItem();
                        System.out.println("");
                        break;*/
            default:
                System.out.println("DEBUG: Item-Bündel konnte nicht aufgelöst werden, Case-Zuordnung in Item.unpack() nicht möglich."); break;
        }
    }

    public static void combine(String input1, String input2) {
        if (input1.equals(input2)) {                // Abbruch, wenn zweimal gleiches Item eingegeben wurde
            System.out.println("Das funktioniert nur mit zwei unterschiedlichen Gegenständen.");
        } else {
            Item item1 = null;
            Item item2 = null;

            for (Item i : Player.inv) {                                // Inventar durchgeben,
                if (input1.equals(i.name.toLowerCase())) {      // wenn input mit Namen eines Items genau übereinstimmt
                    item1 = i;                                  // Item in Wegwerfvariable speichern
                }
                if (input2.equals(i.name.toLowerCase())) {      // für zweiten Input auch
                    item2 = i;
                }
            }
            // Abbruch, wenn eine oder beide Variablen nicht befüllt wurden, d.h. die angegebenen Items nicht im Inventar sind
            if (item1 == null && item2 == null)
                System.out.println("Du hast nichts davon.");
            else if (item1 == null)
                System.out.println("Den ersten Gegenstand hast du nicht.");
            else if (item2 == null)
                System.out.println("Den zweiten Gegenstand hast du nicht.");
            else {

                // Items sind im Inventar
                // Wenn eine ID = 10, könnte eine Verstärkung durchgeführt werden
                if ((item1.combiID == 10) || item2.combiID == 10) {                 // Ein Item ist Katalysator …
                    if ((item1.combiID == 11) || item2.combiID == 11)               // … ein Item ist Heiltrank1
                        obtain(BuildItems.potHealth2);                   //  -> Heiltrank2
                    else if ((item1.combiID == 110) || item2.combiID == 110)        // … ein Item ist Heiltrank2
                        obtain(BuildItems.potHealth3);                   //  -> Heiltrank3
                    else if ((item1.combiID == 22) || item2.combiID == 22)          // … ein Item ist Manatrank1
                        obtain(BuildItems.potMana2);                     //  -> Manatrank2
                    else if ((item1.combiID == 220) || item2.combiID == 220)        // … ein Item ist Manatrank2
                        obtain(BuildItems.potMana3);                     //  -> Manatrank3
                    else
                        System.out.println("Dieser Gegenstand kann nicht mit einem Katalysator verstärkt werden.");

                    consume(item1);
                    consume(item2);

                    // Abbruch, wenn IDs unterschiedlich oder 0 sind – diese Items können nicht kombiniert werden können
                } else if ((item1.combiID != item2.combiID) || item1.combiID == 0) {
                    System.out.println("Diese beiden Gegenstände lassen sich nicht kombinieren.");

                    // Items sind im Inventar vorhanden und können kombiniert werden.
                } else {

                    // Items erzeugen und Zutaten verbrauchen
                    if (item1.combiID == 1) {               // 2x id 1 -> Heiltrank (id 11)
                        obtain(BuildItems.potHealth1);
                    } else if (item1.combiID == 2) {        // 2x id 2 -> Manatrank (id 22)
                        obtain(BuildItems.potMana1);
                    } else if (item1.combiID == 3) {        // 2x id 3 -> Trank (id 33)
                        //Item.obtainItem(WorldBuilder.);
                        System.out.println("PLATZHALTER: Dies erzeugt Trank mit Combi-ID 33");
                    } else if (item1.combiID == 4) {        // 2x id 4 -> Trank (id 44)
                        //Item.obtainItem(WorldBuilder.);
                        System.out.println("PLATZHALTER: Dies erzeugt Trank mit Combi-ID 44");
                    } else if (item1.combiID == 5) {        // 2x id 5 -> Level-up-Trank (id 55)
                        obtain(BuildItems.potLevelUp);

                    } else if (item1.combiID == 12) {        // 2x id 12 -> Käsebrot
                        obtain(BuildItems.cheeseBread);
                    } else if (item1.combiID == 13) {        // 2x id 13 -> Sandwich
                        obtain(BuildItems.sandwich);
                    } else if (item1.combiID == 14) {        // 2x id 14 ->
                        //Item.obtainItem(WorldBuilder.);
                    } else if (item1.combiID == 15) {        // 2x id 15 ->
                        //Item.obtainItem(WorldBuilder.);
                    }

                    consume(item1);
                    consume(item2);

                }
            }
        }
    }
}

class Evocation extends Item {

    String demonShortName;
    String hint;

    public Evocation(String scrollTitle, String nameVague, String desc, String demonShortName, String hint) {
        name = scrollTitle;                     //Name der Beschwörungsformel
        this.nameVague = nameVague;
        this.desc = desc;                       //Text mit Rätsel zum trueName des Dämonen
        this.demonShortName = demonShortName;   //Kann mit name-Variablen des Dämonen abgeglichen werden
        this.hint = hint;
    }

    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().trim();                     // Nutzer gibt ein, was er für wahren Namen eines Dämonen hält.
        for (int i = 0; i < BuildItems.freeDem.size(); i++) {                     // Liste durchgehen und
            if (BuildItems.freeDem.get(i).trueName.equals(WarlockQuest.input)) {  // Dämon finden, dessen trueName mit der Eingabe übereinstimmt.
                Demon toBind = BuildItems.freeDem.get(i);                         // Treffer für Lesbarkeit in temp-Variablen speichern.

                if (Player.team.contains(toBind)) {                                 // Prüfen, ob dieser Dämon bereits gebunden wurde.
                    System.out.println("Diesen Dämon hast du bereits gebunden.");

                } else {                                                            // Wenn nicht, dann
                    for (Item j : Player.inv) {                                     // Inventar durchgehen und prüfen,
                        if (j.name.contains("Beschwörungsformel") && !j.name.contains("des vertrauten Geistes")) {
                            if (((Evocation) j).demonShortName.equals(toBind.name)) { // ob Beschwörungsformel für diesen Dämon in Besitz ist
                                Player.inv.remove(j);                                   // Wenn ja, kann Formel vernichtet und Dämon gebunden werden.
                                toBind.toPlayerLevel();                                 // Dämon-Stufe anpassen und …
                                Player.team.add(toBind);                                // endlich ins Team aufnehmen!
                                System.out.println(toBind.textWhenBound);
                                //WorldBuilder.freeDem.remove(j);                       // Eintrag nicht löschen, damit später noch abgeglichen werden kann für "bereits gebunden".
                                success = true;                                         // Den Prozess als erfolgreich kennzeichnen und Schleife abbrechen.
                                if (toBind.name.equals(BuildItems.dem01.name)) {
                                    System.out.println("DAIMON: \"Yeah, mit diesem Viech zeigen wir den Wachen, wo der Hammer hängt!\"");
                                    BuildCastle.castle[0][1][2].daimon = BuildCastle.castle[0][1][2].daimonB;
                                }
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

    public static void hint() {
        boolean found = false;
        for (Item i : Player.inv)
            if (i instanceof Evocation) {
                System.out.println(((Evocation) i).hint);
                found = true;
            }
        if (!found)
            System.out.println("Keine ungelöste Beschwörungsformel im Inventar.");

    }

}

class GeneralItem extends Item {
    public GeneralItem(String name, String nameVague, String desc, String descInRoom, int roomID, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.descInRoom = descInRoom;
        this.ID = roomID;
        this.combiID = combiID;
    }
}

class Ingredience extends Item {

    public Ingredience(String name, String nameVague, String desc, String descInRoom, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.descInRoom = descInRoom;
        this.combiID = combiID;

    }
}

class Key extends Item {

    public Key(String name, String nameVague, String desc, String descInRoom, int roomID, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.descInRoom = descInRoom;
        this.ID = roomID;
        this.combiID = combiID;
    }
}

class PuzzleItem extends Item {

    public PuzzleItem(String name, String nameVague, String desc, String descInRoom, int roomID, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.descInRoom = descInRoom;
        this.ID = roomID;
        this.combiID = combiID;
    }
}

abstract class Potion extends Item{

    protected int str;

    public Potion() {}

    public static void drink(String trank) {
        for (Item i : Player.inv) {
            if (i instanceof Potion && i.name.toLowerCase().equals(trank)) {
                if (((Potion) i).effect()) {
                    Item.consume(i);
                    break;
                }
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

    @Override
    public boolean effect() {
        int count = 1;
        int pick;
        boolean success = false;
        WarlockQuest.sc.useDelimiter("\\R");

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
                if (healedDemon.hp >= healedDemon.hpMax) {
                    healedDemon.hp = healedDemon.hpMax;
                    System.out.println(healedDemon.name + " wurde vollständig geheilt.");
                } else {
                    System.out.printf("%s wurde geheilt und hat nun %d/%d HP.\n", healedDemon.name, healedDemon.hp, healedDemon.hpMax);
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

    @Override
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
        if (sucess && Player.mp == Player.mpMax) {
            System.out.println("Maleficarius ist wieder vollständig erfrischt. ");
        } else if (sucess)
            System.out.printf("Maleficarius fühlt sich erfrischt und hat nun %d/%d MP. ", Player.mp, Player.mpMax);

        return sucess;
    }
}

class Spellscroll extends Item {

    String nameSpell;

    public Spellscroll(String name, String nameVague, String desc, String descInRoom, String nameSpell) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.descInRoom = descInRoom;
        this.nameSpell = nameSpell;
    }
}


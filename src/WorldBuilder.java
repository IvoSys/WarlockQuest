import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldBuilder {

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Methode: Schloss bauen
    public static void buildCastle() {

        //UG, Reihe unten
        castle[0][0][0] = new Room("Gefängniszelle", Story.desc000, Story.daimon000, Story.solved000, false, false, false, false, false, false, 0);
        castle[0][0][0].reward = key000; castle[0][0][0].dummyLoot = "Zellenschlüssel, Waffe, Ausrüstung"; castle[0][0][0].dummyFeedback = "Ich komme nicht dran"; castle[0][0][0].descSolved = Story.descSolved000; castle[0][0][0].daimonSolved = Story.daimonSolved000;
        castle[0][0][1] = new Room("Kerker", Story.desc001, Story.daimon001, Story.solved001, false, true, false, true, false, false, 1);
        castle[0][0][1].dummyLoot = "Zeug, Ausrüstung, Sachen"; castle[0][0][1].dummyFeedback = "Das ist leider nicht hier.";
        castle[0][0][2] = new Room("zugiger Kellergang", Story.desc002, Story.daimon002, Story.solved002, true, false, false, true, false, false, 2);
        //    [z][y][x]

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("Offiziersquartier", Story.desc010, Story.daimon010, Story.solved010, true, false, false, false, false, false, 10);
        castle[0][1][1] = new Room("Vorratskammer", Story.desc011, Story.daimon011, Story.solved011, false, true, false, false, false, false, 11);
        castle[0][1][2] = new Room("Vorraum (UG)", Story.desc012, Story.daimon012, Story.solved012, true, false, true, true, false, false, 12);

        //UG, Reihe oben
        castle[0][2][0] = new Room("Waschraum", Story.desc020, Story.daimon020, Story.solved020, false, true, true, false, false, false, 20);
        castle[0][2][1] = new Room("Schlafsaal", Story.desc021, Story.daimon021, Story.solved021, false, true, false, true, false, false, 21);
        castle[0][2][2] = new Room("Wachkantine", Story.desc022, Story.daimon022, Story.solved022, false, false, true, true, false, false, 22);

        //EG, Reihe unten
        castle[1][0][0] = new Room("", Story.desc100, Story.daimon100, Story.solved100, false, false, false, false, false, false, 100);
        castle[1][0][1] = new Room("", Story.desc101, Story.daimon101, Story.solved101, false, false, false, false, false, false, 101);
        castle[1][0][2] = new Room("", Story.desc102, Story.daimon102, Story.solved102, false, false, false, false, false, false, 102);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", Story.desc110, Story.daimon110, Story.solved110, false, false, false, false, false, false, 110);
        castle[1][1][1] = new Room("", Story.desc111, Story.daimon111, Story.solved111, false, false, false, false, false, false, 111);
        castle[1][1][2] = new Room("", Story.desc112, Story.daimon112, Story.solved112, false, false, false, false, false, false, 112);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", Story.desc120, Story.daimon120, Story.solved120, false, false, false, false, false, false, 120);
        castle[1][2][1] = new Room("", Story.desc121, Story.daimon121, Story.solved121, false, false, false, false, false, false, 121);
        castle[1][2][2] = new Room("", Story.desc122, Story.daimon122, Story.solved122, false, false, false, false, false, false, 122);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", Story.desc200, Story.daimon200, Story.solved200, false, false, false, false, false, false, 200);
        castle[2][0][1] = new Room("", Story.desc201, Story.daimon201, Story.solved201, false, false, false, false, false, false, 201);
        castle[2][0][2] = new Room("", Story.desc202, Story.daimon202, Story.solved202, false, false, false, false, false, false, 202);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", Story.desc210, Story.daimon210, Story.solved210, false, false, false, false, false, false, 210);
        castle[2][1][1] = new Room("", Story.desc211, Story.daimon211, Story.solved211, false, false, false, false, false, false, 211);
        castle[2][1][2] = new Room("", Story.desc212, Story.daimon212, Story.solved212, false, false, false, false, false, false, 212);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", Story.desc220, Story.daimon220, Story.solved220, false, false, false, false, false, false, 220);
        castle[2][2][1] = new Room("", Story.desc221, Story.daimon221, Story.solved221, false, false, false, false, false, false, 221);
        castle[2][2][2] = new Room("", Story.desc222, Story.daimon222, Story.solved222, false, false, false, false, false, false, 222);
    }

    //GEGNER


    //ENCOUNTER


    //DÄMONEN
    static DemonKind1 dem01 = new DemonKind1();
    static DemonKind2 dem02 = new DemonKind2();
    static DemonKind3 dem03 = new DemonKind3();
    static Demon[] allDem = {dem01, dem02, dem03};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));

    //BESCHWÖRUNGSFORMELN
    static ItemEvoc evoc00 = new ItemEvoc("Beschwörungsformel des Daimon", "Diesen okkulten Text hast du vor einer Weile schon entschlüsselt. \nDer wahre Name des Daimon lautet: \n\033[3mAgathos Daímōn Týchē, Spritus benefactum\033[0m \n\nDAIMON: \"Nooohh, Malle, ich bin doch schon bei dir!\"\n\n… ob es so eine gute Idee war, diesen Plagegeist zu beschwören?", "Daimon");
    static ItemEvoc evoc01 = new ItemEvoc("Beschwörungsformel des Abbaddon", "Hinweistext auf wahren Namen des Abbadon", "Abbadon");

    //ALCHEMIEZUTATEN
    static ItemIngred alch11 = new ItemIngred("Molchauge", "Molchaugen", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", 1);
    static ItemIngred alch12 = new ItemIngred("Glutorchidee", "Glutorchideen", "Rein optisch wird diese empfindliche Blüte ihrem Namen gerecht.", 1);
    static ItemIngred alch21 = new ItemIngred("Mondbeere", "Mondbeeren", "Eisblaue Früchte, die den Geist erfrischen.", 2);
    static ItemIngred alch22 = new ItemIngred("Mitternachtskraut", "Bund Mitternachtskraut", "Fahlblaue Blätter, die sich lieber dem Mondlicht als der Sonne zuwenden.", 2);

    //TRÄNKE
    static ItemPotion pot01 = new ItemPotion("Heiltrank", "Heiltränke", "Ein rot strahlender Trank, stellt 50 HP wiederher.", 50, 10);
    static ItemPotion pot02 = new ItemPotion("Manatrank", "Manatränke", "Ein blau schimmernder Trank, stellt 25 MP wiederher.", 25, 20);

    //SCHRIFTROLLEN
    static ItemScroll scr01 = new ItemScroll("Fluch", "XXX.");

    //ZAUBER
    static Spell[] allSpells = {};
    static ArrayList<Spell> freeSpells = new ArrayList<>();

    //KEY-ITEMS
    static ItemKey bag = new ItemKey("Alchemiebeutel", "Ein samtener Beutel, in dem du all deine alchemistischen Zutaten aufbewahrst: \n", 99);
    static ItemKey book = new ItemKey("Zauberbuch", "Ein ledergebundener Foliant, in dem all deine Zauber verzeichnet sind: \n", 99);
    static ItemKey bookBlackArts = new ItemKey("\"Die schwarzen Künste\"", "Das Standardwerk über Hexerei, Alchemie und Dämonologie verfasst von Meister Maleficarius Liebwerk. \n[…] Zauber werden mittels Schriftrollen erlernt und können bis zur geistigen Erschöpfung gewirkt werden. \n[…] Tränke werden mit Verabreichung verbraucht und entfalten unvergleichliche Heilkraft, selbst bei Dämonen. \n[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!", 99);
    static ItemKey key000 = new ItemKey("Zellenschlüssel", "Ein rostiger Klumpen von Schlüssel.", 0);

    //Startitems
    public static void giveStartItems(){
        Player.inv.add(bookBlackArts);
        Player.inv.add(book);
        Player.inv.add(bag);
        Player.inv.add(evoc00);
    }

}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildCastle {

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Methode: Schloss bauen
    public static void buildCastle() {

        // 000 Zelle
        //    [z][y][x]
        castle[0][0][0] = new Room("Gefängniszelle", Descriptions.desc000, Descriptions.descDoors000, Descriptions.daimon000,
                false, false, false, false, false, false, true, -1);
        castle[0][0][0].reward = BuildItems.key000;
        castle[0][0][0].unlockedText = Descriptions.unlocked000;
        castle[0][0][0].descSolved = Descriptions.descSolved000;
        castle[0][0][0].daimonSolved = Descriptions.daimonSolved000;
        castle[0][0][0].descDoorsB = Descriptions.descDoorsB000;

        // 001 Kerker
        castle[0][0][1] = new Room("Kerker", Descriptions.desc001, Descriptions.descDoors001, Descriptions.daimon001,
                false, true, false, true, false, false, true, 1);
        castle[0][0][1].loot.add(BuildItems.alche108);

        // 002 Kellergang
        castle[0][0][2] = new Room("Zugiger Kellergang", Descriptions.desc002, Descriptions.descDoors002, Descriptions.daimon002,
                true, false, false, true, false, false, false, 2);
        castle[0][0][2].loot.add(BuildItems.rock);
        castle[0][0][2].descB = Descriptions.descB002;

        // 010 Offiziersstube
        castle[0][1][0] = new Room("Offiziersquartier", Descriptions.desc010, Descriptions.descDoors010, Descriptions.daimon010,
                true, false, false, false, false, false, false, 10);
        castle[0][1][0].encounter = BuildEncounters.enc010;
        castle[0][1][0].loot.add(BuildItems.alche501);
        castle[0][1][0].loot.add(BuildItems.scrollDoom);

        // 011 Vorratskammer
        castle[0][1][1] = new Room("Vorratskammer", Descriptions.desc011, Descriptions.descDoors011, Descriptions.daimon011,
                false, true, false, false, false, false, false, 11);
        castle[0][1][1].loot.add(BuildItems.bag011);
        castle[0][1][1].loot.add(BuildItems.alche101);
        castle[0][1][1].loot.add(BuildItems.alche201);

        // 012 Vorraum
        castle[0][1][2] = new Room("Vorraum", Descriptions.desc012, Descriptions.descDoors012, Descriptions.daimon012,
                true, false, true, true, false, false, false, 12);
        castle[0][1][2].daimonB = Descriptions.daimonB012;
        castle[0][1][2].descB = Descriptions.descB012;

        // 020 Badestube
        castle[0][2][0] = new Room("Badestube", Descriptions.desc020, Descriptions.descDoors020, Descriptions.daimon020,
                false, true, false, false, false, false, true, 20);
        castle[0][2][0].loot.add(BuildItems.alche106);
        castle[0][2][0].descPostBattle = Descriptions.descPostBattle020;

        // 021 Schlafsaal
        castle[0][2][1] = new Room("Schlafsaal", Descriptions.desc021, Descriptions.descDoors021, Descriptions.daimon021,
                false, true, false, true, false, false, false, 21);
        castle[0][2][1].loot.add(BuildItems.alche202);

        // 022 Kantine
        castle[0][2][2] = new Room("Wachkantine", Descriptions.desc022, Descriptions.descDoors022, Descriptions.daimon022,
                false, false, true, true, false, false, false, 22);
        castle[0][2][2].encounter = BuildEncounters.enc022;
        castle[0][2][2].loot.add(BuildItems.alche102);

        //EG, Reihe unten
        castle[1][0][0] = new Room("", Descriptions.desc100, Descriptions.descDoors100, Descriptions.daimon100,
                false, false, false, false, false, false, false, 100);
        castle[1][0][1] = new Room("", Descriptions.desc101, Descriptions.descDoors101, Descriptions.daimon101,
                false, false, false, false, false, false, false, 101);
        castle[1][0][2] = new Room("", Descriptions.desc102, Descriptions.descDoors102, Descriptions.daimon102,
                false, false, false, false, false, false, false, 102);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", Descriptions.desc110, Descriptions.descDoors110, Descriptions.daimon110,
                false, false, false, false, false, false, false, 110);
        castle[1][1][1] = new Room("", Descriptions.desc111, Descriptions.descDoors111, Descriptions.daimon111,
                false, false, false, false, false, false, false, 111);
        castle[1][1][2] = new Room("", Descriptions.desc112, Descriptions.descDoors112, Descriptions.daimon112,
                false, false, false, false, false, false, false, 112);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", Descriptions.desc120, Descriptions.descDoors120, Descriptions.daimon120,
                false, false, false, false, false, false, false, 120);
        castle[1][2][1] = new Room("", Descriptions.desc121, Descriptions.descDoors121, Descriptions.daimon121,
                false, false, false, false, false, false, false, 121);
        castle[1][2][2] = new Room("", Descriptions.desc122, Descriptions.descDoors122, Descriptions.daimon122,
                false, false, false, false, false, false, false, 122);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", Descriptions.desc200, Descriptions.descDoors200, Descriptions.daimon200,
                false, false, false, false, false, false, false, 200);
        castle[2][0][1] = new Room("", Descriptions.desc201, Descriptions.descDoors201, Descriptions.daimon201,
                false, false, false, false, false, false, false, 201);
        castle[2][0][2] = new Room("", Descriptions.desc202, Descriptions.descDoors202, Descriptions.daimon202,
                false, false, false, false, false, false, false, 202);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", Descriptions.desc210, Descriptions.descDoors210, Descriptions.daimon210,
                false, false, false, false, false, false, false, 210);
        castle[2][1][1] = new Room("", Descriptions.desc211, Descriptions.descDoors211, Descriptions.daimon211,
                false, false, false, false, false, false, false, 211);
        castle[2][1][2] = new Room("", Descriptions.desc212, Descriptions.descDoors212, Descriptions.daimon212,
                false, false, false, false, false, false, false, 212);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", Descriptions.desc220, Descriptions.descDoors220, Descriptions.daimon220,
                false, false, false, false, false, false, false, 220);
        castle[2][2][1] = new Room("TESTRAUM A", Descriptions.desc221, Descriptions.descDoors221, Descriptions.daimon221,
                false, true, false, false, false, false, false, 221);
        castle[2][2][1].encounter = BuildEncounters.encStandard;
        castle[2][2][2] = new Room("TESTRAUM B", Descriptions.desc222, Descriptions.descDoors222, Descriptions.daimon222,
                false, false, false, true, false, false, false, 222);
    }

    //RAUM-OBJEKTE              name 1-4, descFull, descEmpty, tryLoot, Item loot           static RoomObject o = new RoomObject();
    //000 Zelle
    static RoomObject dummyKey = new RoomObject("Schlüssel", "Zellenschlüssel", "Schlüsselring", "", "Ein rostiger Klumpen von Schlüssel, der dem Wärter am Gürtel hängt.", "Du kommst nicht dran.", null);
    static RoomObject guard = new RoomObject("Wärter", "Wächter", "Gefängniswärter", "", "Ein leicht untersetzter Mann mit Schlüsselring, Schlagstock und schlechtem Schlafmanagement", "… nein.", null);
    static RoomObject bars = new RoomObject("Eisenstangen", "Stangen", "Gitter", "", "Rostige Gitter. In einigen Jahren zerbröseln sie wahrscheinlich von allein.", "Wohl kaum.", null);
    static RoomObject cell = new RoomObject("Nische", "Zelle", "Gefängniszelle", "", "Eine Zelle mit nichts als einer nackten Pritsche und einem stinkendem Eimer. Aber DICH nennen sie einen Unmenschen …", "Das ergibt keinen Sinn.", null);
    static RoomObject cot = new RoomObject("Pritsche", "Bett", "Liege", "", "An erholsamen Schlaf ist auf diesem Gestell nicht zu denken.", "Ich bevorzuge mein eigenes Bett in meinem Quartier.", null);
    static RoomObject bucket = new RoomObject("Eimer", "Nachttopf", "Toilette", "", "Ein Eimer, um die natürlichen Bedürfnisse aufzufangen.", "Auf gar keinen Fall.", null);

    //001 Kerker
    static RoomObject stuff = new RoomObject("Zeug", "Sachen", "Ausrüstung", "Eigentum", "", "Dein Eigentum ist nicht zu finden.", "Deine Besitztümer müssen sich woanders befinden.", null);
    static RoomObject candleClock = new RoomObject("Kerze", "Stundenkerze", "", "Eine Kerze zur Zeitmessung. Es muss später Abend sein.", "Die will ich nicht mit mir rumtragen", null);

    //002 Kellergang
    static RoomObject earth = new RoomObject("Erdreich", "Erde", "Kalte, wurzeldurchzogene, sich windende Erde. \nHalt – was sich dort windet, ist ein Wurm! Er sieht seltsam aus.", "Kalte, wurzeldurchzogene Erde.", "Weder kann, noch werde ich mich da durchwühlen wie Getier.", BuildItems.alche107);
    static RoomObject rocks = new RoomObject("Stein", "Es sind Steine.", "Es sind Steine.", "Es sind Steine.", BuildItems.rock);  //Objekt gleicher Name wie enthaltener Loot? -> Object.loot wird zuerst ausgegeben, dann object.tryLoot nur wenn kein Treffer -> sollte so gehen

    //010 Offiziersstube
    static RoomObject sword = new RoomObject("Schwert", "Schwerter", "Waffe", "Waffen", "", "Eine Reihe geschliffener Schwerter schimmert in den Halterungen.", "Stichwaffen sind etwas für Straßenräuber.", null);
    static RoomObject bat = new RoomObject("Knüppel", "Schlagstock", "Schlagstöcke", "", "Kantige Kiefernholzknüppel, geordnet in Reih und Glied.", "Schlagwaffen sind etwas für Kneipenschläger.", null);
    static RoomObject shield = new RoomObject("Schild", "Schilde", "", "Polierte Schilde mit dem Wappen des Hauses, einem doppelköpfigen Adler mit Heiligenschein.", "Mit so einer Platte am Arm lassen sich keine Zauber wirken.", null);
    static RoomObject armor = new RoomObject("Rüstung", "Rüstungen", "", "Einige Rüstungsgarnituren aus Leder, imprägniert mit dem Schweiß der Vorbesitzerinnen und Vorbesitzer.", "In so steifem Leder lassen sich kaum Zauber wirken. Und sie müffeln.", null);

    //011 Vorratskammer
    static RoomObject sausage = new RoomObject("Wurst", "Würste", "", "Haltbare Würste mit östlichen Gewürzen." ,"Danach ist mir gerade nicht.", null);
    static RoomObject ham = new RoomObject("Schinken", "", "Intensiv über heimischem Nadelholz geräuchert.", "Danach ist mir gerade nicht", null);
    static RoomObject beer = new RoomObject("Bier", "Bierfass", "", "Das dunkle Bier, für das das örtliche Kloster bekannt ist.", "Jetzt nicht.", null);
    static RoomObject wine = new RoomObject("Wein", "Weinfass", "", "Vollmundiger Wein aus südlichen Gefilden.", "Jetzt nicht.",  null);
    static RoomObject cheese = new RoomObject("Käse", "Ein Laib nussig-cremiger und sehr nahrhafter Käse.", "Ein Laib nussig-cremiger und sehr nahrhafter Käse. Eine Scheibe hast du abgeschnitten.", "Mehr ist nicht nötig.", BuildItems.cheese);
    static RoomObject bread = new RoomObject("Brot", "Ein Laib fluffiges Brot mit knuspernder Kruste.", "Ein Laib fluffiges Brot mit knuspernder Kruste. Eine Scheibe hast du abgeschnitten.", "Mehr ist nicht nötig.", BuildItems.bread);
    static RoomObject pickles = new RoomObject("Essiggurke", "Essiggurken", "Ein Fässchen Gurken, süß-sauer mit Senfkörnern und Dill eingelegt.", "Ein Fässchen Gurken, süß-sauer mit Senfkörnern und Dill eingelegt. Eine hast du dir rausgeholt.", "Mehr ist nicht nötig.", BuildItems.pickle);
    //KEINE Objekte, sollen nach Looten aus Raumnbeschreibung verschwinden: Tasche, Molchauge, Mondbeeren
    //castle[0][1][1].viewable.put("tasche", "Eindeutig deine, etliche Schriftrollen sollten da drin sein.");
    //castle[0][1][1].viewable.put("kugel", "Ist das etwa … aus deinem Labor?");
    //castle[0][1][1].viewable.put("beeren", "Vitaminreich und Zutat bestimmter Tränke.");

    //012 Vorraum
    static RoomObject gate = new RoomObject("Gitter", "Gittertür", "Treppe", "", "Die Gittertür vor der Treppe ins Erdgeschoss ist fest verschlossen. \nEinen Schlüssel könntest du von einem Aufseher erhalten.", "Fest verankert.", null);
    static RoomObject padlock = new RoomObject("Vorhängeschloss", "Schloss", "", "Verschließt die Gittertür vor der Treppe ins Erdgeschoss. \nEinen Schlüssel könntest du von einem Aufseher erhalten.", "Hängt fest.", null);

    //020 Badestube
    static RoomObject tub = new RoomObject("Badezuber", "Zuber", "Mit lauwarmem Seifenwasser angefüllt. Da schwimmt etwas … sind das Blüten?", "Mit lauwarmem Seifenwasser angefüllt.", "Viel zu schwer.", BuildItems.alche106);
    static RoomObject water = new RoomObject("Wasser", "", "Klares Wasser", "Gerade nicht.", null);
    static RoomObject hair = new RoomObject("Haar", "Haare", "Haarbüschel", "", "Der Barbier muss da gewesen sein.", "Ich mache mir Sorgen wegen der Hygiene.", null);

    //021 Schlafsaal
    //static RoomObject o = new RoomObject();

    //022 Wachkantine
    static RoomObject pot = new RoomObject("Topf", "Kessel", "", "Braun und eintönig köchelt ein Eintopf da drin schon ewig vor sich hin.", "Zu schwer. Zu schmutzig.", null);

    public static void addRoomObjects() {
        castle[0][0][0].objects.add(guard); castle[0][0][0].objects.add(dummyKey); castle[0][0][0].objects.add(bars); castle[0][0][0].objects.add(cell); castle[0][0][0].objects.add(cot); castle[0][0][0].objects.add(bucket);
        castle[0][0][1].objects.add(guard); castle[0][0][1].objects.add(candleClock); castle[0][0][1].objects.add(stuff); castle[0][0][1].objects.add(cell);
        castle[0][0][2].objects.add(earth); castle[0][0][2].objects.add(rocks);
        castle[0][1][0].objects.add(sword); castle[0][1][0].objects.add(bat); castle[0][1][0].objects.add(shield); castle[0][1][0].objects.add(armor);
        castle[0][1][1].objects.add(sausage); castle[0][1][1].objects.add(ham); castle[0][1][1].objects.add(beer); castle[0][1][1].objects.add(wine); castle[0][1][1].objects.add(bread); castle[0][1][1].objects.add(cheese); castle[0][1][1].objects.add(pickles);
        castle[0][1][2].objects.add(gate); castle[0][1][2].objects.add(padlock);
        castle[0][2][0].objects.add(tub); castle[0][2][0].objects.add(water); castle[0][2][0].objects.add(hair);
        //castle[0][2][1].objects.add();
        castle[0][2][2].objects.add(pot);

    }











}
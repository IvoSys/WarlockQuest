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
        castle[2][2][1] = new Room("TESTRAUM A", Story.desc221, Story.daimon221, Story.solved221, false, true, false, false, false, false, 221);
        castle[2][2][1].encounter = enc221TEST;
        castle[2][2][2] = new Room("TESTRAUM B", Story.desc222, Story.daimon222, Story.solved222, false, false, false, true, false, false, 222);
    }


    //BESCHWÖRUNGSFORMELN
    static ItemEvoc evoc00 = new ItemEvoc("Beschwörungsformel des Daimon", Story.riddleDem00, "Daimon");
    static ItemEvoc evoc01 = new ItemEvoc("Beschwörungsformel des NAME", Story.riddleDem01, "");
    static ItemEvoc evoc02 = new ItemEvoc("Beschwörungsformel des NAME", Story.riddleDem02, "");
    static ItemEvoc evoc03 = new ItemEvoc("Beschwörungsformel des NAME", Story.riddleDem03, "");

    //DÄMONEN
    static DemonKind1 dem01 = new DemonKind1("", Story.trueNameDem01, Story.whenSummonedDem01, Story.whenBoundDem01);
    static DemonKind2 dem02 = new DemonKind2("", Story.trueNameDem02, Story.whenSummonedDem02, Story.whenBoundDem02);
    static DemonKind3 dem03 = new DemonKind3("", Story.trueNameDem03, Story.whenSummonedDem03, Story.whenBoundDem03);
    static Demon[] allDem = {dem01, dem02, dem03};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));


    //ALCHEMIEZUTATEN
    static ItemIngred alch11 = new ItemIngred("Molchauge", "Molchaugen", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", 1);
    static ItemIngred alch12 = new ItemIngred("Glutorchidee", "Glutorchideen", "Rein optisch wird diese empfindliche Blüte ihrem Namen gerecht.", 1);
    static ItemIngred alch21 = new ItemIngred("Mondbeere", "Mondbeeren", "Eisblaue Früchte, die den Geist erfrischen.", 2);
    static ItemIngred alch22 = new ItemIngred("Mitternachtskraut", "Bund Mitternachtskraut", "Fahlblaue Blätter, die sich lieber dem Mondlicht als der Sonne zuwenden.", 2);


    //TRÄNKE
    static ItemPotion potHealth01 = new ItemPotionHealth("Heiltrank", "Ein rot strahlender Trank, stellt 60 HP wiederher.", 60, 11);
    static ItemPotion potMana01 = new ItemPotionMana("Manatrank", "Ein blau schimmernder Trank, stellt 30 MP wiederher.", 30, 22);


    //SCHRIFTROLLEN
    static ItemScroll scrollAderlass = new ItemScroll("Zauberschriftrolle \"Aderlass\"", Story.riddleBloodletting, "Aderlass");
    static ItemScroll scrollLebenslinie = new ItemScroll("Zauberschriftrolle \"Lebenslinie\"", Story.riddleLifeline, "Lebenslinie");
    static ItemScroll scrollSeelendieb = new ItemScroll("Zauberschriftrolle \"Seelendieb\"", Story.riddleSoulreaper, "Seelendieb");

    //ZAUBER
    static Spell bloodletting = new SpellBloodletting("Aderlass", Story.descBloodletting, "123", "", 10, 10, false);
    static Spell lifeline = new SpellLifeline("Lebenslinie", Story.descLifeline, "456", "Du lernst Lebenslinie.", 10, 10, false);
    static Spell soulreaper = new SpellSoulreaper("Seelendieb", Story.descSoulreaper, "789", "Du lernst Seelendieb.", 10, 10, false);

    static Spell[] allSpells = {bloodletting, lifeline, soulreaper};
    static ArrayList<Spell> freeSpells = new ArrayList<>(Arrays.asList(allSpells));

    //KEY-ITEMS
    static ItemKey bagAlche = new ItemKey("Alchemiebeutel", "Ein samtener Beutel, in dem du all deine alchemistischen Zutaten aufbewahrst: \n", 0, 0);
    static ItemKey bagPotions = new ItemKey("Trankgurt", "Ein System aus Lederriemen, mit denen du Trankfläschchen am Gürtel befestigen kannst. \n", 0, 0);
    static ItemKey bookSpells = new ItemKey("Zauberbuch", "Ein ledergebundener Foliant, in dem all deine Zauber verzeichnet sind: \n", 0, 0);
    static ItemKey bookBlackArts = new ItemKey("\"Die schwarzen Künste\"", "Das Standardwerk über Hexerei, Alchemie und Dämonologie in drei Bänden, verfasst von Meister Maleficarius Liebwerk: \n[…] Zauber werden mittels Schriftrollen erlernt und können bis zur geistigen Erschöpfung gewirkt werden. \n[…] Tränke werden mit Verabreichung verbraucht und entfalten unvergleichliche Wirkungen, selbst bei Dämonen. \n[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!", 0, 0);
    static ItemKey key000 = new ItemKey("Zellenschlüssel", "Ein rostiger Klumpen von Schlüssel.", -1, 0);
    static ItemKey key999 = new ItemKey("Goldklumpen", "Alechimistisch betrachtet wertlos.", 0, 0);

    //Startitems
    public static void giveStartItems() {
        Player.inv.add(bookBlackArts);
        Player.inv.add(bookSpells);
        Player.inv.add(bagAlche);
        Player.inv.add(bagPotions);
        Player.inv.add(evoc00);
    }



    //GEGNER
    static Enemy soldier01 = new EnemySoldier();
    static Enemy soldier02 = new EnemySoldier();
    static Enemy soldier03 = new EnemySoldier();

    // ENCOUNTER
    static Encounter enc221TEST = new Encounter(soldier01, soldier02, soldier03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", key999);






}
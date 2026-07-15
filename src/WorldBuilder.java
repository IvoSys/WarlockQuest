import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldBuilder {

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Methode: Schloss bauen
    public static void buildCastle() {

        //UG, Reihe unten
        castle[0][0][0] = new Room("Gefängniszelle", Story.desc000, Story.daimon000, Story.solved000, false, false, false, false, false, false, -1);
        castle[0][0][0].reward = key000; castle[0][0][0].notLoot.put("schlüssel", "Ich komme nicht dran"); castle[0][0][0].descSolved = Story.descSolved000; castle[0][0][0].daimonSolved = Story.daimonSolved000;
        //.notLoot.put(): Key darf nur Kleinbuchstaben verwenden, sonst scheitert Abgleich!
        castle[0][0][1] = new Room("Kerker", Story.desc001, Story.daimon001, Story.solved001, false, true, false, true, false, false, 1);
        castle[0][0][1].notLoot.put("zeug", "Das ist leider nicht hier");
        castle[0][0][2] = new Room("Zugiger Kellergang", Story.desc002, Story.daimon002, Story.solved002, true, false, false, true, false, false, 2);
        //    [z][y][x]

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("Offiziersquartier", Story.desc010, Story.daimon010, Story.solved010, true, false, false, false, false, false, 10);
        castle[0][1][1] = new Room("Vorratskammer", Story.desc011, Story.daimon011, Story.solved011, false, true, false, false, false, false, 11);
        castle[0][1][2] = new Room("Vorraum (UG)", Story.desc012, Story.daimon012, Story.solved012, true, false, true, true, false, false, 12);

        //UG, Reihe oben
        castle[0][2][0] = new Room("Waschraum", Story.desc020, Story.daimon020, Story.solved020, false, true, true, false, false, false, 20);
        castle[0][2][1] = new Room("Schlafsaal", Story.desc021, Story.daimon021, Story.solved021, false, true, false, true, false, false, 21);
        castle[0][2][2] = new Room("Wachkantine", Story.desc022, Story.daimon022, Story.solved022, false, false, true, true, false, false, 22);
        castle[0][2][2].encounter = enc022;

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
    static Evocation evoc00 = new Evocation("Beschwörungsformel des vertrauten Geistes", "Schriftrolle", Story.riddleDem00, "Daimon");
    static Evocation evoc01 = new Evocation("Beschwörungsformel des gehörnten Königs", "Schriftrolle", Story.riddleDem01, "Minotauros");
    static Evocation evoc02 = new Evocation("Beschwörungsformel der entflammten Dame", "Schriftrolle", Story.riddleDem02, "Efreet");
    static Evocation evoc03 = new Evocation("Beschwörungsformel des Abgründigen", "Schriftrolle", Story.riddleDem03, "Abaddon");

    //DÄMONEN
    static Minotauros dem01 = new Minotauros();
    static Efreet dem02 = new Efreet();
    static Abaddon dem03 = new Abaddon();
    static Demon[] allDem = {dem01, dem02, dem03};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));


    //ALCHEMIEZUTATEN
    //weiß, Katalysator static ItemIngred alch01 = new ItemIngred("", "", "", "", 10);
    //weiß, Katalysator static ItemIngred alch02 = new ItemIngred("", "", "", "", 10);
    static Ingredience alch11 = new Ingredience("Molchauge", "", "Molchaugen", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", 1);
    static Ingredience alch12 = new Ingredience("Glutorchidee", "", "Glutorchideen", "Rein optisch wird diese empfindliche Blüte ihrem Namen gerecht.", 1);
    static Ingredience alch21 = new Ingredience("Mondbeere", "", "Mondbeeren", "Eisblaue Früchte, die den Geist erfrischen.", 2);
    static Ingredience alch22 = new Ingredience("Mitternachtskraut", "", "Bund Mitternachtskraut", "Fahlblaue Blätter, die sich lieber dem Mondlicht als der Sonne zuwenden.", 2);
    //gelb static ItemIngred alch31 = new ItemIngred("", "", "", "", 3);
    //gelb static ItemIngred alch32 = new ItemIngred("", "", "", "", 3);
    //grün static ItemIngred alch41 = new ItemIngred("", "", "", "", 4);
    //grün static ItemIngred alch42 = new ItemIngred("", "", "", "", 4);
    //schwarz, Levelup static ItemIngred alch51 = new ItemIngred("", "", "", "", 5);
    //schwarz, Levelup static ItemIngred alch52 = new ItemIngred("", "", "", "", 5);


    //TRÄNKE
    static Item potHealth1 = new Healthpotion("Heiltrank", "", "Ein rot strahlender Trank, stellt 60 HP wiederher.", 60, 11);
    static Item potHealth2 = new Healthpotion("Starker Heiltrank", "", "Ein rot strahlender Trank, der sich lauwarm anfühlt. Stellt 120 HP wiederher.", 120, 110);
    static Item potHealth3 = new Healthpotion("Mächtiger Heiltrank", "", "Ein rot strahlender Trank, in dem goldene Partikel umherwirbeln. stellt 250 HP wiederher.", 250, 1100);
    static Item potMana1 = new Manapotion("Manatrank", "", "Ein blau schimmernder Trank, stellt 30 MP wiederher.", 30, 22);
    static Item potMana2 = new Manapotion("Starker Manatrank", "", "Ein blau schimmernder Trank, der sich angenehm kühl anfühlt. Stellt 60 MP wiederher.", 60, 220);
    static Item potMana3 = new Manapotion("Mächtiger Manatrank", "", "Ein blau schimmernder Trank, in dem silberne Partikel umherwirbeln. Stellt 125 MP wiederher.", 125, 2200);
    //static Item potYellow1 = new ItemPotion("Gelber Trank", "", "", 30, 33);
    //static Item potGreen1 = new ItemPotion("Grüner Trank", "", "", 30, 44);
    static Item potLevelUp = new Levelpotion("Trank des Abgrunds", "", "Ein tiefschwarzer Trank. \nDas Elixir ist so perfekt schwarz, dass die kugelrunde Flasche unnatürlich formlos erscheint, wie ein blinder Fleck in deinem Sichtfeld. \nSie nimmt den Blick gefangen, und nach einer Weile verfestigt sich das Gefühl, dass irgendetwas zurückstarrt. ", 55);


    //SCHRIFTROLLEN
    static Spellscroll scrollBloodletting = new Spellscroll("Zauberschriftrolle \"Aderlass\"", "", Story.riddleBloodletting, "Aderlass");
    static Spellscroll scrollDoom = new Spellscroll("Zauberschriftrolle \"Untergang\"", "", Story.riddleDoom, "Untergang");
    static Spellscroll scrollIronMaiden = new Spellscroll("Zauberschriftrolle \"Eiserne Jungfrau\"", "", Story.riddleIronMaiden, "Eiserne Jungfrau");
    static Spellscroll scrollLifeline = new Spellscroll("Zauberschriftrolle \"Lebenslinie\"", "", Story.riddleLifeline, "Lebenslinie");
    static Spellscroll scrollSoulreaper = new Spellscroll("Zauberschriftrolle \"Seelendieb\"", "", Story.riddleSoulreaper, "Seelendieb");
    static Spellscroll scrollViciousSeed = new Spellscroll("Zauberschriftrolle \"Üble Saat\"", "", Story.riddleViciousSeed, "Üble Saat");

    //ZAUBER
    static Spell bloodletting = new Bloodletting();
    static Spell doom = new Doom();
    static Spell ironMaiden = new IronMaiden();
    static Spell lifeline = new Lifeline();
    static Spell soulreaper = new Soulreaper();
    static Spell viciousSeed = new ViciousSeed();

    static Spell[] allSpells = {bloodletting, doom, lifeline, soulreaper, viciousSeed};
    static ArrayList<Spell> freeSpells = new ArrayList<>(Arrays.asList(allSpells));

    //KEY-ITEMS
    static Key bagAlche = new Key("Alchemiebeutel", "", "Ein samtener Beutel, in dem du all deine alchemistischen Zutaten aufbewahrst: \n", 0, 0);
    static Key bagPotions = new Key("Trankgurt", "", "Ein System aus Lederriemen, mit denen du Trankfläschchen am Gürtel befestigen kannst. \n", 0, 0);
    static Key bookSpells = new Key("Zauberbuch", "", "Ein ledergebundener Foliant, in dem all deine Zauber verzeichnet sind: \n", 0, 0);
    static Key bookBlackArts = new Key("\"Die schwarzen Künste\"", "", Story.bookBlackArtsDesc, 0, 0);
    static Key key000 = new Key("Zellenschlüssel", "Schlüssel", "Ein rostiger Klumpen von Schlüssel.", -1, 0);
    static Key key999 = new Key("Goldklumpen", "", "Alechimistisch betrachtet wertlos.", 0, 0);

    //Startitems
    public static void giveStartItems() {
        Player.inv.add(bookBlackArts);
        Player.inv.add(bookSpells);
        Player.inv.add(bagAlche);
        Player.inv.add(bagPotions);
        Player.inv.add(evoc00);
    }



    //GEGNER
    static Enemy soldier01 = new Guard("Wache Karl", "ihrem Knüppel");
    static Enemy soldier02 = new Watchdog("Hasso");
    static Enemy soldier03 = new Guard("Wache Max", "ihrem Knüppel");

    // ENCOUNTER
    static Encounter enc221TEST = new Encounter("Testsoldaten", soldier01, soldier02, soldier03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", key999);
    static Encounter enc022 = new Encounter("Angeheiterte Wachen", soldier01, soldier02, soldier03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", null);






}
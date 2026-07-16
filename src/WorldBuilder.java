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
    static Ingredience alch11 = new Ingredience("Molchauge", "Kugel", "Molchaugen", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", 1);
    static Ingredience alch12 = new Ingredience("Glutorchidee", "Blüte", "Glutorchideen", "Rein optisch wird diese empfindliche Blüte ihrem Namen gerecht.", 1);
    static Ingredience alch21 = new Ingredience("Mondbeere", "Beere", "Mondbeeren", "Eisblaue Früchte, die den Geist erfrischen.", 2);
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
    //region
    static Enemy guard01 = new Guard("Wache Karl", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard02 = new Guard("Wache Helga", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard03 = new Guard("Wache Wenzel", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard04 = new Guard("Wache Bertha", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard05 = new Guard("Wache Jakob", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard06 = new Guard("Wache Anna", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard07 = new Guard("Wache XY", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard08 = new Guard("Wache XX", "ihrem Knüppel", 60, 5, 5, 0);
    static Enemy guard09 = new Guard("Wache XY", "ihrem Knüppel", 60, 5, 5, 0);

    static Enemy dog01 = new Watchdog("Hund Urs",40, 5, 10);
    static Enemy dog02 = new Watchdog("Hündin Luna",40, 5, 10);
    static Enemy dog03 = new Watchdog("Hund Rumo",40, 5, 10);
    static Enemy dog04 = new Watchdog("Hündin Lupa",40, 5, 10);
    static Enemy dog05 = new Watchdog("Hund Rolv",40, 5, 10);
    static Enemy dog06 = new Watchdog("Hündin Rala",40, 5, 10);
    static Enemy dog07 = new Watchdog("Hund Rufus",40, 5, 10);
    static Enemy dog08 = new Watchdog("Hündin Flauschi",40, 5, 10);
    static Enemy dog09 = new Watchdog("Hund Brutus",40, 5, 10);

    static Enemy soldier01 = new Soldier("Soldat Otto", "seinem Schwert", 80, 10, 8, 0);
    static Enemy soldier02 = new Soldier("Soldatin Barbara", "ihrem Schwert", 80, 10, 8, 0);
    static Enemy soldier03 = new Soldier("Soldat Hermann", "seinem Schwert", 80, 10, 8, 0);
    static Enemy soldier04 = new Soldier("Soldatin X", "ihrem Schwert", 80, 10, 8, 0);
    static Enemy soldier05 = new Soldier("Soldat Hannes", "seinem Schwert", 80, 10, 8, 0);
    static Enemy soldier06 = new Soldier("Soldatin X", "ihrem Schwert", 80, 10, 8, 0);
    static Enemy soldier07 = new Soldier("Soldat X", "seinem Schwert", 80, 10, 8, 0);
    static Enemy soldier08 = new Soldier("Soldatin X", "ihrem Schwert", 80, 10, 8, 0);
    static Enemy soldier09 = new Soldier("Soldat X", "seinem Schwert", 80, 10, 8, 0);

    static Enemy archer01 = new Archer("Schützin X", "ihrem Bogen", 60, 10, 8, 0);
    static Enemy archer02 = new Archer("Schütze X", "seinem Bogen", 60, 10, 8, 0);
    static Enemy archer03 = new Archer("Schützin X", "ihrem Bogen", 60, 10, 8, 0);
    static Enemy archer04 = new Archer("Schütze X", "seinem Bogen", 60, 10, 8, 0);
    static Enemy archer05 = new Archer("Schützin X", "ihrem Bogen", 60, 10, 8, 0);
    static Enemy archer06 = new Archer("Schütze X", "seinem Bogen", 60, 10, 8, 0);
    static Enemy archer07 = new Archer("Schützin X", "ihrem Bogen", 60, 10, 8, 0);
    static Enemy archer08 = new Archer("Schütze X", "seinem Bogen", 60, 10, 8, 0);
    static Enemy archer09 = new Archer("Schützin X", "ihrem Bogen", 60, 10, 8, 0);

    static Enemy apprentice01 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice02 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice03 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice04 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice05 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice06 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice07 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice08 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice09 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);

    static Enemy novice01 = new Novice("Schwester X", "ihrem Streitkolben", 60, 10, 8, 0);
    static Enemy novice02 = new Novice("Bruder X", "seinem Streitkolben", 60, 10, 8, 0);
    static Enemy novice03 = new Novice("Schwester X", "ihrem Streitkolben", 60, 10, 8, 0);
    static Enemy novice04 = new Novice("Bruder X", "seinem Streitkolben", 60, 10, 8, 0);
    static Enemy novice05 = new Novice("Schwester X", "ihrem Streitkolben", 60, 10, 8, 0);
    static Enemy novice06 = new Novice("Bruder X", "seinem Streitkolben", 60, 10, 8, 0);
    static Enemy novice07 = new Novice("Schwester X", "ihrem Streitkolben", 60, 10, 8, 0);
    static Enemy novice08 = new Novice("Bruder X", "seinem Streitkolben", 60, 10, 8, 0);
    static Enemy novice09 = new Novice("Schwester X", "ihrem Streitkolben", 60, 10, 8, 0);

    static Enemy knight01 = new Knight("Sir X", "seinem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight02 = new Knight("Dame Irina", "ihrem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight03 = new Knight("Sir X", "seinem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight04 = new Knight("Dame Beatrix", "ihrem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight05 = new Knight("Sir X", "seinem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight06 = new Knight("Dame X", "ihrem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight07 = new Knight("Sir X", "seinem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight08 = new Knight("Dame X", "ihrem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight09 = new Knight("Sir X", "seinem Zweihänder", 100, 10, 8, 20, 0);

    static Enemy ranger01 = new Ranger("Assassine XX", "ihrem Langbogen", 80, 10, 8, 0);
    static Enemy ranger02 = new Ranger("Assassine XY", "seinem Langbogen", 80, 10, 8, 0);
    static Enemy ranger03 = new Ranger("Assassine XX", "ihrem Langbogen", 80, 10, 8, 0);
    static Enemy ranger04 = new Ranger("Assassine XY", "seinem Langbogen", 80, 10, 8, 0);
    static Enemy ranger05 = new Ranger("Assassine XX", "ihrem Langbogen", 80, 10, 8, 0);
    static Enemy ranger06 = new Ranger("Assassine XY", "seinem Langbogen", 80, 10, 8, 0);
    static Enemy ranger07 = new Ranger("Assassine XX", "ihrem Langbogen", 80, 10, 8, 0);
    static Enemy ranger08 = new Ranger("Assassine XY", "seinem Langbogen", 80, 10, 8, 0);
    static Enemy ranger09 = new Ranger("Assassine XX", "ihrem Langbogen", 80, 10, 8, 0);

    static Enemy mage02 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage03 = new Mage("Professorin Elvira", "dem Zauber \"Feuerpfeil\"", 80, 10, 8, 0);
    static Enemy mage04 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage05 = new Mage("Professorin Minerva", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage06 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage07 = new Mage("Professorin X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage08 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage09 = new Mage("Professorin X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);

    static Enemy cleric01 = new Cleric("Mutter Gwendolyn", "ihrem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric02 = new Cleric("Vater X", "seinem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric03 = new Cleric("Mutter X", "ihrem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric04 = new Cleric("Vater X", "seinem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric05 = new Cleric("Mutter X", "ihrem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric06 = new Cleric("Vater X", "seinem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric07 = new Cleric("Mutter X", "ihrem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric08 = new Cleric("Vater X", "seinem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric09 = new Cleric("Mutter X", "ihrem Morgenstern", 80, 10, 8, 0);

    static Enemy boss01 = new Boss01("X X", "X", 80, 10, 8, 0);
    static Enemy boss02 = new Boss02("X X", "X", 80, 10, 8, 0);
    static Enemy boss03 = new Boss03("X X", "X", 80, 10, 8, 0);
    //endregion


    // ENCOUNTER
    static Encounter enc221TEST = new Encounter("Testsoldaten", guard01, guard02, guard03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", key999);
    static Encounter enc022 = new Encounter("Angeheiterte Wachen", guard01, guard02, guard03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", null);






}
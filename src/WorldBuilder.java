import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldBuilder {

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Methode: Schloss bauen
    public static void buildCastle() {

        //UG, Reihe unten
        castle[0][0][0] = new Room("Gefängniszelle", Descriptions.desc000, Descriptions.daimon000, false, false, false, false, false, false, true, -1);
        castle[0][0][0].reward = key000; castle[0][0][0].notLoot.put("schlüssel", "Ich komme nicht dran"); castle[0][0][0].descSolved = Descriptions.descSolved000; castle[0][0][0].daimonSolved = Descriptions.daimonSolved000;
        //.notLoot.put(): Key darf nur Kleinbuchstaben verwenden, sonst scheitert Abgleich!
        castle[0][0][1] = new Room("Kerker", Descriptions.desc001, Descriptions.daimon001, false, true, false, true, false, false, true, 1);
        castle[0][0][1].notLoot.put("zeug", "Das ist leider nicht hier.");
        castle[0][0][2] = new Room("Zugiger Kellergang", Descriptions.desc002, Descriptions.daimon002, true, false, false, true, false, false, false, 2);
        castle[0][0][2].loot.add(WorldBuilder.rock);
        //    [z][y][x]

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("Offiziersquartier", Descriptions.desc010, Descriptions.daimon010, true, false, false, false, false, false, false, 10);
        castle[0][1][0].encounter = enc010; castle[0][1][0].loot.add(alche51); castle[0][1][0].loot.add(scrollViciousSeed);
        castle[0][1][1] = new Room("Vorratskammer", Descriptions.desc011, Descriptions.daimon011, false, true, false, false, false, false, false, 11);
        castle[0][1][1].loot.add(bag011); castle[0][1][1].loot.add(alche11); castle[0][1][1].loot.add(alche21); castle[0][1][1].loot.add(bread); castle[0][1][1].loot.add(cheese); castle[0][1][1].loot.add(pickle);
        castle[0][1][1].notLoot.put("wurst", "Danach ist mir gerade nicht."); castle[0][1][1].notLoot.put("würste", "Danach ist mir gerade nicht."); castle[0][1][1].notLoot.put("schinken", "Danach ist mir gerade nicht."); castle[0][1][1].notLoot.put("bier", "Jetzt nicht.");castle[0][1][1].notLoot.put("wein", "Jetzt nicht.");
        castle[0][1][2] = new Room("Vorraum (UG)", Descriptions.desc012, Descriptions.daimon012, true, false, true, true, false, false, false, 12);
        castle[0][1][2].daimonB = Descriptions.daimonB012;

        //UG, Reihe oben
        castle[0][2][0] = new Room("Waschraum", Descriptions.desc020, Descriptions.daimon020, false, true, false, false, false, false, true, 20);
        castle[0][2][1] = new Room("Schlafsaal", Descriptions.desc021, Descriptions.daimon021, false, true, false, true, false, false, false, 21);
        castle[0][2][2] = new Room("Wachkantine", Descriptions.desc022, Descriptions.daimon022, false, false, true, true, false, false, false, 22);
        castle[0][2][2].encounter = enc022; castle[0][2][2].loot.add(alche12); castle[0][2][2].notLoot.put("topf", "Zu schwer. Zu schmutzig.");

        //EG, Reihe unten
        castle[1][0][0] = new Room("", Descriptions.desc100, Descriptions.daimon100, false, false, false, false, false, false, false, 100);
        castle[1][0][1] = new Room("", Descriptions.desc101, Descriptions.daimon101, false, false, false, false, false, false, false, 101);
        castle[1][0][2] = new Room("", Descriptions.desc102, Descriptions.daimon102, false, false, false, false, false, false, false, 102);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", Descriptions.desc110, Descriptions.daimon110, false, false, false, false, false, false, false, 110);
        castle[1][1][1] = new Room("", Descriptions.desc111, Descriptions.daimon111, false, false, false, false, false, false, false, 111);
        castle[1][1][2] = new Room("", Descriptions.desc112, Descriptions.daimon112, false, false, false, false, false, false, false, 112);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", Descriptions.desc120, Descriptions.daimon120, false, false, false, false, false, false, false, 120);
        castle[1][2][1] = new Room("", Descriptions.desc121, Descriptions.daimon121, false, false, false, false, false, false, false, 121);
        castle[1][2][2] = new Room("", Descriptions.desc122, Descriptions.daimon122, false, false, false, false, false, false, false, 122);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", Descriptions.desc200, Descriptions.daimon200, false, false, false, false, false, false, false, 200);
        castle[2][0][1] = new Room("", Descriptions.desc201, Descriptions.daimon201, false, false, false, false, false, false, false, 201);
        castle[2][0][2] = new Room("", Descriptions.desc202, Descriptions.daimon202, false, false, false, false, false, false, false, 202);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", Descriptions.desc210, Descriptions.daimon210, false, false, false, false, false, false, false, 210);
        castle[2][1][1] = new Room("", Descriptions.desc211, Descriptions.daimon211, false, false, false, false, false, false, false, 211);
        castle[2][1][2] = new Room("", Descriptions.desc212, Descriptions.daimon212, false, false, false, false, false, false, false, 212);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", Descriptions.desc220, Descriptions.daimon220, false, false, false, false, false, false, false, 220);
        castle[2][2][1] = new Room("TESTRAUM A", Descriptions.desc221, Descriptions.daimon221, false, true, false, false, false, false, false, 221);
        castle[2][2][1].encounter = encStandard;
        castle[2][2][2] = new Room("TESTRAUM B", Descriptions.desc222, Descriptions.daimon222, false, false, false, true, false, false, false, 222);
    }


    //BESCHWÖRUNGSFORMELN
    static Evocation evoc00 = new Evocation("Beschwörungsformel des vertrauten Geistes", "Schriftrolle", Descriptions.riddleDem00, "Daimon");
    static Evocation evoc01 = new Evocation("Beschwörungsformel des gehörnten Königs", "Schriftrolle", Descriptions.riddleDem01, "Minotauros");
    static Evocation evoc02 = new Evocation("Beschwörungsformel der entflammten Dame", "Schriftrolle", Descriptions.riddleDem02, "Efreet");
    static Evocation evoc03 = new Evocation("Beschwörungsformel des Abgründigen", "Schriftrolle", Descriptions.riddleDem03, "Abaddon");

    //DÄMONEN
    static Minotauros dem01 = new Minotauros();
    static Efreet dem02 = new Efreet();
    static Abaddon dem03 = new Abaddon();
    static Demon[] allDem = {dem01, dem02, dem03};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));


    //ALCHEMIEZUTATEN
    //Weiß, Katalysator
    //static Ingredience alche01 = new Ingredience("", "", "", "", 10);
    //static Ingredience alche02 = new Ingredience("", "", "", "", 10);
    //Rot, ergibt Heiltränke
    static Ingredience alche11 = new Ingredience("Molchauge", "Kugel", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", 1);
    static Ingredience alche12 = new Ingredience("Glutorchidee", "Blümchen", "Rein dem Aussehen nach wird diese empfindliche Blüte ihrem Namen gerecht.", 1);
    static Ingredience alche13 = new Ingredience("Fliegenpilz", "Pilz", "Dieses gefährliche Gewächs zu entgiften und gar zu einem Trank zu verbrauen, sollte einem Meisteralchemisten vorbehalten sein. Wie dir.", 1);
    static Ingredience alche14 = new Ingredience("Marienkäferflügel", "", "Zart durchscheinende Häutchen, noch verbunden mit einer gepunkteten Chitinschicht.", 1);
    static Ingredience alche15 = new Ingredience("Sperlingszunge", "", "", 1);
    static Ingredience alche16 = new Ingredience("", "", "", 1);
    //Blau, ergibt Manatränke
    static Ingredience alche21 = new Ingredience("Mondbeeren", "Beeren", "Eisblaue Früchte, die den Geist erfrischen.", 2);
    static Ingredience alche22 = new Ingredience("Mitternachtskraut", "", "Fahlblaue Blätter, die sich lieber dem Mondlicht als der Sonne zuwenden.", 2);
    //Gelb, frei (Wiederbelebung?)
    //gelb static Ingredience alche31 = new Ingredience("", "", "", "", 3);
    //gelb static Ingredience alche32 = new Ingredience("", "", "", "", 3);
    //Grün, frei
    //grün static Ingredience alche41 = new Ingredience("", "", "", "", 4);
    //grün static Ingredience alche42 = new Ingredience("", "", "", "", 4);
    //Schwarz, ergibt Level-up-Tränke
    static Ingredience alche51 = new Ingredience("Maluswurzel", "", "Das gewundene dunkle Wurzelwerk der Crassula ovata.", 5);
    static Ingredience alche52 = new Ingredience("Tollkirsche", "", "In falschen Händen tödlich.", 5);


    //TRÄNKE
    static Item potHealth1 = new Healthpotion("Heiltrank", "", "Ein rot strahlender Trank, stellt 80 HP wiederher.", 80, 11);
    static Item potHealth2 = new Healthpotion("Starker Heiltrank", "", "Ein rot strahlender Trank, der sich lauwarm anfühlt. Stellt 120 HP wiederher.", 120, 110);
    static Item potHealth3 = new Healthpotion("Mächtiger Heiltrank", "", "Ein rot strahlender Trank, in dem goldene Partikel umherwirbeln. stellt 250 HP wiederher.", 250, 1100);
    static Item potMana1 = new Manapotion("Manatrank", "", "Ein blau schimmernder Trank, stellt 40 MP wiederher.", 40, 22);
    static Item potMana2 = new Manapotion("Starker Manatrank", "", "Ein blau schimmernder Trank, der sich angenehm kühl anfühlt. Stellt 60 MP wiederher.", 60, 220);
    static Item potMana3 = new Manapotion("Mächtiger Manatrank", "", "Ein blau schimmernder Trank, in dem silberne Partikel umherwirbeln. Stellt 125 MP wiederher.", 125, 2200);
    //static Item potYellow1 = new ItemPotion("Gelber Trank", "", "", 30, 33);
    //static Item potGreen1 = new ItemPotion("Grüner Trank", "", "", 30, 44);
    static Item potLevelUp = new Levelpotion("Trank des Abgrunds", "", "Ein tiefschwarzer Trank. \nDas Elixir ist so perfekt schwarz, dass die kugelrunde Flasche unnatürlich formlos erscheint, wie ein blinder Fleck in deinem Sichtfeld. \nSie nimmt den Blick gefangen, und nach einer Weile verfestigt sich das Gefühl, dass irgendetwas zurückstarrt. ", 55);


    //SCHRIFTROLLEN
    static Spellscroll scrollBloodletting = new Spellscroll("Zauberschriftrolle zu den Grundlagen der Blutmagie", "", Descriptions.riddleBloodletting, "Aderlass");
    static Spellscroll scrollDoom = new Spellscroll("Zauberschriftrolle zum Zahn der Zeit", "", Descriptions.riddleDoom, "Untergang");
    static Spellscroll scrollIronMaiden = new Spellscroll("Zauberschriftrolle zur ausgleichenden Gerechtigkeit", "", Descriptions.riddleIronMaiden, "Eiserne Jungfrau");
    static Spellscroll scrollLifeline = new Spellscroll("Zauberschriftrolle zum Lebenstransfer", "", Descriptions.riddleLifeline, "Lebenslinie");
    static Spellscroll scrollSoulreaper = new Spellscroll("Zauberschriftrolle zum Ernten von Seelen", "", Descriptions.riddleSoulreaper, "Seelendieb");
    static Spellscroll scrollViciousSeed = new Spellscroll("Zauberschriftrolle zum breit gestreuten Übel", "", Descriptions.riddleViciousSeed, "Üble Saat");

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
    static Key bookBlackArts = new Key("\"Die schwarzen Künste\"", "", Descriptions.bookBlackArtsDesc, 0, 0);
    static Key key000 = new Key("Zellenschlüssel", "Schlüssel", "Ein rostiger Klumpen von Schlüssel.", -1, 0);
    static Key key012 = new Key("Kerkerschlüssel", "Schlüssel", "Der Schlüssel zum Kerker … und AUS dem Kerker.", 12, 0);
    static Key bag011 = new Key("Maleficarius' Tasche", "Tasche", "Eine robuste Stofftasche, in der Maleficarius seine Schriftrollenbehälter transportiert.", 0, -1);
    static Key bread = new Key("Scheibe kerniges Bauernbrot", "Brot", "Fluffiges Brot mit knuspernder Kruste. Malzig im Geschmack und reich an Ballaststoffen.", 0, 12);
    static Key cheese = new Key("Scheibe nussiger Bergkäse", "Käse", "Nussig-cremiger Käse. Vollmundig und sehr nahrhaft.", 0, 12);
    static Key cheeseBread = new Key("Käsebrot", "", "Bestechend einfach und beinahe perfekt.", 0, 13);
    static Key pickle = new Key("Essiggurke", "Essiggurken", "Süß-sauer eingelegt mit Senfkörnern und Dill. Rundet geschmacklich so einiges gelungen ab.", 0, 13);
    static Key sandwich = new Key("Käsebrot mit Essiggurke", "Käsebrot mit Essiggurke", "Ein herzhaft-würziges Käsebrot mit Essiggurke. Eine ideale Zwischenmahlzeit.", 0, 0);    // puzzleID 0, da Item in Dialog verwendet
    static Key rock = new Key("Stein", "Steine", "Es ist ein Stein.", 20, 0);
    static Key gold999 = new Key("Goldklumpen", "", "Alechimistisch betrachtet wertlos.", 0, 0);

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
    static Enemy guard01 = new Guard("Wache Alfred", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard02 = new Guard("Wache Helga", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard03 = new Guard("Wache Wenzel", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard04 = new Guard("Wache Bertha", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard05 = new Guard("Wache Jakob", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard06 = new Guard("Wache Anna", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard07 = new Guard("Wache XY", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard08 = new Guard("Wache XX", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard09 = new Guard("Wache XY", "dem Knüppel", 50, 5, 5, 0);

    static Enemy dog01 = new Watchdog("Hund Urs",35, 5, 10);
    static Enemy dog02 = new Watchdog("Hündin Luna",35, 5, 10);
    static Enemy dog03 = new Watchdog("Hund Rumo",35, 5, 10);
    static Enemy dog04 = new Watchdog("Hündin Lupa",35, 5, 10);
    static Enemy dog05 = new Watchdog("Hund Rolv",35, 5, 10);
    static Enemy dog06 = new Watchdog("Hündin Rala",35, 5, 10);
    static Enemy dog07 = new Watchdog("Hund Rufus",35, 5, 10);
    static Enemy dog08 = new Watchdog("Hündin Flauschi",35, 5, 10);
    static Enemy dog09 = new Watchdog("Hund Brutus",35, 5, 10);

    static Enemy soldier01 = new Soldier("Aufseher Otto", "dem Schwert", 60, 10, 8, 1);
    static Enemy soldier02 = new Soldier("Soldatin Barbara", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier03 = new Soldier("Soldat Hermann", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier04 = new Soldier("Soldatin X", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier05 = new Soldier("Soldat Hannes", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier06 = new Soldier("Soldatin X", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier07 = new Soldier("Soldat X", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier08 = new Soldier("Soldatin X", "dem Schwert", 60, 10, 8, 0);
    static Enemy soldier09 = new Soldier("Soldat X", "dem Schwert", 60, 10, 8, 0);

    static Enemy archer01 = new Archer("Schützin X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer02 = new Archer("Schütze X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer03 = new Archer("Schützin X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer04 = new Archer("Schütze X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer05 = new Archer("Schützin X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer06 = new Archer("Schütze X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer07 = new Archer("Schützin X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer08 = new Archer("Schütze X", "dem Bogen", 50, 10, 12, 0);
    static Enemy archer09 = new Archer("Schützin X", "dem Bogen", 50, 10, 12, 0);

    static Enemy apprentice01 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice02 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice03 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice04 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice05 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice06 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice07 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice08 = new Apprentice("Studentin X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);
    static Enemy apprentice09 = new Apprentice("Student X", "dem Zauber \"Froststrahl\"", 30, 10, 8, 0);

    static Enemy novice01 = new Novice("Schwester X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice02 = new Novice("Bruder X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice03 = new Novice("Schwester X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice04 = new Novice("Bruder X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice05 = new Novice("Schwester X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice06 = new Novice("Bruder X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice07 = new Novice("Schwester X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice08 = new Novice("Bruder X", "dem Streitkolben", 55, 10, 8, 0);
    static Enemy novice09 = new Novice("Schwester X", "dem Streitkolben", 55, 10, 8, 0);

    static Enemy knight01 = new Knight("Dame Beatrix", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight02 = new Knight("Lady Irina", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight03 = new Knight("Sir X", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight04 = new Knight("Dame Beatrix", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight05 = new Knight("Sir X", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight06 = new Knight("Dame X", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight07 = new Knight("Sir X", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight08 = new Knight("Dame X", "dem Zweihänder", 100, 10, 8, 20, 0);
    static Enemy knight09 = new Knight("Sir X", "dem Zweihänder", 100, 10, 8, 20, 0);

    static Enemy ranger01 = new Ranger("Assassine XX", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger02 = new Ranger("Assassine XY", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger03 = new Ranger("Assassine XX", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger04 = new Ranger("Assassine XY", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger05 = new Ranger("Assassine XX", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger06 = new Ranger("Assassine XY", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger07 = new Ranger("Assassine XX", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger08 = new Ranger("Assassine XY", "dem Langbogen", 80, 10, 8, 0);
    static Enemy ranger09 = new Ranger("Assassine XX", "dem Langbogen", 80, 10, 8, 0);

    static Enemy mage02 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage03 = new Mage("Professorin Elvira", "dem Zauber \"Feuerpfeil\"", 80, 10, 8, 0);
    static Enemy mage04 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage05 = new Mage("Professorin Minerva", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage06 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage07 = new Mage("Professorin X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage08 = new Mage("Professor X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);
    static Enemy mage09 = new Mage("Professorin X", "dem Zauber \"Feuerpfeil\"", 40, 10, 8, 0);

    static Enemy cleric01 = new Cleric("Mutter Gwendolyn", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric02 = new Cleric("Vater X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric03 = new Cleric("Mutter X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric04 = new Cleric("Vater X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric05 = new Cleric("Mutter X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric06 = new Cleric("Vater X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric07 = new Cleric("Mutter X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric08 = new Cleric("Vater X", "dem Morgenstern", 80, 10, 8, 0);
    static Enemy cleric09 = new Cleric("Mutter X", "dem Morgenstern", 80, 10, 8, 0);

    static Enemy boss01 = new Boss01("X X", "X", 80, 10, 8, 0);
    static Enemy boss02 = new Boss02("X X", "X", 80, 10, 8, 0);
    static Enemy boss03 = new Boss03("X X", "X", 80, 10, 8, 0);
    //endregion


    // ENCOUNTER
    static Encounter encStandard = new Encounter("Testwachen", true, guard01, guard02, guard03, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", gold999, null);
    static Encounter enc022 = new Encounter("Angeheiterte Wachen", true, guard01, guard02, guard03, Descriptions.enc022Intro, Descriptions.enc022Outro, scrollLifeline, null);
    static Encounter enc010 = new Encounter("Aufseher Otto", false, dog01, soldier01, dog02, Descriptions.enc010Intro, Descriptions.enc010Outro, key012, null);
    static Encounter enc012 = new Encounter("Dame Beatrix", false, guard04, knight01, guard05, Descriptions.enc012Intro, Descriptions.enc012Outro, evoc02, alche52);

    //static Encounter enc012 = new Encounter();






}
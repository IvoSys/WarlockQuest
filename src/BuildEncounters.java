public class BuildEncounters {

    //GEGNER
    static Enemy guard01 = new Guard("Wache Rüdiger", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard02 = new Guard("Wache Alfred", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard03 = new Guard("Wache Helga", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard04 = new Guard("Wache Wenzel", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard05 = new Guard("Wache Bertha", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard06 = new Guard("Wache Hedwig", "dem Knüppel", 50, 5, 5, 0);
    static Enemy guard07 = new Guard("Wache Hans", "dem Knüppel", 50, 5, 5, 0);
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

    static Enemy novice01 = new Novice("Schwester Friede", "dem Streitkolben", 55, 10, 8, 0);
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


    // ENCOUNTER
    static Encounter encStandard = new Encounter("Testwachen", true, guard02, guard03, guard04, "Achtung, Testüberfall!", "Achtung, Testüberfall beendet!", BuildItems.gold, null);
    static Encounter enc022 = new Encounter("Angeheiterte Wachen", true, guard02, guard03, guard04, Descriptions.enc022Intro, Descriptions.enc022Outro, BuildItems.scrollLifeline, null);
    static Encounter enc010 = new Encounter("Aufseher Otto", false, dog01, soldier01, dog02, Descriptions.enc010Intro, Descriptions.enc010Outro, BuildItems.key012, null);
    static Encounter enc021 = new Encounter("Erwachte Wachen", true, guard05, guard06, guard07, Descriptions.enc021Intro, Descriptions.enc021Outro, BuildItems.alche201, BuildItems.alche202);
    static Encounter enc012 = new Encounter("Dame Beatrix", false, guard01, knight01, novice01, Descriptions.enc012Intro, Descriptions.enc012Outro, BuildItems.evoc02, BuildItems.alche502);

}

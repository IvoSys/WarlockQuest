import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildItems {

    //BESCHWÖRUNGSFORMELN
    static Evocation evoc01 = new Evocation("Beschwörungsformel des gehörnten Königs", "Schriftrolle", Descriptions.riddleDem01, "Minotauros", "Die \"Pflastersteine\" sind die Tasten auf der Tastatur.");
    static Evocation evoc02 = new Evocation("Beschwörungsformel der entflammten Dame", "Schriftrolle", Descriptions.riddleDem02, "Efreet", "Das Feuer verzehrt sich nach seiner Meisterin.");
    static Evocation evoc03 = new Evocation("Beschwörungsformel des Abgründigen", "Schriftrolle", Descriptions.riddleDem03, "Abaddon", "Fahre mit dem Finger über den Tintenklecks");

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
    static Ingredience alche101 = new Ingredience("Molchauge", "Kugel", "Feine Blutgefäße ziehen sich in unruhigen Fäden durch den Augapfel.", "In einem Einmachglas schwimmt eine einzelne glitschige Kugel. ", 1);
    static Ingredience alche102 = new Ingredience("Glutorchidee", "Blümchen", "Rein dem Aussehen nach wird diese empfindliche Blüte ihrem Namen gerecht.", null, 1);
    static Ingredience alche103 = new Ingredience("Fliegenpilz", "Pilz", "Dieses gefährliche Gewächs zu entgiften und gar zu einem Trank zu verbrauen, \nsollte einem Meisteralchemisten vorbehalten sein. Wie dir.", null, 1);
    static Ingredience alche104 = new Ingredience("Marienkäferflügel", "", "Zart durchscheinende Häutchen, noch verbunden mit einer gepunkteten Chitinschicht.", null, 1);
    static Ingredience alche105 = new Ingredience("Sperlingszunge", "", "", null, 1);
    static Ingredience alche106 = new Ingredience("Rosenblüten", "Blüten", "Für sinnliches Badevergnügen.", null, 1);
    static Ingredience alche107 = new Ingredience("Tollwurm", "Wurm", "Furia infernalis! Ein rostrotes und, in falschen Händen, hochgiftiges Insekt.", null, 1);
    static Ingredience alche108 = new Ingredience("Radieschen", "Radieschen", "Raphanus sativus. Diese kleinen Wurzelknollen entfalten mitunter einen scharfen Geschmack. \nBreit verfügbar und günstig, dennoch für einfache Tränke geeignet.", null, 1);
    //Blau, ergibt Manatränke
    static Ingredience alche201 = new Ingredience("Mondbeeren", "Beeren", "Eisblaue Früchte, die den Geist erfrischen.", "In einer Schale ist eine Handvoll hellblaue Beeren übrig. ", 2);
    static Ingredience alche202 = new Ingredience("Mitternachtskraut", "Kraut", "Fahlblaue Blätter, die sich lieber dem Mondlicht als der Sonne zuwenden. \nGeschätzt von denjenigen, die Nachts wach bleiben müssen.", null, 2);
    static Ingredience alche203 = new Ingredience("Eisenhut", "Kraut", "Eine Blüte wie ein blauer Helm.", null, 2);
    //Gelb, frei (Wiederbelebung?)
    //gelb static Ingredience alche301 = new Ingredience("", "", "", "", 3);
    //gelb static Ingredience alche302 = new Ingredience("", "", "", "", 3);
    //Grün, frei
    //grün static Ingredience alche401 = new Ingredience("", "", "", "", 4);
    //grün static Ingredience alche402 = new Ingredience("", "", "", "", 4);
    //Schwarz, ergibt Level-up-Tränke
    static Ingredience alche501 = new Ingredience("Maluswurzel", "Wurzel", "Das gewundene dunkle Wurzelwerk der Crassula ovata.", null, 5);
    static Ingredience alche502 = new Ingredience("Tollkirsche", "", "In falschen Händen tödlich.", null, 5);


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
    static Spellscroll scrollBloodletting = new Spellscroll("Zauberschriftrolle des Aderlasses", "", "Diese Schriftrolle lehrt verbotene Blutmagie.", null, "Aderlass");
    static Spellscroll scrollDoom = new Spellscroll("Zauberschriftrolle des Untergangs", "", "Diese Schriftrolle lehrt einen furchtbaren Fluch.", null, "Untergang");
    static Spellscroll scrollIronMaiden = new Spellscroll("Zauberschriftrolle der eisernen Jungfrau", "", "Diese Schriftrolle lehrt einen furchtbaren Fluch.", null, "Eiserne Jungfrau");
    static Spellscroll scrollLifeline = new Spellscroll("Zauberschriftrolle der Lebenslinie", "", "Diese Schriftrolle lehrt verbotene Blutmagie.", null, "Lebenslinie");
    static Spellscroll scrollSoulreaper = new Spellscroll("Zauberschriftrolle des Seelendiebes", "", "Diese Schriftrolle lehrt finstere Hexerei.", null, "Seelendieb");
    static Spellscroll scrollViciousSeed = new Spellscroll("Zauberschriftrolle der üblen Saat", "", "Diese Schriftrolle lehrt finstere Hexerei.", null, "Üble Saat");

    //ZAUBER
    static Spell bloodletting = new Bloodletting();
    static Spell doom = new Doom();
    static Spell ironMaiden = new IronMaiden();
    static Spell lifeline = new Lifeline();
    static Spell soulreaper = new Soulreaper();
    static Spell viciousSeed = new ViciousSeed();

    static Spell[] allSpells = {bloodletting, doom, lifeline, soulreaper, viciousSeed};
    static ArrayList<Spell> freeSpells = new ArrayList<>(Arrays.asList(allSpells));

    //General Items
    static GeneralItem belongings = new GeneralItem("Maleficarius' Habseligkeiten", "Habe", "Maleficarius trägt eine bodenlange violette Robe, hohe Stiefel \nund eine Umhängetasche mit wichtigen Büchern und Utensilien:\n", "", 0, 0);
    static GeneralItem bag011 = new GeneralItem("Hexertasche", "Tasche", "Eine robuste Stofftasche, in der Maleficarius seine Schriftrollenbehälter transportiert.", "Eine dir wohlbekannte Tasche wurde achtlos in die Ecke geworfen. \n", 0, -1);

    //Core-Items
    static CoreItem bookBlackArts = new CoreItem("\"Die schwarzen Künste\"", Descriptions.bookBlackArtsDesc);
    static CoreItem bookSpells = new CoreItem("Zauberbuch", "Ein ledergebundener Foliant, in dem all deine Zauber verzeichnet sind: \n");
    static CoreItem bookCodex = new CoreItem("Codex infernalis", "Ein Foliant, gebunden in mysteriöses Leder. \n");
    static CoreItem bagAlche = new CoreItem("Alchemiebeutel", "Ein samtener Beutel, in dem du all deine alchemistischen Zutaten aufbewahrst: \n");
    static CoreItem bagPotions = new CoreItem("Trankgurt", "Ein System aus Lederriemen, mit denen du Trankfläschchen am Gürtel befestigen kannst. \n");
    static CoreItem evoc00 = new CoreItem("Beschwörungsformel des vertrauten Geistes", Descriptions.riddleDem00);


    //Schlüssel
    static Key key000 = new Key("Zellenschlüssel", "Schlüssel", "Ein rostiger Klumpen von Schlüssel.", null, -1, 0);
    static Key key012 = new Key("Kerkerschlüssel", "Schlüssel", "Der Schlüssel zum Kerker … und AUS dem Kerker.", null, 12, 0);

    //Puzzle-Item
    static PuzzleItem bread = new PuzzleItem("Brot", "Brot", "Eine Scheibe fluffiges Brot mit knuspernder Kruste. Malzig im Geschmack und reich an Ballaststoffen.", null, 0, 12);
    static PuzzleItem cheese = new PuzzleItem("Käse", "Käse", "Eine Scheibe nussig-cremiger Bergkäse. Vollmundig und sehr nahrhaft.", null, 0, 12);
    static PuzzleItem cheeseBread = new PuzzleItem("Käsebrot", "", "Bestechend einfach und beinahe perfekt.", null, 0, 13);
    static PuzzleItem pickle = new PuzzleItem("Essiggurke", "Essiggurken", "Süß-sauer eingelegt mit Senfkörnern und Dill. Rundet geschmacklich so einiges gelungen ab.", null, 0, 13);
    static PuzzleItem sandwich = new PuzzleItem("Käsebrot mit Essiggurke", "Käsebrot mit Essiggurke", "Ein herzhaft-würziges Käsebrot mit Essiggurke. Eine ideale Zwischenmahlzeit.", null, 0, 0);    // roomID 0, da Item in Dialog verwendet
    static PuzzleItem rock = new PuzzleItem("Stein", "Steine", "Es ist ein Stein.", null, 20, 0);
    static PuzzleItem gold = new PuzzleItem("Goldklumpen", "", "Alchemistisch betrachtet wertlos.", null, 0, 0);

    //Startitems
    public static void giveStartItems() {
        Player.inv.add(belongings);
        Player.inv.add(bookBlackArts);
        Player.inv.add(bookSpells);
        Player.inv.add(bookCodex);
        Player.inv.add(bagPotions);
        Player.inv.add(bagAlche);
        Player.inv.add(evoc00);
    }

}

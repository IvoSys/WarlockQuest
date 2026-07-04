import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WarlockQuest {

    //Reminder für mich: Klassenübergreifende Zugriffe erfordern Klassen-/Instanzvariablen, nicht lokale Variablen in Methoden!

    //DÄMONEN
    //region
    //static Demon dem01 = new Demon("Abbadon", "Herr des Abgrunds", 100, "");
    //static Demon[] allDem = {dem01};
    //static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));
    //endregion

    //BESCHWÖRUNGSFORMELN
    //region
    static ItemEvoc evoc00 = new ItemEvoc("Beschwörungsformel des Daimon", "Diesen okkulten Text hast du vor einer Weile schon entschlüsselt. \nDer wahre Name des Daimon lautet: \n\033[3mAgathos Daímōn Týchē, Spritus benefactum\033[0m \n\nDAIMON: \"Nooohh, Malle, ich bin doch schon bei dir!\"\n\n… ob es so eine gute Idee war, diesen Plagegeist zu beschwören?", "Daimon");
    static ItemEvoc evoc01 = new ItemEvoc("Beschwörungsformel des Abbaddon", "Hinweistext auf wahren Namen des Abbadon", "Abbadon");
    //endregion

    //TRÄNKE
    //region
    static ItemPotion pot01 = new ItemPotion("Geringer Heiltrank", "Ein schwacher Heiltrank.", 50);
    //endregion

    //SCHRIFTRROLLEN
    //region
    static ItemScroll scr01 = new ItemScroll("Fluch", "XXX.");
    //endregion

    //ZAUBER
    //region

    static Spell[] allSpells = {};
    static ArrayList<Spell> freeSpells = new ArrayList<>();
    //endregion

    //Schlüssel
    //region
    static ItemKey book = new ItemKey("\"Die schwarzen Künste\"", "Das Standardwerk über Hexerei, Alchemie und Dämonologie verfasst von Meister Maleficarius Liebwerk. \n[…] Zauber werden mittels Schriftrollen erlernt und können bis zur geistigen Erschöpfung gewirkt werden. \n[…] Tränke werden mit Verabreichung verbraucht und entfalten unvergleichliche Heilkraft, selbst bei Dämonen. \n[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!", 99);
    static ItemKey key000 = new ItemKey("Zellenschlüssel", "Ein rostiger Klumpen von Schlüssel.", 0);
    //endregion

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Boolean für Spielschleife
    static boolean running = true;

    //Scanner
    static Scanner sc = new Scanner(System.in);
    static String input;
    static String[] inputSplit;


    //Methode: Schloss bauen
    public static void buildCastle() {

        //UG, Reihe unten
        castle[0][0][0] = new Room("Gefängniszelle", Story.desc000, Story.daimon000, Story.solved000, false, false, false, false, false, false, 0);
        castle[0][0][0].reward = key000;
        castle[0][0][1] = new Room("Kerker", Story.desc001, Story.daimon001, Story.solved001, false, true, false, true, false, false, 1);
        castle[0][0][2] = new Room("zugiger Kellergang", Story.desc002, Story.daimon002, Story.solved002, true, false, false, false, false, false, 2);
        //    [z][y][x]

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("Offiziersquartier", Story.desc010, Story.daimon010, Story.solved010, false, false, false, false, false, false, 10);
        castle[0][1][1] = new Room("Vorratskammer", Story.desc011, Story.daimon011, Story.solved011, false, false, false, false, false, false, 11);
        castle[0][1][2] = new Room("Vorraum (UG)", Story.desc012, Story.daimon012, Story.solved012, false, false, false, false, false, false, 12);

        //UG, Reihe oben
        castle[0][2][0] = new Room("Waschraum", Story.desc020, Story.daimon020, Story.solved020, false, false, false, false, false, false, 20);
        castle[0][2][1] = new Room("Schlafsaal", Story.desc021, Story.daimon021, Story.solved021, false, false, false, false, false, false, 21);
        castle[0][2][2] = new Room("Wachkantine", Story.desc022, Story.daimon022, Story.solved022, false, false, false, false, false, false, 22);

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

    //Methode: Gegner vorbereiten
    public static void createEnemies() {

    }

    // MAIN
    static void main(String[] args){

        buildCastle();
        createEnemies();

        Player.inv.add(book);
        Player.inv.add(evoc00);

        ASCII.title();
        Story.intro();
        Story.needHelp();
        input = sc.nextLine().toLowerCase().trim();
        if (input.contains("j")){
            Story.help();
            Control.enterToContinue();
        }
        System.out.println();
        Story.daimonIntro();

        gameLoop();

    }

    // GAME LOOP
    public static void gameLoop(){
        while (running){
            Player.room = castle[Player.curZ][Player.curY][Player.curX];
            if (Player.moved) {
                System.out.println();
                System.out.println(Player.room.name.toUpperCase());
                System.out.println(Player.room.desc);
                Player.moved = false;
            }
            Control.cta();
            if (input.equals("ende") || input.equals("e")){
                Control.quit();
            } else if (input.contains("hilfe")){
                Story.help();
            } else if (input.equals("i") || input.equals("items")){
                Player.showInv();
            } else if (input.equals("d") || input.equals("daimon")){
                Player.daimon();
            } else if (input.equals("b") || input.equals("binden")){
                Demon.bind();
            } else if (input.contains("kampf")){
                Story.helpBattle();
            } else if (input.contains("alchemie")){
                Story.helpAlchemy();
            } else if (input.contains(".")) {
                inputSplit = input.split("\\.", 3);
                if (inputSplit[0].equals("g") || inputSplit[0].contains("geh")){
                    Player.move(inputSplit[1]);
                } else if (inputSplit[0].equals("u") || inputSplit[0].contains("such") || inputSplit[0].equals("prüf")){
                    Player.checkItem(inputSplit[1]);
                } else if (inputSplit[0].equals("n") || inputSplit[0].equals("nimm") || inputSplit[0].contains("nehm")){
                    Player.room.loot(inputSplit[1]);
                } else if (inputSplit[0].equals("v") || inputSplit[0].equals("verwende") || inputSplit[0].contains("nutze")){
                    Room.solve(inputSplit[1]);
                } else if (inputSplit[0].equals("l") || inputSplit[0].contains("lern")){
                    ItemScroll.learn(inputSplit[1]);
                } else if (inputSplit[0].equals("k") || inputSplit[0].contains("kombi")) {
                    Player.combine(inputSplit[1], inputSplit[2]);
                } else {
                    System.out.println("Du redest wirr!");
                }
            } else if (input.equals("credits")) {
                Story.credits();
            } else {
                System.out.println("Sprich deutlich!");
            }
        }
    }
}
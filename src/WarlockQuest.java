import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WarlockQuest {

    //Reminder für mich: Klassenübergreifende Zugriffe erfordern Klassen-/Instanzvariablen, nicht lokale Variablen in Methoden!

    //Dämonen
    //region
    static Demon dem01 = new Demon("Abbadon", "Herr des Abgrunds", 100, "");
    static Demon[] allDem = {dem01};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));
    //endregion

    //Beschwörungsformeln
    //region
    static ItemEvoc evoc00 = new ItemEvoc("Beschwörungsformel des Daimon", "\"Agathos Daímōn Týchē, Spritus benefactum\" \n… ob es so eine gute Idee war, diesen Plagegeist zu beschwören?", "Daimon");
    static ItemEvoc evoc01 = new ItemEvoc("Beschwörungsformel des Abbaddon", "Hinweistext auf wahren Namen des Abbadon", "Abbadon");
    //endregion

    //Potions
    //region
    static ItemPotion pot01 = new ItemPotion("Geringer Heiltrank", "Ein schwacher Heiltrank.", 50);
    //endregion

    //Spellscolls
    //region
    static ItemScroll scr01 = new ItemScroll("Eislanze", "Schleudert eine Lanze aus Eis auf den Gegner.", 50);
    //endregion

    //Schlüssel
    //region
    static ItemKey book = new ItemKey("\"Die schwarzen Künste\"", "Das Standardwerk über Hexerei, Alchemie und Dämonologie verfasst von Meister Maleficarius Liebwerk. \n[…] Zauberschriftrollen speichern die Kraft eines Zaubers für den einmaligen Gebrauch. \n[…] Tränke entfalten unvergleichliche Heilwirkung, selbst bei Dämonen. \n[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!", 99);
    static ItemKey key000 = new ItemKey("Zellenschlüssel", "Ein rostiger Klumpen von Schlüssel.", 0);
    //endregion

    //3D-Array erstellen
    static Room[][][] castle = new Room[3][3][3];

    //Boolean für Spielschleife
    static boolean running = true;

    //Scanner
    static Scanner sc = new Scanner(System.in);
    static String input;

    //Methode: Schloss bauen
    public static void buildCastle() {

        //UG, Reihe unten
        castle[0][0][0] = new Room("Gefängniszelle", Story.desc000, Story.daimon000, false, false, false, false, false, false, 0);
        castle[0][0][1] = new Room("Kerker", Story.desc001, Story.daimon001, false, true, false, true, false, false, 1);
        castle[0][0][2] = new Room("zugiger Kellergang", Story.desc002, Story.daimon002, true, false, false, false, false, false, 2);
        //    [z][y][x]

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("", Story.desc010, Story.daimon010, false, false, false, false, false, false, 10);
        castle[0][1][1] = new Room("", Story.desc011, Story.daimon011, false, false, false, false, false, false, 11);
        castle[0][1][2] = new Room("", Story.desc012, Story.daimon012, false, false, false, false, false, false, 12);

        //UG, Reihe oben
        castle[0][2][0] = new Room("", Story.desc020, Story.daimon020, false, false, false, false, false, false, 20);
        castle[0][2][1] = new Room("", Story.desc021, Story.daimon021, false, false, false, false, false, false, 21);
        castle[0][2][2] = new Room("", Story.desc022, Story.daimon022, false, false, false, false, false, false, 22);

        //EG, Reihe unten
        castle[1][0][0] = new Room("", Story.desc100, Story.daimon100, false, false, false, false, false, false, 100);
        castle[1][0][1] = new Room("", Story.desc101, Story.daimon101, false, false, false, false, false, false, 101);
        castle[1][0][2] = new Room("", Story.desc102, Story.daimon102, false, false, false, false, false, false, 102);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", Story.desc110, Story.daimon110, false, false, false, false, false, false, 110);
        castle[1][1][1] = new Room("", Story.desc111, Story.daimon111, false, false, false, false, false, false, 111);
        castle[1][1][2] = new Room("", Story.desc112, Story.daimon112, false, false, false, false, false, false, 112);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", Story.desc120, Story.daimon120, false, false, false, false, false, false, 120);
        castle[1][2][1] = new Room("", Story.desc121, Story.daimon121, false, false, false, false, false, false, 121);
        castle[1][2][2] = new Room("", Story.desc122, Story.daimon122, false, false, false, false, false, false, 122);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", Story.desc200, Story.daimon200, false, false, false, false, false, false, 200);
        castle[2][0][1] = new Room("", Story.desc201, Story.daimon201, false, false, false, false, false, false, 201);
        castle[2][0][2] = new Room("", Story.desc202, Story.daimon202, false, false, false, false, false, false, 202);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", Story.desc210, Story.daimon210, false, false, false, false, false, false, 210);
        castle[2][1][1] = new Room("", Story.desc211, Story.daimon211, false, false, false, false, false, false, 211);
        castle[2][1][2] = new Room("", Story.desc212, Story.daimon212, false, false, false, false, false, false, 212);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", Story.desc220, Story.daimon220, false, false, false, false, false, false, 220);
        castle[2][2][1] = new Room("", Story.desc221, Story.daimon221, false, false, false, false, false, false, 221);
        castle[2][2][2] = new Room("", Story.desc222, Story.daimon222, false, false, false, false, false, false, 222);
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
        if (input.equals("j")){
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
            } else if (input.equals("hilfe") || input.equals("h")){
                Story.help();
            } else if (input.equals("items") || input.equals("i")){
                Player.showInv();
            } else  if (input.equals("daimon") || input.equals("d")){
                Player.daimon();
            } else if (input.equals("binden") || input.equals("b")){
                Player.bind();
            } else if (input.contains("gehe ")){
                Player.move(input);
            } else if (input.contains("prüfe ")){
                Player.checkItem(input);
            } else if (input.contains("nimm ")){
                Player.loot(input);
            } else if (input.contains("nutze ")){
                Player.useKey(input);
            } else {
                System.out.println("Sprich deutlich!");
            }
        }
    }

    public static void solveRoom(){
        if (Player.room.puzzleID == 0) {
            castle[0][0][0].east = true;
            System.out.println("Mit rostigem Knarzen schwingt die Zellentür auf, \ndu kannst nach Osten aus der Zelle heraustreten.");
        }
    }


}

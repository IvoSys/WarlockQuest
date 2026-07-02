import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WarlockQuest {

    //Reminder für mich: Klassenübergreifende Zugriffe erfordern Klassen-/Instanzvariablen, nicht lokale Variablen in Methoden!

    //Dämonen
    //region
    static Demon dem01 = new Demon("Abbadon", "Herr des Abgrunds", 100);
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
    static ItemKey book = new ItemKey("\"Die schwarzen Künste\"", "Das Standardwerk über Hexerei, Dämonologie und Alchemie verfasst von Meister Maleficarius Liebwerk. \n[…] Zauberschriftrollen speichern die Kraft eines Zaubers für den einmaligen Gebrauch. \n[…] Tränke entfalten unvergleichliche Heilwirkung, selbst bei Dämonen. \n[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!", 99);
    static ItemKey key01 = new ItemKey("Zellenschlüssel", "Ein rostiger Klumpen von Schlüssel.", 1);
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
        castle[0][0][0] = new Room("Kerker", "Eine klamme Gefängniszelle, davor ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \nDie Tür nach Norden steht offen, aber erstmal musst du aus der Zelle rauskommen.", false, false, false, false, false, false, 1);
        castle[0][0][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[0][0][2] = new Room("", "", false, false, false, false, false, false, 0);

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[0][1][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[0][1][2] = new Room("", "", false, false, false, false, false, false, 0);

        //UG, Reihe oben
        castle[0][2][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[0][2][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[0][2][2] = new Room("", "", false, false, false, false, false, false, 0);

        //EG, Reihe unten
        castle[1][0][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][0][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][0][2] = new Room("", "", false, false, false, false, false, false, 0);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][1][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][1][2] = new Room("", "", false, false, false, false, false, false, 0);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][2][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[1][2][2] = new Room("", "", false, false, false, false, false, false, 0);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][0][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][0][2] = new Room("", "", false, false, false, false, false, false, 0);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][1][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][1][2] = new Room("", "", false, false, false, false, false, false, 0);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][2][1] = new Room("", "", false, false, false, false, false, false, 0);
        castle[2][2][2] = new Room("", "", false, false, false, false, false, false, 0);
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
        Story.daimon01();

        gameLoop();

    }

    // GAME LOOP
    public static void gameLoop(){
        while (running){
            if (Player.moved) {
                System.out.println();
                System.out.println(Player.room.name.toUpperCase());
                System.out.println(Player.room.desc);
                Player.moved = false;
            }
            Control.cta();

            if (input.equals("ende")){
                Control.quit();
            } else if (input.equals("hilfe")){
                Story.help();
            } else if (input.equals("items")){
                Player.showInv();
            } else if (input.contains("gehe ")){
                Player.move(input);
            } else if (input.contains("prüfe ")){
                Player.checkItem(input);
            }
            else {
                System.out.println("Befehl nicht erkannt.");
            }
        }
    }
}

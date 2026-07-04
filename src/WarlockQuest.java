import java.util.Scanner;

public class WarlockQuest {

    static String version = "v0.1.0";

    //Reminder für mich: Klassenübergreifende Zugriffe erfordern Klassen-/Instanzvariablen, nicht lokale Variablen in Methoden!
    //                   Statics greifen nicht auf Objekte zu!


    //Boolean für Spielschleife
    static boolean running = true;

    //Scanner
    static Scanner sc = new Scanner(System.in);
    static String input;
    static String[] inputSplit;

    // MAIN
    static void main(String[] args){

        WorldBuilder.buildCastle();
        WorldBuilder.createEnemies();

        Player.inv.add(WorldBuilder.bookBlackArts);
        Player.inv.add((WorldBuilder.book));
        Player.inv.add((WorldBuilder.bag));
        Player.inv.add(WorldBuilder.evoc00);

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
            Player.room = WorldBuilder.castle[Player.curZ][Player.curY][Player.curX];
            if (Player.moved) {
                Room.describe();
                Player.moved = false;
            }
            Control.cta();
            if (input.equals("ende")){
                Control.quit();
            } else if (input.equals("hilfe")){
                Story.help();
            } else if (input.equals("s") || input.equals("status")){
                Player.status();
            } else if (input.equals("i") || input.equals("items")){
                Player.showInv();
            } else if (input.equals("a") || input.equals("zutaten")){
                Player.showIngredients();
            } else if (input.equals("z") || input.equals("zauber")){
                Player.showSpells();
            } else if (input.equals("d") || input.equals("daimon")){
                Player.daimon();
            } else if (input.equals("b") || input.equals("binden")){
                Demon.bind();
            } else if (input.equals("l") || input.equals("lernen")){
                ItemScroll.learn();
            } else if (input.equals("r") || input.equals("raum")){
                Room.describe();
            } else if (input.equals("kampf")){
                Story.helpBattle();
            } else if (input.equals("kombinieren")){
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
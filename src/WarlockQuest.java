import java.util.Scanner;

public class WarlockQuest {

    static String version = "v0.1.0";
    static boolean running = true;

    static Scanner sc = new Scanner(System.in);
    static String input;
    static String[] inputSplit;

    static void main(String[] args){

        WorldBuilder.buildCastle();
        WorldBuilder.createEnemies();

        //Methode hierfür bauen?
        Player.inv.add(WorldBuilder.bookBlackArts);
        Player.inv.add((WorldBuilder.book));
        Player.inv.add((WorldBuilder.bag));
        Player.inv.add(WorldBuilder.evoc00);

        //DEBUG: Alchemie-Kram
        Player.inv.add(WorldBuilder.alch11);
        Player.inv.add(WorldBuilder.alch12);
        Player.inv.add(WorldBuilder.alch11);
        Player.inv.add(WorldBuilder.alch12);
        Player.inv.add(WorldBuilder.alch11);
        Player.inv.add(WorldBuilder.alch11);


        //ENDE DEBUG


        ASCII.title();
        /*Story.intro();
        Story.needHelp();
        input = sc.nextLine().toLowerCase().trim();
        if (input.contains("j")){
            Story.help();
            Control.enterToContinue();
        }
        System.out.println();
        Story.daimonIntro();

         */

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
            if (input.equals("d") || input.equals("daimon")){
                Player.daimon();                                                                                        // Daimon
            } else if (input.equals("i") || input.equals("items")){
                Player.showInv();                                                                                       // Inventar
            } else if (input.contains(".")) {
                inputSplit = input.split("\\.", 3);
                if (inputSplit[0].equals("n") || inputSplit[0].equals("nimm") || inputSplit[0].contains("nehm")){
                    Player.room.loot(inputSplit[1]);                                                                    // Nimm Item
                } else if (inputSplit[0].equals("v") || inputSplit[0].equals("verwende") || inputSplit[0].contains("nutze")) {
                    Room.solve(inputSplit[1]);                                                                          // Verwende Item
                } else if (inputSplit[0].equals("u") || inputSplit[0].contains("such") || inputSplit[0].equals("prüf")) {
                    Player.checkItem(inputSplit[1]);                                                                    // Untersuche Item
                } else if (inputSplit[0].equals("k") || inputSplit[0].contains("kombi")) {
                    Player.combineItems(inputSplit[1], inputSplit[2]);                                                  // Kombiniere Items
                } else if (inputSplit[0].equals("g") || inputSplit[0].contains("geh")){
                    Player.move(inputSplit[1]);                                                                         // Bewegen
                } else {
                    System.out.println("Du redest wirr!");
                }
            } else if (input.equals("s") || input.equals("status")){
                Player.showStatus();                                                                                    // Status
            } else if (input.equals("z") || input.equals("zauber")){
                Player.showSpells();                                                                                    // Zauberbuch
            } else if (input.equals("a") || input.equals("zutaten") || input.equals("alchemie")){
                Player.showIngredients();                                                                               // Alchemiezutaten
            } else if (input.equals("r") || input.equals("raum")){
                Room.describe();                                                                                        // Raum ansehen
            } else if (input.equals("l") || input.equals("lernen")){
                ItemScroll.learn();                                                                                     // Zauber lernen
            } else if (input.equals("b") || input.equals("binden")){
                Demon.bind();                                                                                           // Dämon binden
            } else if (input.equals("hilfe")){
                Story.help();                                                                                           // Hilfe
            } else if (input.equals("befehle")){
                Story.help2();                                                                                          // weitere Befehle (Inventar aufräumen)
            } else if (input.equals("kampftut")){
                Story.helpBattle();                                                                                     // Kampftutorial
            } else if (input.equals("alchetut")){
                Story.helpAlchemy();                                                                                    // Alchemietutorial
            } else if (input.equals("cleanup")){
                Player.cleanUpInv();                                                                                    // Inventar aufräumen
            } else if (input.equals("credits")) {
                credits();                                                                                              // Credits
            } else if (input.equals("ende")){
                Control.quit();                                                                                         // Beenden
            } else {
                System.out.println("Sprich deutlich!");
            }
        }
    }

    public static void credits(){
        System.out.println("WARLOCK QUEST " + version);
        System.out.println("Erdacht und umgesetzt von Ivo Haarmann");
        System.out.println("Handgetippt am Ammersee, 2026");
    }
}
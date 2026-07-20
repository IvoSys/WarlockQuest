import java.util.Scanner;

public class WarlockQuest {

    static String version = "v0.3.0";
    static boolean running = true;

    static Scanner sc = new Scanner(System.in);
    static String input;
    static String[] inputSplit;

    static void main(String[] args){

        WorldBuilder.buildCastle();
        WorldBuilder.giveStartItems();

        ASCII.title();
        Intro.intro();
        Intro.needHelp();
        input = sc.nextLine().toLowerCase().trim();
        if (input.contains("j")){
            Tutorials.help();
            Control.enterToContinue();
        }
        System.out.println();
        Daimon.intro();

        gameLoop();

    }

    // GAME LOOP
    public static void gameLoop(){
        while (running){
            Player.room = WorldBuilder.castle[Player.curZ][Player.curY][Player.curX];
            if (Player.moved) {
                Room.describe();
                if (Player.room.encounter != null && !Player.room.encounterBeaten) {
                    Battle.encounter = Player.room.encounter;
                    Battle.fight();
                }
                Player.moved = false;
            }
            Control.cta();
            if (input.equals("d") || input.equals("daimon")){
                Daimon.speak();                                                                                         // Daimon
            } else if (input.equals("s") || input.equals("sprechen")){
                Dialogue.speak();                                                                                       // Sprechen
            } else if (input.equals("i") || input.equals("items")){
                Player.showInv();                                                                                       // Inventar
            } else if (input.contains(".")) {
                inputSplit = input.split("\\.", 3);
                if (inputSplit[0].equals("n") || inputSplit[0].equals("t")){
                    Player.room.loot(inputSplit[1]);                                                                    // Nimm Item
                } else if (inputSplit[0].equals("v") || inputSplit[0].equals("u")) {
                    if (inputSplit[1].contains("trank") && !inputSplit[1].contains("gurt")) {
                        Potion.drink(inputSplit[1]);                                                                    // Trinke Trank
                    } else if (inputSplit[1].contains("zauberschriftrolle")) {
                        Spellscroll.learn();                                                                            // Lerne Zauber
                    } else if (inputSplit[1].contains("beschwörungsformel")) {
                        Evocation.bind();                                                                               // Binde Dämon
                    } else if (inputSplit[1].contains("tasche") || inputSplit[1].contains("bündel")) {
                        Item.unpack(inputSplit[1]);                                                                     // Entpacke Bündel
                    } else {
                        Player.room.solve(inputSplit[1]);                                                               // Verwende Item
                    }
                } else if (inputSplit[0].equals("b") || inputSplit[0].equals("e")) {
                    Player.checkItem(inputSplit[1]);                                                                    // Untersuche Item
                } else if (inputSplit[0].equals("k") || inputSplit[0].equals("c")) {
                    Player.combineItems(inputSplit[1], inputSplit[2]);                                                  // Kombiniere Items
                } else if (inputSplit[0].equals("g") || inputSplit[0].equals("m")){
                    Player.move(inputSplit[1]);                                                                         // Bewegen
                } else {
                    System.out.println("Du redest wirr!");
                }
            } else if (input.equals("status")){
                Player.showStatus();                                                                                    // Status
            } else if (input.equals("z") || input.equals("zauber")){
                Player.showSpells();                                                                                    // Zauberbuch
            } else if (input.equals("t") || input.equals("tränke")){
                Player.showPotions();                                                                                   // Tranktasche
            } else if (input.equals("a") || input.equals("zutaten") || input.equals("alchemie")){
                Player.showIngredients();                                                                               // Alchemiezutaten
            } else if (input.equals("r") || input.equals("raum")){
                Room.describe();                                                                                        // Raum ansehen
            } else if (input.equals("dämonen") || input.equals("dämon")){
                Demon.showDemons();                                                                                     // Kompendium infernale
            } else if (input.equals("h") || input.equals("hilfe")){
                Tutorials.help();                                                                                           // Hilfe
            } else if (input.equals("kampf")){
                Tutorials.helpBattle();                                                                                     // Kampftutorial
                //} else if (input.equals("cleanup")){
                //    Player.cleanUpInv();                                                                              // Inventar aufräumen
            } else if (input.equals("credits")) {
                credits();                                                                                              // Credits
            } else if (input.equals("ende")){
                Control.quit();                                                                                         // Beenden
            } else if (input.equals("gib mir einfach die lösungen")) {
                Tutorials.answers();
            //} else if (input.equals("ich bin der geist, der stets verneint!")) {
            } else if (input.equals("cheat")) {
                Player.cheatAllDemsAndSpells();
            } else {
                System.out.println("Sprich deutlich!");
            }
        }
    }

    public static void credits(){
        System.out.println("WARLOCK QUEST " + version);
        System.out.println("Erdacht und umgesetzt von Ivo Haarmann");
        System.out.println("Handgetippt am Ammersee, 2026");
        System.out.println();
        System.out.println("Mit besonderem Dank an meine Trainer \n");
        System.out.println();
        System.out.println("Herzlichen Dank an die Betatester \nfür die wertvollen Rückmeldungen:");
        System.out.println("- ");
    }
}
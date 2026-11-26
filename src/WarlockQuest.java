import java.util.Scanner;

public class WarlockQuest {

    static String version = "v0.5.5";

    static Scanner sc = new Scanner(System.in);
    static String input;
    static String[] inputSplit;


    static void main(String[] args){

        BuildCastle.buildCastle();
        BuildCastle.addRoomObjects();
        BuildItems.giveStartItems();

        ASCII.title();
        Intro.intro();

        //KAMPF-TEST: einmal "g.w" für Encounter
        Player.curX = 2;
        Player.curY = 2;
        Player.curZ = 2;
        Player.cheatAllDemsAndSpells();
        //KAMPF-TEST ENDE

        /*
        Daimon.intro();
        Daimon.intro2();
        Intro.needHelp();
        */

        gameLoop();
    }

    // GAME LOOP
    public static void gameLoop(){
        while (true){
            Player.room = BuildCastle.castle[Player.curZ][Player.curY][Player.curX];
            //if (Player.curZ >= 1)
                //demoFinished();
            if (Player.moved) {
                Room.describe();
                if (Player.room.encounter != null && !Player.room.encounterBeaten) {
                    Battle.fight();
                }
                Player.moved = false;
            }
            System.out.print("\n+++ Zeit zu handeln! +++ \n> ");
            WarlockQuest.input = WarlockQuest.sc.nextLine().toLowerCase().trim();
            if (input.equals("d") || input.equals("daimon")){
                Daimon.speak();                                                                                         // Daimon
            } else if (input.equals("a") || input.equals("ansprechen")){
                Dialogue.speak();                                                                                       // Sprechen
            } else if (input.equals("i") || input.equals("items")){
                Player.showInv();                                                                                       // Inventar
            } else if (input.contains(".")) {
                inputSplit = input.split("\\.", 3);
                if (inputSplit[0].equals("n") || inputSplit[0].equals("t")){
                    Player.loot(inputSplit[1]);                                                                         // Nimm Item
                } else if (inputSplit[0].equals("v") || inputSplit[0].equals("u")) {                            // Verwenden
                    if (inputSplit[1].contains("trank") && !inputSplit[1].contains("gurt")) {
                        Potion.drink(inputSplit[1]);                                                                    // Trinke Trank
                    } else if (inputSplit[1].contains("zauberschriftrolle ")) {
                        Spell.learnOnUse(inputSplit[1]);                                                                // Lerne Zauber
                    } else if (inputSplit[1].contains(BuildItems.evoc01.name.toLowerCase()) ||
                            inputSplit[1].contains(BuildItems.evoc02.name.toLowerCase()) ||
                            inputSplit[1].contains(BuildItems.evoc03.name.toLowerCase())) {
                        Player.examine(inputSplit[1]);
                        Evocation.bind();                                                                               // Binde Dämon
                    } else if (inputSplit[1].contains("tasche") || inputSplit[1].contains("behälter")) {
                        Item.unpack(inputSplit[1]);                                                                     // Entpacke Bündel
                    } else {
                        Player.room.unlockOrSolve(inputSplit[1]);                                                       // aufschließen/lösen
                    }
                } else if (inputSplit[0].equals("b")) {
                    Player.examine(inputSplit[1]);                                                                      // Betrachte Item
                } else if (inputSplit[0].equals("k")){
                    Item.combine(inputSplit[1], inputSplit[2]);                                                         // Kombiniere Items
                } else if (inputSplit[0].equals("g") || inputSplit[0].equals("m")){
                    Player.move(inputSplit[1]);                                                                         // Bewegen
                } else {
                    System.out.println("Du redest wirr!");
                }
            } else if (input.equals("s")|| input.equals("status")){
                Player.showStatus();                                                                                    // Status
            } else if (input.equals("z") || input.equals("zauber")){
                Player.showSpells();                                                                                    // Zauberbuch
            } else if (input.equals("r") || input.equals("raum")){
                Room.describe();                                                                                        // Raum ansehen
            } else if (input.equals("c") || input.equals("codex")){
                Player.showDemons();                                                                                    // Dämonen anzeigen
            } else if (input.equals("h") || input.equals("hilfe")){
                Tutorials.help();                                                                                       // Hilfe
            } else if (input.equals("kampf")){
                Tutorials.helpBattle();                                                                                 // Kampftutorial
            } else if (input.equals("credits")) {
                credits();                                                                                              // Credits
            } else if (input.equals("tipp")) {
                Evocation.hint();                                                                                       // Hinweis zu Beschwörungsformel
            } else if (input.equals("ende")){
                quit();                                                                                                 // Beenden
            } else if (input.equals("gib mir einfach die lösungen")) {
                Tutorials.answers();
            } else if (input.equals("ich bin der geist, der stets verneint!")) {
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
        // System.out.println("Mit besonderem Dank an meine Trainer \n");
        // System.out.println();
        System.out.println("Herzlichen Dank an die Betatester \nfür die wertvollen Rückmeldungen:");
        System.out.println("- Sebastian Bathiany");
    }

    public static void quit(){
        System.out.println("\n===================\nAUF WIEDERSEHEN");
        System.exit(0);
    }

    public static void demoFinished(){
        ASCII.congrats();
        System.out.println("Die Demo von Warlock Quest endet hier – vielen Dank fürs Spielen!");
        System.out.println("Komm gerne für die Vollversion zurück, wenn sie fertig ist.");
        System.exit(0);
    }

}
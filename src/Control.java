public class Control {

    public static void cta() {
        System.out.print("\nZeit zu handeln! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().toLowerCase().trim();
    }

    public static void enterToContinue() {
        System.out.println("(Weiter mit Eingabetaste)");
        try {
            System.in.read();
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void inputLine() {
        System.out.print("> ");
        Story.input = Story.sc.nextLine().toLowerCase().trim();
    }

    public static void quit(){
        System.out.println("\n===================\nAUF WIEDERSEHEN");
        System.exit(0);
    }


}

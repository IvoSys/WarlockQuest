public class Control {

        public static void enterToContinue() {
            System.out.println("(Weiter mit Eingabetaste)");
            try {
                System.in.read();
            }
            catch(Exception e){}
        }
}

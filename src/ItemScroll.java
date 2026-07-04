public class ItemScroll extends Item {

    public ItemScroll(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static void learn() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Zauber zu erlernen. \nGib die Zauberformel, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine();
        for (int i = 0; i < WorldBuilder.freeSpells.size(); i++) {
            if (WorldBuilder.freeSpells.get(i).formula.equals(WarlockQuest.input)) {
                Player.spellbook.add(WorldBuilder.freeSpells.get(i));
                System.out.println(WorldBuilder.freeSpells.get(i).textWhenLearned);
                WorldBuilder.freeDem.remove(i);
                success = true;
                break;
            }
        }
        if (!success)
            System.out.println("Dir raucht der Kopf vor Anstrengung. \nDieses Mal hast du keinen neuen Zauber erlernt.");
    }
}

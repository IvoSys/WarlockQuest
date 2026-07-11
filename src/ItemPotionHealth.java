public class ItemPotionHealth extends ItemPotion{

    public ItemPotionHealth (String name, String nameVague, String desc, int str, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public boolean effect() {
        int count = 1;
        int pick;
        boolean success = false;

        if (Player.team.isEmpty()) {
            System.out.println("Du hast keine Dämonen, die du heilen könntest.");
        } else {

            for (Demon d : Player.team) {
                System.out.printf("[%d] %s \t", count, d.name);
                if (d.ko)
                    System.out.println(">> BESIEGT <<");
                else
                    System.out.printf(" (%d/%d HP) \n", d.hp, d.hpMax);
            }
            System.out.println("Welchem Dämon willst du den Trank einflößen?");
            System.out.print("> ");
            pick = WarlockQuest.sc.nextInt();

            Demon healedDemon = Player.team.get(pick - 1);

            if (healedDemon.hp == healedDemon.hpMax) {
                System.out.printf("%s ist nicht verletzt", healedDemon.name);
            } else {
                healedDemon.hp += str;
                healedDemon.ko = false;             // falls Heiltränke wiederbeleben können sollen, sonst stattdessen in if-Bedingung aufnehmen.
                success = true;
                if (healedDemon.hp > healedDemon.hpMax) {
                    healedDemon.hp = healedDemon.hpMax;
                }
            }
        }
        return success;
    }
}

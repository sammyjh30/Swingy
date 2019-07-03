package za.co.swingy;

import za.co.swingy.model.items.Armor;

public class Main {

	public static void 			main(String[] args) {
		Armor armor = Armor.builder().name().level(1).build();
		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
		return;
	}

}

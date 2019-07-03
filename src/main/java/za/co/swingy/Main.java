package za.co.swingy;

import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;

public class Main {

	public static void 			main(String[] args) {
		Armor armor = Armor.builder().name().level(1).build();
		Helm	helm = Helm.builder().level(1).name().build();
		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
		System.out.println("Helm: " + helm.getName() + " level: " + helm.getLevel() + " hitPoint increase: " + helm.getHitPointIncrease());
		return;
	}

}

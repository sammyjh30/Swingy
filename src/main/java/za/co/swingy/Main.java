package za.co.swingy;

import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;

public class Main {

	public static void 			main(String[] args) {
//		Armor armor = Armor.builder().name().level(1).build();
//		Helm	helm = Helm.builder().level(1).name().build();
//		Weapon	weapon = Weapon.builder().level(1).name().build();
//		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
//		System.out.println("Helm: " + helm.getName() + " level: " + helm.getLevel() + " hitPoint increase: " + helm.getHitPointIncrease());
//		System.out.println("Weapon: " + weapon.getName() + " level: " + weapon.getLevel() + " Attack increase: " + weapon.getAttackIncrease());
//		armor.setStarterArmor();
//		helm.setStarterHelm();
//		weapon.setStarterWeapon();
//		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
//		System.out.println("Helm: " + helm.getName() + " level: " + helm.getLevel() + " hitPoint increase: " + helm.getHitPointIncrease());
//		System.out.println("Weapon: " + weapon.getName() + " level: " + weapon.getLevel() + " Attack increase: " + weapon.getAttackIncrease());

		Inventory inventory = Inventory.builder().weapons().helms().armors().build();
		inventory.starter();
		System.out.println("Inventory: ");
		System.out.println("Max slots: " + inventory.getMaxSlots());
		System.out.println("Used slots: " + inventory.getUsedSlots());
		System.out.println("Armor: ");
		for (int i = 0; i < inventory.getArmors().size(); i++) {
			Armor a = inventory.getArmors().get(i);
			System.out.println(a.getName() + " level: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease());
		}
		System.out.println("Weapon: ");
		for (int i = 0; i < inventory.getWeapons().size(); i++) {
			Weapon w = inventory.getWeapons().get(i);
			System.out.println(w.getName() + " level: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease());
		}
		System.out.println("Helms: ");
		for (int i = 0; i < inventory.getHelms().size(); i++) {
			Helm h = inventory.getHelms().get(i);
			System.out.println(h.getName() + " level: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease());
		}
		System.out.println("Max slots: " + inventory.getMaxSlots());
		System.out.println("Used slots: " + inventory.getUsedSlots());
		return;
	}



}

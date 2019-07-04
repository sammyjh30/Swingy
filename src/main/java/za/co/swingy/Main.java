package za.co.swingy;

import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

public class Main {

	public static void 			main(String[] args) {
		Armor armor = Armor.builder().name().level(1).build();
		Helm	helm = Helm.builder().level(1).name().build();
		Weapon	weapon = Weapon.builder().level(1).name().build();
		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
		System.out.println("Helm: " + helm.getName() + " level: " + helm.getLevel() + " hitPoint increase: " + helm.getHitPointIncrease());
		System.out.println("Weapon: " + weapon.getName() + " level: " + weapon.getLevel() + " Attack increase: " + weapon.getAttackIncrease());
		armor.setStarterArmor();
		helm.setStarterHelm();
		weapon.setStarterWeapon();
		System.out.println("Armor: " + armor.getName() + " level: " + armor.getLevel() + " defence increase: " + armor.getDefenceIncrease());
		System.out.println("Helm: " + helm.getName() + " level: " + helm.getLevel() + " hitPoint increase: " + helm.getHitPointIncrease());
		System.out.println("Weapon: " + weapon.getName() + " level: " + weapon.getLevel() + " Attack increase: " + weapon.getAttackIncrease());
		return;
	}

}

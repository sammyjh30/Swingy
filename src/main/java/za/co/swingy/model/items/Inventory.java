package za.co.swingy.model.items;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<Armor> armors = new ArrayList<Armor>();
	private ArrayList<Helm> helms = new ArrayList<Helm>();
	private int				usedSlots = 0;
	private int				maxSlots = 9;

	void 		addWeapon(Weapon newWeapon) {
		if (usedSlots < maxSlots) {
			weapons.add(newWeapon);
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Weapon!");
		}
	}

	void 		removeWeapon(Weapon weapon) {
		if (weapons.contains(weapon)) {
			weapons.remove(weapon);
			System.out.println("Successfully removed the " + weapon.getName());
		}
	}

	void 		addArmor(Armor newArmor) {
		if (usedSlots < maxSlots) {
			armors.add(newArmor);
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Armor!");
		}
	}

	void 		removeArmor(Armor armor) {
		if (armors.contains(armor)) {
			armors.remove(armor);
			System.out.println("Successfully removed the " + armor.getName());
		}
	}

	void 		addHelm(Helm newHelm) {
		if (usedSlots < maxSlots) {
			helms.add(newHelm);
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Helm!");
		}
	}

	void 		removeHelm(Helm helm) {
		if (helms.contains(helm)) {
			helms.remove(helm);
			System.out.println("Successfully removed the " + helm.getName());
		}
	}

}

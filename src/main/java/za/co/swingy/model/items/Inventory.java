package za.co.swingy.model.items;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@Data
public class Inventory {
	@NotNull
	private ArrayList<Weapon> 	weapons;
	@NotNull
	private ArrayList<Armor> 	armors;
	@NotNull
	private ArrayList<Helm> 	helms;
	@NotNull
	private int					usedSlots;
	@NotNull
	private int					maxSlots;
	@NotNull
	private int					equippedWeaponIndex;
	@NotNull
	private int					equippedArmorIndex;
	@NotNull
	private int					equippedHelmIndex;

	public static class InventoryBuilder {
		private ArrayList<Weapon> 	weapons;
		private ArrayList<Armor> 	armors;
		private ArrayList<Helm> 	helms;
		private int					usedSlots;
		private int					maxSlots;
		private int					equippedWeaponIndex;
		private int					equippedArmorIndex;
		private int					equippedHelmIndex;

		public InventoryBuilder		weapons(){
			this.weapons = new ArrayList<Weapon>();
			return this;
		}

		public InventoryBuilder		armors(){
			this.armors = new ArrayList<Armor>();
			return this;
		}

		public InventoryBuilder		helms(){
			this.helms = new ArrayList<Helm>();
			return this;
		}
	}

	public void 		increaseMaxSlots(int n) {
		this.maxSlots += n;
	}

	public void 		decreaseMaxSlots(int n) {
		this.maxSlots -= n;
	}

	public void 		addWeapon(Weapon newWeapon) {
		if (newWeapon == null) {
			System.out.println("newWeapon is invalid");
			return;
		}
		if (this.weapons == null) {
			System.out.println("weapons is invalid");
			return;
		}
		if (usedSlots < maxSlots) {
			this.weapons.add(newWeapon);
			this.usedSlots++;
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Weapon!");
		}
	}

	public void 		removeWeapon(Weapon weapon) {
		if (weapons.contains(weapon)) {
			weapons.remove(weapon);
			System.out.println("Successfully removed the " + weapon.getName());
		}
	}

	public void 		addArmor(Armor newArmor) {
		if (newArmor == null) {
			System.out.println("New Armor is invalid");
			return;
		}
		if (this.armors == null) {
			System.out.println("Armors is invalid");
			return;
		}
		if (usedSlots < maxSlots) {
			armors.add(newArmor);
			this.usedSlots++;
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Armor!");
		}
	}

	public void 		removeArmor(Armor armor) {
		if (armors.contains(armor)) {
			armors.remove(armor);
			System.out.println("Successfully removed the " + armor.getName());
		}
	}

	public void 		addHelm(Helm newHelm) {
		if (newHelm == null) {
			System.out.println("New Helm is invalid");
			return;
		}
		if (this.helms == null) {
			System.out.println("Helms is invalid");
			return;
		}
		if (usedSlots < maxSlots) {
			helms.add(newHelm);
			this.usedSlots++;
		} else {
			System.out.println("It seems your inventory is full! Remove some items to add this Helm!");
		}
	}

	public void 		removeHelm(Helm helm) {
		if (helms.contains(helm)) {
			helms.remove(helm);
			System.out.println("Successfully removed the " + helm.getName());
		}
	}

	public void						starter() {
		//Set basic stats
		this.usedSlots = 0;
		this.maxSlots = 9;
		this.equippedWeaponIndex = 0;
		this.equippedArmorIndex = 0;
		this.equippedHelmIndex = 0;
		//Starter Weapon
		Weapon starterWeapon = Weapon.builder().build();
		starterWeapon.setStarterWeapon();
		this.addWeapon(starterWeapon);

		//Starter Armor
		Armor starterArmor = Armor.builder().build();
		starterArmor.setStarterArmor();
		this.addArmor(starterArmor);
		//Starter Helm
		Helm starterHelm = Helm.builder().build();
		starterHelm.setStarterHelm();
		this.addHelm(starterHelm);
	}

}

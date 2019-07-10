package za.co.swingy.model.characters;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;

import javax.validation.constraints.NotNull;

import static java.lang.Math.pow;

@Getter
@Setter
@Builder
@Data
public class Hero extends Character {
	@NotNull
	private String		name;
	@NotNull
	private String		classType;
	private Weapon		equippedWeapon;
	private Armor		equippedArmor;
	private Helm		equippedHelm;
	@NotNull
	private  int		experience;
	@NotNull
	private Inventory	inventory;

	// Builder
	public static class HeroBuilder {

		private String		name;
		private Weapon		equippedWeapon;
		private Armor		equippedArmor;
		private  int		experience;
		private Inventory	inventory;

		public HeroBuilder			name(String newName) {
			this.name = newName;
			return this;
		}

		public HeroBuilder			equippedWeapon(Weapon newWeapon) {
			this.equippedWeapon = newWeapon;
			return this;
		}

		public HeroBuilder			equippedArmor(Armor newArmor) {
			this.equippedArmor = newArmor;
			return this;
		}

		public HeroBuilder			experience(int exp) {
			this.experience = exp;
			return this;
		}

		public HeroBuilder			inventory(Inventory invent) {
			this.inventory = invent;
			return  this;
		}

		public HeroBuilder			inventory() {
			this.inventory = Inventory.builder().weapons().armors().helms().build();
			return  this;
		}

		public HeroBuilder			classType(String type) {
			if (type.equalsIgnoreCase("Warrior") || type.equalsIgnoreCase("Knight") ||
					type.equalsIgnoreCase("Explorer") || type.equalsIgnoreCase("Barbarian")) {
				this.classType = type;
			} else {
				System.out.println("'" + type + "' is not a valid type. Setting as Warrior by default.");
				this.classType = "Warrior";
			}
			return this;
		}
	}

	public void 			starterHero(){
		this.setLevel(0);
		this.experience = 0;
		this.inventory.starter();
		//Set Starter Weapon as equipped
		if (this.inventory.getWeapons().size() > 0) {
			this.equippedWeapon = this.inventory.getWeapons().get(0);
			this.inventory.setEquippedWeaponIndex(0);
		}
		//Set Starter Armor as equipped
		if (this.inventory.getArmors().size() > 0) {
			this.equippedArmor = this.inventory.getArmors().get(0);
			this.inventory.setEquippedArmorIndex(0);
		}
		//Set Starter Helms as equipped
		if (this.inventory.getHelms().size() > 0) {
			this.equippedHelm = this.inventory.getHelms().get(0);
			this.inventory.setEquippedHelmIndex(0);
		}
		//Set attack and defence
		if (this.classType == "Explorer") {
			this.setAttack(1);
			this.setDefence(1);
			this.setMaxHitPoints(50);
			this.setHitPoints(50);
		} else if (this.classType == "Warrior") {
			this.setAttack(2);
			this.setDefence(2);
			this.setMaxHitPoints(50);
			this.setHitPoints(50);
		} else if (this.classType == "Knight") {
			this.setAttack(3);
			this.setDefence(3);
			this.setMaxHitPoints(50);
			this.setHitPoints(50);
		} else if (this.classType == "Barbarian") {
			this.setAttack(4);
			this.setDefence(4);
			this.setMaxHitPoints(50);
			this.setHitPoints(50);
		}
	}

	//Equipping items
	public void					equipArmor(int index) {
		try {
			this.equippedArmor = this.inventory.getArmors().get(index);
			this.inventory.setEquippedArmorIndex(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void					equipWeapon(int index) {
		try {
			this.equippedWeapon = this.inventory.getWeapons().get(index);
			this.inventory.setEquippedWeaponIndex(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void					equipHelm(int index) {
		try {
			this.equippedHelm = this.inventory.getHelms().get(index);
			this.inventory.setEquippedHelmIndex(index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	/////////////////

	//Level Up
	public void			levelUp() {
		int xpRequired = this.getLevel() * 1000 -  ((int)pow(this.getLevel() - 1, 2) * 450);
		if (this.experience >= xpRequired) {
			this.setLevel(this.getLevel() + 1);
			this.experience -= xpRequired;
			if (this.classType == "Explorer") {
				this.setAttack(this.getAttack() + 4);
				this.setDefence(this.getDefence() + 4);
			} else if (this.classType == "Warrior") {
				this.setAttack(this.getAttack() + 3);
				this.setDefence(this.getDefence() + 3);
			} else if (this.classType == "Knight") {
				this.setAttack(this.getAttack() + 2);
				this.setDefence(this.getDefence() + 2);
			} else if (this.classType == "Barbarian") {
				this.setAttack(this.getAttack() + 1);
				this.setDefence(this.getDefence() + 1);
			}
			this.setMaxHitPoints(this.getMaxHitPoints() + this.getLevel() * 50);
			this.setHitPoints(this.getMaxHitPoints());
			System.out.println("CONGRATULATIONS! You are now level " + this.getLevel() + "!");
		}
	}


}

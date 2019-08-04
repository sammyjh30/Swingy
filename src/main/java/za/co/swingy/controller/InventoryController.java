package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.InventoryView;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class InventoryController {
	@NotNull
	private Hero hero;
	@NotNull
	private InventoryView inventoryView;
	@NotNull
	private GameController gameController;

	// Builder
	public static class 		InventoryControllerBuilder {
		private Hero hero;
		private InventoryView inventoryView;
		private GameController gameController;

		public InventoryControllerBuilder		hero(Hero hero) {
			this.hero = hero;
			return this;
		}

		public InventoryControllerBuilder			inventoryView(InventoryView view) {
			this.inventoryView = view;
			return this;
		}

		public InventoryControllerBuilder			gameController(GameController gameController) {
			this.gameController = gameController;
			return this;
		}
	}

	public void					equip(String input) {
		int index;
		if (Integer.parseInt(input) <= this.getHero().getInventory().getArmors().size()) {
			//					Armors
			index = Integer.parseInt(input) - 1;
			try {
				Armor armor = this.getHero().getInventory().getArmors().get(index);
				this.getHero().setEquippedArmor(armor);
				this.getHero().getInventory().setEquippedArmorIndex(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(input) > this.getHero().getInventory().getArmors().size() &&
				Integer.parseInt(input) <= (this.getHero().getInventory().getArmors().size() + this.getHero().getInventory().getWeapons().size())) {
			//Weapons
			index = Integer.parseInt(input) - this.getHero().getInventory().getArmors().size() - 1;
			try {
				Weapon weapon = this.getHero().getInventory().getWeapons().get(index);
				this.getHero().setEquippedWeapon(weapon);
				this.getHero().getInventory().setEquippedWeaponIndex(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(input) > (this.getHero().getInventory().getArmors().size() + +this.getHero().getInventory().getWeapons().size()) &&
				Integer.parseInt(input) <= (this.getHero().getInventory().getArmors().size() + this.getHero().getInventory().getWeapons().size() + this.getHero().getInventory().getHelms().size())) {
			//Helms
			index = Integer.parseInt(input) - (this.getHero().getInventory().getArmors().size() + +this.getHero().getInventory().getWeapons().size()) - 1;
			try {
				Helm helm = this.getHero().getInventory().getHelms().get(index);
				this.getHero().setEquippedHelm(helm);
				this.getHero().getInventory().setEquippedHelmIndex(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.inventoryView.display();
	}

	public void					delete(String input) {
		int index;
		if (Integer.parseInt(input) <= this.getHero().getInventory().getArmors().size()) {
			//Armors
			index = Integer.parseInt(input) - 1;
			try {
				Armor armor = this.getHero().getInventory().getArmors().get(index);
				if (this.getHero().getInventory().getEquippedArmorIndex() == index) {
					this.getHero().setEquippedArmor(null);
					this.getHero().getInventory().setEquippedArmorIndex(-1);
				} else if (index < this.hero.getInventory().getEquippedArmorIndex()) {
					this.getHero().getInventory().setEquippedArmorIndex( this.hero.getInventory().getEquippedArmorIndex() - 1);
				}
				this.getHero().getInventory().removeArmor(armor);
				this.getHero().getInventory().setUsedSlots(this.getHero().getInventory().getUsedSlots() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(input) > this.getHero().getInventory().getArmors().size() &&
				Integer.parseInt(input) <= (this.getHero().getInventory().getArmors().size() + this.getHero().getInventory().getWeapons().size())) {
			//Weapons
			index = Integer.parseInt(input) - this.getHero().getInventory().getArmors().size() - 1;
			try {
				Weapon weapon = this.getHero().getInventory().getWeapons().get(index);
				if (this.getHero().getInventory().getEquippedWeaponIndex() == index) {
					this.getHero().setEquippedWeapon(null);
					this.getHero().getInventory().setEquippedArmorIndex(-1);
				} else if (index < this.getHero().getInventory().getEquippedWeaponIndex()) {
					this.getHero().getInventory().setEquippedWeaponIndex(this.getHero().getInventory().getEquippedWeaponIndex() - 1);
				}
				this.getHero().getInventory().removeWeapon(weapon);
				this.getHero().getInventory().setUsedSlots(this.getHero().getInventory().getUsedSlots() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(input) > (this.getHero().getInventory().getArmors().size() + +this.getHero().getInventory().getWeapons().size()) &&
				Integer.parseInt(input) <= (this.getHero().getInventory().getArmors().size() + this.getHero().getInventory().getWeapons().size() + this.getHero().getInventory().getHelms().size())) {
			//Helms
			index = Integer.parseInt(input) - (this.getHero().getInventory().getArmors().size() + this.getHero().getInventory().getWeapons().size()) - 1;
			try {
				Helm helm = this.getHero().getInventory().getHelms().get(index);
				if (this.getHero().getInventory().getEquippedHelmIndex() == index) {
					this.getHero().setEquippedHelm(null);
					this.getHero().getInventory().setEquippedHelmIndex(-1);
				} else if (index < this.getHero().getInventory().getEquippedHelmIndex()) {
					this.getHero().getInventory().setEquippedHelmIndex(this.getHero().getInventory().getEquippedHelmIndex() - 1);
				}
				this.getHero().getInventory().removeHelm(helm);
				this.getHero().getInventory().setUsedSlots(this.getHero().getInventory().getUsedSlots() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.inventoryView.display();
	}
}

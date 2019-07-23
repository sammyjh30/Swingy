package za.co.swingy.view.console;

import za.co.swingy.controller.InventoryController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.InventoryView;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryConsoleView implements InventoryView {
	@NotNull
	private InventoryController controller;

	public InventoryConsoleView(Hero hero) {
		this.controller = InventoryController.builder().inventoryView(this).hero(hero).build();
	}

	public void				display() {
		int list = 1;
		System.out.println("______________INVENTORY______________");
		System.out.println("| Capacity: " + this.controller.getHero().getInventory().getUsedSlots() + "/" + this.controller.getHero().getInventory().getMaxSlots());

		//Armor
		System.out.println("| Armor:");
		for (int i = 0; i < this.controller.getHero().getInventory().getArmors().size(); i++) {
			Armor a = this.controller.getHero().getInventory().getArmors().get(i);
			if (i == this.controller.getHero().getInventory().getEquippedArmorIndex()) {
				System.out.println("|	[" + (i + 1) + "] " + a.getName() + " level: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease() + "     [EQUIPPED]");
			} else {
				System.out.println("|	[" + (i + 1) + "] " + a.getName() + " level: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease());
			}
		}
		list =  this.controller.getHero().getInventory().getArmors().size();

		//Weapon
		System.out.println("| Weapon: ");
		for (int i = 0; i < this.controller.getHero().getInventory().getWeapons().size(); i++) {
			Weapon w = this.controller.getHero().getInventory().getWeapons().get(i);
			if (i == this.controller.getHero().getInventory().getEquippedWeaponIndex()) {
				System.out.println("|	[" + (i + 1 + list) + "] " + w.getName() + " level: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease() + "     [EQUIPPED]");
			} else {
				System.out.println("|	[" + (i + 1 + list) + "] " + w.getName() + " level: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease());
			}
		}
		list +=  this.controller.getHero().getInventory().getWeapons().size();

		// Helm
		System.out.println("| Helms: ");
		for (int i = 0; i < this.controller.getHero().getInventory().getHelms().size(); i++) {
			Helm h = this.controller.getHero().getInventory().getHelms().get(i);
			if (i == this.controller.getHero().getInventory().getEquippedHelmIndex()) {
				System.out.println("|	[" + (i + 1 + list) + "] " + h.getName() + " level: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease() + "     [EQUIPPED]");
			} else {
				System.out.println("|	[" + (i + 1 + list) + "] " + h.getName() + " level: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease());
			}
		}
		System.out.println("_____________________________________");
		System.out.println("You can use the commands EQUIP to equip an item, DELETE to delete and item, or RETURN to return to the game.");
		try {
			System.out.print("Please enter a command: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("EQUIP") && !input.equalsIgnoreCase("DELETE") && !input.equalsIgnoreCase("RETURN")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter a command: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("EQUIP")) {
				System.out.print("Please select the number of the item you would like to equip: ");
				input = bufferedReader.readLine();
				while (!this.isNumeric(input) && (Integer.parseInt(input) > list || Integer.parseInt(input) <= 0)) {
					System.out.println("Oops, that's not a valid input! Please try again!");
					System.out.print("Please select the number of the item you would like to equip: ");
					input = bufferedReader.readLine();
				}
//				int section;
				int index;
				if (Integer.parseInt(input) <= this.controller.getHero().getInventory().getArmors().size()) {
//					Armors
					index = Integer.parseInt(input) - 1;
				} else if (Integer.parseInt(input) > this.controller.getHero().getInventory().getArmors().size() &&
						Integer.parseInt(input) <= (this.controller.getHero().getInventory().getArmors().size() + this.controller.getHero().getInventory().getWeapons().size())) {
					//Weapons
					index = Integer.parseInt(input) - this.controller.getHero().getInventory().getArmors().size() - 1;
				} else if (Integer.parseInt(input) > (this.controller.getHero().getInventory().getArmors().size() + + this.controller.getHero().getInventory().getWeapons().size()) &&
						Integer.parseInt(input) <= (this.controller.getHero().getInventory().getArmors().size() + this.controller.getHero().getInventory().getWeapons().size() + this.controller.getHero().getInventory().getHelms().size())) {
					//Helms
					index = Integer.parseInt(input) - (this.controller.getHero().getInventory().getArmors().size() + + this.controller.getHero().getInventory().getWeapons().size()) - 1;
				}
			} else if (input.equalsIgnoreCase("DELETE")) {
				System.out.print("Please select the number of the item you would like to delete: ");
				input = bufferedReader.readLine();
				while (!this.isNumeric(input) && (Integer.parseInt(input) > list || Integer.parseInt(input) <= 0)) {
					System.out.println("Oops, that's not a valid input! Please try again!");
					System.out.print("Please select the number of the item you would like to delete: ");
					input = bufferedReader.readLine();
				}
			} else if (input.equalsIgnoreCase("RETURN")) {
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean 	isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}

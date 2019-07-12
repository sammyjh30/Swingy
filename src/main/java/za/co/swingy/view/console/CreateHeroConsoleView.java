package za.co.swingy.view.console;

import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.CreateHeroView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class CreateHeroConsoleView implements CreateHeroView {

	public 					CreateHeroConsoleView() {
//		controller = new Create
	}

	public void 			printHeroStatus(Hero hero) {
		System.out.println("_______________________");
		System.out.println("| Name:     " + hero.getName());
		System.out.println("| Type:     " + hero.getClassType());
		System.out.println("| HP:       " + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		System.out.println("| Attack:   " + hero.getAttack());
		System.out.println("| Defence:  " + hero.getDefence());
		System.out.println("|_______Inventory______");
		Inventory inventory = hero.getInventory();
		System.out.println("| Slots: " + inventory.getUsedSlots() +"/" + inventory.getMaxSlots());
		System.out.println("| Armor: ");
		for (int i = 0; i < inventory.getArmors().size(); i++) {
			Armor a = inventory.getArmors().get(i);
			System.out.println("| 	" + a.getName() + " level: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease());
		}
		System.out.println("| Weapon: ");
		for (int i = 0; i < inventory.getWeapons().size(); i++) {
			Weapon w = inventory.getWeapons().get(i);
			System.out.println("| 	" + w.getName() + " level: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease());
		}
		System.out.println("| Helms: ");
		for (int i = 0; i < inventory.getHelms().size(); i++) {
			Helm h = inventory.getHelms().get(i);
			System.out.println("| 	" + h.getName() + " level: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease());
		}
		System.out.println("|______________________");
		try {
			System.out.print("Alright, hero! We're ready to go! Let your adventure begin");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Try clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public String			 promptName() {
		try {
			System.out.println("Greetings hero! Before you begin your journey, let's set up your character!");
			System.out.print("Please enter in your name: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String name = bufferedReader.readLine();
			while (name == null || name.isEmpty()) {
				System.out.println("Oops, that's not a valid name! Please try again!");
				System.out.print("Please enter in your name: ");
				name = bufferedReader.readLine();
			}
			return name;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Bob";
	}

	public String			 promptType() {
		try {
			System.out.println("Alright, now it's time to choose a class! What would you like to be?");
			System.out.println("A : Explorer		(Attack: 1, Defence: 1, MaxHitPoints: 50)");
			System.out.println("B : Warrior			(Attack: 2, Defence: 2, MaxHitPoints: 50)");
			System.out.println("C : Knight			(Attack: 3, Defence: 3, MaxHitPoints: 50)");
			System.out.println("D : Barbarian		(Attack: 4, Defence: 4, MaxHitPoints: 50)");
			System.out.print("Please enter either A, B, C, or D for your class: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String type = bufferedReader.readLine();
			while (type.isEmpty() || !type.equals("A") && !type.equals("B") && !type.equals("C") && !type.equals("D")) {
				System.out.println("Oops, that's not a valid class! Please try again!");
				System.out.print("Please enter either A, B, C, or D for your class: ");
				type = bufferedReader.readLine();
			}
			if (type.equals("A")) {
				type = "Explorer";
			} else if (type.equals("B")) {
				type = "Warrior";
			} else if (type.equals("C")) {
				type = "Knight";
			} else if (type.equals("D")) {
				type = "Barbarian";
			}
			return type;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Explorer";
	}
}
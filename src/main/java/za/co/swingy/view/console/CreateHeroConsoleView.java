package za.co.swingy.view.console;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.controller.GameController;
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
	private CharacterController controller;
	private String				name;
	private String				type;

	public 					CreateHeroConsoleView() {
//		controller = new Create
	}

	public void 			printHeroStatus(Hero hero) {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_______________________");
		System.out.println("| Name:     " + hero.getName());
		System.out.println("| Level:    " + hero.getLevel());
		System.out.println("| Type:     " + hero.getClassType());
		if (hero.getEquippedHelm() == null) {
			System.out.println("| HP:       "  + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		} else {
			System.out.println("| HP:       "  + (hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
		}
		if (hero.getEquippedWeapon() == null) {
			System.out.println("| Attack:   " + hero.getAttack());
		} else {
			System.out.println("| Attack:   " + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}
		if (hero.getEquippedArmor() == null) {
			System.out.println("| Defence:  " + hero.getDefence());
		} else {
			System.out.println("| Defence:  " + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}
		System.out.println("| Position:  [" + hero.getXPos() + ";" + hero.getYPos() + "]");
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
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		GameController controller = GameController.builder().hero(hero).mapView(new MapConsoleView()).characterController(this.controller).build();
		controller.showMapView();
	}

	public void			 promptName(CharacterController controller) {
		this.controller = controller;
		try {
			System.out.println("Greetings hero! Before you begin your journey, let's set up your character!");
			System.out.print("Please enter in your name: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String nameInput = bufferedReader.readLine();
			while (nameInput == null || nameInput.isEmpty()) {
				System.out.println("Oops, that's not a valid name! Please try again!");
				System.out.print("Please enter in your name: ");
				nameInput = bufferedReader.readLine();
			}
			this.name = nameInput;
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.name = "Bob";
		this.promptType();
		this.controller.generateHero(this.name, this.type);
	}

	public void		 promptType() {
		try {
			System.out.println("Alright, now it's time to choose a class! What would you like to be?");
			System.out.println("A : Explorer		(Attack: 1, Defence: 1, MaxHitPoints: 50)");
			System.out.println("B : Warrior			(Attack: 2, Defence: 2, MaxHitPoints: 50)");
			System.out.println("C : Knight			(Attack: 3, Defence: 3, MaxHitPoints: 50)");
			System.out.println("D : Barbarian		(Attack: 4, Defence: 4, MaxHitPoints: 50)");
			System.out.print("Please enter either A, B, C, or D for your class: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String typeInput = bufferedReader.readLine();
			while (typeInput.isEmpty() || !typeInput.equalsIgnoreCase("A") && !typeInput.equalsIgnoreCase("B") && !typeInput.equalsIgnoreCase("C") && !typeInput.equalsIgnoreCase("D")) {
				System.out.println("Oops, that's not a valid class! Please try again!");
				System.out.print("Please enter either A, B, C, or D for your class: ");
				typeInput = bufferedReader.readLine();
			}
			if (typeInput.equalsIgnoreCase("A")) {
				typeInput = "Explorer";
			} else if (typeInput.equalsIgnoreCase("B")) {
				typeInput = "Warrior";
			} else if (typeInput.equalsIgnoreCase("C")) {
				typeInput = "Knight";
			} else if (typeInput.equalsIgnoreCase("D")) {
				typeInput = "Barbarian";
			}
			this.type = typeInput;
//			return type;
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.type = "Explorer";
//		return "Explorer";
	}
}

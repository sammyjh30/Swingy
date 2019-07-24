package za.co.swingy.view.gui.old;

import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.LoadFileView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoadFileGuiView implements LoadFileView {
	//Need to still save/load character position
	public static boolean 	isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public int			saveList(ArrayList<Hero> saves) {
		try {
			int	lineCount = 0;
			System.out.println("LOAD SAVE:");
			System.out.println("_______________________________________");
			System.out.println("| No. |     Name            |  Level  |");
			System.out.println("|_____|_____________________|_________|");
			for (int i = 0; i < saves.size() && i <= 100; i++) {
				//Print index
				if (i < 10) {
					System.out.print("|   "+i+" |");
				} else if (i > 9 && i < 100) {
					System.out.print("|  "+i+" |");
				} else if (i == 100) {
					System.out.print("| "+i+" |");
				}
				//Print name
				if (saves.get(i).getName().length() < 21) {
					System.out.format("%1$"+ (21 - saves.get(i).getName().length()) + "s", "");
					System.out.print(saves.get(i).getName() + "|");
				} else {
					System.out.print(saves.get(i).getName().substring(0, 20) + "|");
				}
				//Print level
				if (saves.get(i).getLevel() < 10) {
					System.out.println("       "+saves.get(i).getLevel()+" |");
				} else if (saves.get(i).getLevel() > 9 && saves.get(i).getLevel() < 100) {
					System.out.println("      "+saves.get(i).getLevel()+" |");
				} else if (saves.get(i).getLevel() == 100) {
					System.out.println("     "+saves.get(i).getLevel()+" |");
				}
				lineCount = i;
			}
			System.out.println("|_____|_____________________|_________|");

			System.out.println("Please enter the number of the save you would like to load, or BACK to return to the menu.");
			System.out.print(": ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("BACK") && !(isNumeric(input) && Integer.parseInt(input) <= lineCount && Integer.parseInt(input) >= 0)) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print(": ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("BACK"))  {
				return (-1);
			} else {
				return Integer.parseInt(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void			noSaves() {
		System.out.println("You don't have any saves yet! Create a new character to start the adventure!");
	}

	public int 			printLoadedHero(Hero hero) {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_______________________");
		System.out.println("| Name:     " + hero.getName());
		System.out.println("| Level:    " + hero.getLevel());
		System.out.println("| Type:     " + hero.getClassType());
		System.out.println("| HP:       " + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		System.out.println("| Attack:   " + hero.getAttack());
		System.out.println("| Defence:  " + hero.getDefence());
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
		//Try clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		//Go to GameController
		GameController controller = GameController.builder().hero(hero).mapView(new MapGuiView()).build();
		return controller.showMapView();
	}
}

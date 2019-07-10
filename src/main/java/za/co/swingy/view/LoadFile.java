package za.co.swingy.view;

import za.co.swingy.model.characters.Hero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadFile {
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
			System.out.println("LOAD SAVE:");
			System.out.println("_______________________________________");
			System.out.println("| No. |		Name			|  Level  |");
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
					System.out.print("[%1$"+ (21 - saves.get(i).getName().length()) + "s]%n" + saves.get(i).getName() + "|");
				} else {
					System.out.print(saves.get(i).getName().substring(0, 20) + "|");
				}
				//Print level
				if (i < 10) {
					System.out.println("|        "+i+" |");
				} else if (i > 9 && i < 100) {
					System.out.println("|       "+i+" |");
				} else if (i == 100) {
					System.out.println("|      "+i+" |");
				}
			}

			System.out.println("Please enter the number of the save you would like to load, or BACK to return to the menu.");
			System.out.print(": ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (input == null || input.isEmpty() ||
					(!input.equalsIgnoreCase("BACK") && (!isNumeric(input) && Integer.parseInt(input) < i))) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print(": ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("BACK"))  {
				return (-1);
			} else {
				return Integer
			}
			return name;
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("LOAD SAVE:");
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
}

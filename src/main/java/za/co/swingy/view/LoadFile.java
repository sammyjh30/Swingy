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
			int	lineCount = 0;
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
//			while (input == null || input.isEmpty()) {
//				System.out.println("Oops, that's not a valid command! Please try again!");
//				System.out.print(": ");
//				input = bufferedReader.readLine();
//			}
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

}

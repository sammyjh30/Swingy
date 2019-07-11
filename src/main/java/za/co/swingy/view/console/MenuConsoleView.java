package za.co.swingy.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuConsoleView {
	public void			menu() {
		try {
			System.out.println("_________________________________________________");
			System.out.println("|				Welcome to Swingy!				|");
			System.out.println("|			What would you like to do?			|");
			System.out.println("|												|");
			System.out.println("|			NEW   -  Create a new hero			|");
			System.out.println("|			LOAD  -  Load a previous hero		|");
			System.out.println("|			EXIT  -  Leave the game, duh!		|");
			System.out.println("_________________________________________________");
			System.out.print("  Please enter one of the above commands:");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("NEW") && !input.equalsIgnoreCase("LOAD") && !input.equalsIgnoreCase("EXIT")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("  Please enter one of the above commands:");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("NEW")) {
//				return 1;
				//Create Character view
			} else if (input.equalsIgnoreCase("LOAD")) {
//				return 2;
				//Load character view
			} else if (input.equalsIgnoreCase("EXIT")) {
//				return 0;
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return 0;
	}
}

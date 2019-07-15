package za.co.swingy.view.console;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.view.MenuView;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuConsoleView implements MenuView {
	@NotNull(message = "Character Controller cannot be NULL")
	private CharacterController		characterController;

	public MenuConsoleView() {
		this.characterController = CharacterController.builder().menuView(this).createHeroView(new CreateHeroConsoleView()).loadFileView(new LoadFileConsoleView()).mapView(new MapConsoleView()).build();
	}

	public void			menu() {
		try {
			System.out.println("_________________________________________________");
			System.out.println("|				Welcome to Swingy!				|");
			System.out.println("|			What would you like to do?			|");
			System.out.println("|												|");
			System.out.println("|			NEW   -  Create a new hero			|");
			System.out.println("|			LOAD  -  Load a previous hero		|");
			System.out.println("|			EXIT  -  Leave the game, duh!		|");
			System.out.println("|_______________________________________________|");
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
				System.out.println("Call character create function.");
				this.characterController.createNewHero();
			} else if (input.equalsIgnoreCase("LOAD")) {
//				return 2;
				//Load character view
				System.out.println("Call character load function.");
				this.characterController.loadHero();
			} else if (input.equalsIgnoreCase("EXIT")) {
//				return 0;
				System.out.println("Exiting...");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

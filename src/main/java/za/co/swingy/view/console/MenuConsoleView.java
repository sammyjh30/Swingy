package za.co.swingy.view.console;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.view.MenuView;
import za.co.swingy.view.gui.MenuGuiView;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class MenuConsoleView implements MenuView {
	@NotNull(message = "Character Controller cannot be NULL")
	private CharacterController		characterController;

	public MenuConsoleView() {
		this.characterController = CharacterController.builder().menuView(this).createHeroView(new CreateHeroConsoleView()).loadFileView(new LoadFileConsoleView()).build();
	}

	public MenuConsoleView(CharacterController controller) {
//		this.characterController = CharacterController.builder().menuView(this).createHeroView(new CreateHeroConsoleView()).loadFileView(new LoadFileConsoleView()).build();
		this.characterController = controller;
	}

	public void 		resetMenu() {
		this.menu();
	}

	public void			menu() {
//		int ret = 0;
//		while (ret <= 0) {
		try {
			//Clean screen
			System.out.print("\033[H\033[2J");
			System.out.flush();
			//Print Menu
			System.out.println("__________________________________________________");
			System.out.println("|               Welcome to Swingy!               |");
			System.out.println("|           What would you like to do?           |");
			System.out.println("|                                                |");
			System.out.println("|           NEW   -  Create a new hero           |");
			System.out.println("|           LOAD  -  Load a previous hero        |");
			System.out.println("|           EXIT  -  Leave the game, duh!        |");
			System.out.println("|________________________________________________|");
			System.out.print("  Please enter one of the above commands: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("SWITCH") && !input.equalsIgnoreCase("NEW") && !input.equalsIgnoreCase("LOAD") && !input.equalsIgnoreCase("EXIT")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("  Please enter one of the above commands: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("SWITCH")) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				MenuGuiView menuGuiView = new MenuGuiView();
				menuGuiView.menu();
			} else if (input.equalsIgnoreCase("NEW")) {
				//Create Character view
				this.characterController.createNewHero();
			} else if (input.equalsIgnoreCase("LOAD")) {
				//Load character view
				this.characterController.loadHero();
			} else if (input.equalsIgnoreCase("EXIT")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.exit(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

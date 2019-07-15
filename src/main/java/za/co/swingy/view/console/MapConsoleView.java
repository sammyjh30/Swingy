package za.co.swingy.view.console;

import za.co.swingy.controller.GameController;
import za.co.swingy.view.MapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MapConsoleView implements MapView {

	public void					displayMap(char[][] map, int mapSize) {
		System.out.print("\n");
		for (int l = 0; l < mapSize * 2 + 2; l++) {
			System.out.print("_");
		}
		System.out.print("     N");
		System.out.print("\n");
		for (int i = 0; i < mapSize; i++) {
			System.out.print("| ");
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == '.') {
					System.out.print('.');
				} else if (map[i][j] == 'X') {
					System.out.print((char)27 + "[32mX" + (char)27 + "[37m");
				} else if (map[i][j] == 'O') {
					System.out.print((char)27 + "[35mO" + (char)27 + "[37m");
				}
				System.out.print(" ");
			}
			System.out.print("|");
			if (i == 0) {
				System.out.print("    Λ");
			} else if (i == 1) {
				System.out.print(" W<-|->E");
			} else if (i == 2) {
				System.out.print("    V");
			} else if (i == 3) {
				System.out.print("    S");
			}
			System.out.print("\n");
		}
		System.out.print("|");
		for (int l = 0; l < mapSize * 2 + 1; l++) {
			System.out.print("_");
		}
		System.out.print("|\n");
	}

	public void					showOptions() {
		System.out.println("Your goal is to leave all the maps, or level up lo level 6.");
		System.out.println("You can move: " + (char)27 + "[32mNORTH" + (char)27 + "[37m, " + (char)27 + "[32mSOUTH" + (char)27 + "[37m, "
				+ (char)27 + "[32mEAST" + (char)27 + "[37m or " + (char)27 + "[32mWEST" + (char)27 + "[37m");
		System.out.println("Or you can go to your " + (char)27 + "[32mINVENTORY" + (char)27 + "[37m or " + (char)27 + "[32mSAVE" + (char)27 + "[37m to save and exit this game.");
	}

	public void					display(GameController controller) {
		int stage = 0;
		while  (stage >= 0) {
			displayMap(controller.getMap(), controller.getMapSize());
			try {
				InputStreamReader streamReader = new InputStreamReader(System.in);
				BufferedReader bufferedReader = new BufferedReader(streamReader);
				showOptions();
				System.out.print("Please enter a command: ");
				String input = bufferedReader.readLine();
				while (!input.equalsIgnoreCase("NORTH") && !input.equalsIgnoreCase("SOUTH") &&
						!input.equalsIgnoreCase("EAST") && !input.equalsIgnoreCase("WEST") &&
						!input.equalsIgnoreCase("INVENTORY") && !input.equalsIgnoreCase("SAVE")){
					System.out.println("Oops, that's not a valid command! Please try again!");
					System.out.print("Please enter a command: ");
					input = bufferedReader.readLine();
				}
				if (input.equalsIgnoreCase("NORTH")) {
					System.out.println("Move the hero north");
				} else if (input.equalsIgnoreCase("SOUTH")) {
					System.out.println("Move the hero south");
				} else if (input.equalsIgnoreCase("EAST")) {
					System.out.println("Move the hero east");
				} else if (input.equalsIgnoreCase("WEST")) {
					System.out.println("Move the hero west");
				} else if (input.equalsIgnoreCase("INVENTORY")) {
					System.out.println("Open inventory");
				} else if (input.equalsIgnoreCase("SAVE")) {
					System.out.println("Save the game");
				}
				//Clean screen
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (stage == -1) {
			//Close game
		} else if (stage == -2) {
			//Hero died
		}
	}
}

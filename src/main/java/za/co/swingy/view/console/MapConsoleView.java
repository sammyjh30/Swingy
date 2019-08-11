package za.co.swingy.view.console;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@Setter
public class MapConsoleView implements MapView {
	private GameController		controller;

	public void	youWin() {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|      " + (char)27 + "[32m" + "Congratulations! You win!" + "\033[0m" + "        |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");
		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.returnToMenu();
	}

	public void	levelUp() {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|   " + (char)27 + "[32m" + "Congratulations! You leveled up!" + "\033[0m" + "    |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");
		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.updateMap();
		this.controller.getMapView().display(this.controller);

//		this.controller.getGameController().removeEnemy(this.controller.getEnemy());
//		this.controller.victory();
	}

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
					System.out.print((char)27 + "[32mX" +  "\033[0m");
				} else if (map[i][j] == 'O') {
					System.out.print((char)27 + "[35mO" +  "\033[0m");
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

	private void				showHero(Hero hero) {
		if (hero.getName().length() > 10) {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName().substring(0, 10) + "\033[0m");
		} else {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName() + "\033[0m");
		}
		System.out.println("| Class: " + (char)27 + "[32m" + hero.getClassType() + "\033[0m");
		System.out.println("| Level: " + (char)27 + "[32m" + hero.getLevel() + "\033[0m");
		System.out.println("| XP:    " + (char)27 + "[32m" + hero.getExperience() + "/" + hero.getXpRequired() +  "\033[0m");
		if (hero.getEquippedHelm() == null) {
			System.out.println("| HP:    " + (char)27 + "[32m" + hero.getHitPoints() + "/" + hero.getMaxHitPoints() + "\033[0m");
		} else {
			System.out.println("| HP:    " + (char)27 + "[32m" + (hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "\033[0m");
		}
		if (hero.getEquippedWeapon() == null) {
			System.out.println("| ATT:   " + (char)27 + "[32m" + hero.getAttack() + "\033[0m");
		} else {
			System.out.println("| ATT:   " + (char)27 + "[32m" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()) + "\033[0m");
		}
		if (hero.getEquippedArmor() == null) {
			System.out.println("| DEF:   " + (char)27 + "[32m" + (hero.getDefence()) + "\033[0m");
		} else {
			System.out.println("| DEF:   " + (char)27 + "[32m" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()) + "\033[0m");
		}
		System.out.println();
	}

	private void				showOptions() {
		System.out.println("Your goal is to level up lo level 6.");
		System.out.println("You can move: " + (char)27 + "[32mNORTH" +  "\033[0m, " + (char)27 + "[32mSOUTH" + (char)27 + "[37m, "
				+ (char)27 + "[32mEAST" +  "\033[0m or " + (char)27 + "[32mWEST" +  "\033[0m");
		System.out.println("Or you can go to your " + (char)27 + "[32mINVENTORY" +  "\033[0m or " + (char)27 + "[32mSAVE" +  "\033[0m to save and exit this game.");
	}

	public void					death() {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|           " + (char)27 + "[31m" + "THE HERO HAS DIED?!" + "\033[0m" + "         |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");

		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.returnToMenu();
	}

	public void					runAway() {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|           " + (char)27 + "[34m" + "THE HERO RAN AWAY!" + "\033[0m" + "          |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");

		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.showMapView();
	}

	public void					falseAlarm(int x, int y) {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|              " + (char)27 + "[34m" + "FALSE ALARM!" + "\033[0m" + "             |");
		System.out.println("|    " + (char)27 + "[34m" + "IT WAS JUST A CARDBOARD CUTOUT!" + "\033[0m" + "    |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");

		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.moveHero(x,y);
	}

	public void					success(int x, int y) {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|  " + (char)27 + "[32m" + "THE HERO DEFEATED THEIR OPPONENT!" + "\033[0m" + "    |");
		System.out.println("|                                       |");
		System.out.println("|_______________________________________|");

		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.controller.moveHero(x,y);
	}

	public void					createEncounter(Enemy enemy) {
		EncounterConsoleView encounterConsoleView = new EncounterConsoleView(this.controller);
		encounterConsoleView.getController().startNewEncounter(enemy);
	}

	public void					display(GameController controller) {
		this.controller = controller;
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
		this.showHero(controller.getHero());
		this.displayMap(controller.getMap(), controller.getMapSize());
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
				controller.checkForCombat(0, -1);
			} else if (input.equalsIgnoreCase("SOUTH")) {
				controller.checkForCombat(0, 1);
			} else if (input.equalsIgnoreCase("EAST")) {
				controller.checkForCombat(1, 0);
			} else if (input.equalsIgnoreCase("WEST")) {
				controller.checkForCombat(-1, 0);
			} else if (input.equalsIgnoreCase("INVENTORY")) {
				InventoryConsoleView inventoryConsoleView = new InventoryConsoleView(controller);
				inventoryConsoleView.display();
				//then call the mapviewDisplay()
			} else if (input.equalsIgnoreCase("SAVE")) {
				controller.saveGame();
			}
			//Clean screen
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

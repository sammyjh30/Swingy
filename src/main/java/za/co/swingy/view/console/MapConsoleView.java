package za.co.swingy.view.console;

import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.pow;

public class MapConsoleView implements MapView {

	//  RESET
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

	private void					showHero(Hero hero) {
		if (hero.getName().length() > 10) {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName().substring(0, 10) + "\033[0m");
		} else {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName() + "\033[0m");
		}
		System.out.println("| Class: " + (char)27 + "[32m" + hero.getClassType() + "\033[0m");
		System.out.println("| Level: " + (char)27 + "[32m" + hero.getLevel() + "\033[0m");
		System.out.println("| XP:    " + (char)27 + "[32m" + hero.getExperience() + "/" + (hero.getLevel() * 1000 -  ((int)pow(hero.getLevel() - 1, 2) * 450)) +  "\033[0m");
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

	public void					showOptions() {
		System.out.println("Your goal is to leave all the maps, or level up lo level 6.");
		System.out.println("You can move: " + (char)27 + "[32mNORTH" +  "\033[0m, " + (char)27 + "[32mSOUTH" + (char)27 + "[37m, "
				+ (char)27 + "[32mEAST" +  "\033[0m or " + (char)27 + "[32mWEST" +  "\033[0m");
		System.out.println("Or you can go to your " + (char)27 + "[32mINVENTORY" +  "\033[0m or " + (char)27 + "[32mSAVE" +  "\033[0m to save and exit this game.");
	}

	public int					checkForCombat(GameController controller, int x, int y) {
		int newX = controller.getHero().getXPos() + x;
		int newY = controller.getHero().getYPos() + y;
		if (newX < 0 || newY < 0 || newX > controller.getMapSize() || newY > controller.getMapSize()) {
			System.out.println("Change the map!");
//			return;
		} else if (controller.getMap()[newY][newX] == 'O') {
			System.out.println("COMBAT!");
			//Create an encounter view
			Enemy enemy = controller.getCombatEnemy(newX,newY);
			if (enemy == null) {
				System.out.println("False alarm! It was just a cardboard cutout!");
				return 1;
			} else {
//Create view and encounter
				EncounterConsoleView encounterConsoleView = new EncounterConsoleView(controller.getHero());
				int ret = encounterConsoleView.getController().startNewEncounter(enemy);
				if (ret == -1) {
					this.death();
					return -2;
				} else if ( ret == 0) {
					this.runAway();
				} else if (ret == 1) {
					this.success();
					controller.removeEnemy(enemy);
					controller.moveHero(x,y);
				}
			}
			//Use controller to get enemy and index
			//Pass to Encounter mode
		} else {
			controller.moveHero(x, y);
		}
		return 1;
	}

	public void					death() {
		System.out.println("\n\n_________________________________________");
		System.out.println("|										|");
		System.out.println("|										|");
		System.out.println("|			" + (char)27 + "[31m" + "THE HERO HAS DIED?!" + "\033[0m" + "			|");
		System.out.println("|										|");
		System.out.println("|_______________________________________|\n\n");
	}

	public void					runAway() {
		System.out.println("\n\n_________________________________________");
		System.out.println("|										|");
		System.out.println("|										|");
		System.out.println("|			" + (char)27 + "[34m" + "THE HERO RAN AWAY! " + "\033[0m" + "			|");
		System.out.println("|										|");
		System.out.println("|_______________________________________|\n\n");
	}

	public void					success() {
		System.out.println("\n\n_________________________________________");
		System.out.println("|										|");
		System.out.println("|										|");
		System.out.println("|	" + (char)27 + "[32m" + "THE HERO DEFEATED THEIR OPPONENT!" + "\033[0m" + "	|");
		System.out.println("|										|");
		System.out.println("|_______________________________________|\n\n");
	}

	public int					display(GameController controller) {
		int stage = 0;
		while  (stage >= 0) {
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
					System.out.println("Move the hero north");
					stage = this.checkForCombat(controller,0, -1);
				} else if (input.equalsIgnoreCase("SOUTH")) {
					System.out.println("Move the hero south");
					stage = this.checkForCombat(controller,0, 1);
				} else if (input.equalsIgnoreCase("EAST")) {
					System.out.println("Move the hero east");
					stage = this.checkForCombat(controller,1, 0);
				} else if (input.equalsIgnoreCase("WEST")) {
					System.out.println("Move the hero west");
					stage = this.checkForCombat(controller,-1, 0);
				} else if (input.equalsIgnoreCase("INVENTORY")) {
					System.out.println("Open inventory");
				} else if (input.equalsIgnoreCase("SAVE")) {
					System.out.println("Save the game");
					controller.saveGame();
					stage = -1;
				}
				//Clean screen
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		if (stage == -1) {
//			//Close game
//		} else if (stage == -2) {
//			//Hero died
//		}
		return stage;
	}
}

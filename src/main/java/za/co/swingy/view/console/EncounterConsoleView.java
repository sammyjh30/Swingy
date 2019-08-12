package za.co.swingy.view.console;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.EncounterController;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.EncounterView;
import za.co.swingy.view.gui.*;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Getter
@Setter
public class EncounterConsoleView implements EncounterView {
	@NotNull
	private EncounterController		controller;
	@NotNull
	public GameController			gameController;


	public EncounterConsoleView(GameController controller) {
		this.gameController = controller;
		this.controller = EncounterController.builder().encounterView(this).hero(controller.getHero()).gameController(controller).build();
	}

	public EncounterConsoleView(EncounterController controller) {
		this.gameController = controller.getGameController();
		this.controller = controller;
	}

	private void					showHero(Hero hero) {
		if (hero.getName().length() > 10) {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName().substring(0, 10) + "\033[0m");
		} else {
			System.out.println("| Name:  " + (char)27 + "[32m" + hero.getName() + "\033[0m");
		}
		System.out.println("| Class: " + (char)27 + "[32m" + hero.getClassType() + "\033[0m");
		System.out.println("| Level: " + (char)27 + "[32m" + hero.getLevel() + "\033[0m");
		if (this.controller.getHero().getEquippedHelm() == null) {
			if (hero.getHitPoints() < 0) {
				System.out.println("| HP:    " + (char) 27 + "[32m0/" + hero.getMaxHitPoints() + "\033[0m");
			} else {
				System.out.println("| HP:    " + (char)27 + "[32m" + hero.getHitPoints() + "/" + hero.getMaxHitPoints() + "\033[0m");
			}
		} else {
			if ((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) < 0) {
				System.out.println("| HP:    " + (char)27 + "[32m0/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "\033[0m");
			} else {
				System.out.println("| HP:    " + (char) 27 + "[32m" + (hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
						"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "\033[0m");
			}
		}
		if (this.controller.getHero().getEquippedWeapon() == null) {
			System.out.println("| ATT:   " + (char)27 + "[32m" + hero.getAttack() + "\033[0m");
		} else {
			System.out.println("| ATT:   " + (char)27 + "[32m" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()) + "\033[0m");
		}
		if (this.controller.getHero().getEquippedArmor() == null) {
			System.out.println("| DEF:   " + (char)27 + "[32m" + (hero.getDefence()) + "\033[0m");
		} else {
			System.out.println("| DEF:   " + (char)27 + "[32m" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()) + "\033[0m");
		}
		System.out.println();
	}

	private void					showEnemy(Enemy enemy) {
		if (enemy.getEnemyName().length() > 10) {
			System.out.println("| Name:  " + (char)27 + "[35m" + enemy.getEnemyName().substring(0, 10) + "the" + enemy.getEnemyType() + "\033[0m");
		} else {
			System.out.println("| Name:  " + (char)27 + "[35m" + enemy.getEnemyName() + " the " + enemy.getEnemyType() + "\033[0m");
		}
		System.out.println("| Level: " + (char)27 + "[35m" + enemy.getLevel() + "\033[0m");
		if ( enemy.getHitPoints() < 0) {
			System.out.println("| HP:    " + (char)27 + "[35m0/" + enemy.getMaxHitPoints() + "\033[0m");
		} else {
			System.out.println("| HP:    " + (char)27 + "[35m" + enemy.getHitPoints() + "/" + enemy.getMaxHitPoints() + "\033[0m");
		}
		System.out.println("| ATT:   " + (char)27 + "[35m" + enemy.getAttack() + "\033[0m");
		System.out.println("| DEF:   " + (char)27 + "[35m" + (enemy.getDefence()) + "\033[0m");
	}

	public void						success() {
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
		this.controller.getGameController().removeEnemy(this.controller.getEnemy());
		this.controller.victory();
	}
	public void 					itemDropFailed() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("				ITEM DROP FAILED!");
		System.out.println("		It seems your inventory is full!");
		System.out.println("	You will need to clear out some of your inventory.");
		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.controller.checkLevel();
	}
	public void						itemDrop(String item, int boost) {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

//		String drops[] = {"Weapon", "Armor", "Helm", "Inventory", "Health"};
		System.out.println("			ITEM DROP!");
		if (item.equalsIgnoreCase("Inventory")) {
			System.out.println("	The enemy dropped an Inventory attachment with a +" + boost + " boost.");
		} else if (item.equalsIgnoreCase("Health")) {
			System.out.println("	The enemy dropped a small health remedy of +" + boost + "HP.");
		}
		System.out.println("	Would you like to take it?");
		try {
			System.out.print("Please enter either YES or NO: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("YES") && !input.equalsIgnoreCase("NO")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter either YES or NO: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("YES")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				if (item.equalsIgnoreCase("Inventory")) {
					this.controller.addInventoryBoost(boost);
				} else if (item.equalsIgnoreCase("Health")) {
					this.controller.addHealthBoost(boost);
				}
			} else if (input.equalsIgnoreCase("NO")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.checkLevel();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void					armorDrop(Armor armor) {
		System.out.println("			ITEM DROP!");
		System.out.println("	The enemy dropped an Armor: " + armor.getName());
		System.out.println("	Would you like to take it?");
		try {
			System.out.print("Please enter either YES or NO: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("YES") && !input.equalsIgnoreCase("NO")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter either YES or NO: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("YES")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.addArmor(armor);
			} else if (input.equalsIgnoreCase("NO")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.checkLevel();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void					weaponDrop(Weapon weapon) {
		System.out.println("			ITEM DROP!");
		System.out.println("	The enemy dropped a Weapon: " + weapon.getName());
		System.out.println("	Would you like to take it?");
		try {
			System.out.print("Please enter either YES or NO: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("YES") && !input.equalsIgnoreCase("NO")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter either YES or NO: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("YES")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.addWeapon(weapon);
			} else if (input.equalsIgnoreCase("NO")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.checkLevel();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void					helmDrop(Helm helm){
		System.out.println("			ITEM DROP!");
		System.out.println("	The enemy dropped a Helm: " + helm.getName());
		System.out.println("	Would you like to take it?");
		try {
			System.out.print("Please enter either YES or NO: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("YES") && !input.equalsIgnoreCase("NO")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter either YES or NO: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("YES")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.addHelm(helm);
			} else if (input.equalsIgnoreCase("NO")) {
				//Clear screen
				System.out.print("\033[H\033[2J");
				System.out.flush();
				this.controller.checkLevel();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void						display() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		//How Helmet and hitpoints will work: hitpoints can go into negative
		//Check if hitpoint + helmet <= 0 (e.g. if -3 + 5 <= 0 die, else continue
		System.out.println("Your Hero Stats:");
		this.showHero(this.controller.getHero());
		System.out.println("Your Opponent's Stats:");
		this.showEnemy(this.controller.getEnemy());
		System.out.println("\n____BATTLE HISTORY____");
		System.out.println(this.controller.getRoundUpdate());
		System.out.println("______________________\n");
		try {
			System.out.println("You have 3 choices! You can try to RUN, or you can FIGHT...\nor you can SIMULATE so the game will fight for you (lazy much?)");
			System.out.print("Please enter in a command: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("SWITCH") && !input.equalsIgnoreCase("RUN") && !input.equalsIgnoreCase("FIGHT") && !input.equalsIgnoreCase("SIMULATE")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter in a command: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("SWITCH")) {
				//CreateHero
				CreateHeroGuiView createHeroGuiView = new CreateHeroGuiView(this.controller.getGameController().getCharacterController());
				this.controller.getGameController().getCharacterController().setCreateHeroView(createHeroGuiView);

				//LoadHero
				LoadFileGuiView loadFileGuiView = new LoadFileGuiView(this.controller.getGameController().getCharacterController());
				this.controller.getGameController().getCharacterController().setLoadFileView(loadFileGuiView);

				//MenuView
				MenuGuiView menuGuiView = new MenuGuiView(this.controller.getGameController().getCharacterController());
				this.controller.getGameController().getCharacterController().setMenuView(menuGuiView);

				//MapView
				MapGuiView mapGuiView = new MapGuiView(this.controller.getGameController());
				this.controller.getGameController().setMapView(mapGuiView);

				//EncounterView
				EncounterGuiView encounterGuiView = new EncounterGuiView(controller);
				encounterGuiView.initFrame();
				encounterGuiView.getFrame().setContentPane(encounterGuiView.getMainPanel());
				encounterGuiView.getFrame().pack();
				encounterGuiView.getMainPanel().setVisible(true);
				controller.setEncounterView(encounterGuiView);

				System.out.print("\033[H\033[2J");
				System.out.flush();

				this.controller.round();
			} else {
				if (input.equalsIgnoreCase("RUN")) {
					this.controller.run();
				} else if (input.equalsIgnoreCase("FIGHT")) {
					this.controller.fight();
				} else if (input.equalsIgnoreCase("SIMULATE")) {
					this.controller.simulate();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void					runFailed() {
		//Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		System.out.println("_________________________________________");
		System.out.println("|                                       |");
		System.out.println("|                                       |");
		System.out.println("|       " + (char)27 + "[34m" + "THE HERO TRIED TO RUN AWAY" + "\033[0m" + "      |");
		System.out.println("|               " + (char)27 + "[34m" + "BUT FAILED!" + "\033[0m" + "             |");
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
		this.controller.round();
	}

	public void					battleHistory() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("			THE BATTLE IS OVER!");
		this.showHero(this.controller.getHero());
		this.showEnemy(this.controller.getEnemy());
		System.out.print(this.controller.getRoundUpdate());

		//Implement "Press ENTER to continue"
		System.out.println("Press \"ENTER\" to continue...");
		try {
			int read = System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("Random drop 1");
		// try{Thread.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
		this.controller.randomDrop();
	}
}

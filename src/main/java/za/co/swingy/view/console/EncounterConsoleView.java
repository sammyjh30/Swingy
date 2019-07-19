package za.co.swingy.view.console;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.EncounterController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.EncounterView;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@Setter
public class EncounterConsoleView implements EncounterView {
	@NotNull
	private EncounterController controller;

	public EncounterConsoleView(Hero hero) {
		this.controller = EncounterController.builder().encounterView(this).hero(hero).build();
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
			System.out.println("| HP:    " + (char)27 + "[32m" + hero.getHitPoints() + "/" + hero.getMaxHitPoints() + "\033[0m");
		} else {
			System.out.println("| HP:    " + (char)27 + "[32m" + (hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "\033[0m");
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
		System.out.println("| HP:    " + (char)27 + "[35m" + enemy.getHitPoints() + "/" + enemy.getMaxHitPoints() + "\033[0m");
		System.out.println("| ATT:   " + (char)27 + "[35m" + enemy.getAttack() + "\033[0m");
		System.out.println("| DEF:   " + (char)27 + "[35m" + (enemy.getDefence()) + "\033[0m");
	}

	public int						display() {
		//How Helmet and hitpoints will work: hitpoints can go into negative
		//Check if hitpoint + helmet <= 0 (e.g. if -3 + 5 <= 0 die, else continue

		System.out.println("								COMBAT!");
		System.out.println("						An enemy has appeared!");
		System.out.println("Your Hero Stats:");
		this.showHero(this.controller.getHero());
		System.out.println("Your Opponent's Stats:");
		this.showEnemy(this.controller.getEnemy());
		try {
			System.out.println("You have 3 choices! You can try to RUN, or you can FIGHT...\nor you can SIMULATE so the game will fight for you (lazy much?)");
			System.out.print("Please enter in a command: ");
			InputStreamReader streamReader = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(streamReader);
			String input = bufferedReader.readLine();
			while (!input.equalsIgnoreCase("RUN") && !input.equalsIgnoreCase("FIGHT") && !input.equalsIgnoreCase("SIMULATE")) {
				System.out.println("Oops, that's not a valid command! Please try again!");
				System.out.print("Please enter in a command: ");
				input = bufferedReader.readLine();
			}
			if (input.equalsIgnoreCase("RUN")) {
				return 0;
			} else if (input.equalsIgnoreCase("FIGHT")) {
				return 1;
			} else if (input.equalsIgnoreCase("SIMULATE")) {
				return 2;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void					fight(String attacker, String defender, int success, int damage) {
		if (success == 1) {
			System.out.println(attacker + " attacked " + defender + " with " + damage + " points of damage!");
		} else if (success == 0) {
			System.out.println(attacker + " tried to attack " + defender + " but missed!");
		}
	}

	public void					run() {

	}

	public void					simulate() {

	}
}

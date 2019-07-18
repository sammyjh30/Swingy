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
			System.out.println("| Name: " + hero.getName().substring(0, 10));
		} else {
			System.out.println("| Name: " + hero.getName());
		}
		System.out.println("| Class: " + hero.getClassType());
		System.out.println("| Level: " + hero.getLevel());
		if (this.controller.getHero().getEquippedHelm() == null) {
			System.out.println("| HP:    " + hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		} else {
			System.out.println("| HP:    " + (hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
		}
		if (this.controller.getHero().getEquippedWeapon() == null) {
			System.out.println("| ATT " + hero.getAttack());
		} else {
			System.out.println("| ATT " + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}
		if (this.controller.getHero().getEquippedArmor() == null) {
			System.out.println("| DEF " + (hero.getDefence()));
		} else {
			System.out.println("| DEF " + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}
	}

	private void					showEnemy(Enemy enemy) {
		if (enemy.getEnemyName().length() > 10) {
			System.out.println("| Name: " + enemy.getEnemyName().substring(0, 10) + "the" + enemy.getEnemyType());
		} else {
			System.out.println("| Name: " + enemy.getEnemyName() + " the " + enemy.getEnemyType());
		}
		System.out.println("| Level: " + enemy.getLevel());
		System.out.println("| HP:    " + enemy.getHitPoints() + "/" + enemy.getMaxHitPoints());
		System.out.println("| ATT " + enemy.getAttack());
		System.out.println("| DEF " + (enemy.getDefence()));
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

	public void					fight() {

	}

	public void					run() {

	}

	public void					simulate() {

	}
}

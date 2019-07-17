package za.co.swingy.view.console;

import za.co.swingy.controller.EncounterController;
import za.co.swingy.view.EncounterView;

import javax.validation.constraints.NotNull;

public class EncounterConsoleView implements EncounterView {
	@NotNull
	private EncounterController controller;

	// Builder
	public static class 		EncounterConsoleViewBuilder {
		private EncounterController controller;

		public EncounterConsoleViewBuilder controller(EncounterController controller) {
			this.controller = controller;
			return this;
		}
	}

	public int						display() {
		//How Helmet and hitpoints will work: hitpoints can go into negative
		//Check if hitpoint + helmet <= 0 (e.g. if -3 + 5 <= 0 die, else continue

		System.out.println(								"COMBAT!");
		System.out.println("| Name: " + this.controller.getHero().getName().substring(0, 10));
		System.out.println("| Class: " + this.controller.getHero().getClassType());
		System.out.println("| Level: " + this.controller.getHero().getLevel());
		if (this.controller.getHero().getEquippedHelm() == null) {
			System.out.println("| HP:    " + this.controller.getHero().getHitPoints() +
				"/" + this.controller.getHero().getMaxHitPoints());
		System.out.println("| ATT " + (this.controller.getHero().getAttack() + this.controller.getHero().getEquippedWeapon().getAttackIncrease()));
		System.out.println("| DEF " + (this.controller.getHero().getDefence() +
				this.controller.getHero().getEquippedArmor().getDefenceIncrease()));

	}
}

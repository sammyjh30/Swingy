package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.EncounterView;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class EncounterController {
	@NotNull
	private Hero hero;
	@NotNull
	private Enemy enemy;
	@NotNull
	private EncounterView encounterView;

	// Builder
	public static class 		EncounterControllerBuilder {
		private Hero hero;
		private EncounterView encounterView;

		public EncounterControllerBuilder		hero(Hero hero) {
			this.hero = hero;
			return this;
		}

		public EncounterControllerBuilder			encounterView(EncounterView view) {
			this.encounterView = view;
			return this;
		}

	}

	public int				startNewEncounter(Enemy enemy) {
		this.setEnemy(enemy);
		//Show encounter and get input
		String input;
		if (input)
	}
}

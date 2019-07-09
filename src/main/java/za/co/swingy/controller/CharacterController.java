package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.CreateHero;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CharacterController {
	@NotNull
	private CreateHero		createHeroView;
	private Hero			hero;

	// Builder
	public static class CharacterControllerBuilder {
		private CreateHero		createHeroView;

		public CharacterControllerBuilder		createHeroView() {
			this.createHeroView = new CreateHero();
			return this;
		}
	}
		public void			createNewHero() {
		String name = this.createHeroView.promptName();
		String type = this.createHeroView.promptType();
		this.hero = Hero.builder().classType(type).name(name).inventory().build();
		this.hero.starterHero();
		this.createHeroView.printHeroStatus(this.hero);
	}
}

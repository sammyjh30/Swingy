package za.co.swingy.controller;


import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MenuView;

public class GameController {
	private MenuView		menuView;
	private Hero hero;

	// Builder
	public static class GameControllerBuilder {
		private MenuView		menuView;

		public GameControllerBuilder		menuView() {
			this.menuView = new MenuView();
			return this;
		}
	}

	// Map
	// Array Enemies
	// Hero

	//Menu() CREATE, LOAD or EXIT
	public void			menu() {
		int ret = this.menuView.menu();
		if (ret == 0) {
			return;
		} else if (ret ==)
	}

	// Place map (enemies, hero, holes)
	// Set up starter commands (Move, inventory, save and quit)
	// Set up interaction commands (Fight, Run)
	// Set up victory commands
	// Set up failure command
	// Set up level up (Map increase, enemy increase)

}

package za.co.swingy.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MenuView;


import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MenuView;

import java.util.ArrayList;
@Getter
@Setter
@Builder
public class GameController {
//	private MenuView			menuView;
	private Hero				hero;
	private char[]				map;
	private int					mapSize;
	private ArrayList<Enemy>	enemies;

	// Builder
	public static class 		GameControllerBuilder {
		private Hero				hero;
		private char[]				map;
		private int					mapSize;
		private ArrayList<Enemy>	enemies;

		public GameControllerBuilder		hero(Hero newHero) {
			this.hero = newHero;
			this.mapSize = (newHero.getLevel() - 1) * 5 + 10 - (newHero.getLevel() % 2);
			this.map = new char[this.mapSize];
			return this;
		}
	}

	// Map
	// Array Enemies
	// Hero

	//Menu() CREATE, LOAD or EXIT
	public void			menu() {
//		int ret = this.menuView.menu();
//		if (ret == 0) {
//			return;
//		} else if (ret ==)
	}

	// Place map (enemies, hero, holes)
	// Set up starter commands (Move, inventory, save and quit)
	// Set up interaction commands (Fight, Run)
	// Set up victory commands
	// Set up failure command
	// Set up level up (Map increase, enemy increase)

}

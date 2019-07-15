package za.co.swingy.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MapView;
import za.co.swingy.view.MenuView;


import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MenuView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@Builder
public class GameController {
//	private MenuView			menuView;
	private Hero				hero;
	private char[][]			map;
	private int					mapSize;
	private ArrayList<Enemy>	enemies;
	@NotNull
	private MapView				mapView;

	// Builder
	public static class 		GameControllerBuilder {
		private Hero				hero;
		private char[][]			map;
		private int					mapSize;
		private ArrayList<Enemy>	enemies;
		private MapView				mapView;

		public GameControllerBuilder		hero(Hero hero) {
			this.hero = hero;
			this.mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
			this.map = new char[this.mapSize][this.mapSize];
			for (int i = 0; i < this.mapSize; i++) {
				Arrays.fill(this.map[i], '.');
			}
			//Place player
			this.map[hero.getYPos()][hero.getXPos()] = 'X';
			//Get enemies
			int maxEnemies = (hero.getLevel() + 1) * 5 - (hero.getLevel() %2);
			System.out.println("Max Enemies = " + maxEnemies);
			return this;
		}

		public GameControllerBuilder		mapView(MapView mapView) {
			this.mapView = mapView;
			return this;
		}
	}

	public void				showMapView() {
		this.mapView.display(this);
	}

	// Map
	// Array Enemies
	// Hero

	//Menu() CREATE, LOAD or EXIT
//	public void			menu() {
//		int ret = this.menuView.menu();
//		if (ret == 0) {
//			return;
//		} else if (ret ==)
//	}

	// Place map (enemies, hero, holes)
	// Set up starter commands (Move, inventory, save and quit)
	// Set up interaction commands (Fight, Run)
	// Set up victory commands
	// Set up failure command
	// Set up level up (Map increase, enemy increase)

}

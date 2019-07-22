package za.co.swingy.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.MapView;
import za.co.swingy.view.MenuView;


import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MenuView;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static za.co.swingy.controller.CharacterController.isNumeric;

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
			System.out.println("GameController HERO!");
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
			Enemy enemy;
			this.enemies = new ArrayList<Enemy>();
			Integer linePlaces[] = new Integer[maxEnemies];
			Arrays.fill(linePlaces, -1);
			Random rand = new Random();
			Integer linePos = 0;
			for (int i = 0; i < maxEnemies; i++) {
				enemy = Enemy.builder().enemyName().enemyType().build();
				enemy.generateEnemy(hero);
				linePos = rand.nextInt(this.mapSize* this.mapSize);
				int positionX = linePos % (this.mapSize);
				int positionY = linePos / (this.mapSize);
				while (Arrays.asList(linePlaces).contains(linePos) || (positionX == hero.getXPos() && positionY == hero.getYPos())) {
					linePos = rand.nextInt(this.mapSize* this.mapSize);
					positionX = linePos % (this.mapSize);
					positionY = linePos / (this.mapSize);
				}
				linePlaces[i] = linePos;
				enemy.setXPos(positionX);
				enemy.setYPos(positionY);
				this.map[positionY][positionX] = 'O';
				enemies.add(enemy);
			}
			return this;
		}

		public GameControllerBuilder		mapView(MapView mapView) {
			this.mapView = mapView;
			return this;
		}
	}

	public int				showMapView() {
		return this.mapView.display(this);
	}

	// Set up starter commands (Move, inventory, save and quit)
	// Set up interaction commands (Fight, Run)
	// Set up victory commands
	// Set up failure command
	// Set up level up (Map increase, enemy increase)

	public void			saveGame() {
		String st;
		try {
			File file = new File("resources/saves.txt");
			if (file.canRead()) {
				System.out.println("Reading file");
				//To load
				BufferedReader br = new BufferedReader(new FileReader(file));
				try {
					int save = 0;
					String line[];
					while ((st = br.readLine()) != null) {
						line = st.split("\\|");
						if (isNumeric(line[0])){
							if (Integer.parseInt(line[0]) > save) {
								save = Integer.parseInt(line[0]);
							}
						}
					}

					//Getting information to add to the save file
					save++;
					String characterDetails = save + "|" + this.hero.getName() + "|" + this.hero.getClassType() +
							"|" + this.hero.getLevel() + "|" + this.hero.getAttack() +
							"|" + this.hero.getDefence() + "|" + this.hero.getHitPoints() +
							"|" + this.hero.getMaxHitPoints() + "|" + this.hero.getExperience() +
							"|" + this.hero.getXPos() + "|" + this.hero.getYPos();
					String inventoryDetails = save +  "|Inventory|" + this.hero.getInventory().getUsedSlots() +
							"|" + this.hero.getInventory().getMaxSlots() +
							"|" + this.hero.getInventory().getEquippedWeaponIndex() + "|" + this.hero.getInventory().getEquippedArmorIndex() +
							"|" + this.hero.getInventory().getEquippedHelmIndex();
					String weaponDetails = save + "|Weapons";
					for (int i = 0; i < this.hero.getInventory().getWeapons().size(); i++) {
						Weapon weapon = this.hero.getInventory().getWeapons().get(i);
						weaponDetails = weaponDetails + "|" + i + "|" + weapon.getName() + "|" + weapon.getLevel() + "|" + weapon.getAttackIncrease();
					}
					String armorDetails = save + "|Armors";
					for (int i = 0; i < this.hero.getInventory().getArmors().size(); i++) {
						Armor armor = this.hero.getInventory().getArmors().get(i);
						armorDetails = armorDetails + "|" + i + "|" + armor.getName() + "|" + armor.getLevel() + "|" + armor.getDefenceIncrease();
					}
					String helmDetails = save + "|Helms";
					for (int i = 0; i < this.hero.getInventory().getHelms().size(); i++) {
						Helm helm = this.hero.getInventory().getHelms().get(i);
						helmDetails = helmDetails + "|" + i + "|" + helm.getName() + "|" + helm.getLevel() + "|" + helm.getHitPointIncrease();
					}
					//Add to file
					BufferedWriter writer = new BufferedWriter(new FileWriter("resources/saves.txt", true));
					writer.write(characterDetails + '\n');
					writer.write(inventoryDetails + '\n');
					writer.write(weaponDetails + '\n');
					writer.write(armorDetails + '\n');
					writer.write(helmDetails + '\n');
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch ( FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Enemy			getCombatEnemy(int x, int y) {
		for (int i = 0; i < this.enemies.size(); i++) {
			if (this.enemies.get(i).getXPos() == x && this.enemies.get(i).getYPos() == y) {
				return this.enemies.get(i);
			}
		}
		return null;
	}

	public void				removeEnemy(Enemy enemy) {
		if (this.enemies.contains(enemy)) {
			this.map[enemy.getYPos()][enemy.getXPos()] = '.';
			this.enemies.remove(enemy);
		}
	}

	public void			moveHero(int x, int y) {
		int newX = this.hero.getXPos() + x;
		int newY = this.hero.getYPos() + y;
		if (newX < 0 || newY < 0 || newX > this.mapSize || newY > this.mapSize) {
			System.out.println("Change the map!");
		} else {
			this.map[this.hero.getYPos()][this.hero.getXPos()] = '.';
			this.map[newY][newX] = 'X';
			this.hero.setXPos(newX);
			this.hero.setYPos(newY);
		}
	}

}

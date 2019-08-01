package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.EncounterView;

import javax.validation.constraints.NotNull;
import java.util.Random;

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
	@NotNull
	private GameController	gameController;

	// Builder
	public static class 		EncounterControllerBuilder {
		private Hero hero;
		private EncounterView	encounterView;
		private GameController	gameController;

		public EncounterControllerBuilder		hero(Hero hero) {
			this.hero = hero;
			return this;
		}

		public EncounterControllerBuilder			encounterView(EncounterView view) {
			this.encounterView = view;
			return this;
		}

		public EncounterControllerBuilder			gameController(GameController controller) {
			this.gameController = controller;
			return this;
		}

	}

	public void				fight() {
		//The outputs need to be a view thing. Need to set up the appropriate functions
		int heroAttack;
		int heroDefence;
		int damage;
		String enemyName = this.enemy.getEnemyName() + " the " + this.enemy.getEnemyType();
		Random rand = new Random();
		int enemyAttack = this.enemy.getAttack() + rand.nextInt(7);
		if (this.hero.getEquippedWeapon() != null) {
			heroAttack = this.hero.getAttack() + this.hero.getEquippedWeapon().getAttackIncrease() + rand.nextInt(10);
		} else {
			heroAttack = this.hero.getAttack() + rand.nextInt(10);
		}
		if (this.hero.getEquippedArmor() != null) {
			heroDefence = this.hero.getDefence() + this.hero.getEquippedArmor().getDefenceIncrease();
		} else {
			heroDefence = this.hero.getDefence();
		}
		//Hero attacks
		if (heroAttack > this.enemy.getDefence()) {
			damage = heroAttack - this.enemy.getDefence();
			this.encounterView.fight(this.hero.getName(), enemyName, 1, damage);
			this.enemy.setHitPoints(this.enemy.getHitPoints() - damage);
		} else {
			this.encounterView.fight(this.hero.getName(), enemyName, 0, 0);
		}
		if (this.enemy.getHitPoints() <= 0) {
			return;
		}
		//Enemy attacks
		if (enemyAttack > heroDefence) {
			damage = enemyAttack - heroDefence;
			this.encounterView.fight(enemyName, this.hero.getName(), 1, damage);
			this.hero.setHitPoints((this.hero.getHitPoints()) - damage);
		} else {
			this.encounterView.fight(enemyName, this.hero.getName(), 0, 0);
		}
		if ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) ||
			(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() <= 0)) {
			return;
		}
		return;
	}

	public int				run() {
		Random rand = new Random();
		return rand.nextInt(2);
	}

	public void				simulate() {
		int i = 0;
		while (this.enemy.getHitPoints() > 0 && ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) > 0) ||
		(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() > 0))) {
			this.encounterView.simulate(i);
			this.fight();
			i++;
		}
	}

	private void			randomDrop() {
		Random rand = new Random();
		int ret;
		if (rand.nextInt(2) == 1) {
			String drops[] = {"Weapon", "Armor", "Helm", "Inventory", "Health"};
			int i = rand.nextInt(5);
			if (drops[i].equalsIgnoreCase("Weapon")) {
				Weapon weaponDrop = Weapon.builder().name().level(this.hero.getLevel()).build();
				if (this.encounterView.itemDrop(weaponDrop.getName(), i) == 1) {
					this.hero.getInventory().addWeapon(weaponDrop);
				}
			} else if (drops[i].equalsIgnoreCase("Armor")) {
				Armor armorDrop = Armor.builder().name().level(this.hero.getLevel()).build();
				if (this.encounterView.itemDrop(armorDrop.getName(), i) == 1) {
					this.hero.getInventory().addArmor(armorDrop);
				}
			} else if (drops[i].equalsIgnoreCase("Helm")) {
				Helm helmDrop = Helm.builder().name().level(this.hero.getLevel()).build();
				if (this.encounterView.itemDrop(helmDrop.getName(), i) == 1) {
					this.hero.getInventory().addHelm(helmDrop);
				}
			} else if (drops[i].equalsIgnoreCase("Inventory")) {
				int boost = rand.nextInt(5) + 1;
				if (this.encounterView.itemDrop(Integer.toString(boost), i) == 1) {
					this.hero.getInventory().setMaxSlots(this.hero.getInventory().getMaxSlots() + boost);
				}
			} else if (drops[i].equalsIgnoreCase("Health")) {
				int boost = rand.nextInt(5) + 1;
				if (this.encounterView.itemDrop(Integer.toString(boost), i) == 1) {
					if (this.hero.getHitPoints() + boost >= this.hero.getMaxHitPoints()) {
						this.hero.setHitPoints(this.hero.getMaxHitPoints());
					} else {
						this.hero.setHitPoints(this.hero.getHitPoints() + boost);
					}
				}
			}
		}
	}

	public void				round() {
		int heroHitpoints;
		if (this.hero.getEquippedHelm() != null) {
			heroHitpoints = this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease();
		} else {
			heroHitpoints = this.hero.getHitPoints();
		}

		if (this.enemy.getHitPoints() > 0 && heroHitpoints > 0) {
//			continue encounter


		} else if (this.enemy.getHitPoints() <= 0) {
			//Call the item drop!
			this.randomDrop();
			int currentLevel = this.hero.getLevel();
			this.hero.setExperience(this.hero.getExperience() + ((this.enemy.getLevel() + 1) * 10 + 50));
			this.hero.levelUp();
			if (this.hero.getLevel() > currentLevel) {
				//Level up screen
				this.encounterView.success();
			} else {
				this.victory();
			}
		} else if (heroHitpoints <= 0)  {
			this.gameController.getMapView().death();
		}
	}

	public  void			victory() {
		this.gameController.getMapView().success(this.enemy.getXPos(), this.enemy.getYPos());

	}

	public int				startNewEncounter(Enemy enemy) {
		this.setEnemy(enemy);
		//Show encounter and get input
		while (this.enemy.getHitPoints() > 0 && ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) > 0) ||
				(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() > 0))) {
			this.encounterView.title();
			int ret = this.encounterView.display();
			if (ret == 0) {
				if (this.run() == 1) {
					this.encounterView.run(1);
					return 0;
				} else {
					this.encounterView.run(0);
				}
			} else if (ret == 1) {
				this.fight();
			} else if (ret == 2) {
				this.simulate();
			}
		}
		if (this.enemy.getHitPoints() <= 0) {
			//Call the item drop!
			this.randomDrop();
			int currentLevel = this.hero.getLevel();
			this.hero.setExperience(this.hero.getExperience() + ((this.enemy.getLevel() + 1) * 10 + 50));
			this.hero.levelUp();
			if (this.hero.getLevel() > currentLevel) {
				//Level up screen
				this.encounterView.success();
				//Map update
				this.gameController.updateMap();
			}
			return 1;
		} else if (((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) ||
				(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() <= 0))) {
			return -1;
		}
		return 1;
	}

	public int				startNewEncounterLD(Enemy enemy) {
		this.setEnemy(enemy);
		//Show encounter and get input
		while (this.enemy.getHitPoints() > 0 && ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) > 0) ||
		(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() > 0))) {
			this.encounterView.title();
			int ret = this.encounterView.display();
			if (ret == 0) {
				if (this.run() == 1) {
					this.encounterView.run(1);
					return 0;
				} else {
					this.encounterView.run(0);
				}
			} else if (ret == 1) {
				this.fight();
			} else if (ret == 2) {
				this.simulate();
			}
		}
		if (this.enemy.getHitPoints() <= 0) {
			//Call the item drop!
			this.randomDrop();
			int currentLevel = this.hero.getLevel();
			this.hero.setExperience(this.hero.getExperience() + ((this.enemy.getLevel() + 1) * 10 + 50));
			this.hero.levelUp();
			if (this.hero.getLevel() > currentLevel) {
				//Level up screen
				this.encounterView.success();
				//Map update
				this.gameController.updateMap();
			}
			return 1;
		} else if (((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) ||
		(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() <= 0))) {
			return -1;
		}
		return 1;
	}
}

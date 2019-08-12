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
	private Hero			hero;
	@NotNull
	private Enemy			enemy;
	@NotNull
	private EncounterView	encounterView;
	@NotNull
	private GameController	gameController;
	private int				turn;
	private String roundUpdate;

	// Builder
	public static class 		EncounterControllerBuilder {
		private Hero hero;
		private EncounterView	encounterView;
		private GameController	gameController;

		public EncounterControllerBuilder			hero(Hero hero) {
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

	private String				heroAttacks() {
		int heroAttack;
		int damage;
		String enemyName = this.enemy.getEnemyName() + " the " + this.enemy.getEnemyType();
		String ret = "";

		Random rand = new Random();
		if (this.hero.getEquippedWeapon() != null) {
			heroAttack = this.hero.getAttack() + this.hero.getEquippedWeapon().getAttackIncrease() + rand.nextInt(10);
		} else {
			heroAttack = this.hero.getAttack() + rand.nextInt(10);
		}
		//Attack
		if (heroAttack > this.enemy.getDefence()) {
			damage = heroAttack - this.enemy.getDefence();
			this.enemy.setHitPoints(this.enemy.getHitPoints() - damage);
			ret += this.hero.getName() + " attacked " + enemyName + " with " + damage + " points of damage!\n";
		} else {
			ret += this.hero.getName() + " tried to attack " + enemyName + " but missed!\n";
		}
		return ret;
	}

	private String				enemyAttacks() {
		int heroDefence;
		int damage;
		String enemyName = this.enemy.getEnemyName() + " the " + this.enemy.getEnemyType();
		String ret = "";

		Random rand = new Random();
		int enemyAttack = this.enemy.getAttack() + rand.nextInt(7);
		if (this.hero.getEquippedArmor() != null) {
			heroDefence = this.hero.getDefence() + this.hero.getEquippedArmor().getDefenceIncrease();
		} else {
			heroDefence = this.hero.getDefence();
		}

		//Attack
		if (enemyAttack > heroDefence) {
			damage = enemyAttack - heroDefence;
			this.hero.setHitPoints((this.hero.getHitPoints()) - damage);
			ret += enemyName + " attacked " + this.hero.getName() + " with " + damage + " points of damage!\n";
		} else {
			ret += enemyName + " tried to attack " + this.hero.getName() + " but missed!\n";
		}
		return ret;
	}

	public void					fight() {
		this.turn++;
		this.roundUpdate += ("Round " + this.turn + ":\n");
		this.roundUpdate += this.heroAttacks();
		if (this.enemy.getHitPoints() <= 0) {
//			this.encounterView.fight(this.roundUpdate);
			this.round();
		}
		this.roundUpdate += this.enemyAttacks();
		if ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) ||
			(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() <= 0)) {
//			this.encounterView.fight(this.roundUpdate);
			this.round();
		} else {
			this.round();
		}
//		this.encounterView.fight(this.roundUpdate);
	}

	public void					run() {
		Random rand = new Random();
		int ret =  rand.nextInt(2);
		if (ret == 1) {
			this.gameController.getMapView().runAway();
		} else {
			this.encounterView.runFailed();
		}
	}

	public void					simulate() {
		this.roundUpdate += "Simulation started!\n";
		while (this.enemy.getHitPoints() > 0 && ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) > 0) ||
		(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() > 0))) {
			this.turn++;
			this.roundUpdate += ("Round " + this.turn + ":\n");
			this.roundUpdate += this.heroAttacks();
			if (this.enemy.getHitPoints() <= 0) {
				break;
			}
			this.roundUpdate += this.enemyAttacks();
			if ((this.hero.getEquippedHelm() != null && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) ||
					(this.hero.getEquippedHelm() == null && this.hero.getHitPoints() <= 0)) {
				break;
			}
		}
		this.round();
//		this.encounterView.simulate(roundUpdate);
	}

	//NEEED TO FIX

	public void					randomDrop() {
		int heroHitPoints;
		if (this.hero.getEquippedHelm() != null) {
			heroHitPoints = this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease();
		} else {
			heroHitPoints = this.hero.getHitPoints();
		}
		Random rand = new Random();
		int ret;
		if (this.enemy.getHitPoints() <= 0) {
			if (rand.nextInt(2) == 1) {
				String drops[] = {"Weapon", "Armor", "Helm", "Inventory", "Health"};
				int i = rand.nextInt(5);
				if (drops[i].equalsIgnoreCase("Weapon")) {
					if (this.hero.getInventory().getUsedSlots() >= this.hero.getInventory().getMaxSlots()) {
						this.encounterView.itemDropFailed();
					} else {
						Weapon weaponDrop = Weapon.builder().name().level(this.hero.getLevel()).build();
						this.encounterView.weaponDrop(weaponDrop);
					}
				} else if (drops[i].equalsIgnoreCase("Armor")) {
					if (this.hero.getInventory().getUsedSlots() >= this.hero.getInventory().getMaxSlots()) {
						this.encounterView.itemDropFailed();
					} else {
						Armor armorDrop = Armor.builder().name().level(this.hero.getLevel()).build();
						this.encounterView.armorDrop(armorDrop);
					}
				} else if (drops[i].equalsIgnoreCase("Helm")) {
					if (this.hero.getInventory().getUsedSlots() >= this.hero.getInventory().getMaxSlots()) {
						this.encounterView.itemDropFailed();
					} else {
						Helm helmDrop = Helm.builder().name().level(this.hero.getLevel()).build();
						this.encounterView.helmDrop(helmDrop);
					}
				} else if (drops[i].equalsIgnoreCase("Inventory")) {
					int boost = rand.nextInt(5) + 1;
					this.encounterView.itemDrop("Inventory", boost);
				} else if (drops[i].equalsIgnoreCase("Health")) {
					int boost = rand.nextInt(5) + 1;
					this.encounterView.itemDrop("Health", boost);
				}
			} else {
				this.checkLevel();
			}
		} else if (heroHitPoints <= 0)  {
			this.gameController.getMapView().death();
		}

	}

	public void					addArmor(Armor armor) {
		this.hero.getInventory().addArmor(armor);
		this.checkLevel();
	}

	public void					addWeapon(Weapon weapon) {
		this.hero.getInventory().addWeapon(weapon);
		this.checkLevel();
	}

	public void					addHelm(Helm helm) {
		this.hero.getInventory().addHelm(helm);
		this.checkLevel();
	}

	public void					addInventoryBoost(int boost){
		this.hero.getInventory().setMaxSlots(this.hero.getInventory().getMaxSlots() + boost);
		this.checkLevel();
	}

	public void					addHealthBoost(int boost){
		if (this.hero.getHitPoints() + boost >= this.hero.getMaxHitPoints()) {
			this.hero.setHitPoints(this.hero.getMaxHitPoints());
		} else {
			this.hero.setHitPoints(this.hero.getHitPoints() + boost);
		}
		this.checkLevel();
	}

	/////////////

	public void					checkLevel() {
		int currentLevel = this.hero.getLevel();
		this.hero.setExperience(this.hero.getExperience() + ((this.enemy.getLevel() + 1) * 10 + 50));
		this.hero.levelUp();
		if (this.hero.getLevel() > currentLevel) {
			//Level up screen
			if (this.hero.getLevel() == 6) {
				System.out.println("Encounter controller level 6!");
				this.getGameController().getMapView().youWin();
			} else {
				this.getGameController().getMapView().levelUp();
			}
//			this.gameController.updateMap();
//			this.encounterView.success();
		} else {
			this.gameController.removeEnemy(this.enemy);
			this.victory();
		}
	}

	public void					round() {
		int heroHitPoints;
		if (this.hero.getEquippedHelm() != null) {
			heroHitPoints = this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease();
		} else {
			heroHitPoints = this.hero.getHitPoints();
		}

		if (this.enemy == null) {
			System.out.println("Enemy is null");
		}
		if (this.enemy.getHitPoints() > 0 && heroHitPoints > 0) {
			this.encounterView.display();
		} else if (this.enemy.getHitPoints() <= 0 || heroHitPoints <= 0) {
			this.encounterView.battleHistory();
		}
	}

	public  void				victory() {
		this.gameController.getMapView().success(this.enemy.getXPos() - this.hero.getXPos(), this.enemy.getYPos() - this.hero.getYPos());

	}

	public void					startNewEncounter(Enemy enemy) {
		this.setEnemy(enemy);
		this.turn = 0;
		this.roundUpdate = "";
		//Show encounter and get input
		this.round();
	}
}

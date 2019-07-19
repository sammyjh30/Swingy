package za.co.swingy.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
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

	public void				fight() {
		//The outputs need to be a view thing. Need to set up the appropriate functions
		int heroAttack;
		int heroDefence;
		int damage;
		String enemyName = this.enemy.getEnemyName() + " the " + this.enemy.getEnemyType();
		Random rand = new Random();
		int enemyAttack = this.enemy.getAttack() + rand.nextInt(10);
		if (this.hero.getEquippedWeapon() != null) {
			heroAttack = this.hero.getAttack() + this.hero.getEquippedWeapon().getAttackIncrease() + rand.nextInt(10);
		} else {
			heroAttack = this.hero.getAttack() + this.hero.getEquippedWeapon().getAttackIncrease() + rand.nextInt(10);
		}
		if (this.hero.getEquippedArmor() != null) {
			heroDefence = this.hero.getDefence() + this.hero.getEquippedArmor().getDefenceIncrease();
		} else {
			heroDefence = this.hero.getDefence() + this.hero.getEquippedArmor().getDefenceIncrease();
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
			this.hero.setHitPoints((this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) - damage);
		} else {
			this.encounterView.fight(enemyName, this.hero.getName(), 0, 0);
		}
		if ((this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) {
			return;
		}
		return;
	}

	public int				run() {
		Random rand = new Random();
		return rand.nextInt(2);
	}

	public int				startNewEncounter(Enemy enemy) {
		this.setEnemy(enemy);
		//Show encounter and get input
		while (this.enemy.getHitPoints() > 0 && (this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) > 0) {
			int ret = this.encounterView.display();
			if (ret == 0) {
				System.out.println("Call run function");
				if (this.run() == 1) {
					return 0;
				}
			} else if (ret == 1) {
				System.out.println("Call fight function");
				this.fight();
			} else if (ret == 2) {
				System.out.println("Call simulate function");
			}
		}
		if (this.enemy.getHitPoints() <= 0) {
			return 1;
		} else if ((this.hero.getHitPoints() + this.hero.getEquippedHelm().getHitPointIncrease()) <= 0) {
			return -1;
		}
				//To return
	//			if (ret == -1) {
	//				System.out.println("THE HERO IS DEAD?!");
	//			} else if ( ret == 0) {
	//				System.out.println("THE HERO RAN AWAY!");
	//			} else if (ret == 1) {
	//				System.out.println("THE HERO DEFEATED THEIR OPPONENT!");
	//			}
		return 1;
	}
}

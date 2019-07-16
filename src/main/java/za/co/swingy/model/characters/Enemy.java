package za.co.swingy.model.characters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Random;

@Getter
@Setter
@Builder
public class Enemy extends Character{
	@NotNull
	private String		enemyName;
	@NotNull
	private String		enemyType;

	// Builder
	public static class EnemyBuilder {
		private String		enemyName;
		private String		enemyType;

		public EnemyBuilder			enemyName() {
			Random rand = new Random();
			String enemyNames[] = {"Karen","Chad","Kyle","Philip","Susan","Donald"};

			String name = enemyNames[rand.nextInt(5)];
			this.enemyName = name;
			return this;
		}

		public EnemyBuilder			enemyType() {
			Random rand = new Random();
			String enemyTypes[] = {"Orc","Witch","Troll","Bandit","Spooky Scary Skeleton","Bird"};

			String type = enemyTypes[rand.nextInt(5)];
			this.enemyType = type;
			return this;
		}
	}

	public void				generateEnemy(Hero hero){
		Random rand = new Random();
		int level = (hero.getLevel() - 1) + rand.nextInt(3);
		if (level < 0) {
			this.setLevel(0);
		} else {
			this.setLevel(level);
		}
		this.setAttack(this.getLevel() + 3);
		this.setDefence(this.getLevel() + 3);
		this.setMaxHitPoints(this.getLevel() + this.getLevel() * 20 + 1);
		this.setHitPoints(this.getLevel() + this.getLevel() * 20 + 1);
		this.setXPos(0);
		this.setYPos(0);
	}
}

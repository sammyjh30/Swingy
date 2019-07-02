package za.co.swingy.model.items;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Random;

@Getter
@Setter
public class Weapon {
	@NotNull
	private int		level;
	@NotNull
	private int		attackIncrease;
	@NotNull
	private String	name;

	public				Weapon(Builder builder) {
		this.level = builder.level;
		this.attackIncrease = builder.attackIncrease;
		this.name = builder.name;
	}

	// Builder
	public static class Builder {
		private int		level;
		private int		attackIncrease;
		private String	name;

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {}

		public Builder setLevel(int level) {
			this.level = level;
			Random rand = new Random();
			this.attackIncrease =  rand.nextInt(level) + 2;
			return this;
		}

		public Builder			setName() {
			Random rand = new Random();
			String weaponType[] = {"Sword","Axe","Crossbow","Lance","Dagger"};
			String weaponStyle[] = {"Blood","Silver","Wooden","Platinum","Shadow","Gold"};
			String weaponDescription[] = {"Darkness","Skull Crushing","Tree Cutting","Bone Shattering", "Tooth Brushing"};

			String name = weaponStyle[rand.nextInt(6)] + " " +
					weaponType[rand.nextInt(7)] + " of " +
					weaponDescription[rand.nextInt(6)];
			this.name = name;
			return this;
		}

		public Builder			setStarterWeapon() {
			this.name = "Wooden Sword of Beginners";
			this.level = 1;
			this.attackIncrease = 1;
			return this;
		}

		public Weapon 				build() {
			return new Weapon(this);
		}

	}
}
//Weapon weapon = Weapon.Builder.newInstance().setLevel(playerLevel).setName().build();

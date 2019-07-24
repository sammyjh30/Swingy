package za.co.swingy.model.items;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Random;

import static java.lang.Math.abs;

@Getter
@Setter
@Builder
@Data
public class Weapon {
	@NotNull
	private int		level;
	@NotNull
	private int		attackIncrease;
	@NotNull
	private String	name;

	// Builder
	public static class WeaponBuilder {
		private int		level;
		private int		attackIncrease;
		private String	name;

		public WeaponBuilder level(int level) {
			this.level = level;
			Random rand = new Random();
			if (level > 0) {
				this.attackIncrease =  rand.nextInt(abs(level)) + 2;
			} else {
				this.attackIncrease =  rand.nextInt(1) + 2;
			}
			return this;
		}

		public WeaponBuilder			name() {
			Random rand = new Random();
			String weaponType[] = {"Sword","Axe","Crossbow","Lance","Dagger"};
			String weaponStyle[] = {"Blood","Silver","Wooden","Platinum","Shadow","Gold"};
			String weaponDescription[] = {"Darkness","Skull Crushing","Tree Cutting","Bone Shattering", "Tooth Brushing"};

			String name = weaponStyle[rand.nextInt(5)] + " " +
					weaponType[rand.nextInt(4)] + " of " +
					weaponDescription[rand.nextInt(4)];
			this.name = name;
			return this;
		}
	}

	public void			setStarterWeapon() {
		this.name = "Wooden Sword of Noobs";
		this.level = 1;
		this.attackIncrease = 1;
	}

}
//Weapon weapon = Weapon.Builder.newInstance().setLevel(playerLevel).setName().build();

package za.co.swingy.model.items;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Random;

import static java.lang.Math.abs;

@Getter
@Setter
@Builder
@Data
public class Armor {
	@NotNull
	private int		level;
	@NotNull
	private int		defenceIncrease;
	@NotNull
	private String	name;

	// Builder
	public static class ArmorBuilder {
		private int		level;
		private int		defenceIncrease;
		private String	name;

		public ArmorBuilder level(int playerLevel) {
			this.level = playerLevel;
			Random rand = new Random();
			this.defenceIncrease =  rand.nextInt(abs(playerLevel)) + 2;
			return this;
		}

		public ArmorBuilder			name() {
			Random rand = new Random();
			String armorType[] = {"Chest Plate","Mail","Shield","Gauntlet","Knee Piece"};
			String armorStyle[] = {"Leather","Chain","Wooden","Bone","Shadow","Gold"};

			String name = armorStyle[rand.nextInt(6)] + " " +
					armorType[rand.nextInt(7)];
			this.name = name;
			return this;
		}

	}

	public void			setStarterArmor() {
		this.name = "Casual Clothes";
		this.level = 1;
		this.defenceIncrease = 1;
	}
}

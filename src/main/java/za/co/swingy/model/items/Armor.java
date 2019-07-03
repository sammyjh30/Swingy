package za.co.swingy.model.items;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Random;

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

//	public				Armor(Builder builder) {
//		this.level = builder.level;
//		this.defenceIncrease = builder.defenceIncrease;
//		this.name = builder.name;
//	}


	// Builder
	public static class ArmorBuilder {
		private int		level;
		private int		defenceIncrease;
		private String	name;
//
//		public static Builder newInstance() {
//			return new Builder();
//		}
//
//		private Builder() {}
//
		public ArmorBuilder level(int level) {
			this.level = level;
			Random rand = new Random();
			this.defenceIncrease =  rand.nextInt(level) + 2;
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
//Armor armor = Armor.Builder.newInstance().setLevel(playerLevel).setName().build();
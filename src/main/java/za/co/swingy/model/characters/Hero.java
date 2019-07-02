package za.co.swingy.model.characters;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Hero extends Character {
	@NotNull
	private Weapon		equippedWeapon;
	@NotNull
	private Armor		equippedArmor;
	@NotNull
	private  int		experience;
	@NotNull
	private Inventory	inventory;

	public				Hero(Builder builder) {
		this.equippedWeapon = builder.equippedWeapon;
		this.equippedArmor = builder.equippedArmor;
		this.experience = builder.experience;
	}
	// Builder
	public static class Builder {
		private Weapon		equippedWeapon;
		private Armor		equippedArmor;
		private  int		experience;
		private Inventory	inventory;

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {}

		public Builder setLevel(int level) {
			this.level = level;

			return this;
		}


		public Weapon 				build() {
			return new Hero(this);
		}

	}
}

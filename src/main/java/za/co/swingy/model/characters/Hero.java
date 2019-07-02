package za.co.swingy.model.characters;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.items.Armor;
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
}

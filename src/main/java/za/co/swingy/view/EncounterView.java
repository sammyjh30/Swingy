package za.co.swingy.view;

import za.co.swingy.controller.EncounterController;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

import javax.validation.constraints.NotNull;

public interface EncounterView {

	void					display();

	void					fight(String round);

	void					runFailed();

	void					simulate(String round);

//	void					title();

	void					itemDrop(String item, int index);

	void					armorDrop(Armor armor);

	void					weaponDrop(Weapon weapon);

	void					helmDrop(Helm helm);

	void					success();
	}

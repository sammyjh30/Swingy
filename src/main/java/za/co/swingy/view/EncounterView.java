package za.co.swingy.view;

import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;

public interface EncounterView {

	void					display();

	void					runFailed();

	void					battleHistory();

	void					itemDrop(String item, int index);

	void					armorDrop(Armor armor);

	void					weaponDrop(Weapon weapon);

	void					helmDrop(Helm helm);

	void					success();
}

package za.co.swingy.view;

import za.co.swingy.controller.EncounterController;
import za.co.swingy.controller.GameController;

import javax.validation.constraints.NotNull;

public interface EncounterView {

	void					display();

	void					fight(String round);

	void					runFailed();

	void					simulate(String round);

	void					title();

	int						itemDrop(String item, int index);

	void					success();
	}

package za.co.swingy.view;

import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;

public interface MapView {
	void					displayMap(char[][] map, int mapSize);

	void					display(GameController controller);

	void					death();

	void					runAway();

	void					success();

	void					falseAlarm();

	void					createEncounter(Enemy enemy);
}

package za.co.swingy.view;

import za.co.swingy.controller.GameController;

public interface MapView {
	void					displayMap(char[][] map, int mapSize);
	void					display(GameController controller);
}

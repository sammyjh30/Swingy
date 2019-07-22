package za.co.swingy.view;

import za.co.swingy.controller.GameController;

public interface MapView {
	void					displayMap(char[][] map, int mapSize);
	int					display(GameController controller);
}

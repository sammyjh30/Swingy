package za.co.swingy.view.console;

import za.co.swingy.controller.GameController;
import za.co.swingy.view.MapView;

public class MapConsoleView implements MapView {

	public void					displayMap(char[][] map, int mapSize) {
//		System.out.println((char)27 + "[30mYELLOW");
//		System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");
//		System.out.println((char)27 + "[32mGREEN");
//		System.out.println((char)27 + "[33mYELLOW");
//		System.out.println((char)27 + "[34mBLUE");
//		System.out.println((char)27 + "[35mPURPLE");
//		System.out.println((char)27 + "[36mLIGHTBLUE");
//		System.out.println((char)27 + "[37mWHITE");
		for (int i = 0; i < mapSize; i++) {
			System.out.format("%1$"+ (mapSize * 2) + "s", "_");
			System.out.print("| ");
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == '.') {
					System.out.print('.');
				} else if (map[i][j] == 'O') {
					System.out.print((char)27 + "[32mO" + (char)27 + "[37m");
				} else if (map[i][j] == 'X') {
					System.out.print((char)27 + "[35mX" + (char)27 + "[37m");
				}
				System.out.print(" ");
			}
			System.out.print("|\n");
		}
		System.out.format("%1$"+ (mapSize * 2) + "s", "_");
		System.out.print("| ");
	}

	public void					display(GameController controller) {
		displayMap(controller.getMap(), controller.getMapSize());
	}
}

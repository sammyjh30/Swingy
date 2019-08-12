package za.co.swingy;

import za.co.swingy.view.console.MenuConsoleView;
import za.co.swingy.view.gui.MenuGuiView;

public class Main {

	public static void 			main(String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase( "console")) {
				MenuConsoleView menuConsoleView = new MenuConsoleView();
				menuConsoleView.menu();
			} else if (args[0].equalsIgnoreCase( "gui")) {
				MenuGuiView menuGuiView = new MenuGuiView();
				menuGuiView.menu();
			} else {
				System.out.println("ERROR: Incorrect parameters.");
			}
		} else {
			System.out.println("ERROR: Incorrect parameters.");
		}
		return;
	}



}

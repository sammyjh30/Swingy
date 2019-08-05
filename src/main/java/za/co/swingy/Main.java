package za.co.swingy;

import za.co.swingy.view.console.MenuConsoleView;
import za.co.swingy.view.gui.MenuGuiView;

public class Main {
	//Todo:
	//Need to be able to win!
	//Switch

	public static void 			main(String[] args) {
//		MenuConsoleView menuConsoleView = new MenuConsoleView();
//		menuConsoleView.menu();

		MenuGuiView menuGuiView = new MenuGuiView();
		menuGuiView.menu();
		return;
	}



}

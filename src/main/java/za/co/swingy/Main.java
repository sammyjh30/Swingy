package za.co.swingy;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.view.console.MenuConsoleView;

public class Main {

	public static void 			main(String[] args) {
//		CharacterController		characterController = CharacterController.builder().createHeroView().loadFileView().build();
////		characterController.createNewHero();
//		characterController.loadHero();
		MenuConsoleView menuConsoleView = new MenuConsoleView();
		menuConsoleView.menu();
		return;
	}



}

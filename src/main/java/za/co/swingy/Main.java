package za.co.swingy;

import za.co.swingy.controller.CharacterController;

public class Main {

	public static void 			main(String[] args) {
		CharacterController		characterController = CharacterController.builder().createHeroView().build();
		characterController.createNewHero();
		return;
	}



}

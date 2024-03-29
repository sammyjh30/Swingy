package za.co.swingy.view;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.model.characters.Hero;
import java.util.ArrayList;

public interface LoadFileView {
	void				saveList(ArrayList<Hero> saves);

	void			noSaves();

	void 			printLoadedHero(Hero hero, CharacterController controller);

	void 			setCharacterController(CharacterController characterController);
}

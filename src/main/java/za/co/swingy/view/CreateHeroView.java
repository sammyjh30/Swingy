package za.co.swingy.view;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.model.characters.Hero;

public interface CreateHeroView {
//	https://www.tutorialspoint.com/design_pattern/mvc_pattern

	void			printHeroStatus(Hero hero);

	void			promptName(CharacterController controller);

	void			promptType();
}

package za.co.swingy.view;

import za.co.swingy.controller.CharacterController;
import za.co.swingy.model.characters.Hero;

public interface CreateHeroView {
//	https://www.tutorialspoint.com/design_pattern/mvc_pattern

	int 			printHeroStatus(Hero hero);

	int				 promptName(CharacterController controller);

	void			 promptType();
}

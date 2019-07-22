package za.co.swingy.view;

import za.co.swingy.model.characters.Hero;

public interface CreateHeroView {
//	https://www.tutorialspoint.com/design_pattern/mvc_pattern

	int 			printHeroStatus(Hero hero);

	String			 promptName();

	String			 promptType();
}

package za.co.swingy.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.CreateHeroView;

import javax.swing.*;

@Getter
@Setter
public class CreateHeroGuiView implements CreateHeroView {
	private JPanel topPanel;
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private JTextArea nameTextArea;
	private JTextArea levelTextArea;
	private JTextArea typeTextArea;
	private JTextArea hpTexxtArea;
	private JTextArea attackTextArea;
	private JPanel heroPanel;
	private JTextArea positionTextArea;
	private JTextArea slotsTextArea;
	private JTextArea weaponsTextArea;
	private JTextArea helmsTextArea;
	private JTextPane alrightHeroWeReTextPane;
	private JTextArea defenceTextArea;

	private JFrame frame;

	public CreateHeroGuiView(JFrame frame) {
		this.frame = frame;
	}

	public int 			printHeroStatus(Hero hero) {
		return 0;
	}

	public String			 promptName() {
		return null;
	}

	public String			 promptType() {
		return null;
	}

}

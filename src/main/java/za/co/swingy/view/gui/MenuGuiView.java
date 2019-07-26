package za.co.swingy.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.CharacterController;
import za.co.swingy.view.MenuView;
import za.co.swingy.view.console.CreateHeroConsoleView;
import za.co.swingy.view.console.LoadFileConsoleView;

import javax.swing.*;

@Getter
@Setter
public class MenuGuiView implements MenuView {
	private JFrame frame;
	private CharacterController characterController;
	private JTextArea welcomeToSwingyWhatTextArea;
	private JButton newButton;
	private JButton loadButton;
	private JButton exitButton;
	private JPanel mainPanel;

	public MenuGuiView(JFrame frame) {
		this.characterController = CharacterController.builder().menuView(this).createHeroView(new CreateHeroConsoleView()).loadFileView(new LoadFileConsoleView()).build();
		this.frame = frame;
	}

	public void			menu() {
		this.frame.setContentPane(mainPanel);
		this.frame.pack();
	}
}

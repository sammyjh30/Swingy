package za.co.swingy.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.CharacterController;
import za.co.swingy.view.MenuView;
import za.co.swingy.view.console.CreateHeroConsoleView;
import za.co.swingy.view.console.LoadFileConsoleView;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class MenuGuiView implements MenuView {
	private JFrame frame;
	private CharacterController characterController;
	private JTextArea welcomeToSwingyWhatTextArea;
	private JButton newButton;
	private JButton loadButton;
	private JButton exitButton;
	@NotNull
	private JPanel mainPanel;

	public MenuGuiView(JFrame frame) {
		this.characterController = CharacterController.builder().menuView(this).createHeroView(new CreateHeroGuiView(frame)).loadFileView(new LoadFileGuiView()).build();
		this.frame = frame;
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterController.createNewHero();
			}
		});

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterController.loadHero();
			}
		});
	}

	public void			menu() {
		this.frame.setContentPane(mainPanel);
		this.frame.pack();
	}
}

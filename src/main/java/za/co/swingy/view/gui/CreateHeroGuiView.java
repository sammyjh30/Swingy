package za.co.swingy.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.CreateHeroView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class CreateHeroGuiView implements CreateHeroView {
	private JPanel topPanel;
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private JTextArea nameTextArea;
	private JTextArea levelTextArea;
	private JTextArea typeTextArea;
	private JTextArea hpTextArea;
	private JTextArea attackTextArea;
	private JPanel heroPanel;
	private JTextArea positionTextArea;
	private JTextArea slotsTextArea;
	private JTextArea weaponsTextArea;
	private JTextArea helmsTextArea;
	private JTextPane alrightHeroWeReTextPane;
	private JTextArea defenceTextArea;
	private JButton OKButton;
	private JTextField nameTextField;

	private JFrame frame;

	private String name = null;

	public CreateHeroGuiView(JFrame frame) {
		this.topPanel.setVisible(false);
		this.bottomPanel.setVisible(false);
		this.heroPanel.setVisible(false);
//
//		this.OKButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(nameTextArea.getText() != null && !nameTextArea.getText().isEmpty()) {
//					name = nameTextArea.getText();
////					topPanel.setVisible(false);
//				}
//			}
//		});
//
//		nameTextField.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(nameTextArea.getText() == null && nameTextArea.getText().isEmpty()) {
//					OKButton.setEnabled(false);
//				} else {
//					OKButton.setEnabled(true);
//				}
//			}
//		});
	}

	public int 			printHeroStatus(Hero hero) {
		this.frame.setContentPane(heroPanel);
		this.frame.pack();
		return 0;
	}

	public String			 promptName() {
//		this.frame = frame;
		this.frame.setContentPane(this.mainPanel);
//		this.frame.setContentPane(topPanel);
		this.frame.pack();
		this.topPanel.setVisible(true);
		while (this.name == null) {
		}
		return this.name;
	}

	public String			 promptType() {
		this.frame.setContentPane(bottomPanel);
		this.frame.pack();
		return null;
	}

}

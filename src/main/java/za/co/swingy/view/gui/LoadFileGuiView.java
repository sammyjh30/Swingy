package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import za.co.swingy.controller.CharacterController;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.LoadFileView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadFileGuiView extends FrameView implements LoadFileView {
	private CharacterController characterController;
	private JPanel mainPanel;
	private JTextPane LOADSAVETextPane;
	private JScrollPane loadScrollPanel;
	private JPanel loadsPanel;
	private JPanel loadMainPanel;
	private JButton returnButton;
	private JPanel noSavesPanel;
	private JButton deathButton;
	private JPanel heroPanel;
	private JTextArea nameTextArea;
	private JTextArea levelTextArea;
	private JTextArea typeTextArea;
	private JTextArea hpTextArea;
	private JTextArea attackTextArea;
	private JTextArea positionTextArea;
	private JTextArea slotsTextArea;
	private JTextArea armorTextArea;
	private JTextArea weaponsTextArea;
	private JTextArea helmsTextArea;
	private JTextPane alrightHeroWeReTextPane;
	private JTextArea defenceTextArea;
	private JButton letSGOButton;

	private Hero hero;

	public LoadFileGuiView() {
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterController.returnToMenu();
			}
		});
		deathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterController.returnToMenu();
			}
		});
		letSGOButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				heroPanel.setVisible(false);
				GameController gameController = GameController.builder().hero(hero).mapView(new MapGuiView()).characterController(characterController).build();
				gameController.showMapView();
			}
		});
	}

	private void setLoadGUI(ArrayList<Hero> saves) {
		int lineCount = 0;

//		JTextArea headerTextArea = new JTextArea();
//		headerTextArea.setText("_______________________________________\n" +
//				"| No. |     Name            |  Level  |\n" +
//				"|_____|_____________________|_________|");
//		headerTextArea.setBackground(new Color(-11645362));
//		headerTextArea.setForeground(new Color(-4342339));
//		headerTextArea.setMinimumSize(new Dimension(400, 90));
//		headerTextArea.setMaximumSize(new Dimension(400, 90));
//		headerTextArea.setPreferredSize(new Dimension(400, 90));
//		headerTextArea.setEditable(false);
//
//		JPanel loadHeaderPanel = new JPanel();
//		loadHeaderPanel.setMinimumSize(new Dimension(460, 30));
//		loadHeaderPanel.setMaximumSize(new Dimension(460, 30));
//		loadHeaderPanel.setPreferredSize(new Dimension(460, 30));
//
//		GridBagConstraints loadHeaderGbd = new GridBagConstraints();
//		loadHeaderGbd.gridx = 0;
//		loadHeaderGbd.gridy = 0;
//
//		loadHeaderPanel.add(headerTextArea, loadHeaderGbd);
//
//		loadHeaderGbd.gridx = 0;
//		loadHeaderGbd.gridy = lineCount;
//
//		this.loadsPanel.add(loadHeaderPanel, loadHeaderGbd);


//		lineCount++;
//
		for (int i = 0; i < saves.size() && i <= 100; i++) {
			//Print index
			String index = null;
			if (i < 10) {
				index = "|   " + i + " |";
			} else if (i > 9 && i < 100) {
				index = "|  " + i + " |";
			} else if (i == 100) {
				index = "| " + i + " |";
			}
			//Print name
			String name = "";
			if (saves.get(i).getName().length() < 21) {
				for (int j = 0; j < (21 - saves.get(i).getName().length()); j++) {
					name += " ";
				}
				name += saves.get(i).getName() + "|";
			} else {
				name += saves.get(i).getName().substring(0, 20) + "|";
			}
			//Print level
			String level = null;
			if (saves.get(i).getLevel() < 10) {
				level = "       " + saves.get(i).getLevel() + " |";
			} else if (saves.get(i).getLevel() > 9 && saves.get(i).getLevel() < 100) {
				level = "      " + saves.get(i).getLevel() + " |";
			} else if (saves.get(i).getLevel() == 100) {
				level = "     " + saves.get(i).getLevel() + " |";
			}
//			lineCount = i;

			JTextArea loadIterTextArea = new JTextArea();
			loadIterTextArea.setText(index + name + level);
			loadIterTextArea.setBackground(new Color(-11645362));
			loadIterTextArea.setForeground(new Color(-4342339));
			loadIterTextArea.setMinimumSize(new Dimension(300, 30));
			loadIterTextArea.setMaximumSize(new Dimension(300, 30));
			loadIterTextArea.setPreferredSize(new Dimension(300, 30));
			loadIterTextArea.setEditable(false);

			JPanel loadIterPanel = new JPanel();
			loadIterPanel.setMinimumSize(new Dimension(460, 30));
			loadIterPanel.setMaximumSize(new Dimension(460, 30));
			loadIterPanel.setPreferredSize(new Dimension(460, 30));

			GridBagConstraints loadIterGbd = new GridBagConstraints();
			loadIterGbd.gridx = 0;
			loadIterGbd.gridy = 0;

			loadIterPanel.add(loadIterTextArea, loadIterGbd);

			JButton loadButton = new JButton();
			loadButton.putClientProperty("id", i);
			loadButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					characterController.loadSave(index);
				}
			});
			loadButton.setText("LOAD");
			loadIterGbd.gridx = 1;
			loadIterGbd.gridy = 0;
			loadIterPanel.add(loadButton, loadIterGbd);

			loadIterGbd.gridx = 0;
			loadIterGbd.gridy = lineCount;
			this.loadsPanel.add(loadIterPanel, loadIterGbd);

			lineCount++;
		}
//		JTextArea loadIterTextArea = new JTextArea();
//		loadIterTextArea.setText("|_____|_____________________|_________|");
//		loadIterTextArea.setBackground(new Color(-11645362));
//		loadIterTextArea.setForeground(new Color(-4342339));
//		loadIterTextArea.setMinimumSize(new Dimension(300, 30));
//		loadIterTextArea.setMaximumSize(new Dimension(300, 30));
//		loadIterTextArea.setPreferredSize(new Dimension(300, 30));
//		loadIterTextArea.setEditable(false);
//
//		JPanel loadFooterPanel = new JPanel();
//		loadFooterPanel.setMinimumSize(new Dimension(460, 30));
//		loadFooterPanel.setMaximumSize(new Dimension(460, 30));
//		loadFooterPanel.setPreferredSize(new Dimension(460, 30));
//
//		GridBagConstraints loadFooterGbd = new GridBagConstraints();
//		loadFooterGbd.gridx = 0;
//		loadFooterGbd.gridy = 0;
//
//		loadFooterPanel.add(loadIterTextArea, loadFooterGbd);
//
//		GridBagConstraints loadIterGbd = new GridBagConstraints();
//		loadIterGbd.gridx = 0;
//		loadIterGbd.gridy = lineCount;
//
//		this.loadsPanel.add(loadFooterPanel, loadIterGbd);
	}

	public void saveList(ArrayList<Hero> saves) {
		this.setLoadGUI(saves);
		this.getFrame().setContentPane(this.mainPanel);
		this.loadMainPanel.setVisible(true);
		this.noSavesPanel.setVisible(false);
		this.heroPanel.setVisible(false);
	}

	public void noSaves() {
		this.getFrame().setContentPane(this.mainPanel);
		this.loadMainPanel.setVisible(false);
		this.noSavesPanel.setVisible(true);
		this.heroPanel.setVisible(false);
	}

	public void printLoadedHero(Hero hero, CharacterController controller) {
		this.hero = hero;
		this.nameTextArea.setText(hero.getName());
		this.levelTextArea.setText("" + hero.getLevel());
		this.typeTextArea.setText(hero.getClassType());

		if (hero.getEquippedHelm() == null) {
			this.hpTextArea.setText(hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		} else {
			this.hpTextArea.setText((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
		}

		if (hero.getEquippedWeapon() == null) {
			this.attackTextArea.setText("" + hero.getAttack());
		} else {
			this.attackTextArea.setText("" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}

		if (hero.getEquippedArmor() == null) {
			this.defenceTextArea.setText("" + hero.getDefence());
		} else {
			this.defenceTextArea.setText("" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}

		this.positionTextArea.setText("[" + hero.getXPos() + ";" + hero.getYPos() + "]");
		//Inventory
		Inventory inventory = hero.getInventory();
		this.slotsTextArea.setText(inventory.getUsedSlots() + "/" + inventory.getMaxSlots());

		String armorString = "";
		for (int i = 0; i < inventory.getArmors().size(); i++) {
			Armor a = inventory.getArmors().get(i);
			armorString += a.getName() + " level: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease() + "\n";
		}
		this.armorTextArea.setText(armorString);

		String weaponString = "";
		for (int i = 0; i < inventory.getWeapons().size(); i++) {
			Weapon w = inventory.getWeapons().get(i);
			weaponString += "" + w.getName() + " level: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease() + "\n";
		}
		this.weaponsTextArea.setText(weaponString);

		String helmString = "";
		for (int i = 0; i < inventory.getHelms().size(); i++) {
			Helm h = inventory.getHelms().get(i);
			helmString += "" + h.getName() + " level: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease() + "\n";
		}
		this.helmsTextArea.setText(helmString);
//		this.bottomPanel.setVisible(false);
		this.loadMainPanel.setVisible(false);
		this.noSavesPanel.setVisible(false);
		this.heroPanel.setVisible(true);
	}

	public void setCharacterController(CharacterController characterController) {
		this.characterController = characterController;
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		mainPanel.setMaximumSize(new Dimension(560, 560));
		mainPanel.setMinimumSize(new Dimension(560, 560));
		mainPanel.setPreferredSize(new Dimension(560, 560));
		loadMainPanel = new JPanel();
		loadMainPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		loadMainPanel.setBackground(new Color(-12566464));
		loadMainPanel.setForeground(new Color(-4342339));
		mainPanel.add(loadMainPanel, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer1 = new Spacer();
		loadMainPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
		LOADSAVETextPane = new JTextPane();
		LOADSAVETextPane.setBackground(new Color(-12566464));
		LOADSAVETextPane.setCaretColor(new Color(-4342339));
		LOADSAVETextPane.setDisabledTextColor(new Color(-4342339));
		LOADSAVETextPane.setEnabled(false);
		Font LOADSAVETextPaneFont = this.$$$getFont$$$(null, Font.BOLD, 16, LOADSAVETextPane.getFont());
		if (LOADSAVETextPaneFont != null) LOADSAVETextPane.setFont(LOADSAVETextPaneFont);
		LOADSAVETextPane.setText("\t\t\tLOAD SAVE:");
		loadMainPanel.add(LOADSAVETextPane, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer2 = new Spacer();
		loadMainPanel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
		final Spacer spacer3 = new Spacer();
		loadMainPanel.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
		loadScrollPanel = new JScrollPane();
		loadScrollPanel.setBackground(new Color(-11645362));
		loadScrollPanel.setForeground(new Color(-4342339));
		loadMainPanel.add(loadScrollPanel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, 360), new Dimension(500, 360), new Dimension(500, -1), 0, false));
		loadsPanel = new JPanel();
		loadsPanel.setLayout(new GridBagLayout());
		loadsPanel.setBackground(new Color(-11645362));
		loadsPanel.setForeground(new Color(-4342339));
		loadScrollPanel.setViewportView(loadsPanel);
		final Spacer spacer4 = new Spacer();
		loadMainPanel.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		returnButton = new JButton();
		returnButton.setText("Button");
		loadMainPanel.add(returnButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		noSavesPanel = new JPanel();
		noSavesPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		noSavesPanel.setBackground(new Color(-12566464));
		mainPanel.add(noSavesPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane1 = new JTextPane();
		textPane1.setBackground(new Color(-12566464));
		textPane1.setCaretColor(new Color(-4380623));
		textPane1.setDisabledTextColor(new Color(-4380623));
		textPane1.setEditable(false);
		textPane1.setForeground(new Color(-4380623));
		textPane1.setText("You don't have any saves yet! Create a new character to start the adventure!\"");
		noSavesPanel.add(textPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer5 = new Spacer();
		noSavesPanel.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer6 = new Spacer();
		noSavesPanel.add(spacer6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer7 = new Spacer();
		noSavesPanel.add(spacer7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer8 = new Spacer();
		noSavesPanel.add(spacer8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		deathButton = new JButton();
		deathButton.setLabel("OK");
		deathButton.setText("OK");
		noSavesPanel.add(deathButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer9 = new Spacer();
		noSavesPanel.add(spacer9, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayoutManager(20, 4, new Insets(0, 0, 0, 0), -1, -1));
		heroPanel.setBackground(new Color(-12566464));
		heroPanel.setForeground(new Color(-4342339));
		heroPanel.setOpaque(true);
		mainPanel.add(heroPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer10 = new Spacer();
		heroPanel.add(spacer10, new GridConstraints(19, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer11 = new Spacer();
		heroPanel.add(spacer11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final Spacer spacer12 = new Spacer();
		heroPanel.add(spacer12, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final JLabel label1 = new JLabel();
		label1.setBackground(new Color(-12566464));
		Font label1Font = this.$$$getFont$$$(null, Font.BOLD, -1, label1.getFont());
		if (label1Font != null) label1.setFont(label1Font);
		label1.setForeground(new Color(-4342339));
		label1.setText("Name:");
		heroPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setBackground(new Color(-12566464));
		Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
		if (label2Font != null) label2.setFont(label2Font);
		label2.setForeground(new Color(-4342339));
		label2.setText("Level:");
		heroPanel.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setBackground(new Color(-12566464));
		Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
		if (label3Font != null) label3.setFont(label3Font);
		label3.setForeground(new Color(-4342339));
		label3.setText("Type:");
		heroPanel.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setBackground(new Color(-12566464));
		Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
		if (label4Font != null) label4.setFont(label4Font);
		label4.setForeground(new Color(-4342339));
		label4.setText("HP:");
		heroPanel.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setBackground(new Color(-12566464));
		Font label5Font = this.$$$getFont$$$(null, Font.BOLD, -1, label5.getFont());
		if (label5Font != null) label5.setFont(label5Font);
		label5.setForeground(new Color(-4342339));
		label5.setText("Attack:");
		heroPanel.add(label5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setBackground(new Color(-12566464));
		Font label6Font = this.$$$getFont$$$(null, Font.BOLD, -1, label6.getFont());
		if (label6Font != null) label6.setFont(label6Font);
		label6.setForeground(new Color(-4342339));
		label6.setText("Defence:");
		heroPanel.add(label6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label7 = new JLabel();
		label7.setBackground(new Color(-12566464));
		Font label7Font = this.$$$getFont$$$(null, Font.BOLD, -1, label7.getFont());
		if (label7Font != null) label7.setFont(label7Font);
		label7.setForeground(new Color(-4342339));
		label7.setText("Position:");
		heroPanel.add(label7, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setBackground(new Color(-12566464));
		Font label8Font = this.$$$getFont$$$(null, Font.BOLD, -1, label8.getFont());
		if (label8Font != null) label8.setFont(label8Font);
		label8.setForeground(new Color(-4342339));
		label8.setText("______________Inventory______________");
		heroPanel.add(label8, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label9 = new JLabel();
		label9.setBackground(new Color(-12566464));
		Font label9Font = this.$$$getFont$$$(null, Font.BOLD, -1, label9.getFont());
		if (label9Font != null) label9.setFont(label9Font);
		label9.setForeground(new Color(-4342339));
		label9.setText("Slots: ");
		heroPanel.add(label9, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		nameTextArea = new JTextArea();
		nameTextArea.setBackground(new Color(-12566464));
		nameTextArea.setCaretColor(new Color(-4342339));
		nameTextArea.setEditable(false);
		nameTextArea.setForeground(new Color(-4342339));
		nameTextArea.setLineWrap(false);
		heroPanel.add(nameTextArea, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		levelTextArea = new JTextArea();
		levelTextArea.setBackground(new Color(-12566464));
		levelTextArea.setDisabledTextColor(new Color(-4342339));
		levelTextArea.setEditable(false);
		levelTextArea.setForeground(new Color(-4342339));
		levelTextArea.setLineWrap(false);
		levelTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(levelTextArea, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		typeTextArea = new JTextArea();
		typeTextArea.setBackground(new Color(-12566464));
		typeTextArea.setCaretColor(new Color(-4342339));
		typeTextArea.setDisabledTextColor(new Color(-4342339));
		typeTextArea.setEditable(false);
		typeTextArea.setForeground(new Color(-4342339));
		typeTextArea.setLineWrap(false);
		heroPanel.add(typeTextArea, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		hpTextArea = new JTextArea();
		hpTextArea.setBackground(new Color(-12566464));
		hpTextArea.setCaretColor(new Color(-4342339));
		hpTextArea.setDisabledTextColor(new Color(-4342339));
		hpTextArea.setEditable(false);
		hpTextArea.setForeground(new Color(-4342339));
		hpTextArea.setLineWrap(false);
		heroPanel.add(hpTextArea, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		attackTextArea = new JTextArea();
		attackTextArea.setBackground(new Color(-12566464));
		attackTextArea.setCaretColor(new Color(-4342339));
		attackTextArea.setDisabledTextColor(new Color(-4342339));
		attackTextArea.setEditable(false);
		attackTextArea.setForeground(new Color(-4342339));
		attackTextArea.setLineWrap(false);
		attackTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(attackTextArea, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		positionTextArea = new JTextArea();
		positionTextArea.setBackground(new Color(-12566464));
		positionTextArea.setCaretColor(new Color(-4342339));
		positionTextArea.setEditable(false);
		positionTextArea.setForeground(new Color(-4342339));
		positionTextArea.setLineWrap(false);
		positionTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(positionTextArea, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		slotsTextArea = new JTextArea();
		slotsTextArea.setBackground(new Color(-12566464));
		slotsTextArea.setCaretColor(new Color(-4342339));
		slotsTextArea.setDisabledTextColor(new Color(-4342339));
		slotsTextArea.setEditable(false);
		slotsTextArea.setForeground(new Color(-4342339));
		slotsTextArea.setLineWrap(false);
		heroPanel.add(slotsTextArea, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final JLabel label10 = new JLabel();
		label10.setBackground(new Color(-12566464));
		Font label10Font = this.$$$getFont$$$(null, Font.BOLD, -1, label10.getFont());
		if (label10Font != null) label10.setFont(label10Font);
		label10.setForeground(new Color(-4342339));
		label10.setText("Armor:");
		heroPanel.add(label10, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		armorTextArea = new JTextArea();
		armorTextArea.setBackground(new Color(-12566464));
		armorTextArea.setCaretColor(new Color(-4342339));
		armorTextArea.setDisabledTextColor(new Color(-4342339));
		armorTextArea.setEditable(false);
		armorTextArea.setForeground(new Color(-4342339));
		armorTextArea.setLineWrap(false);
		armorTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(armorTextArea, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final JLabel label11 = new JLabel();
		label11.setBackground(new Color(-12566464));
		Font label11Font = this.$$$getFont$$$(null, Font.BOLD, -1, label11.getFont());
		if (label11Font != null) label11.setFont(label11Font);
		label11.setForeground(new Color(-4342339));
		label11.setText("Weapons:");
		heroPanel.add(label11, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		weaponsTextArea = new JTextArea();
		weaponsTextArea.setBackground(new Color(-12566464));
		weaponsTextArea.setCaretColor(new Color(-4342339));
		weaponsTextArea.setDisabledTextColor(new Color(-4342339));
		weaponsTextArea.setEditable(false);
		weaponsTextArea.setForeground(new Color(-4342339));
		weaponsTextArea.setLineWrap(false);
		heroPanel.add(weaponsTextArea, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final JLabel label12 = new JLabel();
		label12.setBackground(new Color(-12566464));
		Font label12Font = this.$$$getFont$$$(null, Font.BOLD, -1, label12.getFont());
		if (label12Font != null) label12.setFont(label12Font);
		label12.setForeground(new Color(-4342339));
		label12.setText("Helms:");
		heroPanel.add(label12, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		helmsTextArea = new JTextArea();
		helmsTextArea.setBackground(new Color(-12566464));
		helmsTextArea.setCaretColor(new Color(-4342339));
		helmsTextArea.setDisabledTextColor(new Color(-4342339));
		helmsTextArea.setEditable(false);
		helmsTextArea.setForeground(new Color(-4342339));
		helmsTextArea.setLineWrap(false);
		heroPanel.add(helmsTextArea, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final Spacer spacer13 = new Spacer();
		heroPanel.add(spacer13, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		alrightHeroWeReTextPane = new JTextPane();
		alrightHeroWeReTextPane.setBackground(new Color(-12566464));
		alrightHeroWeReTextPane.setCaretColor(new Color(-4342339));
		alrightHeroWeReTextPane.setDisabledTextColor(new Color(-4342339));
		alrightHeroWeReTextPane.setEditable(false);
		alrightHeroWeReTextPane.setForeground(new Color(-4342339));
		alrightHeroWeReTextPane.setText("Alright, hero! We're ready to go! Let your adventure begin...");
		heroPanel.add(alrightHeroWeReTextPane, new GridConstraints(17, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		defenceTextArea = new JTextArea();
		defenceTextArea.setBackground(new Color(-12566464));
		defenceTextArea.setCaretColor(new Color(-4342339));
		defenceTextArea.setDisabledTextColor(new Color(-4342339));
		defenceTextArea.setEditable(false);
		defenceTextArea.setForeground(new Color(-4342339));
		defenceTextArea.setLineWrap(false);
		defenceTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(defenceTextArea, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final Spacer spacer14 = new Spacer();
		heroPanel.add(spacer14, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		letSGOButton = new JButton();
		letSGOButton.setText("Let's GO!");
		heroPanel.add(letSGOButton, new GridConstraints(18, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
		if (currentFont == null) return null;
		String resultName;
		if (fontName == null) {
			resultName = currentFont.getName();
		} else {
			Font testFont = new Font(fontName, Font.PLAIN, 10);
			if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
				resultName = fontName;
			} else {
				resultName = currentFont.getName();
			}
		}
		return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return mainPanel;
	}

}

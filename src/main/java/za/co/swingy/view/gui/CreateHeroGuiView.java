package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.CharacterController;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Inventory;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.CreateHeroView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class CreateHeroGuiView extends FrameView implements CreateHeroView {
	private CharacterController controller;
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
	private JTextArea defenceTextArea;
	private JButton OKButton;
	private JTextField nameTextField;
	private JButton explorerButton;
	private JTextArea weaponsTextArea;
	private JTextArea helmsTextArea;
	private JTextArea armorTextArea;
	private JButton warriorButton;
	private JButton knightButton;
	private JButton barbarianButton;
	private JButton letSGOButton;
	private JTextPane alrightHeroWeReTextPane;

	private String name = null;
	private String type = null;

	private Hero hero;

	CreateHeroGuiView() {
		this.initFrame();

		OKButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("OK")) {
					if (nameTextField.getText() != null && !nameTextField.getText().isEmpty()) {
						name = nameTextField.getText();
						topPanel.setVisible(false);
						promptType();
						System.out.println("Name = " + name);

					}
				}
			}
		});

		nameTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameTextField.getText() == null && nameTextField.getText().isEmpty()) {
					OKButton.setEnabled(false);
				} else {
					OKButton.setEnabled(true);
				}
			}
		});
		explorerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "Explorer";
				bottomPanel.setVisible(false);
				controller.generateHero(name, type);
			}
		});
		warriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "Warrior";
				bottomPanel.setVisible(false);
				controller.generateHero(name, type);
			}
		});
		knightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "Knight";
				bottomPanel.setVisible(false);
				controller.generateHero(name, type);
			}
		});
		barbarianButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "Barbarian";
				bottomPanel.setVisible(false);
				controller.generateHero(name, type);
			}
		});
		letSGOButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				heroPanel.setVisible(false);
				GameController controller = GameController.builder().hero(hero).mapView(new MapGuiView()).build();
				controller.showMapView();
			}
		});
	}

	public int printHeroStatus(Hero hero) {
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
		this.bottomPanel.setVisible(false);
		this.heroPanel.setVisible(true);
		return 0;
	}

	public int promptName(CharacterController controller) {
		this.controller = controller;
		this.getFrame().setContentPane(this.mainPanel);
		this.getFrame().pack();
		this.topPanel.setVisible(true);
		this.heroPanel.setVisible(false);
		this.bottomPanel.setVisible(false);
		return 0;
	}

	public void promptType() {
		this.topPanel.setVisible(false);
		this.heroPanel.setVisible(false);
		this.bottomPanel.setVisible(true);
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
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel1.setBackground(new Color(-12566464));
		panel1.setMaximumSize(new Dimension(560, 560));
		panel1.setMinimumSize(new Dimension(560, 560));
		panel1.setPreferredSize(new Dimension(560, 560));
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		panel1.add(mainPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
		topPanel.setBackground(new Color(-12566464));
		mainPanel.add(topPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		topPanel.add(spacer1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		final Spacer spacer2 = new Spacer();
		topPanel.add(spacer2, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JTextPane textPane1 = new JTextPane();
		textPane1.setBackground(new Color(-12566464));
		textPane1.setEditable(false);
		Font textPane1Font = this.$$$getFont$$$(null, Font.BOLD, 16, textPane1.getFont());
		if (textPane1Font != null) textPane1.setFont(textPane1Font);
		textPane1.setForeground(new Color(-4342339));
		textPane1.setText("Greetings hero! Before you begin your journey, \n            let's set up your character!");
		topPanel.add(textPane1, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 5, false));
		final Spacer spacer3 = new Spacer();
		topPanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final Spacer spacer4 = new Spacer();
		topPanel.add(spacer4, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final JLabel label1 = new JLabel();
		label1.setForeground(new Color(-4342339));
		label1.setText("Please enter in your name:");
		topPanel.add(label1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer5 = new Spacer();
		topPanel.add(spacer5, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		OKButton = new JButton();
		OKButton.setText("OK");
		topPanel.add(OKButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer6 = new Spacer();
		topPanel.add(spacer6, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		nameTextField = new JTextField();
		nameTextField.setBackground(new Color(-7237231));
		nameTextField.setForeground(new Color(-13355980));
		nameTextField.setText("");
		topPanel.add(nameTextField, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
		bottomPanel.setBackground(new Color(-12566464));
		mainPanel.add(bottomPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JTextArea textArea1 = new JTextArea();
		textArea1.setBackground(new Color(-12566464));
		textArea1.setEditable(false);
		textArea1.setForeground(new Color(-4342339));
		textArea1.setText("Alright, now it's time to choose a class! What would you like to be?");
		bottomPanel.add(textArea1, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(150, 30), new Dimension(-1, 30), 0, false));
		explorerButton = new JButton();
		explorerButton.setBackground(new Color(-7237231));
		explorerButton.setText("Explorer");
		bottomPanel.add(explorerButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(227, 30), new Dimension(120, -1), 0, false));
		final JLabel label2 = new JLabel();
		label2.setForeground(new Color(-4342339));
		label2.setText("(Attack: 1, Defence: 1, MaxHitPoints: 50)");
		bottomPanel.add(label2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		warriorButton = new JButton();
		warriorButton.setBackground(new Color(-7237231));
		warriorButton.setText("Warrior");
		bottomPanel.add(warriorButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(227, 30), new Dimension(120, -1), 0, false));
		knightButton = new JButton();
		knightButton.setBackground(new Color(-7237231));
		knightButton.setText("Knight");
		bottomPanel.add(knightButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(227, 30), new Dimension(120, -1), 0, false));
		barbarianButton = new JButton();
		barbarianButton.setBackground(new Color(-7237231));
		barbarianButton.setText("Barbarian");
		bottomPanel.add(barbarianButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(227, 30), new Dimension(120, -1), 0, false));
		final JLabel label3 = new JLabel();
		label3.setForeground(new Color(-4342339));
		label3.setText("(Attack: 2, Defence: 2, MaxHitPoints: 50)");
		bottomPanel.add(label3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setForeground(new Color(-4342339));
		label4.setText("(Attack: 3, Defence: 3, MaxHitPoints: 50)");
		bottomPanel.add(label4, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setForeground(new Color(-4342339));
		label5.setText("(Attack: 4, Defence: 4, MaxHitPoints: 50)");
		bottomPanel.add(label5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer7 = new Spacer();
		bottomPanel.add(spacer7, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer8 = new Spacer();
		bottomPanel.add(spacer8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final Spacer spacer9 = new Spacer();
		bottomPanel.add(spacer9, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayoutManager(20, 4, new Insets(0, 0, 0, 0), -1, -1));
		heroPanel.setBackground(new Color(-12566464));
		heroPanel.setForeground(new Color(-4342339));
		heroPanel.setOpaque(true);
		mainPanel.add(heroPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer10 = new Spacer();
		heroPanel.add(spacer10, new GridConstraints(19, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer11 = new Spacer();
		heroPanel.add(spacer11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final Spacer spacer12 = new Spacer();
		heroPanel.add(spacer12, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), new Dimension(10, -1), 0, false));
		final JLabel label6 = new JLabel();
		label6.setBackground(new Color(-12566464));
		Font label6Font = this.$$$getFont$$$(null, Font.BOLD, -1, label6.getFont());
		if (label6Font != null) label6.setFont(label6Font);
		label6.setForeground(new Color(-4342339));
		label6.setText("Name:");
		heroPanel.add(label6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label7 = new JLabel();
		label7.setBackground(new Color(-12566464));
		Font label7Font = this.$$$getFont$$$(null, Font.BOLD, -1, label7.getFont());
		if (label7Font != null) label7.setFont(label7Font);
		label7.setForeground(new Color(-4342339));
		label7.setText("Level:");
		heroPanel.add(label7, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setBackground(new Color(-12566464));
		Font label8Font = this.$$$getFont$$$(null, Font.BOLD, -1, label8.getFont());
		if (label8Font != null) label8.setFont(label8Font);
		label8.setForeground(new Color(-4342339));
		label8.setText("Type:");
		heroPanel.add(label8, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label9 = new JLabel();
		label9.setBackground(new Color(-12566464));
		Font label9Font = this.$$$getFont$$$(null, Font.BOLD, -1, label9.getFont());
		if (label9Font != null) label9.setFont(label9Font);
		label9.setForeground(new Color(-4342339));
		label9.setText("HP:");
		heroPanel.add(label9, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label10 = new JLabel();
		label10.setBackground(new Color(-12566464));
		Font label10Font = this.$$$getFont$$$(null, Font.BOLD, -1, label10.getFont());
		if (label10Font != null) label10.setFont(label10Font);
		label10.setForeground(new Color(-4342339));
		label10.setText("Attack:");
		heroPanel.add(label10, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label11 = new JLabel();
		label11.setBackground(new Color(-12566464));
		Font label11Font = this.$$$getFont$$$(null, Font.BOLD, -1, label11.getFont());
		if (label11Font != null) label11.setFont(label11Font);
		label11.setForeground(new Color(-4342339));
		label11.setText("Defence:");
		heroPanel.add(label11, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label12 = new JLabel();
		label12.setBackground(new Color(-12566464));
		Font label12Font = this.$$$getFont$$$(null, Font.BOLD, -1, label12.getFont());
		if (label12Font != null) label12.setFont(label12Font);
		label12.setForeground(new Color(-4342339));
		label12.setText("Position:");
		heroPanel.add(label12, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label13 = new JLabel();
		label13.setBackground(new Color(-12566464));
		Font label13Font = this.$$$getFont$$$(null, Font.BOLD, -1, label13.getFont());
		if (label13Font != null) label13.setFont(label13Font);
		label13.setForeground(new Color(-4342339));
		label13.setText("______________Inventory______________");
		heroPanel.add(label13, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label14 = new JLabel();
		label14.setBackground(new Color(-12566464));
		Font label14Font = this.$$$getFont$$$(null, Font.BOLD, -1, label14.getFont());
		if (label14Font != null) label14.setFont(label14Font);
		label14.setForeground(new Color(-4342339));
		label14.setText("Slots: ");
		heroPanel.add(label14, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
		final JLabel label15 = new JLabel();
		label15.setBackground(new Color(-12566464));
		Font label15Font = this.$$$getFont$$$(null, Font.BOLD, -1, label15.getFont());
		if (label15Font != null) label15.setFont(label15Font);
		label15.setForeground(new Color(-4342339));
		label15.setText("Armor:");
		heroPanel.add(label15, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		armorTextArea = new JTextArea();
		armorTextArea.setBackground(new Color(-12566464));
		armorTextArea.setCaretColor(new Color(-4342339));
		armorTextArea.setDisabledTextColor(new Color(-4342339));
		armorTextArea.setEditable(false);
		armorTextArea.setForeground(new Color(-4342339));
		armorTextArea.setLineWrap(false);
		armorTextArea.setSelectedTextColor(new Color(-4342339));
		heroPanel.add(armorTextArea, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final JLabel label16 = new JLabel();
		label16.setBackground(new Color(-12566464));
		Font label16Font = this.$$$getFont$$$(null, Font.BOLD, -1, label16.getFont());
		if (label16Font != null) label16.setFont(label16Font);
		label16.setForeground(new Color(-4342339));
		label16.setText("Weapons:");
		heroPanel.add(label16, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		weaponsTextArea = new JTextArea();
		weaponsTextArea.setBackground(new Color(-12566464));
		weaponsTextArea.setCaretColor(new Color(-4342339));
		weaponsTextArea.setDisabledTextColor(new Color(-4342339));
		weaponsTextArea.setEditable(false);
		weaponsTextArea.setForeground(new Color(-4342339));
		weaponsTextArea.setLineWrap(false);
		heroPanel.add(weaponsTextArea, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 5), null, 0, false));
		final JLabel label17 = new JLabel();
		label17.setBackground(new Color(-12566464));
		Font label17Font = this.$$$getFont$$$(null, Font.BOLD, -1, label17.getFont());
		if (label17Font != null) label17.setFont(label17Font);
		label17.setForeground(new Color(-4342339));
		label17.setText("Helms:");
		heroPanel.add(label17, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

}

package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MapView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapGuiView extends FrameView implements MapView {
	private JPanel mainPanel;
	private JTextArea nameTextArea;
	private JTextArea classTextArea;
	private JTextArea levelTextArea;
	private JTextArea xpTextArea;
	private JTextArea hpTextArea;
	private JTextArea defTextArea;
	private JPanel mapPane;
	private JTextArea attTextArea;
	private JPanel heroPanel;
	private JButton northButton;
	private JButton westButton;
	private JButton southButton;
	private JPanel mapViewPanel;
	private JButton eastButton;
	private JPanel falseAlarmPanel;
	private JTextPane FALSEALARMITWASTextPane;
	private JButton falseAlarmButton;
	private JButton inventoryButton;
	private JPanel directionsPanel;
	private JPanel successPanel;
	private JButton successButton;
	private JPanel runAwayPanel;
	private JButton ranAwayButton;
	private JPanel deathPanel;
	private JButton deathButton;

	private GameController controller;
	private int xPosition;
	private int yPosition;

	public MapGuiView() {
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkForCombat(0, -1);
//					updateView();
			}
		});
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkForCombat(1, 0);
			}
		});
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkForCombat(0, 1);
			}
		});
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkForCombat(-1, 0);
			}
		});
		falseAlarmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveHero(xPosition, yPosition);
			}
		});
		successButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveHero(xPosition, yPosition);
			}
		});
		ranAwayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showMapView();
			}
		});
		deathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.returnToMenu();
			}
		});
	}

	public void displayMap(char[][] map, int mapSize) {
		this.mapPane.removeAll();
		this.mapPane.updateUI();
		GridBagConstraints gbc = new GridBagConstraints();
		int size = 300 / mapSize;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				JPanel cellPane = new JPanel();
				cellPane.setPreferredSize(new Dimension(size, size));
				if (map[row][col] == '.') {
					cellPane.setBackground(Color.GRAY);
				} else if (map[row][col] == 'O') {
					cellPane.setBackground(Color.MAGENTA);
				} else if (map[row][col] == 'X') {
					cellPane.setBackground(Color.GREEN);
				}
				Border border = null;
				if (row < mapSize - 1) {
					if (col < mapSize - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.BLACK);
					}
				} else {
					if (col < mapSize - 1) {
						border = new MatteBorder(1, 1, 1, 0, Color.BLACK);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
					}
				}
				cellPane.setBorder(border);
				mapPane.add(cellPane, gbc);
			}
		}
	}

	private void setHeroView(Hero hero) {
		this.nameTextArea.setText(hero.getName());

		this.levelTextArea.setText("" + hero.getLevel());
		this.classTextArea.setText(hero.getClassType());

		if (hero.getEquippedHelm() == null) {
			this.hpTextArea.setText(hero.getHitPoints() + "/" + hero.getMaxHitPoints());
		} else {
			this.hpTextArea.setText((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) +
					"/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
		}

		this.xpTextArea.setText(hero.getExperience() + "/" + hero.getXpRequired());

		if (hero.getEquippedWeapon() == null) {
			this.attTextArea.setText("" + hero.getAttack());
		} else {
			this.attTextArea.setText("" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}

		if (hero.getEquippedArmor() == null) {
			this.defTextArea.setText("" + hero.getDefence());
		} else {
			this.defTextArea.setText("" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}
	}

	private void updateView() {
		this.setHeroView(this.controller.getHero());
		this.displayMap(this.controller.getMap(), this.controller.getMapSize());
		this.mainPanel.setVisible(true);
		this.heroPanel.setVisible(true);
		this.mapViewPanel.setVisible(true);

	}

	public void display(GameController controller) {
		this.controller = controller;
		this.falseAlarmPanel.setVisible(false);
		this.mapViewPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.runAwayPanel.setVisible(false);
		this.deathPanel.setVisible(false);
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.mapViewPanel.setVisible(true);
		this.heroPanel.setVisible(true);
		this.mapPane.setVisible(true);

		this.updateView();
		//Set other viewPanels as false
	}

	public void death() {
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.falseAlarmPanel.setVisible(false);
		this.mapViewPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.runAwayPanel.setVisible(false);
		this.deathPanel.setVisible(true);
	}

	public void runAway() {
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.falseAlarmPanel.setVisible(false);
		this.mapViewPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.runAwayPanel.setVisible(true);
		this.deathPanel.setVisible(false);
	}

	public void success(int x, int y) {
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.xPosition = x;
		this.yPosition = y;
		this.falseAlarmPanel.setVisible(false);
		this.mapViewPanel.setVisible(false);
		this.successPanel.setVisible(true);
		this.runAwayPanel.setVisible(false);
		this.deathPanel.setVisible(false);
	}

	public void falseAlarm(int x, int y) {
//		this.getFrame()
		this.xPosition = x;
		this.yPosition = y;
		this.falseAlarmPanel.setVisible(true);
		this.mapViewPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.runAwayPanel.setVisible(false);
		this.deathPanel.setVisible(false);
	}

	public void createEncounter(Enemy enemy) {
		EncounterGuiView encounterGuiView = new EncounterGuiView(this.controller);
		encounterGuiView.getController().startNewEncounter(enemy);
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
		panel1.setMaximumSize(new Dimension(560, 560));
		panel1.setMinimumSize(new Dimension(560, 560));
		panel1.setPreferredSize(new Dimension(560, 560));
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		mainPanel.setForeground(new Color(-4342339));
		panel1.add(mainPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		falseAlarmPanel = new JPanel();
		falseAlarmPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		falseAlarmPanel.setBackground(new Color(-12566464));
		mainPanel.add(falseAlarmPanel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		FALSEALARMITWASTextPane = new JTextPane();
		FALSEALARMITWASTextPane.setBackground(new Color(-12566464));
		FALSEALARMITWASTextPane.setCaretColor(new Color(-4342339));
		FALSEALARMITWASTextPane.setDisabledTextColor(new Color(-4342339));
		FALSEALARMITWASTextPane.setEditable(false);
		FALSEALARMITWASTextPane.setForeground(new Color(-4342339));
		FALSEALARMITWASTextPane.setText("                  FALSE ALARM!\n     IT WAS JUST A CARDBOARD CUTOUT!");
		falseAlarmPanel.add(FALSEALARMITWASTextPane, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer1 = new Spacer();
		falseAlarmPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer2 = new Spacer();
		falseAlarmPanel.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		falseAlarmPanel.add(spacer3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer4 = new Spacer();
		falseAlarmPanel.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		falseAlarmButton = new JButton();
		falseAlarmButton.setLabel("OK");
		falseAlarmButton.setText("OK");
		falseAlarmPanel.add(falseAlarmButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer5 = new Spacer();
		falseAlarmPanel.add(spacer5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		mapViewPanel = new JPanel();
		mapViewPanel.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
		mapViewPanel.setBackground(new Color(-12566464));
		mainPanel.add(mapViewPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer6 = new Spacer();
		mapViewPanel.add(spacer6, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
		heroPanel.setBackground(new Color(-12566464));
		mapViewPanel.add(heroPanel, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		Font label1Font = this.$$$getFont$$$(null, Font.BOLD, -1, label1.getFont());
		if (label1Font != null) label1.setFont(label1Font);
		label1.setForeground(new Color(-4342339));
		label1.setText("Name: ");
		heroPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		nameTextArea = new JTextArea();
		nameTextArea.setBackground(new Color(-12566464));
		nameTextArea.setEditable(false);
		nameTextArea.setForeground(new Color(-4342339));
		heroPanel.add(nameTextArea, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		final JLabel label2 = new JLabel();
		Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
		if (label2Font != null) label2.setFont(label2Font);
		label2.setForeground(new Color(-4342339));
		label2.setText("Class: ");
		heroPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		classTextArea = new JTextArea();
		classTextArea.setBackground(new Color(-12566464));
		classTextArea.setEditable(false);
		classTextArea.setForeground(new Color(-4342339));
		heroPanel.add(classTextArea, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		final JLabel label3 = new JLabel();
		Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
		if (label3Font != null) label3.setFont(label3Font);
		label3.setForeground(new Color(-4342339));
		label3.setText("HP: ");
		heroPanel.add(label3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		hpTextArea = new JTextArea();
		hpTextArea.setBackground(new Color(-12566464));
		hpTextArea.setEditable(false);
		hpTextArea.setForeground(new Color(-4342339));
		heroPanel.add(hpTextArea, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final JLabel label4 = new JLabel();
		Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
		if (label4Font != null) label4.setFont(label4Font);
		label4.setForeground(new Color(-4342339));
		label4.setText("Level: ");
		heroPanel.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		levelTextArea = new JTextArea();
		levelTextArea.setBackground(new Color(-12566464));
		levelTextArea.setEditable(false);
		levelTextArea.setForeground(new Color(-4342339));
		heroPanel.add(levelTextArea, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		final JLabel label5 = new JLabel();
		Font label5Font = this.$$$getFont$$$(null, Font.BOLD, -1, label5.getFont());
		if (label5Font != null) label5.setFont(label5Font);
		label5.setForeground(new Color(-4342339));
		label5.setText("ATT:");
		heroPanel.add(label5, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		attTextArea = new JTextArea();
		attTextArea.setBackground(new Color(-12566464));
		attTextArea.setEditable(false);
		attTextArea.setForeground(new Color(-4342339));
		heroPanel.add(attTextArea, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final JLabel label6 = new JLabel();
		Font label6Font = this.$$$getFont$$$(null, Font.BOLD, -1, label6.getFont());
		if (label6Font != null) label6.setFont(label6Font);
		label6.setForeground(new Color(-4342339));
		label6.setText("XP: ");
		heroPanel.add(label6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		xpTextArea = new JTextArea();
		xpTextArea.setBackground(new Color(-12566464));
		xpTextArea.setEditable(false);
		xpTextArea.setForeground(new Color(-4342339));
		heroPanel.add(xpTextArea, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		final JLabel label7 = new JLabel();
		Font label7Font = this.$$$getFont$$$(null, Font.BOLD, -1, label7.getFont());
		if (label7Font != null) label7.setFont(label7Font);
		label7.setForeground(new Color(-4342339));
		label7.setText("DEF:");
		heroPanel.add(label7, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		defTextArea = new JTextArea();
		defTextArea.setBackground(new Color(-12566464));
		defTextArea.setEditable(false);
		defTextArea.setForeground(new Color(-4342339));
		heroPanel.add(defTextArea, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final Spacer spacer7 = new Spacer();
		mapViewPanel.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), null, null, 0, false));
		final Spacer spacer8 = new Spacer();
		mapViewPanel.add(spacer8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		mapPane = new JPanel();
		mapPane.setLayout(new GridBagLayout());
		mapPane.setBackground(new Color(-11645362));
		mapViewPanel.add(mapPane, new GridConstraints(3, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, 300), new Dimension(300, 300), new Dimension(300, 300), 0, false));
		final Spacer spacer9 = new Spacer();
		mapViewPanel.add(spacer9, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		inventoryButton = new JButton();
		inventoryButton.setLabel("Inventory");
		inventoryButton.setText("Inventory");
		mapViewPanel.add(inventoryButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JButton button1 = new JButton();
		button1.setLabel("Save  Exit");
		button1.setText("Save  Exit");
		button1.setMnemonic(' ');
		button1.setDisplayedMnemonicIndex(5);
		mapViewPanel.add(button1, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer10 = new Spacer();
		mapViewPanel.add(spacer10, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		directionsPanel = new JPanel();
		directionsPanel.setLayout(new GridBagLayout());
		directionsPanel.setBackground(new Color(-12566464));
		directionsPanel.setForeground(new Color(-4342339));
		mapViewPanel.add(directionsPanel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(220, 110), new Dimension(220, 110), new Dimension(220, 110), 0, false));
		final JTextArea textArea1 = new JTextArea();
		textArea1.setAlignmentX(0.0f);
		textArea1.setAlignmentY(0.5f);
		textArea1.setBackground(new Color(-12566464));
		textArea1.setCaretColor(new Color(-4342339));
		textArea1.setDisabledTextColor(new Color(-4342339));
		textArea1.setEditable(false);
		textArea1.setForeground(new Color(-4342339));
		textArea1.setMaximumSize(new Dimension(80, 50));
		textArea1.setMinimumSize(new Dimension(80, 50));
		textArea1.setPreferredSize(new Dimension(80, 50));
		textArea1.setText("       Λ  \n   <-|->\n       V");
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		directionsPanel.add(textArea1, gbc);
		northButton = new JButton();
		northButton.setText("North");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		directionsPanel.add(northButton, gbc);
		westButton = new JButton();
		westButton.setMaximumSize(new Dimension(80, 30));
		westButton.setMinimumSize(new Dimension(80, 30));
		westButton.setText("West");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		directionsPanel.add(westButton, gbc);
		southButton = new JButton();
		southButton.setText("South");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		directionsPanel.add(southButton, gbc);
		eastButton = new JButton();
		eastButton.setText("East");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		directionsPanel.add(eastButton, gbc);
		final JTextArea textArea2 = new JTextArea();
		textArea2.setBackground(new Color(-12566464));
		textArea2.setCaretColor(new Color(-4342339));
		textArea2.setEditable(false);
		textArea2.setForeground(new Color(-4342339));
		textArea2.setText("Your goal is to leave all the maps, or level up lo level 6.\nYou can move: NORTH, SOUTH, EAST or WEST\nOr you can go to your INVENTORY or SAVE to save and exit this game.");
		mapViewPanel.add(textArea2, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(60, 50), null, 0, false));
		final Spacer spacer11 = new Spacer();
		mapViewPanel.add(spacer11, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
		final Spacer spacer12 = new Spacer();
		mapViewPanel.add(spacer12, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		final Spacer spacer13 = new Spacer();
		mapViewPanel.add(spacer13, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), null, null, 0, false));
		successPanel = new JPanel();
		successPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		successPanel.setBackground(new Color(-12566464));
		mainPanel.add(successPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane1 = new JTextPane();
		textPane1.setBackground(new Color(-12566464));
		textPane1.setCaretColor(new Color(-10109628));
		textPane1.setDisabledTextColor(new Color(-10109628));
		textPane1.setEditable(false);
		textPane1.setForeground(new Color(-10109628));
		textPane1.setText("THE HERO DEFEATED THEIR OPPONENT!");
		successPanel.add(textPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer14 = new Spacer();
		successPanel.add(spacer14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer15 = new Spacer();
		successPanel.add(spacer15, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer16 = new Spacer();
		successPanel.add(spacer16, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer17 = new Spacer();
		successPanel.add(spacer17, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		successButton = new JButton();
		successButton.setLabel("OK");
		successButton.setText("OK");
		successPanel.add(successButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer18 = new Spacer();
		successPanel.add(spacer18, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		runAwayPanel = new JPanel();
		runAwayPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		runAwayPanel.setBackground(new Color(-12566464));
		mainPanel.add(runAwayPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane2 = new JTextPane();
		textPane2.setBackground(new Color(-12566464));
		textPane2.setCaretColor(new Color(-12948803));
		textPane2.setDisabledTextColor(new Color(-12948803));
		textPane2.setEditable(false);
		textPane2.setForeground(new Color(-12948803));
		textPane2.setText("THE HERO RAN AWAY!");
		runAwayPanel.add(textPane2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer19 = new Spacer();
		runAwayPanel.add(spacer19, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer20 = new Spacer();
		runAwayPanel.add(spacer20, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer21 = new Spacer();
		runAwayPanel.add(spacer21, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer22 = new Spacer();
		runAwayPanel.add(spacer22, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		ranAwayButton = new JButton();
		ranAwayButton.setLabel("OK");
		ranAwayButton.setText("OK");
		runAwayPanel.add(ranAwayButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer23 = new Spacer();
		runAwayPanel.add(spacer23, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		deathPanel = new JPanel();
		deathPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		deathPanel.setBackground(new Color(-12566464));
		mainPanel.add(deathPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane3 = new JTextPane();
		textPane3.setBackground(new Color(-12566464));
		textPane3.setCaretColor(new Color(-4380623));
		textPane3.setDisabledTextColor(new Color(-4380623));
		textPane3.setEditable(false);
		textPane3.setForeground(new Color(-4380623));
		textPane3.setText("THE HERO HAS DIED?!");
		deathPanel.add(textPane3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer24 = new Spacer();
		deathPanel.add(spacer24, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer25 = new Spacer();
		deathPanel.add(spacer25, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer26 = new Spacer();
		deathPanel.add(spacer26, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer27 = new Spacer();
		deathPanel.add(spacer27, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		deathButton = new JButton();
		deathButton.setLabel("OK");
		deathButton.setText("OK");
		deathPanel.add(deathButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer28 = new Spacer();
		deathPanel.add(spacer28, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
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

package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.MapView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.pow;

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
	private JPanel movementPanel;
	private JTextArea λVTextArea;
	private JButton northButton;
	private JButton westButton;
	private JButton southButton;
	private JButton inventoryButton;
	private JButton button2;
	private JTextArea yourGoalIsToTextArea;
	private JPanel ouputPanel;
	private JTextArea outputTextArea;
	private JPanel mapViewPanel;
	private JButton eastButton;

	private GameController controller;

	public MapGuiView() {
//		this.initFrame();
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.checkForCombat(0,-1) < 0) {
					//Go back to menu
				} else {
					updateView();
				}
			}
		});
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.checkForCombat(1,0) < 0) {
					//Go back to menu
				} else {
					updateView();
				}
			}
		});
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.checkForCombat(0,1) < 0) {
					//Go back to menu
				} else {
					updateView();
				}
			}
		});
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.checkForCombat(-1,0) < 0) {
					//Go back to menu
				} else {
					updateView();
				}
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

		this.xpTextArea.setText(hero.getExperience() + "/" + (hero.getLevel() * 1000 - ((int) pow(hero.getLevel() - 1, 2) * 450)));

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

	private void		updateView() {
		this.setHeroView(this.controller.getHero());
		this.displayMap(this.controller.getMap(), this.controller.getMapSize());
		this.mainPanel.setVisible(true);
		this.heroPanel.setVisible(true);
		this.mapViewPanel.setVisible(true);

	}

	public int display(GameController controller) {
		this.controller = controller;

		this.getFrame().setContentPane(this.mainPanel);
		this.updateView();
		//Set other viewPanels as false

		//Would return if hero died
		return 0;
	}

	public void death() {
	}

	public void runAway() {
	}

	public void success() {
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
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		mainPanel.setForeground(new Color(-4342339));
		panel1.add(mainPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer1 = new Spacer();
		mainPanel.add(spacer1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
		final Spacer spacer2 = new Spacer();
		mainPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		mainPanel.add(spacer3, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), null, null, 0, false));
		mapViewPanel = new JPanel();
		mapViewPanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
		mapViewPanel.setBackground(new Color(-12566464));
		mainPanel.add(mapViewPanel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		movementPanel = new JPanel();
		movementPanel.setLayout(new GridBagLayout());
		movementPanel.setBackground(new Color(-12566464));
		movementPanel.setForeground(new Color(-4342339));
		mapViewPanel.add(movementPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(220, 110), new Dimension(220, 110), new Dimension(220, 110), 0, false));
		λVTextArea = new JTextArea();
		λVTextArea.setAlignmentX(0.0f);
		λVTextArea.setAlignmentY(0.5f);
		λVTextArea.setBackground(new Color(-12566464));
		λVTextArea.setCaretColor(new Color(-4342339));
		λVTextArea.setDisabledTextColor(new Color(-4342339));
		λVTextArea.setEditable(false);
		λVTextArea.setForeground(new Color(-4342339));
		λVTextArea.setMaximumSize(new Dimension(80, 50));
		λVTextArea.setMinimumSize(new Dimension(80, 50));
		λVTextArea.setPreferredSize(new Dimension(80, 50));
		λVTextArea.setText("       Λ  \n   <-|->\n       V");
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		movementPanel.add(λVTextArea, gbc);
		northButton = new JButton();
		northButton.setText("North");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		movementPanel.add(northButton, gbc);
		westButton = new JButton();
		westButton.setMaximumSize(new Dimension(80, 30));
		westButton.setMinimumSize(new Dimension(80, 30));
		westButton.setText("West");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		movementPanel.add(westButton, gbc);
		southButton = new JButton();
		southButton.setText("South");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		movementPanel.add(southButton, gbc);
		final JButton button1 = new JButton();
		button1.setText("East");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		movementPanel.add(button1, gbc);
		mapPane = new JPanel();
		mapPane.setLayout(new GridBagLayout());
		mapPane.setBackground(new Color(-11645362));
		mapViewPanel.add(mapPane, new GridConstraints(1, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, 300), new Dimension(300, 300), new Dimension(300, 300), 0, false));
		inventoryButton = new JButton();
		inventoryButton.setLabel("Inventory");
		inventoryButton.setText("Inventory");
		mapViewPanel.add(inventoryButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		button2 = new JButton();
		button2.setLabel("Save  Exit");
		button2.setText("Save  Exit");
		button2.setMnemonic(' ');
		button2.setDisplayedMnemonicIndex(5);
		mapViewPanel.add(button2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer4 = new Spacer();
		mapViewPanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		yourGoalIsToTextArea = new JTextArea();
		yourGoalIsToTextArea.setBackground(new Color(-12566464));
		yourGoalIsToTextArea.setCaretColor(new Color(-4342339));
		yourGoalIsToTextArea.setEditable(false);
		yourGoalIsToTextArea.setForeground(new Color(-4342339));
		yourGoalIsToTextArea.setText("Your goal is to leave all the maps, or level up lo level 6.\nYou can move: NORTH, SOUTH, EAST or WEST\nOr you can go to your INVENTORY or SAVE to save and exit this game.");
		mapViewPanel.add(yourGoalIsToTextArea, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(60, 50), null, 0, false));
		ouputPanel = new JPanel();
		ouputPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		ouputPanel.setBackground(new Color(-6381922));
		ouputPanel.setForeground(new Color(-12566464));
		mapViewPanel.add(ouputPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 180), new Dimension(-1, 180), new Dimension(-1, 180), 0, false));
		outputTextArea = new JTextArea();
		outputTextArea.setBackground(new Color(-6381922));
		outputTextArea.setCaretColor(new Color(-12566464));
		outputTextArea.setEditable(false);
		outputTextArea.setForeground(new Color(-12566464));
		ouputPanel.add(outputTextArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer5 = new Spacer();
		mainPanel.add(spacer5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), null, null, 0, false));
		heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
		heroPanel.setBackground(new Color(-12566464));
		mainPanel.add(heroPanel, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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

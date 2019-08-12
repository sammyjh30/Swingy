package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.GameController;
import za.co.swingy.controller.InventoryController;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.InventoryView;
import za.co.swingy.view.console.*;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class InventoryGuiView extends FrameView implements InventoryView {
	private JPanel mainPanel;
	private JTextArea inventoryTextArea;
	private JTextArea capacityTextArea;
	private JButton returnButton;
	private JButton switchButton;
	private JPanel helmsPanel;
	private JScrollPane helmsScrollPanel;
	private JScrollPane armorScrollPane;
	private JScrollPane weaponScrollPanel;
	private JPanel weaponPanel;
	private JPanel testPanel;
	private JScrollPane armorScrollPanel;
	private JList armorList;

	@NotNull
	private InventoryController controller;

	public InventoryGuiView(GameController gameController) {
		this.controller = InventoryController.builder().inventoryView(this).hero(gameController.getHero()).gameController(gameController).build();
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getGameController().getMapView().display(controller.getGameController());
			}
		});
		switchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CreateHero
				CreateHeroConsoleView createHeroConsoleView = new CreateHeroConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setCreateHeroView(createHeroConsoleView);

				//LoadHero
				LoadFileConsoleView loadFileConsoleView = new LoadFileConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setLoadFileView(loadFileConsoleView);

				//MenuView
				MenuConsoleView menuConsoleView = new MenuConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setMenuView(menuConsoleView);

				//MapView
				MapConsoleView mapConsoleView = new MapConsoleView(controller.getGameController());
				controller.getGameController().setMapView(mapConsoleView);

				//InventoryView
				InventoryConsoleView inventoryConsoleView = new InventoryConsoleView(controller);
				controller.setInventoryView(inventoryConsoleView);

				System.out.print("\033[H\033[2J");
				System.out.flush();
				mainPanel.setVisible(false); //you can't see me!
				getFrame().dispose();

				controller.getInventoryView().display();
			}
		});
	}

	public InventoryGuiView(InventoryController inventoryController) {
		this.controller = inventoryController;
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getGameController().getMapView().display(controller.getGameController());
			}
		});
		switchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CreateHero
				CreateHeroConsoleView createHeroConsoleView = new CreateHeroConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setCreateHeroView(createHeroConsoleView);

				//LoadHero
				LoadFileConsoleView loadFileConsoleView = new LoadFileConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setLoadFileView(loadFileConsoleView);

				//MenuView
				MenuConsoleView menuConsoleView = new MenuConsoleView(controller.getGameController().getCharacterController());
				controller.getGameController().getCharacterController().setMenuView(menuConsoleView);

				//MapView
				MapConsoleView mapConsoleView = new MapConsoleView(controller.getGameController());
				controller.getGameController().setMapView(mapConsoleView);

				//InventoryView
				InventoryConsoleView inventoryConsoleView = new InventoryConsoleView(controller);
				controller.setInventoryView(inventoryConsoleView);

				System.out.print("\033[H\033[2J");
				System.out.flush();
				mainPanel.setVisible(false); //you can't see me!
				getFrame().dispose();

				controller.getInventoryView().display();
			}
		});
	}

	private void setInventoryValues() {
		int list = 0;

		this.capacityTextArea.setText(this.controller.getHero().getInventory().getUsedSlots() + "/" + this.controller.getHero().getInventory().getMaxSlots());

		//Armor
		this.testPanel.removeAll();
		this.testPanel.updateUI();
		for (int i = 0; i < this.controller.getHero().getInventory().getArmors().size(); i++) {
			JPanel armorIterPanel = new JPanel();
			armorIterPanel.setLayout(new GridBagLayout());

			Armor a = this.controller.getHero().getInventory().getArmors().get(i);
			String name = a.getName() + "\nlevel: " + a.getLevel() + " defence increase: " + a.getDefenceIncrease();

			JTextArea nameTextArea = new JTextArea();
			nameTextArea.setText(name);
			nameTextArea.setBackground(new Color(-11645362));
			nameTextArea.setForeground(new Color(-4342339));
			nameTextArea.setMinimumSize(new Dimension(300, 30));
			nameTextArea.setMaximumSize(new Dimension(300, 30));
			nameTextArea.setPreferredSize(new Dimension(300, 30));
			nameTextArea.setEditable(false);

			armorIterPanel.setMinimumSize(new Dimension(460, 30));
			armorIterPanel.setMaximumSize(new Dimension(460, 30));
			armorIterPanel.setPreferredSize(new Dimension(460, 30));
			GridBagConstraints armorIterGbd = new GridBagConstraints();
			armorIterGbd.gridx = 0;
			armorIterGbd.gridy = i;
			armorIterPanel.add(nameTextArea, armorIterGbd);

			JButton equipButton = new JButton();
			equipButton.putClientProperty("id", i + 1 + list);
			equipButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.equip("" + (index));
				}
			});
			if (i == this.controller.getHero().getInventory().getEquippedArmorIndex()) {
				equipButton.setText("EQUIPPED");
				equipButton.setEnabled(false);
			} else {
				equipButton.setText("EQUIP");
			}
			armorIterGbd.gridx = 1;
			armorIterGbd.gridy = i;
			armorIterPanel.add(equipButton, armorIterGbd);


			JButton deleteButton = new JButton();
			deleteButton.setText("DELETE");
			deleteButton.putClientProperty("id", i + 1 + list);
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.delete("" + (index));
				}
			});
			armorIterGbd.gridx = 2;
			armorIterGbd.gridy = i;
			armorIterPanel.add(deleteButton, armorIterGbd);

			armorIterPanel.setBackground(new Color(-11645362));
			armorIterPanel.setForeground(new Color(-4342339));

			armorIterGbd.gridx = 0;
			armorIterGbd.gridy = i;
			this.testPanel.setVisible(true);
			this.testPanel.add(armorIterPanel, armorIterGbd);

//			armorModel.addRow(new Object[]{name, equipButton, deleteButton});
		}
		list = this.controller.getHero().getInventory().getArmors().size();

		//Weapon
		this.weaponPanel.removeAll();
		this.weaponPanel.updateUI();
		GridBagConstraints gbcWeapon = new GridBagConstraints();
		for (int i = 0; i < this.controller.getHero().getInventory().getWeapons().size(); i++) {
			Weapon w = this.controller.getHero().getInventory().getWeapons().get(i);

			//Display information
			gbcWeapon.gridx = 0;
			gbcWeapon.gridy = i;
			JTextArea weaponTextArea = new JTextArea();
			weaponTextArea.setBackground(new Color(-11645362));
			weaponTextArea.setCaretColor(new Color(-4342339));
			weaponTextArea.setForeground(new Color(-4342339));
			weaponTextArea.setText(w.getName() + "\nlevel: " + w.getLevel() + " Attack increase: " + w.getAttackIncrease());
			weaponPanel.add(weaponTextArea, gbcWeapon);

			//Show equip button
			gbcWeapon.gridx = 1;
			gbcWeapon.gridy = i;
			JButton equipButton = new JButton();
			equipButton.putClientProperty("id", i + 1 + list);
			equipButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.equip("" + (index));
				}
			});
			if (i == this.controller.getHero().getInventory().getEquippedWeaponIndex()) {
				equipButton.setText("EQUIPPED");
				equipButton.setEnabled(false);
			} else {
				equipButton.setText("EQUIP");
			}
			weaponPanel.add(equipButton, gbcWeapon);

			//DeleteButton
			gbcWeapon.gridx = 2;
			gbcWeapon.gridy = i;
			JButton deleteButton = new JButton();
			deleteButton.setText("DELETE");
			deleteButton.putClientProperty("id", i + 1 + list);
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.delete("" + (index));
				}
			});
			weaponPanel.add(deleteButton, gbcWeapon);
		}
		list += this.controller.getHero().getInventory().getWeapons().size();

		//Helmets
		this.helmsPanel.removeAll();
		this.helmsPanel.updateUI();
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < this.controller.getHero().getInventory().getHelms().size(); i++) {
			Helm h = this.controller.getHero().getInventory().getHelms().get(i);

			//Display information
			gbc.gridx = 0;
			gbc.gridy = i;
			JTextArea helmTextArea = new JTextArea();
			helmTextArea.setBackground(new Color(-11645362));
			helmTextArea.setCaretColor(new Color(-4342339));
			helmTextArea.setForeground(new Color(-4342339));
			helmTextArea.setText(h.getName() + "\nlevel: " + h.getLevel() + " hitPoint increase: " + h.getHitPointIncrease());
			helmsPanel.add(helmTextArea, gbc);

			//Show equip button
			gbc.gridx = 1;
			gbc.gridy = i;
			JButton equipButton = new JButton();
			equipButton.putClientProperty("id", i + 1 + list);
			equipButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.equip("" + (index));
				}
			});
			if (i == this.controller.getHero().getInventory().getEquippedHelmIndex()) {
				equipButton.setText("EQUIPPED");
				equipButton.setEnabled(false);
			} else {
				equipButton.setText("EQUIP");
			}
			helmsPanel.add(equipButton, gbc);

			//DeleteButton
			gbc.gridx = 2;
			gbc.gridy = i;
			JButton deleteButton = new JButton();
			deleteButton.setText("DELETE");
			deleteButton.putClientProperty("id", i + 1 + list);
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) ((JButton) e.getSource()).getClientProperty("id");
					controller.delete("" + (index));
				}
			});
			helmsPanel.add(deleteButton, gbc);
		}

	}

	public void display() {
		this.setInventoryValues();
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
//		this.armorScrollPane.setVisible(true);
//		this.armorPanel.setVisible(true);
		this.weaponScrollPanel.setVisible(true);
		this.weaponPanel.setVisible(true);
		this.helmsScrollPanel.setVisible(true);
		this.helmsPanel.setVisible(true);
		this.getFrame().pack();
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
		mainPanel.setLayout(new GridLayoutManager(11, 4, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		mainPanel.setForeground(new Color(-4342339));
		mainPanel.setMaximumSize(new Dimension(560, 560));
		mainPanel.setMinimumSize(new Dimension(560, 560));
		mainPanel.setPreferredSize(new Dimension(560, 560));
		inventoryTextArea = new JTextArea();
		inventoryTextArea.setBackground(new Color(-12566464));
		inventoryTextArea.setCaretColor(new Color(-4342339));
		inventoryTextArea.setEditable(false);
		Font inventoryTextAreaFont = this.$$$getFont$$$(null, Font.BOLD, -1, inventoryTextArea.getFont());
		if (inventoryTextAreaFont != null) inventoryTextArea.setFont(inventoryTextAreaFont);
		inventoryTextArea.setForeground(new Color(-4342339));
		inventoryTextArea.setText("Inventory");
		mainPanel.add(inventoryTextArea, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 25), new Dimension(100, 25), new Dimension(100, 25), 0, false));
		final Spacer spacer1 = new Spacer();
		mainPanel.add(spacer1, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		final Spacer spacer2 = new Spacer();
		mainPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
		final Spacer spacer3 = new Spacer();
		mainPanel.add(spacer3, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer4 = new Spacer();
		mainPanel.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
		final JTextArea textArea1 = new JTextArea();
		textArea1.setBackground(new Color(-12566464));
		textArea1.setCaretColor(new Color(-4342339));
		textArea1.setEditable(false);
		Font textArea1Font = this.$$$getFont$$$(null, Font.BOLD, -1, textArea1.getFont());
		if (textArea1Font != null) textArea1.setFont(textArea1Font);
		textArea1.setForeground(new Color(-4342339));
		textArea1.setText("Armor:");
		mainPanel.add(textArea1, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		weaponScrollPanel = new JScrollPane();
		weaponScrollPanel.setBackground(new Color(-11645362));
		weaponScrollPanel.setForeground(new Color(-11645362));
		mainPanel.add(weaponScrollPanel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, 100), new Dimension(480, 100), new Dimension(480, -1), 0, false));
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridBagLayout());
		weaponPanel.setBackground(new Color(-11645362));
		weaponPanel.setForeground(new Color(-4342339));
		weaponScrollPanel.setViewportView(weaponPanel);
		helmsScrollPanel = new JScrollPane();
		helmsScrollPanel.setBackground(new Color(-11645362));
		helmsScrollPanel.setForeground(new Color(-11645362));
		mainPanel.add(helmsScrollPanel, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, 100), new Dimension(480, 100), new Dimension(480, -1), 0, false));
		helmsPanel = new JPanel();
		helmsPanel.setLayout(new GridBagLayout());
		helmsPanel.setBackground(new Color(-11645362));
		helmsPanel.setForeground(new Color(-4342339));
		helmsScrollPanel.setViewportView(helmsPanel);
		final JTextArea textArea2 = new JTextArea();
		textArea2.setBackground(new Color(-12566464));
		textArea2.setCaretColor(new Color(-4342339));
		textArea2.setEditable(false);
		Font textArea2Font = this.$$$getFont$$$(null, Font.BOLD, -1, textArea2.getFont());
		if (textArea2Font != null) textArea2.setFont(textArea2Font);
		textArea2.setForeground(new Color(-4342339));
		textArea2.setText("Weapon:");
		mainPanel.add(textArea2, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		final JTextArea textArea3 = new JTextArea();
		textArea3.setBackground(new Color(-12566464));
		textArea3.setCaretColor(new Color(-4342339));
		textArea3.setEditable(false);
		Font textArea3Font = this.$$$getFont$$$(null, Font.BOLD, -1, textArea3.getFont());
		if (textArea3Font != null) textArea3.setFont(textArea3Font);
		textArea3.setForeground(new Color(-4342339));
		textArea3.setText("Helm:");
		mainPanel.add(textArea3, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		returnButton = new JButton();
		returnButton.setEnabled(true);
		returnButton.setText("Return");
		mainPanel.add(returnButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		switchButton = new JButton();
		switchButton.setText("Switch");
		mainPanel.add(switchButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		capacityTextArea = new JTextArea();
		capacityTextArea.setBackground(new Color(-12566464));
		capacityTextArea.setCaretColor(new Color(-4342339));
		capacityTextArea.setEditable(false);
		capacityTextArea.setForeground(new Color(-4342339));
		mainPanel.add(capacityTextArea, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 25), new Dimension(150, 25), new Dimension(-1, 25), 0, false));
		final JTextArea textArea4 = new JTextArea();
		textArea4.setBackground(new Color(-12566464));
		textArea4.setCaretColor(new Color(-4342339));
		textArea4.setEditable(false);
		Font textArea4Font = this.$$$getFont$$$(null, Font.BOLD, -1, textArea4.getFont());
		if (textArea4Font != null) textArea4.setFont(textArea4Font);
		textArea4.setForeground(new Color(-4342339));
		textArea4.setText("Capacity");
		mainPanel.add(textArea4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 25), new Dimension(150, 25), new Dimension(-1, 25), 0, false));
		armorScrollPanel = new JScrollPane();
		mainPanel.add(armorScrollPanel, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, 100), new Dimension(480, 100), new Dimension(480, -1), 0, false));
		testPanel = new JPanel();
		testPanel.setLayout(new GridBagLayout());
		testPanel.setBackground(new Color(-11645362));
		armorScrollPanel.setViewportView(testPanel);
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

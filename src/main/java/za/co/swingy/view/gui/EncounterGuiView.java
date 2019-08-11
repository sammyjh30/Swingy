package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.Getter;
import lombok.Setter;
import za.co.swingy.controller.EncounterController;
import za.co.swingy.controller.GameController;
import za.co.swingy.model.characters.Enemy;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.model.items.Armor;
import za.co.swingy.model.items.Helm;
import za.co.swingy.model.items.Weapon;
import za.co.swingy.view.EncounterView;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class EncounterGuiView extends FrameView implements EncounterView {
	@NotNull
	private EncounterController controller;
	@NotNull
	public GameController gameController;

	private String				item = null;
	private int					boost = 0;
	private Armor				armor = null;
	private Weapon				weapon = null;
	private Helm				helm = null;

	private JPanel mainPanel;
	private JPanel encounterPanel;
	private JPanel heroPanel;
	private JTextArea nameTextArea;
	private JTextArea classTextArea;
	private JTextArea levelTextArea;
	private JTextArea hpTextArea;
	private JTextArea attTextArea;
	private JTextArea defTextArea;
	private JPanel enemyPanel;
	private JTextArea enemyNameTextArea;
	private JTextArea enemyLevelTextArea;
	private JTextArea enemyHPTextArea;
	private JTextArea enemyAttTextArea;
	private JTextArea enemyDefTextArea;
	private JButton fightButton;
	private JButton simulateButton;
	private JButton runButton;
	private JButton switchButton;
	private JTextPane battleHistoryTextPane;
	private JLabel enemyNameLabel;
	private JLabel enemyClassLabel;
	private JTextArea enemyClassTextArea;
	private JLabel enemyLevelLabel;
	private JLabel enemyHPLabel;
	private JLabel enemyAttLabel;
	private JLabel enemyDefLabel;
	private JTextPane enemyStatsTextPane;
	private JTextPane heroStatsTestPane;
	private JScrollPane battleHistoryScrollPanel;
	private JTextArea youHave3ChoicesTextArea;
	private JPanel runFailedPanel;
	private JButton runFailedButton;
	private JPanel battleHistoryPanel;
	private JPanel battleHistoryHeroPanel;
	private JPanel battleHistoryEnemyPanel;
	private JScrollPane battleHistoryFinalScrollPane;
	private JTextPane battleHistoryFinalTextPane;
	private JButton battleHistoryButton;
	private JLabel battleHistoryEnemyNameLabel;
	private JTextArea battleHistoryEnemyNameTextArea;
	private JLabel battleHistoryEnemyClassLabel;
	private JTextArea battleHistoryEnemyClassTextArea;
	private JLabel battleHistoryEnemyLevelLabel;
	private JTextArea battleHistoryEnemyLevelTextArea;
	private JLabel battleHistoryEnemyHpLabel;
	private JLabel battleHistoryEnemyAttLabel;
	private JLabel battleHistoryEnemyDefLabel;
	private JTextArea battleHistoryEnemyHpTextArea;
	private JTextArea battleHistoryEnemyAttTextArea;
	private JTextArea battleHistoryEnemyDefTextArea;
	private JLabel battleHistoryHeroNameLabel;
	private JTextArea battleHistoryHeroNameTextArea;
	private JLabel battleHistoryHeroClassLabel;
	private JTextArea battleHistoryHeroClassTextArea;
	private JLabel battleHistoryHeroLevelLabel;
	private JTextArea battleHistoryHeroLevelTextArea;
	private JLabel battleHistoryHeroHpLabel;
	private JLabel battleHistoryHeroAttLabel;
	private JLabel battleHistoryHeroDefLabel;
	private JTextArea battleHistoryHeroHpTextArea;
	private JTextArea battleHistoryHeroAttTextArea;
	private JTextArea battleHistoryHeroDefTextArea;
	private JTextPane THEBATTLEISOVERTextPane;
	private JPanel successPanel;
	private JButton levelUpButton;
	private JPanel itemDropPanel;
	private JTextArea itemDropTextArea;
	private JTextPane wouldYouLikeToTextPane;
	private JButton itemNoButton;
	private JButton itemYesButton;
	private JPanel armorDropPanel;
	private JButton armorYesButton;
	private JButton armorNoButton;
	private JTextArea armorDropTextArea;
	private JPanel weaponDropPanel;
	private JTextArea weaponDropTextArea;
	private JButton weaponDropYesButton;
	private JButton weaponDropNoButton;
	private JPanel helmDropPanel;
	private JTextArea helmDropTextArea;
	private JButton helmDropYesButton;
	private JButton helmDropNoButton;
	private JPanel inventoryFullPanel;
	private JButton deathButton;

	public EncounterGuiView(GameController gameController) {
		this.gameController = gameController;
		this.controller = EncounterController.builder().encounterView(this).hero(gameController.getHero()).gameController(gameController).build();


		fightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fight();
			}
		});
		simulateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.simulate();
			}
		});
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.run();
			}
		});
		switchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		runFailedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display();
			}
		});
		battleHistoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.randomDrop();
			}
		});
		levelUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getGameController().removeEnemy(controller.getEnemy());
				controller.victory();
			}
		});
		itemYesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//If inventory
				int boostAmount = boost;
				if (item.equalsIgnoreCase("Inventory")) {
					controller.addInventoryBoost(boost);
				} else if (item.equalsIgnoreCase("Health")) {
					controller.addHealthBoost(boost);
				}
			}
		});
		itemNoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkLevel();
			}
		});
		armorYesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addArmor(armor);
			}
		});
		armorNoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkLevel();
			}
		});
		weaponDropYesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addWeapon(weapon);
			}
		});
		weaponDropNoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkLevel();
			}
		});
		helmDropYesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addHelm(helm);
			}
		});
		helmDropNoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkLevel();
			}
		});
		deathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.checkLevel();
			}
		});
	}

	private void			setHero(Hero hero) {
		if (hero.getName().length() > 10) {
			this.nameTextArea.setText(hero.getName().substring(0, 10));
		} else {
			this.nameTextArea.setText(hero.getName());
		}
		this.classTextArea.setText(hero.getClassType());
		this.levelTextArea.setText("" + hero.getLevel());
		if (this.controller.getHero().getEquippedHelm() == null) {
			if (hero.getHitPoints() < 0) {
				this.hpTextArea.setText("0/" + hero.getMaxHitPoints());
			} else {
				this.hpTextArea.setText(hero.getHitPoints() + "/" + hero.getMaxHitPoints());
			}
		} else {
			if ((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) < 0) {
				this.hpTextArea.setText("0/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
			} else {
				this.hpTextArea.setText((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "/" +
						(hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
			}
		}
		if (this.controller.getHero().getEquippedWeapon() == null) {
			this.attTextArea.setText("" + hero.getAttack());
		} else {
			this.attTextArea.setText("" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}
		if (this.controller.getHero().getEquippedArmor() == null) {
			this.defTextArea.setText("" + hero.getDefence());
		} else {
			this.defTextArea.setText("" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}
	}

	private void			setEnemy(Enemy enemy) {
		if (enemy.getEnemyName().length() > 10) {
			this.enemyNameTextArea.setText(enemy.getEnemyName().substring(0, 10) + "the" + enemy.getEnemyType());
		} else {
			this.enemyNameTextArea.setText(enemy.getEnemyName() + " the " + enemy.getEnemyType());
		}
		this.enemyLevelTextArea.setText("" + enemy.getLevel());
		if (enemy.getHitPoints() < 0) {
			this.enemyHPTextArea.setText("0/" + enemy.getMaxHitPoints());
		} else {
			this.enemyHPTextArea.setText(enemy.getHitPoints() + "/" + enemy.getMaxHitPoints());
		}
		this.enemyAttTextArea.setText("" + enemy.getAttack());
		this.enemyDefTextArea.setText("" + enemy.getDefence());
	}

	public void				display() {
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.encounterPanel.setVisible(true);
		this.heroPanel.setVisible(true);
		this.enemyPanel.setVisible(true);
		this.battleHistoryScrollPanel.setVisible(true);
		this.runFailedPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.itemDropPanel.setVisible(false);
		this.armorDropPanel.setVisible(false);
		this.weaponDropPanel.setVisible(false);
		this.helmDropPanel.setVisible(false);
		this.inventoryFullPanel.setVisible(false);

		this.setHero(this.controller.getHero());
		this.setEnemy(this.controller.getEnemy());
		this.battleHistoryTextPane.setText("\n____BATTLE HISTORY____\n" + this.controller.getRoundUpdate() + "______________________\n");

	}

	public void				runFailed() {
		this.encounterPanel.setVisible(false);
		this.heroPanel.setVisible(false);
		this.enemyPanel.setVisible(false);
		this.battleHistoryScrollPanel.setVisible(false);
		this.runFailedPanel.setVisible(true);
		this.battleHistoryPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.battleHistoryFinalScrollPane.setVisible(false);
	}

	private void			setBattleHistoryHero(Hero hero) {
		if (hero.getName().length() > 10) {
			this.battleHistoryHeroNameTextArea.setText(hero.getName().substring(0, 10));
		} else {
			this.battleHistoryHeroNameTextArea.setText(hero.getName());
		}
		this.battleHistoryHeroClassTextArea.setText(hero.getClassType());
		this.battleHistoryHeroLevelTextArea.setText("" + hero.getLevel());
		if (this.controller.getHero().getEquippedHelm() == null) {
			if (hero.getHitPoints() < 0) {
				this.battleHistoryHeroHpTextArea.setText("0/" + hero.getMaxHitPoints());
			} else {
				this.battleHistoryHeroHpTextArea.setText(hero.getHitPoints() + "/" + hero.getMaxHitPoints());
			}
		} else {
			if ((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) < 0) {
				this.battleHistoryHeroHpTextArea.setText("0/" + (hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
			} else {
				this.battleHistoryHeroHpTextArea.setText((hero.getHitPoints() + hero.getEquippedHelm().getHitPointIncrease()) + "/" +
						(hero.getMaxHitPoints() + hero.getEquippedHelm().getHitPointIncrease()));
			}
		}
		if (this.controller.getHero().getEquippedWeapon() == null) {
			this.battleHistoryHeroAttTextArea.setText("" + hero.getAttack());
		} else {
			this.battleHistoryHeroAttTextArea.setText("" + (hero.getAttack() + hero.getEquippedWeapon().getAttackIncrease()));
		}
		if (this.controller.getHero().getEquippedArmor() == null) {
			this.battleHistoryHeroDefTextArea.setText("" + hero.getDefence());
		} else {
			this.battleHistoryHeroDefTextArea.setText("" + (hero.getDefence() + hero.getEquippedArmor().getDefenceIncrease()));
		}
	}

	private void			setBattleHistoryEnemy(Enemy enemy) {
		if (enemy.getEnemyName().length() > 10) {
			this.battleHistoryEnemyNameTextArea.setText(enemy.getEnemyName().substring(0, 10) + "the" + enemy.getEnemyType());
		} else {
			this.battleHistoryEnemyNameTextArea.setText(enemy.getEnemyName() + " the " + enemy.getEnemyType());
		}
		this.battleHistoryEnemyLevelTextArea.setText("" + enemy.getLevel());
		if (enemy.getHitPoints() < 0) {
			this.battleHistoryEnemyHpTextArea.setText("0/" + enemy.getMaxHitPoints());
		} else {
			this.battleHistoryEnemyHpTextArea.setText(enemy.getHitPoints() + "/" + enemy.getMaxHitPoints());
		}
		this.battleHistoryEnemyAttTextArea.setText("" + enemy.getAttack());
		this.battleHistoryEnemyDefTextArea.setText("" + enemy.getDefence());
	}

	public void				battleHistory() {
		this.setBattleHistoryHero(this.controller.getHero());
		this.setBattleHistoryEnemy(this.controller.getEnemy());
		this.battleHistoryFinalTextPane.setText(this.controller.getRoundUpdate());
		this.encounterPanel.setVisible(false);
		this.heroPanel.setVisible(false);
		this.enemyPanel.setVisible(false);
		this.battleHistoryScrollPanel.setVisible(false);
		this.runFailedPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(true);
		this.battleHistoryHeroPanel.setVisible(true);
		this.battleHistoryHeroPanel.setVisible(true);
		this.battleHistoryFinalScrollPane.setVisible(true);
	}

	public void 			itemDropFailed() {
		this.mainPanel.setVisible(true);
		this.encounterPanel.setVisible(false);
		this.runFailedButton.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.itemDropPanel.setVisible(false);
		this.armorDropPanel.setVisible(false);
		this.itemDropPanel.setVisible(false);
		this.weaponDropPanel.setVisible(false);
		this.helmDropPanel.setVisible(false);
		this.inventoryFullPanel.setVisible(true);
	}

	public void itemDrop(String item, int boost) {
		this.item = item;
		this.boost = boost;
		if (item.equalsIgnoreCase("Inventory")) {
			this.itemDropTextArea.setText("	The enemy dropped an Inventory attachment with a +" + boost + " boost.");
		} else if (item.equalsIgnoreCase("Health")) {
			this.itemDropTextArea.setText("	The enemy dropped a small health remedy of +" + boost + "HP.");
		}
		this.encounterPanel.setVisible(false);
		this.heroPanel.setVisible(false);
		this.enemyPanel.setVisible(false);
		this.battleHistoryScrollPanel.setVisible(false);
		this.runFailedPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.successPanel.setVisible(false);
		this.itemDropPanel.setVisible(true);
		this.inventoryFullPanel.setVisible(false);
	}

	public void armorDrop(Armor armor) {
		this.armor = armor;
		this.armorDropTextArea.setText("	The enemy dropped an Armor: " + armor.getName());
		this.encounterPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.armorDropPanel.setVisible(true);
		this.inventoryFullPanel.setVisible(false);

	}

	public void weaponDrop(Weapon weapon) {
		this.weapon = weapon;
		this.weaponDropTextArea.setText("	The enemy dropped a Weapon: " + weapon.getName());
		this.encounterPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.weaponDropPanel.setVisible(true);
		this.inventoryFullPanel.setVisible(false);
	}

	public void helmDrop(Helm helm) {
		this.helm = helm;
		this.helmDropTextArea.setText("	The enemy dropped a Helm: " + helm.getName());
		this.encounterPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.helmDropPanel.setVisible(true);
		this.inventoryFullPanel.setVisible(false);
	}

	public void success() {
		this.encounterPanel.setVisible(false);
		this.heroPanel.setVisible(false);
		this.enemyPanel.setVisible(false);
		this.battleHistoryScrollPanel.setVisible(false);
		this.runFailedPanel.setVisible(false);
		this.battleHistoryPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.battleHistoryHeroPanel.setVisible(false);
		this.itemDropPanel.setVisible(false);
		this.armorDropPanel.setVisible(false);
		this.weaponDropPanel.setVisible(false);
		this.helmDropPanel.setVisible(false);
		this.successPanel.setVisible(true);
		this.inventoryFullPanel.setVisible(false);

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
		mainPanel.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainPanel.setBackground(new Color(-12566464));
		mainPanel.setMaximumSize(new Dimension(560, 560));
		mainPanel.setMinimumSize(new Dimension(560, 560));
		mainPanel.setPreferredSize(new Dimension(560, 560));
		encounterPanel = new JPanel();
		encounterPanel.setLayout(new GridLayoutManager(11, 4, new Insets(0, 0, 0, 0), -1, -1));
		encounterPanel.setBackground(new Color(-12566464));
		encounterPanel.setForeground(new Color(-4342339));
		mainPanel.add(encounterPanel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		encounterPanel.setBorder(BorderFactory.createTitledBorder("0"));
		heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
		heroPanel.setBackground(new Color(-12566464));
		encounterPanel.add(heroPanel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
		heroPanel.add(nameTextArea, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
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
		label3.setText("Level: ");
		heroPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		levelTextArea = new JTextArea();
		levelTextArea.setBackground(new Color(-12566464));
		levelTextArea.setEditable(false);
		levelTextArea.setForeground(new Color(-4342339));
		heroPanel.add(levelTextArea, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		final JLabel label4 = new JLabel();
		Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
		if (label4Font != null) label4.setFont(label4Font);
		label4.setForeground(new Color(-4342339));
		label4.setText("HP: ");
		heroPanel.add(label4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		final JLabel label5 = new JLabel();
		Font label5Font = this.$$$getFont$$$(null, Font.BOLD, -1, label5.getFont());
		if (label5Font != null) label5.setFont(label5Font);
		label5.setForeground(new Color(-4342339));
		label5.setText("ATT:");
		heroPanel.add(label5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		final JLabel label6 = new JLabel();
		Font label6Font = this.$$$getFont$$$(null, Font.BOLD, -1, label6.getFont());
		if (label6Font != null) label6.setFont(label6Font);
		label6.setForeground(new Color(-4342339));
		label6.setText("DEF:");
		heroPanel.add(label6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		hpTextArea = new JTextArea();
		hpTextArea.setBackground(new Color(-12566464));
		hpTextArea.setEditable(false);
		hpTextArea.setForeground(new Color(-4342339));
		heroPanel.add(hpTextArea, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		attTextArea = new JTextArea();
		attTextArea.setBackground(new Color(-12566464));
		attTextArea.setEditable(false);
		attTextArea.setForeground(new Color(-4342339));
		heroPanel.add(attTextArea, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		defTextArea = new JTextArea();
		defTextArea.setBackground(new Color(-12566464));
		defTextArea.setEditable(false);
		defTextArea.setForeground(new Color(-4342339));
		heroPanel.add(defTextArea, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final Spacer spacer1 = new Spacer();
		encounterPanel.add(spacer1, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		final Spacer spacer2 = new Spacer();
		encounterPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
		final Spacer spacer3 = new Spacer();
		encounterPanel.add(spacer3, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer4 = new Spacer();
		encounterPanel.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		enemyPanel = new JPanel();
		enemyPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
		enemyPanel.setBackground(new Color(-12566464));
		encounterPanel.add(enemyPanel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		enemyNameLabel = new JLabel();
		Font enemyNameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyNameLabel.getFont());
		if (enemyNameLabelFont != null) enemyNameLabel.setFont(enemyNameLabelFont);
		enemyNameLabel.setForeground(new Color(-4342339));
		enemyNameLabel.setText("Name: ");
		enemyPanel.add(enemyNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyNameTextArea = new JTextArea();
		enemyNameTextArea.setBackground(new Color(-12566464));
		enemyNameTextArea.setEditable(false);
		enemyNameTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyNameTextArea, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		enemyClassLabel = new JLabel();
		Font enemyClassLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyClassLabel.getFont());
		if (enemyClassLabelFont != null) enemyClassLabel.setFont(enemyClassLabelFont);
		enemyClassLabel.setForeground(new Color(-4342339));
		enemyClassLabel.setText("Class: ");
		enemyPanel.add(enemyClassLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyClassTextArea = new JTextArea();
		enemyClassTextArea.setBackground(new Color(-12566464));
		enemyClassTextArea.setEditable(false);
		enemyClassTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyClassTextArea, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		enemyLevelLabel = new JLabel();
		Font enemyLevelLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyLevelLabel.getFont());
		if (enemyLevelLabelFont != null) enemyLevelLabel.setFont(enemyLevelLabelFont);
		enemyLevelLabel.setForeground(new Color(-4342339));
		enemyLevelLabel.setText("Level: ");
		enemyPanel.add(enemyLevelLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyLevelTextArea = new JTextArea();
		enemyLevelTextArea.setBackground(new Color(-12566464));
		enemyLevelTextArea.setEditable(false);
		enemyLevelTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyLevelTextArea, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		enemyHPLabel = new JLabel();
		Font enemyHPLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyHPLabel.getFont());
		if (enemyHPLabelFont != null) enemyHPLabel.setFont(enemyHPLabelFont);
		enemyHPLabel.setForeground(new Color(-4342339));
		enemyHPLabel.setText("HP: ");
		enemyPanel.add(enemyHPLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyAttLabel = new JLabel();
		Font enemyAttLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyAttLabel.getFont());
		if (enemyAttLabelFont != null) enemyAttLabel.setFont(enemyAttLabelFont);
		enemyAttLabel.setForeground(new Color(-4342339));
		enemyAttLabel.setText("ATT:");
		enemyPanel.add(enemyAttLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyDefLabel = new JLabel();
		Font enemyDefLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyDefLabel.getFont());
		if (enemyDefLabelFont != null) enemyDefLabel.setFont(enemyDefLabelFont);
		enemyDefLabel.setForeground(new Color(-4342339));
		enemyDefLabel.setText("DEF:");
		enemyPanel.add(enemyDefLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		enemyHPTextArea = new JTextArea();
		enemyHPTextArea.setBackground(new Color(-12566464));
		enemyHPTextArea.setEditable(false);
		enemyHPTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyHPTextArea, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		enemyAttTextArea = new JTextArea();
		enemyAttTextArea.setBackground(new Color(-12566464));
		enemyAttTextArea.setEditable(false);
		enemyAttTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyAttTextArea, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		enemyDefTextArea = new JTextArea();
		enemyDefTextArea.setBackground(new Color(-12566464));
		enemyDefTextArea.setEditable(false);
		enemyDefTextArea.setForeground(new Color(-4342339));
		enemyPanel.add(enemyDefTextArea, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		enemyStatsTextPane = new JTextPane();
		enemyStatsTextPane.setBackground(new Color(-12566464));
		enemyStatsTextPane.setEditable(false);
		Font enemyStatsTextPaneFont = this.$$$getFont$$$(null, Font.BOLD, -1, enemyStatsTextPane.getFont());
		if (enemyStatsTextPaneFont != null) enemyStatsTextPane.setFont(enemyStatsTextPaneFont);
		enemyStatsTextPane.setForeground(new Color(-4342339));
		enemyStatsTextPane.setText("Enemy stats:");
		encounterPanel.add(enemyStatsTextPane, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		heroStatsTestPane = new JTextPane();
		heroStatsTestPane.setBackground(new Color(-12566464));
		heroStatsTestPane.setEditable(false);
		Font heroStatsTestPaneFont = this.$$$getFont$$$(null, Font.BOLD, -1, heroStatsTestPane.getFont());
		if (heroStatsTestPaneFont != null) heroStatsTestPane.setFont(heroStatsTestPaneFont);
		heroStatsTestPane.setForeground(new Color(-4342339));
		heroStatsTestPane.setText("Your stats:");
		encounterPanel.add(heroStatsTestPane, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		final Spacer spacer5 = new Spacer();
		encounterPanel.add(spacer5, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		fightButton = new JButton();
		fightButton.setText("Fight");
		encounterPanel.add(fightButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		simulateButton = new JButton();
		simulateButton.setText("Simulate");
		encounterPanel.add(simulateButton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		runButton = new JButton();
		runButton.setText("Run");
		encounterPanel.add(runButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		switchButton = new JButton();
		switchButton.setText("Switch");
		encounterPanel.add(switchButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		battleHistoryScrollPanel = new JScrollPane();
		battleHistoryScrollPanel.setBackground(new Color(-11908534));
		battleHistoryScrollPanel.setForeground(new Color(-4342339));
		encounterPanel.add(battleHistoryScrollPanel, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 180), new Dimension(-1, 180), new Dimension(-1, 180), 0, false));
		battleHistoryTextPane = new JTextPane();
		battleHistoryTextPane.setBackground(new Color(-11645362));
		battleHistoryTextPane.setCaretColor(new Color(-4342339));
		battleHistoryTextPane.setForeground(new Color(-4342339));
		battleHistoryScrollPanel.setViewportView(battleHistoryTextPane);
		youHave3ChoicesTextArea = new JTextArea();
		youHave3ChoicesTextArea.setBackground(new Color(-12566464));
		youHave3ChoicesTextArea.setEditable(false);
		youHave3ChoicesTextArea.setForeground(new Color(-4342339));
		youHave3ChoicesTextArea.setText("You have 3 choices! You can try to RUN, or you can FIGHT...\nor you can SIMULATE so the game will fight for you (lazy much?)");
		encounterPanel.add(youHave3ChoicesTextArea, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 35), null, 0, false));
		runFailedPanel = new JPanel();
		runFailedPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		runFailedPanel.setBackground(new Color(-12566464));
		mainPanel.add(runFailedPanel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane1 = new JTextPane();
		textPane1.setBackground(new Color(-12566464));
		textPane1.setCaretColor(new Color(-12948803));
		textPane1.setDisabledTextColor(new Color(-12948803));
		textPane1.setEditable(false);
		textPane1.setForeground(new Color(-12948803));
		textPane1.setText("THE HERO TRIED TO RUN AWAY BUT FAILED!");
		runFailedPanel.add(textPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer6 = new Spacer();
		runFailedPanel.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer7 = new Spacer();
		runFailedPanel.add(spacer7, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer8 = new Spacer();
		runFailedPanel.add(spacer8, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer9 = new Spacer();
		runFailedPanel.add(spacer9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		runFailedButton = new JButton();
		runFailedButton.setLabel("OK");
		runFailedButton.setText("OK");
		runFailedPanel.add(runFailedButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer10 = new Spacer();
		runFailedPanel.add(spacer10, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		battleHistoryPanel = new JPanel();
		battleHistoryPanel.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
		battleHistoryPanel.setBackground(new Color(-12566464));
		battleHistoryPanel.setForeground(new Color(-4342339));
		mainPanel.add(battleHistoryPanel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		battleHistoryPanel.setBorder(BorderFactory.createTitledBorder("0"));
		battleHistoryHeroPanel = new JPanel();
		battleHistoryHeroPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
		battleHistoryHeroPanel.setBackground(new Color(-12566464));
		battleHistoryPanel.add(battleHistoryHeroPanel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		battleHistoryHeroNameLabel = new JLabel();
		Font battleHistoryHeroNameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroNameLabel.getFont());
		if (battleHistoryHeroNameLabelFont != null) battleHistoryHeroNameLabel.setFont(battleHistoryHeroNameLabelFont);
		battleHistoryHeroNameLabel.setForeground(new Color(-4342339));
		battleHistoryHeroNameLabel.setText("Name: ");
		battleHistoryHeroPanel.add(battleHistoryHeroNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroNameTextArea = new JTextArea();
		battleHistoryHeroNameTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroNameTextArea.setEditable(false);
		battleHistoryHeroNameTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroNameTextArea, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryHeroClassLabel = new JLabel();
		Font battleHistoryHeroClassLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroClassLabel.getFont());
		if (battleHistoryHeroClassLabelFont != null)
			battleHistoryHeroClassLabel.setFont(battleHistoryHeroClassLabelFont);
		battleHistoryHeroClassLabel.setForeground(new Color(-4342339));
		battleHistoryHeroClassLabel.setText("Class: ");
		battleHistoryHeroPanel.add(battleHistoryHeroClassLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroClassTextArea = new JTextArea();
		battleHistoryHeroClassTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroClassTextArea.setEditable(false);
		battleHistoryHeroClassTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroClassTextArea, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryHeroLevelLabel = new JLabel();
		Font battleHistoryHeroLevelLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroLevelLabel.getFont());
		if (battleHistoryHeroLevelLabelFont != null)
			battleHistoryHeroLevelLabel.setFont(battleHistoryHeroLevelLabelFont);
		battleHistoryHeroLevelLabel.setForeground(new Color(-4342339));
		battleHistoryHeroLevelLabel.setText("Level: ");
		battleHistoryHeroPanel.add(battleHistoryHeroLevelLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroLevelTextArea = new JTextArea();
		battleHistoryHeroLevelTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroLevelTextArea.setEditable(false);
		battleHistoryHeroLevelTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroLevelTextArea, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryHeroHpLabel = new JLabel();
		Font battleHistoryHeroHpLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroHpLabel.getFont());
		if (battleHistoryHeroHpLabelFont != null) battleHistoryHeroHpLabel.setFont(battleHistoryHeroHpLabelFont);
		battleHistoryHeroHpLabel.setForeground(new Color(-4342339));
		battleHistoryHeroHpLabel.setText("HP: ");
		battleHistoryHeroPanel.add(battleHistoryHeroHpLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroAttLabel = new JLabel();
		Font battleHistoryHeroAttLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroAttLabel.getFont());
		if (battleHistoryHeroAttLabelFont != null) battleHistoryHeroAttLabel.setFont(battleHistoryHeroAttLabelFont);
		battleHistoryHeroAttLabel.setForeground(new Color(-4342339));
		battleHistoryHeroAttLabel.setText("ATT:");
		battleHistoryHeroPanel.add(battleHistoryHeroAttLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroDefLabel = new JLabel();
		Font battleHistoryHeroDefLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryHeroDefLabel.getFont());
		if (battleHistoryHeroDefLabelFont != null) battleHistoryHeroDefLabel.setFont(battleHistoryHeroDefLabelFont);
		battleHistoryHeroDefLabel.setForeground(new Color(-4342339));
		battleHistoryHeroDefLabel.setText("DEF:");
		battleHistoryHeroPanel.add(battleHistoryHeroDefLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryHeroHpTextArea = new JTextArea();
		battleHistoryHeroHpTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroHpTextArea.setEditable(false);
		battleHistoryHeroHpTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroHpTextArea, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		battleHistoryHeroAttTextArea = new JTextArea();
		battleHistoryHeroAttTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroAttTextArea.setEditable(false);
		battleHistoryHeroAttTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroAttTextArea, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		battleHistoryHeroDefTextArea = new JTextArea();
		battleHistoryHeroDefTextArea.setBackground(new Color(-12566464));
		battleHistoryHeroDefTextArea.setEditable(false);
		battleHistoryHeroDefTextArea.setForeground(new Color(-4342339));
		battleHistoryHeroPanel.add(battleHistoryHeroDefTextArea, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final Spacer spacer11 = new Spacer();
		battleHistoryPanel.add(spacer11, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
		final Spacer spacer12 = new Spacer();
		battleHistoryPanel.add(spacer12, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
		final Spacer spacer13 = new Spacer();
		battleHistoryPanel.add(spacer13, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer14 = new Spacer();
		battleHistoryPanel.add(spacer14, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		battleHistoryEnemyPanel = new JPanel();
		battleHistoryEnemyPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
		battleHistoryEnemyPanel.setBackground(new Color(-12566464));
		battleHistoryPanel.add(battleHistoryEnemyPanel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		battleHistoryEnemyNameLabel = new JLabel();
		Font battleHistoryEnemyNameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyNameLabel.getFont());
		if (battleHistoryEnemyNameLabelFont != null)
			battleHistoryEnemyNameLabel.setFont(battleHistoryEnemyNameLabelFont);
		battleHistoryEnemyNameLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyNameLabel.setText("Name: ");
		battleHistoryEnemyPanel.add(battleHistoryEnemyNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyNameTextArea = new JTextArea();
		battleHistoryEnemyNameTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyNameTextArea.setEditable(false);
		battleHistoryEnemyNameTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyNameTextArea, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryEnemyClassLabel = new JLabel();
		Font battleHistoryEnemyClassLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyClassLabel.getFont());
		if (battleHistoryEnemyClassLabelFont != null)
			battleHistoryEnemyClassLabel.setFont(battleHistoryEnemyClassLabelFont);
		battleHistoryEnemyClassLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyClassLabel.setText("Class: ");
		battleHistoryEnemyPanel.add(battleHistoryEnemyClassLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyClassTextArea = new JTextArea();
		battleHistoryEnemyClassTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyClassTextArea.setEditable(false);
		battleHistoryEnemyClassTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyClassTextArea, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryEnemyLevelLabel = new JLabel();
		Font battleHistoryEnemyLevelLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyLevelLabel.getFont());
		if (battleHistoryEnemyLevelLabelFont != null)
			battleHistoryEnemyLevelLabel.setFont(battleHistoryEnemyLevelLabelFont);
		battleHistoryEnemyLevelLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyLevelLabel.setText("Level: ");
		battleHistoryEnemyPanel.add(battleHistoryEnemyLevelLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyLevelTextArea = new JTextArea();
		battleHistoryEnemyLevelTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyLevelTextArea.setEditable(false);
		battleHistoryEnemyLevelTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyLevelTextArea, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 20), new Dimension(100, 20), new Dimension(100, 20), 0, false));
		battleHistoryEnemyHpLabel = new JLabel();
		Font battleHistoryEnemyHpLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyHpLabel.getFont());
		if (battleHistoryEnemyHpLabelFont != null) battleHistoryEnemyHpLabel.setFont(battleHistoryEnemyHpLabelFont);
		battleHistoryEnemyHpLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyHpLabel.setText("HP: ");
		battleHistoryEnemyPanel.add(battleHistoryEnemyHpLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyAttLabel = new JLabel();
		Font battleHistoryEnemyAttLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyAttLabel.getFont());
		if (battleHistoryEnemyAttLabelFont != null) battleHistoryEnemyAttLabel.setFont(battleHistoryEnemyAttLabelFont);
		battleHistoryEnemyAttLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyAttLabel.setText("ATT:");
		battleHistoryEnemyPanel.add(battleHistoryEnemyAttLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyDefLabel = new JLabel();
		Font battleHistoryEnemyDefLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, battleHistoryEnemyDefLabel.getFont());
		if (battleHistoryEnemyDefLabelFont != null) battleHistoryEnemyDefLabel.setFont(battleHistoryEnemyDefLabelFont);
		battleHistoryEnemyDefLabel.setForeground(new Color(-4342339));
		battleHistoryEnemyDefLabel.setText("DEF:");
		battleHistoryEnemyPanel.add(battleHistoryEnemyDefLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 10), new Dimension(80, 10), new Dimension(80, 10), 0, false));
		battleHistoryEnemyHpTextArea = new JTextArea();
		battleHistoryEnemyHpTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyHpTextArea.setEditable(false);
		battleHistoryEnemyHpTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyHpTextArea, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		battleHistoryEnemyAttTextArea = new JTextArea();
		battleHistoryEnemyAttTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyAttTextArea.setEditable(false);
		battleHistoryEnemyAttTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyAttTextArea, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		battleHistoryEnemyDefTextArea = new JTextArea();
		battleHistoryEnemyDefTextArea.setBackground(new Color(-12566464));
		battleHistoryEnemyDefTextArea.setEditable(false);
		battleHistoryEnemyDefTextArea.setForeground(new Color(-4342339));
		battleHistoryEnemyPanel.add(battleHistoryEnemyDefTextArea, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(80, 20), new Dimension(80, 20), new Dimension(80, 20), 0, false));
		final JTextPane textPane2 = new JTextPane();
		textPane2.setBackground(new Color(-12566464));
		textPane2.setEditable(false);
		Font textPane2Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane2.getFont());
		if (textPane2Font != null) textPane2.setFont(textPane2Font);
		textPane2.setForeground(new Color(-4342339));
		textPane2.setText("Enemy stats:");
		battleHistoryPanel.add(textPane2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		final JTextPane textPane3 = new JTextPane();
		textPane3.setBackground(new Color(-12566464));
		textPane3.setEditable(false);
		Font textPane3Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane3.getFont());
		if (textPane3Font != null) textPane3.setFont(textPane3Font);
		textPane3.setForeground(new Color(-4342339));
		textPane3.setText("Your stats:");
		battleHistoryPanel.add(textPane3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(150, 20), new Dimension(-1, 20), 0, false));
		battleHistoryButton = new JButton();
		battleHistoryButton.setText("OK");
		battleHistoryPanel.add(battleHistoryButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		battleHistoryFinalScrollPane = new JScrollPane();
		battleHistoryFinalScrollPane.setBackground(new Color(-11908534));
		battleHistoryFinalScrollPane.setForeground(new Color(-4342339));
		battleHistoryPanel.add(battleHistoryFinalScrollPane, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 180), new Dimension(-1, 180), new Dimension(-1, 180), 0, false));
		battleHistoryFinalTextPane = new JTextPane();
		battleHistoryFinalTextPane.setBackground(new Color(-11645362));
		battleHistoryFinalTextPane.setCaretColor(new Color(-4342339));
		battleHistoryFinalTextPane.setForeground(new Color(-4342339));
		battleHistoryFinalScrollPane.setViewportView(battleHistoryFinalTextPane);
		THEBATTLEISOVERTextPane = new JTextPane();
		THEBATTLEISOVERTextPane.setBackground(new Color(-12566464));
		THEBATTLEISOVERTextPane.setEditable(false);
		Font THEBATTLEISOVERTextPaneFont = this.$$$getFont$$$(null, Font.BOLD, -1, THEBATTLEISOVERTextPane.getFont());
		if (THEBATTLEISOVERTextPaneFont != null) THEBATTLEISOVERTextPane.setFont(THEBATTLEISOVERTextPaneFont);
		THEBATTLEISOVERTextPane.setForeground(new Color(-4342339));
		THEBATTLEISOVERTextPane.setText("                                  THE BATTLE IS OVER!");
		battleHistoryPanel.add(THEBATTLEISOVERTextPane, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		successPanel = new JPanel();
		successPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		successPanel.setBackground(new Color(-12566464));
		mainPanel.add(successPanel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane4 = new JTextPane();
		textPane4.setBackground(new Color(-12566464));
		textPane4.setCaretColor(new Color(-10109628));
		textPane4.setDisabledTextColor(new Color(-10109628));
		textPane4.setEditable(false);
		textPane4.setForeground(new Color(-10109628));
		textPane4.setText("CONGRATULATIONS! YOU LEVELED UP!");
		successPanel.add(textPane4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer15 = new Spacer();
		successPanel.add(spacer15, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer16 = new Spacer();
		successPanel.add(spacer16, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer17 = new Spacer();
		successPanel.add(spacer17, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer18 = new Spacer();
		successPanel.add(spacer18, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		levelUpButton = new JButton();
		levelUpButton.setLabel("OK");
		levelUpButton.setText("OK");
		successPanel.add(levelUpButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer19 = new Spacer();
		successPanel.add(spacer19, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		itemDropPanel = new JPanel();
		itemDropPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
		itemDropPanel.setBackground(new Color(-12566464));
		mainPanel.add(itemDropPanel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer20 = new Spacer();
		itemDropPanel.add(spacer20, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer21 = new Spacer();
		itemDropPanel.add(spacer21, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		itemYesButton = new JButton();
		itemYesButton.setLabel("YES");
		itemYesButton.setText("YES");
		itemDropPanel.add(itemYesButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer22 = new Spacer();
		itemDropPanel.add(spacer22, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		final JTextPane textPane5 = new JTextPane();
		textPane5.setBackground(new Color(-12566464));
		textPane5.setCaretColor(new Color(-4343722));
		textPane5.setDisabledTextColor(new Color(-4343722));
		textPane5.setEditable(false);
		Font textPane5Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane5.getFont());
		if (textPane5Font != null) textPane5.setFont(textPane5Font);
		textPane5.setForeground(new Color(-4343722));
		textPane5.setText("         ITEM DROP!!!!");
		itemDropPanel.add(textPane5, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		itemDropTextArea = new JTextArea();
		itemDropTextArea.setBackground(new Color(-12566464));
		itemDropTextArea.setCaretColor(new Color(-4343722));
		itemDropTextArea.setDisabledTextColor(new Color(-4343722));
		itemDropTextArea.setForeground(new Color(-4343722));
		itemDropPanel.add(itemDropTextArea, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, -1), new Dimension(480, 50), new Dimension(480, -1), 0, false));
		wouldYouLikeToTextPane = new JTextPane();
		wouldYouLikeToTextPane.setBackground(new Color(-12566464));
		wouldYouLikeToTextPane.setCaretColor(new Color(-4343722));
		wouldYouLikeToTextPane.setDisabledTextColor(new Color(-4343722));
		wouldYouLikeToTextPane.setEditable(false);
		wouldYouLikeToTextPane.setForeground(new Color(-4343722));
		wouldYouLikeToTextPane.setText("Would you like to take it?");
		itemDropPanel.add(wouldYouLikeToTextPane, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		itemNoButton = new JButton();
		itemNoButton.setLabel("NO");
		itemNoButton.setText("NO");
		itemDropPanel.add(itemNoButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer23 = new Spacer();
		itemDropPanel.add(spacer23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer24 = new Spacer();
		itemDropPanel.add(spacer24, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		armorDropPanel = new JPanel();
		armorDropPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
		armorDropPanel.setBackground(new Color(-12566464));
		mainPanel.add(armorDropPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer25 = new Spacer();
		armorDropPanel.add(spacer25, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer26 = new Spacer();
		armorDropPanel.add(spacer26, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		armorYesButton = new JButton();
		armorYesButton.setLabel("YES");
		armorYesButton.setText("YES");
		armorDropPanel.add(armorYesButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer27 = new Spacer();
		armorDropPanel.add(spacer27, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		final JTextPane textPane6 = new JTextPane();
		textPane6.setBackground(new Color(-12566464));
		textPane6.setCaretColor(new Color(-4343722));
		textPane6.setDisabledTextColor(new Color(-4343722));
		textPane6.setEditable(false);
		Font textPane6Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane6.getFont());
		if (textPane6Font != null) textPane6.setFont(textPane6Font);
		textPane6.setForeground(new Color(-4343722));
		textPane6.setText("         ITEM DROP!!!!");
		armorDropPanel.add(textPane6, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		armorDropTextArea = new JTextArea();
		armorDropTextArea.setBackground(new Color(-12566464));
		armorDropTextArea.setCaretColor(new Color(-4343722));
		armorDropTextArea.setDisabledTextColor(new Color(-4343722));
		armorDropTextArea.setForeground(new Color(-4343722));
		armorDropPanel.add(armorDropTextArea, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, -1), new Dimension(480, 50), new Dimension(480, -1), 0, false));
		final JTextPane textPane7 = new JTextPane();
		textPane7.setBackground(new Color(-12566464));
		textPane7.setCaretColor(new Color(-4343722));
		textPane7.setDisabledTextColor(new Color(-4343722));
		textPane7.setEditable(false);
		textPane7.setForeground(new Color(-4343722));
		textPane7.setText("Would you like to take it?");
		armorDropPanel.add(textPane7, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		armorNoButton = new JButton();
		armorNoButton.setLabel("NO");
		armorNoButton.setText("NO");
		armorDropPanel.add(armorNoButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer28 = new Spacer();
		armorDropPanel.add(spacer28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer29 = new Spacer();
		armorDropPanel.add(spacer29, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		weaponDropPanel = new JPanel();
		weaponDropPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
		weaponDropPanel.setBackground(new Color(-12566464));
		mainPanel.add(weaponDropPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer30 = new Spacer();
		weaponDropPanel.add(spacer30, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer31 = new Spacer();
		weaponDropPanel.add(spacer31, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		weaponDropYesButton = new JButton();
		weaponDropYesButton.setLabel("YES");
		weaponDropYesButton.setText("YES");
		weaponDropPanel.add(weaponDropYesButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer32 = new Spacer();
		weaponDropPanel.add(spacer32, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		final JTextPane textPane8 = new JTextPane();
		textPane8.setBackground(new Color(-12566464));
		textPane8.setCaretColor(new Color(-4343722));
		textPane8.setDisabledTextColor(new Color(-4343722));
		textPane8.setEditable(false);
		Font textPane8Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane8.getFont());
		if (textPane8Font != null) textPane8.setFont(textPane8Font);
		textPane8.setForeground(new Color(-4343722));
		textPane8.setText("         ITEM DROP!!!!");
		weaponDropPanel.add(textPane8, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		weaponDropTextArea = new JTextArea();
		weaponDropTextArea.setBackground(new Color(-12566464));
		weaponDropTextArea.setCaretColor(new Color(-4343722));
		weaponDropTextArea.setDisabledTextColor(new Color(-4343722));
		weaponDropTextArea.setForeground(new Color(-4343722));
		weaponDropPanel.add(weaponDropTextArea, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, -1), new Dimension(480, 50), new Dimension(480, -1), 0, false));
		final JTextPane textPane9 = new JTextPane();
		textPane9.setBackground(new Color(-12566464));
		textPane9.setCaretColor(new Color(-4343722));
		textPane9.setDisabledTextColor(new Color(-4343722));
		textPane9.setEditable(false);
		textPane9.setForeground(new Color(-4343722));
		textPane9.setText("Would you like to take it?");
		weaponDropPanel.add(textPane9, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		weaponDropNoButton = new JButton();
		weaponDropNoButton.setLabel("NO");
		weaponDropNoButton.setText("NO");
		weaponDropPanel.add(weaponDropNoButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer33 = new Spacer();
		weaponDropPanel.add(spacer33, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer34 = new Spacer();
		weaponDropPanel.add(spacer34, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		helmDropPanel = new JPanel();
		helmDropPanel.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
		helmDropPanel.setBackground(new Color(-12566464));
		mainPanel.add(helmDropPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final Spacer spacer35 = new Spacer();
		helmDropPanel.add(spacer35, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer36 = new Spacer();
		helmDropPanel.add(spacer36, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		helmDropYesButton = new JButton();
		helmDropYesButton.setLabel("YES");
		helmDropYesButton.setText("YES");
		helmDropPanel.add(helmDropYesButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer37 = new Spacer();
		helmDropPanel.add(spacer37, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
		final JTextPane textPane10 = new JTextPane();
		textPane10.setBackground(new Color(-12566464));
		textPane10.setCaretColor(new Color(-4343722));
		textPane10.setDisabledTextColor(new Color(-4343722));
		textPane10.setEditable(false);
		Font textPane10Font = this.$$$getFont$$$(null, Font.BOLD, -1, textPane10.getFont());
		if (textPane10Font != null) textPane10.setFont(textPane10Font);
		textPane10.setForeground(new Color(-4343722));
		textPane10.setText("         ITEM DROP!!!!");
		helmDropPanel.add(textPane10, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		helmDropTextArea = new JTextArea();
		helmDropTextArea.setBackground(new Color(-12566464));
		helmDropTextArea.setCaretColor(new Color(-4343722));
		helmDropTextArea.setDisabledTextColor(new Color(-4343722));
		helmDropTextArea.setForeground(new Color(-4343722));
		helmDropPanel.add(helmDropTextArea, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(480, -1), new Dimension(480, 50), new Dimension(480, -1), 0, false));
		final JTextPane textPane11 = new JTextPane();
		textPane11.setBackground(new Color(-12566464));
		textPane11.setCaretColor(new Color(-4343722));
		textPane11.setDisabledTextColor(new Color(-4343722));
		textPane11.setEditable(false);
		textPane11.setForeground(new Color(-4343722));
		textPane11.setText("Would you like to take it?");
		helmDropPanel.add(textPane11, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 20), null, 0, false));
		helmDropNoButton = new JButton();
		helmDropNoButton.setLabel("NO");
		helmDropNoButton.setText("NO");
		helmDropPanel.add(helmDropNoButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer38 = new Spacer();
		helmDropPanel.add(spacer38, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer39 = new Spacer();
		helmDropPanel.add(spacer39, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		inventoryFullPanel = new JPanel();
		inventoryFullPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		inventoryFullPanel.setBackground(new Color(-12566464));
		mainPanel.add(inventoryFullPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
		final JTextPane textPane12 = new JTextPane();
		textPane12.setBackground(new Color(-12566464));
		textPane12.setCaretColor(new Color(-4380623));
		textPane12.setDisabledTextColor(new Color(-4380623));
		textPane12.setEditable(false);
		textPane12.setForeground(new Color(-4380623));
		textPane12.setText("\t\t\t\tITEM DROP FAILED!\n              It seems your inventory is full!\nYou will need to clear out some of your inventory.");
		inventoryFullPanel.add(textPane12, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer40 = new Spacer();
		inventoryFullPanel.add(spacer40, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer41 = new Spacer();
		inventoryFullPanel.add(spacer41, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final Spacer spacer42 = new Spacer();
		inventoryFullPanel.add(spacer42, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer43 = new Spacer();
		inventoryFullPanel.add(spacer43, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		deathButton = new JButton();
		deathButton.setLabel("OK");
		deathButton.setText("OK");
		inventoryFullPanel.add(deathButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer44 = new Spacer();
		inventoryFullPanel.add(spacer44, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
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

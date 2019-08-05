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
	private JTextArea youHave3ChoicesTextArea;
	private JScrollPane battleHistoryScrollPanel;

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
	}

	private void setHero(Hero hero) {
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

	private void setEnemy(Enemy enemy) {
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

	public void display() {
		this.getFrame().setContentPane(this.mainPanel);
		this.mainPanel.setVisible(true);
		this.encounterPanel.setVisible(true);
		this.heroPanel.setVisible(true);
		this.enemyPanel.setVisible(true);
		this.battleHistoryScrollPanel.setVisible(true);

		this.setHero(this.controller.getHero());
		this.setEnemy(this.controller.getEnemy());
		this.battleHistoryTextPane.setText("\n____BATTLE HISTORY____" + this.controller.getRoundUpdate() + "______________________\n");

	}

	public void runFailed() {

	}

	public void battleHistory() {

	}

	public void itemDrop(String item, int index) {

	}

	public void armorDrop(Armor armor) {

	}

	public void weaponDrop(Weapon weapon) {

	}

	public void helmDrop(Helm helm) {

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
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		encounterPanel = new JPanel();
		encounterPanel.setLayout(new GridLayoutManager(11, 4, new Insets(0, 0, 0, 0), -1, -1));
		encounterPanel.setBackground(new Color(-12566464));
		encounterPanel.setForeground(new Color(-4342339));
		mainPanel.add(encounterPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(560, 560), new Dimension(560, 560), new Dimension(560, 560), 0, false));
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
		youHave3ChoicesTextArea = new JTextArea();
		youHave3ChoicesTextArea.setBackground(new Color(-12566464));
		youHave3ChoicesTextArea.setEditable(false);
		youHave3ChoicesTextArea.setForeground(new Color(-4342339));
		youHave3ChoicesTextArea.setText("You have 3 choices! You can try to RUN, or you can FIGHT...\nor you can SIMULATE so the game will fight for you (lazy much?)");
		encounterPanel.add(youHave3ChoicesTextArea, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
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
		final Spacer spacer6 = new Spacer();
		mainPanel.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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

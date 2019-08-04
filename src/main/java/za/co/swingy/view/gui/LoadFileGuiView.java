package za.co.swingy.view.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import za.co.swingy.controller.CharacterController;
import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.LoadFileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LoadFileGuiView implements LoadFileView {
	private CharacterController			characterController;
	private JTable table1;
	private JPanel panel1;
	private JTextPane LOADSAVETextPane;

	private void createUIComponents() {
		// TODO: place custom component creation code here
		//http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
		DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("No.");
		tableModel.addColumn("Name");
		tableModel.addColumn("Level");
		tableModel.addColumn("Select");
		table1 = new JTable(tableModel);
		table1.getColumn("Select").setCellRenderer(new ButtonRenderer());
		table1.setRowSelectionAllowed(true);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//
//			public void valueChanged(ListSelectionEvent event) {
//				if (event.getValueIsAdjusting())
//					return;
//				//System.out.println("row : " + table.getValueAt(table.getSelectedRow(), 0).toString());
//				fillAppServiceDetails();
//			}
//		});
//		AnActionButton refreshAction = new AnActionButton("Refresh", AllIcons.Actions.Refresh) {
//
//			@Override
//			public void actionPerformed(AnActionEvent anActionEvent) {
//				refreshAppServices();
//			}
//		};
//		ToolbarDecorator tableToolbarDecorator = ToolbarDecorator.createDecorator(table).setAddAction(new AnActionButtonRunnable() {
//
//			@Override
//			public void run(AnActionButton button) {
//				createAppService();
//			}
//		}).setRemoveAction(new AnActionButtonRunnable() {
//
//			@Override
//			public void run(AnActionButton button) {
//				deleteAppService();
//			}
//		}).setRemoveActionUpdater(new AnActionButtonUpdater() {
//
//			@Override
//			public boolean isEnabled(AnActionEvent e) {
//				return table.getSelectedRow() != -1;
//			}
//		}).disableUpDownActions().addExtraActions(refreshAction);
//		panelTable = tableToolbarDecorator.createPanel();
	}

	public void		 saveList(ArrayList<Hero> saves) {
	}

	public void noSaves() {

	}

	public void printLoadedHero(Hero hero, CharacterController controller) {
	}

	public void 			setCharacterController(CharacterController characterController) {
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
		createUIComponents();
		panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
		panel1.setBackground(new Color(-12566464));
		panel1.setMaximumSize(new Dimension(560, 560));
		panel1.setMinimumSize(new Dimension(560, 560));
		panel1.setPreferredSize(new Dimension(560, 560));
		table1.setBackground(new Color(-12566464));
		table1.setCellSelectionEnabled(false);
		table1.setColumnSelectionAllowed(true);
		panel1.add(table1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(500, -1), new Dimension(500, 50), new Dimension(500, -1), 0, false));
		LOADSAVETextPane = new JTextPane();
		LOADSAVETextPane.setBackground(new Color(-12566464));
		LOADSAVETextPane.setCaretColor(new Color(-4342339));
		LOADSAVETextPane.setDisabledTextColor(new Color(-4342339));
		LOADSAVETextPane.setEnabled(false);
		Font LOADSAVETextPaneFont = this.$$$getFont$$$(null, Font.BOLD, 16, LOADSAVETextPane.getFont());
		if (LOADSAVETextPaneFont != null) LOADSAVETextPane.setFont(LOADSAVETextPaneFont);
		LOADSAVETextPane.setText("\t\t\tLOAD SAVE:");
		panel1.add(LOADSAVETextPane, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel1.add(spacer1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer2 = new Spacer();
		panel1.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel1.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
		final Spacer spacer4 = new Spacer();
		panel1.add(spacer4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
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
		return panel1;
	}
}

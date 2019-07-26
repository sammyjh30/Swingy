package za.co.swingy.view.gui;

import za.co.swingy.model.characters.Hero;
import za.co.swingy.view.LoadFileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class LoadFileGuiView implements LoadFileView {
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

	public int				saveList(ArrayList<Hero> saves) {
		return 0;
	}

	public void			noSaves() {

	}

	public int 			printLoadedHero(Hero hero) {
		return 0;
	}
}

package za.co.swingy;

import za.co.swingy.view.console.MenuConsoleView;
import za.co.swingy.view.gui.LoadFileGuiView;
import za.co.swingy.view.gui.MenuGuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	//Todo:
	//Need to be able to win!
	private static  JFrame frame;

	public static void 			main(String[] args) {
		MenuConsoleView menuConsoleView = new MenuConsoleView();
		menuConsoleView.menu();
//		frame = new JFrame("Swingy");
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		frame.setVisible(true);
//		frame.setPreferredSize(new Dimension(560,560));
//		MenuGuiView menuGuiView = new MenuGuiView(frame);
//		menuGuiView.menu();
		return;
	}



}

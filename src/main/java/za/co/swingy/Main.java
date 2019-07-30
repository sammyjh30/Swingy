package za.co.swingy;

import za.co.swingy.view.console.MenuConsoleView;
import za.co.swingy.view.gui.CreateHeroGuiView;
import za.co.swingy.view.gui.FrameView;
import za.co.swingy.view.gui.LoadFileGuiView;
import za.co.swingy.view.gui.MenuGuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	//Todo:
	//Need to be able to win!
//	private static  JFrame frame;

//	private static FrameView frameView;

	public static void 			main(String[] args) {
//		MenuConsoleView menuConsoleView = new MenuConsoleView();
//		menuConsoleView.menu();

//		frame = new JFrame("Swingy");
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		frame.setVisible(true);
//		frame.setPreferredSize(new Dimension(560,560));

//		frameView = new FrameView();
//		frameView.getFrame().setVisible(true);

//		CreateHeroGuiView createHeroGuiView = new CreateHeroGuiView(frame);
//		frame.setContentPane(createHeroGuiView.getTopPanel());
//		createHeroGuiView.getTopPanel().setVisible(true);
//		System.out.println("Name = " + createHeroGuiView.getName());
		MenuGuiView menuGuiView = new MenuGuiView();
		menuGuiView.menu();
		return;
	}



}

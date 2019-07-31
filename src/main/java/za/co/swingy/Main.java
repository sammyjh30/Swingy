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
		MenuConsoleView menuConsoleView = new MenuConsoleView();
		menuConsoleView.menu();

//		MenuGuiView menuGuiView = new MenuGuiView();
//		menuGuiView.menu();
		return;
	}



}

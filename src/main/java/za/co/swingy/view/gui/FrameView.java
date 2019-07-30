package za.co.swingy.view.gui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameView {
	private static JFrame		frame = new JFrame("Swingy");;

	public void 			initFrame() {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(560,560));
	}

	public JFrame			getFrame() {
		return this.frame;
	}
}

package com.coamctech.admin.generator.swing.dialog;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public class GenerateDialog extends JDialog {
	private static final long serialVersionUID = 4258280088222631753L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
				mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
			}
		});
	}
}

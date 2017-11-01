package com.coamctech.admin.generator.swing.action;

import java.awt.event.ActionEvent;
import java.net.URL;

import com.coamctech.admin.generator.swing.frame.MainFrame;
import com.coamctech.admin.generator.swing.utils.BrowserLauncher;

public class HelpSubmitBug extends AbstractDefaultAction {

	private static final long serialVersionUID = -6403825795962682216L;

	public HelpSubmitBug(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			BrowserLauncher
					.openURL(new URL("mailto:" + "lilei-dfjk@coamc.com"));
		} catch (Exception e2) {
			this.mainFrame.getStatusBar().setMessage(e2);
		}
	}
}

package com.coamctech.admin.generator.swing.action;

import java.awt.event.ActionEvent;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public class FileExit extends AbstractDefaultAction {
	private static final long serialVersionUID = 4043067127106476840L;

	public FileExit(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	public void update() {
	}
}

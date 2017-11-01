package com.coamctech.admin.generator.swing.action;

import java.awt.event.ActionEvent;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public class ViewStatusBar extends AbstractCheckBoxAction {
	private static final long serialVersionUID = 215926113718607221L;

	public ViewStatusBar(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void actionPerformed(ActionEvent e) {
		this.mainFrame.getStatusBar().setVisible(
				!this.mainFrame.getStatusBar().isVisible());
		this.mainFrame.update();
	}

	public boolean isSelected(String actionCommand) {
		return this.mainFrame.getStatusBar().isVisible();
	}
}

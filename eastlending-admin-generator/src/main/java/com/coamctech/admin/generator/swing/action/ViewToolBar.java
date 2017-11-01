package com.coamctech.admin.generator.swing.action;

import java.awt.event.ActionEvent;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public class ViewToolBar extends AbstractCheckBoxAction {
	private static final long serialVersionUID = -1661031324593279248L;

	public ViewToolBar(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void actionPerformed(ActionEvent e) {
		this.mainFrame.getToolBar().setVisible(
				!this.mainFrame.getToolBar().isVisible());
	}

	public boolean isSelected(String actionCommand) {
		return this.mainFrame.getToolBar().isVisible();
	}
}

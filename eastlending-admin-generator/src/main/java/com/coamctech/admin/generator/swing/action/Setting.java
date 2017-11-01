package com.coamctech.admin.generator.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import com.coamctech.admin.generator.swing.dialog.SettingDialog;
import com.coamctech.admin.generator.swing.frame.MainFrame;
import com.coamctech.admin.generator.swing.utils.SwingUtils;

public class Setting extends AbstractDefaultAction {

	private static final long serialVersionUID = -829482023281712337L;

	public Setting(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new SettingDialog(this.mainFrame);
		SwingUtils.center(dialog);
		dialog.setVisible(true);
		this.mainFrame.update();
	}

	public void update() {

	}

}

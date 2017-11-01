package com.coamctech.admin.generator.swing.action;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public abstract class AbstractCheckBoxAction extends AbstractToggleAction {

	private static final long serialVersionUID = 1L;

	public AbstractCheckBoxAction(MainFrame mainFrame) {
		super(mainFrame);
	}

	protected Component getMenuComponent(String actionCommand) {
		JCheckBoxMenuItem button = new JCheckBoxMenuItem(this);
		String presentationText = getPresentationText(actionCommand);
		if (presentationText != null) {
			button.setText(presentationText);
		}
		this.abstractButtons.add(button);
		return button;
	}

	protected Component getToolComponent(String actionCommand) {
		JCheckBox button = new JCheckBox(this);
		String presentationText = getPresentationText(actionCommand);
		if (presentationText != null) {
			button.setText(presentationText);
		}
		this.abstractButtons.add(button);
		return button;
	}

}

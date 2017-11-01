package com.coamctech.admin.generator.swing.action;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public abstract class AbstractDefaultAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	protected MainFrame mainFrame;

	public AbstractDefaultAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public String getName() {
		return (String) getValue("Name");
	}

	public void update() {
	}

	public Component[] getMenuComponents() {
		return new Component[] { getMenuComponent(null) };
	}

	protected Component getMenuComponent(String actionCommand) {
		JMenuItem item = new JMenuItem(this);
		String presentationText = getPresentationText(actionCommand);
		if (presentationText != null) {
			item.setText(presentationText);
		}
		return item;
	}

	public String getPresentationText(String actionCommand) {
		return null;
	}

	public Component[] getToolComponents() {
		return new Component[] { getToolComponent(null) };
	}

	protected Component getToolComponent(String actionCommand) {
		AbstractButton button = new JButton(this);
		String presentationText = getPresentationText(actionCommand);
		if (presentationText != null) {
			button.setText(presentationText);
		}
		return button;
	}
}

package com.coamctech.admin.generator.swing.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.AbstractButton;

import com.coamctech.admin.generator.swing.frame.MainFrame;

public abstract class AbstractToggleAction extends AbstractDefaultAction {
	private static final long serialVersionUID = 1L;
	protected Collection<Object> abstractButtons = new ArrayList<Object>();

	public AbstractToggleAction(MainFrame mainFrame) {
		super(mainFrame);
	}

	public void update() {
		super.update();
		for (Iterator<Object> iter = this.abstractButtons.iterator(); iter
				.hasNext();) {
			AbstractButton button = (AbstractButton) iter.next();
			button.setSelected(isSelected(button.getActionCommand()));
		}
	}

	public void removeAbstractButton(AbstractButton button) {
		this.abstractButtons.remove(button);
	}

	public abstract boolean isSelected(String paramString);
}

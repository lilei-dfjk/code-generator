package com.coamctech.admin.generator.swing.frame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.coamctech.admin.generator.swing.BarFactory;
import com.coamctech.admin.generator.swing.StatusBar;
import com.coamctech.admin.generator.swing.action.AbstractDefaultAction;
import com.coamctech.admin.generator.swing.action.FileExit;
import com.coamctech.admin.generator.swing.action.HelpSubmitBug;
import com.coamctech.admin.generator.swing.action.Setting;
import com.coamctech.admin.generator.swing.utils.ImageLoader;
import com.coamctech.admin.generator.swing.utils.ResourceBundleUtils;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4330284496815809914L;
	private JPanel mainContentPane = null;
	private JDesktopPane mainDesktopPane = null;
	private static ActionMap currentActionMap = new ActionMap();
	private StatusBar statusBar = null;
	private JToolBar toolBar = null;

	static {
		ResourceBundleUtils
				.pushBundle("com.coamctech.admin.generator.swing.action.ActionResource");
		ImageLoader
				.pushSearchPath("/com/coamctech/admin/generator/swing/images");
	}

	private JDesktopPane getMainDesktopPane() {
		if (this.mainDesktopPane == null) {
			this.mainDesktopPane = new JDesktopPane();
		}
		return this.mainDesktopPane;
	}

	public StatusBar getStatusBar() {
		if (this.statusBar == null) {
			this.statusBar = new StatusBar();
		}
		return this.statusBar;
	}

	public JToolBar getToolBar() {
		if (this.toolBar == null) {
			this.toolBar = BarFactory.createToolBar();
		}
		return this.toolBar;
	}

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		setSize(571, 463);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainFrame.getAction("FileExit").actionPerformed(null);
			}
		});
		fillDefaultActionMap();
		setTitle("Generator");
		setJMenuBar(BarFactory.createMenuBar());
		setContentPane(getMainContentPane());
	}

	private JPanel getMainContentPane() {
		if (this.mainContentPane == null) {
			this.mainContentPane = new JPanel();
			this.mainContentPane.setLayout(new BorderLayout());
			this.mainContentPane.add(getStatusBar(), "South");
			this.mainContentPane.add(getMainDesktopPane(), "Center");
			// this.mainContentPane.add(getToolBar(), "North");
		}
		return this.mainContentPane;
	}

	public JInternalFrame getCurrentFrame() {
		if (this.mainDesktopPane == null) {
			return null;
		}
		return this.mainDesktopPane.getSelectedFrame();
	}

	public void update() {
		Object[] keys = currentActionMap.keys();
		for (int i = 0; i < keys.length; i++) {
			Action a = currentActionMap.get(keys[i]);
			if ((a instanceof AbstractDefaultAction)) {
				((AbstractDefaultAction) a).update();
			}
		}
	}

	public void fillDefaultActionMap() {
		Action[] defaultActions = { new FileExit(this), new Setting(this),
				new HelpSubmitBug(this) };
		String name = null;
		for (int i = 0; i < defaultActions.length; i++) {
			Action action = defaultActions[i];
			name = action.getClass().getSimpleName();
			currentActionMap.put(name, action);
		}
	}

	public Action getAction(AbstractButton button) {
		Action action = currentActionMap.get(button.getText());
		if (action == null) {
			return null;
		}
		if (((button instanceof JMenuItem))
				&& (action.getValue("AcceleratorKey") != null)) {
			((JMenuItem) button).setAccelerator((KeyStroke) action
					.getValue("AcceleratorKey"));
		}
		return action;
	}

	public static Action getAction(String commandKey) {
		Action action = currentActionMap.get(commandKey);
		return action;
	}

	public void addFrame(JInternalFrame frame) {
		getMainDesktopPane().add(frame);
		frame.setVisible(true);
	}

}

package com.coamctech.admin.generator.swing;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.coamctech.admin.generator.swing.action.AbstractDefaultAction;
import com.coamctech.admin.generator.swing.frame.MainFrame;
import com.coamctech.admin.generator.swing.utils.ImageLoader;
import com.coamctech.admin.generator.swing.utils.ResourceBundleUtils;

public class BarFactory {
	public static String MENU = ".Menu";
	public static String BUTTON = ".Button";
	public static String NAME = ".Name";
	public static String TEXT = ".Text";
	public static String MNEMONIC_KEY = ".MnemonicKey";
	public static String ACCELERATOR_KEY = ".AcceleratorKey";
	public static Icon BLANK_ICON = ImageLoader.getImageIcon("Blank.gif");

	public static JMenuBar createMenuBar() {
		JMenuBar mainMenuBar = new JMenuBar();
		String menuBarString = ResourceBundleUtils.getString("MenuBar");
		String[] menuStrings = menuBarString.split(",");
		for (int i = 0; i < menuStrings.length; i++) {
			String menuKey = menuStrings[i].trim();
			if (menuKey.length() != 0) {
				JMenu menu = createMenu(menuKey);
				mainMenuBar.add(menu);
			}
		}
		return mainMenuBar;
	}

	private static JMenu createMenu(String menuKey) {
		JMenu menu = new JMenu();
		String text = ResourceBundleUtils.getString(menuKey + TEXT);
		menu.setText(text);

		String value = ResourceBundleUtils.getString(menuKey + MNEMONIC_KEY);
		if ((value != null) && (value.length() == 1)) {
			menu.setMnemonic(value.charAt(0));
		}
		String menuString = ResourceBundleUtils.getString(menuKey + MENU);
		String[] menuStrings = menuString.split(",");
		for (int i = 0; i < menuStrings.length; i++) {
			String key = menuStrings[i].trim();
			if (key.length() != 0) {
				if (key.equals("-")) {
					menu.addSeparator();
				} else {
					String subMenuString = ResourceBundleUtils.getString(key + MENU);
					if (subMenuString == null) {
						Component[] mi = createMenuItem(key);
						for (int j = 0; j < mi.length; j++) {
							if (mi[j] != null) {
								menu.add(mi[j]);
							}
						}
					} else {
						JMenu subMenu = createMenu(key);
						ImageIcon imageIcon = ImageLoader.getImageIcon(key + ".gif");
						if (imageIcon != null) {
							subMenu.setIcon(imageIcon);
						} else {
							subMenu.setIcon(BLANK_ICON);
						}
						menu.add(subMenu);
					}
				}
			}
		}
		return menu;
	}

	private static Component[] createMenuItem(String menuKey) {
		Action action = getAction(menuKey);
		if (action == null) {
			return new Component[0];
		}
		if ((action instanceof AbstractDefaultAction)) {
			return ((AbstractDefaultAction) action).getMenuComponents();
		}
		JMenuItem menuItem = new JMenuItem();
		menuItem.setAction(getAction(menuKey));
		menuItem.setText(ResourceBundleUtils.getString(menuKey + TEXT));
		String value = ResourceBundleUtils.getString(menuKey + MNEMONIC_KEY);
		if ((value != null) && (value.length() == 1)) {
			menuItem.setMnemonic(value.charAt(0));
		}
		return new Component[] { menuItem };
	}

	public static JToolBar createToolBar() {
		JToolBar mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		String toolBarString = ResourceBundleUtils.getString("ToolBar");
		String[] toolStrings = toolBarString.split(",");
		for (int i = 0; i < toolStrings.length; i++) {
			String barKey = toolStrings[i].trim();
			if (barKey.length() != 0) {
				JToolBar toolBar = createToolBar(barKey);
				mainToolBar.add(toolBar);
			}
		}
		return mainToolBar;
	}

	private static JToolBar createToolBar(String menuKey) {
		JToolBar toolBar = new JToolBar();
		String name = ResourceBundleUtils.getString(menuKey + NAME);
		toolBar.setName(name);
		String buttonString = ResourceBundleUtils.getString(menuKey + BUTTON);
		String[] buttonStrings = buttonString.split(",");
		for (int i = 0; i < buttonStrings.length; i++) {
			String key = buttonStrings[i].trim();
			if (key.length() != 0) {
				if (key.equals("-")) {
					toolBar.addSeparator();
				} else {
					Action action = getAction(key);
					toolBar.add(action);
				}
			}
		}
		return toolBar;
	}

	private static Action getAction(String actionCommand) {
		if (actionCommand == null) {
			return null;
		}
		Action action = MainFrame.getAction(actionCommand);
		if (action != null) {
			action.putValue("Name",
					ResourceBundleUtils.getString(actionCommand + TEXT));
			String value = "";
			value = ResourceBundleUtils.getString(actionCommand
					+ ACCELERATOR_KEY);
			if ((value != null) && (value.length() > 0)) {
				action.putValue("AcceleratorKey", KeyStroke.getKeyStroke(value));
			}
			ImageIcon imageIcon = ImageLoader.getImageIcon(actionCommand + ".gif");
			if (imageIcon != null) {
				action.putValue("SmallIcon", imageIcon);
			} else {
				action.putValue("SmallIcon", BLANK_ICON);
			}
		}
		return action;
	}
}

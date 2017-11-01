package com.coamctech.admin.generator.swing.utils;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Stack;

public class ResourceBundleUtils {
	private static Stack<Object> bundles = new Stack<Object>();
	private static Stack<Object> bundleNames = new Stack<Object>();

	public static void pushBundle(String bundleName) {
		ResourceBundle resourcebundle = PropertyResourceBundle
				.getBundle(bundleName);
		bundles.push(resourcebundle);
		bundleNames.push(bundleName);
	}

	public static void removeBundle(int bundleIndex) {
		bundles.remove(bundleIndex);
		bundleNames.remove(bundleIndex);
	}

	public static boolean removeBundle(String bundleName) {
		for (int i = 0; i < bundleNames.size(); i++) {
			String oldBundleName = (String) bundleNames.get(i);
			if (oldBundleName.equals(bundleName)) {
				bundles.remove(i);
				bundleNames.remove(i);
				return true;
			}
		}
		return false;
	}

	public static void reloadBundles() {
		ResourceBundle resourcebundle = null;
		for (int i = 0; i < bundleNames.size(); i++) {
			String bundleName = (String) bundleNames.get(i);
			resourcebundle = PropertyResourceBundle.getBundle(bundleName);
			bundles.set(i, resourcebundle);
		}
	}

	public static String getString(String key) {
		String value = getString(bundles.size() - 1, key);
		return value;
	}

	private static String getString(int bundleIndex, String key) {
		if ((bundleIndex < bundles.size()) && (bundleIndex >= 0)) {
			ResourceBundle bundle = (ResourceBundle) bundles.get(bundleIndex);
			try {
				return bundle.getString(key);
			} catch (MissingResourceException mrex) {
				return getString(bundleIndex - 1, key);
			}
		}
		return null;
	}
}

package com.coamctech.admin.generator.swing.utils;

import java.awt.Image;
import java.net.URL;
import java.util.Stack;

import javax.swing.ImageIcon;

public class ImageLoader {
	private static Stack<String> searchPath = new Stack<String>();

	public static Image getImage(String imageName) {
		return getImageIcon(imageName).getImage();
	}

	public static ImageIcon getImageIcon(String imageName) {
		return getImageIcon(searchPath.size() - 1, imageName);
	}

	public static ImageIcon getImageIcon(int searchPathIndex, String imageName) {
		if (imageName == null) {
			return null;
		}
		if ((searchPathIndex < searchPath.size()) && (searchPathIndex >= 0)) {
			URL url = ImageLoader.class.getResource((String) searchPath
					.get(searchPathIndex) + imageName);
			if (url != null) {
				return new ImageIcon(url);
			}
			return getImageIcon(searchPathIndex - 1, imageName);
		}
		return null;
	}

	public static void pushSearchPath(String path) {
		if (path == null) {
			return;
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		searchPath.push(path);
	}

	public static void removeSearchPath(int index) {
		searchPath.remove(index);
	}

	public static void popSearchPath() {
		searchPath.pop();
	}

}

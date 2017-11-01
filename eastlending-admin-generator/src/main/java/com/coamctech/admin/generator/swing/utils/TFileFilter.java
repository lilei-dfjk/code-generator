package com.coamctech.admin.generator.swing.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TFileFilter extends FileFilter {
	private String description;
	private String extention;

	public boolean accept(File f) {
		if (f == null) {
			return false;
		}
		if (f.getName() == null) {
			return false;
		}
		if (f.getName().toLowerCase()
				.endsWith(("." + this.extention).toLowerCase())) {
			return true;
		}
		if (f.isDirectory()) {
			return true;
		}
		return false;
	}

	public TFileFilter(String description, String extention) {
		this.description = description;
		this.extention = extention;
	}

	public String getDescription() {
		return this.description;
	}

	public String getExtention() {
		return this.extention;
	}
}

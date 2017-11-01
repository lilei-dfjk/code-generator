package com.coamctech.admin.generator.swing.render;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRender extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		boolean selected = false;
		if((null != value && !value.equals("-1") && !value.toString().equals("false"))
				|| value.toString().equals("true")) {
			selected = true;
		}
		setSelected(selected);
		setHorizontalAlignment((int) 0.5f);
		return this;
	}

}

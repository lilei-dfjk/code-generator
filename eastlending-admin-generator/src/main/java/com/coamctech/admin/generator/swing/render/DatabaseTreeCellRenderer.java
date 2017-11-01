package com.coamctech.admin.generator.swing.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.coamctech.admin.generator.swing.tree.node.ColumnMeta;
import com.coamctech.admin.generator.swing.tree.node.DataBaseMeta;
import com.coamctech.admin.generator.swing.tree.node.TableMeta;
import com.coamctech.admin.generator.swing.utils.ImageLoader;

public class DatabaseTreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = -3875051515862957913L;
	private static final Icon DATABASE_ICON = ImageLoader.getImageIcon("Database.gif");
	private static final Icon TABLE_ICON = ImageLoader.getImageIcon("Table.gif");
	private static final Icon COLUMN_ICON = ImageLoader.getImageIcon("Column.gif");
	private static final Icon KEYCOLUMN_ICON = ImageLoader.getImageIcon("KeyColumn.gif");

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if (value.getClass() == DataBaseMeta.class) {
			DataBaseMeta database = (DataBaseMeta) value;
			setText(database.getName());
			setIcon(DATABASE_ICON);
		} else if (value.getClass() == DefaultMutableTreeNode.class
				&& ((DefaultMutableTreeNode) value).getUserObject().getClass() == new TableMeta[0].getClass()) {
			TableMeta[] tables = (TableMeta[]) ((DefaultMutableTreeNode) value).getUserObject();
			setText("Tables(" + tables.length + ")");
		} else if (value.getClass() == TableMeta.class) {
			TableMeta table = (TableMeta) value;
			setText(table.getTableName());
			setIcon(TABLE_ICON);
		} else if (value.getClass() == ColumnMeta.class) {
			ColumnMeta column = (ColumnMeta) value;
			setText(column.getColumnName());
			if (column.getPrimaryKey()) {
				setForeground(Color.BLUE);
				setIcon(KEYCOLUMN_ICON);
			} else {
				setIcon(COLUMN_ICON);
			}
		} else if (value.getClass() == DefaultMutableTreeNode.class
				&& ((DefaultMutableTreeNode) value).getUserObject().getClass() == new ColumnMeta[0]
						.getClass()) {
			ColumnMeta[] columns = (ColumnMeta[]) ((DefaultMutableTreeNode) value)
					.getUserObject();
			setText("Columns(" + columns.length + ")");
		}
		return this;
	}
}

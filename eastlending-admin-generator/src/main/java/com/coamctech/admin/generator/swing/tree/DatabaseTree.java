package com.coamctech.admin.generator.swing.tree;

import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.coamctech.admin.generator.swing.render.DatabaseTreeCellRenderer;
import com.coamctech.admin.generator.swing.tree.node.ColumnMeta;
import com.coamctech.admin.generator.swing.tree.node.DataBaseMeta;
import com.coamctech.admin.generator.swing.tree.node.TableMeta;

public abstract class DatabaseTree {
	public static final JTree getDatabaseTree(
			Map<String, List<TableMeta>> tableMetas) {
		JTree tree = null;
		if (null != tableMetas) {
			for (String key : tableMetas.keySet()) {
				DataBaseMeta top = new DataBaseMeta(key);

				DefaultMutableTreeNode tableNodes = new DefaultMutableTreeNode(
						tableMetas.get(key).toArray(
								new TableMeta[tableMetas.get(key).size()]));

				for (TableMeta tableMeta : tableMetas.get(key)) {
					DefaultMutableTreeNode columnNodes = new DefaultMutableTreeNode(
							tableMeta.getColumns().toArray( new ColumnMeta[tableMeta.getColumns().size()]));

					for (ColumnMeta columnMeta : tableMeta.getColumns()) {
						columnNodes.add(columnMeta);
					}
					tableMeta.add(columnNodes);
					tableNodes.add(tableMeta);
				}

				top.add(tableNodes);
				tree = new JTree(top);
			}
		}
		tree.setCellRenderer(new DatabaseTreeCellRenderer());
		return tree;
	}

}

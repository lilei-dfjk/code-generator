package com.coamctech.admin.generator.swing.tree.node;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TableMeta extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 4805598997866612145L;
	private String schemaName;
	private String tableName;
	private String comment;
	private List<ColumnMeta> columns;

}

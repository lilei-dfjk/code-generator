package com.coamctech.admin.generator.swing.tree.node;

import javax.swing.tree.DefaultMutableTreeNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ColumnMeta extends DefaultMutableTreeNode {
	private static final long serialVersionUID = -4345591433649117331L;
	private String columnName;
	private String columnCName;// 中文名称
	private String dataType;
	private Boolean primaryKey = false;
	private Boolean nullable = false;
	private String ordinalPosition;
	private String columnDefault;
	private String columnComment;
	private Integer columnSize;
	private Boolean condition = false;
	private Boolean queryable = false;
	private Integer orderBy = -1;// -1代表非排序字段
}

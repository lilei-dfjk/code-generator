package com.coamctech.admin.generator.swing.tree.node;

import javax.swing.tree.DefaultMutableTreeNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class DataBaseMeta extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 2923160796300527533L;
	private String name;
}

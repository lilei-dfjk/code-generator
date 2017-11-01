package com.coamctech.admin.generator.swing.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.coamctech.admin.generator.swing.tree.node.TableMeta;

public class TablesPanel extends JPanel {
	private static final long serialVersionUID = 5822413910894722258L;
	private JTable mainTable = null;
	private JPanel mainPanel = null;
	private JScrollPane mainScrollPane = null;
	List<TableMeta> tables = null;

	public TablesPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		setSize(300, 200);
		add(getMainPanel(), "Center");
	}

	private JTable getJTable() {
		if (this.mainTable == null) {
			this.mainTable = new JTable();
		}
		return this.mainTable;
	}

	private JPanel getMainPanel() {
		if (this.mainPanel == null) {
			this.mainPanel = new JPanel();
			this.mainPanel.setLayout(new BorderLayout());
			this.mainPanel.setBorder(BorderFactory.createTitledBorder(null,
					"数据库表", 0, 0, null, null));
			this.mainPanel.add(getMainScrollPane(), "Center");
		}
		return this.mainPanel;
	}

	public void setTables(List<TableMeta> tables) {
		this.tables = tables;
		fillTable();
	}

	private void fillTable() {
		String[] columnNames = { "表代码", "注释" };
		String[][] data = new String[tables.size()][2];
		for (int i = 0; i < tables.size(); i++) {
			TableMeta table = tables.get(i);
			data[i][0] = table.getTableName();
			data[i][1] = table.getComment();
		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		this.mainTable.setModel(model);
		this.mainTable.revalidate();
	}

	private JScrollPane getMainScrollPane() {
		if (this.mainScrollPane == null) {
			this.mainScrollPane = new JScrollPane();
			this.mainScrollPane.setViewportView(getJTable());
		}
		return this.mainScrollPane;
	}
}

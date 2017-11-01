package com.coamctech.admin.generator.swing.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import com.coamctech.admin.generator.GenerateResource;
import com.coamctech.admin.generator.bean.FieldGenerate;
import com.coamctech.admin.generator.bean.ModelProperty;
import com.coamctech.admin.generator.swing.editor.CheckBoxCellEditor;
import com.coamctech.admin.generator.swing.render.CheckBoxRender;
import com.coamctech.admin.generator.swing.tree.node.ColumnMeta;
import com.coamctech.admin.generator.swing.tree.node.TableMeta;

public class ColumnsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable mainTable = null;
	private JPanel mainPanel = null;
	private JPanel controlPanel = null;
	private TableMeta table = null;
	private JScrollPane mainScrollPane = null;
	private JPanel jPanel = null;
	private JLabel tableCodeLabel = null;
	private JTextField tableCodeTextField = null;
	private JLabel tableNameLabel = null;
	private JTextField tableNameTextField = null;
	private JButton generateButton = null;
	private ModelProperty modelProperty;

	public ColumnsPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		setSize(300, 200);
		add(getMainPanel(), "Center");
		add(getJPanel(), "North");
		add(getControlPanel(), "South");
	}

	private JTable getJTable() {
		if (this.mainTable == null) {
			this.mainTable = new JTable();
			this.mainTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		}
		return this.mainTable;
	}

	private JPanel getMainPanel() {
		if (this.mainPanel == null) {
			this.mainPanel = new JPanel();
			this.mainPanel.setLayout(new BorderLayout());
			this.mainPanel.setBorder(BorderFactory.createTitledBorder(null,
					"字段列表", 0, 0, null, null));
			this.mainPanel.add(getMainScrollPane(), "Center");
		}
		return this.mainPanel;
	}

	private JPanel getControlPanel() {
		if (this.controlPanel == null) {
			this.controlPanel = new JPanel();
			this.controlPanel.setLayout(new FlowLayout());
			this.controlPanel.add(getGenerateButton(), null);
		}
		return this.controlPanel;
	}

	private JButton getGenerateButton() {
		if (this.generateButton == null) {
			this.generateButton = new JButton();
			this.generateButton.setText("生成资源");
			this.generateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ColumnsPanel.this.mainTable.clearSelection();
						List<FieldGenerate> columns = new ArrayList<FieldGenerate>(
								ColumnsPanel.this.table.getColumns().size());
						Set<String> importsPackage = new HashSet<String>();

						int i = 0;
						for (ColumnMeta columnMeta : ColumnsPanel.this.table.getColumns()) {
							FieldGenerate generator = new FieldGenerate();
							generator.setCode(columnMeta.getColumnName());
							generator.setName((String) ColumnsPanel.this.mainTable.getValueAt(i, 4));
							generator.setFieldType(columnMeta.getDataType());
							generator.setNullable(columnMeta.getNullable());
							generator.setCondition(Boolean.parseBoolean(ColumnsPanel.this.mainTable.getValueAt(i, 5).toString()));
							generator.setQueryable(Boolean.parseBoolean(ColumnsPanel.this.mainTable.getValueAt(i, 6).toString()));
							generator.setPrimaryKey(columnMeta.getPrimaryKey());
							generator.setLength(columnMeta.getColumnSize());

							if ((Boolean.parseBoolean(ColumnsPanel.this.mainTable
											.getValueAt(i, 7).toString())) == true) {
								if (null != ColumnsPanel.this.mainTable.getValueAt(i, 8)) {
									generator.setOrderBy(Integer.parseInt(ColumnsPanel.this.mainTable
													.getValueAt(i, 8).toString()));
								} else {
									generator.setOrderBy(1);
								}

								importsPackage.add("javax.persistence.OrderBy");
							} else {
								generator.setOrderBy(-1);
							}

							if (columnMeta.getDataType().contains(".")) {
								importsPackage.add(columnMeta.getDataType());
							}
							columns.add(generator);
							i++;
						}

						String tableName = ColumnsPanel.this.table
								.getTableName();
						new GenerateResource(tableName, ColumnsPanel.this.modelProperty, columns,importsPackage)
							.generateAdminResource();
						JOptionPane.showMessageDialog(null, "生成成功");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.toString());
					}
				}
			});
		}
		return this.generateButton;
	}

	public void setTable(TableMeta table, ModelProperty modelProperty) {
		this.modelProperty = modelProperty;
		this.table = table;
		fillTable();
	}

	private void fillTable() {
		this.tableCodeTextField.setText(this.table.getTableName());
		this.tableNameTextField.setText(this.table.getComment());

		String[] columnNames = { "字段名称", "数据类型", "是否主键", "是否为空", "字段中文名称",
				"是否查询字段", "是否列表展示字段", "是否排序字段", "排序字段顺序(数字)" };

		String[][] data = new String[this.table.getColumns().size()][columnNames.length];
		for (int i = 0; i < this.table.getColumns().size(); i++) {
			ColumnMeta column = this.table.getColumns().get(i);
			data[i][0] = column.getColumnName();
			data[i][1] = column.getDataType();
			data[i][2] = column.getPrimaryKey() + "";
			data[i][3] = column.getNullable() + "";
			data[i][4] = column.getColumnCName() == null ? "" : column.getColumnCName();
			data[i][5] = column.getCondition() + "";
			data[i][6] = column.getQueryable() + "";
			data[i][7] = column.getOrderBy() + "";
		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3) {
					return false;
				}
				return true;
			}
		};
		this.mainTable.setModel(model);
		this.mainTable.getColumnModel().getColumn(5)
				.setCellEditor(new CheckBoxCellEditor());
		this.mainTable.getColumnModel().getColumn(5)
				.setCellRenderer(new CheckBoxRender());
		this.mainTable.getColumnModel().getColumn(6)
				.setCellEditor(new CheckBoxCellEditor());
		this.mainTable.getColumnModel().getColumn(6)
				.setCellRenderer(new CheckBoxRender());
		this.mainTable.getColumnModel().getColumn(7)
				.setCellEditor(new CheckBoxCellEditor());
		this.mainTable.getColumnModel().getColumn(7)
				.setCellRenderer(new CheckBoxRender());
		this.mainTable.revalidate();
	}

	private JScrollPane getMainScrollPane() {
		if (this.mainScrollPane == null) {
			this.mainScrollPane = new JScrollPane();
			this.mainScrollPane.setViewportView(getJTable());
		}
		return this.mainScrollPane;
	}

	private JPanel getJPanel() {
		if (this.jPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = 2;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0D;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			this.tableNameLabel = new JLabel();
			this.tableNameLabel.setText("表名称：");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = 2;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0D;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			this.tableCodeLabel = new JLabel();
			this.tableCodeLabel.setText("表代码：");
			this.jPanel = new JPanel();
			this.jPanel.setLayout(new GridBagLayout());
			this.jPanel.setBorder(BorderFactory.createTitledBorder(null, "表信息",
					0, 0, null, null));
			this.jPanel.add(this.tableCodeLabel, gridBagConstraints);
			this.jPanel.add(getTableCodeTextField(), gridBagConstraints1);
			this.jPanel.add(this.tableNameLabel, gridBagConstraints2);
			this.jPanel.add(getTableNameTextField(), gridBagConstraints3);
		}
		return this.jPanel;
	}

	private JTextField getTableCodeTextField() {
		if (this.tableCodeTextField == null) {
			this.tableCodeTextField = new JTextField();
			this.tableCodeTextField.setEditable(false);
		}
		return this.tableCodeTextField;
	}

	private JTextField getTableNameTextField() {
		if (this.tableNameTextField == null) {
			this.tableNameTextField = new JTextField();
			this.tableNameTextField.setEditable(false);
		}
		return this.tableNameTextField;
	}
}

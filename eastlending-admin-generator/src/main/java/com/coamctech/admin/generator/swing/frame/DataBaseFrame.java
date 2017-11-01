package com.coamctech.admin.generator.swing.frame;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.coamctech.admin.generator.bean.ModelProperty;
import com.coamctech.admin.generator.swing.panel.ColumnsPanel;
import com.coamctech.admin.generator.swing.tree.DatabaseTree;
import com.coamctech.admin.generator.swing.tree.node.TableMeta;
import com.coamctech.admin.generator.utils.DataBaseUtils;

public class DataBaseFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane = null;
	private JSplitPane mainSplitPane = null;
	private JScrollPane leftScrollPane = null;
	private boolean dirty = false;
	private static ColumnsPanel columnsPanel = new ColumnsPanel();
	private JPanel mainPanel = null;
	private MainFrame mainFrame = null;
	private ModelProperty modelProperty;
	private Map<String, List<TableMeta>> tables;

	private JSplitPane getMainSplitPane() {
		if (this.mainSplitPane == null) {
			this.mainSplitPane = new JSplitPane();
			this.mainSplitPane.setDividerLocation(250);
			this.mainSplitPane.setRightComponent(getMainPanel());
			this.mainSplitPane.setLeftComponent(getLeftScrollPane());
		}
		return this.mainSplitPane;
	}

	private JPanel getMainContentPane() {
		if (this.mainContentPane == null) {
			this.mainContentPane = new JPanel();
			this.mainContentPane.setLayout(new BorderLayout());
			this.mainContentPane.add(getMainSplitPane(), "Center");
		}
		return this.mainContentPane;
	}

	private JScrollPane getLeftScrollPane() {
		if (this.leftScrollPane == null) {
			this.leftScrollPane = new JScrollPane();
			this.leftScrollPane.setViewportView(getDbTree());
		}
		return this.leftScrollPane;
	}

	private JTree getDbTree() {
		JTree tree = DatabaseTree.getDatabaseTree(tables);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getNewLeadSelectionPath() == null) {
					return;
				}
				Class<?> c1 = e.getNewLeadSelectionPath()
						.getLastPathComponent().getClass();

				if (c1 == TableMeta.class) {
					DataBaseFrame.this.mainPanel.removeAll();
					DataBaseFrame.columnsPanel.setTable((TableMeta) e
							.getNewLeadSelectionPath().getPathComponent(2),
							modelProperty);
					DataBaseFrame.this.mainPanel
							.add(DataBaseFrame.columnsPanel);
				}

				DataBaseFrame.this.mainPanel.revalidate();
				DataBaseFrame.this.mainPanel.repaint();
			}

		});
		return tree;
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		// updateTitle();
	}

	private JPanel getMainPanel() {
		if (this.mainPanel == null) {
			this.mainPanel = new JPanel();
			this.mainPanel.setLayout(new BorderLayout());
		}
		return this.mainPanel;
	}

	public DataBaseFrame(MainFrame mainFrame, ModelProperty modelProperty) {
		this.mainFrame = mainFrame;
		this.modelProperty = modelProperty;
		tables = DataBaseUtils.findTables(modelProperty);
		initialize();
	}

	private void initialize() {
		setSize(610, 386);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new BorderLayout());
		setContentPane(getMainContentPane());
		addInternalFrameListener(new InternalFrameListener() {
			public void internalFrameClosing(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameActivated(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameOpened(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameClosed(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameIconified(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameDeiconified(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

			public void internalFrameDeactivated(InternalFrameEvent e) {
				DataBaseFrame.this.mainFrame.update();
			}

		});
		// updateTitle();
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

}

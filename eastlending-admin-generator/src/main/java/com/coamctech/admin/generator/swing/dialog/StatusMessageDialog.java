package com.coamctech.admin.generator.swing.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.coamctech.admin.generator.swing.StatusBar;
import com.coamctech.admin.generator.utils.ExceptionUtils;

public class StatusMessageDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel mainContentPane = null;
	private JScrollPane mainScrollPane = null;
	private JTextArea stackTraceTextArea = null;
	private JPanel controlPanel = null;
	private JPanel exceptionControlPanel = null;
	private JLabel serialNoLabel = null;
	private JButton nextButton = null;
	private JButton prevButton = null;
	private JButton cleanButton = null;
	private ArrayList<Object> exceptions = null;
	private JButton closeButton = null;
	private int currentIndex = -1;
	private StatusBar statusBar = null;

	public StatusMessageDialog() {
		initialize();
	}

	private void initialize() {
		setSize(574, 455);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				StatusMessageDialog.this.close();
			}
		});
		setTitle("状态显示对话框");
		setContentPane(getJContentPane());
	}

	private JPanel getJContentPane() {
		if (this.mainContentPane == null) {
			this.mainContentPane = new JPanel();
			this.mainContentPane.setLayout(new BorderLayout());
			this.mainContentPane.add(getMainScrollPane(), "Center");

			this.mainContentPane.add(getControlPanel(), "South");
		}
		return this.mainContentPane;
	}

	private JScrollPane getMainScrollPane() {
		if (this.mainScrollPane == null) {
			this.mainScrollPane = new JScrollPane();
			this.mainScrollPane.setEnabled(false);
			this.mainScrollPane.setViewportView(getStackTraceTextArea());
		}
		return this.mainScrollPane;
	}

	private JTextArea getStackTraceTextArea() {
		if (this.stackTraceTextArea == null) {
			this.stackTraceTextArea = new JTextArea();
			this.stackTraceTextArea.setEditable(false);
			this.stackTraceTextArea.setWrapStyleWord(true);
			this.stackTraceTextArea.setLineWrap(false);
		}
		return this.stackTraceTextArea;
	}

	private JPanel getControlPanel() {
		if (this.controlPanel == null) {
			this.controlPanel = new JPanel();
			this.controlPanel.add(getExceptionControlPanel(), null);
		}
		return this.controlPanel;
	}

	private JPanel getExceptionControlPanel() {
		if (this.exceptionControlPanel == null) {
			this.serialNoLabel = new JLabel();
			this.serialNoLabel.setText("0/0");
			this.exceptionControlPanel = new JPanel();
			this.exceptionControlPanel.add(this.serialNoLabel, null);
			this.exceptionControlPanel.add(getPrevButton(), null);
			this.exceptionControlPanel.add(getNextButton(), null);
			this.exceptionControlPanel.add(getCleanButton(), null);
			this.exceptionControlPanel.add(getCloseButton(), null);
		}
		return this.exceptionControlPanel;
	}

	private JButton getNextButton() {
		if (this.nextButton == null) {
			this.nextButton = new JButton();
			this.nextButton.setText("下一条");
			this.nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// StatusMessageDialog.access$108(StatusMessageDialog.this);
					StatusMessageDialog.this.update();
				}
			});
		}
		return this.nextButton;
	}

	private JButton getPrevButton() {
		if (this.prevButton == null) {
			this.prevButton = new JButton();
			this.prevButton.setText("上一条");
			this.prevButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatusMessageDialog.this.update();
				}
			});
		}
		return this.prevButton;
	}

	private JButton getCleanButton() {
		if (this.cleanButton == null) {
			this.cleanButton = new JButton();
			this.cleanButton.setText("清除");
			this.cleanButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatusMessageDialog.this.exceptions.clear();
					StatusMessageDialog.this.update();
				}
			});
		}
		return this.cleanButton;
	}

	public void setExceptions(ArrayList<Object> exceptions) {
		this.exceptions = exceptions;
		this.currentIndex = 0;
		update();
	}

	private JButton getCloseButton() {
		if (this.closeButton == null) {
			this.closeButton = new JButton();
			this.closeButton.setText("关闭");
			this.closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatusMessageDialog.this.close();
				}
			});
		}
		return this.closeButton;
	}

	private void close() {
		this.statusBar.update();
		dispose();
	}

	private void update() {
		if (this.exceptions.size() > 0) {
			this.serialNoLabel.setText(this.currentIndex + 1 + "/"
					+ this.exceptions.size());
			this.stackTraceTextArea.setText(ExceptionUtils
					.getStackTraceString((Throwable) this.exceptions
							.get(this.currentIndex)));

			this.cleanButton.setEnabled(true);
		} else {
			this.serialNoLabel.setText("0/0");
			this.stackTraceTextArea.setText("");
			this.cleanButton.setEnabled(false);
		}
		if (this.currentIndex <= 0) {
			this.prevButton.setEnabled(false);
		} else {
			this.prevButton.setEnabled(true);
		}
		if (this.currentIndex < this.exceptions.size() - 1) {
			this.nextButton.setEnabled(true);
		} else {
			this.nextButton.setEnabled(false);
		}
		this.stackTraceTextArea.setSelectionStart(0);
		this.stackTraceTextArea.setSelectionEnd(0);
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}
}

package com.coamctech.admin.generator.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.coamctech.admin.generator.swing.dialog.StatusMessageDialog;
import com.coamctech.admin.generator.swing.utils.SwingUtils;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel messageLabel = null;
	private JButton showStackTraceButton = null;
	private ArrayList<Object> exceptions = null;
	private Timer timer = null;

	private JButton getShowStackTraceButton() {
		if (this.showStackTraceButton == null) {
			this.showStackTraceButton = new JButton();
			this.showStackTraceButton.setText("     ");
			this.showStackTraceButton.setOpaque(true);
			this.showStackTraceButton.setEnabled(false);
			this.showStackTraceButton.setBorder(BorderFactory
					.createBevelBorder(1));

			this.showStackTraceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatusMessageDialog dialog = new StatusMessageDialog();
					dialog.setStatusBar(StatusBar.this);
					dialog.setExceptions(StatusBar.this.exceptions);
					SwingUtils.center(dialog);
					dialog.setVisible(true);
				}
			});
		}
		return this.showStackTraceButton;
	}

	public StatusBar() {
		initialize();
	}

	private void initialize() {
		this.messageLabel = new JLabel();
		this.messageLabel.setText("");
		this.messageLabel.setBorder(BorderFactory.createBevelBorder(1));

		setLayout(new BorderLayout());
		setSize(454, 24);
		add(this.messageLabel, "Center");
		add(getShowStackTraceButton(), "East");

		this.timer = new Timer(6000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusBar.this.messageLabel.setText("");
				StatusBar.this.timer.stop();
			}
		});
		this.exceptions = new ArrayList<>();
	}

	public void setMessage(String message) {
		this.messageLabel.setText(message);
		this.timer.restart();
	}

	public void setMessage(Throwable message) {
		if (message == null) {
			setMessage("");
			return;
		}
		message.printStackTrace();
		if ((message.getLocalizedMessage() != null)
				&& (message.getLocalizedMessage().trim().length() > 0)) {
			setMessage(message.getLocalizedMessage());
		} else {
			setMessage(message.getMessage());
		}
		this.exceptions.add(message);
		update();
	}

	public void update() {
		System.out.println("Update---------exceptions.size()="
				+ this.exceptions.size());
		if (this.exceptions.size() > 0) {
			this.showStackTraceButton.setEnabled(true);
			if (this.showStackTraceButton.getClientProperty("oldBackground") == null) {
				this.showStackTraceButton.putClientProperty("oldBackground",
						this.showStackTraceButton.getBackground());
			}
			this.showStackTraceButton.setBackground(Color.RED);
		} else {
			this.showStackTraceButton.setEnabled(false);
			this.showStackTraceButton.setBackground((Color) this.showStackTraceButton.getClientProperty("oldBackground"));
		}
	}
}

package com.coamctech.admin.generator.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.coamctech.admin.generator.bean.ModelProperty;
import com.coamctech.admin.generator.swing.frame.DataBaseFrame;
import com.coamctech.admin.generator.swing.frame.MainFrame;

public class SettingDialog extends JDialog {
	private static final long serialVersionUID = 1272957855540749006L;

	private static final String JDBCURL = "jdbc:mysql://localhost:3306/fisp?useUnicode=true&characterEncoding=GBK";

	private JPanel mainContentPane = null;
	private JPanel controlPanel = null;
	private JPanel mainPanel = null;

	private JButton okButton = null;
	private JButton cancelButton = null;

	private JTextField jdbcUrl;
	private JTextField jdbcUser;
	private JPasswordField jdbcPass;
	private JTextField outputPath;
	private JTextField packagePre;
	private JButton outputPathButton;

	private JLabel jdbcUrlLabel = null;
	private JLabel jdbcUserLabel = null;
	private JLabel jdbcPassLabel = null;
	private JLabel packagePreLabel = null;
	private JLabel outputPathLabel = null;

	private MainFrame mainFrame;

	public SettingDialog(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		initialize();
	}

	private void initialize() {
		setSize(600, 295);
		setModal(true);
		setContentPane(getMainContentPane());
		setTitle("设置应用属性");
	}

	private JPanel getMainContentPane() {
		if (this.mainContentPane == null) {
			this.mainContentPane = new JPanel();
			this.mainContentPane.setLayout(new BorderLayout());
			this.mainContentPane.setCursor(new Cursor(0));
			this.mainContentPane.add(getMainPanel(), "Center");
		}
		return this.mainContentPane;
	}

	private JTextField getJdbcUrlTextField() {
		if (this.jdbcUrl == null) {
			this.jdbcUrl = new JTextField();
			this.jdbcUrl.setText(JDBCURL);
		}
		return this.jdbcUrl;
	}

	private JTextField getJdbcUserTextField() {
		if (this.jdbcUser == null) {
			this.jdbcUser = new JTextField();
			this.jdbcUser.setText("root");
		}
		return this.jdbcUser;
	}

	private JTextField getJdbcPassTextField() {
		if (this.jdbcPass == null) {
			this.jdbcPass = new JPasswordField();
			this.jdbcPass.setText("root");
		}
		return this.jdbcPass;
	}

	private JTextField getOutputPathField() {
		if (this.outputPath == null) {
			this.outputPath = new JTextField();
			this.outputPath.setText("G://admin-resource");
		}
		return this.outputPath;
	}

	protected JTextField getPackagePreField() {
		if (this.packagePre == null) {
			this.packagePre = new JTextField();
			this.packagePre.setText("com.coamctech.eastlending.fisp");
		}
		return this.packagePre;
	}

	private JButton getOkButton() {
		if (this.okButton == null) {
			this.okButton = new JButton();
			this.okButton.setText("确定(O)");
			this.okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ModelProperty property = new ModelProperty();
						property.setJdbcUrl(SettingDialog.this
								.getJdbcUrlTextField().getText());
						property.setJdbcUser(SettingDialog.this
								.getJdbcUserTextField().getText());
						property.setJdbcPass(SettingDialog.this
								.getJdbcPassTextField().getText());
						property.setOutputPath(SettingDialog.this
								.getOutputPathField().getText());
						property.setPackagePre(SettingDialog.this
								.getPackagePreField().getText());
						SettingDialog.this.dispose();

						DataBaseFrame dbFrame = new DataBaseFrame(
								SettingDialog.this.mainFrame, property);
						SettingDialog.this.mainFrame.addFrame(dbFrame);
						SettingDialog.this.mainFrame.update();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(
								SettingDialog.this.mainFrame, ex.toString());
					}
				}
			});
		}
		return this.okButton;
	}

	private JButton getCancelButton() {
		if (this.cancelButton == null) {
			this.cancelButton = new JButton();
			this.cancelButton.setText("取消(C)");
			this.cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SettingDialog.this.dispose();
				}
			});
		}
		return this.cancelButton;
	}

	private JPanel getControlPanel() {
		if (this.controlPanel == null) {
			this.controlPanel = new JPanel();
			this.controlPanel.setLayout(new FlowLayout());
			this.controlPanel.add(getOkButton(), null);
			this.controlPanel.add(getCancelButton(), null);
		}
		return this.controlPanel;
	}

	private JButton getOutputPathButton() {
		if (this.outputPathButton == null) {
			this.outputPathButton = new JButton();
			this.outputPathButton.setCursor(new Cursor(0));

			this.outputPathButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser(SettingDialog.this
							.getOutputPathField().getText());

					chooser.setFileSelectionMode(1);

					chooser.showOpenDialog(SettingDialog.this.mainFrame);
					if (chooser.getSelectedFile() != null) {
						SettingDialog.this.getOutputPathField().setText(
								chooser.getSelectedFile().toString());
					}
				}
			});
			this.outputPathButton.setText("...");
		}
		return this.outputPathButton;
	}

	private JPanel getMainPanel() {
		if (this.mainPanel == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridwidth = 3;
			gridBagConstraints14.fill = 1;
			gridBagConstraints14.insets = new Insets(0, 0, 2, 0);
			gridBagConstraints14.gridy = 6;
			this.mainPanel = new JPanel();

			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			this.mainPanel.setLayout(gridBagLayout);

			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.anchor = 17;
			gridBagConstraints13.fill = 1;
			gridBagConstraints13.gridy = 0;

			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.fill = 2;
			gridBagConstraints12.gridy = 5;

			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = 1;
			gridBagConstraints7.gridy = 5;
			gridBagConstraints7.weightx = 1.0D;
			gridBagConstraints7.gridx = 1;

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = 1;
			gridBagConstraints11.gridy = 4;
			gridBagConstraints11.weightx = 1.0D;
			gridBagConstraints11.gridx = 1;

			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.fill = 2;
			gridBagConstraints10.gridy = 4;

			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints9.gridy = 5;
			gridBagConstraints9.ipadx = 50;
			gridBagConstraints9.ipady = 12;
			gridBagConstraints9.anchor = 17;
			gridBagConstraints9.fill = 1;
			gridBagConstraints9.gridx = 0;

			GridBagConstraints gridBagConstraints6_6 = new GridBagConstraints();
			gridBagConstraints6_6.fill = 2;
			gridBagConstraints6_6.gridx = 1;
			gridBagConstraints6_6.gridy = 4;
			gridBagConstraints6_6.ipadx = 50;
			gridBagConstraints6_6.ipady = 12;
			gridBagConstraints6_6.weightx = 1.0D;
			gridBagConstraints6_6.gridwidth = 3;
			gridBagConstraints6_6.insets = new Insets(0, 0, 0, 0);

			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.ipadx = 50;
			gridBagConstraints6.ipady = 12;
			gridBagConstraints6.anchor = 17;
			gridBagConstraints6.fill = 1;
			gridBagConstraints6.gridy = 4;

			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = 2;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.ipadx = 50;
			gridBagConstraints5.ipady = 12;
			gridBagConstraints5.weightx = 1.0D;
			gridBagConstraints5.gridwidth = 3;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);

			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.ipadx = 50;
			gridBagConstraints4.ipady = 12;
			gridBagConstraints4.anchor = 17;
			gridBagConstraints4.fill = 1;
			gridBagConstraints4.gridy = 2;

			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = 2;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 50;
			gridBagConstraints3.ipady = 12;
			gridBagConstraints3.weightx = 1.0D;
			gridBagConstraints3.gridwidth = 3;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 1);

			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.ipadx = 50;
			gridBagConstraints2.ipady = 12;
			gridBagConstraints2.fill = 1;
			gridBagConstraints2.gridy = 1;

			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = 2;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 50;
			gridBagConstraints1.ipady = 12;
			gridBagConstraints1.weightx = 1.0D;
			gridBagConstraints1.gridwidth = 3;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 0);

			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.fill = 1;

			this.mainPanel.setBorder(BorderFactory.createBevelBorder(0));

			this.jdbcUrlLabel = new JLabel();
			this.jdbcUrlLabel.setText("jdbc.url：");
			this.mainPanel.add(this.jdbcUrlLabel, gridBagConstraints);
			this.mainPanel.add(getJdbcUrlTextField(), gridBagConstraints1);

			this.jdbcUserLabel = new JLabel();
			this.jdbcUserLabel.setText("jdbc.user：");
			this.mainPanel.add(this.jdbcUserLabel, gridBagConstraints2);
			this.mainPanel.add(getJdbcUserTextField(), gridBagConstraints3);

			this.jdbcPassLabel = new JLabel();
			this.jdbcPassLabel.setText("jdbc.pass：");
			this.mainPanel.add(this.jdbcPassLabel, gridBagConstraints4);
			this.mainPanel.add(getJdbcPassTextField(), gridBagConstraints5);

			this.packagePreLabel = new JLabel();
			this.packagePreLabel.setText("类包名前缀：");
			this.mainPanel.add(this.packagePreLabel, gridBagConstraints6);
			this.mainPanel.add(getPackagePreField(), gridBagConstraints6_6);

			this.outputPathLabel = new JLabel();
			this.outputPathLabel.setText("代码输出路径：");
			this.mainPanel.add(outputPathLabel, gridBagConstraints9);
			this.mainPanel.add(getOutputPathField(), gridBagConstraints7);
			this.mainPanel.add(getOutputPathButton(), gridBagConstraints12);

			this.mainPanel.add(getControlPanel(), gridBagConstraints14);

		}
		return this.mainPanel;
	}

}

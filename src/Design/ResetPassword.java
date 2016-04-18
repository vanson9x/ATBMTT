package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

import Connection.sqlConnection;
import Security.MD5Encyptor;

import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPassword extends JDialog {
	public static String email="vanson9x.it@gmail.com";
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField;
	private JCheckBox showPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResetPassword dialog = new ResetPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResetPassword() {
		setTitle("Reset Password");
		setResizable(false);
		setBounds(100, 100, 377, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblMtKhuMi = new JLabel("M\u1EADt kh\u1EA9u m\u1EDBi: ");
		
		passwordField = new JPasswordField();
		
		showPassword = new JCheckBox("Hi\u1EC3n th\u1ECB m\u1EADt kh\u1EA9u");
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(showPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}else{
					passwordField.setEchoChar('*');
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("M\u00E3 x\u00E1c nh\u1EADn:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btniMtKhu = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		btniMtKhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connector = sqlConnection.dbConnector();
				String sql = "SELECT Code FROM Account WHERE Email = ?";
				try {
					PreparedStatement pts = connector.prepareStatement(sql);
					pts.setString(1, email);
					ResultSet rs = pts.executeQuery();
					if(rs.getString(1).equals(textField.getText().toString())){
							sql = "UPDATE Account SET PassWord = ? WHERE Email = ?";
							pts = connector.prepareStatement(sql);
							try {
								pts.setString(1,new MD5Encyptor().encrypt(passwordField.getText().toString()));
								pts.setString(2, email);
								pts.executeUpdate();
								JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công !");
								connector.close();
								Main.Login();
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}else{
							pts.close();
							rs.close();
							JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng !");
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblMtKhuMi)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(showPassword)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(127)
							.addComponent(btniMtKhu)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhuMi)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(showPassword))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btniMtKhu)
					.addGap(31))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}

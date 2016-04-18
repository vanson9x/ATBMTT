package Design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import Security.MD5Encyptor;
import Connection.sqlConnection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.UIManager;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox showPass;
	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Đăng Nhập");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel lblTiKhon = new JLabel("Tài khoản:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		
		passwordField = new JPasswordField();
		
		showPass = new JCheckBox("Hiện mật khẩu");
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(showPass.isSelected()){
					passwordField.setEchoChar((char)0);
				}else{
					passwordField.setEchoChar('*');
				}
			}
		});
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection connector = sqlConnection.dbConnector();
					String sql = "Select PassWord from Account where ID = ?";
					String MD5PassForm="+",MD5PassDB="-";
					MD5PassForm = new MD5Encyptor().encrypt(passwordField.getText().toString());
					PreparedStatement pts = connector.prepareStatement(sql);
					pts.setString(1, textField.getText().toString());
					ResultSet rs = pts.executeQuery();
					while(rs.next()){
						MD5PassDB = rs.getString(1);
						break;
					}
					if(MD5PassDB.equals(MD5PassForm)){
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
					}else{
						JOptionPane.showMessageDialog(null, "Mật khẩu hoặc tài khoản không đúng.");
					}
					connector.close();
				}catch(Exception e){}		
			}
		});
		btnLogin.setBackground(Color.ORANGE);
		JButton btnSignIn = new JButton("Đăng ký");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.SignIn();
			}
		});
		btnSignIn.setForeground(Color.BLACK);
		btnSignIn.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		JLabel lblResetPass = new JLabel("Nhấp vào đây để đặt lại mật khẩu");
		lblResetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.SendCode();
			}
		});
		lblResetPass.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		 GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(183, Short.MAX_VALUE)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(153))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(171)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(361, Short.MAX_VALUE)
					.addComponent(btnSignIn))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTiKhon, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMtKhu)
							.addGap(6)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(showPass, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
					.addGap(112))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(143, Short.MAX_VALUE)
					.addComponent(lblResetPass, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnSignIn)
					.addGap(13)
					.addComponent(label)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiKhon)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhu)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(showPass, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblResetPass)
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

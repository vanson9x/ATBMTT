package Design;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

import Security.MD5Encyptor;
import Connection.sqlConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JPasswordField passwordField;
	private JLabel lblMtKhu;
	private JTextField textFieldName;
	private JLabel lblHVTn;
	private JTextField textFieldEmail;
	private JLabel lblaCh;
	private JButton btnSignIn;
	JCheckBox showPassword;
	/**
	 * Create the frame.
	 */
	public SignIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Main.Login();
			}
		});
		btnLogin.setBackground(Color.ORANGE);
		
		JLabel lblngK = new JLabel("\u0110\u0103ng K\u00FD");
		lblngK.setForeground(Color.GREEN);
		lblngK.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblTiKhon = new JLabel("Tài khoản:");
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		
		passwordField = new JPasswordField();
		
		lblMtKhu = new JLabel("Mật khẩu:");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		lblHVTn = new JLabel("Họ Và Tên:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		lblaCh = new JLabel("Email:");
		
		btnSignIn = new JButton("Đăng Ký");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldID.getText().equals("") || textFieldName.getText().equals("") || textFieldEmail.getText().equals("") || passwordField.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Đăng ký thất bại !");
				else{
					try{
						Connection connector = sqlConnection.dbConnector();
						String sql = "INSERT INTO Account(ID,PassWord,HoVaTen,Email) VALUES (?,?,?,?)";
						PreparedStatement pts = connector.prepareStatement(sql);
						pts.setString(1,textFieldID.getText().toString());
						pts.setString(2,new MD5Encyptor().encrypt(passwordField.getText().toString()));
						pts.setString(3, textFieldName.getText().toString());
						pts.setString(4, textFieldEmail.getText().toString());
						pts.execute();
						JOptionPane.showMessageDialog(null,"Đăng ký thành công !");
						setVisible(false);
						Main.Login();
					}catch(Exception e){System.out.println(e);}
				}
			}	
		});
		btnSignIn.setForeground(Color.BLACK);
		btnSignIn.setBackground(Color.BLUE);
		
		showPassword = new JCheckBox("Hiển thị mật khẩu");
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}else{
					passwordField.setEchoChar('*');
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addComponent(lblngK, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(216))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(313, Short.MAX_VALUE)
					.addComponent(btnLogin)
					.addGap(54))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTiKhon)
								.addComponent(lblMtKhu))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField, Alignment.LEADING)
								.addComponent(textFieldID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(showPassword, Alignment.LEADING)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHVTn)
								.addComponent(lblaCh))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldEmail)
								.addComponent(textFieldName, 161, 161, Short.MAX_VALUE)
								.addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(lblngK)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiKhon)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMtKhu))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(showPassword))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHVTn)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblaCh))
					.addGap(30)
					.addComponent(btnSignIn)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

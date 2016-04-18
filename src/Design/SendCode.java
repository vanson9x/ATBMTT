package Design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Connection.MailClient;
import Connection.sqlConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SendCode extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	/**
	 * Create the dialog.
	 */
	public SendCode() {
		setTitle("Send code confirm");
		setResizable(false);
		setBounds(100, 100, 309, 154);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		JLabel lblaChEmail = new JLabel("\u0110\u1ECBa ch\u1EC9 email:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("X\u00E1c nh\u1EADn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connector = sqlConnection.dbConnector();
				String sql = "SELECT * FROM Account WHERE Email = ?";
				PreparedStatement pts = null;
				ResultSet rs = null;
				
				try {
					pts = connector.prepareStatement(sql);
					pts.setString(1, textField.getText().toString());
					rs = pts.executeQuery();
					if(rs.next()){
						Random r = new Random();
						int code = r.nextInt(9000);
						MailClient.sendMail("o0onhocko0o@gmail.com", "01041995", textField.getText().toString(), "Mã xác nhận thay đổi mật khẩu",String.valueOf(code + 1000));
						//----------------------------------------------------------------------
						
						sql = "UPDATE Account SET Code = ? WHERE Email = ?";
						pts = connector.prepareStatement(sql);
						pts.setString(1, String.valueOf(code+1000));
						pts.setString(2, textField.getText().toString());
						ResetPassword.email = textField.getText().toString();
						
						pts.executeUpdate();
						connector.close();
						Main.ResetPassword();
					}else{
						JOptionPane.showMessageDialog(null, "Không tồn tại email này trong CSDL");
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
					.addContainerGap()
					.addComponent(lblaChEmail)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addGap(19))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(111, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(105))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(81)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblaChEmail)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}

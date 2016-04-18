package Connection;

import java.sql.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class sqlConnection {
	public static Connection dbConnector(){
		Connection connect = null;
		//Link Local Database
		String url="jdbc:sqlite:src/data.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
			//JOptionPane.showMessageDialog(null, "Connection Succses!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

}
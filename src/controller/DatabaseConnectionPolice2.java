package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionPolice2 {

	private static Connection con = null;

	static {
		String url = "jdbc:mysql://localhost:3306/policiasycriminales?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false";
		String user = "policia";
		String pass = "T0S3rv34ndPr0tect*";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}
}

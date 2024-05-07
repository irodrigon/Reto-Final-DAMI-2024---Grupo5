package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Usaremos esta clase para conectarnos al usuario noticias que ya hemos creado en la base de datos MysQL.
public class DatabaseConnectionNews{

	private static Connection con = null;

	static {
		String url = "jdbc:mysql://localhost:3306/policiasycriminales?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false";
		String user = "news";
		String pass = "news";
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

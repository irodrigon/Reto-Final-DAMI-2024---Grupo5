package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Usaremos esta clase para conectarnos al usuario administrador que ya hemos creado en la base de datos MysQL. 
public class DatabaseConnectionAdmin{

	private static Connection con = null;

	static {

		//La conexión a la base de datos gracias a las librerías importadas por el conector mysql.
		String url = "jdbc:mysql://localhost:3306/policiasycriminales?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false";
		//El usuario a usar, en este caso administrador.
		String user = "administrador";
		//La contraseña del usuario en cuestión.
		String pass = "Francisco.*?";
		try {
			//Sentencias propias del método: Driver de conexión.

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	* 	El método devuelve una conexión a la base de datos como administrador.
	* 	@return objeto Connection con
	*/

	
	public static Connection getConnection() {
		return con;
	}
}

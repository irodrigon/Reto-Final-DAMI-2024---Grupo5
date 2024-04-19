package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import model.Criminal;
import model.News;

import model.Policia;

public class Controller implements InterfaceController{
	
	private Connection con;
	private PreparedStatement stmt;
	
	private final String SHOW_NEWS = "SELECT * FROM NOTICIA";
	private final String RETURN_POLICEMAN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia WHERE contrasena = ? AND dni in (SELECT dni from policia WHERE dni = ?);";
	private final String SHOW_POLICEMEN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia";

	private final String SHOW_ARSENAL ="SELECT * FROM ARSENAL ";
	private final String SHOW_CRIMINAL_BY_POLICEMAN = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni WHERE dni_policia = ?";
	
	@Override
	public Policia policeLogIn(String password, String dni) {
		
		con = DatabaseConnectionPolice2.getConnection();
		
		ResultSet rs = null;
		Policia p = null;
		
		try {
			
			stmt = con.prepareStatement(RETURN_POLICEMAN);
			stmt.setString(1, password);
			stmt.setString(2, dni);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				p = new Policia(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("contrasena"),rs.getBlob("fotografia_persona"),rs.getString("rango"));
			
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error en la BD.");
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					System.out.println("Error de cierre del ResultSet");
				}
			}
		}
	
		return p;
	}

	@Override
	public ArrayList<News2> showNews() {
		con = DatabaseConnectionNews2.getConnection();
		ResultSet rs = null;
		News2 n= null;
		ArrayList<News2> news = new ArrayList<News2>();

		
		
		try {
			stmt = con.prepareStatement(SHOW_NEWS);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				n = new News2();
				n.setId_noticia(rs.getInt("id_noticia"));
				n.setFoto_noticia(rs.getBlob("fotografia_noticia"));
				n.setTitulo(rs.getString("titulo"));
				n.setDescripcion(rs.getString("descripcion"));
				n.setDni_administrador(rs.getString("dni"));
				news.add(n);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return news;
		
	}

	@Override
	public ArrayList<Policia> showPolicemen() {
		
		con = DatabaseConnectionPolice2.getConnection();
		ResultSet rs = null;
		Policia p= null;
		ArrayList<Policia> policemen = new ArrayList<Policia>();

		
		
		try {
			stmt = con.prepareStatement(SHOW_POLICEMEN);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				p = new Policia(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("contrasena"),rs.getBlob("fotografia_persona"),rs.getString("rango"));
				policemen.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return policemen;
		
	}

	@Override
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia) {
		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Criminal c = null;
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();
		
		try {
			
			stmt = con.prepareStatement(SHOW_CRIMINAL_BY_POLICEMAN);
			stmt.setString(1, dni_policia);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				c = new Criminal(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("contrasena"),rs.getBlob("fotografia_persona"),rs.getString("descripcion"),rs.getString("dni_policia"));
				criminals.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return criminals;
	}
}

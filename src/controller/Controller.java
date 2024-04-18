package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.mysql.cj.jdbc.CallableStatement;

import model.Administrador;
import model.Arsenal;
import model.Criminal;
import model.Elige;
import model.News;
import model.Policia;

public class Controller implements InterfaceController{
	
	private Connection con;
	private PreparedStatement stmt;
	
	private final String SHOW_NEWS = "SELECT * FROM NOTICIA";
	private final String RETURN_POLICEMAN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia WHERE contrasena = ? AND dni in (SELECT dni_policia from policia WHERE dni_policia = ?);";
	private final String SHOW_POLICEMEN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia";
	private final String SHOW_CRIMINAL_BY_POLICEMAN = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni WHERE dni_policia = ?";
	private final String SHOW_ARSENAL = "SELECT * FROM ARSENAL";
	private final String DELETE_PEOPLE = "DELETE FROM PERSONA WHERE dni=?";
	private final String RETURN_ADMIN = "SELECT persona.dni,nombre,apellido,contrasena,fotografia_persona,fecha_primerLog,fecha_ultimoLog FROM persona join administrador on persona.dni = administrador.dni WHERE contrasena = ? AND persona.dni in (SELECT dni from administrador WHERE dni = ?);";
	private final String RETURN_CHOICE = "SELECT * FROM elige WHERE dni_policia = ?";
	private final String SHOW_CRIMINAL ="SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni";
	
	@Override
	public Policia policeLogIn(String password, String dni) {
		
		con = DatabaseConnectionPolice.getConnection();
		
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
	public ArrayList<News> showNews() {
		con = DatabaseConnectionNews.getConnection();
		ResultSet rs = null;
		News n= null;
		ArrayList<News> news = new ArrayList<News>();

		
		
		try {
			stmt = con.prepareStatement(SHOW_NEWS);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				n = new News();
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
		
		con = DatabaseConnectionPolice.getConnection();
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

	@Override
	public ArrayList<Arsenal> showArsenal() {
		
		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Arsenal a= null;
		ArrayList<Arsenal> weapons = new ArrayList<Arsenal>();

		
		
		try {
			stmt = con.prepareStatement(SHOW_ARSENAL);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				a = new Arsenal();
				a.setId_arsenal(rs.getInt("id_arsenal"));
				a.setFoto_arsenal(rs.getBlob("fotografia_arsenal"));
				a.setNombre(rs.getString("nombre"));
				a.setTipo(rs.getString("tipo"));
				a.setDescripcion(rs.getString("descripcion"));
				weapons.add(a);
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

		return weapons;
	}

	@Override
	public ArrayList<Elige> weaponsAssigned(String dni_policia) {
		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Elige e = null;
		ArrayList<Elige> search = new ArrayList<Elige>();
		
		try {
			
			stmt = con.prepareStatement(RETURN_CHOICE);
			stmt.setString(1, dni_policia);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				e = new Elige();
				e.setDni_policia(rs.getString("dni_policia"));
				e.setId_arsenal(rs.getInt("id_arsenal"));
				search.add(e);
			}
		} catch (SQLException e2) {
			System.out.println("Error de SQL");
			e2.printStackTrace();
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
		return search;
	}

	@Override
	public boolean deletePoliceman(String dni) {
		
		boolean cambios = false;
		
		con = DatabaseConnectionPolice.getConnection();
		

		try {
			stmt = con.prepareStatement(DELETE_PEOPLE);

			stmt.setString(1, dni);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} 

		return cambios;
	}

	@Override
	public Administrador adminLogIn(String password, String dni) {
		con = DatabaseConnectionAdmin.getConnection();
		
		ResultSet rs = null;
		Administrador admin= null;
		
		try {
			
			stmt = con.prepareStatement(RETURN_ADMIN);
			stmt.setString(1, password);
			stmt.setString(2, dni);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				admin = new Administrador(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("contrasena"),rs.getBlob("fotografia_persona"),rs.getDate("fecha_primerLog"),rs.getDate("fecha_ultimoLog"));
			
				
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
	
		return admin;
	}

	@Override
	public ArrayList<Criminal> showCriminals() {
		con = DatabaseConnectionAdmin.getConnection();
		ResultSet rs = null;
		Criminal c= null;
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();

		
		
		try {
			stmt = con.prepareStatement(SHOW_CRIMINAL);

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

	@Override
	public boolean deleteWeapon(int id_weapon) {
		boolean cambios = false;
		
		con = DatabaseConnectionAdmin.getConnection();
		

		try {
			CallableStatement cs = (CallableStatement) this.con.prepareCall("{CALL BorrarArsenal(?)}");

			cs.setInt(1, id_weapon);

			if (cs.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} 

		return cambios;
	}
}

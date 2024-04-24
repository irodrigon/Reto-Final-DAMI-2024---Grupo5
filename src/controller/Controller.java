package controller;

import java.sql.Blob;
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

public class Controller implements InterfaceController {

	// La conexión a la base de datos.
	private Connection con;

	// Sirve para gestionar las sentencias SQL.
	private PreparedStatement stmt;

	// Sentencias SQL utilizadas en el programa.

	private final String SHOW_NEWS = "SELECT * FROM NOTICIA";
	private final String RETURN_POLICEMAN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia WHERE contrasena = ? AND dni in (SELECT dni_policia from policia WHERE dni_policia = ?)";
	private final String SHOW_POLICEMEN = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia";
	private final String SHOW_CRIMINAL_BY_POLICEMAN = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni WHERE dni_policia = ?";
	private final String SHOW_ARSENAL = "SELECT * FROM ARSENAL";
	private final String DELETE_PEOPLE = "DELETE FROM PERSONA WHERE dni=?";
	private final String RETURN_ADMIN = "SELECT persona.dni,nombre,apellido,contrasena,fotografia_persona,fecha_primerLog,fecha_ultimoLog FROM persona join administrador on persona.dni = administrador.dni WHERE contrasena = ? AND persona.dni in (SELECT dni from administrador WHERE dni = ?);";
	private final String RETURN_CHOICE = "SELECT * FROM elige WHERE dni_policia = ?";
	private final String SHOW_CRIMINAL = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni";
	private final String INSERT_WEAPON = "{CALL AnadirArsenal(?,?,?,?,?};";
	private final String RETURN_WEAPON_BY_NAME = "SELECT * FROM ARSENAL WHERE nombre = ?";
	private final String RETURN_POLICEMAN_BY_ID = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia WHERE dni in (SELECT dni_policia from policia WHERE dni_policia = ?)";
	private final String INSERT_ASSOCIATION = "INSERT INTO ELIGE VALUES(?,?)";
	private final String SHOW_ASSOCIATION = "SELECT * FROM ELIGE";
	private final String RETURN_CRIMINAL_BY_ID = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni WHERE criminal.dni = ?";
	private final String INSERT_POLICEMAN = "INSERT INTO POLICIA VALUES(?,?)";
	private final String INSERT_PEOPLE = "INSERT INTO PERSONA VALUES(?,?,?,?,?)";
	private final String UPDATE_DEFAULT_CRIMINAL = "UPDATE CRIMINAL SET dni_policia = ? WHERE dni = ?";
	private final String SELECT_RANDOM_CRIMINAL = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni ORDER BY RAND() LIMIT 1";
	private final String UPDATE_PEOPLE ="UPDATE PERSONA SET nombre = ?, apellido = ?, contrasena = ?, fotografia_persona = ? WHERE dni = ?";		
	private final String UPDATE_POLICEMAN = "UPDATE POLICIA SET rango = ? WHERE dni = ?";		
	private final String RETURN_NEWS = "SELECT * FROM NOTICIA WHERE titulo = ?";
	private final String DELETE_POLICEMAN2 = "DELETE FROM POLICIA WHERE dni_policia = ?";
	private final String DELETE_CRIMINAL = "DELETE FROM CRIMINAL WHERE dni = ?";
	private final String DELETE_NEW = "DELETE FROM NOTICIA WHERE id_noticia = ?";
	
	public Policia policeLogIn(String password, String dni) {

		con = DatabaseConnectionPolice.getConnection();

		// El set de resultados recoge la consulta de la base de datos.
		ResultSet rs = null;
		Policia p = null;

		try {

			// prepara la conexión con la base datos.

			stmt = con.prepareStatement(RETURN_POLICEMAN);
			// Mira el primer parámetro que le introduce el usuario en la base de datos.
			stmt.setString(1, password);
			// El número dos indica que se trata del segundo parámetro.
			stmt.setString(2, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Policia(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("rango"));
			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Error de cierre del ResultSet");
				}
			}
		}

		return p;
	}

	@Override
	public ArrayList<News> showNews() {

		// Cada método lleva asociada una conexión distinta a un usuario diferente de la
		// base de datos.

		con = DatabaseConnectionNews.getConnection();
		ResultSet rs = null;
		News n = null;
		ArrayList<News> news = new ArrayList<News>();

		try {
			stmt = con.prepareStatement(SHOW_NEWS);

			rs = stmt.executeQuery();

			while (rs.next()) {

				n = new News();

				// Crea un objeto News para recoger el set de resultados en sus atributos.
				n = new News();
				// Recoge el primer atributo según el atributo correspondiente en la base de
				// datos.

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

		// En este caso, usa el usuario policia.

		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Policia p = null;
		ArrayList<Policia> policemen = new ArrayList<Policia>();

		try {
			stmt = con.prepareStatement(SHOW_POLICEMEN);

			rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Policia(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("rango"));
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

			while (rs.next()) {
				c = new Criminal(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("descripcion"),
						rs.getString("dni_policia"));
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
		Arsenal a = null;
		ArrayList<Arsenal> weapons = new ArrayList<Arsenal>();

		try {
			stmt = con.prepareStatement(SHOW_ARSENAL);

			rs = stmt.executeQuery();

			while (rs.next()) {
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

			while (rs.next()) {
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
		Administrador admin = null;

		try {

			stmt = con.prepareStatement(RETURN_ADMIN);
			stmt.setString(1, password);
			stmt.setString(2, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {

				admin = new Administrador(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getDate("fecha_primerLog"),
						rs.getDate("fecha_ultimoLog"));

			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
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
		Criminal c = null;
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();

		try {
			stmt = con.prepareStatement(SHOW_CRIMINAL);

			rs = stmt.executeQuery();

			while (rs.next()) {
				c = new Criminal(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("descripcion"),
						rs.getString("dni_policia"));
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

	@Override
	public boolean insertWeapon(int id, Blob foto, String nombre, String tipo, String descripcion) {

		boolean cambios = false;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			CallableStatement cs = (CallableStatement) this.con.prepareCall(INSERT_WEAPON);

			cs.setInt(1, id);
			cs.setBlob(2, foto);
			cs.setString(3, nombre);
			cs.setString(4, tipo);
			cs.setString(5, descripcion);

			if (cs.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public Arsenal returnWeaponByName(String nombre_arsenal) {

		ResultSet rs = null;
		Arsenal a = null;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			stmt = con.prepareStatement(RETURN_WEAPON_BY_NAME);

			// Cargamos los parámetros
			stmt.setString(1, nombre_arsenal);

			rs = stmt.executeQuery();

			if (rs.next()) {
				a = new Arsenal();
				a.setId_arsenal(rs.getInt("id_arsenal"));
				a.setFoto_arsenal(rs.getBlob("fotografia_arsenal"));
				a.setNombre(rs.getString("nombre"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setTipo(rs.getString("tipo"));
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
		return a;

	}

	@Override
	public Policia returnPolicemanById(String dni) {
		// El set de resultados recoge la consulta de la base de datos.
		ResultSet rs = null;
		Policia p = null;
		con = DatabaseConnectionAdmin.getConnection();

		try {

			// prepara la conexión con la base datos.

			stmt = con.prepareStatement(RETURN_POLICEMAN_BY_ID);
			// Mira el primer parámetro que le introduce el usuario en la base de datos.
			stmt.setString(1, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Policia(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("rango"));
			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");

		}
		return p;
	}

	@Override

	public boolean insertAssociation(String dni, int id) {

		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(INSERT_ASSOCIATION);

			stmt.setString(1, dni);
			stmt.setInt(2, id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public ArrayList<Elige> showAssociations() {

		// Cada método lleva asociada una conexión distinta a un usuario diferente de la
		// base de datos.

		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Elige el = null;
		ArrayList<Elige> busquedas = new ArrayList<Elige>();

		try {
			stmt = con.prepareStatement(SHOW_ASSOCIATION);

			rs = stmt.executeQuery();

			while (rs.next()) {

				el = new Elige();
				el.setDni_policia(rs.getString("dni_policia"));
				el.setId_arsenal(rs.getInt("id_arsenal"));
				busquedas.add(el);
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

		return busquedas;

	}

	public Criminal showCriminalByPolicemanAdmin(String dni_policia) {
		con = DatabaseConnectionPolice.getConnection();
		ResultSet rs = null;
		Criminal cr = null;

		try {

			stmt = con.prepareStatement(RETURN_CRIMINAL_BY_ID);
			stmt.setString(1, dni_policia);

			rs = stmt.executeQuery();

			if (rs.next()) {
				cr = new Criminal(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("descripcion"),
						rs.getString("dni_policia"));

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
		return cr;
	}

	@Override
	public boolean insertPeople(String dni, String nombre, String apellido, String contrasena,
			Blob fotografia_persona) {
		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(INSERT_PEOPLE);

			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, apellido);
			stmt.setString(4, contrasena);
			stmt.setBlob(5, fotografia_persona);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public boolean insertPoliceman(String dni, String rango) {
		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(INSERT_POLICEMAN);

			stmt.setString(1, dni);
			stmt.setString(2, rango);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public boolean updateDefaultCriminal(String dni_policia, String dni) {
		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(UPDATE_DEFAULT_CRIMINAL);

			stmt.setString(1, dni_policia);
			stmt.setString(2, dni);
			
			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public Criminal selectRandomCriminal() {
		con = DatabaseConnectionAdmin.getConnection();
		ResultSet rs = null;
		Criminal c = null;

		try {
			stmt = con.prepareStatement(SELECT_RANDOM_CRIMINAL);

			rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Criminal(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("contrasena"), rs.getBlob("fotografia_persona"), rs.getString("descripcion"),
						rs.getString("dni_policia"));
				
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

		return c;
	}

	@Override
	public boolean updatePeople(String nombre, String apellido, String contrasena, Blob fotografia_persona,
			String dni) {
		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(UPDATE_PEOPLE);

			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, contrasena);
			stmt.setBlob(4, fotografia_persona);
			stmt.setString(5, dni);
			
			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public boolean updatePoliceman(String rango,String dni) {
		boolean cambios = false;

		con = DatabaseConnectionPolice.getConnection();

		try {
			stmt = con.prepareStatement(UPDATE_POLICEMAN);

			stmt.setString(1, rango);
			stmt.setString(2, dni);
			
			
			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public News returnNews(String titulo) {
		ResultSet rs = null;
		News n= null;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			stmt = con.prepareStatement(RETURN_NEWS);

			// Cargamos los parámetros
			stmt.setString(1, titulo);

			rs = stmt.executeQuery();

			if (rs.next()) {
				n = new News();
				n.setId_noticia(rs.getInt("id_noticia"));
				n.setFoto_noticia(rs.getBlob("fotografia_noticia"));
				n.setTitulo(rs.getString("Titulo"));
				n.setDescripcion(rs.getString("descripcion"));
				n.setDni_administrador(rs.getString("dni"));
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
		return n;

	}

	@Override
	public boolean deletePoliceman2(String dni) {
		boolean cambios = false;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			stmt = con.prepareStatement(DELETE_POLICEMAN2);

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
	public boolean deleteCriminal(String dni) {
		boolean cambios = false;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			stmt = con.prepareStatement(DELETE_CRIMINAL);

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
	public boolean deleteNew(int id) {
		boolean cambios = false;

		con = DatabaseConnectionAdmin.getConnection();

		try {
			stmt = con.prepareStatement(DELETE_NEW);

			stmt.setInt(1,id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

}

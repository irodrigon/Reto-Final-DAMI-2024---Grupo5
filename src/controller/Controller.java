package controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.cj.jdbc.CallableStatement;
import model.Administrador;
import model.Arsenal;
import model.Criminal;
import model.Elige;
import model.News;
import model.Policia;

public class Controller implements InterfaceController {

	// La conexión a la base de datos.
	private Connection conexion;

	// Sirve para gestionar las sentencias SQL.
	private PreparedStatement sentenciaSQL;

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
	private final String RETURN_WEAPON_BY_NAME = "SELECT * FROM ARSENAL WHERE nombre = ?";
	private final String RETURN_POLICEMAN_BY_ID = "SELECT dni,nombre,apellido,contrasena,fotografia_persona,rango FROM persona join policia on persona.dni = policia.dni_policia WHERE dni in (SELECT dni_policia from policia WHERE dni_policia = ?)";
	private final String INSERT_ASSOCIATION = "INSERT INTO ELIGE VALUES(?,?)";
	private final String SHOW_ASSOCIATION = "SELECT * FROM ELIGE";
	private final String RETURN_CRIMINAL_BY_ID = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni WHERE criminal.dni = ?";
	private final String INSERT_POLICEMAN = "INSERT INTO POLICIA VALUES(?,?)";
	private final String INSERT_PEOPLE = "INSERT INTO PERSONA VALUES(?,?,?,?,?)";
	private final String UPDATE_DEFAULT_CRIMINAL = "UPDATE CRIMINAL SET dni_policia = ? WHERE dni = ?";
	private final String SELECT_RANDOM_CRIMINAL = "SELECT criminal.dni,nombre,apellido,contrasena,fotografia_persona,descripcion,dni_policia FROM persona join criminal on persona.dni = criminal.dni ORDER BY RAND() LIMIT 1";
	private final String UPDATE_PEOPLE = "UPDATE PERSONA SET nombre = ?, apellido = ?, contrasena = ?, fotografia_persona = ? WHERE dni = ?";
	private final String UPDATE_POLICEMAN = "UPDATE POLICIA SET rango = ? WHERE dni = ?";
	private final String RETURN_NEWS = "SELECT * FROM NOTICIA WHERE titulo = ?";
	private final String DELETE_POLICEMAN2 = "DELETE FROM POLICIA WHERE dni_policia = ?";
	private final String DELETE_CRIMINAL = "DELETE FROM CRIMINAL WHERE dni = ?";
	private final String DELETE_NEW = "DELETE FROM NOTICIA WHERE id_noticia = ?";
	private final String RETURN_MAX_WEAPON = "SELECT * FROM ARSENAL WHERE ID_arsenal = (SELECT MAX(ID_arsenal) FROM Arsenal)";
	private final String INSERT_CRIMINAL = "INSERT INTO CRIMINAL VALUES(?,?,?)";
	private final String INSERT_NEW = "INSERT INTO NOTICIA VALUES(?,?,?,?,?)";
	private final String RETURN_MAX_NEW = "SELECT * FROM NOTICIA WHERE ID_noticia = (SELECT MAX(ID_noticia) FROM NOTICIA)";
	private final String UPDATE_NEW = "UPDATE NOTICIA SET fotografia_noticia = ?, titulo = ?, descripcion = ?  WHERE titulo = ?";
	private final String UPDATE_ARSENAL = "UPDATE ARSENAL SET fotografia_arsenal =?, nombre = ?, tipo = ?, descripcion = ? WHERE id_arsenal = ?";
	private final String UPDATE_CRIMINAL = "UPDATE CRIMINAL SET DESCRIPCION = ? WHERE DNI = ?";
	private final String SELECTING_NEWS = "{CALL SelectNoticia(?)}";
	
	/*
	 * Este método inicia una conexión con la base de datos como Policía lanza una sentencia SELECT, 
	 * crea un set de resultados y devuelve un objeto Policia usando el set de resultados
	 * para rellenar los datos. Sirve para comprobar que el usuario no existe en la ventana de entrada.
	 * 
	 * @param1 String password
	 * @param2 String dni
	 * 
	 * @return Policia policia
	 */
	
	@Override
	public Policia policeLogIn(String password, String dni) {

		conexion = DatabaseConnectionPolice.getConnection();

		// El set de resultados recoge la consulta de la base de datos.
		ResultSet resultSet = null;
		Policia policia = null;

		try {

			// prepara la conexión con la base datos.

			sentenciaSQL = conexion.prepareStatement(RETURN_POLICEMAN);
			// Mira el primer parámetro que le introduce el usuario en la base de datos.
			sentenciaSQL.setString(1, password);
			// El número dos indica que se trata del segundo parámetro.
			sentenciaSQL.setString(2, dni);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				policia = new Policia(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("rango"));
			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println("Error de cierre del ResultSet");
				}
			}
		}

		return policia;
	}
	
	/*
	 *Este método devuelve un ArrayList como Usuario news sacando los datos a un resultSet
	 *con la sentencia SELECT * de la tabla NOTICIA y pasando los datos a un ArrayList de objetos Noticia.
	 *Sirve para llenar la tabla de noticias de la ventana de gestión del usuario administrador.
	 *
	 * @return ArrayList<News> noticias
	 */
	
	@Override
	public ArrayList<News> showNews() {

		// Cada método lleva asociada una conexión distinta a un usuario diferente de la
		// base de datos.

		conexion = DatabaseConnectionNews.getConnection();
		ResultSet resultSet= null;
		News noticia = null;
		ArrayList<News> news = new ArrayList<News>();

		try {
			sentenciaSQL = conexion.prepareStatement(SHOW_NEWS);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {

				noticia = new News();

				// Crea un objeto News para recoger el set de resultados en sus atributos.
				noticia = new News();
				// Recoge el primer atributo según el atributo correspondiente en la base de
				// datos.

				noticia.setId_noticia(resultSet.getInt("id_noticia"));
				noticia.setFoto_noticia(resultSet.getBlob("fotografia_noticia"));
				noticia.setTitulo(resultSet.getString("titulo"));
				noticia.setDescripcion(resultSet.getString("descripcion"));
				noticia.setDni_administrador(resultSet.getString("dni"));
				news.add(noticia);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return news;

	}
	
	
	/*
	 * Este método crea un ArrayList de objetos Policia y lo rellena con un set de resultados
	 * y un bucle while. Sirve para llenar la tabla de datos de policias de la ventana de gestión
	 * del usuario administrador.
	 * 
	 * @return ArrayList<Policia> policias
	 */
	
	@Override
	public ArrayList<Policia> showPolicemen() {

		// En este caso, usa el usuario policia.

		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Policia policia = null;
		ArrayList<Policia> policemen = new ArrayList<Policia>();

		try {
			sentenciaSQL = conexion.prepareStatement(SHOW_POLICEMEN);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {
				policia = new Policia(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("rango"));
				policemen.add(policia);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return policemen;

	}
	
	
	/*
	 * Este método muestra los criminales asociados a un policía. Sirve
	 * para mostrar los criminales en la ventana del usuario policía.
	 * 
	 * @param1 String dni_policia
	 * 
	 * @return ArrayList<Criminal> criminales
	 */
	
	@Override
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia) {
		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Criminal criminal = null;
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();

		try {

			sentenciaSQL = conexion.prepareStatement(SHOW_CRIMINAL_BY_POLICEMAN);
			sentenciaSQL.setString(1, dni_policia);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {
				criminal = new Criminal(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("descripcion"),
						resultSet.getString("dni_policia"));
				criminals.add(criminal);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return criminals;
	}
	
	
	/*
	 * Este método devuelve un Arraylist de objetos Arsenal. Sirve para rellenar los datos de la tabla
	 * de arsenal en la ventana de gestión del administrador. Usa resultSet.
	 * 
	 * @return ArrayList<Arsenal> weapons 
	 * 
	 */
	
	@Override
	public ArrayList<Arsenal> showArsenal() {

		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Arsenal arsenal = null;
		ArrayList<Arsenal> weapons = new ArrayList<Arsenal>();

		try {
			sentenciaSQL = conexion.prepareStatement(SHOW_ARSENAL);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {
				arsenal = new Arsenal();
				arsenal.setId_arsenal(resultSet.getInt("id_arsenal"));
				arsenal.setFoto_arsenal(resultSet.getBlob("fotografia_arsenal"));
				arsenal.setNombre(resultSet.getString("nombre"));
				arsenal.setTipo(resultSet.getString("tipo"));
				arsenal.setDescripcion(resultSet.getString("descripcion"));
				weapons.add(arsenal);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return weapons;
	}
	
	/*
	 * Este método devuelve una lista de los datos de la tabla ELIGE de la base de datos
	 * con respecto a una de sus columnas, DNI del policía, en un objeto de tipo Elige.
	 * SQL. Sirve para añadir las armas en el perfil de los policías y mostrarlas en su ventana.
	 * 
	 * @param1 String dni_policia
	 * 
	 * @return ArrayList<Elige> elegir
	 */
	
	@Override
	public ArrayList<Elige> weaponsAssigned(String dni_policia) {
		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Elige elegir = null;
		ArrayList<Elige> search = new ArrayList<Elige>();

		try {

			sentenciaSQL = conexion.prepareStatement(RETURN_CHOICE);
			sentenciaSQL.setString(1, dni_policia);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {
				elegir = new Elige();
				elegir.setDni_policia(resultSet.getString("dni_policia"));
				elegir.setId_arsenal(resultSet.getInt("id_arsenal"));
				search.add(elegir);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return search;
	}

	/*
	 * Este método borra una entrada de la Tabla Policías con DELETE.
	 * Sirve para eliminar el perfil de policía en la aplicación.
	 * 
	 * @param1 String dni
	 * 
	 * @return boolean cambios
	 */
	
	@Override
	public boolean deletePoliceman(String dni) {

		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(DELETE_PEOPLE);

			sentenciaSQL.setString(1, dni);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}
	
	/*
	 * Este método se emplea para identificar si el usuario es el administrador.
	 * Lo comprueba con un SELECT en la tabla PERSONA de SQL.
	 * Sirve para que el administrador pueda entrar en la aplicación dentro de la ventana de entrada.
	 * 
	 * @param1 String password
	 * @param2 String dni
	 * 
	 * @return Administrador admin
	 * 
	 */
	
	@Override
	public Administrador adminLogIn(String password, String dni) {
		conexion = DatabaseConnectionAdmin.getConnection();

		ResultSet resultSet = null;
		Administrador admin = null;

		try {

			sentenciaSQL = conexion.prepareStatement(RETURN_ADMIN);
			sentenciaSQL.setString(1, password);
			sentenciaSQL.setString(2, dni);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {

				admin = new Administrador(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getDate("fecha_primerLog"),
						resultSet.getDate("fecha_ultimoLog"));

			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println("Error de cierre del ResultSet");
				}
			}
		}

		return admin;
	}
	
	/*
	 * Este método retorna una lista con los datos de los criminales en objetos 
	 * de tipo Criminal. Sirve para llenar la tabla de Criminales 
	 * al cargar la ventana de gestión del administrador.
	 * 
	 * @return ArrayList<Criminal> criminals
	 */
	
	@Override
	public ArrayList<Criminal> showCriminals() {
		conexion = DatabaseConnectionAdmin.getConnection();
		ResultSet resultSet = null;
		Criminal criminal = null;
		ArrayList<Criminal> criminals = new ArrayList<Criminal>();

		try {
			sentenciaSQL = conexion.prepareStatement(SHOW_CRIMINAL);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {
				criminal = new Criminal(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("descripcion"),
						resultSet.getString("dni_policia"));
				criminals.add(criminal);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return criminals;
	}
	
	/*
	 * Este método devuelve un valor verdadero o falso si la sentencia SQL de borrar a la
	 * que va asociado se ejecuta correctamente. Sólo borra una entrada en la tabla de la base
	 * de datos SQL. Sirve para eliminar arsenal en la ventana de gestión del administrador.
	 * 
	 * @param1 int id_weapon
	 * 
	 * @return boolean cambios
	 */
	
	@Override
	public boolean deleteWeapon(int id_weapon) {
		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			// Llamamos a un procedimiento almacenado en la base de datos.
			CallableStatement llamadaProcedimiento = (CallableStatement) this.conexion.prepareCall("{CALL BorrarArsenal(?)}");

			llamadaProcedimiento.setInt(1, id_weapon);

			if (llamadaProcedimiento.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}
	
	
	/*
	 * Este método crea una entrada en la tabla ARSENAL de la base de datos y
	 * devuelve un valor booleano si la ejecuta correctamente. Sirve para añadir
	 * arsenal en la ventana de gestión del administrador.
	 * 
	 * @param1 int id
	 * @param2 Blob foto
	 * @param3 String nombre
	 * @param4 String tipo
	 * @param5 String descripcion
	 * 
	 * @return boolean cambios
	 */
	
	@Override
	public boolean insertWeapon(int id, Blob foto, String nombre, String tipo, String descripcion) {

		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			// Llamamos a un procedimiento.
			CallableStatement llamadaProcedimiento = (CallableStatement) this.conexion.prepareCall("{CALL AnadirArsenal(?, ?, ?, ?, ?)}");

			llamadaProcedimiento.setInt(1, id);
			llamadaProcedimiento.setBlob(2, foto);
			llamadaProcedimiento.setString(3, nombre);
			llamadaProcedimiento.setString(4, tipo);
			llamadaProcedimiento.setString(5, descripcion);

			if (llamadaProcedimiento.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}
	
	/*
	 * Este método retorna un objeto de tipo Arsenal con todos los datos de la tabla
	 * Arsenal de SQL. Sirve para mostrar los perfiles de arsenal 
	 * en la ventana de gestión del administrador, al pulsar el botón de "ver arsenal". 
	 * 
	 * @param1 String nombre_arsenal
	 * 
	 * @return Arsenal arsenal
	 * 
	 */
	
	@Override
	public Arsenal returnWeaponByName(String nombre_arsenal) {

		ResultSet resultSet = null;
		Arsenal arsenal = null;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(RETURN_WEAPON_BY_NAME);

			// Cargamos los parámetros
			sentenciaSQL.setString(1, nombre_arsenal);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				arsenal = new Arsenal();
				arsenal.setId_arsenal(resultSet.getInt("id_arsenal"));
				arsenal.setFoto_arsenal(resultSet.getBlob("fotografia_arsenal"));
				arsenal.setNombre(resultSet.getString("nombre"));
				arsenal.setDescripcion(resultSet.getString("descripcion"));
				arsenal.setTipo(resultSet.getString("tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return arsenal;

	}
	
	/*
	 * Este método retorna un objeto de tipo Policia con los datos de 
	 * las tablas POLICIA Y PERSONA. Sirve para ver los datos de los policías
	 * en la ventana de gestión del administrador, al pulsar el
	 * botón "ver perfil de policía"
	 * 
	 * @param1 String dni
	 * 
	 * @return Policia policia
	 */
	@Override
	public Policia returnPolicemanById(String dni) {
		// El set de resultados recoge la consulta de la base de datos.
		ResultSet resultSet = null;
		Policia policia = null;
		conexion = DatabaseConnectionAdmin.getConnection();

		try {

			// prepara la conexión con la base datos.

			sentenciaSQL = conexion.prepareStatement(RETURN_POLICEMAN_BY_ID);
			// Mira el primer parámetro que le introduce el usuario en la base de datos.
			sentenciaSQL.setString(1, dni);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				policia = new Policia(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("rango"));
			}

		} catch (SQLException e) {
			System.out.println("Error en la BD.");

		}
		return policia;
	}
	
	/*
	 * Este método crea una entrada en la tabla ELIGE de la base de datos SQL.
	 * Retorna verdadero si la sentencia se ejecuta bien.
	 * Sirve para crear las asociaciones en el perfil de policías,
	 * al pulsar el botón "Ver arsenal".
	 * 
	 * @param1 String dni
	 * @param2 int id
	 * 
	 * @return boolean cambios
	 */
	
	@Override
	public boolean insertAssociation(String dni, int id) {

		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(INSERT_ASSOCIATION);

			sentenciaSQL.setString(1, dni);
			sentenciaSQL.setInt(2, id);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}
	
	
	/*
	 * Este método devuelve una lista de los datos de la tabla ELIGE de SQl, los datos de ´
	 * las asociaciones entre policias y artículos de arsenal. Sirve para enlazar un policía
	 * con el arsenal que posee, en la ventana de perfil de policías, al pulsar el botón
	 * "ver arsenal".
	 * 
	 *  @return ArrayList<Elige> busquedas
	 */
	@Override
	public ArrayList<Elige> showAssociations() {

		// Cada método lleva asociada una conexión distinta a un usuario diferente de la
		// base de datos.

		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Elige elegir = null;
		ArrayList<Elige> busquedas = new ArrayList<Elige>();

		try {
			sentenciaSQL = conexion.prepareStatement(SHOW_ASSOCIATION);

			resultSet = sentenciaSQL.executeQuery();

			while (resultSet.next()) {

				elegir = new Elige();
				elegir.setDni_policia(resultSet.getString("dni_policia"));
				elegir.setId_arsenal(resultSet.getInt("id_arsenal"));
				busquedas.add(elegir);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return busquedas;

	}
	
	/*
	 * Este método devuelve un objeto Criminal con los datos de las tablas PERSONA
	 * y CRIMINAL. Sirve para mostrar los criminales en la ventana de perfil de
	 * los usuarios policías..
	 * 
	 * @param1 dni_policia
	 * 
	 * @return Criminal criminal
	 */
	public Criminal showCriminalByPolicemanAdmin(String dni_policia) {
		conexion = DatabaseConnectionPolice.getConnection();
		ResultSet resultSet = null;
		Criminal criminal = null;

		try {

			sentenciaSQL = conexion.prepareStatement(RETURN_CRIMINAL_BY_ID);
			sentenciaSQL.setString(1, dni_policia);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				criminal = new Criminal(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("descripcion"),
						resultSet.getString("dni_policia"));

			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return criminal;
	}

	@Override
	public boolean insertPeople(String dni, String nombre, String apellido, String contrasena,
			Blob fotografia_persona) {
		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(INSERT_PEOPLE);

			sentenciaSQL.setString(1, dni);
			sentenciaSQL.setString(2, nombre);
			sentenciaSQL.setString(3, apellido);
			sentenciaSQL.setString(4, contrasena);
			sentenciaSQL.setBlob(5, fotografia_persona);

			if (sentenciaSQL.executeUpdate() == 1)
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

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(INSERT_POLICEMAN);

			sentenciaSQL.setString(1, dni);
			sentenciaSQL.setString(2, rango);

			if (sentenciaSQL.executeUpdate() == 1)
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

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_DEFAULT_CRIMINAL);

			sentenciaSQL.setString(1, dni_policia);
			sentenciaSQL.setString(2, dni);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public Criminal selectRandomCriminal() {
		conexion = DatabaseConnectionAdmin.getConnection();
		ResultSet resultSet = null;
		Criminal criminal = null;

		try {
			sentenciaSQL = conexion.prepareStatement(SELECT_RANDOM_CRIMINAL);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				criminal = new Criminal(resultSet.getString("dni"), resultSet.getString("nombre"), resultSet.getString("apellido"),
						resultSet.getString("contrasena"), resultSet.getBlob("fotografia_persona"), resultSet.getString("descripcion"),
						resultSet.getString("dni_policia"));

			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}

		return criminal;
	}

	@Override
	public boolean updatePeople(String nombre, String apellido, String contrasena, Blob fotografia_persona,
			String dni) {
		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_PEOPLE);

			sentenciaSQL.setString(1, nombre);
			sentenciaSQL.setString(2, apellido);
			sentenciaSQL.setString(3, contrasena);
			sentenciaSQL.setBlob(4, fotografia_persona);
			sentenciaSQL.setString(5, dni);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public boolean updatePoliceman(String rango, String dni) {
		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_POLICEMAN);

			sentenciaSQL.setString(1, rango);
			sentenciaSQL.setString(2, dni);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public News returnNews(String titulo) {
		ResultSet resultSet = null;
		News noticia = null;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(RETURN_NEWS);

			// Cargamos los parámetros
			sentenciaSQL.setString(1, titulo);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				noticia = new News();
				noticia.setId_noticia(resultSet.getInt("id_noticia"));
				noticia.setFoto_noticia(resultSet.getBlob("fotografia_noticia"));
				noticia.setTitulo(resultSet.getString("Titulo"));
				noticia.setDescripcion(resultSet.getString("descripcion"));
				noticia.setDni_administrador(resultSet.getString("dni"));
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return noticia;

	}

	@Override
	public boolean deletePoliceman2(String dni) {
		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(DELETE_POLICEMAN2);

			sentenciaSQL.setString(1, dni);

			if (sentenciaSQL.executeUpdate() == 1)
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

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(DELETE_CRIMINAL);

			sentenciaSQL.setString(1, dni);

			if (sentenciaSQL.executeUpdate() == 1)
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

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(DELETE_NEW);

			sentenciaSQL.setInt(1, id);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public Arsenal returnMaxWeapon() {
		ResultSet resultSet = null;
		Arsenal arsenal = null;

		// Abrimos la conexión
		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(RETURN_MAX_WEAPON);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				arsenal = new Arsenal();
				arsenal.setId_arsenal(resultSet.getInt("id_arsenal"));
				arsenal.setFoto_arsenal(resultSet.getBlob("fotografia_arsenal"));
				arsenal.setNombre(resultSet.getString("nombre"));
				arsenal.setTipo(resultSet.getString("tipo"));
				arsenal.setDescripcion(resultSet.getString("descripcion"));

			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return arsenal;
	}

	@Override
	public boolean insertCriminal(String dni, String descripcion, String dni_policia) {
		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(INSERT_CRIMINAL);
			sentenciaSQL.setString(1, dni);
			sentenciaSQL.setString(2, descripcion);
			sentenciaSQL.setString(3, dni_policia);

			if (sentenciaSQL.executeUpdate() == 1) {
				cambios = true;
			}
		} catch (Exception e) {
			System.out.println("Error SQL");
		}

		return cambios;
	}

	@Override
	public boolean insertNew(int id_noticia, Blob fotografia_noticia, String titulo, String descripcion, String dni) {
		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(INSERT_NEW);
			sentenciaSQL.setInt(1, id_noticia);
			sentenciaSQL.setBlob(2, fotografia_noticia);
			sentenciaSQL.setString(3, titulo);
			sentenciaSQL.setString(4, descripcion);
			sentenciaSQL.setString(5, dni);

			if (sentenciaSQL.executeUpdate() == 1) {
				cambios = true;
			}
		} catch (Exception e) {
			System.out.println("Error SQL");
		}

		return cambios;
	}

	@Override
	public News returnMaxNews() {
		ResultSet resultSet = null;
		News noticia = null;

		// Abrimos la conexión
		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(RETURN_MAX_NEW);

			resultSet = sentenciaSQL.executeQuery();

			if (resultSet.next()) {
				noticia = new News();
				noticia.setId_noticia(resultSet.getInt("id_noticia"));
				noticia.setFoto_noticia(resultSet.getBlob("fotografia_noticia"));
				noticia.setTitulo(resultSet.getString("titulo"));
				noticia.setDescripcion(resultSet.getString("descripcion"));
				noticia.setDni_administrador(resultSet.getString("dni"));

			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
		}
		return noticia;
	}

	@Override
	public boolean updateNew(Blob fotografia_noticia, String titulo, String descripcion, String tituloAntiguo) {
		boolean cambios = false;

		conexion = DatabaseConnectionAdmin.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_NEW);

			sentenciaSQL.setBlob(1, fotografia_noticia);
			sentenciaSQL.setString(2, titulo);
			sentenciaSQL.setString(3, descripcion);
			sentenciaSQL.setString(4, tituloAntiguo);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;

	}

	@Override
	public boolean updateArsenal(Blob fotografia_arsenal, String nombre, String tipo, String descripcion,
			int id_arsenal) {
		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_ARSENAL);

			sentenciaSQL.setBlob(1, fotografia_arsenal);
			sentenciaSQL.setString(2, nombre);
			sentenciaSQL.setString(3, tipo);
			sentenciaSQL.setString(4, descripcion);
			sentenciaSQL.setInt(5, id_arsenal);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public boolean updateCriminal(String descripcion, String dni) {
		boolean cambios = false;

		conexion = DatabaseConnectionPolice.getConnection();

		try {
			sentenciaSQL = conexion.prepareStatement(UPDATE_CRIMINAL);

			sentenciaSQL.setString(1, descripcion);
			sentenciaSQL.setString(2, dni);

			if (sentenciaSQL.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		}

		return cambios;
	}

	@Override
	public News selectingNoticia(int id) {

		conexion = DatabaseConnectionAdmin.getConnection();
		
		ResultSet resultSet = null;
		
		News noticia = new News();

		try {
			// Llamamos a un procedimiento con cursores.
			CallableStatement llamadaProcedimiento = (CallableStatement) this.conexion.prepareCall(SELECTING_NEWS);
			
			llamadaProcedimiento.setInt(1, id);
			
			resultSet = llamadaProcedimiento.executeQuery();
			
			if(resultSet.next()) {
				noticia.setId_noticia(resultSet.getInt("id"));
				noticia.setFoto_noticia(resultSet.getBlob("foto"));
				noticia.setTitulo(resultSet.getString("title"));
				noticia.setDescripcion(resultSet.getString("descripc"));
				noticia.setDni_administrador(resultSet.getString("dniadmin"));
			}

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}

		return noticia;
	}

}

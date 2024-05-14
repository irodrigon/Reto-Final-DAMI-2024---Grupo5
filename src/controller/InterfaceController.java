package controller;

import java.sql.Blob;
import java.util.ArrayList;
import model.Administrador;
import model.Arsenal;
import model.Criminal;
import model.Elige;
import model.News;
import model.Policia;

public interface InterfaceController {

	public Policia policeLogIn(String password, String dni); // Método que saca los datos de un policía de la base de
																// datos según su usuario y contraseña.

	public ArrayList<News> showNews();// Saca todos los datos de noticias de la base de datos.

	public ArrayList<Policia> showPolicemen();// Saca todos los datos de policías de la base de datos.

	public boolean deletePoliceman(String dni);// Borra un policía de la base de datos

	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia);// Muestra los criminales asociados al
																			// policía.

	public ArrayList<Arsenal> showArsenal();// Saca todos los datos del arsenal de la base de datos.

	public ArrayList<Elige> weaponsAssigned(String dni_policia);// Saca los datos de la tabla de asociación
																// policías/arsenal.

	public Administrador adminLogIn(String password, String dni);// Para que el administrador pueda entrar en el
																	// programa.

	public ArrayList<Criminal> showCriminals();// Saca todos los datos de criminales de la base de datos

	public boolean deleteWeapon(int id_weapon);// Borra un artículo del arsenal.

	public boolean insertWeapon(int id, Blob foto, String nombre, String tipo, String descripcion);// Inserta un
																									// artículo nuevo
																									// del arsenal.

	public Arsenal returnWeaponByName(String nombre);// Según el nombre del arma, saca todos los datos del arma de la
														// base de datos.

	public boolean insertAssociation(String dni, int id_arsenal);//El método sirve para crear una entrada en la tabla ELIGE de la base de datos, que asocia un artículo de arsenal a un dni de un policía.

	public Policia returnPolicemanById(String dni);//Saca un policía a través del DNI.

	public ArrayList<Elige> showAssociations();//Nos ayuda a gestionar las asociaciones arsenal/policía.

	public Criminal showCriminalByPolicemanAdmin(String dni_policia);//Saca un criminal a través del DNI del policía.
	
	public boolean insertPeople(String dni, String nombre, String apellido, String contrasena, Blob fotografia_persona);//Inserta una entrada en la tabla PERSONA.

	public boolean insertPoliceman(String dni, String rango);//Inserta una entrada en la tabla de policías.

	public boolean updateDefaultCriminal(String dni_policia, String dni);//Actualizará la tabla CRIMINAL al ser llamado.

	public Criminal selectRandomCriminal();//Selecciona un criminal de la tabla al azar.

	public boolean updatePeople(String nombre, String apellido, String contrasena, Blob fotografia_persona, String dni);//Actualizará la tabla PERSONA.

	public boolean updatePoliceman(String rango, String dni);//ACtualizará la tabla POLICIA.

	public News returnNews(String titulo);//Devuelve una noticia por título.

	public boolean deletePoliceman2(String dni);//Borra un policía.

	public boolean deleteCriminal(String dni);//Borra la entrada indicada por el DNI.

	public boolean deleteNew(int id);//Borra noticias.

	public Arsenal returnMaxWeapon();//Trae el arma por número de ID más alto. Lo usamos para actualizar la tabla dentro de la ventana.

	public boolean insertCriminal(String dni, String descripcion, String dni_policia);//Añade un nuevo criminal.

	public boolean insertNew(int id_noticia, Blob fotografia_noticia, String titulo, String descripcion, String dni);//Añade noticias.

	public News returnMaxNews();//El id de noticia más alto.

	public boolean updateNew(Blob fotografia_noticia, String titulo, String descripcion,String tituloAntiguo);//Actualiza noticias.

	public boolean updateArsenal(Blob fotografia_arsenal, String nombre, String tipo, String descripcion, int id_arsenal);//Actualiza artículos de arsenal.
	
	public boolean updateCriminal(String descripcion, String dni);//Actualiza criminales.
	
	public News selectingNoticia(int id);

}

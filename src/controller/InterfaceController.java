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


	public Policia policeLogIn(String password, String dni); //Método que saca los datos de un policía de la base de datos según su usuario y contraseña.
	public ArrayList<News> showNews();//Saca todos los datos de noticias de la base de datos.
	public ArrayList<Policia> showPolicemen();//Saca todos los datos de policías de la base de datos. 
	public boolean deletePoliceman(String dni);//Borra un policía de la base de datos
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia);//Muestra el criminal asociado al policía.
	public ArrayList<Arsenal> showArsenal();//Saca todos los datos del arsenal de la base de datos.
	public ArrayList<Elige> weaponsAssigned(String dni_policia);//Saca los datos de la tabla de asociación policías/arsenal.
	public Administrador adminLogIn(String password, String dni);//Para que el administrador pueda entrar en el programa.
	public ArrayList<Criminal> showCriminals();//Saca todos los datos de criminales de la base de datos
	public boolean deleteWeapon(int id_weapon);//Borra un artículo del arsenal.
	public boolean insertWeapon(int id, Blob foto,String nombre, String tipo, String descripcion);//Inserta un artículo nuevo del arsenal.
	public Arsenal returnWeaponByName(String nombre);//Según el nombre del arma, saca todos los datos del arma de la base de datos.

	public boolean insertAssociation(String dni, int id);
	public Policia returnPolicemanById (String dni);//


}

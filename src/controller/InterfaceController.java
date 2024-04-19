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
	public Policia policeLogIn(String password, String dni);
	public ArrayList<News> showNews();
	public ArrayList<Policia> showPolicemen();
	public boolean deletePoliceman(String dni);
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia);
	public ArrayList<Arsenal> showArsenal();
	public ArrayList<Elige> weaponsAssigned(String dni_policia);
	public Administrador adminLogIn(String password, String dni);
	public ArrayList<Criminal> showCriminals();
	public boolean deleteWeapon(int id_weapon);
	public boolean insertWeapon(int id, Blob foto,String nombre, String tipo, String descripcion);
	public Arsenal returnWeaponByName(String nombre);
}

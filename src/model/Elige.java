package model;

//La clase que serviría para recoger la tabla de asociación de policías y arsenal.


public class Elige {
	
	private int id_arsenal;
	private String dni_policia;
	
	public Elige() {
		
	}
	
	public Elige(int id_arsenal, String dni_policia) {
		this.id_arsenal = id_arsenal;
		this.dni_policia = dni_policia;
	}
	public int getId_arsenal() {
		return id_arsenal;
	}
	public void setId_arsenal(int id_arsenal) {
		this.id_arsenal = id_arsenal;
	}
	public String getDni_policia() {
		return dni_policia;
	}
	public void setDni_policia(String dni_policia) {
		this.dni_policia = dni_policia;
	}
	
	
}

package model;

import java.sql.Blob;

public class Criminal extends Persona{
	
	private String descripcion;
	private String dni_policia;
	
	public Criminal() {
		
	}
	public Criminal(String dni, String nombre, String apellido, String password, Blob fotografia,String descripcion, String dni_policia) {
		super(dni, nombre, apellido, password, fotografia);
		// TODO Auto-generated constructor stub
		this.descripcion = descripcion;
		this.dni_policia = dni_policia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDni_policia() {
		return dni_policia;
	}

	public void setDni_policia(String dni_policia) {
		this.dni_policia = dni_policia;
	}
}

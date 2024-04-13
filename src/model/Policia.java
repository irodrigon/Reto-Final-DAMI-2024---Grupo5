package model;

import java.sql.Blob;

import enums.Rango;

public class Policia extends Persona{
	
	private Rango rango;
	
	public Policia(String dni, String nombre, String apellido, String password, Blob fotografia, String rango) {
		super(dni, nombre, apellido, password, fotografia);
		this.rango = Rango.valueOf(rango.toUpperCase());
		
	}

	public String getRango() {
		return rango.name();
	}

	public void setRango(String rango) {
		this.rango = Rango.valueOf(rango);
	}

}

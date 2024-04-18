package model;

import java.sql.Blob;

import enums.RangoP;


public class Policia extends Persona{
	
	private RangoP rango;
	
	public Policia(String dni, String nombre, String apellido, String password, Blob fotografia, String rango) {
		super(dni, nombre, apellido, password, fotografia);
		this.rango = RangoP.valueOf(rango.toUpperCase());
		
	}

	public String getRango() {
		return rango.name();
	}

	public void setRango(String rango) {
		this.rango = RangoP.valueOf(rango);
	}

}

package model;

import java.sql.Blob;

import enums.Rango;
import enums.Tipo;

public class Arsenal {
	private int id_arsenal;
	private Blob foto_arsenal;
	private String nombre;
	private Tipo tipo;
	private String descripcion;
	
	public int getId_arsenal() {
		return id_arsenal;
	}
	public void setId_arsenal(int id_arsenal) {
		this.id_arsenal = id_arsenal;
	}
	public Blob getFoto_arsenal() {
		return foto_arsenal;
	}
	public void setFoto_arsenal(Blob foto_arsenal) {
		this.foto_arsenal = foto_arsenal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo.name();
	}
	//El método getter convierte el atributo del enum a String para recogerlo de SQL que también usa enum.
	public void setTipo(String tipo) {
		this.tipo = Tipo.valueOf(tipo.toUpperCase());
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

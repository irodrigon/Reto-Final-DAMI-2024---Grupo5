package model;

import java.sql.Blob;

public class News {
	
	private int id_noticia;
	private Blob foto_noticia;
	private String titulo;
	private String descripcion;
	private String dni_administrador;
	public int getId_noticia() {
		return id_noticia;
	}
	public void setId_noticia(int id_noticia) {
		this.id_noticia = id_noticia;
	}
	public Blob getFoto_noticia() {
		return foto_noticia;
	}
	public void setFoto_noticia(Blob foto_noticia) {
		this.foto_noticia = foto_noticia;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDni_administrador() {
		return dni_administrador;
	}
	public void setDni_administrador(String dni_administrador) {
		this.dni_administrador = dni_administrador;
	}
	
}

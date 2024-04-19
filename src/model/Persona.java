package model;

import java.sql.Blob;

//De esta clase heredan Administrador y Policía.
public class Persona {
	private String dni;
	private String nombre;
	private String apellido;
	private String password;
	//El tipo "Blob" sirve para incluir imágenes importadas previamente a SQL.
	private Blob fotografia;
	
	public Persona() {
		
	}
	
	public Persona(String dni, String nombre, String apellido, String password, Blob fotografia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.fotografia = fotografia;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Blob getFotografia() {
		return fotografia;
	}

	public void setFotografia(Blob fotografia) {
		this.fotografia = fotografia;
	}
	
	
}

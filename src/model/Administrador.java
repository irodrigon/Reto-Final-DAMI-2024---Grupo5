package model;

import java.sql.Blob;
import java.sql.Date;
<<<<<<< HEAD
import java.time.LocalDate;

=======

//Aquí está una de las clases utilizadas en el programa.
>>>>>>> 5bfdf4f4973cf4802c748cd8d84feba63a91ad10
public class Administrador extends Persona{
	
	private Date fechaPrimerLog;
	private Date fechaUltimoLog;
	
	public Administrador(String dni, String nombre, String apellido, String password, Blob fotografia, Date fechaPrimerLog, Date fechaUltimoLog) {
		super(dni, nombre, apellido, password, fotografia);
		this.fechaPrimerLog = fechaPrimerLog;
		this.fechaUltimoLog = fechaUltimoLog;
	}

	public Date getFechaPrimerLog() {
		return fechaPrimerLog;
	}

	public void setFechaPrimerLog(Date fechaPrimerLog) {
		this.fechaPrimerLog = fechaPrimerLog;
	}

	public Date getFechaUltimoLog() {
		return fechaUltimoLog;
	}

	public void setFechaUltimoLog(Date fechaUltimoLog) {
		this.fechaUltimoLog = fechaUltimoLog;
	}
	
	
}

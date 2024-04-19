package model;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

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

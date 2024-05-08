package exceptions;

//Esta excepción lanzará un mensaje en un JOptionPane en la ventana de entrada.
public class ExceptionDni extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dni;
	
	public ExceptionDni(String dni) {
		this.dni = dni;
		
	}
	
	public String mostrarMensajeIncorrecto() {
		return "El dni: " + dni + " no tiene el formato correcto.";
	}
}

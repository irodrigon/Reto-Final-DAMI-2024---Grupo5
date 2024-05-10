package app;

import controller.Controller;
import view.VEntrada;

//Este código lanza la ventana principal, la de entrada.

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller controlador = new Controller();
		VEntrada frame = new VEntrada(controlador);
		frame.setVisible(true);
	}

}
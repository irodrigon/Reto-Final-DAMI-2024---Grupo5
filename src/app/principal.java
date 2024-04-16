package app;

import controller.Controller;
import view.VEntrada;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		VEntrada frame = new VEntrada(c);
		frame.setVisible(true);
	}

}
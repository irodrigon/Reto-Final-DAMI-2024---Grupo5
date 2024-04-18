package controller;

import java.util.ArrayList;

import model.Arsenal;
import model.Criminal;
import model.Elige;
import model.News;
import model.Policia;

public interface InterfaceController {
	public Policia policeLogIn(String password, String dni);
	public ArrayList<News> showNews();
	public ArrayList<Policia> showPolicemen();
	public boolean eliminarPolicia(String dni);
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia);
	public ArrayList<Arsenal> showArsenal();
	public ArrayList<Elige> weaponsAssigned(String dni_policia);
}

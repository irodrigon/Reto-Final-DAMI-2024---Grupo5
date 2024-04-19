package controller;

import java.util.ArrayList;


import model.Criminal;
import model.News;
import model.Policia;

public interface InterfaceController {
	public Policia policeLogIn(String password, String dni);
	public ArrayList<News2> showNews();
	public ArrayList<Policia> showPolicemen();
	public ArrayList<Criminal> showCriminalByPoliceman(String dni_policia);
}

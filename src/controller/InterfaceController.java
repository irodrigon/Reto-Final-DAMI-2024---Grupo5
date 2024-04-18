package controller;

import java.util.ArrayList;

import model.News2;
import model.Policia;

public interface InterfaceController {
	public Policia policeLogIn(String password, String dni);
	public ArrayList<News2> showNews();
	public ArrayList<Policia> showPolicemen();
}

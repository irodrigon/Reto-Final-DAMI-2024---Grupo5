package controller;

import java.util.ArrayList;

import model.News;
import model.Policia;

public interface InterfaceController {
	public Policia policeLogIn(String password, String dni);
	public ArrayList<News> showNews();
	public ArrayList<Policia> showPolicemen();
}

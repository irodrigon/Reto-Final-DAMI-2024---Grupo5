package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;


//La clase se usa para redondear el perfil de las ventanas.

public class RoundedBorder implements Border{

	private int radius;
	  private Color favColor=Color.decode("#BFBBBA");
	  //private Color favColor=Color.decode("#F55C52");

	  public RoundedBorder(int radius) {
	    this.radius=radius;
	  }

	  @Override
	  public Insets getBorderInsets(Component c) {
	    return new Insets(this.radius,this.radius,this.radius,this.radius);
	  }

	  @Override
	  public boolean isBorderOpaque() {
	    return false;
	  }

	  @Override
	  public void paintBorder(Component c,Graphics g,int x,int y,int width,int height) {
	    Graphics2D graphics2=(Graphics2D)g;
	    graphics2.setColor(favColor);
	    graphics2.setStroke(new BasicStroke(3.0f));
	    graphics2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
	    graphics2.drawRoundRect(x+1,y+1,width-3,height-3,radius,radius);
	  }
	}


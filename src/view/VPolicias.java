package view;



import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Policia;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class VPolicias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private String dni;
	private String pass;
	private Policia p;
	private Blob aBlob;
	
	public VPolicias(Controller c, String dni,String pass) {
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		p = c.policeLogIn(this.pass, this.dni);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aBlob = p.getFotografia();
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(239, 33, 321, 411);
		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(lblFoto);
		
		JLabel lblDni = new JLabel("DNI: " + p.getDni());
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDni.setBounds(82, 499, 285, 32);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre: " + p.getNombre());
		lblNombre.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNombre.setBounds(82, 541, 285, 32);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: " + p.getApellido());
		lblApellido.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblApellido.setBounds(82, 583, 285, 32);
		contentPane.add(lblApellido);
		
		JLabel lblRango = new JLabel("Rango: " + p.getRango());
		lblRango.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblRango.setBounds(82, 625, 285, 32);
		contentPane.add(lblRango);
	}

}

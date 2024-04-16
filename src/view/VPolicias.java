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
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import model.Criminal;

public class VPolicias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private String dni;
	private String pass;
	private Policia p;
	private Blob aBlob;
	private ArrayList<Criminal> crims;
	
	public VPolicias(Controller c, String dni,String pass) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VPolicias.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		
		crims = c.showCriminalByPoliceman(dni);
		p = c.policeLogIn(this.pass, this.dni);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 884, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aBlob = p.getFotografia();
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(22, 10, 106, 153);
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
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblDni.setBounds(142, 10, 285, 32);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre: " + p.getNombre());
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNombre.setBounds(142, 40, 285, 32);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: " + p.getApellido());
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblApellido.setBounds(142, 68, 285, 32);
		contentPane.add(lblApellido);
		
		JLabel lblRango = new JLabel("Rango: " + p.getRango());
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblRango.setBounds(142, 98, 285, 32);
		contentPane.add(lblRango);
		
		JLabel lblFoto2 = new JLabel("");
		lblFoto2.setBounds(738, 10, 106, 153);
		contentPane.add(lblFoto2);
		
		aBlob = crims.get(0).getFotografia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(682, 174, 89, 23);
		contentPane.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(781, 174, 89, 23);
		contentPane.add(btnSiguiente);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VPolicias.class.getResource("/fotos/fondoPolicia2.jpg")));
		lblNewLabel.setBounds(0, 10, 870, 543);
		contentPane.add(lblNewLabel);
	}
}
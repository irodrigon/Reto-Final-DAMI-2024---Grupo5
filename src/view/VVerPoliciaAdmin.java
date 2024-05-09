package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Blob;

import controller.Controller;
import model.Policia;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class VVerPoliciaAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFoto;
	private Blob aBlob;
	private Controller c;
	private Policia p;
	private String dni;
	private String dni2;
	private JButton btnAtras;

	public VVerPoliciaAdmin(Controller c, String dni, String dni2) {
		setResizable(false);
		this.c = c;
		this.dni = dni;
		this.dni2 = dni2;
		this.p = c.returnPolicemanById(dni2);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VVerPoliciaAdmin.class.getResource("/fotos/pixelart2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 26));
		btnAtras.setBounds(570, 596, 129, 43);
		contentPane.add(btnAtras);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDNI = new JLabel("DNI: "+ p.getDni());
		lblDNI.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		lblDNI.setForeground(new Color(255, 255, 255));
		lblDNI.setBounds(48, 50, 529, 49);
		contentPane.add(lblDNI);

		JLabel lblNombre = new JLabel("Nombre: "+p.getNombre());
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(48, 93, 484, 43);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido: "+p.getApellido());
		lblApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setBounds(48, 136, 529, 43);
		contentPane.add(lblApellido);

		JLabel lblContrasena = new JLabel("Contraseña: "+p.getPassword());
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		lblContrasena.setBounds(48, 174, 529, 43);
		contentPane.add(lblContrasena);

		JLabel lblRango = new JLabel("Rango: "+p.getRango());
		lblRango.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setBounds(48, 224, 376, 40);
		contentPane.add(lblRango);

		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(922, 50, 253, 312);
		contentPane.add(lblFoto);
//
		aBlob = p.getFotografia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),
					lblFoto.getHeight(), Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VVerPoliciaAdmin.class.getResource("/fotos/fondoPoliciaFinal2.jpg")));
		lblNewLabel.setBounds(0, 0, 1266, 696);
		contentPane.add(lblNewLabel);
		
		btnAtras.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if(o == btnAtras) {
			VManagement vm = new VManagement(c, dni);
			vm.setVisible(true);
			this.dispose();
		}
		
	}

}

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import model.Arsenal;
import java.awt.Toolkit;

public class VVerArsenalAdmin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private String nombre;
	private Arsenal a;
	private Blob aBlob;
	private JLabel lblFoto;
	private JLabel lblNombre;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblBienvenida;
	private JButton btnAtras;
	private JLabel lblFondo;
	private String dni;
	
	public VVerArsenalAdmin(Controller controlador, String nombre,String dni) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VVerArsenalAdmin.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		this.nombre = nombre;
		this.dni = dni;
		a = controlador.returnWeaponByName(this.nombre);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 20, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(458, 86, 455, 275);
		contentPane.add(lblFoto);
		
		lblNombre = new JLabel("Nombre: " + a.getNombre());
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblNombre.setBounds(458, 410, 388, 27);
		contentPane.add(lblNombre);
		
		lblTipo = new JLabel("Tipo: " + a.getTipo());
		lblTipo.setForeground(new Color(0, 0, 0));
		lblTipo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTipo.setBounds(458, 444, 320, 27);
		contentPane.add(lblTipo); 
		
		lblDescripcion = new JLabel("Descripción: " + a.getDescripcion());
		lblDescripcion.setForeground(new Color(0, 0, 0));
		lblDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblDescripcion.setBounds(458, 471, 740, 43);
		contentPane.add(lblDescripcion);
		
		lblBienvenida = new JLabel("ARSENAL");
		lblBienvenida.setForeground(new Color(0, 0, 0));
		lblBienvenida.setFont(new Font("Dialog", Font.BOLD, 37));
		lblBienvenida.setBounds(590, 10, 207, 54);
		contentPane.add(lblBienvenida);

		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		btnAtras.setBounds(578, 636, 167, 37);
		contentPane.add(btnAtras);

		aBlob = a.getFoto_arsenal();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			
			lblFondo = new JLabel("\r\n");
			lblFondo.setIcon(new ImageIcon(VVerArsenal.class.getResource("/fotos/fondoArsenal.jpg")));
			lblFondo.setBounds(0, -22, 1280, 720);
			contentPane.add(lblFondo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		btnAtras.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		if(e.getSource().equals(btnAtras)) {
			VManagement vm = new VManagement(controlador, dni);
			vm.setVisible(true);
			this.dispose();
		}
		
	}

}

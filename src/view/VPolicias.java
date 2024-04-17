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
import java.util.ListIterator;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import model.Criminal;
import model.News;
import model.Persona;

public class VPolicias extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private String dni;
	private String pass;
	private Policia p;
	private Blob aBlob;
	private ArrayList<Criminal> crims;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JLabel lblDniC;
	private JLabel lblNombreC;
	private JLabel lblApellidoC;
	private JLabel lblDesc;
	private JLabel lblFoto2;
	private Criminal crim;
	private ListIterator<Criminal> it;
	private JButton btnVerArsenal;
	private JButton btnElegirArsenal;
	private JButton btnModificar;
	private JButton btnAtras;
	
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
		lblDni.setBounds(138, 10, 285, 32);
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
		
		lblFoto2 = new JLabel("");
		lblFoto2.setBounds(738, 10, 106, 153);
		
		aBlob = crims.get(0).getFotografia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(),lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		contentPane.add(lblFoto2);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(682, 174, 89, 23);
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(781, 174, 89, 23);
		contentPane.add(btnSiguiente);
		
		lblDniC = new JLabel("DNI: " + crims.get(0).getDni());
		lblDniC.setForeground(Color.WHITE);
		lblDniC.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblDniC.setBounds(443, 10, 285, 32);
		contentPane.add(lblDniC);
		
		lblNombreC = new JLabel("Nombre: " + crims.get(0).getNombre());
		lblNombreC.setForeground(Color.WHITE);
		lblNombreC.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNombreC.setBounds(443, 40, 285, 32);
		contentPane.add(lblNombreC);
		
		lblApellidoC = new JLabel("Apellido: " + crims.get(0).getApellido());
		lblApellidoC.setForeground(Color.WHITE);
		lblApellidoC.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblApellidoC.setBounds(443, 68, 285, 32);
		contentPane.add(lblApellidoC);
		
		lblDesc = new JLabel("Descripción: " + crims.get(0).getDescripcion());
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblDesc.setBounds(443, 98, 285, 32);
		contentPane.add(lblDesc);
		
		btnVerArsenal = new JButton("Ver el arsenal disponible");
		btnVerArsenal.setBounds(94, 417, 207, 23);
		contentPane.add(btnVerArsenal);
		
		btnElegirArsenal = new JButton("Elegir Arsenal");
		btnElegirArsenal.setBounds(662, 417, 182, 23);
		contentPane.add(btnElegirArsenal);
		
		btnModificar = new JButton("Modificar mi perfil");
		btnModificar.setBounds(227, 489, 183, 23);
		contentPane.add(btnModificar);
		
		btnAtras = new JButton("Volver");
		btnAtras.setBounds(476, 489, 200, 23);
		contentPane.add(btnAtras);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VPolicias.class.getResource("/fotos/fondoPolicia2.jpg")));
		lblFondo.setBounds(0, 10, 870, 543);
		contentPane.add(lblFondo);
		
		btnAnterior.addActionListener(this);
		btnSiguiente.addActionListener(this);
		btnAtras.addActionListener(this);
		
		it = crims.listIterator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		crim = new Criminal();
		Object o = e.getSource();
		
		if(it.nextIndex() == crims.size()) {
			it.previous();
		}
		
		if(it.previousIndex() == -1) {
			it.next();
		}

		if (o == btnSiguiente) {
			if (it.hasNext()) {
				btnAnterior.setEnabled(true);
				crim = it.next();
				lblDniC.setText("DNI: "+ crim.getDni());
				lblNombreC.setText("Nombre:" + crim.getNombre());
				lblApellidoC.setText("Apellido: " + crim.getApellido());
				lblDesc.setText("Descripción: "+ crim.getDescripcion());
				aBlob = crim.getFotografia();
				try {
					InputStream is;
					is = aBlob.getBinaryStream(1, aBlob.length());
					BufferedImage imag;
					imag = ImageIO.read(is);
					lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		} else if (o == btnAnterior) {
			if (it.hasPrevious()) {
				crim = it.previous();
				lblDniC.setText("DNI: " + crim.getDni());
				lblNombreC.setText("Nombre: " + crim.getNombre());
				lblApellidoC.setText("Apellido: " + crim.getApellido());
				lblDesc.setText("Descripción: " + crim.getDescripcion());
				aBlob = crim.getFotografia();
				try {
					InputStream is;
					is = aBlob.getBinaryStream(1, aBlob.length());
					BufferedImage imag;
					imag = ImageIO.read(is);
					lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(),lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		}else if(o == btnAtras) {
			VEntrada vE = new VEntrada(c);
			vE.setVisible(true);
			this.dispose();
		}
		
	}
}
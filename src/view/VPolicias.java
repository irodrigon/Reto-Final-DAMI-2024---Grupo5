package view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Policia;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

public class VPolicias extends JFrame implements ActionListener {

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
	private JLabel lblFoto2;
	private Criminal crim;
	private JButton btnVerArsenal;
	private JButton btnElegirArsenal;
	private JButton btnModificar;
	private JButton btnAtras;
	private JButton btnEliminar;
	private int index;

	public VPolicias(Controller c, String dni, String pass) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VPolicias.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		p = new Policia();

		crims = c.showCriminalByPoliceman(dni);
		p = c.returnPolicemanById(dni);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		aBlob = p.getFotografia();

		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(33, 22, 169, 235);
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
		contentPane.add(lblFoto);

		JLabel lblDni = new JLabel("DNI: " + p.getDni());
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));

		lblDni.setBounds(224, 33, 285, 32);

		contentPane.add(lblDni);

		JLabel lblNombre = new JLabel("Nombre: " + p.getNombre());
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblNombre.setBounds(224, 75, 285, 32);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido: " + p.getApellido());
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblApellido.setBounds(224, 124, 285, 32);
		contentPane.add(lblApellido);

		JLabel lblRango = new JLabel("Rango: " + p.getRango());
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblRango.setBounds(224, 166, 285, 45);
		contentPane.add(lblRango);

		lblFoto2 = new JLabel("");
		lblFoto2.setBounds(1056, 33, 169, 212);

		aBlob = crims.get(0).getFotografia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(),
					lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		contentPane.add(lblFoto2);
		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		btnAnterior.setBounds(1031, 269, 98, 23);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		btnSiguiente.setBounds(1154, 269, 89, 23);
		contentPane.add(btnSiguiente);

		lblDniC = new JLabel("DNI: " + crims.get(0).getDni());
		lblDniC.setForeground(Color.WHITE);
		lblDniC.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblDniC.setBounds(694, 33, 311, 32);
		contentPane.add(lblDniC);

		lblNombreC = new JLabel("Nombre: " + crims.get(0).getNombre());
		lblNombreC.setForeground(Color.WHITE);
		lblNombreC.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblNombreC.setBounds(694, 75, 311, 32);
		contentPane.add(lblNombreC);

		lblApellidoC = new JLabel("Apellido: " + crims.get(0).getApellido());
		lblApellidoC.setForeground(Color.WHITE);
		lblApellidoC.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		lblApellidoC.setBounds(694, 124, 314, 32);
		contentPane.add(lblApellidoC);

		btnVerArsenal = new JButton("Ver el arsenal disponible");
		btnVerArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnVerArsenal.setBounds(224, 324, 227, 23);
		contentPane.add(btnVerArsenal);

		btnElegirArsenal = new JButton("Elegir Arsenal");
		btnElegirArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnElegirArsenal.setBounds(798, 440, 182, 23);
		contentPane.add(btnElegirArsenal);

		btnModificar = new JButton("Modificar mi perfil");
		btnModificar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnModificar.setBounds(311, 440, 183, 23);
		contentPane.add(btnModificar);

		btnAtras = new JButton("Volver");
		btnAtras.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnAtras.setBounds(554, 593, 200, 23);
		contentPane.add(btnAtras);

		btnEliminar = new JButton("Eliminar mi perfil");
		btnEliminar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminar.setBounds(839, 324, 207, 23);
		contentPane.add(btnEliminar);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VPolicias.class.getResource("/fotos/fondoPoliciaFinal2.jpg")));
		lblFondo.setBounds(0, 0, 1276, 683);
		contentPane.add(lblFondo);

		btnAnterior.addActionListener(this);
		btnSiguiente.addActionListener(this);
		btnAtras.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnVerArsenal.addActionListener(this);
		btnModificar.addActionListener(this);
		btnElegirArsenal.addActionListener(this);
		btnAnterior.setEnabled(false);

		// it = crims.listIterator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		crim = new Criminal();
		Object o = e.getSource();

		// Con ListIterator no funcionaba
		/*
		 * if(it.nextIndex() == crims.size()) { it.previous(); }
		 * 
		 * if(it.previousIndex() == -1) { it.next(); }
		 */
		if (o == btnSiguiente) {
			siguiente();
		} else if (o == btnAnterior) {
			anterior();
		} else if (o == btnAtras) {
			VEntrada vE = new VEntrada(c);
			vE.setVisible(true);
			this.dispose();
		} else if (o == btnEliminar) {
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar su perfil?");
			if (option == JOptionPane.YES_OPTION) {
				dni = p.getDni();
				c.deletePoliceman(dni);
				JOptionPane.showMessageDialog(this,
						"Usuario eliminado correctamente. No podrá volver a utilizar su perfil.", "Advertencia",
						JOptionPane.INFORMATION_MESSAGE);
				btnElegirArsenal.setEnabled(false);
				btnVerArsenal.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnSiguiente.setEnabled(false);
				btnAnterior.setEnabled(false);
			} else if (option == JOptionPane.NO_OPTION) {

			} else if (option == JOptionPane.CANCEL_OPTION) {

			} else if (option == JOptionPane.CLOSED_OPTION) {

			}
		} else if (o == btnVerArsenal) {
			dni = p.getDni();
			VVerArsenal vva = new VVerArsenal(c, dni, pass);
			vva.setVisible(true);
			this.dispose();
		} else if (o == btnModificar) {
			VModificarPerfilPolicia vmpp = new VModificarPerfilPolicia(c, dni, pass);
			vmpp.setVisible(true);
			this.dispose();
		} else if (o == btnElegirArsenal) {
			dni = p.getDni();
			VElegirArmas vea = new VElegirArmas(c, dni, pass);
			vea.setVisible(true);
			this.dispose();
		}

	}

	private void siguiente() {
		
		btnAnterior.setEnabled(true);
		if (index >= 0 && index <= crims.size()) {
			index++;
			crim = crims.get(index);
			lblDniC.setText(crim.getDni());
			lblNombreC.setText(crim.getNombre());
			lblApellidoC.setText(crim.getApellido());
			aBlob = crim.getFotografia();
			try {
				InputStream is;
				is = aBlob.getBinaryStream(1, aBlob.length());
				BufferedImage imag;
				imag = ImageIO.read(is);
				lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(),
						lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if (index == crims.size() - 1) {
				btnSiguiente.setEnabled(false);
			}
		} else {
			btnSiguiente.setEnabled(false);
			btnAnterior.setEnabled(true);
		}
	}

	private void anterior() {
		// TODO Auto-generated method stub
		if (index >= 0 && index > -1) {
			index--;
			crim = crims.get(index);
			lblDniC.setText(crim.getDni());
			lblNombreC.setText(crim.getNombre());
			lblApellidoC.setText(crim.getApellido());
			aBlob = crim.getFotografia();
			btnSiguiente.setEnabled(true);
			try {
				InputStream is;
				is = aBlob.getBinaryStream(1, aBlob.length());
				BufferedImage imag;
				imag = ImageIO.read(is);
				lblFoto2.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto2.getWidth(),
						lblFoto2.getHeight(), Image.SCALE_DEFAULT)));
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			if (index == 0) {
				btnAnterior.setEnabled(false);
			}
		} else {
			btnSiguiente.setEnabled(true);
			btnAnterior.setEnabled(false);
		}
	}
}

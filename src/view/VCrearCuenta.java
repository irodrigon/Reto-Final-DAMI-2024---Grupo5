package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.Blob;

import javax.swing.border.LineBorder;


import controller.Controller;
import model.Criminal;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class VCrearCuenta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JPasswordField passwordField;
	private JButton btnSubirFoto;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JButton btnCancelar;
	private JLabel lblNewLabel_4;
	private JPasswordField passwordField2;
	private Controller c;
	private JButton btnCrear;
	private JLabel lblFiles;
	private String dni;
	private JToggleButton tglbtnSee;
	private JToggleButton tglbtnSee2;
	private JComboBox<String> comboBoxRango;
	/**
	 * Create the frame.
	 */
	public VCrearCuenta(Controller c,String dni) {

		setResizable(false);
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearCuenta.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(218, 10, 0, 0);
		contentPane.add(verticalBox);

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldNombre.setBounds(495, 108, 165, 28);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBorder(new LineBorder(Color.BLUE, 3));

		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldApellido.setBounds(495, 150, 165, 28);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		textFieldApellido.setBorder(new LineBorder(Color.BLUE, 3));

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		passwordField.setBounds(495, 236, 165, 28);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		passwordField.setBorder(new LineBorder(Color.BLUE, 3));

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblContrasena.setBounds(60, 225, 320, 47);
		contentPane.add(lblContrasena);

		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(60, 141, 352, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(60, 103, 264, 35);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("REGISTRO DE USUARIO");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(299, 30, 276, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Usuario: " + this.dni);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_3.setBounds(60, 196, 276, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblRango = new JLabel("Rango:");
		lblRango.setForeground(Color.WHITE);
		lblRango.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRango.setBounds(60, 323, 406, 29);
		contentPane.add(lblRango);
		
		comboBoxRango = new JComboBox<String>();
		comboBoxRango.setBounds(495, 329, 165, 28);
		comboBoxRango.addItem("-");
		comboBoxRango.addItem("CABO");
		comboBoxRango.addItem("TENIENTE");
		comboBoxRango.addItem("SARGENTO");
		comboBoxRango.addItem("CAPITAN");
		comboBoxRango.addItem("COMANDANTE");
		comboBoxRango.setForeground(new Color(0, 0, 0));
		contentPane.add(comboBoxRango);


		btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setBackground(new Color(192, 192, 192));
		btnSubirFoto.setForeground(new Color(0, 0, 0));

		btnSubirFoto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSubirFoto.setBounds(996, 300, 157, 35);

		contentPane.add(btnSubirFoto);

		btnCancelar = new JButton("Cancelar");

		btnCancelar.setBackground(new Color(192, 192, 192));
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCancelar.setBounds(33, 429, 157, 29);


		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);

		lblNewLabel_4 = new JLabel("Vuelve a introducir la contraseña:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_4.setBounds(60, 282, 406, 19);
		contentPane.add(lblNewLabel_4);

		passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		passwordField2.setBounds(495, 279, 165, 28);
		contentPane.add(passwordField2);
		passwordField2.setEchoChar('*');
		passwordField2.setBorder(new LineBorder(Color.BLUE, 3));
		
		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCrear.setBackground(new Color(192, 192, 192));
		btnCrear.setBounds(364, 429, 157, 29);
		contentPane.add(btnCrear);
		
		
		lblFiles = new JLabel("");
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setBounds(161, 365, 569, 33);
		contentPane.add(lblFiles);
		
		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setBounds(670, 238, 89,23);
		contentPane.add(tglbtnSee);
		
		tglbtnSee2 = new JToggleButton("Ver");
		tglbtnSee2.setBounds(670, 286, 89, 23);
		contentPane.add(tglbtnSee2);
		
		JLabel lblFotoRegistro = new JLabel("");
		lblFotoRegistro.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));

		lblFotoRegistro.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFotoRegistro.setBounds(-12, 0, 1493, 683);

		contentPane.add(lblFotoRegistro);
		
		tglbtnSee.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (tglbtnSee.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		tglbtnSee2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (tglbtnSee2.isSelected()) {
					passwordField2.setEchoChar((char) 0);
				} else {
					passwordField2.setEchoChar('*');
				}
			}
		});
		
		btnSubirFoto.addActionListener(this);
		btnCrear.addActionListener(this);
		btnCancelar.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Object o = e.getSource();
		if (e.getSource().equals(btnSubirFoto)) {
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			filtro = new FileNameExtensionFilter("Imágenes jpg", "jpg");
			fileChooser.addChoosableFileFilter(filtro);
			int opcion = fileChooser.showOpenDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				// si ha pulsado Aceptar
				file = fileChooser.getSelectedFile();
				lblFiles.setText("Ha elegido el archivo " + fileChooser.getSelectedFile());

			} else if (opcion == JFileChooser.CANCEL_OPTION) {
				// si ha pulsado Cancelar
				lblFiles.setText("Ha pulsado Cancelar");
			} else if (opcion == JFileChooser.ERROR_OPTION) {
				// si ha producido un Error
				lblFiles.setText("Se ha producido un Error.");
			}
		} else if ((e.getSource().equals(btnCancelar))) {
			VEntrada vE = new VEntrada(c);
			vE.setVisible(true);
			this.dispose();
		}else if (e.getSource().equals(btnCrear)&& lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && comboBoxRango.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(this, "Selecciona un rango disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && textFieldNombre.getText().equals("") && textFieldApellido.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && new String(passwordField.getPassword()).equals("") && new String(passwordField2.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && !new String(passwordField.getPassword()).equals(new String(passwordField2.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear)) {
			Criminal crim = new Criminal();
			FileInputStream is = null;
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Blob blob = null;
			try {
				blob = new Blob(is.readAllBytes(), null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int option = JOptionPane.showConfirmDialog(this,
					"¿Está seguro de que desea crear un nuevo usuario?");
			if (option == JOptionPane.YES_OPTION) {
			c.insertPeople(dni, textFieldNombre.getText(), textFieldApellido.getText(),new String(passwordField2.getPassword()) , blob);
			c.insertPoliceman(dni, (String)comboBoxRango.getSelectedItem());
			crim = c.selectRandomCriminal();
			c.updateDefaultCriminal(dni,crim.getDni());
			c.insertAssociation(dni, 7);
			JOptionPane.showMessageDialog(this, "Usuario creado correctamente", "Mensaje para el usuario", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}

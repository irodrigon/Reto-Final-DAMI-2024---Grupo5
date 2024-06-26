package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;
import controller.Controller;

public class VCrearCriminal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDni;
	private JTextField textFieldNombre;
	private JButton btnSubirFoto;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JButton btnCancelar;
	private JLabel lblDNI;
	private Controller controlador;
	private JButton btnCrear;
	private JLabel lblFiles;
	private String dni;
	private JTextArea textDescripcion;
	private JTextField textFieldApellido;
	private JTextField textFieldDNIPolicia;

	public VCrearCriminal(Controller controlador, String dni) {
		setResizable(false);
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearCuenta.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
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

		textFieldDni = new JTextField();
		textFieldDni.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldDni.setBounds(409, 108, 165, 28);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);
		textFieldDni.setBorder(new LineBorder(Color.BLUE, 3));

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldNombre.setBounds(409, 146, 165, 28);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBorder(new LineBorder(Color.BLUE, 3));

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDescripcion.setBounds(210, 235, 165, 47);
		contentPane.add(lblDescripcion);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblApellido.setBounds(256, 191, 119, 33);
		contentPane.add(lblApellido);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombre.setBounds(256, 139, 125, 35);
		contentPane.add(lblNombre);

		JLabel lblDatos = new JLabel("DATOS DE CRIMINAL");
		lblDatos.setForeground(new Color(255, 255, 255));
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDatos.setBounds(384, 27, 276, 35);
		contentPane.add(lblDatos);

		textDescripcion = new JTextArea();
		textDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		textDescripcion.setBounds(396, 233, 264, 99);
		contentPane.add(textDescripcion);

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

		lblDNI = new JLabel("DNI:");
		lblDNI.setForeground(new Color(255, 255, 255));
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDNI.setBounds(299, 106, 82, 19);
		contentPane.add(lblDNI);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCrear.setBackground(new Color(192, 192, 192));
		btnCrear.setBounds(364, 429, 157, 29);
		contentPane.add(btnCrear);

		lblFiles = new JLabel("");
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setBounds(150, 483, 558, 33);
		contentPane.add(lblFiles);

		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldApellido.setColumns(10);
		textFieldApellido.setBorder(new LineBorder(Color.BLUE, 3));
		textFieldApellido.setBounds(409, 185, 165, 28);
		contentPane.add(textFieldApellido);

		textFieldDNIPolicia = new JTextField();
		textFieldDNIPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldDNIPolicia.setColumns(10);
		textFieldDNIPolicia.setBorder(new LineBorder(Color.BLUE, 3));
		textFieldDNIPolicia.setBounds(396, 357, 165, 28);
		contentPane.add(textFieldDNIPolicia);

		JLabel lblDniPoli = new JLabel("DNI del policía:");
		lblDniPoli.setForeground(Color.WHITE);
		lblDniPoli.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDniPoli.setBounds(179, 349, 196, 47);
		contentPane.add(lblDniPoli);

		JLabel lblFotoRegistro = new JLabel("");
		lblFotoRegistro.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));

		lblFotoRegistro.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFotoRegistro.setBounds(-12, 0, 1493, 683);

		contentPane.add(lblFotoRegistro);

		btnSubirFoto.addActionListener(this);
		btnCrear.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
		} else if (e.getSource().equals(btnCancelar)) {
			VManagement vm = new VManagement(controlador, dni);
			vm.setVisible(true);
			this.dispose();
		} else if (e.getSource().equals(btnCrear) && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear) && textFieldDni.getText().equals("")
				&& textFieldNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y el apellido.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear)) {
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
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea crear un nuevo perfil?");
			if (option == JOptionPane.YES_OPTION) {
				controlador.insertPeople(textFieldDni.getText(), textFieldNombre.getText(), textFieldApellido.getText(),
						Integer.toString((int) (Math.random() * 10000)), blob);
				controlador.insertCriminal(textFieldDni.getText(), textDescripcion.getText(), textFieldDNIPolicia.getText());
				JOptionPane.showMessageDialog(this, "Perfil creado correctamente", "Mensaje para el administrador",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}
}

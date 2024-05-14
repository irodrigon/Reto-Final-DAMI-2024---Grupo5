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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;
import controller.Controller;
import model.Arsenal;

//Ventana para modificar arsenal

public class VModificarArsenal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnNewButton;
	private JButton btnCrear;
	private JButton btnCancelar;
	private Controller controlador;
	private String dni;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JComboBox<String> comboBoxTipo;
	private String nombre;
	private Arsenal arsenal;
	private JLabel lblDescripcion;
	private JLabel lblNombre;
	
	public VModificarArsenal(Controller controlador, String dni, String nombre) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		this.dni = dni;
		this.nombre = nombre;
		arsenal = this.controlador.returnWeaponByName(this.nombre);
		

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTituloVentana = new JLabel("Modificar arsenal");
		lblTituloVentana.setForeground(new Color(255, 255, 255));
		lblTituloVentana.setBounds(469, 32, 262, 81);
		lblTituloVentana.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		contentPane.add(lblTituloVentana);

		JLabel lblNombreAntiguo = new JLabel("Nombre antiguo: " + arsenal.getNombre());
		lblNombreAntiguo.setForeground(new Color(255, 255, 255));
		lblNombreAntiguo.setBounds(310, 104, 491, 52);
		lblNombreAntiguo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNombreAntiguo);

		JLabel lblDescAntigua = new JLabel("Descripción antigua: " + arsenal.getDescripcion());
		lblDescAntigua.setForeground(new Color(255, 255, 255));
		lblDescAntigua.setBounds(315, 190, 780, 52);
		lblDescAntigua.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblDescAntigua);

		btnNewButton = new JButton("Subir Foto");
		btnNewButton.setBounds(552, 484, 161, 52);
		btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnNewButton);

		btnCrear = new JButton("Modificar");
		btnCrear.setBounds(340, 484, 161, 52);
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(762, 484, 161, 52);
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCancelar);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtNombre.setBounds(595, 166, 136, 31);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblTipoAntiguo = new JLabel("Tipo antiguo: " + arsenal.getTipo());
		lblTipoAntiguo.setForeground(new Color(255, 255, 255));
		lblTipoAntiguo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblTipoAntiguo.setBounds(310, 275, 213, 52);
		contentPane.add(lblTipoAntiguo);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(595, 252, 136, 31);
		contentPane.add(txtDescripcion);

		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);

		String[] arrayStrings = { "-", "ARMA", "HERRAMIENTA", "ARMADURA" };
		comboBoxTipo = new JComboBox<String>(arrayStrings);
		comboBoxTipo.setForeground(Color.BLACK);
		comboBoxTipo.setBounds(595, 343, 136, 28);
		contentPane.add(comboBoxTipo);

		lblDescripcion = new JLabel("Nueva descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDescripcion.setBounds(307, 238, 254, 52);
		contentPane.add(lblDescripcion);

		JLabel lblRango = new JLabel("Nuevo tipo:");
		lblRango.setForeground(Color.WHITE);
		lblRango.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRango.setBounds(310, 337, 191, 29);
		contentPane.add(lblRango);

		lblNombre = new JLabel("Nuevo nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(310, 166, 213, 28);
		contentPane.add(lblNombre);

		JLabel lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblFondo.setForeground(new Color(0, 0, 0));
		lblFondo.setIcon(new ImageIcon(VModificarArsenal.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFondo.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblFondo);

		btnCancelar.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnCrear.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnCancelar)) {
			VManagement vm = new VManagement(controlador, dni);
			vm.setVisible(true);
			this.dispose();
		}
		if (e.getSource().equals(btnNewButton)) {
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
		} else if (e.getSource().equals(btnCrear) && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear) && txtNombre.getText().equals("")
				&& txtDescripcion.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y la descripción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear) && comboBoxTipo.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(this, "Selecciona un tipo disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear)) {

			FileInputStream is = null;
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Blob blob = null;
			try {
				blob = new Blob(is.readAllBytes(), null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea modificar el artículo?");
			if (option == JOptionPane.YES_OPTION) {
				controlador.updateArsenal(blob, txtNombre.getText(),(String)comboBoxTipo.getSelectedItem(), txtDescripcion.getText(),arsenal.getId_arsenal());
				JOptionPane.showMessageDialog(this, "Artículo modificado correctamente", "Mensaje para el aministrador",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

}

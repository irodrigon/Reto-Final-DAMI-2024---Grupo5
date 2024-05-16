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
import javax.swing.JTextArea;

public class VModificarNoticia extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JButton btnUpload;
	private JButton btnCrear;
	private JButton btnCancelar;
	private Controller controlador;
	private String dni;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JTextArea textDescripcion;
	private String titulo;

	// Ventana para modificar perfil

	public VModificarNoticia(Controller controlador, String dni, String titulo) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		this.dni = dni;
		this.titulo = titulo;
		// Ventana para modificar perfil

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Modificar noticia:");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(469, 32, 262, 81);
		lblTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		contentPane.add(lblTitulo);

		JLabel lblTitle = new JLabel("Título:");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(469, 136, 84, 52);
		lblTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblTitle);


		btnUpload = new JButton("Subir Foto");
		btnUpload.setBounds(552, 484, 161, 52);
		btnUpload.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnUpload);

		btnCrear = new JButton("Modificar");
		btnCrear.setBounds(340, 484, 161, 52);
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(762, 484, 161, 52);
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCancelar);

		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtTitulo.setBounds(588, 147, 252, 31);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);


		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblDescripcion.setBounds(469, 231, 109, 52);
		contentPane.add(lblDescripcion);


		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);

		
		textDescripcion = new JTextArea();
		textDescripcion.setBounds(588, 248, 252, 131);
		contentPane.add(textDescripcion);


		JLabel lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblFondo.setForeground(new Color(0, 0, 0));
		lblFondo.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFondo.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblFondo);

		JLabel label = new JLabel("New label");
		label.setBounds(590, 592, 46, 14);
		contentPane.add(label);

		btnCancelar.addActionListener(this);
		btnUpload.addActionListener(this);
		btnCrear.addActionListener(this);

	};

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnCancelar)) {
			VManagement vP = new VManagement(controlador, dni);
			vP.setVisible(true);
			this.dispose();
		}
		if (e.getSource().equals(btnUpload)) {
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
		} else if (e.getSource().equals(btnCrear) && txtTitulo.getText().equals("") && textDescripcion.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el título.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear) && (textDescripcion.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Introduce la descripción.", "Error", JOptionPane.ERROR_MESSAGE);
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
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea modificar la noticia?");
			if (option == JOptionPane.YES_OPTION) {
				controlador.updateNew(blob, txtTitulo.getText(), textDescripcion.getText(),titulo);
				JOptionPane.showMessageDialog(this, "Noticia modificada correctamente", "Mensaje para el administrador",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
}

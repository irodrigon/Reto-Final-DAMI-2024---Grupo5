package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.News;
import model.Policia;
import javax.swing.JTextArea;

public class VModificarNoticia extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JButton btnNewButton;
	private JButton btnCrear;
	private JButton btnCancelar;
	private Controller c;
	private String dni;
	private String pass;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private News n;
	private JTextArea textArea;

	// Ventana para modificar perfil

	public VModificarNoticia(Controller c, String dni, String pass) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		this.n = n;
		// Ventana para modificar perfil

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Modifica tu perfil:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(469, 32, 262, 81);
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Título:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(469, 136, 84, 52);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1);

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

		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtTitulo.setBounds(588, 147, 252, 31);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);


		JLabel lblNewLabel_1_2 = new JLabel("Descripción:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(469, 231, 109, 52);
		contentPane.add(lblNewLabel_1_2);


		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);

		
		textArea = new JTextArea();
		textArea.setBounds(588, 248, 252, 131);
		contentPane.add(textArea);


		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblNewLabel_2.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblNewLabel_2);

		JLabel label = new JLabel("New label");
		label.setBounds(590, 592, 46, 14);
		contentPane.add(label);

		btnCancelar.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnCrear.addActionListener(this);

	};

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnCancelar)) {
			VManagement vP = new VManagement(c, dni);
			vP.setVisible(true);
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
		} else if (e.getSource().equals(btnCrear) && txtTitulo.getText().equals("") && textArea.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el título.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (e.getSource().equals(btnCrear) && (textArea.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Introduce la descripción.", "Error", JOptionPane.ERROR_MESSAGE);
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
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea modificar la noticia?");
			if (option == JOptionPane.YES_OPTION) {
				
				JOptionPane.showMessageDialog(this, "Noticia modificada correctamente", "Mensaje para el usuario",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
}

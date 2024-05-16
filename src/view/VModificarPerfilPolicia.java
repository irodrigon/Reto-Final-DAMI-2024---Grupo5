package view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;
import controller.Controller;
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
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class VModificarPerfilPolicia extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JPasswordField passwordField;
	private JTextField txtApellido;
	private JButton btnUpload;
	private JButton btnCrear;
	private JButton btnCancelar;
	private Controller controlador;
	private String dni;
	private String pass;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JToggleButton tglbtnSee;

	//Ventana para modificar perfil
	
	public VModificarPerfilPolicia(Controller controlador,String dni,String pass) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		this.dni = dni;
		this.pass = pass;
		//Ventana para modificar perfil

	
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Modifica tu perfil:");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(469, 32, 262, 81);
		lblTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(469, 136, 84, 52);
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNombre);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(469, 323, 97, 52);
		lblPassword.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblPassword);


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

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtNombre.setBounds(563, 147, 136, 31);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		passwordField.setBounds(563, 337, 136, 31);
		contentPane.add(passwordField);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblApellido.setBounds(469, 231, 84, 52);
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtApellido.setColumns(10);
		txtApellido.setBounds(563, 242, 136, 31);
		contentPane.add(txtApellido);
		
		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);
		
		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setBounds(710, 341, 121, 23);
		contentPane.add(tglbtnSee);

		JLabel lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblFondo.setForeground(new Color(0, 0, 0));
		lblFondo.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFondo.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblFondo);
		
		JLabel label = new JLabel("New label");
		label.setBounds(590, 592, 46, 14);
		contentPane.add(label);
		char cPass = passwordField.getEchoChar();
		
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
					passwordField.setEchoChar(cPass);
				}
			}
		});

		
		btnCancelar.addActionListener(this);
		btnUpload.addActionListener(this);
		btnCrear.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnCancelar)) {
			VPolicias vP = new VPolicias(controlador, dni, pass);
			vP.setVisible(true);
			this.dispose();
		}if (e.getSource().equals(btnUpload)) {
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
		}else if (e.getSource().equals(btnCrear)&& lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && txtNombre.getText().equals("") && txtApellido.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && new String(passwordField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear)) {
			
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
			int option = JOptionPane.showConfirmDialog(this,
					"¿Está seguro de que desea modificar el policía?");
			if (option == JOptionPane.YES_OPTION) {
			controlador.updatePeople(txtNombre.getText(), txtApellido.getText(), new String(passwordField.getPassword()), blob, dni);
			JOptionPane.showMessageDialog(this, "Policía modificado correctamente", "Mensaje para el usuario", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		

	}
}
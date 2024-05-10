package view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.Criminal;
import model.Policia;

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
	private JTextField txtDhrhdt;
	private JPasswordField passwordField;
	private JTextField txtjh;
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
	private JToggleButton tglbtnSee;
	private Policia p;

	//Ventana para modificar perfil
	
	public VModificarPerfilPolicia(Controller c,String dni,String pass) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		this.p = p;
		//Ventana para modificar perfil

	
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

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(469, 136, 84, 52);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(469, 323, 97, 52);
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1_1);


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

		txtDhrhdt = new JTextField();
		txtDhrhdt.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtDhrhdt.setBounds(563, 147, 136, 31);
		contentPane.add(txtDhrhdt);
		txtDhrhdt.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		passwordField.setBounds(563, 337, 136, 31);
		contentPane.add(passwordField);

		JLabel lblNewLabel_1_2 = new JLabel("Apellido:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(469, 231, 84, 52);
		contentPane.add(lblNewLabel_1_2);

		txtjh = new JTextField();
		txtjh.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtjh.setColumns(10);
		txtjh.setBounds(563, 242, 136, 31);
		contentPane.add(txtjh);
		
		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);
		
		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setBounds(710, 341, 121, 23);
		contentPane.add(tglbtnSee);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblNewLabel_2.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblNewLabel_2);
		
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
		btnNewButton.addActionListener(this);
		btnCrear.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource().equals(btnCancelar)) {
			VPolicias vP = new VPolicias(c, dni, pass);
			vP.setVisible(true);
			this.dispose();
		}if (e.getSource().equals(btnNewButton)) {
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
		}else if(e.getSource().equals(btnCrear) && txtDhrhdt.getText().equals("") && txtjh.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && new String(passwordField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear)) {
			
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
					"¿Está seguro de que desea modificar el policía?");
			if (option == JOptionPane.YES_OPTION) {
			c.updatePeople(txtDhrhdt.getText(), txtjh.getText(), new String(passwordField.getPassword()), blob, dni);
			JOptionPane.showMessageDialog(this, "Policía modificado correctamente", "Mensaje para el usuario", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		

	}
}
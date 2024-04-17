package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class VCrearCuenta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JPasswordField passwordField;
	private JTextField textFieldUsuario;
	private JButton btnSubirFoto;
	private JFileChooser fileChooser;
	private JButton btnCancelar;
	private JLabel lblNewLabel_4;
	private JPasswordField passwordField_1;
	private Controller c;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VCrearCuenta(Controller c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearCuenta.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 884, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(218, 10, 0, 0);
		contentPane.add(verticalBox);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(376, 114, 130, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(376, 156, 130, 19);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(376, 242, 130, 19);
		contentPane.add(passwordField);

		JLabel lblContrasena = new JLabel("Introduce la contraseña:");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblContrasena.setBounds(60, 225, 215, 47);
		contentPane.add(lblContrasena);

		JLabel lblNewLabel_1 = new JLabel("Introduce tu primer apellido:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(60, 153, 264, 19);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Introduce tu nombre:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(60, 103, 199, 35);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("REGISTRO DE USUARIO");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(299, 30, 276, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Introduce tu usuario:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(60, 196, 199, 19);
		contentPane.add(lblNewLabel_3);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(376, 199, 130, 19);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setBackground(new Color(0, 0, 160));
		btnSubirFoto.setForeground(new Color(0, 0, 0));
		btnSubirFoto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSubirFoto.setBounds(616, 329, 157, 35);
		contentPane.add(btnSubirFoto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(0, 0, 160));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancelar.setBounds(33, 429, 157, 29);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);

		lblNewLabel_4 = new JLabel("Vuelve a introducir la contraseña:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(60, 282, 306, 19);
		contentPane.add(lblNewLabel_4);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(376, 285, 130, 19);
		contentPane.add(passwordField_1);
		
		JLabel lblFotoRegistro = new JLabel("");
		lblFotoRegistro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFotoRegistro.setIcon(new ImageIcon(VCrearCuenta.class.getResource("/fotos/fondoPolicia3.jpg")));
		lblFotoRegistro.setBounds(-552, 121, 1493, 485);
		contentPane.add(lblFotoRegistro);
		
		btnSubirFoto.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((e.getSource() == btnSubirFoto)) {

			fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);
		} else if ((e.getSource() == btnCancelar)) {
			VEntrada vE = new VEntrada(c);
			vE.setVisible(true);
			this.dispose();
		

		}
	}
}

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
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import controller.Controller;
import model.RandomString;

public class VCrearCriminal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JButton btnSubirFoto;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JButton btnCancelar;
	private JLabel lblDNI;
	private JPasswordField passwordField;
	private Controller c;
	private JButton btnCrear;
	private JLabel lblFiles;
	private String dni;
	private JTextField textField;
	private JTextField textFieldDNI;
	private JTextArea textArea;

	public VCrearCriminal(Controller c, String dni) {
		setResizable(false);
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearCuenta.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
		RandomString tickets = new RandomString(23, new SecureRandom(), easy);
		System.out.println(tickets.toString());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(218, 10, 0, 0);
		contentPane.add(verticalBox);

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldNombre.setBounds(409, 108, 165, 28);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBorder(new LineBorder(Color.BLUE, 3));

		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldApellido.setBounds(409, 146, 165, 28);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		textFieldApellido.setBorder(new LineBorder(Color.BLUE, 3));

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(518, 204, 142, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDescripcion.setBounds(61, 244, 320, 47);
		contentPane.add(lblDescripcion);

		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(256, 191, 119, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(256, 139, 125, 35);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("DATOS DE CRIMINAL");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(384, 27, 276, 35);
		contentPane.add(lblNewLabel);

		textArea = new JTextArea();
		textArea.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		textArea.setBounds(396, 233, 264, 99);
		contentPane.add(textArea);

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
		lblFiles.setBounds(161, 365, 569, 33);
		contentPane.add(lblFiles);

		JLabel lblFotoRegistro = new JLabel("");
		lblFotoRegistro.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));

		lblFotoRegistro.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFotoRegistro.setBounds(-12, 0, 1493, 683);

		contentPane.add(lblFotoRegistro);

		textField = new JTextField();
		textField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.BLUE, 3));
		textField.setBounds(471, 146, 165, 28);
		contentPane.add(textField);

		btnSubirFoto.addActionListener(this);
		btnCrear.addActionListener(this);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}

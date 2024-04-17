package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Policia;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VEntrada extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private JTextField textFieldUser;
	private JPasswordField passField;
	private JButton btnNews;
	private JButton btnEntrar;
	private Policia p;
	private String dni;
	private JLabel lblIncorrecto;
	private String pass;

	public VEntrada(Controller c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VEntrada.class.getResource("/fotos/pixelart2.png")));

		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 1280, 720);
		contentPane = new JPanel();
		// contentPane.setBackground((img));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		lblUsuario.setBounds(150, 197, 187, 28);
		contentPane.add(lblUsuario);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldUser.setBounds(397, 200, 473, 27);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		passField = new JPasswordField();
		passField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		passField.setBounds(397, 314, 473, 28);
		contentPane.add(passField);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		lblPassword.setBounds(150, 311, 187, 28);
		contentPane.add(lblPassword);

		JLabel lblBienvenida = new JLabel("BIENVENIDO, AGENTE.");
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		lblBienvenida.setBounds(550, 10, 255, 25);
		contentPane.add(lblBienvenida);

		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		btnEntrar.setBounds(521, 459, 220, 42);
		contentPane.add(btnEntrar);
		btnEntrar.setOpaque(true);
		btnEntrar.setBorderPainted(false);

		btnNews = new JButton("NOTICIAS");
		btnNews.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		btnNews.setBounds(291, 570, 690, 74);
		contentPane.add(btnNews);
		btnNews.setBorderPainted(false);
		btnNews.setBackground(Color.GRAY);

		lblIncorrecto = new JLabel("", SwingConstants.CENTER);
		lblIncorrecto.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblIncorrecto.setBounds(130, 403, 690, 28);
		contentPane.add(lblIncorrecto);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFoto.setBounds(0, -20, 1478, 821);
		contentPane.add(lblFoto);

		btnNews.addActionListener(this);
		btnEntrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (o == btnNews) {
			VNoticias vn = new VNoticias(c);
			vn.setVisible(true);
			this.dispose();
		} else if (o == btnEntrar) {

			p = c.policeLogIn(new String(passField.getPassword()), textFieldUser.getText());
			if (p != null) {
				dni = p.getDni();
				pass = p.getPassword();
				VPolicias vp = new VPolicias(c, dni, pass);
				vp.setVisible(true);
				this.dispose();
			} else {
				VCrearCuenta vcc = new VCrearCuenta(c);
				vcc.setVisible(true);
				this.dispose();
			}
		}

	}
}

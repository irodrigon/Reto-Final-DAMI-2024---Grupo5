package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import exceptions.ExceptionDni;
import model.Administrador;
import model.Policia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.SystemColor;

import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;

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
	private String pass;
	private Administrador admin;
	private String adminDni;
	private JButton btnSalir;
	private JToggleButton tglbtnSee;

	public VEntrada(Controller c) {
		setResizable(false);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}

		// El método cambia el icono en la parte superior izquierda de la ventana.
		setIconImage(Toolkit.getDefaultToolkit().getImage(VEntrada.class.getResource("/fotos/pixelart2.png")));

		// Para usar los métodos SQL, tenemos que traer el controlador.
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 1280, 720);
		contentPane = new JPanel();

		// El panel principal usa la clase "RoundedBorder" como borde para las ventanas.

		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		// El setLayout(null) permite colocar los botones, etiquetas y el resto de
		// elementos de la ventana dónde quieras
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		lblUsuario.setBounds(150, 197, 187, 28);
		contentPane.add(lblUsuario);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));

		textFieldUser.setBounds(397, 200, 473, 27);

		textFieldUser.setFocusable(false);

		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		// Usar un passwordField en vez de un textField sirve para ocultar lo que se
		// escriba.
		passField = new JPasswordField();
		passField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		passField.setBounds(397, 314, 473, 28);
		contentPane.add(passField);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		lblPassword.setBounds(150, 311, 187, 28);
		contentPane.add(lblPassword);

		JLabel lblBienvenida = new JLabel("BIENVENIDO, AGENTE.");
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		lblBienvenida.setBounds(550, 10, 255, 25);
		contentPane.add(lblBienvenida);

		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		btnEntrar.setBounds(650, 395, 220, 42);
		btnEntrar.setOpaque(true);
		// btnEntrar.setBorderPainted(true);
		contentPane.add(btnEntrar);

		btnNews = new JButton("NOTICIAS");
		btnNews.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		btnNews.setBounds(291, 570, 690, 74);
		contentPane.add(btnNews);
		btnNews.setBorderPainted(false);

		btnNews.setBackground(new Color(128, 128, 255));

		btnSalir = new JButton("SALIR");
		btnSalir.setOpaque(true);
		btnSalir.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 17));
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(397, 395, 220, 42);
		contentPane.add(btnSalir);

		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setBounds(893, 316, 121, 23);
		contentPane.add(tglbtnSee);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFoto.setBounds(-12, 0, 1493, 683);

		contentPane.add(lblFoto);

		btnNews.addActionListener(this);
		btnEntrar.addActionListener(this);
		btnSalir.addActionListener(this);

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
					passField.setEchoChar((char) 0);
				} else {
					passField.setEchoChar('*');
				}
			}
		});

		textFieldUser.addMouseListener(new MouseListener() {

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
				textFieldUser.setFocusable(true);
			}
		});

		textFieldUser.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				dni = textFieldUser.getText();

				ExceptionDni errorDni = new ExceptionDni(dni);
				Pattern pat = Pattern.compile("[0-9]{8}[A-Z]");
				Matcher mat = pat.matcher(dni);
				if (!mat.matches()) {
					JOptionPane.showMessageDialog(null,
							errorDni.mostrarMensajeIncorrecto() + " 8 números + letra Mayúscula", "Error",
							JOptionPane.ERROR_MESSAGE);
					textFieldUser.requestFocus();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				textFieldUser.setBackground(Color.CYAN);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o == btnEntrar && textFieldUser.getText().equals("") && new String(passField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Los datos están vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (o == btnNews) {
			VNoticias vn = new VNoticias(c);
			vn.setVisible(true);
			this.dispose();
		} else if (o == btnEntrar) {
			admin = c.adminLogIn(new String(passField.getPassword()), textFieldUser.getText());
			p = c.policeLogIn(new String(passField.getPassword()), textFieldUser.getText());
			if (admin != null) {
				VAdmin vA = new VAdmin(c, admin.getDni());
				vA.setVisible(true);
				this.dispose();
			} else if (p != null) {
				dni = p.getDni();
				pass = p.getPassword();
				VPolicias vp = new VPolicias(c, dni, pass);
				vp.setVisible(true);
				this.dispose();
			} else {
				int option = JOptionPane.showConfirmDialog(this,
						"Esto le llevará a crear un nuevo usuario. ¿Está seguro de que desea crear un nuevo usuario?");
				if (option == JOptionPane.YES_OPTION) {
					dni = textFieldUser.getText();
					VCrearCuenta vcc = new VCrearCuenta(c, dni);
					vcc.setVisible(true);
					this.dispose();
				}
			}
		} else if (o == btnSalir) {
			this.dispose();
		}
	}
}

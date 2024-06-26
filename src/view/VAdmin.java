package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Controller;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//Esta ventana usa un autentificación doble para el administrador.
public class VAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JToggleButton tglbtnSee;
	private JButton btnEnter;
	private JButton btnCancelar;
	private Controller controlador;
	private JLabel lblWrong;
	private JLabel lblFondo;
	private String dni;

	public VAdmin(Controller controlador, String dni) {
		setResizable(false);
		//setUndecorated(true);
		this.controlador = controlador;		
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VAdmin.class.getResource("/fotos/pixelart2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIntroduceTuUsuario = new JLabel("INTRODUCE TU USUARIO Y CONTRASEÑA DE ADMINISTRADOR:");
		lblIntroduceTuUsuario.setForeground(new Color(255, 255, 255));
		lblIntroduceTuUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblIntroduceTuUsuario.setBounds(209, 48, 809, 37);
		contentPane.add(lblIntroduceTuUsuario);

		JLabel lblUser = new JLabel("USUARIO:");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblUser.setBounds(246, 150, 120, 37);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("CONTRASEÑA:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblPassword.setBounds(187, 222, 179, 37);
		contentPane.add(lblPassword);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(401, 157, 246, 30);
		contentPane.add(textFieldUsuario);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(401, 227, 246, 30);
		contentPane.add(passwordField);

		lblWrong = new JLabel("", SwingConstants.CENTER);
		lblWrong.setForeground(new Color(0, 0, 0));
		lblWrong.setFont(new Font("Teko SemiBold", Font.PLAIN, 30));
		lblWrong.setBounds(221, 663, 523, 63);
		contentPane.add(lblWrong);

		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setForeground(new Color(0, 0, 0));
		tglbtnSee.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		tglbtnSee.setBorder(new RoundedBorder(10));
		tglbtnSee.setBackground(new Color(116, 116, 116));
		tglbtnSee.setBounds(657, 231, 50, 23);
		contentPane.add(tglbtnSee);

		btnEnter = new JButton("Entrar");
		btnEnter.setForeground(new Color(0, 0, 0));
		btnEnter.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		btnEnter.setBorder(new RoundedBorder(10));
		btnEnter.setBackground(new Color(116, 116, 116));
		btnEnter.setBounds(657, 442, 130, 26);
		contentPane.add(btnEnter);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		btnCancelar.setBorder(new RoundedBorder(10));
		btnCancelar.setBackground(new Color(116, 116, 116));
		btnCancelar.setBounds(289, 442, 130, 26);
		contentPane.add(btnCancelar);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(new ImageIcon(VAdmin.class.getResource("/fotos/fondoPoliciaFinal.jpg")).getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));
		lblFondo.setBounds(-18,-81,1297,884);
		lblFondo.setBorder(new RoundedBorder(20));
		contentPane.add(lblFondo);

		char cPass = passwordField.getEchoChar();
		btnEnter.addActionListener(this);
		btnCancelar.addActionListener(this);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String username = textFieldUsuario.getText();
		String password = new String(passwordField.getPassword());
		if (o == btnCancelar) {
			VEntrada vE = new VEntrada(controlador);
			vE.setVisible(true);
			this.dispose();
		} else if (o == btnEnter && username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
			VManagement vm = new VManagement(controlador,  dni);
			vm.setVisible(true);
			this.dispose();
		}
		if (o == btnEnter && textFieldUsuario.getText().equals("")
				&& new String(passwordField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Los datos están vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
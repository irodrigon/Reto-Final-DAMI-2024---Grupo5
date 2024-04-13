package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Policia;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VEntrada extends JFrame implements ActionListener{

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
		
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 250, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsuario.setBounds(130, 69, 187, 28);
		contentPane.add(lblUsuario);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(347, 69, 473, 27);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(347, 141, 473, 28);
		contentPane.add(passField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(130, 138, 187, 28);
		contentPane.add(lblPassword);
		
		
		JLabel lblBienvenida = new JLabel("BIENVENIDO, AGENTE.");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBienvenida.setBounds(380, 0, 187, 25);
		contentPane.add(lblBienvenida);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(347, 247, 220, 42);
		contentPane.add(btnEntrar);
		
		btnNews = new JButton("Noticias");
		btnNews.setBounds(130, 314, 690, 74);
		contentPane.add(btnNews);
		
		lblIncorrecto = new JLabel("",SwingConstants.CENTER);
		lblIncorrecto.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblIncorrecto.setBounds(130, 403, 690, 28);
		contentPane.add(lblIncorrecto);
		
		btnNews.addActionListener(this);
		btnEntrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if(o == btnNews) {
			VNoticias vn = new VNoticias(c);
			vn.setVisible(true);
			this.dispose();
		}else if(o == btnEntrar) {
			
			p = c.policeLogIn(new String(passField.getPassword()), textFieldUser.getText());
			if(p != null) {
				dni = p.getDni();
				pass = p.getPassword();
				VPolicias vp =  new VPolicias(c,dni,pass);
				vp.setVisible(true);
				this.dispose();
			}else {
				lblIncorrecto.setText("Usuario o contraseña incorrectos.");
			}
		}

		
	}
}

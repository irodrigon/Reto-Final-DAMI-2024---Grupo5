package view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

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
	//Ventana para modificar perfil
	
	public VModificarPerfilPolicia(Controller c,String dni,String pass) {
		this.c = c;
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

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblNewLabel_2.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblNewLabel_2);

		
		btnCancelar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource().equals(btnCancelar)) {
			VPolicias vP = new VPolicias(c, dni, pass);
			vP.setVisible(true);
			this.dispose();
		}
		

	}
}
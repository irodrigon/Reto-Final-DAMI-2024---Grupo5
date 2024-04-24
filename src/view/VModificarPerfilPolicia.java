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

<<<<<<< HEAD
public class s extends JFrame implements ActionListener{
=======
public class VModificarPerfilPolicia extends JFrame implements ActionListener{
>>>>>>> 47903d2b90956f139fa55d5d94b57d298749af83

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDhrhdt;
	private JPasswordField passwordField;
	private JTextField txtjh;
//Ventana para modificar perfil
	
	public VModificarPerfilPolicia(Controller c) {
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
		
		JButton btnNewButton = new JButton("Subir Foto");
		btnNewButton.setBounds(552, 484, 161, 52);
		btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnNewButton);
		
		JButton btnCrear = new JButton("Modificar");
		btnCrear.setBounds(340, 484, 161, 52);
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
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
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
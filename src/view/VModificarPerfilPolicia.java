package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VModificarPerfilPolicia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
//Ventana para modificar perfil
	
	public VModificarPerfilPolicia(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 1000);
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
		lblNewLabel_1.setBounds(469, 182, 84, 52);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(469, 362, 97, 52);
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Subir Foto");
		btnNewButton.setBounds(469, 484, 161, 52);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnNewButton);
		
		JButton btnCrear = new JButton("Modificar");
		btnCrear.setBounds(248, 484, 161, 52);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(692, 484, 161, 52);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(563, 197, 136, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(563, 377, 136, 31);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Apellido:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(469, 276, 84, 52);
		contentPane.add(lblNewLabel_1_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(563, 291, 136, 31);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblNewLabel_2.setBounds(0, 0, 1266, 863);
		contentPane.add(lblNewLabel_2);
	}
}
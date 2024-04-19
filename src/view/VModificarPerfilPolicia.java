package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class VModificarPerfilPolicia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VModificarPerfilPolicia(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modifica tu perfil:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(490, 10, 253, 105);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(469, 269, 161, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(469, 368, 161, 52);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Subir Foto");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(469, 484, 161, 52);
		contentPane.add(btnNewButton);
		
		JButton btnCrear = new JButton("Modificar");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCrear.setBounds(248, 484, 161, 52);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCancelar.setBounds(692, 484, 161, 52);
		contentPane.add(btnCancelar);
	}
}

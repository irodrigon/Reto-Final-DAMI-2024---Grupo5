package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.Arsenal;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class VCrearArsenal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private Controller c;
	private JButton btnUpload;
	private JButton btnSave;
	private JButton btnBack;
	private JButton btnCancelar;
	private JTextArea textArea;
	private JLabel lblNewLabel_1_1;
	private JLabel lblID_Arsenal;
	private JLabel lblTipo;
	private JComboBox<String> comboTipo;
	private int id_admin;
	private String dni;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JLabel lblSaveChanges;
	
	public VCrearArsenal(Controller c, String dni) {
		setResizable(false);
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearArsenal.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBounds(521, 172, 316, 28);
		contentPane.add(textField);

		JLabel lblCrearFichaDe = new JLabel("CREAR FICHA DE ARSENAL", SwingConstants.CENTER);
		lblCrearFichaDe.setForeground(Color.BLACK);
		lblCrearFichaDe.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblCrearFichaDe.setBounds(476, 96, 361, 36);
		contentPane.add(lblCrearFichaDe);

		JLabel lblName = new JLabel("Nombre:");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblName.setBounds(391, 207, 104, 36);
		contentPane.add(lblName);

		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setForeground(Color.BLACK);
		lblDesc.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblDesc.setBounds(346, 264, 149, 36);
		contentPane.add(lblDesc);

		JLabel lblAviso = new JLabel("Las fotos deberán tener una resolución de 474x711, si no, no se mostrarán bien.");
		lblAviso.setForeground(Color.BLACK);
		lblAviso.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblAviso.setBounds(401, 461, 625, 18);
		contentPane.add(lblAviso);

		btnUpload = new JButton("Subir Foto");
		btnUpload.setForeground(Color.BLACK);
		btnUpload.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnUpload.setBackground(new Color(116, 116, 116));
		btnUpload.setBounds(531, 490, 316, 23);
		contentPane.add(btnUpload);

		JLabel lblIdarsenal = new JLabel("ID_arsenal:");
		lblIdarsenal.setForeground(Color.BLACK);
		lblIdarsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblIdarsenal.setBounds(354, 161, 149, 36);
		contentPane.add(lblIdarsenal);

		btnSave = new JButton("Guardar");
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSave.setBackground(new Color(116, 116, 116));
		btnSave.setBounds(446, 551, 114, 23);
		contentPane.add(btnSave);

		btnBack = new JButton("Volver");
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack.setEnabled(false);
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setBounds(630, 551, 132, 23);
		contentPane.add(btnBack);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCancelar.setBackground(new Color(116, 116, 116));
		btnCancelar.setBounds(835, 551, 132, 23);
		contentPane.add(btnCancelar);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textField_2.setColumns(10);
		textField_2.setBounds(521, 218, 316, 28);
		contentPane.add(textField_2);

		textArea = new JTextArea();
		textArea.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		textArea.setBounds(521, 276, 316, 123);
		contentPane.add(textArea);

		lblID_Arsenal = new JLabel("");
		lblID_Arsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 32));
		lblID_Arsenal.setBounds(465, 21, 522, 36);
		contentPane.add(lblID_Arsenal);

		lblID_Arsenal.setText("El último id de arsenal es: "+ c.returnMaxWeapon().getId_arsenal());

		lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.BLACK);
		lblTipo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTipo.setBounds(428, 414, 67, 36);
		contentPane.add(lblTipo);

		String[] tipoStrings = { "-", "Arma", "Armadura", "Herramienta" };
		comboTipo = new JComboBox<String>(tipoStrings);
		comboTipo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		comboTipo.setBounds(521, 416, 316, 34);
		contentPane.add(comboTipo);
		
		lblFiles = new JLabel("");
		lblFiles.setForeground(Color.BLACK);
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblFiles.setBounds(425, 523, 509, 18);
		contentPane.add(lblFiles);

		lblSaveChanges = new JLabel("");
		lblSaveChanges.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 32));
		lblSaveChanges.setBounds(306, 522, 316, 36);
		contentPane.add(lblSaveChanges);

		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoArsenal.jpg")));
		lblNewLabel_1_1.setBounds(-17, -179, 1283, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);

		btnBack.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnSave.addActionListener(this);
		btnUpload.addActionListener(this);
		comboTipo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		Arsenal a = new Arsenal();

		if (o == btnCancelar) {
			
			VManagement vM = new VManagement(c,dni);
			vM.setVisible(true);
			this.dispose();
		} else if (o == btnSave && textField.getText().equals("") || textField_2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce al menos los datos de id y nombre.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave && textArea.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce una descripción.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave && comboTipo.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(this, "Elige un tipo disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (o == btnUpload) {

			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			filtro = new FileNameExtensionFilter("Imágenes jpg", "jpg");
			fileChooser.addChoosableFileFilter(filtro);
			int opcion = fileChooser.showOpenDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				// si ha pulsado Aceptar
				file = fileChooser.getSelectedFile();
				lblFiles.setText("Ha elegido el archivo " + fileChooser.getSelectedFile());
			} else if (opcion == JFileChooser.CANCEL_OPTION) {
				// si ha pulsado Cancelar
				lblFiles.setText("Ha pulsado Cancelar");
			} else if (opcion == JFileChooser.ERROR_OPTION) {
				// si ha producido un Error
				lblFiles.setText("Se ha producido un Error.");
			}
		} else if (o == btnSave && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave) {
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea crear este artículo?");
			if (option == JOptionPane.YES_OPTION) {
				FileInputStream is = null;
				try {
					is = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Blob blob = null;
				try {
					blob = new Blob(is.readAllBytes(), null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				c.insertWeapon(Integer.parseInt(textField.getText()), blob, textField_2.getText(),
						(String) comboTipo.getSelectedItem(),textArea.getText());
				lblSaveChanges.setText("Cambios guardados.");
				btnBack.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnSave.setEnabled(false);
			} else if (option == JOptionPane.NO_OPTION) {

			} else if (option == JOptionPane.CANCEL_OPTION) {

			} else if (option == JOptionPane.CLOSED_OPTION) {

			}
		}else if (o == btnBack) {
			VManagement vM = new VManagement(c,dni);
			vM.setVisible(true);
			this.dispose();
		}
	}
}

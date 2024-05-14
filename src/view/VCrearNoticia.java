package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;
import controller.Controller;

public class VCrearNoticia extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private Controller controlador;
	private JButton btnUpload;
	private JButton btnSave;
	private JButton btnBack;
	private JButton btnCancelar;
	private JTextArea textDescripcion;
	private JLabel lblFondo;
	private JLabel lblID_Arsenal;
	private String dni;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JLabel lblSaveChanges;
	
	public VCrearNoticia(Controller controlador, String dni) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		setResizable(false);
		this.dni = dni;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VCrearArsenal.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCrearFichaDe = new JLabel("CREAR NOTICIA", SwingConstants.CENTER);
		lblCrearFichaDe.setForeground(Color.BLACK);
		lblCrearFichaDe.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblCrearFichaDe.setBounds(476, 96, 361, 36);
		contentPane.add(lblCrearFichaDe);

		JLabel lblName = new JLabel("Titulo:");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblName.setBounds(391, 207, 104, 36);
		contentPane.add(lblName);

		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setForeground(Color.BLACK);
		lblDesc.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblDesc.setBounds(346, 264, 149, 36);
		contentPane.add(lblDesc);


		btnUpload = new JButton("Subir Foto");
		btnUpload.setForeground(Color.BLACK);
		btnUpload.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnUpload.setBackground(new Color(116, 116, 116));
		btnUpload.setBounds(531, 490, 316, 23);
		contentPane.add(btnUpload);

		btnSave = new JButton("Guardar Cambios");

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

		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(521, 218, 316, 28);
		contentPane.add(textFieldTitulo);

		textDescripcion = new JTextArea();
		textDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		textDescripcion.setBounds(521, 276, 316, 123);
		contentPane.add(textDescripcion);

		lblID_Arsenal = new JLabel("");

		lblID_Arsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 32));
		lblID_Arsenal.setBounds(465, 21, 522, 36);
		contentPane.add(lblID_Arsenal);

		lblID_Arsenal.setText("El último id de noticia es: "+ controlador.returnMaxNews().getId_noticia());
		
		lblFiles = new JLabel("");
		lblFiles.setForeground(Color.BLACK);
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblFiles.setBounds(425, 523, 509, 18);
		contentPane.add(lblFiles);

		lblSaveChanges = new JLabel("");
		lblSaveChanges.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 32));
		lblSaveChanges.setBounds(306, 522, 316, 36);
		contentPane.add(lblSaveChanges);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblFondo.setBounds(-17, -179, 1283, 1135);
		lblFondo.setBorder(new RoundedBorder(20));
		contentPane.add(lblFondo);

		btnBack.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnSave.addActionListener(this);
		btnUpload.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object object = e.getSource();

		if (object == btnCancelar) {
			
			VManagement vM = new VManagement(controlador,dni);
			vM.setVisible(true);
			this.dispose();
		} else if (object == btnSave && textFieldTitulo.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce al menos los datos del título.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (object == btnSave && textDescripcion.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce una descripción.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (object == btnUpload) {

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
		} else if (object == btnSave && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (object == btnSave) {
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

				controlador.insertNew(controlador.returnMaxNews().getId_noticia() + 1, blob, textFieldTitulo.getText(),textDescripcion.getText() , dni);
				lblSaveChanges.setText("Cambios guardados.");
				btnBack.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnSave.setEnabled(false);
			} else if (option == JOptionPane.NO_OPTION) {

			} else if (option == JOptionPane.CANCEL_OPTION) {

			} else if (option == JOptionPane.CLOSED_OPTION) {

			}
		}else if (object == btnBack) {
			VManagement vM = new VManagement(controlador,dni);
			vM.setVisible(true);
			this.dispose();
		}
		
	}

}

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
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;
import controller.Controller;
import model.Criminal;


public class VModificarCriminal extends JFrame implements ActionListener {
	public VModificarCriminal() {
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnUpload;
	private JButton btnCrear;
	private JButton btnCancelar;
	private Controller c;
	private String dni;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JTextArea textArea;
	private Criminal crim;

	//Ventana para modificar perfil
	
	public VModificarCriminal(Controller c,String dni) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VModificarPerfilPolicia.class.getResource("/fotos/pixelart2.png")));
		this.c = c;
		this.dni = dni;
		//Ventana para modificar perfil
		crim = c.showCriminalByPolicemanAdmin(dni);
	
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo= new JLabel("Modifica el perfil:");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(469, 32, 262, 81);
		lblTitulo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 33));
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(469, 136, 84, 52);
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBounds(350, 323, 120, 52);
		lblDescripcion.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(lblDescripcion);


		btnUpload = new JButton("Subir Foto");
		btnUpload.setBounds(552, 484, 161, 52);
		btnUpload.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnUpload);

		btnCrear = new JButton("Modificar");
		btnCrear.setBounds(340, 484, 161, 52);
		btnCrear.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCrear);
		
	
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(762, 484, 161, 52);
		btnCancelar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		contentPane.add(btnCancelar);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtNombre.setBounds(563, 147, 136, 31);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblApellido.setBounds(469, 231, 84, 52);
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		txtApellido.setColumns(10);
		txtApellido.setBounds(563, 242, 136, 31);
		contentPane.add(txtApellido);
		
		lblFiles = new JLabel();
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblFiles.setBounds(552, 588, 136, 31);
		contentPane.add(lblFiles);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		textArea.setBounds(521, 301, 316, 123);
		contentPane.add(textArea);
	
		
		JLabel lblDni = new JLabel("DNI: " + this.dni);
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 19));
		lblDni.setBounds(469, 88, 464, 52);
		contentPane.add(lblDni);

		JLabel lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 10));
		lblFondo.setForeground(new Color(0, 0, 0));
		lblFondo.setIcon(new ImageIcon(VModificarPerfilPolicia.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFondo.setBounds(-14, -45, 1290, 893);
		contentPane.add(lblFondo);
		
		JLabel label = new JLabel("New label");
		label.setBounds(590, 592, 46, 14);
		contentPane.add(label);

		
		btnCancelar.addActionListener(this);
		btnUpload.addActionListener(this);
		btnCrear.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource().equals(btnCancelar)) {
			VManagement vm = new VManagement(c,dni);
			vm.setVisible(true);
			this.dispose();
		}if (e.getSource().equals(btnUpload)) {
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
		}else if (e.getSource().equals(btnCrear)&& lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}else if(e.getSource().equals(btnCrear) && txtNombre.getText().equals("") && txtApellido.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce el nombre y el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
		
		}else if(e.getSource().equals(btnCrear)) {
			
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
			int option = JOptionPane.showConfirmDialog(this,
					"¿Está seguro de que desea modificar el perfil?");
			if (option == JOptionPane.YES_OPTION) {
			c.updatePeople(txtNombre.getText(), txtApellido.getText(),Integer.toString((int) (Math.random() * 10000)), blob, dni);
			c.updateCriminal(textArea.getText(),crim.getDni());
			JOptionPane.showMessageDialog(this, "Perfil modificado correctamente", "Mensaje para el aministrador", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}

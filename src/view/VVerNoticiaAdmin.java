package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.Controller;
import model.News;

public class VVerNoticiaAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private JLabel lblFoto;
	private JButton btnAtras;
	private Blob aBlob;
	private News noticia;
	private JLabel lblTitulo;
	private JLabel lblDescripcion;
	private JLabel lblBienvenida;
	private String dni;
	private int id;

	public VVerNoticiaAdmin(Controller controlador, int id) {
		setResizable(false);
		this.id = id;
		this.controlador = controlador;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VNoticias.class.getResource("/fotos/pixelart2.png")));
		noticia = controlador.selectingNoticia(this.id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(530, 50, 1280, 720);

		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(306, 89, 697, 396);
		contentPane.add(lblFoto);

		lblTitulo = new JLabel("", SwingConstants.CENTER);

		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitulo.setBounds(255, 495, 755, 27);
		contentPane.add(lblTitulo);

		lblDescripcion = new JLabel("");
		lblDescripcion.setForeground(new Color(0, 0, 0));
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 17));
		lblDescripcion.setBounds(313, 532, 793, 43);

		contentPane.add(lblDescripcion);

		lblBienvenida = new JLabel("NOTICIAS");
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Dialog", Font.BOLD, 37));
		lblBienvenida.setBounds(561, 10, 210, 54);
		contentPane.add(lblBienvenida);

		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAtras.setBounds(561, 585, 167, 37);

		contentPane.add(btnAtras);

		aBlob = this.noticia.getFoto_noticia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),
					lblFoto.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lblTitulo.setText(this.noticia.getTitulo());
		lblDescripcion.setText(this.noticia.getDescripcion());

		JLabel lblFondo = new JLabel("");

		lblFondo.setIcon(new ImageIcon(new ImageIcon(VNoticias.class.getResource("/fotos/fondoNoticiasFinal.png"))
				.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));
		lblFondo.setBounds(0, -70, 1369, 853);

		contentPane.add(lblFondo);
		btnAtras.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if(o == btnAtras) {
			VManagement vm = new VManagement(controlador, dni);
			vm.setVisible(true);
			this.dispose();
		}
		
		
	}

}

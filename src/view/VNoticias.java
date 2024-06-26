package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.Controller;
import model.News;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;


public class VNoticias extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private JLabel lblFoto;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private ArrayList<News> news;
	private Blob aBlob;
	private News noticia;
	private JLabel lblTitulo;
	private JLabel lblDescripcion;
	private JLabel lblBienvenida;
	private int index;

	public VNoticias(Controller c) {
		setResizable(false);
		//con el código comentado volvemos a la ventana anterior al cerrar.
		/*addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				VEntrada ve = new VEntrada(c);
				ve.setVisible(true);

			}
		});
	*/
		setIconImage(Toolkit.getDefaultToolkit().getImage(VNoticias.class.getResource("/fotos/pixelart2.png")));

		this.controlador = c;

		setBounds(530, 50, 1280, 720);

		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

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
		lblDescripcion.setBounds(304, 532, 1019, 43);

		contentPane.add(lblDescripcion);

		lblBienvenida = new JLabel("NOTICIAS");
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Dialog", Font.BOLD, 37));
		lblBienvenida.setBounds(561, 10, 210, 54);
		contentPane.add(lblBienvenida);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnAnterior.setBounds(255, 585, 167, 37);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSiguiente.setBounds(850, 585, 167, 37);
		contentPane.add(btnSiguiente);

		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnAtras.setBounds(561, 585, 167, 37);
		contentPane.add(btnAtras);

		news = c.showNews();

		aBlob = c.showNews().get(0).getFoto_noticia();

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
		lblTitulo.setText(news.get(0).getTitulo());
		lblDescripcion.setText(news.get(0).getDescripcion());

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(VNoticias.class.getResource("/fotos/fondoNoticiasFinal.png"))
				.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(0, -70, 1369, 853);

		contentPane.add(lblNewLabel);

		btnAnterior.addActionListener(this);
		btnAtras.addActionListener(this);
		btnSiguiente.addActionListener(this);
		btnAnterior.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Intento de hacer el slideshow con la clase ListIterator
		// it = news.listIterator();
		/*
		 * if (it.nextIndex() > news.size()-1 && !it.hasNext()) {
		 * btnSiguiente.setEnabled(false); } else { btnSiguiente.setEnabled(true);
		 * ======= boolean siguiente = true; boolean anterior = true; /* n = new News();
		 * 
		 * 
		 * it = news.listIterator(); if (it.nextIndex() >= news.size() && !it.hasNext())
		 * { btnSiguiente.setEnabled(false); } else { btnSiguiente.setEnabled(true); }
		 * 
		 * if (it.nextIndex() == news.size()) { it.previous(); }
		 * 
		 * if (it.previousIndex() == -1) { it.next(); }
		 * 
		 * if (it.previousIndex() == -1 && !it.hasPrevious()) {
		 * btnAnterior.setEnabled(false); } else { btnAnterior.setEnabled(true); }
		 */

		// n = new News();

		// it = news.listIterator();
		/*
		 * if (it.nextIndex() > news.size()-2 && !it.hasNext()) {
		 * btnSiguiente.setEnabled(false); } else { btnSiguiente.setEnabled(true); }
		 * 
		 * if (it.nextIndex() == news.size()) { it.previous(); }
		 * 
		 * if (it.previousIndex() == -1) { it.next(); }
		 * 
		 * if (it.previousIndex() == -1 && !it.hasPrevious()) {
		 * btnAnterior.setEnabled(false); } else { btnAnterior.setEnabled(true); }
		 */

		
			// System.out.println("siguiente");

			if (e.getSource().equals(btnSiguiente)) {
				// System.out.println("siguiente");

				siguiente();

			}

			if (e.getSource().equals(btnAnterior)) {
				anterior();

			}
			if (e.getSource().equals(btnAtras)) {
				volver();
			}
		}
	

	private void siguiente() {
		// TODO Auto-generated method stub
		btnAnterior.setEnabled(true);

		if(index >= 0 && index <= news.size()) {
			index++;
			noticia = news.get(index);
			lblTitulo.setText(noticia.getTitulo());
			lblDescripcion.setText(noticia.getDescripcion());
			aBlob = noticia.getFoto_noticia();
			try {
				InputStream is;
				is = aBlob.getBinaryStream(1, aBlob.length());
				BufferedImage imag;
				imag = ImageIO.read(is);
				lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(index == news.size()-1) {
				btnSiguiente.setEnabled(false);
			}
		} else {
			btnSiguiente.setEnabled(false);
			btnAnterior.setEnabled(true);
		}

	}

	private void volver() {
		VEntrada ve = new VEntrada(controlador);
		ve.setVisible(true);
		this.dispose();
	}


	private void anterior() {
		
			if(index >= 0 && index > -1) {
				index--;
			noticia = news.get(index);
			lblTitulo.setText(noticia.getTitulo());
			lblDescripcion.setText(noticia.getDescripcion());
			aBlob = noticia.getFoto_noticia();
			btnSiguiente.setEnabled(true);
			try {
				InputStream is;
				is = aBlob.getBinaryStream(1, aBlob.length());
				BufferedImage imag;
				imag = ImageIO.read(is);
				lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			} catch (IOException e3) {
				e3.printStackTrace();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			if(index == 0) {
				btnAnterior.setEnabled(false);
			}
		}else {
			btnSiguiente.setEnabled(true);
			btnAnterior.setEnabled(false);
		}

	}
}
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.News;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

public class VNoticias extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private JLabel lblFoto;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private ArrayList<News> news;
	private Blob aBlob;
	private ListIterator<News> it;
	private News n;
	private JLabel lblTitulo;
	private JLabel lblDescripcion;
	private JLabel lblBienvenida;
	public VNoticias(Controller c) {

		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 884, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(180, 74, 455, 275);
		contentPane.add(lblFoto);
		
		lblTitulo = new JLabel("",SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblTitulo.setBounds(28, 359, 777, 27);
		contentPane.add(lblTitulo);
		
		lblDescripcion = new JLabel("");
		lblDescripcion.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblDescripcion.setBounds(108, 378, 793, 43);
		contentPane.add(lblDescripcion);
		
		lblBienvenida = new JLabel("NOTICIAS");
		lblBienvenida.setFont(new Font("Dialog", Font.BOLD, 37));
		lblBienvenida.setBounds(311, 10, 210, 54);
		contentPane.add(lblBienvenida);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAnterior.setBounds(80, 431, 167, 37);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSiguiente.setBounds(620, 431, 167, 37);
		contentPane.add(btnSiguiente);

		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAtras.setBounds(354, 431, 167, 37);
		contentPane.add(btnAtras);

		news = c.showNews();

		aBlob = c.showNews().get(0).getFoto_noticia();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lblTitulo.setText(news.get(0).getTitulo());
		lblDescripcion.setText(news.get(0).getDescripcion());
		
		btnAnterior.addActionListener(this);
		btnAtras.addActionListener(this);
		btnSiguiente.addActionListener(this);
		
		it = news.listIterator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		n = new News();
		Object o = e.getSource();
		
		if(it.nextIndex() >= news.size() && !it.hasNext()){
			btnSiguiente.setEnabled(false);
		}else {
			btnSiguiente.setEnabled(true);
		}
		
		if(it.nextIndex() == news.size()) {
			it.previous();
		}
		
		if(it.previousIndex() == -1) {
			it.next();
		}
		
		if(it.previousIndex() == -1 && !it.hasPrevious()){
			btnAnterior.setEnabled(false);
		}else {
			btnAnterior.setEnabled(true);
		}

		if (o == btnSiguiente) {
			if (it.hasNext()) {
				btnAnterior.setEnabled(true);
				n = it.next();
				lblTitulo.setText(n.getTitulo());
				lblDescripcion.setText(n.getDescripcion());
				aBlob = n.getFoto_noticia();
				try {
					InputStream is;
					is = aBlob.getBinaryStream(1, aBlob.length());
					BufferedImage imag;
					imag = ImageIO.read(is);
					lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		} else if (o == btnAnterior) {
			if (it.hasPrevious()) {
				n = it.previous();
				lblTitulo.setText(n.getTitulo());
				lblDescripcion.setText(n.getDescripcion());
				aBlob = n.getFoto_noticia();
				try {
					InputStream is;
					is = aBlob.getBinaryStream(1, aBlob.length());
					BufferedImage imag;
					imag = ImageIO.read(is);
					lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		}else if(o == btnAtras){
			VEntrada ve = new VEntrada(c);
			ve.setVisible(true);
			this.dispose();
		}
	}
	}


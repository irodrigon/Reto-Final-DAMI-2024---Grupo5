package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Arsenal;
import model.Elige;
import model.News;
import model.Policia;
import java.awt.Color;

public class VVerArsenal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller c;
	private String dni;
	private ArrayList<Arsenal> weapons;
	private ArrayList<Elige> elige;
	private ArrayList<Arsenal> weaponsAssigned;
	private JLabel lblFoto;
	private JLabel lblNombre;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblBienvenida;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private ListIterator<Arsenal> it;
	private Blob aBlob;
	private String pass;
	private JLabel lblNewLabel;
	private int index;

	public VVerArsenal(Controller c, String dni,String pass) {
		setResizable(false);
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		this.weapons = c.showArsenal();
		this.elige = c.weaponsAssigned(dni);
		weaponsAssigned = new ArrayList<Arsenal>();
		for(Arsenal w : weapons) {
			for(Elige el : elige) {
				if(el.getId_arsenal() == (w.getId_arsenal()) && dni.equals(el.getDni_policia())) {
					weaponsAssigned.add(w);
				}
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(440, 84, 455, 275);
		contentPane.add(lblFoto);
		
		lblNombre = new JLabel("Nombre: "+ weaponsAssigned.get(0).getNombre());
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNombre.setBounds(428, 398, 388, 27);
		contentPane.add(lblNombre);
		
		lblTipo = new JLabel("Tipo: "+ weaponsAssigned.get(0).getTipo());
		lblTipo.setForeground(new Color(0, 0, 0));
		lblTipo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTipo.setBounds(428, 435, 320, 27);
		contentPane.add(lblTipo); 
		
		lblDescripcion = new JLabel("Descripción: " +weaponsAssigned.get(0).getDescripcion());
		lblDescripcion.setForeground(new Color(0, 0, 0));
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 25));
		lblDescripcion.setBounds(428, 461, 740, 43);
		contentPane.add(lblDescripcion);
		
		lblBienvenida = new JLabel("ARSENAL ASIGNADO");
		lblBienvenida.setForeground(new Color(0, 0, 0));
		lblBienvenida.setFont(new Font("Dialog", Font.BOLD, 37));
		lblBienvenida.setBounds(457, 10, 438, 54);
		contentPane.add(lblBienvenida);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnterior.setBounds(233, 616, 167, 37);
		contentPane.add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSiguiente.setBounds(981, 616, 167, 37);
		contentPane.add(btnSiguiente);

		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(592, 616, 167, 37);
		contentPane.add(btnAtras);

		aBlob = weaponsAssigned.get(0).getFoto_arsenal();

		try {
			InputStream is;
			is = aBlob.getBinaryStream(1, aBlob.length());
			BufferedImage imag;
			imag = ImageIO.read(is);
			lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(), Image.SCALE_DEFAULT)));
			
			lblNewLabel = new JLabel("\r\n");
			lblNewLabel.setIcon(new ImageIcon(VVerArsenal.class.getResource("/fotos/fondoArsenal.jpg")));
			lblNewLabel.setBounds(0, -22, 1280, 720);
			contentPane.add(lblNewLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		btnAnterior.addActionListener(this);
		btnAtras.addActionListener(this);
		btnSiguiente.addActionListener(this);
		btnAnterior.setEnabled(false);
		
		//it = weaponsAssigned.listIterator();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			Arsenal a = new Arsenal();
			Object o = e.getSource();
			/*
			if(it.nextIndex() >= weaponsAssigned.size() && !it.hasNext()){
				btnSiguiente.setEnabled(false);
			}else {
				btnSiguiente.setEnabled(true);
			}
			
			if(it.nextIndex() == weaponsAssigned.size()) {
				it.previous();
			}
			
			if(it.previousIndex() == -1) {
				it.next();
			}
			
			if(it.previousIndex() == -1 && !it.hasPrevious()){
				btnAnterior.setEnabled(false);
			}else {
				btnAnterior.setEnabled(true);
			}*/

			if (o == btnSiguiente) {
				/*if (it.hasNext()) {
					btnAnterior.setEnabled(true);
					a = it.next();
					lblNombre.setText("Nombre: " + a.getNombre());
					lblTipo.setText("Tipo: " + a .getTipo());
					lblDescripcion.setText("Descripción: " + a.getDescripcion());
					aBlob = a.getFoto_arsenal();
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
					}*/
					
				btnAnterior.setEnabled(true);
				if(index >= 0 && index <= weaponsAssigned.size()) {
					index++;
					a = weaponsAssigned.get(index);
					lblNombre.setText(a.getNombre());
					lblDescripcion.setText(a.getDescripcion());
					lblTipo.setText(a.getTipo());
					aBlob = a.getFoto_arsenal();
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
					if(index == weaponsAssigned.size()-1) {
						btnSiguiente.setEnabled(false);
					}
				} else {
					btnSiguiente.setEnabled(false);
					btnAnterior.setEnabled(true);
				}
			
			} else if (o == btnAnterior) {
				/*if (it.hasPrevious()) {
					a= it.previous();
					lblNombre.setText("Nombre: " + a.getNombre());
					lblTipo.setText("Tipo: " + a .getTipo());
					lblDescripcion.setText("Descripción: " + a.getDescripcion());
					aBlob = a.getFoto_arsenal();
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
				}*/
				
				if(index >= 0 && index > -1) {
					index--;
					a = weaponsAssigned.get(index);
					lblNombre.setText(a.getNombre());
					lblDescripcion.setText(a.getDescripcion());
					lblTipo.setText(a.getTipo());
					aBlob = a.getFoto_arsenal();
					btnSiguiente.setEnabled(true);
					try {
						InputStream is;
						is = aBlob.getBinaryStream(1, aBlob.length());
						BufferedImage imag;
						imag = ImageIO.read(is);
						lblFoto.setIcon(new ImageIcon(new ImageIcon(imag).getImage().getScaledInstance(lblFoto.getWidth(),
								lblFoto.getHeight(), Image.SCALE_DEFAULT)));
					} catch (IOException e3) {
						// TODO Auto-generated catch block
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
			}else if(o == btnAtras){
				VPolicias vP = new VPolicias(c,dni,pass);
				vP.setVisible(true);
				this.dispose();
			}
		}
	}



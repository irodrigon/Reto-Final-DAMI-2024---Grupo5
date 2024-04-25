package view;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Arsenal;
import model.Criminal;
import model.News;
import model.Policia;
import controller.Controller;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VManagement extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private Controller contr;
	private ArrayList<Policia> policemen;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel model;
	private JButton btnSeeProfile;
	private JButton btnMdifyPoliceman;
	private JButton btnEliminarPolicia;
	private JButton btnBack;
	private JLabel lblNewLabel;
	private Policia p;
	private JLabel lblNewLabel_1_1;
	private DefaultTableModel model2;
	private JTable table2;
	private JScrollPane scroll2;
	private ArrayList<Arsenal> weapons;
	private JButton btnVerArsenal;
	private JButton btnMdifyWeapons;
	private JButton btnEliminarWeapons;
	private JButton btnBack2;
	private JLabel lblChanges;
	private JButton btnCreateArticle;
	private JLabel lblChanges_1;
	private JButton btnSeeProfile3;
	private JButton btnCreateSuspect;
	private JButton btnMdifySuspects;
	private JButton btnEliminarCriminal;
	private JButton btnBack3;
	private JLabel lblNewLabel3;
	private JLabel lblChanges_3;
	private DefaultTableModel model3;
	private JTable table3;
	private JScrollPane scroll3;
	private ArrayList<Criminal> suspects;
	private Criminal c;
	private String dni;
	private ArrayList<News> news;
	private JButton btnMdifyNew;
	private JButton btnEliminarNew;
	private JButton btnBack4;
	private JButton btnCreateNew;
	private JLabel lblChanges_4;
	private JButton btnSeeProfile4;
	private DefaultTableModel model4;
	private JTable table4;
	private JScrollPane scroll4;
	private JLabel lblNewLabel4;
	private News n;
	private JButton btnSeeProfile2;

	public VManagement(Controller contr, String dni) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VManagement.class.getResource("/fotos/pixelart2.png")));
		this.contr = contr;
		this.dni = dni;
		policemen = this.contr.showPolicemen();
		weapons = this.contr.showArsenal();
		suspects = this.contr.showCriminals();
		news = this.contr.showNews();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1007, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 1000, 1000);
		contentPane.add(tabbedPane);
		panel1 = new JPanel();
		panel1.setLayout(null);
		String[] columnNames = { "DNI:", "Nombre:", "Apellido:" };
		model = new DefaultTableModel(null, columnNames);
		for (int i = 0; i < policemen.size(); i++) {
			String dniPol = policemen.get(i).getDni();
			String nombre = policemen.get(i).getNombre();
			String apellido = policemen.get(i).getApellido();
			Object[] data = { dniPol, nombre, apellido };
			model.addRow(data);
		}
		table = new JTable(model);
		table.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table.setBackground(new Color(255, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		table.setBounds(10, 40, 964, 557);
		table.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table.getTableHeader().setBackground(new Color(116, 116, 116));
		table.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 40, 964, 557);
		panel1.add(scroll);
		tabbedPane.addTab("Policias", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panel1,
				"Pestaña de agentes");

		btnSeeProfile = new JButton("Ver Perfil de Policia");
		btnSeeProfile.setBackground(new Color(116, 116, 116));
		btnSeeProfile.setForeground(new Color(0, 0, 0));
		btnSeeProfile.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfile.setBounds(63, 654, 216, 23);
		panel1.add(btnSeeProfile);

		btnMdifyPoliceman = new JButton("Modificar policia");
		btnMdifyPoliceman.setBackground(new Color(116, 116, 116));
		btnMdifyPoliceman.setForeground(new Color(0, 0, 0));
		btnMdifyPoliceman.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyPoliceman.setBounds(397, 654, 216, 23);
		panel1.add(btnMdifyPoliceman);

		btnEliminarPolicia = new JButton("Eliminar policia");
		btnEliminarPolicia.setBackground(new Color(116, 116, 116));
		btnEliminarPolicia.setForeground(new Color(0, 0, 0));
		btnEliminarPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarPolicia.setBounds(733, 654, 216, 23);
		panel1.add(btnEliminarPolicia);

		btnBack = new JButton("Atrás");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setBounds(397, 716, 216, 23);
		panel1.add(btnBack);

		lblNewLabel = new JLabel("Seleccione un policía:", SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblNewLabel.setBounds(155, 0, 625, 25);
		panel1.add(lblNewLabel);

		lblChanges = new JLabel("");
		lblChanges.setForeground(new Color(0, 0, 0));
		lblChanges.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChanges.setBounds(288, 896, 464, 37);
		panel1.add(lblChanges);

		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblNewLabel_1_1.setBounds(-60, -73, 1076, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		panel1.add(lblNewLabel_1_1);

		btnSeeProfile.addActionListener(this);
		btnMdifyPoliceman.addActionListener(this);
		btnEliminarPolicia.addActionListener(this);
		btnBack.addActionListener(this);

		panel2 = new JPanel();
		tabbedPane.addTab("Arsenal", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panel2,
				"Pestaña de arsenal");

		panel2.setLayout(null);
		String[] columnNames2 = { "Nombre:", "Descripcion" };
		model2 = new DefaultTableModel(null, columnNames2);
		for (int i = 0; i < weapons.size(); i++) {
			String nombre = weapons.get(i).getNombre();
			String descripcion = weapons.get(i).getDescripcion();
			Object[] data = { nombre, descripcion };
			model2.addRow(data);
		}
		table2 = new JTable(model2);
		table2.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table2.setBackground(new Color(255, 255, 255));
		table2.setForeground(new Color(0, 0, 0));
		table2.setBounds(10, 40, 964, 557);
		table2.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table2.getTableHeader().setBackground(new Color(116, 116, 116));
		table2.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll2 = new JScrollPane(table2);
		scroll2.setBounds(10, 40, 964, 557);
		panel2.add(scroll2);

		btnSeeProfile2 = new JButton("Ver Perfil de arsenal");
		btnSeeProfile2.setBackground(new Color(116, 116, 116));
		btnSeeProfile2.setForeground(new Color(0, 0, 0));
		btnSeeProfile2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfile2.setBounds(10, 654, 216, 23);
		panel2.add(btnSeeProfile2);

		btnCreateArticle = new JButton("Crear Perfil de artículo");
		btnCreateArticle.setForeground(Color.BLACK);
		btnCreateArticle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateArticle.setBackground(new Color(116, 116, 116));
		btnCreateArticle.setBounds(255, 654, 216, 23);
		panel2.add(btnCreateArticle);

		btnMdifyWeapons = new JButton("Modificar artículo");
		btnMdifyWeapons.setBackground(new Color(116, 116, 116));
		btnMdifyWeapons.setForeground(new Color(0, 0, 0));
		btnMdifyWeapons.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyWeapons.setBounds(507, 654, 216, 23);
		panel2.add(btnMdifyWeapons);

		btnEliminarWeapons = new JButton("Eliminar artículo");
		btnEliminarWeapons.setBackground(new Color(116, 116, 116));
		btnEliminarWeapons.setForeground(new Color(0, 0, 0));
		btnEliminarWeapons.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarWeapons.setBounds(758, 654, 216, 23);
		panel2.add(btnEliminarWeapons);

		btnBack2 = new JButton("Atrás");
		btnBack2.setForeground(new Color(0, 0, 0));
		btnBack2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack2.setBackground(new Color(116, 116, 116));
		btnBack2.setBounds(397, 716, 216, 23);
		panel2.add(btnBack2);

		lblNewLabel = new JLabel("Seleccione un artículo:", SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblNewLabel.setBounds(155, 0, 625, 25);
		panel2.add(lblNewLabel);

		lblChanges_1 = new JLabel("");
		lblChanges_1.setForeground(Color.BLACK);
		lblChanges_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChanges_1.setBounds(269, 896, 464, 37);
		panel2.add(lblChanges_1);

		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		panel2.add(lblNewLabel_1_1);

		btnSeeProfile2.addActionListener(this);

		btnBack2.addActionListener(this);
		btnMdifyWeapons.addActionListener(this);
		btnEliminarWeapons.addActionListener(this);
		btnCreateArticle.addActionListener(this);
		btnSeeProfile2.addActionListener(this);

		panel3 = new JPanel();
		tabbedPane.addTab("Criminales", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panel3,
				"Pestaña de criminales");
		panel3.setLayout(null);
		String[] columnNames3 = { "DNI:", "Nombre:", "Apellido:" };
		model3 = new DefaultTableModel(null, columnNames3);
		for (int i = 0; i < suspects.size(); i++) {
			String dniCrim = suspects.get(i).getDni();
			String nombre = suspects.get(i).getNombre();
			String apellido = suspects.get(i).getApellido();
			Object[] data = { dniCrim, nombre, apellido };
			model3.addRow(data);
		}
		table3 = new JTable(model3);
		table3.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table3.setBackground(new Color(255, 255, 255));
		table3.setForeground(new Color(0, 0, 0));
		table3.setBounds(10, 40, 964, 557);
		table3.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table3.getTableHeader().setBackground(new Color(116, 116, 116));
		table3.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll3 = new JScrollPane(table3);
		scroll3.setBounds(10, 40, 964, 557);
		panel3.add(scroll3);

		btnSeeProfile3 = new JButton("Ver Perfil de criminal");
		btnSeeProfile3.setBackground(new Color(116, 116, 116));
		btnSeeProfile3.setForeground(new Color(0, 0, 0));
		btnSeeProfile3.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfile3.setBounds(10, 654, 216, 23);
		panel3.add(btnSeeProfile3);

		btnCreateSuspect = new JButton("Crear Perfil de criminal");
		btnCreateSuspect.setForeground(Color.BLACK);
		btnCreateSuspect.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateSuspect.setBackground(new Color(116, 116, 116));
		btnCreateSuspect.setBounds(257, 654, 216, 23);
		panel3.add(btnCreateSuspect);

		btnMdifySuspects = new JButton("Modificar perfil");
		btnMdifySuspects.setBackground(new Color(116, 116, 116));
		btnMdifySuspects.setForeground(new Color(0, 0, 0));
		btnMdifySuspects.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifySuspects.setBounds(507, 654, 216, 23);
		panel3.add(btnMdifySuspects);

		btnEliminarCriminal = new JButton("Eliminar perfil");
		btnEliminarCriminal.setBackground(new Color(116, 116, 116));
		btnEliminarCriminal.setForeground(new Color(0, 0, 0));
		btnEliminarCriminal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarCriminal.setBounds(758, 654, 216, 23);
		panel3.add(btnEliminarCriminal);

		btnBack3 = new JButton("Atrás");
		btnBack3.setForeground(new Color(0, 0, 0));
		btnBack3.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack3.setBackground(new Color(116, 116, 116));
		btnBack3.setBounds(397, 716, 216, 23);
		panel3.add(btnBack3);

		lblNewLabel3 = new JLabel("Seleccione un perfil:", SwingConstants.CENTER);
		lblNewLabel3.setForeground(new Color(0, 0, 0));
		lblNewLabel3.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblNewLabel3.setBounds(155, 0, 625, 25);
		panel3.add(lblNewLabel3);

		lblChanges_3 = new JLabel("");
		lblChanges_3.setForeground(Color.BLACK);
		lblChanges_3.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChanges_3.setBounds(269, 896, 464, 37);
		panel3.add(lblChanges_3);

		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		panel3.add(lblNewLabel_1_1);

		btnSeeProfile3.addActionListener(this);
		btnBack3.addActionListener(this);
		btnMdifySuspects.addActionListener(this);
		btnEliminarCriminal.addActionListener(this);
		btnCreateSuspect.addActionListener(this);

		panel4 = new JPanel();
		tabbedPane.addTab("Noticias", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panel4,
				"Pestaña de noticias");
		panel4.setLayout(null);
		String[] columnNames4 = { "Título:", "Descripción:" };
		model4 = new DefaultTableModel(null, columnNames4);
		for (int i = 0; i < news.size(); i++) {
			String titulo = news.get(i).getTitulo();
			String descripcion = news.get(i).getDescripcion();
			Object[] data = { titulo, descripcion };
			model4.addRow(data);
		}
		table4 = new JTable(model4);
		table4.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table4.setBackground(new Color(255, 255, 255));
		table4.setForeground(new Color(0, 0, 0));
		table4.setBounds(10, 40, 964, 557);
		table4.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table4.getTableHeader().setBackground(new Color(116, 116, 116));
		table4.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll4 = new JScrollPane(table4);
		scroll4.setBounds(10, 40, 964, 557);
		panel4.add(scroll4);

		btnSeeProfile4 = new JButton("Ver Noticia");
		btnSeeProfile4.setBackground(new Color(116, 116, 116));
		btnSeeProfile4.setForeground(new Color(0, 0, 0));
		btnSeeProfile4.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfile4.setBounds(10, 654, 216, 23);
		panel4.add(btnSeeProfile4);

		btnCreateNew = new JButton("Crear Noticia");
		btnCreateNew.setForeground(Color.BLACK);
		btnCreateNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateNew.setBackground(new Color(116, 116, 116));
		btnCreateNew.setBounds(255, 654, 216, 23);
		panel4.add(btnCreateNew);

		btnMdifyNew = new JButton("Modificar Noticia");
		btnMdifyNew.setBackground(new Color(116, 116, 116));
		btnMdifyNew.setForeground(new Color(0, 0, 0));
		btnMdifyNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyNew.setBounds(507, 654, 216, 23);
		panel4.add(btnMdifyNew);

		btnEliminarNew = new JButton("Eliminar Noticia");
		btnEliminarNew.setBackground(new Color(116, 116, 116));
		btnEliminarNew.setForeground(new Color(0, 0, 0));
		btnEliminarNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarNew.setBounds(758, 654, 216, 23);
		panel4.add(btnEliminarNew);

		btnBack4 = new JButton("Atrás");
		btnBack4.setForeground(new Color(0, 0, 0));
		btnBack4.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack4.setBackground(new Color(116, 116, 116));
		btnBack4.setBounds(397, 716, 216, 23);
		panel4.add(btnBack4);

		lblNewLabel4 = new JLabel("Seleccione una noticia:", SwingConstants.CENTER);
		lblNewLabel4.setForeground(new Color(0, 0, 0));
		lblNewLabel4.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblNewLabel4.setBounds(155, 0, 625, 25);
		panel4.add(lblNewLabel4);

		lblChanges_4 = new JLabel("");
		lblChanges_4.setForeground(Color.BLACK);
		lblChanges_4.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChanges_4.setBounds(269, 896, 464, 37);
		panel4.add(lblChanges_4);

		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		panel4.add(lblNewLabel_1_1);

		btnSeeProfile4.addActionListener(this);
		btnBack4.addActionListener(this);
		btnMdifyNew.addActionListener(this);
		btnEliminarNew.addActionListener(this);
		btnCreateNew.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (o == btnSeeProfile) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un policia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Policia p = new Policia();
				p = contr.returnPolicemanById((String) table.getValueAt(table.getSelectedRow(), 0));
				VVerPoliciaAdmin vvpa = new VVerPoliciaAdmin(contr, dni, p.getDni());
				vvpa.setVisible(true);
				this.dispose();
			}

		} else if (o == btnEliminarPolicia) {
			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un policía en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Policia p = new Policia();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este policía?");
				if (option == JOptionPane.YES_OPTION) {
					p = contr.returnPolicemanById((String) table.getValueAt(table.getSelectedRow(), 0));
					contr.deletePoliceman(p.getDni());
					contr.deletePoliceman2(p.getDni());
					JOptionPane.showMessageDialog(this,
							"Policía eliminado correctamente. No podrá volver a ver este policía.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					model.removeRow(table.getSelectedRow());
				}

			}
		} else if (o == btnBack) {
			VEntrada ve = new VEntrada(contr);
			ve.setVisible(true);
			this.dispose();
		} else if (o == btnBack2) {
			VEntrada ve = new VEntrada(contr);
			ve.setVisible(true);
			this.dispose();
		} else if (o == btnBack3) {
			VEntrada ve = new VEntrada(contr);
			ve.setVisible(true);
			this.dispose();
		} else if (o == btnBack4) {
			VEntrada ve = new VEntrada(contr);
			ve.setVisible(true);
			this.dispose();
		} else if (o == btnCreateArticle) {
			VCrearArsenal vca = new VCrearArsenal(contr, dni);
			vca.setVisible(true);
			this.dispose();
		} else if (o == btnEliminarWeapons) {
			if (table2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Arsenal a = new Arsenal();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este artículo?");
				if (option == JOptionPane.YES_OPTION) {
					a = contr.returnWeaponByName((String) table2.getValueAt(table2.getSelectedRow(), 0));
					contr.deleteWeapon(a.getId_arsenal());
					JOptionPane.showMessageDialog(this,
							"Artículo eliminado correctamente. No podrá volver a ver este artículo.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					model2.removeRow(table2.getSelectedRow());
				}

			}
		} else if (o == btnSeeProfile2) {
			if (table2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Arsenal a = new Arsenal();
				a = contr.returnWeaponByName((String) table2.getValueAt(table2.getSelectedRow(), 0));
				VVerArsenalAdmin vvaa = new VVerArsenalAdmin(contr, a.getNombre(), dni);
				vvaa.setVisible(true);
				this.dispose();
			}
		} else if (o == btnSeeProfile3) {
			if (table3.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un criminal en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Criminal cr = new Criminal();
				cr = contr.showCriminalByPolicemanAdmin((String) table3.getValueAt(table3.getSelectedRow(), 0));
				VVerCriminalAdmin vvca = new VVerCriminalAdmin(contr, dni, cr.getDni());
				vvca.setVisible(true);
				this.dispose();

			}

		} else if (o == btnEliminarCriminal) {

			if (table3.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un criminal en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Criminal crim = new Criminal();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este perfil?");
				if (option == JOptionPane.YES_OPTION) {
					crim = contr.showCriminalByPolicemanAdmin((String) table3.getValueAt(table3.getSelectedRow(), 0));
					contr.deletePoliceman(crim.getDni());
					contr.deletePoliceman2(crim.getDni());
					JOptionPane.showMessageDialog(this,
							"Criminal eliminado correctamente. No podrá volver a ver este criminal.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					model3.removeRow(table3.getSelectedRow());
				}

			}
		} else if (o == btnSeeProfile4) {
			if (table4.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una noticia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				News n = new News();
				n = contr.returnNews((String) table4.getValueAt(table4.getSelectedRow(), 0));
				VVerNoticiaAdmin vna = new VVerNoticiaAdmin(contr, n.getTitulo());
				vna.setVisible(true);
				this.dispose();

			}
		} else if (o == btnEliminarNew) {
			if (table4.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una noticia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				News n = new News();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta noticia?");
				if (option == JOptionPane.YES_OPTION) {
					n = contr.returnNews((String) table4.getValueAt(table4.getSelectedRow(), 0));
					contr.deleteNew(n.getId_noticia());

					JOptionPane.showMessageDialog(this,
							"Noticia eliminada correctamente. No podrá volver a ver esta noticia.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					model4.removeRow(table4.getSelectedRow());
				}
			}

		} else if (o == btnCreateSuspect) {
			VCrearCriminal vcc = new VCrearCriminal(contr, dni);
			vcc.setVisible(true);
			this.dispose();
		}
	}

}

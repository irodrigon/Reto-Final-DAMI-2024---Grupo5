package view;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;
import model.Arsenal;
import model.Criminal;
import model.News;
import model.Policia;
import controller.Controller;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VManagement extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelPolicias;
	private JPanel panelArsenal;
	private JPanel panelCriminales;
	private JPanel panelNoticias;
	private Controller controlador;
	private ArrayList<Policia> policemen;
	private JTable tablePolicias;
	private JScrollPane scrollPolicias;
	private DefaultTableModel modelPolicias;
	private JButton btnSeeProfilePolicia;
	private JButton btnMdifyPoliceman;
	private JButton btnEliminarPolicia;
	private JButton btnBackPolicia;
	private JLabel lblTituloPolicias;
	private JLabel lblFondoPolicias;
	private DefaultTableModel modelArsenal;
	private JTable tableArsenal;
	private JScrollPane scrollArsenal;
	private ArrayList<Arsenal> weapons;
	private JButton btnCrearPolicia;
	private JButton btnMdifyWeapons;
	private JButton btnEliminarWeapons;
	private JButton btnBackArsenal;
	private JLabel lblChangesPolicia;
	private JButton btnCreateArticle;
	private JLabel lblChangesArsenal;
	private JButton btnSeeProfileCriminal;
	private JButton btnCreateSuspect;
	private JButton btnMdifySuspects;
	private JButton btnEliminarCriminal;
	private JButton btnBackCriminales;
	private JLabel lblTituloCriminales;
	private JLabel lblChangesCriminales;
	private DefaultTableModel modelCriminales;
	private JTable tableCriminales;
	private JScrollPane scrollCriminales;
	private ArrayList<Criminal> suspects;
	private String dni;
	private ArrayList<News> news;
	private JButton btnMdifyNew;
	private JButton btnEliminarNew;
	private JButton btnBackNoticias;
	private JButton btnCreateNew;
	private JLabel lblChangesNoticias;
	private JButton btnSeeProfileNoticia;
	private DefaultTableModel modelNoticias;
	private JTable tableNoticias;
	private JScrollPane scrollNoticias;
	private JLabel lblTituloNoticias;
	private JButton btnSeeProfileArsenal;
	private JLabel lblTituloArsenal;
	private JLabel lblFondoArsenal;
	private JLabel lblFondoCriminales;
	private JLabel lblFondoNoticias;

	public VManagement(Controller controlador, String dni) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VManagement.class.getResource("/fotos/pixelart2.png")));
		this.controlador = controlador;
		this.dni = dni;
		policemen = this.controlador.showPolicemen();
		weapons = this.controlador.showArsenal();
		suspects = this.controlador.showCriminals();
		news = this.controlador.showNews();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1007, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 1000, 1000);
		contentPane.add(tabbedPane);
		panelPolicias = new JPanel();
		panelPolicias.setLayout(null);
		String[] columnNames = { "DNI:", "Nombre:", "Apellido:" };
		modelPolicias = new DefaultTableModel(null, columnNames);
		for (int i = 0; i < policemen.size(); i++) {
			String dniPol = policemen.get(i).getDni();
			String nombre = policemen.get(i).getNombre();
			String apellido = policemen.get(i).getApellido();
			Object[] data = { dniPol, nombre, apellido };
			modelPolicias.addRow(data);
		}
		tablePolicias = new JTable(modelPolicias);
		tablePolicias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tablePolicias.setBackground(new Color(255, 255, 255));
		tablePolicias.setForeground(new Color(0, 0, 0));
		tablePolicias.setBounds(10, 40, 964, 557);
		tablePolicias.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tablePolicias.getTableHeader().setBackground(new Color(116, 116, 116));
		tablePolicias.getTableHeader().setForeground(new Color(0, 0, 0));
		scrollPolicias = new JScrollPane(tablePolicias);
		scrollPolicias.setBounds(10, 40, 964, 557);
		panelPolicias.add(scrollPolicias);
		tabbedPane.addTab("Policias", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panelPolicias,
				"Pestaña de agentes");

		btnCrearPolicia = new JButton("Crear Perfil de Policía");
		btnCrearPolicia.setBackground(new Color(116, 116, 116));
		btnCrearPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCrearPolicia.setBounds(295, 655, 200, 21);
		panelPolicias.add(btnCrearPolicia);

		btnSeeProfilePolicia = new JButton("Ver Perfil de Policia");
		btnSeeProfilePolicia.setBackground(new Color(116, 116, 116));
		btnSeeProfilePolicia.setForeground(new Color(0, 0, 0));
		btnSeeProfilePolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfilePolicia.setBounds(63, 654, 178, 23);
		panelPolicias.add(btnSeeProfilePolicia);

		btnMdifyPoliceman = new JButton("Modificar policia");
		btnMdifyPoliceman.setBackground(new Color(116, 116, 116));
		btnMdifyPoliceman.setForeground(new Color(0, 0, 0));
		btnMdifyPoliceman.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyPoliceman.setBounds(558, 654, 170, 23);
		panelPolicias.add(btnMdifyPoliceman);

		btnEliminarPolicia = new JButton("Eliminar policia");
		btnEliminarPolicia.setBackground(new Color(116, 116, 116));
		btnEliminarPolicia.setForeground(new Color(0, 0, 0));
		btnEliminarPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarPolicia.setBounds(793, 654, 156, 23);
		panelPolicias.add(btnEliminarPolicia);

		btnBackPolicia = new JButton("Atrás");
		btnBackPolicia.setForeground(new Color(0, 0, 0));
		btnBackPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBackPolicia.setBackground(new Color(116, 116, 116));
		btnBackPolicia.setBounds(397, 716, 216, 23);
		panelPolicias.add(btnBackPolicia);

		lblTituloPolicias = new JLabel("Seleccione un policía:", SwingConstants.CENTER);
		lblTituloPolicias.setForeground(new Color(0, 0, 0));
		lblTituloPolicias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTituloPolicias.setBounds(155, 0, 625, 25);
		panelPolicias.add(lblTituloPolicias);

		lblChangesPolicia = new JLabel("");
		lblChangesPolicia.setForeground(new Color(0, 0, 0));
		lblChangesPolicia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChangesPolicia.setBounds(288, 896, 464, 37);
		panelPolicias.add(lblChangesPolicia);

		lblFondoPolicias = new JLabel("");
		lblFondoPolicias.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblFondoPolicias.setBounds(-60, -73, 1076, 1135);
		lblFondoPolicias.setBorder(new RoundedBorder(20));
		panelPolicias.add(lblFondoPolicias);

		btnSeeProfilePolicia.addActionListener(this);
		btnMdifyPoliceman.addActionListener(this);
		btnEliminarPolicia.addActionListener(this);
		btnBackPolicia.addActionListener(this);
		btnCrearPolicia.addActionListener(this);

		panelArsenal = new JPanel();
		tabbedPane.addTab("Arsenal", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panelArsenal,
				"Pestaña de arsenal");

		panelArsenal.setLayout(null);
		String[] columnNames2 = { "Nombre:", "Descripcion" };
		modelArsenal = new DefaultTableModel(null, columnNames2);
		for (int i = 0; i < weapons.size(); i++) {
			String nombre = weapons.get(i).getNombre();
			String descripcion = weapons.get(i).getDescripcion();
			Object[] data = { nombre, descripcion };
			modelArsenal.addRow(data);
		}
		tableArsenal = new JTable(modelArsenal);
		tableArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableArsenal.setBackground(new Color(255, 255, 255));
		tableArsenal.setForeground(new Color(0, 0, 0));
		tableArsenal.setBounds(10, 40, 964, 557);
		tableArsenal.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableArsenal.getTableHeader().setBackground(new Color(116, 116, 116));
		tableArsenal.getTableHeader().setForeground(new Color(0, 0, 0));
		scrollArsenal = new JScrollPane(tableArsenal);
		scrollArsenal.setBounds(10, 40, 964, 557);
		panelArsenal.add(scrollArsenal);

		btnSeeProfileArsenal = new JButton("Ver Perfil de arsenal");
		btnSeeProfileArsenal.setBackground(new Color(116, 116, 116));
		btnSeeProfileArsenal.setForeground(new Color(0, 0, 0));
		btnSeeProfileArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfileArsenal.setBounds(10, 654, 216, 23);
		panelArsenal.add(btnSeeProfileArsenal);

		btnCreateArticle = new JButton("Crear Perfil de artículo");
		btnCreateArticle.setForeground(Color.BLACK);
		btnCreateArticle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateArticle.setBackground(new Color(116, 116, 116));
		btnCreateArticle.setBounds(255, 654, 216, 23);
		panelArsenal.add(btnCreateArticle);

		btnMdifyWeapons = new JButton("Modificar artículo");
		btnMdifyWeapons.setBackground(new Color(116, 116, 116));
		btnMdifyWeapons.setForeground(new Color(0, 0, 0));
		btnMdifyWeapons.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyWeapons.setBounds(507, 654, 216, 23);
		panelArsenal.add(btnMdifyWeapons);

		btnEliminarWeapons = new JButton("Eliminar artículo");
		btnEliminarWeapons.setBackground(new Color(116, 116, 116));
		btnEliminarWeapons.setForeground(new Color(0, 0, 0));
		btnEliminarWeapons.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarWeapons.setBounds(758, 654, 216, 23);
		panelArsenal.add(btnEliminarWeapons);

		btnBackArsenal = new JButton("Atrás");
		btnBackArsenal.setForeground(new Color(0, 0, 0));
		btnBackArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBackArsenal.setBackground(new Color(116, 116, 116));
		btnBackArsenal.setBounds(397, 716, 216, 23);
		panelArsenal.add(btnBackArsenal);

		lblTituloArsenal = new JLabel("Seleccione un artículo:", SwingConstants.CENTER);
		lblTituloArsenal.setForeground(new Color(0, 0, 0));
		lblTituloArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTituloArsenal.setBounds(155, 0, 625, 25);
		panelArsenal.add(lblTituloArsenal);

		lblChangesArsenal = new JLabel("");
		lblChangesArsenal.setForeground(Color.BLACK);
		lblChangesArsenal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChangesArsenal.setBounds(269, 896, 464, 37);
		panelArsenal.add(lblChangesArsenal);

		lblFondoArsenal = new JLabel("");
		lblFondoArsenal.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblFondoArsenal.setBounds(-60, -73, 1066, 1135);
		lblFondoArsenal.setBorder(new RoundedBorder(20));
		panelArsenal.add(lblFondoArsenal);

		btnBackArsenal.addActionListener(this);
		btnMdifyWeapons.addActionListener(this);
		btnEliminarWeapons.addActionListener(this);
		btnCreateArticle.addActionListener(this);
		btnSeeProfileArsenal.addActionListener(this);

		panelCriminales = new JPanel();
		tabbedPane.addTab("Criminales", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panelCriminales,
				"Pestaña de criminales");
		panelCriminales.setLayout(null);
		String[] columnNames3 = { "DNI:", "Nombre:", "Apellido:" };
		modelCriminales = new DefaultTableModel(null, columnNames3);
		for (int i = 0; i < suspects.size(); i++) {
			String dniCrim = suspects.get(i).getDni();
			String nombre = suspects.get(i).getNombre();
			String apellido = suspects.get(i).getApellido();
			Object[] data = { dniCrim, nombre, apellido };
			modelCriminales.addRow(data);
		}
		tableCriminales = new JTable(modelCriminales);
		tableCriminales.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableCriminales.setBackground(new Color(255, 255, 255));
		tableCriminales.setForeground(new Color(0, 0, 0));
		tableCriminales.setBounds(10, 40, 964, 557);
		tableCriminales.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableCriminales.getTableHeader().setBackground(new Color(116, 116, 116));
		tableCriminales.getTableHeader().setForeground(new Color(0, 0, 0));
		scrollCriminales = new JScrollPane(tableCriminales);
		scrollCriminales.setBounds(10, 40, 964, 557);
		panelCriminales.add(scrollCriminales);

		btnSeeProfileCriminal = new JButton("Ver Perfil de criminal");
		btnSeeProfileCriminal.setBackground(new Color(116, 116, 116));
		btnSeeProfileCriminal.setForeground(new Color(0, 0, 0));
		btnSeeProfileCriminal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfileCriminal.setBounds(10, 654, 216, 23);
		panelCriminales.add(btnSeeProfileCriminal);

		btnCreateSuspect = new JButton("Crear Perfil de criminal");
		btnCreateSuspect.setForeground(Color.BLACK);
		btnCreateSuspect.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateSuspect.setBackground(new Color(116, 116, 116));
		btnCreateSuspect.setBounds(257, 654, 216, 23);
		panelCriminales.add(btnCreateSuspect);

		btnMdifySuspects = new JButton("Modificar perfil");
		btnMdifySuspects.setBackground(new Color(116, 116, 116));
		btnMdifySuspects.setForeground(new Color(0, 0, 0));
		btnMdifySuspects.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifySuspects.setBounds(507, 654, 216, 23);
		panelCriminales.add(btnMdifySuspects);

		btnEliminarCriminal = new JButton("Eliminar perfil");
		btnEliminarCriminal.setBackground(new Color(116, 116, 116));
		btnEliminarCriminal.setForeground(new Color(0, 0, 0));
		btnEliminarCriminal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarCriminal.setBounds(758, 654, 216, 23);
		panelCriminales.add(btnEliminarCriminal);

		btnBackCriminales = new JButton("Atrás");
		btnBackCriminales.setForeground(new Color(0, 0, 0));
		btnBackCriminales.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBackCriminales.setBackground(new Color(116, 116, 116));
		btnBackCriminales.setBounds(397, 716, 216, 23);
		panelCriminales.add(btnBackCriminales);

		lblTituloCriminales = new JLabel("Seleccione un perfil:", SwingConstants.CENTER);
		lblTituloCriminales.setForeground(new Color(0, 0, 0));
		lblTituloCriminales.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTituloCriminales.setBounds(155, 0, 625, 25);
		panelCriminales.add(lblTituloCriminales);

		lblChangesCriminales = new JLabel("");
		lblChangesCriminales.setForeground(Color.BLACK);
		lblChangesCriminales.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChangesCriminales.setBounds(269, 896, 464, 37);
		panelCriminales.add(lblChangesCriminales);

		lblFondoCriminales = new JLabel("");
		lblFondoCriminales.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblFondoCriminales.setBounds(-60, -73, 1066, 1135);
		lblFondoCriminales.setBorder(new RoundedBorder(20));
		panelCriminales.add(lblFondoCriminales);

		btnSeeProfileCriminal.addActionListener(this);
		btnBackCriminales.addActionListener(this);
		btnMdifySuspects.addActionListener(this);
		btnEliminarCriminal.addActionListener(this);
		btnCreateSuspect.addActionListener(this);

		panelNoticias = new JPanel();
		tabbedPane.addTab("Noticias", new ImageIcon(VManagement.class.getResource("/fotos/pixelart2.png")), panelNoticias,
				"Pestaña de noticias");
		panelNoticias.setLayout(null);
		String[] columnNames4 = { "Título:", "Descripción:" };
		modelNoticias = new DefaultTableModel(null, columnNames4);
		for (int i = 0; i < news.size(); i++) {
			String titulo = news.get(i).getTitulo();
			String descripcion = news.get(i).getDescripcion();
			Object[] data = { titulo, descripcion };
			modelNoticias.addRow(data);
		}
		tableNoticias = new JTable(modelNoticias);
		tableNoticias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableNoticias.setBackground(new Color(255, 255, 255));
		tableNoticias.setForeground(new Color(0, 0, 0));
		tableNoticias.setBounds(10, 40, 964, 557);
		tableNoticias.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		tableNoticias.getTableHeader().setBackground(new Color(116, 116, 116));
		tableNoticias.getTableHeader().setForeground(new Color(0, 0, 0));
		scrollNoticias = new JScrollPane(tableNoticias);
		scrollNoticias.setBounds(10, 40, 964, 557);
		panelNoticias.add(scrollNoticias);

		btnSeeProfileNoticia = new JButton("Ver Noticia");
		btnSeeProfileNoticia.setBackground(new Color(116, 116, 116));
		btnSeeProfileNoticia.setForeground(new Color(0, 0, 0));
		btnSeeProfileNoticia.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnSeeProfileNoticia.setBounds(10, 654, 216, 23);
		panelNoticias.add(btnSeeProfileNoticia);

		btnCreateNew = new JButton("Crear Noticia");
		btnCreateNew.setForeground(Color.BLACK);
		btnCreateNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCreateNew.setBackground(new Color(116, 116, 116));
		btnCreateNew.setBounds(255, 654, 216, 23);
		panelNoticias.add(btnCreateNew);

		btnMdifyNew = new JButton("Modificar Noticia");
		btnMdifyNew.setBackground(new Color(116, 116, 116));
		btnMdifyNew.setForeground(new Color(0, 0, 0));
		btnMdifyNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnMdifyNew.setBounds(507, 654, 216, 23);
		panelNoticias.add(btnMdifyNew);

		btnEliminarNew = new JButton("Eliminar Noticia");
		btnEliminarNew.setBackground(new Color(116, 116, 116));
		btnEliminarNew.setForeground(new Color(0, 0, 0));
		btnEliminarNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnEliminarNew.setBounds(758, 654, 216, 23);
		panelNoticias.add(btnEliminarNew);

		btnBackNoticias = new JButton("Atrás");
		btnBackNoticias.setForeground(new Color(0, 0, 0));
		btnBackNoticias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBackNoticias.setBackground(new Color(116, 116, 116));
		btnBackNoticias.setBounds(397, 716, 216, 23);
		panelNoticias.add(btnBackNoticias);

		lblTituloNoticias= new JLabel("Seleccione una noticia:", SwingConstants.CENTER);
		lblTituloNoticias.setForeground(new Color(0, 0, 0));
		lblTituloNoticias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		lblTituloNoticias.setBounds(155, 0, 625, 25);
		panelNoticias.add(lblTituloNoticias);

		lblChangesNoticias = new JLabel("");
		lblChangesNoticias.setForeground(Color.BLACK);
		lblChangesNoticias.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblChangesNoticias.setBounds(269, 896, 464, 37);
		panelNoticias.add(lblChangesNoticias);

		lblFondoNoticias = new JLabel("");
		lblFondoNoticias.setIcon(new ImageIcon(VManagement.class.getResource("/fotos/fondoNoticiasFinal.png")));
		lblFondoNoticias.setBounds(-60, -73, 1066, 1135);
		lblFondoNoticias.setBorder(new RoundedBorder(20));
		panelNoticias.add(lblFondoNoticias);

		btnSeeProfileNoticia.addActionListener(this);
		btnBackNoticias.addActionListener(this);
		btnMdifyNew.addActionListener(this);
		btnEliminarNew.addActionListener(this);
		btnCreateNew.addActionListener(this);
		btnMdifyWeapons.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (e.getSource().equals(btnMdifyWeapons)) {
			if (tableArsenal.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				VModificarArsenal VMa = new VModificarArsenal(controlador, dni,
						(String) tableArsenal.getValueAt(tableArsenal.getSelectedRow(), 0));

				VMa.setVisible(true);
				this.dispose();
			}
		}

		if (o == btnMdifyNew) {
			if (tableNoticias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una noticia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				News noticia = new News();
				noticia = controlador.returnNews((String) tableNoticias.getValueAt(tableNoticias.getSelectedRow(), 0));
				VModificarNoticia vmn = new VModificarNoticia(controlador, dni, noticia.getTitulo());
				vmn.setVisible(true);
				this.dispose();

			}
		}

		if (o == btnSeeProfilePolicia) {
			if (tablePolicias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un policia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Policia policia = new Policia();
				policia = controlador.returnPolicemanById((String) tablePolicias.getValueAt(tablePolicias.getSelectedRow(), 0));
				VVerPoliciaAdmin vvpa = new VVerPoliciaAdmin(controlador, dni, policia.getDni());
				vvpa.setVisible(true);
				this.dispose();
			}

		} else if (o == btnEliminarPolicia) {
			if (tablePolicias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un policía en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Policia policia = new Policia();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este policía?");
				if (option == JOptionPane.YES_OPTION) {
					policia = controlador.returnPolicemanById((String) tablePolicias.getValueAt(tablePolicias.getSelectedRow(), 0));
					controlador.deletePoliceman(policia.getDni());
					controlador.deletePoliceman2(policia.getDni());
					JOptionPane.showMessageDialog(this,
							"Policía eliminado correctamente. No podrá volver a ver este policía.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					modelPolicias.removeRow(tablePolicias.getSelectedRow());
				}

			}
		} else if (e.getSource().equals(btnBackPolicia)) {
			VEntrada ve = new VEntrada(controlador);
			ve.setVisible(true);
			this.dispose();
		} else if (e.getSource().equals(btnBackArsenal)) {
			VEntrada ve = new VEntrada(controlador);
			ve.setVisible(true);
			this.dispose();
		} else if (e.getSource().equals(btnBackCriminales)) {
			VEntrada ve = new VEntrada(controlador);
			ve.setVisible(true);
			this.dispose();
		} else if (e.getSource().equals(btnBackNoticias)) {
			VEntrada ve = new VEntrada(controlador);
			ve.setVisible(true);
			this.dispose();
		} else if (o == btnCreateArticle) {
			VCrearArsenal vca = new VCrearArsenal(controlador, dni);
			vca.setVisible(true);
			this.dispose();
		} else if (o == btnEliminarWeapons) {
			if (tableArsenal.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Arsenal arsenal = new Arsenal();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este artículo?");
				if (option == JOptionPane.YES_OPTION) {
					arsenal = controlador.returnWeaponByName((String) tableArsenal.getValueAt(tableArsenal.getSelectedRow(), 0));
					controlador.deleteWeapon(arsenal.getId_arsenal());
					JOptionPane.showMessageDialog(this,
							"Artículo eliminado correctamente. No podrá volver a ver este artículo.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					modelArsenal.removeRow(tableArsenal.getSelectedRow());
				}

			}
		} else if (o == btnSeeProfileArsenal) {
			if (tableArsenal.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Arsenal arsenal = new Arsenal();
				arsenal = controlador.returnWeaponByName((String) tableArsenal.getValueAt(tableArsenal.getSelectedRow(), 0));
				VVerArsenalAdmin vvaa = new VVerArsenalAdmin(controlador, arsenal.getNombre(), dni);
				vvaa.setVisible(true);
				this.dispose();
			}
		} else if (o == btnSeeProfileCriminal) {
			if (tableCriminales.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un criminal en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Criminal criminal = new Criminal();
				criminal = controlador.showCriminalByPolicemanAdmin((String) tableCriminales.getValueAt(tableCriminales.getSelectedRow(), 0));
				VVerCriminalAdmin vvca = new VVerCriminalAdmin(controlador, dni, criminal.getDni());
				vvca.setVisible(true);
				this.dispose();

			}

		} else if (o == btnEliminarCriminal) {

			if (tableCriminales.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un criminal en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Criminal criminal = new Criminal();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este perfil?");
				if (option == JOptionPane.YES_OPTION) {
					criminal = controlador.showCriminalByPolicemanAdmin((String) tableCriminales.getValueAt(tableCriminales.getSelectedRow(), 0));
					controlador.deletePoliceman(criminal.getDni());
					controlador.deletePoliceman2(criminal.getDni());
					JOptionPane.showMessageDialog(this,
							"Criminal eliminado correctamente. No podrá volver a ver este criminal.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					modelCriminales.removeRow(tableCriminales.getSelectedRow());
				}

			}
		} else if (o == btnSeeProfileNoticia) {
			if (tableNoticias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una noticia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				News noticia = new News();
				noticia = controlador.returnNews((String) tableNoticias.getValueAt(tableNoticias.getSelectedRow(), 0));
				VVerNoticiaAdmin vna = new VVerNoticiaAdmin(controlador, noticia.getId_noticia());
				vna.setVisible(true);
				this.dispose();

			}
		} else if (o == btnEliminarNew) {
			if (tableNoticias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una noticia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				News noticia = new News();
				int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta noticia?");
				if (option == JOptionPane.YES_OPTION) {
					noticia = controlador.returnNews((String) tableNoticias.getValueAt(tableNoticias.getSelectedRow(), 0));
					controlador.deleteNew(noticia.getId_noticia());

					JOptionPane.showMessageDialog(this,
							"Noticia eliminada correctamente. No podrá volver a ver esta noticia.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
					modelNoticias.removeRow(tableNoticias.getSelectedRow());
				}
			}

		} else if (e.getSource().equals(btnCreateSuspect)) {
			VCrearCriminal vcc = new VCrearCriminal(controlador, dni);
			vcc.setVisible(true);
			this.dispose();

		} else if (o == btnCreateNew) {
			VCrearNoticia vcn = new VCrearNoticia(controlador, dni);
			vcn.setVisible(true);
			this.dispose();
		} else if (o == btnMdifyPoliceman) {
			if (tablePolicias.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un policia en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Policia policia = new Policia();
				policia = controlador.returnPolicemanById((String) tablePolicias.getValueAt(tablePolicias.getSelectedRow(), 0));
				VModifyPolicemanAdmin vmpa = new VModifyPolicemanAdmin(controlador, policia.getDni());
				vmpa.setVisible(true);
				this.dispose();
			}

		} else if (e.getSource().equals(btnCrearPolicia)) {

			VCrearPoliciaAdmin vpa = new VCrearPoliciaAdmin(controlador, dni);
			vpa.setVisible(true);
			this.dispose();

		} else if (e.getSource().equals(btnMdifySuspects)) {
			if (tableCriminales.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un perfil en la tabla.", "Error.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Criminal crim = new Criminal();
				crim = controlador.showCriminalByPolicemanAdmin((String) tableCriminales.getValueAt(tableCriminales.getSelectedRow(), 0));
				VModificarCriminal vmc = new VModificarCriminal(controlador, crim.getDni());
				vmc.setVisible(true);
			}
		}

	}

}

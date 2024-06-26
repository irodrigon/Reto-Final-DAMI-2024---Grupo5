package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Arsenal;
import model.Elige;
import model.Policia;
import java.awt.Toolkit;

public class VElegirArmas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Controller controlador;
	private Policia policia;
	private ArrayList<Arsenal> weapons;
	private JScrollPane scroll;
	private int count;
	private DefaultTableModel model;
	private JButton btnConfirmar;
	private JButton btnBack;
	private JButton btnCancel;
	private JCheckBox checkbox;
	private ArrayList<Elige> busquedas;
	private JLabel lblCambios;
	private int guarda;
	private JLabel lblFondo;
	private String dni;
	private String pass;

	public VElegirArmas(Controller controlador, String dni, String pass) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VElegirArmas.class.getResource("/fotos/pixelart2.png")));
		setResizable(false);
		this.controlador = controlador;
		this.dni = dni;
		this.pass = pass;
		policia = controlador.returnPolicemanById(dni);
		weapons = controlador.showArsenal();
		busquedas = controlador.weaponsAssigned(policia.getDni());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 50, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//El código que sigue hace que los checkbox aparezcan en la tabla sin marcar al cargar la ventana.
		String[] columnNames = { "Nombre:", "Descripcion:", "Elegir" };
		model = new DefaultTableModel(null, columnNames) {

			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int c) {
				switch (c) {
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		for (int i = 0; i < weapons.size(); i++) {
			String nombre = weapons.get(i).getNombre();
			String descripcion = weapons.get(i).getDescripcion();
			Object[] data = { nombre, descripcion, false };
			model.addRow(data);
		}

		table = new JTable(model);
		table.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table.setBackground(new Color(116, 116, 116));
		table.setForeground(new Color(0, 0, 0));
		table.setBounds(10, 169, 964, 557);
		table.getTableHeader().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		table.getTableHeader().setBackground(new Color(116, 116, 116));
		table.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 33, 964, 557);
		contentPane.add(scroll);
		checkbox = new JCheckBox();
		checkbox.addActionListener(this);
		
		
		//Con el editor de celdas conseguimos el valor de cada checkbox por separado.
		
		DefaultCellEditor editor = new DefaultCellEditor(checkbox) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				Component tableCellEditorComponent = super.getTableCellEditorComponent(table, value, isSelected, row,
						column);
				((JCheckBox) tableCellEditorComponent).setHorizontalAlignment(JCheckBox.CENTER);
				return tableCellEditorComponent;
			}
		};
		table.getColumnModel().getColumn(2).setCellEditor(editor);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnConfirmar.setForeground(new Color(0, 0, 0));
		btnConfirmar.setBackground(new Color(116, 116, 116));
		btnConfirmar.setBounds(117, 620, 142, 23);
		contentPane.add(btnConfirmar);

		btnBack = new JButton("Volver");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setEnabled(false);
		btnBack.setBounds(432, 620, 89, 23);
		contentPane.add(btnBack);

		btnCancel = new JButton("Cancelar");
		btnCancel.setForeground(new Color(0, 0, 0));
		btnCancel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCancel.setBackground(new Color(116, 116, 116));
		btnCancel.setBounds(797, 620, 142, 23);
		contentPane.add(btnCancel);

		lblCambios = new JLabel("");
		lblCambios.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		lblCambios.setBounds(372, 840, 226, 32);
		contentPane.add(lblCambios);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(VElegirArmas.class.getResource("/fotos/fondoPoliciaFinal.jpg")));
		lblFondo.setBounds(0, 0, 1280, 720);
		contentPane.add(lblFondo);

		btnConfirmar.addActionListener(this);
		btnBack.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == checkbox && checkbox.isSelected()) {
			count++;
		} else if (o == checkbox && !checkbox.isSelected()) {
			count--;
		}
		if (count > 1) {
			JOptionPane.showMessageDialog(this, "El agente no puede seleccionar más de un artículo por vez.", "Error",
					JOptionPane.ERROR_MESSAGE);
			for (int i = 0; i < table.getRowCount(); i++) {
				checkbox.setSelected(false);
				table.setValueAt(false, i, 2);
				count = guarda;
			}
		}
		if (o == btnCancel) {
			VPolicias vP = new VPolicias(controlador, dni, pass);
			vP.setVisible(true);
			this.dispose();
		} else if (o == btnBack) {
			VPolicias vP = new VPolicias(controlador, dni, pass);
			vP.setVisible(true);
			this.dispose();
		} else if (o == btnConfirmar) {
			Arsenal arsenal = new Arsenal();
			boolean found = false;
			if (!checkbox.isSelected()) {
				JOptionPane.showMessageDialog(this, "Seleccione algún artículo.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 2).equals(true)) {
						busquedas = controlador.showAssociations();
						for (Arsenal a : weapons) {
							if (table.getValueAt(i, 0).equals(a.getNombre())) {
								for(Elige el : busquedas) {
									if(el.getId_arsenal() == a.getId_arsenal() && dni.equals(el.getDni_policia())) {
										JOptionPane.showMessageDialog(this, "Ya tienes alguno de estos artículos en tu arsenal.", "Error", JOptionPane.ERROR_MESSAGE);
										found = true;
										
									}
								}
							}
						}
						if(!found) {
							for (int j = 0; j < table.getRowCount(); j++) {
								if (table.getValueAt(j, 2).equals(true)) {
									arsenal = controlador.returnWeaponByName((String)table.getValueAt(j, 0));
								}
							}
							controlador.insertAssociation(dni, arsenal.getId_arsenal());
						}
						lblCambios.setText("Cambios guardados correctamente.");
						btnConfirmar.setEnabled(false);
						btnCancel.setEnabled(false);
						btnBack.setEnabled(true);
					}
				}
			}
		}
	}
}

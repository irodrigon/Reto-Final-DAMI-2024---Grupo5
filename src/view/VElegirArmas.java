package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Arsenal;
import model.Elige;
import model.Policia;

public class VElegirArmas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Controller c;
	private Policia pol;
	private int id_user;
	private ArrayList<Arsenal> weapons;
	private JScrollPane scroll;
	private int count;
	private DefaultTableModel model;
	private JButton btnConfirmar;
	private JButton btnBack;
	private JButton btnCancel;
	private JCheckBox chb;
	private ArrayList<Elige> busquedas;
	private ArrayList<Arsenal> weaponsAvailable;
	private JLabel lblCambios;
	private int guarda;
	private JLabel lblNewLabel_1_1;
	private String dni;
	private String pass;

	public VElegirArmas(Controller c, String dni, String pass) {
		setResizable(false);
		setUndecorated(true);
		this.c = c;
		this.dni = dni;
		this.pass = pass;
		pol = c.returnPolicemanById(dni);
		weapons = c.showArsenal();
		busquedas = c.weaponsAssigned(pol.getDni());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		table.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table.setBackground(new Color(116, 116, 116));
		table.setForeground(new Color(0, 0, 0));
		table.setBounds(10, 169, 964, 557);
		table.getTableHeader().setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table.getTableHeader().setBackground(new Color(116, 116, 116));
		table.getTableHeader().setForeground(new Color(0, 0, 0));
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 169, 964, 557);
		contentPane.add(scroll);
		chb = new JCheckBox();
		chb.addActionListener(this);

		DefaultCellEditor editor = new DefaultCellEditor(chb) {
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
		btnConfirmar.setBounds(130, 777, 104, 23);
		contentPane.add(btnConfirmar);

		btnBack = new JButton("Volver");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setEnabled(false);
		btnBack.setBounds(452, 777, 89, 23);
		contentPane.add(btnBack);

		btnCancel = new JButton("Cancelar");
		btnCancel.setForeground(new Color(0, 0, 0));
		btnCancel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 17));
		btnCancel.setBackground(new Color(116, 116, 116));
		btnCancel.setBounds(781, 777, 89, 23);
		contentPane.add(btnCancel);

		lblCambios = new JLabel("");
		lblCambios.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblCambios.setBounds(372, 840, 226, 32);
		contentPane.add(lblCambios);
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(VEntrada.class.getResource("/fotos/FondoPoliciaFinal.jpg")));
		lblNewLabel_1_1.setBounds(-296, -145, 1296, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);

		btnConfirmar.addActionListener(this);
		btnBack.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == chb && chb.isSelected()) {
			count++;
		} else if (o == chb && !chb.isSelected()) {
			count--;
		}
		if (count > 3) {
			JOptionPane.showMessageDialog(this, "El agente no puede seleccionar más de tres artículos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			for (int i = 0; i < table.getRowCount(); i++) {
				chb.setSelected(false);
				table.setValueAt(false, i, 2);
				count = guarda;
			}
		}
		if (o == btnCancel) {
			VPolicias vP = new VPolicias(c, dni, pass);
			vP.setVisible(true);
			this.dispose();
		} else if (o == btnBack) {
			VPolicias vP = new VPolicias(c, dni, pass);
			vP.setVisible(true);
			this.dispose();
		} else if (o == btnConfirmar) {
			Arsenal ar = new Arsenal();
			boolean found = false;
			if (!chb.isSelected()) {
				JOptionPane.showMessageDialog(this, "Seleccione algún artículo.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 2).equals(true)) {
						busquedas = c.showAssociations();
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
									ar = c.returnWeaponByName((String)table.getValueAt(j, 0));
								}
							}
							c.insertAssociation(dni, ar.getId_arsenal());
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

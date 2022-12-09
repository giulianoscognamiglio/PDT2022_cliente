package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.entities.Funcionalidad;
import com.entities.Rol;
import com.entities.*;

import controlador.DAOGeneral;

public class PanelFuncionalidades extends JPanel {
	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;

	public PanelFuncionalidades() {
		rolSeleccionado = MenuPrincipal.usuarioIngresado.getRol();
		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 332);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "Nombre", "Descripcion" });
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(modeloCombo);

		modeloCombo.addElement("Asignado");
		modeloCombo.addElement("No Asignado");
		comboBoxEstado.setBounds(235, 106, 198, 36);
		add(comboBoxEstado);

		comboBoxEstado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try {
					if (comboBoxEstado.getSelectedItem().toString() == "Asignado") {
						cargarTabla(rolSeleccionado.getFuncionalidades());
					} else if (comboBoxEstado.getSelectedItem().toString() == "No Asignado") {
						List<Funcionalidad> funNo = new ArrayList<>();
						for (Funcionalidad f : DAOGeneral.funcionalidadBean.obtenerFuncionalidades()) {
							Boolean flag = false;
							for (Funcionalidad j : rolSeleccionado.getFuncionalidades()) {
								if (f.getId() == j.getId()) {
									flag = true;
									break;
								}
								if (!flag) {
									funNo.add(f);
								}
							}
						}
						cargarTabla(funNo);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblFuncionalidades = new JLabel("Funcionalidades");
		lblFuncionalidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionalidades.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionalidades.setBounds(235, 37, 198, 36);
		add(lblFuncionalidades);

		cargarTabla(rolSeleccionado.getFuncionalidades());
		
	}

	public void cargarTabla(List<Funcionalidad> fun) {
		modeloTabla.setRowCount(0);
		for (Funcionalidad f : fun) {
			Vector v = new Vector();
			v.addElement(f.getNombre());
			v.addElement(f.getDescripcion());
			modeloTabla.addRow(v);
		}
	}

}

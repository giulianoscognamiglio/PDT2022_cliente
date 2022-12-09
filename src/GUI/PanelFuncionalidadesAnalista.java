package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.entities.Rol;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelFuncionalidadesAnalista extends JPanel {

	DefaultTableModel modeloTablaRol;

	public PanelFuncionalidadesAnalista() {
		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 332);
		add(scrollPane);
		modeloTablaRol = new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "Nombre", "Descripcion" });

		JTable table = new JTable();
		table.setModel(modeloTablaRol);
		scrollPane.setViewportView(table);

		JLabel lblRoles = new JLabel("Roles");
		lblRoles.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoles.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRoles.setBounds(235, 37, 198, 36);
		add(lblRoles);

		JButton btnDetalles = new JButton("Detalles");
		btnDetalles.setBounds(274, 514, 85, 21);
		add(btnDetalles);
		btnDetalles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Rol r = DAOGeneral.rolBean.buscarNombre(modeloTablaRol.getValueAt(table.getSelectedRow(), 0).toString());
					FuncionalidadesAsignadasARol.rolSeleccionado=r;
					FuncionalidadesAsignadasARol fun=new FuncionalidadesAsignadasARol();
					fun.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		try {
			cargarTablaRol();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void cargarTablaRol() throws ServiciosException {
		modeloTablaRol.setRowCount(0);
		for (Rol r : DAOGeneral.rolBean.obtenerRoles()) {
			Vector v = new Vector();
			v.addElement(r.getNombre());
			v.addElement(r.getDescripcion());
			modeloTablaRol.addRow(v);
		}
	}
}

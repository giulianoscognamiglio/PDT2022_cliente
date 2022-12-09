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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.entities.Funcionalidad;
import com.entities.Rol;
import com.exceptions.ServiciosException;
import com.entities.*;

import controlador.DAOGeneral;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelBajaUsuario extends JPanel {
	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;

	public PanelBajaUsuario() {
		
		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 332);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Nombre", "Apellido", "Cédula" });
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarios.setBounds(235, 37, 198, 36);
		add(lblUsuarios);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int column = 2;
				int row = table.getSelectedRow();
				String documento = table.getModel().getValueAt(row, column).toString();		
				
				try {
					Usuario requerido = DAOGeneral.usuarioBean.obtenerPorDocumento(documento);
					DAOGeneral.usuarioBean.borrar(requerido.getId_usuario());
					JOptionPane.showMessageDialog(null, "Usuario dado de baja con éxito", null, JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
			}
		});
		btnBaja.setBounds(274, 512, 85, 21);
		add(btnBaja);

		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
	}

	public void cargarTabla(List<Usuario> usuario) {
		modeloTabla.setRowCount(0);
		for (Usuario u : usuario) {
			Vector v = new Vector();
			v.addElement(u.getNombre1());
			v.addElement(u.getApellido1());
			v.addElement(u.getCedula());
			modeloTabla.addRow(v);
		}
	}
}

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
import javax.swing.JTextField;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBajaUsuario extends JPanel {
	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;

	public PanelBajaUsuario() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 332);
		add(scrollPane);

		JPanel panelDinamico = new JPanel();
		panelDinamico.setBounds(0, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);

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
					JOptionPane.showMessageDialog(null, "Usuario dado de baja con éxito", null,
							JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
			}
		});
		btnBaja.setBounds(206, 500, 85, 21);
		add(btnBaja);
		btnBaja.setVisible(true);

		// modificacion
////////////////////////////////////////////////////////////////////////////////////////
		JButton btnModificación = new JButton("Modificar");
		btnModificación.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// usamos esto para agarrar el documento
				int column = 2;
				int row = table.getSelectedRow();
				String documento = table.getModel().getValueAt(row, column).toString();

				System.out.println("1- " + documento);
				try {
					// generamos el usuario requerido y se lo asignamos a ActualizarUsuario
					Usuario requerido = DAOGeneral.usuarioBean.obtenerPorDocumento(documento);

					System.out.println("1.5 -" + requerido);
					
					ModificarUsuario.setUsuarioIngresado(requerido);
					ModificarUsuario modUsuario = new ModificarUsuario();

					panelDinamico.removeAll();
					modUsuario.setSize(684, 581);
					modUsuario.setLocation(0, 0);
					panelDinamico.add(modUsuario);
					panelDinamico.revalidate();
					panelDinamico.repaint();

					// invisibilizamos cosas innecesarias
					scrollPane.setVisible(false);
					btnBaja.setVisible(false);
					btnModificación.setVisible(false);
					
					//campos no editables
					modUsuario.textFieldDocumento.setEditable(false);
					modUsuario.textFieldEmailUTEC.setEditable(false);



				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnModificación.setBounds(322, 500, 95, 21);
		add(btnModificación);
		btnModificación.setVisible(true);

		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
	}

////////////////////////////////////////////////////////////////////////////////////////
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

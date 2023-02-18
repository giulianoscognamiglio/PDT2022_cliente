package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.entities.ITR;
import com.entities.Rol;

import controlador.DAOGeneral;

public class PanelListaITRs extends JPanel {
	private static PanelListaITRs instance = new PanelListaITRs();

	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;
	JTable table;
	public JPanel panelDinamico;
	public JScrollPane scrollPane;
//	public JButton btnModificación;
	public JComboBox comboBoxEstado;
	public ArrayList<ITR> listaFiltro;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnReactivar;
	private JButton btnCrear;
	public static ITR itrStatic;

	private PanelListaITRs() {
		initGUI();
	}

	public void cargarTabla(List<ITR> itr) {
		modeloTabla.setRowCount(0);
		for (ITR i : itr) {
			Vector v = new Vector();
			v.addElement(i.getNombre());
			v.addElement(i.getId());
			if(i.getActivo().equals("Y")) v.addElement("Activo");
			if(i.getActivo().equals("N")) v.addElement("Inactivo");
			v.addElement(i.getDepartamento().getNombre());
			modeloTabla.addRow(v);
		}
	}

	public void reiniciarPanel() {
		instance = new PanelListaITRs();
	}

	public void initGUI() {
		
		removeAll();

		setBounds(0, 0, 684, 581);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 128, 611, 361);
		add(scrollPane);

		panelDinamico = new JPanel();
		panelDinamico.setBounds(0, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);

		JLabel lblUsuarios = new JLabel("Lista auxiliar de ITRs");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarios.setBounds(235, 28, 198, 36);
		panelDinamico.add(lblUsuarios);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		modeloTabla = new DefaultTableModel(
				new Object[][] { { null, null}, },
				new String[] {"Nombre de ITR", "ID", "Estado", "Departamento" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//elimino id de la vista
		table.removeColumn(table.getColumnModel().getColumn(1));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					try {
						modificarITR();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistroITR registro = new RegistroITR();
				registro.setVisible(true);
			}
		});
		btnCrear.setBounds(136, 502, 95, 23);
		panelDinamico.add(btnCrear);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Activo", "Eliminado" }));
		comboBoxEstado.setBounds(38, 99, 118, 22);
		panelDinamico.add(comboBoxEstado);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(38, 78, 118, 14);
		panelDinamico.add(lblEstado);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eliminarITR();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(346, 502, 95, 23);
		panelDinamico.add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					modificarITR();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(241, 502, 95, 23);
		panelDinamico.add(btnModificar);
		
		btnReactivar = new JButton("Reactivar");
		btnReactivar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					reactivarITR();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReactivar.setBounds(451, 502, 95, 23);
		panelDinamico.add(btnReactivar);
		
		cargarTabla(DAOGeneral.itrBean.obtenerTodos());
		
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		
	}

	public static PanelListaITRs getInstance() {
		return instance;
	}

	public void filtrar() {
		try {
			listaFiltro = new ArrayList<>();
			if (comboBoxEstado.getSelectedItem().toString().equals("Todos")) {
				listaFiltro = (ArrayList<ITR>) DAOGeneral.itrBean.obtenerTodos();
			} else if (comboBoxEstado.getSelectedItem().toString().equals("Activo")) {
				listaFiltro = (ArrayList<ITR>) filtrarActivo((ArrayList<ITR>) DAOGeneral.itrBean.obtenerTodos());
			} else if (comboBoxEstado.getSelectedItem().toString().equals("Eliminado")) {
				listaFiltro = (ArrayList<ITR>) filtrarInactivo((ArrayList<ITR>) DAOGeneral.itrBean.obtenerTodos());
			} 
			cargarTabla(listaFiltro);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<ITR> filtrarActivo(ArrayList<ITR> itr) {
		ArrayList<ITR> listaFiltro = new ArrayList<>();
		for (Iterator<ITR> iter = itr.iterator(); iter.hasNext();) {
			ITR i = iter.next();
			if (i.getActivo().equals("Y")) listaFiltro.add(i);
		}
		return listaFiltro;
	}

	public List<ITR> filtrarInactivo(ArrayList<ITR> itr) {
		ArrayList<ITR> listaFiltro = new ArrayList<>();
		for (Iterator<ITR> iter = itr.iterator(); iter.hasNext();) {
			ITR i = iter.next();
			if (i.getActivo().equals("N")) listaFiltro.add(i);
		}
		return listaFiltro;
	}
	
	public void eliminarITR() throws Exception {
		int column = 1;
		int row = table.getSelectedRow();
		Long idItr = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			ITR itr = DAOGeneral.itrBean.obtenerPorId(idItr);
			if(itr.getActivo().equals("N")) throw new Exception("El ITR ya se encuentra eliminado");
			int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al ITR seleccionado?",
					"Confirmación", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				itr.setActivo("N");
				DAOGeneral.itrBean.actualizar(itr);
				JOptionPane.showMessageDialog(null, "ITR eliminado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				comboBoxEstado.setSelectedIndex(0);
			} else {
				JOptionPane.showMessageDialog(null, "Cambios cancelados");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
					e1.getMessage(),
					null, JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		cargarTabla(DAOGeneral.itrBean.obtenerTodos());
	}
	
	public void reactivarITR() throws Exception {
		int column = 1;
		int row = table.getSelectedRow();
		Long idItr = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			ITR itr = DAOGeneral.itrBean.obtenerPorId(idItr);
			if(itr.getActivo().equals("Y")) throw new Exception("El ITR ya se encuentra activo");
			int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de reactivar al ITR seleccionado?",
					"Confirmación", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				itr.setActivo("Y");
				DAOGeneral.itrBean.actualizar(itr);
				JOptionPane.showMessageDialog(null, "ITR reactivado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				comboBoxEstado.setSelectedIndex(0);
			} else {
				JOptionPane.showMessageDialog(null, "Cambios cancelados");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
					e1.getMessage(),
					null, JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		cargarTabla(DAOGeneral.itrBean.obtenerTodos());
	}
	
	public void modificarITR() throws Exception {
		int column = 1;
		int row = table.getSelectedRow();
		Long idItr = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			ITR itr = DAOGeneral.itrBean.obtenerPorId(idItr);
			itrStatic = itr;
			ModificarITR modificarITR = new ModificarITR();
			modificarITR.setVisible(true);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	
}

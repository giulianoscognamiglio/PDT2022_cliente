package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.ITR;
import com.entities.Rol;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelBajaUsuario extends JPanel {
	private static PanelBajaUsuario instance = new PanelBajaUsuario();

	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;
	JTable table;
	public JPanel panelDinamico;
	public JScrollPane scrollPane;
//	public JButton btnBaja;
	public JButton btnModificación;
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxITR;
	public JComboBox comboBoxGeneracion;
	public JComboBox comboBoxUsuario;
	public DefaultComboBoxModel modeloITR;
	public DefaultComboBoxModel modeloGeneracion;
	public ArrayList<Usuario> listaFiltro;
//	public List<ITR> itrs;

	private PanelBajaUsuario() {
		initGUI();
	}

////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Usuario> usuario) {
		modeloTabla.setRowCount(0);
		for (Usuario u : usuario) {
			Vector v = new Vector();
			v.addElement(u.getNombre1());
			v.addElement(u.getApellido1());
			v.addElement(u.getCedula());
			v.addElement(u.getFechaNac());
			v.addElement(u.getMail());
			v.addElement(u.getMailInstitucional());
			v.addElement(u.getItr().getNombre());
			v.addElement(u.getValidado());
			v.addElement(u.getActivo());
			if (u instanceof Analista)
				v.addElement("Analista");
			if (u instanceof Estudiante)
				v.addElement("Estudiante");
			if (u instanceof Tutor)
				v.addElement("Tutor");
			if (u instanceof Estudiante) {
				v.addElement(((Estudiante) u).getGeneracion());
			} else {
				v.addElement("-");
			}
			v.addElement(u.getId_usuario());

			modeloTabla.addRow(v);
		}
	}

	public void reiniciarPanel() {
		instance = new PanelBajaUsuario();
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

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarios.setBounds(235, 28, 198, 36);
		panelDinamico.add(lblUsuarios);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		modeloTabla = new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Nombre", "Apellido", "Cédula", "Fecha de Nacimiento", "Mail", "Mail Institucional",
						"ITR", "Validado", "Activo", "Usuario", "Generación", "ID" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.removeColumn(table.getColumnModel().getColumn(11));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					try {
						modificarUsuario();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		

//		btnBaja = new JButton("Baja");
//		btnBaja.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				int column = 2;
//				int row = table.getSelectedRow();
//				String documento = table.getModel().getValueAt(row, column).toString();
//
//				try {
//					Usuario requerido = DAOGeneral.usuarioBean.obtenerPorDocumento(documento);
//					DAOGeneral.usuarioBean.borrar(requerido.getId_usuario());
//					JOptionPane.showMessageDialog(null, "Usuario dado de baja con éxito", null,
//							JOptionPane.PLAIN_MESSAGE);
//				} catch (ServiciosException e1) {
//					e1.printStackTrace();
//				}
//				cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
//			}
//		});
//		btnBaja.setBounds(132, 497, 95, 21);
//		panelDinamico.add(btnBaja);
//		btnBaja.setVisible(true);

////////////////////////////////////////////////////////////////////////////////////////
		// modificacion
		btnModificación = new JButton("Modificar");
		btnModificación.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modificarUsuario();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModificación.setBounds(244, 501, 95, 21);
		panelDinamico.add(btnModificación);

		JButton btnActivar = new JButton("Activar");
		btnActivar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					activarUsuario();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnActivar.setBounds(349, 501, 95, 23);
		panelDinamico.add(btnActivar);

		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					desactivarUsuario();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDesactivar.setBounds(454, 501, 95, 23);
		panelDinamico.add(btnDesactivar);

		JButton btnAlta = new JButton("Alta");
		btnAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistroUsuario registro = new RegistroUsuario();
				registro.setVisible(true);
			}
		});
		btnAlta.setBounds(139, 500, 95, 23);
		panelDinamico.add(btnAlta);

		JLabel lblITR = new JLabel("ITR");
		lblITR.setHorizontalAlignment(SwingConstants.CENTER);
		lblITR.setBounds(224, 75, 118, 14);
		panelDinamico.add(lblITR);

		comboBoxITR = new JComboBox();
		modeloITR = new DefaultComboBoxModel();
		comboBoxITR.setModel(modeloITR);
		comboBoxITR.setBounds(224, 96, 118, 22);
		panelDinamico.add(comboBoxITR);
		
		cargarComboBoxITR();

		JLabel lblGeneracion = new JLabel("Generacion");
		lblGeneracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneracion.setBounds(482, 75, 118, 14);
		panelDinamico.add(lblGeneracion);

		comboBoxGeneracion = new JComboBox();
		comboBoxGeneracion.setBounds(482, 96, 118, 22);
		panelDinamico.add(comboBoxGeneracion);

		modeloGeneracion = new DefaultComboBoxModel();
		comboBoxGeneracion.setModel(modeloGeneracion);
		LocalDate year = LocalDate.now();
		modeloGeneracion.addElement("Todas");
		for (int i = year.getYear(); i >= 2012; i--) {
			modeloGeneracion.addElement(i);
		}

		lblGeneracion.setVisible(false);
		comboBoxGeneracion.setVisible(false);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Sin Validar", "Activo", "Eliminado" }));
		comboBoxEstado.setBounds(96, 96, 118, 22);
		panelDinamico.add(comboBoxEstado);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(96, 75, 118, 14);
		panelDinamico.add(lblEstado);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(354, 75, 118, 14);
		panelDinamico.add(lblUsuario);

		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Estudiante", "Tutor", "Analista" }));
		comboBoxUsuario.setBounds(354, 96, 118, 22);
		panelDinamico.add(comboBoxUsuario);
		
		JButton btnLimpiarFiltros = new JButton("X");
		btnLimpiarFiltros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
				defaultValueComboBox();
			}
		});
		btnLimpiarFiltros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpiarFiltros.setBounds(36, 96, 50, 23);
		panelDinamico.add(btnLimpiarFiltros);
		
		btnModificación.setVisible(true);
		
		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
		
		comboBoxITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		
		comboBoxGeneracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		
		comboBoxUsuario.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				filtrar();
				if (comboBoxUsuario.getSelectedItem().toString().equals("Estudiante")) {
					lblGeneracion.setVisible(true);
					comboBoxGeneracion.setVisible(true);
				} else {
					lblGeneracion.setVisible(false);
					comboBoxGeneracion.setVisible(false);
					comboBoxGeneracion.setSelectedIndex(0);
				}

			}
		});
	}

	public static PanelBajaUsuario getInstance() {
		return instance;
	}

	public void activarUsuario() throws Exception {
		int column = 11;
		int row = table.getSelectedRow();
		Long idUsuario = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			Usuario requerido = DAOGeneral.usuarioBean.obtenerPorId(idUsuario);
			if (requerido.getActivo().equals("Y"))
				throw new Exception("El usuario ya se encuentra activado");
			requerido.setActivo("Y");
			requerido.setValidado("Y");
			DAOGeneral.usuarioBean.actualizar(requerido);
			JOptionPane.showMessageDialog(null, "Usuario activado con éxito", null, JOptionPane.PLAIN_MESSAGE);
			defaultValueComboBox();
		} catch (ServiciosException e1) {
			e1.printStackTrace();
		}
		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
	}

	public void desactivarUsuario() throws Exception {

		int column = 11;
		int row = table.getSelectedRow();
		Long idUsuario = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			Usuario requerido = DAOGeneral.usuarioBean.obtenerPorId(idUsuario);
			if (requerido.getActivo().equals("N"))
				throw new Exception("El usuario ya se encuentra desactivado");
			requerido.setActivo("N");
			requerido.setValidado("Y");
			int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de descativar al usuario seleccionado?",
					"Confirmación", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				DAOGeneral.usuarioBean.actualizar(requerido);
				JOptionPane.showMessageDialog(null, "Usuario desactivado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				defaultValueComboBox();
			} else {
				JOptionPane.showMessageDialog(null, "Cambios cancelados");
			}
		} catch (ServiciosException e1) {
			e1.printStackTrace();
		}
		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());

	}

	public void modificarUsuario() throws Exception {
		int column = 2;
		int row = table.getSelectedRow();
		String documento = table.getModel().getValueAt(row, column).toString();

		// generamos el usuario requerido y se lo asignamos a ActualizarUsuario
		Usuario requerido = DAOGeneral.usuarioBean.obtenerPorDocumento(documento);

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
//		btnBaja.setVisible(false);
		btnModificación.setVisible(false);

		// campos no editables
		modUsuario.textFieldDocumento.setEditable(false);
		modUsuario.textFieldEmailUTEC.setEditable(false);

	}

	public void cargarComboBoxITR() {
		modeloITR.removeAllElements();
		modeloITR.addElement("Todos");
		for (ITR itr : DAOGeneral.itrBean.obtenerTodos()) {
			if(itr.getActivo().equals("Y")) {
				modeloITR.addElement(itr.getNombre());
			}
		}
	}

	public void defaultValueComboBox() {
		comboBoxEstado.setSelectedIndex(0);
		comboBoxGeneracion.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxUsuario.setSelectedIndex(0);
	}

	public void filtrar() {
		try {
			listaFiltro = new ArrayList<>();
			if (comboBoxEstado.getSelectedItem().toString().equals("Activo")) {
				listaFiltro = (ArrayList<Usuario>) filtrarActivo(
						(ArrayList<Usuario>) DAOGeneral.usuarioBean.obtenerTodos());
			} else if (comboBoxEstado.getSelectedItem().toString().equals("Eliminado")) {
				listaFiltro = (ArrayList<Usuario>) filtrarInactivo(
						(ArrayList<Usuario>) DAOGeneral.usuarioBean.obtenerTodos());
			} else if (comboBoxEstado.getSelectedItem().toString().equals("Sin Validar")) {
				listaFiltro = (ArrayList<Usuario>) filtrarSinValidar(
						(ArrayList<Usuario>) DAOGeneral.usuarioBean.obtenerTodos());
			} else if (comboBoxEstado.getSelectedItem().toString().equals("Todos")) {
				listaFiltro = (ArrayList<Usuario>) DAOGeneral.usuarioBean.obtenerTodos();
			}
			List<Usuario> listaFiltro2 = new ArrayList<>();
			for (Iterator<Usuario> iter = listaFiltro.iterator(); iter.hasNext();) {
				Usuario u = iter.next();
				if (comboBoxUsuario.getSelectedItem().toString().equals("Estudiante")) {
					if (u instanceof Estudiante) {
						listaFiltro2.add(u);
					}
				} else if (comboBoxUsuario.getSelectedItem().toString().equals("Tutor")) {
					if (u instanceof Tutor) {
						listaFiltro2.add(u);
					}
				} else if (comboBoxUsuario.getSelectedItem().toString().equals("Analista")) {
					if (u instanceof Analista) {
						listaFiltro2.add(u);
					}
				} else {
					listaFiltro2.add(u);
				}
			}

			List<Usuario> listaFiltro3 = new ArrayList<>();
			for (Iterator<Usuario> iter = listaFiltro2.iterator(); iter.hasNext();) {
				Usuario u = iter.next();
				if (u.getItr().getNombre().matches(comboBoxITR.getSelectedItem().toString())) {
					listaFiltro3.add(u);
				} else if (comboBoxITR.getSelectedIndex() == 0) {
					listaFiltro3.add(u);
				}
			}

			List<Usuario> listaFiltro4 = new ArrayList<>();
			for (Iterator<Usuario> iter = listaFiltro3.iterator(); iter.hasNext();) {
				Usuario u = iter.next();
				if (comboBoxGeneracion.getSelectedIndex() != 0) {
					if (u instanceof Estudiante) {
						if (((Estudiante) u).getGeneracion().equals(comboBoxGeneracion.getSelectedItem().toString())) {
							listaFiltro4.add(u);
						}
					}
				} else {
					listaFiltro4.add(u);
				}
			}

			cargarTabla(listaFiltro4);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<Usuario> filtrarActivo(ArrayList<Usuario> usuarios) {
		ArrayList<Usuario> listaFiltro = new ArrayList<>();
		for (Iterator<Usuario> iter = usuarios.iterator(); iter.hasNext();) {
			Usuario u = iter.next();
			if (u.getActivo().equals("Y") && u.getValidado().equals("Y")) {
				listaFiltro.add(u);
			}
		}
		return listaFiltro;
	}

	public List<Usuario> filtrarInactivo(ArrayList<Usuario> usuarios) {
		ArrayList<Usuario> listaFiltro = new ArrayList<>();
		for (Iterator<Usuario> iter = usuarios.iterator(); iter.hasNext();) {
			Usuario u = iter.next();
			if (u.getActivo().equals("N") && u.getValidado().equals("Y")) {
				listaFiltro.add(u);
			}
		}
		return listaFiltro;
	}

	public List<Usuario> filtrarSinValidar(ArrayList<Usuario> usuarios) {
		ArrayList<Usuario> listaFiltro = new ArrayList<>();
		for (Iterator<Usuario> iter = usuarios.iterator(); iter.hasNext();) {
			Usuario u = iter.next();
			if (u.getActivo().equals("N") && u.getValidado().equals("N")) {
				listaFiltro.add(u);
			}
		}
		return listaFiltro;
	}
}

package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

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
import javax.swing.table.DefaultTableModel;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Funcionalidad;
import com.entities.Rol;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelBajaUsuario extends JPanel {
	private static PanelBajaUsuario instance=new PanelBajaUsuario();
	
	
	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;
	JTable table;
	public JPanel panelDinamico;
	public JScrollPane scrollPane;
//	public JButton btnBaja;
	public JButton btnModificación;
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxUsuario;
	public JComboBox comboBoxITR;
	public JComboBox comboBoxGeneracion;

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
			if(u instanceof Analista)v.addElement("Analista");
			if(u instanceof Estudiante)v.addElement("Estudiante");
			if(u instanceof Tutor)v.addElement("Tutor");
			if(u instanceof Estudiante){
				v.addElement(((Estudiante)u).getGeneracion());
			}else{
				v.addElement("-");
			}
			v.addElement(u.getId_usuario());

			
			modeloTabla.addRow(v);
		}
	}
	
	public void reiniciarPanel(){
		instance=new PanelBajaUsuario();
	}
	
	
	public void initGUI(){
		
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

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null, null, null, null}, },
				new String[] {  "Nombre", "Apellido", "Cédula", "Fecha de Nacimiento", "Mail", 
								"Mail Institucional", "ITR", "Validado", "Activo", "Usuario", "Generación", "ID"}){
				
				@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			};
		table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(225, 75, 118, 14);
		panelDinamico.add(lblUsuario);
		
		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setBounds(225, 96, 118, 22);
		panelDinamico.add(comboBoxUsuario);
		
		JLabel lblITR = new JLabel("ITR");
		lblITR.setHorizontalAlignment(SwingConstants.CENTER);
		lblITR.setBounds(354, 75, 118, 14);
		panelDinamico.add(lblITR);
		
		comboBoxITR = new JComboBox();
		comboBoxITR.setBounds(354, 96, 118, 22);
		panelDinamico.add(comboBoxITR);
		
		JLabel lblGeneracion = new JLabel("Generacion");
		lblGeneracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneracion.setBounds(482, 75, 118, 14);
		panelDinamico.add(lblGeneracion);
		
		comboBoxGeneracion = new JComboBox();
		comboBoxGeneracion.setBounds(482, 96, 118, 22);
		panelDinamico.add(comboBoxGeneracion);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(96, 75, 118, 14);
		panelDinamico.add(lblEstado);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Usuario> todos = DAOGeneral.usuarioBean.obtenerTodos();
				if(comboBoxEstado.getSelectedItem().toString() == "Todos") {
					cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
				}else if (comboBoxEstado.getSelectedItem().toString() == "Sin Validar") {
					List<Usuario> sinValidar = todos.stream().filter(u -> u.getValidado().equals("N")).collect(Collectors.toList());
					cargarTabla(sinValidar);
				}else if (comboBoxEstado.getSelectedItem().toString() == "Activo") {
					List<Usuario> activos = todos.stream().filter(u -> u.getActivo().equals("Y")).collect(Collectors.toList());
					cargarTabla(activos);
				}
				else if (comboBoxEstado.getSelectedItem().toString() == "Eliminado") {
					List<Usuario> eliminados = todos.stream().filter(u -> u.getActivo().equals("N")).collect(Collectors.toList());
					cargarTabla(eliminados);
				}
			}
		});
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Sin Validar", "Activo", "Eliminado"}));
		comboBoxEstado.setBounds(96, 96, 118, 22);
		panelDinamico.add(comboBoxEstado);
		
		btnModificación.setVisible(true);
		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
	}
	public static PanelBajaUsuario getInstance(){
		return instance;
	}
	
	public void activarUsuario()throws Exception {
		int column = 11;
		int row = table.getSelectedRow();
		Long idUsuario = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		try {
			Usuario requerido = DAOGeneral.usuarioBean.obtenerPorId(idUsuario);
			if (requerido.getActivo().equals("Y")) throw new Exception("El usuario ya se encuentra activado");
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
	
	public void desactivarUsuario()throws Exception {
		
			int column = 11;
			int row = table.getSelectedRow();
			Long idUsuario = Long.parseLong(table.getModel().getValueAt(row, column).toString());
			try {
				Usuario requerido = DAOGeneral.usuarioBean.obtenerPorId(idUsuario);
				if (requerido.getActivo().equals("N")) throw new Exception("El usuario ya se encuentra desactivado");
				requerido.setActivo("N");
				requerido.setValidado("Y");
				int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de descativar al usuario seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					DAOGeneral.usuarioBean.actualizar(requerido);
					JOptionPane.showMessageDialog(null, "Usuario desactivado con éxito", null, JOptionPane.PLAIN_MESSAGE);
					defaultValueComboBox();
				}else {
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

		System.out.println("1- " + documento);

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
//		btnBaja.setVisible(false);
		btnModificación.setVisible(false);

		// campos no editables
		modUsuario.textFieldDocumento.setEditable(false);
		modUsuario.textFieldEmailUTEC.setEditable(false);

	}
	
	public void defaultValueComboBox() {
		comboBoxEstado.setSelectedIndex(0);
		comboBoxGeneracion.setSelectedIndex(0);
		comboBoxITR.setSelectedIndex(0);
		comboBoxUsuario.setSelectedIndex(0);
	}
	
	
	
	
//	DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
//	JComboBox comboBoxEstado = new JComboBox();
//	comboBoxEstado.setModel(modeloCombo);
//
//	modeloCombo.addElement("Asignado");
//	modeloCombo.addElement("No Asignado");
//	comboBoxEstado.setBounds(235, 106, 198, 36);
//	add(comboBoxEstado);
//
//	comboBoxEstado.addItemListener(new ItemListener() {
//		@Override
//		public void itemStateChanged(ItemEvent arg0) {
//			try {
//				
//				if (comboBoxEstado.getSelectedItem().toString() == "Asignado") {
//					cargarTabla(rolSeleccionado.getFuncionalidades());
//					
//				} else if (comboBoxEstado.getSelectedItem().toString() == "No Asignado") {
//					
//					List<Funcionalidad> funNo = new ArrayList<>();
//					
//					for (Funcionalidad f : DAOGeneral.funcionalidadBean.obtenerFuncionalidades()) {
//						
//						Boolean flag = false;
//						for (Funcionalidad j : rolSeleccionado.getFuncionalidades()) {
//							
//							if (f.getId() == j.getId()) {
//								flag = true;
//								break;
//							}
//							if (!flag) {
//								funNo.add(f);
//							}
//						}
//					}
//					cargarTabla(funNo);
//				}
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
//	});
	
//	public void cargarTablaFiltro(List<Funcionalidad> fun) {
//		modeloTabla.setRowCount(0);
//		for (Funcionalidad f : fun) {
//			Vector v = new Vector();
//			v.addElement(f.getNombre());
//			v.addElement(f.getDescripcion());
//			modeloTabla.addRow(v);
//		}
//	}
}

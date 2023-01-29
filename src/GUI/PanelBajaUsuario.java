package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
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
import com.entities.Rol;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;
import java.awt.Color;

public class PanelBajaUsuario extends JPanel {
	private static PanelBajaUsuario instance=new PanelBajaUsuario();
	
	
	public static Rol rolSeleccionado;
	private DefaultTableModel modeloTabla;
	JTable table;
	public JPanel panelDinamico;
	public JScrollPane scrollPane;
	public JButton btnBaja;
	public JButton btnModificación;

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
		scrollPane.setBounds(63, 88, 561, 401);
		add(scrollPane);

		panelDinamico = new JPanel();
		panelDinamico.setBounds(0, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarios.setBounds(235, 37, 198, 36);
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
		btnModificación.setBounds(188, 500, 95, 21);
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
		btnActivar.setBounds(293, 500, 95, 23);
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
		btnDesactivar.setBounds(398, 500, 95, 23);
		panelDinamico.add(btnDesactivar);
		
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
			DAOGeneral.usuarioBean.actualizar(requerido);
			JOptionPane.showMessageDialog(null, "Usuario desactivado con éxito", null, JOptionPane.PLAIN_MESSAGE);
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
		btnBaja.setVisible(false);
		btnModificación.setVisible(false);

		// campos no editables
		modUsuario.textFieldDocumento.setEditable(false);
		modUsuario.textFieldEmailUTEC.setEditable(false);

	}
}

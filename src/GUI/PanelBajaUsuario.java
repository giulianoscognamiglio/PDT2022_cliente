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
	public JButton btnModificaci�n;

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


			
			modeloTabla.addRow(v);
		}
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
		btnModificaci�n.setVisible(false);

		// campos no editables
		modUsuario.textFieldDocumento.setEditable(false);
		modUsuario.textFieldEmailUTEC.setEditable(false);

	}
	
	
	public void reiniciarPanel(){
		instance=new PanelBajaUsuario();
	}
	
	
	public void initGUI(){
		
		removeAll();
		
		setBounds(0, 0, 684, 581);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 101, 561, 388);
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

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] {  "Nombre", "Apellido", "C�dula", "Fecha de Nacimiento", "Mail", 
								"Mail Institucional", "ITR", "Validado", "Activo", "Usuario", "Generaci�n"}){
				
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

		btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int column = 2;
				int row = table.getSelectedRow();
				String documento = table.getModel().getValueAt(row, column).toString();

				try {
					Usuario requerido = DAOGeneral.usuarioBean.obtenerPorDocumento(documento);
					DAOGeneral.usuarioBean.borrar(requerido.getId_usuario());
					JOptionPane.showMessageDialog(null, "Usuario dado de baja con �xito", null,
							JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
			}
		});
		btnBaja.setBounds(206, 500, 85, 21);
		panelDinamico.add(btnBaja);
		btnBaja.setVisible(true);

		// modificacion
////////////////////////////////////////////////////////////////////////////////////////
		btnModificaci�n = new JButton("Modificar");
		btnModificaci�n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					modificarUsuario();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnModificaci�n.setBounds(322, 500, 95, 21);
		panelDinamico.add(btnModificaci�n);
		btnModificaci�n.setVisible(true);

		cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
	}
	public static PanelBajaUsuario getInstance(){
		return instance;
	}
}

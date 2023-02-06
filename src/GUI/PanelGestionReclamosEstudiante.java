package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.entities.Estudiante;
import com.entities.Reclamo;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelGestionReclamosEstudiante extends JPanel {

	private DefaultTableModel modeloTabla;
	private Estudiante estudiante = DAOGeneral.estudianteBean.obtenerPorUsuario(PanelMenu.usuarioIngresado.getId_usuario());
	public PanelGestionReclamosEstudiante() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 238);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null}, },
				new String[] { "ID", "Estado"});
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		

		JLabel lblReclamos = new JLabel("Reclamos");
		lblReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReclamos.setBounds(235, 37, 198, 36);
		add(lblReclamos);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AltaReclamo altaReclamo = null;
				try {
					altaReclamo = new AltaReclamo();
					altaReclamo.setVisible(true);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAlta.setBounds(197, 499, 86, 22);
		add(btnAlta);
		btnAlta.setVisible(false);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				Reclamo reclamoDB = seleccionarReclamo(table);

				//con esta condicional nos aseguramos que solo se puedan eliminar los que aún
				//no hayan sido tocados por un analista
				if(reclamoDB.getEstado().equals("INGRESADO")) {
					
					//Se le adjudica el reclamo obtenido desde la base a la variable static "reclamo" y se lanza el pop up de confirmacion
					ConfirmacionPopUp.reclamo = reclamoDB;
					ConfirmacionPopUp confirmacionPopUp = new ConfirmacionPopUp();
					
					confirmacionPopUp.setVisible(true);
					
					//DAOGeneral.reclamoBean.borrar(reclamoDB.getId_reclamo());
					//JOptionPane.showMessageDialog(null, "Reclamo dado de baja con éxito", null,
						//	JOptionPane.PLAIN_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null, "No se permite eliminar reclamos finalizados o en proceso.", null,
							JOptionPane.ERROR_MESSAGE);
				}
				cargarTabla(DAOGeneral.reclamoBean.obtenerPorEstudiante(estudiante.getId_estudiante()));
			}
		});
		btnBaja.setBounds(293, 499, 85, 21);
		add(btnBaja);
		btnBaja.setVisible(false);

		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					
					
					Reclamo reclamoDB = seleccionarReclamo(table);

					DetalleReclamo.reclamo=reclamoDB;
					
					DetalleReclamo detRecl = new DetalleReclamo();
					
					detRecl.setVisible(true);

				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDetalle.setBounds(388, 499, 85, 21);
		add(btnDetalle);
		btnDetalle.setVisible(false);
		
		JButton btnActualizar = new JButton("Actualizar reclamos");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cargarTabla(DAOGeneral.reclamoBean.obtenerPorEstudiante(PanelMenu.usuarioIngresado.getId_usuario()));

			}
		});
		btnActualizar.setBounds(432, 421, 192, 21);
		add(btnActualizar);
		
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(modeloCombo);

		modeloCombo.addElement("Todos");
		modeloCombo.addElement("En proceso");
		modeloCombo.addElement("Finalizado");
		modeloCombo.addElement("Ingresado");

		comboBoxEstado.setBounds(235, 106, 198, 36);
		add(comboBoxEstado);

		//Filtrar
		comboBoxEstado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try {
					//primero nos traemos todos los reclamos y luego creamos una lista auxiliar donde vamos a almacenar los
					//reclamos filtrados
					List<Reclamo> reclamos = DAOGeneral.reclamoBean.obtenerTodos();
					List<Reclamo> reclamosFiltrados;

					if (comboBoxEstado.getSelectedItem().toString() == "En proceso") {
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						reclamosFiltrados = reclamos.stream().filter(r -> r.getEstado().equals("EN PROCESO")).collect(Collectors.toList());
						cargarTabla(reclamosFiltrados);

					} else if (comboBoxEstado.getSelectedItem().toString() == "Finalizado") {
						
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
							reclamosFiltrados = (List<Reclamo>) reclamos.stream().filter(r -> r.getEstado().equals("FINALIZADO")).collect(Collectors.toList());
							cargarTabla(reclamosFiltrados);
							
					} else if (comboBoxEstado.getSelectedItem().toString() == "Ingresado") {


						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						reclamosFiltrados = (List<Reclamo>) reclamos.stream().filter(r -> r.getEstado().equals("INGRESADO")).collect(Collectors.toList());
						cargarTabla(reclamosFiltrados);
						
				   } else if (comboBoxEstado.getSelectedItem().toString() == "Todos") {

					  	//aca retornamos todos los elementos de la lista
					   cargarTabla(reclamos);
				   } 	


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
							
				btnAlta.setVisible(true);
				btnBaja.setVisible(true);
				btnDetalle.setVisible(true);

			}
		});
		
		cargarTabla(DAOGeneral.reclamoBean.obtenerPorEstudiante(estudiante.getId_estudiante()));

	}
	


////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Reclamo> reclamos) {
		modeloTabla.setRowCount(0);
		for (Reclamo r : reclamos) {
			
					

			try {
				
				Vector v = new Vector();
				Usuario usuarioDB;
				usuarioDB = DAOGeneral.usuarioBean.obtenerPorId(r.getEstudiante());
				
				v.addElement(r.getId_reclamo());
				v.addElement(r.getEstado());

				modeloTabla.addRow(v);


			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

	
	public Reclamo seleccionarReclamo(JTable table) {
		//metodo para seleccionar un reclamo de la tabla
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		
		Reclamo reclamoDB = DAOGeneral.reclamoBean.obtenerReclamo(id);
		
		return reclamoDB;
	}
}

package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

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

import com.exceptions.ServiciosException;
import com.entities.*;

import controlador.DAOGeneral;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGestionJustificados extends JPanel {

	private DefaultTableModel modeloTabla;

	public PanelGestionJustificados() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 561, 238);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null}, },
				new String[] { "ID", "Estudiante", "Estado"});
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		

		JLabel lblJustificados = new JLabel("Justificados");
		lblJustificados.setHorizontalAlignment(SwingConstants.CENTER);
		lblJustificados.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJustificados.setBounds(235, 37, 198, 36);
		add(lblJustificados);
		
		cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
	

		
//		DETALLE DE JUSTIFICADO
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					
					
					Justificado justificadoDB = seleccionarJustificado(table);

					DetalleJustificado.justificado = justificadoDB;
					
					DetalleJustificado detJustif = new DetalleJustificado();
					
					detJustif.setVisible(true);

				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDetalle.setBounds(331, 500, 85, 21);
		add(btnDetalle);
		
		
//		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "En proceso", "Finalizado"}));
		comboBox.setBounds(496, 428, 128, 26);
		add(comboBox);
		
		JLabel lblEstado = new JLabel("Modificar estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(496, 405, 128, 13);
		add(lblEstado);

		
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(modeloCombo);

		modeloCombo.addElement("Todos");		
		modeloCombo.addElement("En proceso");
		modeloCombo.addElement("Finalizado");
		modeloCombo.addElement("Ingresado");

		comboBoxEstado.setBounds(235, 106, 198, 36);
		add(comboBoxEstado);

		
//		BAJA DE JUSTIFICADO	
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {

			
			try {
				
				Justificado justificadoDB = seleccionarJustificado(table);

				System.out.println(justificadoDB);
				DAOGeneral.justificadoBean.borrar(justificadoDB.getId_justificado());
				
					
				JOptionPane.showMessageDialog(null, "Justificado dado de baja con éxito", null,
						JOptionPane.PLAIN_MESSAGE);
			} catch (ServiciosException e1) {
				e1.printStackTrace();
			}
			cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
		}
	});
	btnBaja.setBounds(206, 500, 85, 21);
		add(btnBaja);
		

		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			Justificado justificadoDB = seleccionarJustificado(table);

			String estado =  comboBox.getSelectedItem().toString().toUpperCase();
			try {
				
				modificarEstado(justificadoDB,estado);
				
				cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());

				
			} catch (ServiciosException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	btnActualizar.setBounds(496, 479, 128, 21);
		add(btnActualizar);

		
		
		//Filtrar
		comboBoxEstado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try {
					//primero nos traemos todos los justificados y luego creamos una lista auxiliar donde vamos a almacenar los
					//justificados filtrados
					List<Justificado> justificados = DAOGeneral.justificadoBean.obtenerTodos();
					List<Justificado> justificadosFiltrados;

					if (comboBoxEstado.getSelectedItem().toString() == "En proceso") {
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						justificadosFiltrados = justificados.stream().filter(j -> j.getEstado().equals("EN PROCESO")).collect(Collectors.toList());
						cargarTabla(justificadosFiltrados);

					} else if (comboBoxEstado.getSelectedItem().toString() == "Finalizado") {
						
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						justificadosFiltrados = justificados.stream().filter(j -> j.getEstado().equals("FINALIZADO")).collect(Collectors.toList());
							cargarTabla(justificadosFiltrados);
							
					} else if (comboBoxEstado.getSelectedItem().toString() == "Ingresado") {
						
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						justificadosFiltrados = justificados.stream().filter(j -> j.getEstado().equals("INGRESADO")).collect(Collectors.toList());
						cargarTabla(justificadosFiltrados);
					}  else if (comboBoxEstado.getSelectedItem().toString() == "Todos") {
												
						//aca retornamos todos los elementos de la lista
							cargarTabla(justificados);
					} 	
					

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		


	}
	


////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Justificado> justificados) {
		modeloTabla.setRowCount(0);
		for (Justificado j : justificados) {
			
					

			try {
				
				Vector v = new Vector();
				Usuario usuarioDB;
				usuarioDB = DAOGeneral.usuarioBean.obtenerPorId(j.getEstudiante());
				
				v.addElement(j.getId_justificado());
				v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
				v.addElement(j.getEstado());
				
				modeloTabla.addRow(v);


			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void modificarEstado(Justificado justificadoDB, String nuevoEstado) throws ServiciosException {
		//seteamos el estado nuevo
		justificadoDB.setEstado(nuevoEstado);
		
		//mandamos el registro modificado a la base
		DAOGeneral.justificadoBean.actualizar(justificadoDB);
		
	}
	
	public Justificado seleccionarJustificado(JTable table) {
		//metodo para seleccionar un justificado de la tabla
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		
		Justificado justificadoDB = DAOGeneral.justificadoBean.obtenerJustificado(id);
		
		return justificadoDB;
	}
}
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

public class PanelGestionJustificadosEstudiante extends JPanel {

	private DefaultTableModel modeloTabla;

	public PanelGestionJustificadosEstudiante() {

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
		

		JLabel lblJustificados = new JLabel("Justificados");
		lblJustificados.setHorizontalAlignment(SwingConstants.CENTER);
		lblJustificados.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJustificados.setBounds(235, 37, 198, 36);
		add(lblJustificados);
		
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				Justificado justificadoDB = seleccionarJustificado(table);

				//con esta condicional nos aseguramos que solo se puedan eliminar los que aún
				//no hayan sido tocados por un analista
				if(justificadoDB.getEstado().equals("INGRESADO")) {
					
					//Se le adjudica el justificado obtenido desde la base a la variable static "justificado" y se lanza el pop up de confirmación
					ConfirmacionPopUpJustificado.justificado = justificadoDB;
					ConfirmacionPopUpJustificado confirmacionPopUpJustificado = new ConfirmacionPopUpJustificado();
					
					confirmacionPopUpJustificado.setVisible(true);
					
<<<<<<< HEAD
=======
					//DAOGeneral.justificadoBean.borrar(justificadoDB.getId_justificado());
					//JOptionPane.showMessageDialog(null, "Justificado dado de baja con éxito", null,
						//	JOptionPane.PLAIN_MESSAGE);
>>>>>>> cba656a (Nuevas features de justificados)
					
				}else {
					JOptionPane.showMessageDialog(null, "No se permite eliminar justificados finalizados o en proceso.", null,
							JOptionPane.ERROR_MESSAGE);
				}
				cargarTabla(DAOGeneral.justificadoBean.obtenerPorEstudiante(PanelMenu.usuarioIngresado.getId_usuario()));
			}
		});
		btnBaja.setBounds(206, 500, 85, 21);
		add(btnBaja);
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					
					
					Justificado justificadoDB = seleccionarJustificado(table);

					DetalleJustificado.justificado=justificadoDB;
					
<<<<<<< HEAD
					DetalleJustificado detJust = new DetalleJustificado();
					
					detJust.setVisible(true);
=======
					DetalleJustificado detRecl = new DetalleJustificado();
					
					detRecl.setVisible(true);
>>>>>>> cba656a (Nuevas features de justificados)

				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnDetalle.setBounds(331, 500, 85, 21);
		add(btnDetalle);
		
		JButton btnActualizar = new JButton("Actualizar justificados");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cargarTabla(DAOGeneral.justificadoBean.obtenerPorEstudiante(PanelMenu.usuarioIngresado.getId_usuario()));

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
					//primero nos traemos todos los justificados y luego creamos una lista auxiliar donde vamos a almacenar los
					//justificados filtrados
					List<Justificado> justificados = DAOGeneral.justificadoBean.obtenerTodos();
					List<Justificado> justificadosFiltrados;

					if (comboBoxEstado.getSelectedItem().toString() == "En proceso") {
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						justificadosFiltrados = justificados.stream().filter(r -> r.getEstado().equals("EN PROCESO")).collect(Collectors.toList());
						cargarTabla(justificadosFiltrados);

					} else if (comboBoxEstado.getSelectedItem().toString() == "Finalizado") {
						
						
						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
							justificadosFiltrados = (List<Justificado>) justificados.stream().filter(r -> r.getEstado().equals("FINALIZADO")).collect(Collectors.toList());
							cargarTabla(justificadosFiltrados);
							
					} else if (comboBoxEstado.getSelectedItem().toString() == "Ingresado") {


						//aca utilizamos .stream().filter().collect para filtrar los elementos de la lista auxiliar y retornamos
						justificadosFiltrados = (List<Justificado>) justificados.stream().filter(r -> r.getEstado().equals("INGRESADO")).collect(Collectors.toList());
						cargarTabla(justificadosFiltrados);
						
				   } else if (comboBoxEstado.getSelectedItem().toString() == "Todos") {

					  	//aca retornamos todos los elementos de la lista
					   cargarTabla(justificados);
				   } 	


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		cargarTabla(DAOGeneral.justificadoBean.obtenerPorEstudiante(PanelMenu.usuarioIngresado.getId_usuario()));

	}
	


////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Justificado> justificados) {
		modeloTabla.setRowCount(0);
		for (Justificado r : justificados) {
			
					

			try {
				
				Vector v = new Vector();
				Usuario usuarioDB;
				usuarioDB = DAOGeneral.usuarioBean.obtenerPorId(r.getEstudiante());
				
				v.addElement(r.getId_justificado());
				v.addElement(r.getEstado());

				modeloTabla.addRow(v);


			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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

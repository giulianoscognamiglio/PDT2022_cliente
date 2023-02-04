package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.JTextArea;

public class PanelAnaliticaReportes extends JPanel {

	private DefaultTableModel modeloTabla;
	private JComboBox comboBoxFiltroValor;

	public PanelAnaliticaReportes() {

		setBounds(0, 0, 625, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 166, 409, 282);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Nombre", "Cantidad" });
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);

		JLabel lblReclamos = new JLabel("Analítica de reportes");
		lblReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReclamos.setBounds(235, 37, 198, 36);
		add(lblReclamos);

//------------------------------------------------------------------------------------
		JComboBox comboBoxFiltroCriterio = new JComboBox();
		comboBoxFiltroCriterio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Reclamo> reclamos = DAOGeneral.reclamoBean.obtenerTodos();

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Generación")) {

					HashMap<String, Integer> hmGeneraciones = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = DAOGeneral.estudianteBean.obtenerPorId(r.getEstudiante()).getGeneracion();

						// si una generacion no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmGeneraciones.containsKey(key))) {

							hmGeneraciones.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmGeneraciones.put(key, hmGeneraciones.get(key) + 1);
						}

					}
					cargarTablaHM(hmGeneraciones);

				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Mes")) {
					HashMap<String, Integer> hmMeses = new HashMap<>();

					// hacemos una lista con los meses
					List<String> meses = new ArrayList<String>();

					meses.add("Enero");
					meses.add("Febrero");
					meses.add("Marzo");
					meses.add("Abril");
					meses.add("Mayo");
					meses.add("Junio");
					meses.add("Julio");
					meses.add("Agosto");
					meses.add("Septiembre");
					meses.add("Octubre");
					meses.add("Noviembre");
					meses.add("Diciembre");

					for (Reclamo r : reclamos) {

//						seleccionamos el nombre del mes mediante el numero que nos da getFecha.getMonth
//						NOTA: Estos metodos devuelven el mes en entero menos uno. 
//						Ejemplo: Noviembre = =10
						String key = meses.get(r.getFecha().getMonth());

						// si un mes no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmMeses.containsKey(key))) {

							hmMeses.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
							// map.put(key, map.get(key) + 1);
							hmMeses.put(key, hmMeses.get(key) + 1);
						}

					}
					cargarTablaHM(hmMeses);

				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("ITR")) {

					HashMap<String, Integer> hmGeneraciones = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = DAOGeneral.estudianteBean.obtenerPorId(r.getEstudiante()).getItr().getNombre();

						// si un ITR no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmGeneraciones.containsKey(key))) {

							hmGeneraciones.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmGeneraciones.put(key, hmGeneraciones.get(key) + 1);
						}

					}
					cargarTablaHM(hmGeneraciones);
				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Tipo")) {

					HashMap<String, Integer> hmGeneraciones = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = r.getTipo();

						// si un Tipo de reclamo no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmGeneraciones.containsKey(key))) {

							hmGeneraciones.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmGeneraciones.put(key, hmGeneraciones.get(key) + 1);
						}

					}
					cargarTablaHM(hmGeneraciones);
				}

			}
		});
		comboBoxFiltroCriterio.setBounds(124, 109, 145, 36);
		add(comboBoxFiltroCriterio);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();

		modeloCombo.addElement("- Seleccione criterio -");

		modeloCombo.addElement("Mes");
		modeloCombo.addElement("ITR");
		modeloCombo.addElement("Generación");
		modeloCombo.addElement("Tipo");

		comboBoxFiltroCriterio.setModel(modeloCombo);

		JButton btnDescargar = new JButton("Descargar reporte");
		btnDescargar.setBounds(48, 499, 153, 36);
		add(btnDescargar);

		comboBoxFiltroValor = new JComboBox();
		comboBoxFiltroValor.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				List<Reclamo> reclamos = new ArrayList<Reclamo>();

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Mes")) {
					reclamos = DAOGeneral.reclamoBean.obtenerTodos().stream()
							.filter(r -> (r.getFecha().getMonth() + 1) == (comboBoxFiltroValor.getSelectedIndex()))
							.collect(Collectors.toList());

				}

			}
		});
		comboBoxFiltroValor.setBounds(363, 109, 145, 36);
		add(comboBoxFiltroValor);

		comboBoxFiltroValor.setVisible(false);

	}

	private DefaultComboBoxModel cargarComboFiltroValor(String criterio) throws ServiciosException {
		// método para hacer aparecer el comboBoxFiltroCriterio y darle los datos a
		// mostrar
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		modeloCombo.addElement("Seleccione item");

		if (criterio.equals("Mes")) {

			modeloCombo.addElement("Enero");
			modeloCombo.addElement("Febrero");
			modeloCombo.addElement("Marzo");
			modeloCombo.addElement("Abril");
			modeloCombo.addElement("Mayo");
			modeloCombo.addElement("Junio");
			modeloCombo.addElement("Julio");
			modeloCombo.addElement("Agosto");
			modeloCombo.addElement("Septiembre");
			modeloCombo.addElement("Octubre");
			modeloCombo.addElement("Noviembre");
			modeloCombo.addElement("Diciembre");
//			List<Usuario> estudiantes = DAOGeneral.usuarioBean.obtenerPorRol(1L);
//
//			for (Usuario e : estudiantes) {
//				modeloCombo.addElement("" + e.getId_usuario() + " - " + e.getCedula());
//			}
		} else if (criterio.equals("Generación")) {

			// agarramos todos los estudiantes y metemos todas las generaciones (no
			// repetidas) dentro del modeloCombo
			List<Estudiante> estudiantes = DAOGeneral.estudianteBean.obtenerTodos();

			// lista auxiliar para evitar repetidos
			List<String> generacionesAux = new ArrayList<String>();

			for (Estudiante e : estudiantes) {
				if (!generacionesAux.contains(e.getGeneracion())) {

					generacionesAux.add(e.getGeneracion());

					modeloCombo.addElement(e.getGeneracion());

				}
			}

		} else if (criterio.equals("Tipo")) {

			modeloCombo.addElement("VME");
			modeloCombo.addElement("APE");
			modeloCombo.addElement("Optativas");
			modeloCombo.addElement("Otro");

		} else if (criterio.equals("ITR")) {

			List<ITR> itrs = DAOGeneral.itrBean.obtenerTodos();

			for (ITR itr : itrs) {
				modeloCombo.addElement(itr.getNombre());
			}

		}

		return modeloCombo;

	}

	public void cargarTabla(List<Reclamo> reclamos) {
		modeloTabla.setRowCount(0);
		for (Reclamo r : reclamos) {

			try {

				Vector v = new Vector();
				Usuario usuarioDB;
				usuarioDB = DAOGeneral.usuarioBean.obtenerPorId(r.getEstudiante());

				v.addElement(r.getId_reclamo());
				v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
//				v.addElement(r.getEstado());

				modeloTabla.addRow(v);

			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void cargarTablaHM(HashMap<String, Integer> hmGeneraciones) {
		modeloTabla.setRowCount(0);

		for (Map.Entry<String, Integer> r : hmGeneraciones.entrySet()) {

			String key = r.getKey();
			Integer value = r.getValue();

			Vector v = new Vector();
			v.addElement(key);
			v.addElement(value);

			modeloTabla.addRow(v);
//
//				Vector v = new Vector();
//				Usuario usuarioDB;
//
//				v.addElement(r.getId_reclamo());
//				v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
////				v.addElement(r.getEstado());
//
//				modeloTabla.addRow(v);

		}
	}
}

package GUI;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
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

import com.entities.AccionJustificacion;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Justificado;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelGestionJustificados extends JPanel {

	private DefaultTableModel modeloTabla;
	public static PanelGestionJustificados instance = new PanelGestionJustificados();

	private JButton btnBaja;
	private JButton btnActualizar;
	private JButton btnDetalle;
	private TextArea textDetalle;
	private JComboBox comboBox;
	private JButton btnAccion;
	private JLabel lblValor;

	public PanelGestionJustificados() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 159, 360, 259);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "ID", "Estudiante", "Estado" });
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
//		table.removeColumn(table.getColumnModel().getColumn(0));

		JLabel lblJustificados = new JLabel("Justificaciones de inasistencias");
		lblJustificados.setHorizontalAlignment(SwingConstants.CENTER);
		lblJustificados.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJustificados.setBounds(63, 37, 551, 36);
		add(lblJustificados);

		cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());

		btnDetalle = new JButton("Detalle");
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
		btnDetalle.setBounds(266, 494, 85, 21);
		add(btnDetalle);

//		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "En proceso", "Finalizado" }));
		comboBox.setBounds(496, 428, 128, 26);
		add(comboBox);

		JLabel lblEstado = new JLabel("Modificar estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(496, 405, 128, 13);
		add(lblEstado);
		
		JButton btnActualizarLista = new JButton("Refrescar");
		btnActualizarLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
			}
		});
		btnActualizarLista.setBounds(321, 131, 102, 21);
		add(btnActualizarLista);
		
		JLabel lblDetalleAccion = new JLabel("Detalle acci\u00F3n");
		lblDetalleAccion.setBounds(496, 282, 90, 13);
		add(lblDetalleAccion);
		
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		JComboBox comboFiltroValor = new JComboBox();

		comboFiltroValor.setBounds(456, 210, 198, 36);
		add(comboFiltroValor);
		comboFiltroValor.setVisible(false);

		btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					Justificado justificadoDB = seleccionarJustificado(table);

					System.out.println(justificadoDB);
					DAOGeneral.justificadoBean.borrar(justificadoDB.getId_justificado());

					JOptionPane.showMessageDialog(null, "Justificacion dado de baja con ï¿½xito", null,
							JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
			}
		});
		btnBaja.setBounds(141, 494, 85, 21);
		add(btnBaja);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Justificado justificadoDB = seleccionarJustificado(table);
				String estado = comboBox.getSelectedItem().toString().toUpperCase();

				justificadoDB.setEstado(estado);

				AccionJustificacion accionJustificacion = new AccionJustificacion();
				Analista usuarioAnalista = DAOGeneral.analistaBean.obtenerAnalistaDocumento(MenuPrincipal.usuarioIngresado.getCedula());
					
				String stringDetalle = "La justificación pasó a estar "+ comboBox.getSelectedItem();

				accionJustificacion.setFecha(new Date());
				accionJustificacion.setAnalista(usuarioAnalista.getId_analista());
				accionJustificacion.setDetalle(stringDetalle);
				accionJustificacion.setJustificado_id(justificadoDB.getId_justificado());
				
				ConfirmacionAccionReclamo.justificado = justificadoDB;
				ConfirmacionAccionReclamo.accionJustificacion = accionJustificacion;

				ConfirmacionAccionReclamo popUp = new ConfirmacionAccionReclamo();
				
				textDetalle.setText("");

				popUp.setVisible(true);
				
				reiniciar();


			}
		});
		btnActualizar.setBounds(496, 479, 128, 21);
		add(btnActualizar);

		textDetalle = new TextArea();

		textDetalle.setBounds(496, 301, 145, 48);
		add(textDetalle);

		JComboBox comboFiltroCriterio = new JComboBox();
		comboFiltroCriterio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				System.out.println(comboFiltroCriterio.getSelectedItem().toString());
				if (comboFiltroCriterio.getSelectedItem().toString().equals("Todos")) {

					List<Justificado> justificados = DAOGeneral.justificadoBean.obtenerTodos();

					cargarTabla(justificados);

				} else {
					try {
						comboFiltroValor
								.setModel(cargarComboFiltroValor(comboFiltroCriterio.getSelectedItem().toString()));

						comboFiltroValor.setVisible(true);
						lblValor.setVisible(true);

					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		comboFiltroCriterio.setBounds(456, 131, 198, 36);
		add(comboFiltroCriterio);
		comboFiltroCriterio.setModel(modeloCombo);

		btnAccion = new JButton("Registrar acci\u00F3n");
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AccionJustificacion accionJustificacion = new AccionJustificacion();
				Analista usuarioAnalista = DAOGeneral.analistaBean
						.obtenerAnalistaDocumento(MenuPrincipal.usuarioIngresado.getCedula());

				accionJustificacion.setFecha(new Date());
				accionJustificacion.setAnalista(usuarioAnalista.getId_analista());
				accionJustificacion.setDetalle(textDetalle.getText());

				Justificado justificadoDB = seleccionarJustificado(table);
				ConfirmacionAccionReclamo.justificado = justificadoDB;
				ConfirmacionAccionReclamo.accionJustificacion = accionJustificacion;

				ConfirmacionAccionReclamo popUp = new ConfirmacionAccionReclamo();
				popUp.setVisible(true);
			}

		});
		btnAccion.setBounds(496, 365, 145, 21);
		add(btnAccion);

		textDetalle.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(textDetalle.getText());
				if (textDetalle.getText().length() <= 0) {
					btnAccion.setEnabled(false);
				} else {
					btnAccion.setEnabled(true);

				}
			}
		});

		btnAccion.setEnabled(false);
		btnActualizar.setEnabled(false);
		textDetalle.setEnabled(false);

		modeloCombo.addElement("Todos");
		modeloCombo.addElement("Estado");
		modeloCombo.addElement("Estudiante");

		add(comboFiltroValor);
		// Filtrar
		comboFiltroValor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					// primero nos traemos todos los justificados y luego creamos una lista auxiliar
					// donde vamos a almacenar los
					// justificados filtrados
					List<Justificado> justificados = DAOGeneral.justificadoBean.obtenerTodos();
					List<Justificado> justificadosFiltrados;
					if (comboFiltroCriterio.getSelectedItem().toString() == "Estado") {
						if (comboFiltroValor.getSelectedItem().toString() == "En proceso") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							justificadosFiltrados = justificados.stream()
									.filter(j -> j.getEstado().equals("EN PROCESO")).collect(Collectors.toList());
							cargarTabla(justificadosFiltrados);

						} else if (comboFiltroValor.getSelectedItem().toString() == "Finalizado") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							justificadosFiltrados = justificados.stream()
									.filter(j -> j.getEstado().equals("FINALIZADO")).collect(Collectors.toList());
							cargarTabla(justificadosFiltrados);

						} else if (comboFiltroValor.getSelectedItem().toString() == "Ingresado") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							justificadosFiltrados = justificados.stream().filter(j -> j.getEstado().equals("INGRESADO"))
									.collect(Collectors.toList());
							cargarTabla(justificadosFiltrados);
						} else if (comboFiltroValor.getSelectedItem().toString() == "Todos") {

							// aca retornamos todos los elementos de la lista
							cargarTabla(justificados);
						}
					} else if (comboFiltroCriterio.getSelectedItem().toString() == "Estudiante") {

						int idInt = Integer
								.parseInt(String.valueOf(comboFiltroValor.getSelectedItem().toString().charAt(0)));
						// obtenemos el long del estudiante seleccionado
						long idEstudiante = new Long(idInt);

						List<Justificado> justificadosPorEstudiante = DAOGeneral.justificadoBean
								.obtenerPorEstudiante(idEstudiante);

						cargarTabla(justificadosPorEstudiante);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnBaja.setVisible(true);
				btnDetalle.setVisible(true);

				comboBox.setEnabled(true);
				btnBaja.setEnabled(true);
				btnDetalle.setEnabled(true);
				textDetalle.setEnabled(true);
				btnActualizar.setEnabled(true);
			}
		});

		btnBaja.setEnabled(false);
		btnDetalle.setEnabled(false);
		comboBox.setEnabled(false);

		lblValor = new JLabel("Valor:");
		lblValor.setBounds(456, 187, 73, 13);
		add(lblValor);

		JLabel lblCriterio = new JLabel("Filtrar por:");
		lblCriterio.setBounds(456, 108, 73, 13);
		add(lblCriterio);

		lblValor.setVisible(false);
	}

////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Justificado> justificados) {
		modeloTabla.setRowCount(0);
		for (Justificado j : justificados) {

			Vector v = new Vector();
			Usuario usuarioDB;
			usuarioDB = DAOGeneral.estudianteBean.obtenerPorId(j.getEstudiante());

			v.addElement(j.getId_justificado());
			v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
			v.addElement(j.getEstado());

			modeloTabla.addRow(v);

		}

	}

	public void modificarEstado(Justificado justificadoDB, String nuevoEstado) throws ServiciosException {
		// seteamos el estado nuevo
		justificadoDB.setEstado(nuevoEstado);

		// mandamos el registro modificado a la base
		DAOGeneral.justificadoBean.actualizar(justificadoDB);

	}

	public Justificado seleccionarJustificado(JTable table) {
		// metodo para seleccionar un justificado de la tabla
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());

		Justificado justificadoDB = DAOGeneral.justificadoBean.obtenerJustificado(id);

		return justificadoDB;
	}

	private DefaultComboBoxModel cargarComboFiltroValor(String criterio) throws ServiciosException {
		// mï¿½todo para hacer aparecer el comboBoxFiltroCriterio y darle los datos a
		// mostrar
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		modeloCombo.addElement("Seleccione item");

		if (criterio.equals("Estudiante")) {
			List<Usuario> usuarios = DAOGeneral.usuarioBean.obtenerPorRol(1L);
			List<Estudiante> estudiantes = new ArrayList<Estudiante>();
			List<Justificado> justificados = DAOGeneral.justificadoBean.obtenerTodos();

			for (Usuario u : usuarios) {
				estudiantes.add(DAOGeneral.estudianteBean.obtenerPorUsuario(u.getId_usuario()));
			}

			for (Estudiante e : estudiantes) {
				modeloCombo.addElement("" + e.getId_estudiante() + " - " + e.getCedula());

			}

		} else if (criterio.equals("Estado")) {
			modeloCombo.addElement("Ingresado");
			modeloCombo.addElement("En proceso");
			modeloCombo.addElement("Finalizado");
		}

		return modeloCombo;

	}

	public static PanelGestionJustificados getInstance() {
		return instance;
	}

	public void reiniciar() {
		btnBaja.setEnabled(false);
		btnActualizar.setEnabled(false);
		textDetalle.setEnabled(false);
		comboBox.setEnabled(false);
		btnBaja.setEnabled(false);
		btnDetalle.setEnabled(false);
		btnAccion.setEnabled(false);

		lblValor.setVisible(false);

		cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
	}
}
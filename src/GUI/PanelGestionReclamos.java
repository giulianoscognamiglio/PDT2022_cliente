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

import com.entities.AccionReclamo;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Reclamo;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class PanelGestionReclamos extends JPanel {

	private DefaultTableModel modeloTabla;
	public static PanelGestionReclamos instance = new PanelGestionReclamos();
	private JButton btnBaja;
	private JButton btnActualizar;
	private JButton btnDetalle;
	private TextArea textDetalle;
	private JComboBox comboBoxEstado;
	private JButton btnAccion;
	private JLabel lblValor;

	public PanelGestionReclamos() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 152, 379, 266);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "ID", "Estudiante", "Estado" });
		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
//		table.removeColumn(table.getColumnModel().getColumn(0));

		JLabel lblReclamos = new JLabel("Reclamos");
		lblReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReclamos.setBounds(235, 37, 198, 36);
		add(lblReclamos);

		btnBaja = new JButton("Baja");
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					Reclamo reclamoDB = seleccionarReclamo(table);

					DAOGeneral.reclamoBean.borrar(reclamoDB.getId_reclamo());

					JOptionPane.showMessageDialog(null, "Reclamo dado de baja con éxito", null,
							JOptionPane.PLAIN_MESSAGE);

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}

				cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
			}
		});
		btnBaja.setBounds(148, 500, 85, 21);
		add(btnBaja);
		btnBaja.setEnabled(false);

		btnDetalle = new JButton("Detalle");
		btnDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					Reclamo reclamoDB = seleccionarReclamo(table);

					DetalleReclamo.reclamo = reclamoDB;
					DetalleReclamo detRecl = new DetalleReclamo();

					detRecl.setVisible(true);

				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDetalle.setBounds(331, 500, 85, 21);
		add(btnDetalle);
		btnDetalle.setEnabled(false);
		// ------------------------------------------------------------------------------------
		// Esto tiene que ser invisible hasta que se interactua con la tabla
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "", "En proceso", "Finalizado" }));
		comboBoxEstado.setBounds(496, 428, 145, 26);
		add(comboBoxEstado);
		comboBoxEstado.setEnabled(false);

		textDetalle = new TextArea();

		textDetalle.setBounds(496, 301, 145, 48);
		add(textDetalle);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Reclamo reclamoDB = seleccionarReclamo(table);

				String estado = comboBoxEstado.getSelectedItem().toString().toUpperCase();

				reclamoDB.setEstado(estado);
				Analista usuarioAnalista = DAOGeneral.analistaBean
						.obtenerAnalistaDocumento(MenuPrincipal.usuarioIngresado.getCedula());

				String stringDetalle ="El reclamo pasó a estar "+ comboBoxEstado.getSelectedItem();

				AccionReclamo accionReclamo = new AccionReclamo();

				accionReclamo.setFecha(new Date());
				accionReclamo.setAnalista(usuarioAnalista.getId_analista());
				accionReclamo.setDetalle(stringDetalle);

				ConfirmacionAccionReclamo.accionReclamo = accionReclamo;
				ConfirmacionAccionReclamo.reclamo = reclamoDB;

				ConfirmacionAccionReclamo popUp = new ConfirmacionAccionReclamo();
				popUp.setVisible(true);

				cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
				reiniciar();

			}
		});
		btnActualizar.setBounds(496, 479, 145, 21);
		add(btnActualizar);
		btnActualizar.setEnabled(false);
//------------------------------------------------------------------------------------
		JComboBox comboBoxFiltroValor = new JComboBox();
		comboBoxFiltroValor.setBounds(496, 228, 145, 36);
		add(comboBoxFiltroValor);
		comboBoxFiltroValor.setVisible(false);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();

		JComboBox comboBoxFiltro = new JComboBox();
		comboBoxFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cuando se elige un item de este combo:
				if (comboBoxFiltro.getSelectedIndex() > 0) {
					try {
						// Al combo de valores para filtrar se le cargan los valores
						comboBoxFiltroValor
								.setModel(cargarComboFiltroValor(comboBoxFiltro.getSelectedItem().toString()));

						// El combo de valores para filtrar se hace visible
						comboBoxFiltroValor.setVisible(true);
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					// invisibilizamos el combo si tenemos la opcion "Todos" seleccionada

					cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
					comboBoxFiltroValor.setVisible(false);

				}
			}

		});
		modeloCombo.addElement("Todos");
		modeloCombo.addElement("Estudiante");
		modeloCombo.addElement("Estado");
		comboBoxFiltro.setModel(modeloCombo);

		comboBoxFiltro.setBounds(496, 152, 145, 36);
		add(comboBoxFiltro);

		btnAccion = new JButton("Registrar acci\u00F3n");
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AccionReclamo accionReclamo = new AccionReclamo();
				Analista usuarioAnalista = DAOGeneral.analistaBean
						.obtenerAnalistaDocumento(MenuPrincipal.usuarioIngresado.getCedula());
				Reclamo reclamoDB = seleccionarReclamo(table);

				accionReclamo.setFecha(new Date());
				accionReclamo.setAnalista(usuarioAnalista.getId_analista());
				accionReclamo.setDetalle(textDetalle.getText());
				accionReclamo.setReclamo_id(reclamoDB.getId_reclamo());

				ConfirmacionAccionReclamo.accionReclamo = accionReclamo;
				ConfirmacionAccionReclamo.reclamo = reclamoDB;

				ConfirmacionAccionReclamo popUp = new ConfirmacionAccionReclamo();
				popUp.setVisible(true);

				textDetalle.setText("");

				reiniciar();

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

		JLabel lblDetalleAccion = new JLabel("Detalle acci\u00F3n");
		lblDetalleAccion.setBounds(496, 282, 90, 13);
		add(lblDetalleAccion);

		JLabel lblFiltro = new JLabel("Filtrar por: ");
		lblFiltro.setBounds(496, 129, 105, 13);
		add(lblFiltro);

		JLabel lblEstado = new JLabel("Modificar estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(496, 405, 128, 13);
		add(lblEstado);
		
		lblValor = new JLabel("Valor: ");
		lblValor.setBounds(496, 205, 105, 13);
		add(lblValor);
		lblValor.setVisible(false);

		JButton btnActualizarLista = new JButton("Refrescar");
		btnActualizarLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
			}
		});
		btnActualizarLista.setBounds(331, 121, 102, 21);
		add(btnActualizarLista);
		lblValor.setVisible(false);

		// Filtrar
		comboBoxFiltroValor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if (comboBoxFiltro.getSelectedItem().toString() == "Estado") {

					try {
						// primero nos traemos todos los reclamos y luego creamos una lista auxiliar
						// donde vamos a almacenar los
						// reclamos filtrados
						List<Reclamo> reclamos = DAOGeneral.reclamoBean.obtenerTodos();
						List<Reclamo> reclamosFiltrados;

						if (comboBoxFiltroValor.getSelectedItem().toString() == "En proceso") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							reclamosFiltrados = reclamos.stream().filter(r -> r.getEstado().equals("EN PROCESO"))
									.collect(Collectors.toList());
							cargarTabla(reclamosFiltrados);

						} else if (comboBoxFiltroValor.getSelectedItem().toString() == "Finalizado") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							reclamosFiltrados = reclamos.stream().filter(r -> r.getEstado().equals("FINALIZADO"))
									.collect(Collectors.toList());
							cargarTabla(reclamosFiltrados);

						} else if (comboBoxFiltroValor.getSelectedItem().toString() == "Ingresado") {

							// aca utilizamos .stream().filter().collect para filtrar los elementos de la
							// lista auxiliar y retornamos
							reclamosFiltrados = reclamos.stream().filter(r -> r.getEstado().equals("INGRESADO"))
									.collect(Collectors.toList());
							cargarTabla(reclamosFiltrados);
						} else {
							cargarTabla(reclamos);
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (comboBoxFiltro.getSelectedItem().toString() == "Estudiante") {

					int idInt = Integer
							.parseInt(String.valueOf(comboBoxFiltroValor.getSelectedItem().toString().charAt(0)));
					// obtenemos el long del estudiante seleccionado
					long idEstudiante = new Long(idInt);

					List<Reclamo> reclamosPorEstudiante = DAOGeneral.reclamoBean.obtenerPorEstudiante(idEstudiante);

					cargarTabla(reclamosPorEstudiante);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				lblValor.setVisible(true);

				btnActualizar.setEnabled(true);
				textDetalle.setEnabled(true);
				comboBoxEstado.setEnabled(true);
				btnBaja.setEnabled(true);
				btnDetalle.setEnabled(true);
			}
		});
		cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());

	}

////////////////////////////////////////////////////////////////////////////////////////
	public void cargarTabla(List<Reclamo> reclamos) {
		modeloTabla.setRowCount(0);
		for (Reclamo r : reclamos) {

			Vector v = new Vector();
			Usuario usuarioDB;
			usuarioDB = DAOGeneral.estudianteBean.obtenerPorId(r.getEstudiante());

			v.addElement(r.getId_reclamo());
			v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
			v.addElement(r.getEstado());
				
			modeloTabla.addRow(v);
		}
	}

	public void modificarEstado(Reclamo reclamoDB, String nuevoEstado) throws ServiciosException {
		// seteamos el estado nuevo

		reclamoDB.setEstado(nuevoEstado);

		// mandamos el registro modificado a la base
		DAOGeneral.reclamoBean.actualizar(reclamoDB);

	}

	public Reclamo seleccionarReclamo(JTable table) {
		// metodo para seleccionar un reclamo de la tabla
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());

		Reclamo reclamoDB = DAOGeneral.reclamoBean.obtenerReclamo(id);

		System.out.println(">>>>>" + id);
		return reclamoDB;
	}

	private DefaultComboBoxModel cargarComboFiltroValor(String criterio) throws ServiciosException {
		// método para hacer aparecer el comboBoxFiltroCriterio y darle los datos a
		// mostrar
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		modeloCombo.addElement("Seleccione item");

		if (criterio.equals("Estudiante")) {
			List<Usuario> usuarios = DAOGeneral.usuarioBean.obtenerPorRol(1L);
			List<Estudiante> estudiantes = new ArrayList<Estudiante>();

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

	public static PanelGestionReclamos getInstance() {
		return instance;
	}

	public void reiniciar() {
		btnBaja.setEnabled(false);
		btnActualizar.setEnabled(false);
		textDetalle.setEnabled(false);
		textDetalle.setText("");
		comboBoxEstado.setEnabled(false);
		btnBaja.setEnabled(false);
		btnDetalle.setEnabled(false);
		btnAccion.setEnabled(false);
		
		lblValor.setVisible(false);

		cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
	}
}

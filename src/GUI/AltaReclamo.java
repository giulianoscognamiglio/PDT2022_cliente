package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.ITR;
import com.entities.Reclamo;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;
import necesario.Panel;
import rojeru_san.rsdate.RSDateChooser;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class AltaReclamo extends JFrame {

	
	public static Reclamo reclamoExistente;
	private JPanel contentPane;
	private JLabel lblNombre2;
	DefaultComboBoxModel modeloITR;
	private JTextField textCreditos;
	public RSDateChooser dateChooser;
	private JTextField textSemestre;
	private JTextField textNombreActividadEvento;
	private JComboBox comboDocente;
	private JButton btnRegistrar;
	private JLabel lblDocente;
	private JLabel lblNombreActividadEvento;
	private JLabel lblCreditos;
	private JLabel lblSemestre;
	private JLabel lblFecha;
	private JTextField textTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaReclamo frame = new AltaReclamo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ServiciosException
	 */

	public AltaReclamo() throws ServiciosException {

		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());

		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 447, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblReclamo = new JLabel("Reclamo:");
		lblReclamo.setBounds(23, 10, 175, 13);
		contentPane.add(lblReclamo);

		dateChooser = new RSDateChooser();
		dateChooser.setColorButtonHover(Color.BLACK);
		dateChooser.setColorDiaActual(Color.BLACK);
		dateChooser.setColorForeground(Color.BLACK);
		dateChooser.setColorBackground(Color.BLACK);
		dateChooser.setBounds(231, 113, 187, 19);
		getContentPane().add(dateChooser);

		TextArea textAreaReclamo = new TextArea();
		textAreaReclamo.setBounds(27, 165, 156, 129);
		contentPane.add(textAreaReclamo);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(93, 388, 105, 21);
		contentPane.add(btnVolver);

		JComboBox comboTipoReclamo = new JComboBox();
		comboTipoReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opcionSeleccionada = comboTipoReclamo.getSelectedItem().toString();
				if (opcionSeleccionada.equals("VME")) {
					textCreditos.setVisible(true);
					comboDocente.setVisible(true);
					textSemestre.setVisible(true);
					dateChooser.setVisible(true);

					textNombreActividadEvento.setVisible(true);
					lblNombreActividadEvento.setVisible(true);

					lblNombreActividadEvento.setVisible(true);
					lblDocente.setVisible(true);
					lblCreditos.setVisible(true);
					lblSemestre.setVisible(true);
					lblFecha.setVisible(true);

				} else if (opcionSeleccionada.equals("APE")) {
					textCreditos.setVisible(true);
					comboDocente.setVisible(true);
					textSemestre.setVisible(true);
					dateChooser.setVisible(true);

					textNombreActividadEvento.setVisible(true);
					lblNombreActividadEvento.setVisible(true);

					lblNombreActividadEvento.setVisible(true);
					lblDocente.setVisible(true);
					lblCreditos.setVisible(true);
					lblSemestre.setVisible(true);
					lblFecha.setVisible(true);
				} else if (opcionSeleccionada.equals("Optativas")) {
					textCreditos.setVisible(true);
					comboDocente.setVisible(true);
					textSemestre.setVisible(true);
					dateChooser.setVisible(true);

					textNombreActividadEvento.setVisible(true);
					lblNombreActividadEvento.setVisible(true);

					lblDocente.setVisible(true);
					lblCreditos.setVisible(true);
					lblSemestre.setVisible(true);
					lblFecha.setVisible(true);

				}

				else {
					textCreditos.setVisible(false);
					comboDocente.setVisible(false);
					textSemestre.setVisible(false);
					dateChooser.setVisible(false);
					textNombreActividadEvento.setVisible(false);
					lblNombreActividadEvento.setVisible(false);

					lblDocente.setVisible(false);
					lblCreditos.setVisible(false);
					lblSemestre.setVisible(false);
					lblFecha.setVisible(false);

				}
			}
		});

		comboTipoReclamo.setModel(new DefaultComboBoxModel(new String[] { "Otro", "VME", "APE", "Optativas" }));
		comboTipoReclamo.setBounds(23, 54, 122, 21);
		contentPane.add(comboTipoReclamo);

		textCreditos = new JTextField();
		textCreditos.setBounds(231, 227, 96, 19);
		contentPane.add(textCreditos);
		textCreditos.setColumns(10);

		comboDocente = new JComboBox();
		comboDocente.setBounds(229, 54, 122, 21);
		contentPane.add(comboDocente);

		lblDocente = new JLabel("Docente");
		lblDocente.setBounds(229, 31, 102, 13);
		contentPane.add(lblDocente);

		JLabel lblTipoReclamo = new JLabel("Tipo reclamo");
		lblTipoReclamo.setBounds(23, 33, 96, 13);
		contentPane.add(lblTipoReclamo);

		textSemestre = new JTextField();
		textSemestre.setColumns(10);
		textSemestre.setBounds(231, 293, 96, 19);
		contentPane.add(textSemestre);

		lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(231, 271, 119, 13);
		contentPane.add(lblSemestre);

		lblCreditos = new JLabel("Creditos");
		lblCreditos.setBounds(231, 204, 119, 13);
		contentPane.add(lblCreditos);

		textNombreActividadEvento = new JTextField();
		textNombreActividadEvento.setColumns(10);
		textNombreActividadEvento.setBounds(231, 165, 96, 19);
		contentPane.add(textNombreActividadEvento);

		lblNombreActividadEvento = new JLabel("Nombre actividad");
		lblNombreActividadEvento.setBounds(231, 142, 105, 13);
		contentPane.add(lblNombreActividadEvento);

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(24, 143, 105, 13);
		contentPane.add(lblDetalle);

		lblFecha = new JLabel("Fecha de inicio del evento");
		lblFecha.setBounds(226, 85, 156, 13);
		contentPane.add(lblFecha);

		btnRegistrar = new JButton("Enviar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Reclamo reclamo = new Reclamo();

				// Usamos esto para obtener la fecha de ahora y agregarle una hora neutra para
				// parsearlo de LocalDate a Date
				LocalDate localDate = LocalDate.now();
				ZoneId defaultZoneId = ZoneId.systemDefault();

				// necesitamos parsear esto porque en la clase usuario tenemos long pero para la
				// base precisamos Long
				Long idUsuario = new Long(PanelMenu.usuarioIngresado.getId_usuario());
				Estudiante estudiante = DAOGeneral.estudianteBean.obtenerPorUsuario(idUsuario);

				// dependiendo del tipo de reclamo vamos a guardar determinados campos o
				// nullearlos
				reclamo.setTipo(comboTipoReclamo.getSelectedItem().toString());
				reclamo.setTitulo(textTitulo.getText());
				if (reclamo.getTipo().equals("VME")) {
					//Si es un reclamo VME

					reclamo.setDetalle(textAreaReclamo.getText());
					reclamo.setEstudiante(estudiante.getId_estudiante());
					reclamo.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
					reclamo.setEstado("INGRESADO");

					reclamo.setCreditosReclamados(Integer.parseInt(textCreditos.getText()));

					int idInt = Integer.parseInt(String.valueOf(comboDocente.getSelectedItem().toString().charAt(0)));
					// obtenemos el long del docente seleccionado
					long idDocente = new Long(idInt);

					reclamo.setDocente_id(idDocente);
					reclamo.setFechaInicioActividad(dateChooser.getDatoFecha());
					reclamo.setSemestre(Integer.parseInt(textSemestre.getText()));
					reclamo.setNombreEventoVME(textNombreActividadEvento.getText());

				} else if (reclamo.getTipo().equals("Otro")) {
					
					//Si es un reclamo que no sea VME, ni APE ni Optativas
					
					reclamo.setDetalle(textAreaReclamo.getText());
					reclamo.setEstudiante(estudiante.getId_estudiante());
					reclamo.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
					reclamo.setEstado("INGRESADO");
					
					
					
					reclamo.setCreditosReclamados(null);
					reclamo.setDocente_id(null);
					reclamo.setFechaInicioActividad(null);
					reclamo.setSemestre(null);
					reclamo.setNombreActividadAPE(null);
				}else {
					//Si es APE u optativas

					reclamo.setDetalle(textAreaReclamo.getText());
					reclamo.setEstudiante(estudiante.getId_estudiante());
					//					reclamo.setEstudiante(idUsuario);

					reclamo.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
					reclamo.setEstado("INGRESADO");

					reclamo.setCreditosReclamados(Integer.parseInt(textCreditos.getText()));

					int idInt = Integer.parseInt(String.valueOf(comboDocente.getSelectedItem().toString().charAt(0)));
					// obtenemos el long del docente seleccionado
					long idDocente = new Long(idInt);

					reclamo.setDocente_id(idDocente);

					reclamo.setFechaInicioActividad(dateChooser.getDatoFecha());
					reclamo.setSemestre(Integer.parseInt(textSemestre.getText()));
					reclamo.setNombreActividadAPE(textNombreActividadEvento.getText());
				}
				try {
					
					if(reclamoExistente != null) {
						
						reclamo.setId_reclamo(reclamoExistente.getId_reclamo());
						
						
						ConfirmacionPopUpModificarReclamo.reclamo = reclamo;
						ConfirmacionPopUpModificarReclamo confirmacionPopUp = new ConfirmacionPopUpModificarReclamo();
						
						confirmacionPopUp.setVisible(true);
				
						
					}else {
						DAOGeneral.reclamoBean.crear(reclamo);
						JOptionPane.showMessageDialog(null, "Reclamo registrado con éxito", null,
								JOptionPane.PLAIN_MESSAGE);

					}
					reclamoExistente = null;
					setVisible(false);

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistrar.setBounds(222, 388, 105, 21);
		contentPane.add(btnRegistrar);

		textTitulo = new JTextField();
		textTitulo.setBounds(27, 120, 96, 19);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(24, 97, 105, 13);
		contentPane.add(lblTitulo);

		textCreditos.setVisible(false);
		comboDocente.setVisible(false);
		textSemestre.setVisible(false);
		dateChooser.setVisible(false);

		lblNombreActividadEvento.setVisible(false);
		lblDocente.setVisible(false);
		lblCreditos.setVisible(false);
		lblSemestre.setVisible(false);
		lblFecha.setVisible(false);
		textNombreActividadEvento.setVisible(false);

		comboDocente.setModel(cargarComboDocentes());
		
		if(reclamoExistente != null) {
			btnRegistrar.setText("Actualizar");
		}
		
		if(reclamoExistente!=null) {
			
			comboTipoReclamo.setSelectedItem(reclamoExistente.getTipo());
			textAreaReclamo.setText(reclamoExistente.getDetalle());
			textTitulo.setText(reclamoExistente.getTitulo());
			if(reclamoExistente.getTipo().equals("VME")) {
				
				Tutor tutor = DAOGeneral.tutorBean.obtenerPorUsuario(reclamoExistente.getDocente_id());				
				comboDocente.setSelectedItem("" + tutor.getId_usuario() + " - " + tutor.getNombre1() + " " + tutor.getApellido1());
				textSemestre.setText(""+reclamoExistente.getSemestre());
				textNombreActividadEvento.setText(reclamoExistente.getNombreEventoVME());
				dateChooser.setDatoFecha(reclamoExistente.getFechaInicioActividad());
				textCreditos.setText(""+reclamoExistente.getCreditosReclamados());


			} else if(reclamoExistente.getTipo().equals("APE")) {
				
				
				Tutor tutor = DAOGeneral.tutorBean.obtenerPorUsuario(reclamoExistente.getDocente_id());				
				comboDocente.setSelectedItem("" + tutor.getId_usuario() + " - " + tutor.getNombre1() + " " + tutor.getApellido1());
				textSemestre.setText(""+reclamoExistente.getSemestre());
				textNombreActividadEvento.setText(reclamoExistente.getNombreActividadAPE());
				dateChooser.setDatoFecha(reclamoExistente.getFechaInicioActividad());
				textCreditos.setText(""+reclamoExistente.getCreditosReclamados());

			} else if(reclamoExistente.getTipo().equals("Optativas")) {
				Tutor tutor = DAOGeneral.tutorBean.obtenerPorUsuario(reclamoExistente.getDocente_id());				
				comboDocente.setSelectedItem("" + tutor.getId_usuario() + " - " + tutor.getNombre1() + " " + tutor.getApellido1());
				textSemestre.setText(""+reclamoExistente.getSemestre());
				textNombreActividadEvento.setText(reclamoExistente.getNombreActividadAPE());
				dateChooser.setDatoFecha(reclamoExistente.getFechaInicioActividad());
				textCreditos.setText(""+reclamoExistente.getCreditosReclamados());
			}
			
			
		}
		
	}

	private DefaultComboBoxModel cargarComboDocentes() throws ServiciosException {
		// método para hacer aparecer el comboBoxFiltroCriterio y darle los datos a
		// mostrar
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		modeloCombo.addElement("Seleccione item");

		List<Tutor> docentes = DAOGeneral.tutorBean.obtenerTodos();

		for (Tutor t : docentes) {

			modeloCombo.addElement("" + t.getId_usuario() + " - " + t.getNombre1() + " " + t.getApellido1());
		}

		return modeloCombo;

	}
}

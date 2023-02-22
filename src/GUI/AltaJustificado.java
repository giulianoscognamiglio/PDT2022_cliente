package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import com.entities.ConvocatoriaAsistencia;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.ITR;
import com.entities.Justificado;
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

public class AltaJustificado extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre3;
	DefaultComboBoxModel modeloITR;
	static Justificado justificadoExistente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaJustificado frame = new AltaJustificado();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public AltaJustificado() {

		Estudiante estudiante = DAOGeneral.estudianteBean.obtenerPorUsuario(PanelMenu.usuarioIngresado.getId_usuario());
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());

		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 582, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBoxEvento = new JComboBox();
		comboBoxEvento.setBounds(207, 20, 283, 21);
		contentPane.add(comboBoxEvento);

		Panel panel = new Panel();
		panel.setColorBackground(UIManager.getColor("Button.background"));
		panel.setBounds(588, 115, 315, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblJustificado = new JLabel("Justificacion:");
		lblJustificado.setBounds(57, 20, 175, 13);
		contentPane.add(lblJustificado);

		TextArea textAreaJustificado = new TextArea();
		textAreaJustificado.setBounds(30, 116, 175, 106);
		contentPane.add(textAreaJustificado);

		JButton btnRegistrarse = new JButton("Enviar");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Justificado justificado = new Justificado();

				// Usamos esto para obtener la fecha de ahora y agregarle una hora neutra para
				// parsearlo de LocalDate a Date
				LocalDate localDate = LocalDate.now();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				
				int idEventoInt = Character.getNumericValue(comboBoxEvento.getSelectedItem().toString().charAt(0));
				
				Long idEventoLong = new Long(idEventoInt);
				
				Evento evento = DAOGeneral.eventosBean.obtenerPorId(idEventoLong);

				// necesitamos parsear esto porque en la clase usuario tenemos long pero para la
				// base precisamos Long
				
				Long idUsuario = new Long(PanelMenu.usuarioIngresado.getId_usuario());
				
				Estudiante estudiante = DAOGeneral.estudianteBean.obtenerPorUsuario(idUsuario);
				
				justificado.setDetalle(textAreaJustificado.getText());
				justificado.setEstudiante(estudiante.getId_estudiante());
				justificado.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
				justificado.setEstado("INGRESADO");
				justificado.setId_evento(evento.getId_evento());

				try {
					
					if(justificadoExistente!=null) {
						
						justificado.setId_justificado(justificadoExistente.getId_justificado());
						
						ConfirmacionPopUpModificarJustificado.justificado = justificado;
						ConfirmacionPopUpModificarJustificado confirmacionPopUp = new ConfirmacionPopUpModificarJustificado();
						
						confirmacionPopUp.setVisible(true);
					} else {
						DAOGeneral.justificadoBean.crear(justificado);
					JOptionPane.showMessageDialog(null, "Justificación registrada con éxito", null,
							JOptionPane.PLAIN_MESSAGE);
					}
					
					
					justificadoExistente = null;

					setVisible(false);

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistrarse.setBounds(149, 288, 105, 21);
		contentPane.add(btnRegistrarse);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(30, 288, 105, 21);
		contentPane.add(btnVolver);

		
		
		try {
			comboBoxEvento.setModel(cargarComboEventos(estudiante.getId_estudiante()));
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(justificadoExistente != null) {
			btnRegistrarse.setText("Actualizar");
		}
		
		
		if(justificadoExistente!=null) {
			
			Evento evento = DAOGeneral.eventosBean.obtenerPorId(justificadoExistente.getId_evento());
			
			textAreaJustificado.setText(justificadoExistente.getDetalle());
			comboBoxEvento.setSelectedItem(""+justificadoExistente.getId_evento()+" "+ evento.getTitulo());	
			
		}
		
	}

	private DefaultComboBoxModel cargarComboEventos(Long id_estudiante) throws ServiciosException {
		
		System.out.println(id_estudiante);
		// mï¿½todo para hacer aparecer el comboBoxFiltroCriterio y darle los datos a
		// mostrar
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		modeloCombo.addElement("Seleccione evento");

		List<ConvocatoriaAsistencia> convocatorias = DAOGeneral.convocatoriaBean
				.obtenerPorEstudiante(id_estudiante);
		
		System.out.println("lista CA >>" + convocatorias.toString());
		List<Evento> eventos = new ArrayList<Evento>();

		for (ConvocatoriaAsistencia cA : convocatorias) {

			eventos.add(DAOGeneral.eventosBean.obtenerPorId(cA.getId_evento()));
		}
		System.out.println("eventos post for>>>"+ eventos.toString());
		for(Evento e : eventos) {
			modeloCombo.addElement("" + e.getId_evento() + " " + e.getTitulo());
		}

		return modeloCombo;

	}
}

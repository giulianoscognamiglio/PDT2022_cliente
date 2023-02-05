package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextArea;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.*;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetalleReclamo extends JFrame {

	private JPanel contentPane;
	public static Reclamo reclamo;
	public LocalDate fechaReclamo;
	public Usuario usuario;
	private Estudiante estudiante = DAOGeneral.estudianteBean.obtenerPorUsuario(PanelMenu.usuarioIngresado.getId_usuario());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalleReclamo frame = new DetalleReclamo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ServiciosException 
	 */
	public DetalleReclamo() throws ServiciosException {
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 673, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblFuncionalidadesRol = new JLabel("Reclamo " + reclamo.getId_reclamo()+":");
		lblFuncionalidadesRol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionalidadesRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionalidadesRol.setBounds(10, 60, 427, 39);
		contentPane.add(lblFuncionalidadesRol);
		
		//aca agarramos la fecha que nos trae de la base y parseamos a LocalDate
		fechaReclamo = Instant.ofEpochMilli(reclamo.getFecha().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		
		JLabel lblFecha = new JLabel("Fecha de registro: " + fechaReclamo);
		lblFecha.setBounds(37, 109, 273, 13);
		contentPane.add(lblFecha);
		
		usuario = DAOGeneral.usuarioBean.obtenerPorId(reclamo.getEstudiante());
		
		JLabel lblAlumno = new JLabel("De alumno "+ usuario.getNombre1()+" "+usuario.getApellido1());
		lblAlumno.setBounds(37, 135, 273, 13);
		contentPane.add(lblAlumno);
		setLocationRelativeTo(null);
		
		JLabel lblDocumento = new JLabel("Con documento " + usuario.getCedula());
		lblDocumento.setBounds(37, 155, 273, 13);
		contentPane.add(lblDocumento);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(88, 505, 85, 21);
		contentPane.add(btnVolver);
		
		TextArea textDetalle = new TextArea();
		textDetalle.setEditable(false);
		textDetalle.setBounds(37, 207, 273, 77);
		contentPane.add(textDetalle);
		textDetalle.setText(reclamo.getDetalle());
		
		JLabel lblDetalle = new JLabel("Descripci\u00F3n");
		lblDetalle.setBounds(37, 178, 107, 13);
		contentPane.add(lblDetalle);
		
		JLabel lblTipo = new JLabel("Tipo de reclamo: " + reclamo.getTipo());
		lblTipo.setBounds(364, 109, 194, 13);
		contentPane.add(lblTipo);
		
//		if(estudiante!=null) {
			JButton btnModificar = new JButton("Modificar reclamo");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					AltaReclamo.reclamoExistente = reclamo;
					AltaReclamo altaReclamo;
					try {
						
						altaReclamo = new AltaReclamo();
						altaReclamo.setVisible(true);

					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnModificar.setBounds(426, 421, 182, 21);
			contentPane.add(btnModificar);
//		}
		
		
		
		if(!(reclamo.getTipo().equals("Otros"))) {
			JLabel lblCreditos = new JLabel("Creditos a reclamar: " + reclamo.getCreditosReclamados());
			lblCreditos.setBounds(37, 425, 154, 13);
			contentPane.add(lblCreditos);
			
			LocalDate fechaInicioActividad = Instant.ofEpochMilli(reclamo.getFechaInicioActividad().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

			JLabel lblFechaInicio = new JLabel("Fecha de inicio de actividad: " + fechaInicioActividad );
			lblFechaInicio.setBounds(37, 375, 230, 13);
			contentPane.add(lblFechaInicio);
			
			Usuario docente = DAOGeneral.usuarioBean.obtenerPorId(reclamo.getDocente_id());
			JLabel lblDocente = new JLabel("Docente a cargo: " + docente.getNombre1() +" " + docente.getApellido1());
			lblDocente.setBounds(37, 345, 466, 13);
			contentPane.add(lblDocente);
			
			if(reclamo.getTipo().equals("APE")) {
				JLabel lblAPE = new JLabel("Nombre actividad APE: " + reclamo.getNombreActividadAPE());
				lblAPE.setBounds(37, 309, 466, 13);
				contentPane.add(lblAPE);
			}
			
			if(reclamo.getTipo().equals("VME")) {
				JLabel lblVME = new JLabel("Nombre evento VME: " + reclamo.getNombreEventoVME());
				lblVME.setBounds(37, 309, 238, 13);
				contentPane.add(lblVME);
			}
			
		}
		
		
		
		
		
	
	}
}

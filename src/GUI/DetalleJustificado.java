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

public class DetalleJustificado extends JFrame{

	private JPanel contentPane;
	public static Justificado justificado;
	public LocalDate fechaJustificado;
	public Usuario usuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalleJustificado frame = new DetalleJustificado();
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
	public DetalleJustificado() throws ServiciosException {
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblFuncionalidadesRol = new JLabel("Justificado " + justificado.getId_justificado()+":");
		lblFuncionalidadesRol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionalidadesRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionalidadesRol.setBounds(10, 60, 427, 39);
		contentPane.add(lblFuncionalidadesRol);
		
		//aca agarramos la fecha que nos trae de la base y parseamos a LocalDate
		fechaJustificado = Instant.ofEpochMilli(justificado.getFecha().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		
		JLabel lblFecha = new JLabel("Fecha de registro: " + fechaJustificado);
		lblFecha.setBounds(88, 109, 273, 13);
		contentPane.add(lblFecha);
		
		usuario = DAOGeneral.usuarioBean.obtenerPorId(justificado.getEstudiante());
		
		JLabel lblAlumno = new JLabel("De alumno "+ usuario.getNombre1()+" "+usuario.getApellido1());
		lblAlumno.setBounds(88, 135, 273, 13);
		contentPane.add(lblAlumno);
		setLocationRelativeTo(null);
		
		JLabel lblDocumento = new JLabel("Con documento " + usuario.getCedula());
		lblDocumento.setBounds(88, 155, 273, 13);
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
		textDetalle.setBounds(88, 203, 273, 246);
		contentPane.add(textDetalle);
		textDetalle.setText(justificado.getDetalle());
		
		
		
		
	
	}
}

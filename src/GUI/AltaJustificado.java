package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 304, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setColorBackground(UIManager.getColor("Button.background"));
		panel.setBounds(588, 115, 315, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblJustificado = new JLabel("Justificado:");
		lblJustificado.setBounds(57, 20, 175, 13);
		contentPane.add(lblJustificado);
		

		TextArea textAreaJustificado = new TextArea();
		textAreaJustificado.setBounds(30, 62, 241, 150);
		contentPane.add(textAreaJustificado);
		
		JButton btnRegistrarse = new JButton("Enviar");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Justificado justificado = new Justificado();
				
				//Usamos esto para obtener la fecha de ahora y agregarle una hora neutra para parsearlo de LocalDate a Date
				LocalDate localDate = LocalDate.now();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				
				
				//necesitamos parsear esto porque en la clase usuario tenemos long pero para la base precisamos Long
				Long idUsuario = new Long(PanelMenu.usuarioIngresado.getId_usuario());
				justificado.setDetalle(textAreaJustificado.getText());
				justificado.setEstudiante(idUsuario);
				justificado.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
				justificado.setEstado("INGRESADO");
				
				try {
					DAOGeneral.justificadoBean.crear(justificado);
					JOptionPane.showMessageDialog(null, "Justificado registrado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				
					setVisible(false);

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
			}
		);
		btnRegistrarse.setBounds(166, 235, 105, 21);
		contentPane.add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(37, 235, 105, 21);
		contentPane.add(btnVolver);
		
		
		
		

	}
		}

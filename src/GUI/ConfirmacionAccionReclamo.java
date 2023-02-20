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

import com.entities.AccionJustificacion;
import com.entities.AccionReclamo;
import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.ITR;
import com.entities.Justificado;
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
import javax.swing.SwingConstants;

public class ConfirmacionAccionReclamo extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre2;
	public static Reclamo reclamo;
	public static Justificado justificado;
	public static AccionReclamo accionReclamo;
	public static AccionJustificacion accionJustificacion;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmacionAccionReclamo frame = new ConfirmacionAccionReclamo();

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

	public ConfirmacionAccionReclamo() {

		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 303, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());

		Panel panel = new Panel();
		panel.setColorBackground(UIManager.getColor("Button.background"));
		panel.setBounds(588, 115, 315, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (reclamo != null) {
						DAOGeneral.reclamoBean.actualizar(reclamo);

						DAOGeneral.accionesReclamoBean.crear(accionReclamo);

						JOptionPane.showMessageDialog(null, "Reclamo modificado con éxito", null,
								JOptionPane.PLAIN_MESSAGE);

						PanelGestionReclamos pgr = PanelGestionReclamos.getInstance();
						
						pgr.cargarTabla(DAOGeneral.reclamoBean.obtenerTodos());
						
						reclamo = null;
						setVisible(false);
					}
					
					if (justificado != null) {
						DAOGeneral.justificadoBean.actualizar(justificado);

						DAOGeneral.accionesJustificadoBean.crear(accionJustificacion);

						JOptionPane.showMessageDialog(null, "Modificación exitosa", null,
								JOptionPane.PLAIN_MESSAGE);

						PanelGestionJustificados pgr = PanelGestionJustificados.getInstance();
						
						pgr.getInstance().cargarTabla(DAOGeneral.justificadoBean.obtenerTodos());
						
						justificado = null;
						setVisible(false);
					}

					

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(142, 74, 105, 21);
		contentPane.add(btnConfirmar);

		JButton btnVolver = new JButton("Cancelar");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(22, 74, 105, 21);
		contentPane.add(btnVolver);

		JLabel lblReclamo = new JLabel("Seguro de que desea modificar el estado de este reclamo?");
		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamo.setBounds(10, 10, 278, 43);
		contentPane.add(lblReclamo);

	}
}

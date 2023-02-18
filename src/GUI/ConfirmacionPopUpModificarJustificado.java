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
import javax.swing.SwingConstants;

public class ConfirmacionPopUpModificarJustificado extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre3;
	public static Justificado justificado;
	DefaultComboBoxModel modeloITR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmacionPopUpModificarJustificado frame = new ConfirmacionPopUpModificarJustificado();
					
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

	public ConfirmacionPopUpModificarJustificado() {
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 290, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setColorBackground(UIManager.getColor("Button.background"));
		panel.setBounds(588, 115, 315, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnRegistrarse = new JButton("Confirmar");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//Utilizamos un atributo static del tipo Justificado para eliminar si se oprime este boton
					DAOGeneral.justificadoBean.actualizar(justificado);
					
					JOptionPane.showMessageDialog(null, "Justificado modificado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				
					AltaJustificado.justificadoExistente = null;
					setVisible(false);

				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
			}
		);
		btnRegistrarse.setBounds(146, 115, 105, 21);
		contentPane.add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(26, 115, 105, 21);
		contentPane.add(btnVolver);
		
		JLabel lblJustificado = new JLabel("Seguro de que desea modificar este justificado?");
		lblJustificado.setHorizontalAlignment(SwingConstants.CENTER);
		lblJustificado.setBounds(10, 10, 256, 43);
		contentPane.add(lblJustificado);
		
		
		
		

	}
		}

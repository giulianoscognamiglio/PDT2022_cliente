package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;
import necesario.Panel;
import rojeru_san.rsdate.RSDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegistroITR extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombreITR;
	DefaultComboBoxModel modeloITR;
	DefaultComboBoxModel modeloDepartamento;
	JComboBox comboBoxTipoDeUsuario;
	public ITR itr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroITR frame = new RegistroITR();
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

	public RegistroITR() {

		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());

		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 494, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreITR = new JLabel("Nombre de ITR");
		lblNombreITR.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreITR.setBounds(150, 99, 175, 13);
		contentPane.add(lblNombreITR);

		textFieldNombreITR = new JTextField();
		textFieldNombreITR.setBounds(150, 118, 175, 19);
		contentPane.add(textFieldNombreITR);
		textFieldNombreITR.setColumns(10);

		JComboBox comboBoxDepartamento = new JComboBox();
		modeloDepartamento = new DefaultComboBoxModel();
		comboBoxDepartamento.setModel(modeloDepartamento);
		comboBoxDepartamento.setBounds(150, 177, 175, 21);
		contentPane.add(comboBoxDepartamento);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamento.setBounds(150, 159, 175, 13);
		contentPane.add(lblDepartamento);
		modeloITR = new DefaultComboBoxModel();

		JButton btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelListaITRs panelListaITRs = PanelListaITRs.getInstance();
				ITR ITRIngresado= new ITR();
					try{
						ITRIngresado.setNombre(controlNombre(textFieldNombreITR.getText()).toString());
						ITRIngresado.setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
						ITRIngresado.setActivo("Y");
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
					ITR nombreITR = null;
					try {
						nombreITR = DAOGeneral.itrBean.obtenerPorNombre(ITRIngresado.getNombre());
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					if (nombreITR == null) {
						try {
							DAOGeneral.itrBean.crear(ITRIngresado);
							JOptionPane.showMessageDialog(null, "ITR creado con éxito", null, JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
						} catch (ServiciosException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Ya existe en el sistema un ITR registrado con este nombre", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					PanelListaITRs.getInstance().cargarTabla(DAOGeneral.itrBean.obtenerTodos());
		}});
		btnCrear.setBounds(249, 316, 105, 21);
		contentPane.add(btnCrear);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(114, 316, 105, 21);
		contentPane.add(btnVolver);

		cargarComboBox();

	}

	public void cargarComboBox() {
		modeloDepartamento.removeAllElements();
		modeloDepartamento.addElement("");
		for (Departamento departamento : DAOGeneral.departamentoBean.obtenerTodos()) {
			modeloDepartamento.addElement(departamento.getNombre());
		}
	}
	
	public String controlNombre(String nombre)throws Exception{
		if(nombre.length()>50) {
			throw new Exception("El campo Nombre de ITR debe contener menos de 50 caracteres.");
		}
		return nombre;
	}

}

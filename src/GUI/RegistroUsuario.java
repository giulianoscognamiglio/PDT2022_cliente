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

public class RegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre1;
	private JTextField textFieldNombre2;
	private JLabel lblNombre2;
	private JLabel lblApellido1;
	private JTextField textFieldApellido1;
	private JLabel lblApellido2;
	private JTextField textFieldApellido2;
	private JLabel lblDocumento;
	private JTextField textFieldDocumento;
	private JLabel lblContraseña;
	private JTextField textFieldContraseña;
	private JLabel lblCelular;
	private JTextField textFieldCelular;
	private JTextField textFieldEmailPersonal;
	private JTextField textFieldEmailUTEC;
	private JTextField textFieldLocalidad;
	DefaultComboBoxModel modeloITR;
	DefaultComboBoxModel modeloDepartamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuario frame = new RegistroUsuario();
					
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

	public RegistroUsuario() {
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		DAOGeneral.initConexion();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 907, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setColorBackground(UIManager.getColor("Button.background"));
		panel.setBounds(588, 115, 315, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setBounds(75, 96, 175, 13);
		contentPane.add(lblNombre1);
		
		textFieldNombre1 = new JTextField();
		textFieldNombre1.setBounds(75, 115, 175, 19);
		contentPane.add(textFieldNombre1);
		textFieldNombre1.setColumns(10);
		
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setColumns(10);
		textFieldNombre2.setBounds(74, 174, 176, 19);
		contentPane.add(textFieldNombre2);
		
		lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setBounds(74, 155, 176, 13);
		contentPane.add(lblNombre2);
		
		lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setBounds(76, 203, 174, 13);
		contentPane.add(lblApellido1);
		
		textFieldApellido1 = new JTextField();
		textFieldApellido1.setColumns(10);
		textFieldApellido1.setBounds(76, 222, 174, 19);
		contentPane.add(textFieldApellido1);
		
		lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setBounds(75, 262, 175, 13);
		contentPane.add(lblApellido2);
		
		textFieldApellido2 = new JTextField();
		textFieldApellido2.setColumns(10);
		textFieldApellido2.setBounds(75, 281, 175, 19);
		contentPane.add(textFieldApellido2);
		
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(75, 310, 175, 13);
		contentPane.add(lblDocumento);
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(75, 329, 175, 19);
		contentPane.add(textFieldDocumento);
		
		lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(74, 369, 176, 13);
		contentPane.add(lblContraseña);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(74, 388, 176, 19);
		contentPane.add(textFieldContraseña);
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(75, 422, 175, 13);
		contentPane.add(lblCelular);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(75, 441, 175, 19);
		contentPane.add(textFieldCelular);
		
		RSDateChooser dateChooser = new RSDateChooser();
		dateChooser.setPlaceholder("");
		dateChooser.setColorButtonHover(Color.BLACK);
		dateChooser.setColorDiaActual(Color.BLACK);
		dateChooser.setColorForeground(Color.BLACK);
		dateChooser.setColorBackground(Color.BLACK);
		dateChooser.setBounds(280, 115, 211, 19);
		contentPane.add(dateChooser);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(280, 96, 175, 13);
		contentPane.add(lblFechaNacimiento);
		
		JLabel lblEmailPersonal = new JLabel("Email Personal");
		lblEmailPersonal.setBounds(280, 155, 175, 13);
		contentPane.add(lblEmailPersonal);
		
		textFieldEmailPersonal = new JTextField();
		textFieldEmailPersonal.setColumns(10);
		textFieldEmailPersonal.setBounds(280, 174, 175, 19);
		contentPane.add(textFieldEmailPersonal);
		
		JLabel lblEmailUTEC = new JLabel("Email UTEC");
		lblEmailUTEC.setBounds(280, 203, 175, 13);
		contentPane.add(lblEmailUTEC);
		
		textFieldEmailUTEC = new JTextField();
		textFieldEmailUTEC.setColumns(10);
		textFieldEmailUTEC.setBounds(280, 222, 175, 19);
		contentPane.add(textFieldEmailUTEC);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(280, 262, 175, 13);
		contentPane.add(lblLocalidad);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(280, 281, 175, 19);
		contentPane.add(textFieldLocalidad);
		
		JComboBox comboBoxDepartamento = new JComboBox();
		modeloDepartamento = new DefaultComboBoxModel();
		comboBoxDepartamento.setModel(modeloDepartamento);
		comboBoxDepartamento.setBounds(280, 328, 175, 21);
		contentPane.add(comboBoxDepartamento);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(280, 310, 175, 13);
		contentPane.add(lblDepartamento);
		
		JLabel lblITR = new JLabel("ITR");
		lblITR.setBounds(280, 369, 175, 13);
		contentPane.add(lblITR);
		
		JComboBox comboBoxITR = new JComboBox();
		modeloITR = new DefaultComboBoxModel();
		comboBoxITR.setModel(modeloITR);
		comboBoxITR.setBounds(280, 387, 175, 21);
		contentPane.add(comboBoxITR);
		JRadioButton rdbtnEstudiante = new JRadioButton("Estudiante");
		JRadioButton rdbtnTutor = new JRadioButton("Tutor");
		JRadioButton rdbtnAnalista = new JRadioButton("Analista");
		
//		cargarComboBox();
		
		rdbtnTutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				rdbtnEstudiante.setSelected(false);
				rdbtnTutor.setSelected(true);
				rdbtnAnalista.setSelected(false);
				
				PanelRegistroExtra extra = new PanelRegistroExtra();
				panel.removeAll();
				extra.setSize(315, 79);
				extra.setLocation(0, 0);
				extra.registroExtraTutor();
				panel.add(extra);
				panel.revalidate();
				panel.repaint();
				
			}
		});
		rdbtnTutor.setBounds(378, 440, 85, 21);
		contentPane.add(rdbtnTutor);
		
		
		rdbtnAnalista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				rdbtnEstudiante.setSelected(false);
				rdbtnTutor.setSelected(false);
				rdbtnAnalista.setSelected(true);
				
				PanelRegistroExtra extra = new PanelRegistroExtra();
				panel.removeAll();
				extra.setSize(315, 79);
				extra.setLocation(0, 0);
				extra.registroExtraAnalista();
				panel.add(extra);
				panel.revalidate();
				panel.repaint();
				
			}
		});
		rdbtnAnalista.setBounds(460, 440, 85, 21);
		contentPane.add(rdbtnAnalista);
		
		rdbtnEstudiante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				rdbtnEstudiante.setSelected(true);
				rdbtnTutor.setSelected(false);
				rdbtnAnalista.setSelected(false);
				
				PanelRegistroExtra extra = new PanelRegistroExtra();
				panel.removeAll();
				extra.setSize(315, 79);
				extra.setLocation(0, 0);
				extra.registroExtraEstudiante();
				panel.add(extra);
				panel.revalidate();
				panel.repaint();
				
			}
		});
		rdbtnEstudiante.setBounds(280, 440, 96, 21);
		contentPane.add(rdbtnEstudiante);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setBounds(280, 422, 96, 13);
		contentPane.add(lblTipoDeUsuario);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				Usuario usuarioIngresado;
				if (rdbtnAnalista.isSelected()) {
					usuarioIngresado = new Analista();
					usuarioIngresado.setNombre1(textFieldNombre1.getText());
					usuarioIngresado.setNombre2(textFieldNombre2.getText());
					usuarioIngresado.setApellido1(textFieldApellido1.getText());
					usuarioIngresado.setApellido2(textFieldApellido2.getText());
					usuarioIngresado.setCedula(textFieldDocumento.getText());
					usuarioIngresado.setTelefono(textFieldCelular.getText());
					usuarioIngresado.setFechaNac(dateChooser.getDatoFecha());
					usuarioIngresado.setMail(textFieldEmailPersonal.getText());
					usuarioIngresado.setMailInstitucional(textFieldEmailUTEC.getText());
		
					usuarioIngresado.setContrasena(textFieldContraseña.getText());
					String usuario = textFieldEmailUTEC.getText().split("@")[0];
					usuarioIngresado.setUsuario(usuario);
					try {
						usuarioIngresado.setRol(DAOGeneral.rolBean.buscarNombre("Analista"));
						usuarioIngresado.setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
						usuarioIngresado.setItr(DAOGeneral.itrBean.obtenerPorNombre(comboBoxITR.getSelectedItem().toString()));
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					usuarioIngresado.setLocalidad(textFieldLocalidad.getText());
					usuarioIngresado.setValidado("0");
					usuarioIngresado.setActivo("0");
					
					Usuario checkeo = null;

					try {
						 checkeo = DAOGeneral.usuarioBean.obtenerPorDocumento(usuarioIngresado.getCedula());
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					
					if(checkeo == null){
						try {
							DAOGeneral.usuarioBean.crear(usuarioIngresado);
							JOptionPane.showMessageDialog(null, "Usuario Analista creado con éxito", null, JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
						} catch (ServiciosException e1) {
							e1.printStackTrace();
						}
					}else {
							JOptionPane.showMessageDialog(null, "Ya existe en el sistema un usuario registrado con este documento", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else if (rdbtnEstudiante.isSelected()) {
					usuarioIngresado = new Estudiante();
					usuarioIngresado.setNombre1(textFieldNombre1.getText());
					usuarioIngresado.setNombre2(textFieldNombre2.getText());
					usuarioIngresado.setApellido1(textFieldApellido1.getText());
					usuarioIngresado.setApellido2(textFieldApellido2.getText());
					usuarioIngresado.setCedula(textFieldDocumento.getText());
					usuarioIngresado.setTelefono(textFieldCelular.getText());
					usuarioIngresado.setFechaNac(dateChooser.getDatoFecha());
					usuarioIngresado.setMail(textFieldEmailPersonal.getText());
					usuarioIngresado.setMailInstitucional(textFieldEmailUTEC.getText());
					usuarioIngresado.setContrasena(textFieldContraseña.getText());
					String usuario = textFieldEmailUTEC.getText().split("@")[0];
					usuarioIngresado.setUsuario(usuario);
					try {
						usuarioIngresado.setRol(DAOGeneral.rolBean.buscarNombre("Estudiante"));
						usuarioIngresado.setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
						usuarioIngresado.setItr(DAOGeneral.itrBean.obtenerPorNombre(comboBoxITR.getSelectedItem().toString()));
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					usuarioIngresado.setLocalidad(textFieldLocalidad.getText());
					usuarioIngresado.setValidado("0");
					usuarioIngresado.setActivo("0");
					((Estudiante)usuarioIngresado).setGeneracion(PanelRegistroExtra.yearDate.getYear()+"");
					
					Usuario checkeo = null;

					try {
						 checkeo = DAOGeneral.usuarioBean.obtenerPorDocumento(usuarioIngresado.getCedula());
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					
					if(checkeo == null){
						try {
							DAOGeneral.usuarioBean.crear(usuarioIngresado);
							JOptionPane.showMessageDialog(null, "Usuario Estudiante creado con éxito", null, JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
						} catch (ServiciosException e1) {
							e1.printStackTrace();
						}
					}else {
							JOptionPane.showMessageDialog(null, "Ya existe en el sistema un usuario registrado con este documento", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else if (rdbtnTutor.isSelected()) {
					usuarioIngresado = new Tutor();
					usuarioIngresado.setNombre1(textFieldNombre1.getText());
					usuarioIngresado.setNombre2(textFieldNombre2.getText());
					usuarioIngresado.setApellido1(textFieldApellido1.getText());
					usuarioIngresado.setApellido2(textFieldApellido2.getText());
					usuarioIngresado.setCedula(textFieldDocumento.getText());
					usuarioIngresado.setTelefono(textFieldCelular.getText());
					usuarioIngresado.setFechaNac(dateChooser.getDatoFecha());
					usuarioIngresado.setMail(textFieldEmailPersonal.getText());
					usuarioIngresado.setMailInstitucional(textFieldEmailUTEC.getText());
					usuarioIngresado.setContrasena(textFieldContraseña.getText());
					String usuario = textFieldEmailUTEC.getText().split("@")[0];
					usuarioIngresado.setUsuario(usuario);
					usuarioIngresado.setLocalidad(textFieldLocalidad.getText());
					usuarioIngresado.setValidado("0");
					usuarioIngresado.setActivo("0");
					try {
						usuarioIngresado.setRol(DAOGeneral.rolBean.buscarNombre("Tutor"));
						usuarioIngresado.setItr(DAOGeneral.itrBean.obtenerPorNombre(comboBoxITR.getSelectedItem().toString()));
						usuarioIngresado.setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
						((Tutor)usuarioIngresado).setArea(DAOGeneral.areaBean.obtenerPorNombre(PanelRegistroExtra.comboBoxAreaTutor.getSelectedItem().toString()));
						((Tutor)usuarioIngresado).setTipo(DAOGeneral.tipoTutorBean.obtenerPorNombre(PanelRegistroExtra.comboBoxTipoTutor.getSelectedItem().toString()));
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					
					Usuario checkeo = null;

					try {
						 checkeo = DAOGeneral.usuarioBean.obtenerPorDocumento(usuarioIngresado.getCedula());
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					
					if(checkeo == null){
						try {
							DAOGeneral.usuarioBean.crear(usuarioIngresado);
							JOptionPane.showMessageDialog(null, "Usuario Tutor creado con éxito", null, JOptionPane.PLAIN_MESSAGE);
							setVisible(false);
						} catch (ServiciosException e1) {
							e1.printStackTrace();
						}
					}else {
							JOptionPane.showMessageDialog(null, "Ya existe en el sistema un usuario registrado con este documento", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnRegistrarse.setBounds(280, 500, 105, 21);
		contentPane.add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(145, 500, 105, 21);
		contentPane.add(btnVolver);
		
		cargarComboBox();

	}
	
	public void cargarComboBox() {
		modeloITR.removeAllElements();
		modeloITR.addElement("");
		for(ITR itr : DAOGeneral.itrBean.obtenerTodos()) {
			modeloITR.addElement(itr.getNombre());
		}
		
		modeloDepartamento.removeAllElements();
		modeloDepartamento.addElement("");
		for(Departamento departamento: DAOGeneral.departamentoBean.obtenerTodos()) {
			modeloDepartamento.addElement(departamento.getNombre());
		}
	}
	
}

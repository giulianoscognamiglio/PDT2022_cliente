package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.ITR;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.sun.jdi.event.EventQueue;

import controlador.DAOGeneral;
import necesario.Panel;
import rojeru_san.rsdate.RSDateChooser;
import javax.swing.SwingConstants;

public class ActualizarUsuario extends JPanel {

	private Usuario usuarioIngresado;

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
	 * Create the frame.
	 */

	public ActualizarUsuario() {
		
		usuarioIngresado=MenuPrincipal.usuarioIngresado;
		setBounds(0, 0, 684, 581);
		setLayout(null);
		
		Panel panel = new Panel();
		panel.setColorBackground(Color.GRAY);
		panel.setBounds(11, 14, 1, 1);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setBounds(35, 53, 150, 13);
		add(lblNombre1);
		
		textFieldNombre1 = new JTextField();
		textFieldNombre1.setBounds(34, 75, 151, 19);
		add(textFieldNombre1);
		textFieldNombre1.setText(usuarioIngresado.getNombre1());
		
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setColumns(10);
		textFieldNombre2.setBounds(35, 127, 150, 19);
		add(textFieldNombre2);
		textFieldNombre2.setText(usuarioIngresado.getNombre2());
		
		lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setBounds(35, 104, 150, 13);
		add(lblNombre2);
		
		lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setBounds(35, 156, 150, 13);
		add(lblApellido1);
		
		textFieldApellido1 = new JTextField();
		textFieldApellido1.setColumns(10);
		textFieldApellido1.setBounds(35, 181, 150, 19);
		add(textFieldApellido1);
		textFieldApellido1.setText(usuarioIngresado.getApellido1());
		
		lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setBounds(35, 212, 150, 13);
		add(lblApellido2);
		
		textFieldApellido2 = new JTextField();
		textFieldApellido2.setColumns(10);
		textFieldApellido2.setBounds(35, 235, 150, 19);
		add(textFieldApellido2);
		textFieldApellido2.setText(usuarioIngresado.getApellido2());
		
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(35, 472, 150, 13);
		add(lblDocumento);
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(35, 495, 150, 19);
		add(textFieldDocumento);
		textFieldDocumento.setText(usuarioIngresado.getCedula());
		
		lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(35, 264, 150, 13);
		add(lblContraseña);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(35, 287, 150, 19);
		add(textFieldContraseña);
		textFieldContraseña.setText(usuarioIngresado.getContrasena());
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(35, 316, 150, 13);
		add(lblCelular);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(35, 339, 150, 19);
		add(textFieldCelular);
		textFieldCelular.setText(usuarioIngresado.getTelefono());
		
		RSDateChooser dateChooser = new RSDateChooser();
		dateChooser.setColorButtonHover(Color.BLACK);
		dateChooser.setColorDiaActual(Color.BLACK);
		dateChooser.setColorForeground(Color.BLACK);
		dateChooser.setColorBackground(Color.BLACK);
		dateChooser.setBounds(224, 235, 187, 19);
		add(dateChooser);
		dateChooser.setDatoFecha(usuarioIngresado.getFechaNac());
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(224, 210, 142, 13);
		add(lblFechaNacimiento);
		
		JLabel lblEmailPersonal = new JLabel("Email Personal");
		lblEmailPersonal.setBounds(35, 420, 150, 13);
		add(lblEmailPersonal);
		
		textFieldEmailPersonal = new JTextField();
		textFieldEmailPersonal.setColumns(10);
		textFieldEmailPersonal.setBounds(35, 443, 150, 19);
		add(textFieldEmailPersonal);
		textFieldEmailPersonal.setText(usuarioIngresado.getMail());
		
		JLabel lblEmailUTEC = new JLabel("Email UTEC");
		lblEmailUTEC.setBounds(35, 368, 150, 13);
		add(lblEmailUTEC);
		
		textFieldEmailUTEC = new JTextField();
		textFieldEmailUTEC.setColumns(10);
		textFieldEmailUTEC.setBounds(34, 391, 151, 19);
		add(textFieldEmailUTEC);
		textFieldEmailUTEC.setText(usuarioIngresado.getMailInstitucional());
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(224, 162, 142, 13);
		add(lblLocalidad);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(224, 181, 152, 19);
		add(textFieldLocalidad);
		textFieldLocalidad.setText(usuarioIngresado.getLocalidad());

		
		JComboBox comboBoxDepartamento = new JComboBox();
		modeloDepartamento = new DefaultComboBoxModel();
		comboBoxDepartamento.setModel(modeloDepartamento);
		comboBoxDepartamento.setBounds(224, 126, 152, 21);
		add(comboBoxDepartamento);
		
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(223, 108, 143, 13);
		add(lblDepartamento);
		
		JLabel lblITR = new JLabel("ITR");
		lblITR.setBounds(224, 53, 142, 13);
		add(lblITR);
		
		JComboBox comboBoxITR = new JComboBox();
		modeloITR = new DefaultComboBoxModel();
		comboBoxITR.setModel(modeloITR);
		comboBoxITR.setBounds(224, 74, 152, 21);
		add(comboBoxITR);
//		JRadioButton rdbtnEstudiante = new JRadioButton("Estudiante");
//		JRadioButton rdbtnTutor = new JRadioButton("Tutor");
//		JRadioButton rdbtnAnalista = new JRadioButton("Analista");
		
		cargarComboBox();
		comboBoxITR.setSelectedItem(usuarioIngresado.getItr().getNombre());
		comboBoxDepartamento.setSelectedItem(usuarioIngresado.getDepartamento().getNombre());
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
					usuarioIngresado.setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				usuarioIngresado.setLocalidad(textFieldLocalidad.getText());
				usuarioIngresado.setValidado("0");
				usuarioIngresado.setActivo("0");
				try {
					DAOGeneral.usuarioBean.actualizar(usuarioIngresado);
					JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(280, 494, 121, 21);
		add(btnActualizar);
		
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

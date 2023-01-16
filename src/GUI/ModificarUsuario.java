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

public class ModificarUsuario extends JPanel {

	public static Usuario usuarioIngresadoAct;

	public JPanel contentPane;
	public JLabel lblNombre2;
	public JLabel lblApellido1;
	public JLabel lblApellido2;
	public JLabel lblDocumento;
	public JLabel lblCelular;
	public JTextField textFieldCelular;
	public JTextField textFieldEmailPersonal;
	public JTextField textFieldEmailUTEC;
	public JTextField textFieldLocalidad;
	public JTextField textFieldApellido1;
	public JTextField textFieldNombre2;
	public JTextField textFieldNombre1;
	public JTextField textFieldApellido2;
	public JTextField textFieldDocumento;

	public DefaultComboBoxModel modeloITR;
	public DefaultComboBoxModel modeloDepartamento;

	public JComboBox comboBoxDepartamento;
	public RSDateChooser dateChooser;
	/**
	 * Create the frame.
	 */

	public ModificarUsuario() {
		
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
		textFieldNombre1.setText(getUsuarioIngresado().getNombre1());
		
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setColumns(10);
		textFieldNombre2.setBounds(35, 127, 150, 19);
		add(textFieldNombre2);
		textFieldNombre2.setText(getUsuarioIngresado().getNombre2());
		
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
		textFieldApellido1.setText(getUsuarioIngresado().getApellido1());
		
		lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setBounds(35, 212, 150, 13);
		add(lblApellido2);
		
		textFieldApellido2 = new JTextField();
		textFieldApellido2.setColumns(10);
		textFieldApellido2.setBounds(35, 235, 150, 19);
		add(textFieldApellido2);
		textFieldApellido2.setText(getUsuarioIngresado().getApellido2());
		
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(36, 421, 150, 13);
		add(lblDocumento);
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(36, 444, 150, 19);
		add(textFieldDocumento);
		textFieldDocumento.setText(getUsuarioIngresado().getCedula());
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(36, 265, 150, 13);
		add(lblCelular);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(36, 288, 150, 19);
		add(textFieldCelular);
		textFieldCelular.setText(getUsuarioIngresado().getTelefono());
		
		dateChooser = new RSDateChooser();
		dateChooser.setColorButtonHover(Color.BLACK);
		dateChooser.setColorDiaActual(Color.BLACK);
		dateChooser.setColorForeground(Color.BLACK);
		dateChooser.setColorBackground(Color.BLACK);
		dateChooser.setBounds(224, 235, 187, 19);
		add(dateChooser);
		dateChooser.setDatoFecha(getUsuarioIngresado().getFechaNac());
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(224, 210, 142, 13);
		add(lblFechaNacimiento);
		
		JLabel lblEmailPersonal = new JLabel("Email Personal");
		lblEmailPersonal.setBounds(36, 369, 150, 13);
		add(lblEmailPersonal);
		
		textFieldEmailPersonal = new JTextField();
		textFieldEmailPersonal.setColumns(10);
		textFieldEmailPersonal.setBounds(36, 392, 150, 19);
		add(textFieldEmailPersonal);
		textFieldEmailPersonal.setText(getUsuarioIngresado().getMail());
		
		JLabel lblEmailUTEC = new JLabel("Email UTEC");
		lblEmailUTEC.setBounds(36, 317, 150, 13);
		add(lblEmailUTEC);
		
		textFieldEmailUTEC = new JTextField();
		textFieldEmailUTEC.setColumns(10);
		textFieldEmailUTEC.setBounds(35, 340, 151, 19);
		add(textFieldEmailUTEC);
		textFieldEmailUTEC.setText(getUsuarioIngresado().getMailInstitucional());
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(224, 162, 142, 13);
		add(lblLocalidad);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(224, 181, 152, 19);
		add(textFieldLocalidad);
		textFieldLocalidad.setText(getUsuarioIngresado().getLocalidad());

		comboBoxDepartamento = new JComboBox();
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
		comboBoxITR.setSelectedItem(getUsuarioIngresado().getItr().getNombre());
		comboBoxDepartamento.setSelectedItem(getUsuarioIngresado().getDepartamento().getNombre());
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getUsuarioIngresado().setNombre1(textFieldNombre1.getText());
				getUsuarioIngresado().setNombre2(textFieldNombre2.getText());
				getUsuarioIngresado().setApellido1(textFieldApellido1.getText());
				getUsuarioIngresado().setApellido2(textFieldApellido2.getText());
				getUsuarioIngresado().setCedula(textFieldDocumento.getText());
				getUsuarioIngresado().setTelefono(textFieldCelular.getText());
				getUsuarioIngresado().setFechaNac(dateChooser.getDatoFecha());
				getUsuarioIngresado().setMail(textFieldEmailPersonal.getText());
				getUsuarioIngresado().setMailInstitucional(textFieldEmailUTEC.getText());
//				getUsuarioIngresado().setContrasena(textFieldContraseña.getText());
				String usuario = textFieldEmailUTEC.getText().split("@")[0];
				getUsuarioIngresado().setUsuario(usuario);
				
				try {
					getUsuarioIngresado().setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
				getUsuarioIngresado().setLocalidad(textFieldLocalidad.getText());
				getUsuarioIngresado().setValidado("0");
				getUsuarioIngresado().setActivo("0");
				try {
					DAOGeneral.usuarioBean.actualizar(getUsuarioIngresado());
					JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito", null, JOptionPane.PLAIN_MESSAGE);
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(391, 496, 121, 21);
		add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PanelBajaUsuario.getInstance().panelDinamico.removeAll();
				
				PanelBajaUsuario.getInstance().cargarTabla(DAOGeneral.usuarioBean.obtenerTodos());
				
				PanelBajaUsuario.getInstance().btnBaja.setVisible(true);

				PanelBajaUsuario.getInstance().btnModificación.setVisible(true);

				PanelBajaUsuario.getInstance().scrollPane.setVisible(true);

				PanelBajaUsuario.getInstance().revalidate();
				
				PanelBajaUsuario.getInstance().repaint();
				
			}
		});
		btnVolver.setBounds(260, 495, 121, 21);
		add(btnVolver);
		
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

	public Usuario getUsuarioIngresado() {
		return usuarioIngresadoAct;
		
	}

	public static void setUsuarioIngresado(Usuario usuarioIngresado) {
		usuarioIngresadoAct = usuarioIngresado;
		
	}
}

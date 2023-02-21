package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;
import necesario.Panel;
import rojeru_san.rsdate.RSDateChooser;

public class ActualizarUsuario extends JPanel {

	public static Usuario usuarioIngresadoAct;

	public JPanel contentPane;
	public JLabel lblNombre2;
	public JLabel lblApellido1;
	public JLabel lblApellido2;
	public JLabel lblDocumento;
	public JLabel lblContraseña;
	public JLabel lblCelular;
	public JLabel lblGeneracion;
	public JLabel lblRol;
	public JLabel lblArea;
	public JTextField textFieldCelular;
	public JTextField textFieldEmailPersonal;
	public JTextField textFieldEmailUTEC;
	public JTextField textFieldLocalidad;
	public JTextField textFieldApellido1;
	public JTextField textFieldNombre2;
	public JTextField textFieldNombre1;
	public JTextField textFieldApellido2;
	public JTextField textFieldDocumento;
	public JTextField textFieldContraseña;
	public JTextField textFieldGeneracion;
	public JTextField textFieldITR;
	public JTextField textFieldRol;
	public JTextField textFieldArea;
//	public DefaultComboBoxModel modeloITR;
	public DefaultComboBoxModel modeloDepartamento;
	public JComboBox comboBoxDepartamento;
	public RSDateChooser dateChooser;
	private JTextField textField;
	/**
	 * Create the frame.
	 */

	public ActualizarUsuario() {
		
		setUsuarioIngresado(MenuPrincipal.usuarioIngresado);
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
		lblDocumento.setBounds(35, 472, 150, 13);
		add(lblDocumento);
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(35, 495, 150, 19);
		add(textFieldDocumento);
		textFieldDocumento.setText(getUsuarioIngresado().getCedula());
		
		lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(35, 264, 150, 13);
		add(lblContraseña);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(35, 287, 150, 19);
		add(textFieldContraseña);
		textFieldContraseña.setText(getUsuarioIngresado().getContrasena());
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(35, 316, 150, 13);
		add(lblCelular);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(35, 339, 150, 19);
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
		lblEmailPersonal.setBounds(35, 420, 150, 13);
		add(lblEmailPersonal);
		
		textFieldEmailPersonal = new JTextField();
		textFieldEmailPersonal.setColumns(10);
		textFieldEmailPersonal.setBounds(35, 443, 150, 19);
		add(textFieldEmailPersonal);
		textFieldEmailPersonal.setText(getUsuarioIngresado().getMail());
		
		JLabel lblEmailUTEC = new JLabel("Email UTEC");
		lblEmailUTEC.setBounds(35, 368, 150, 13);
		add(lblEmailUTEC);
		
		textFieldEmailUTEC = new JTextField();
		textFieldEmailUTEC.setColumns(10);
		textFieldEmailUTEC.setBounds(34, 391, 151, 19);
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
		
//		JComboBox comboBoxITR = new JComboBox();
//		modeloITR = new DefaultComboBoxModel();
//		comboBoxITR.setModel(modeloITR);
		
		textFieldITR = new JTextField();
		textFieldITR.setBounds(224, 74, 152, 21);
		textFieldITR.setText(getUsuarioIngresado().getItr().getNombre());
		add(textFieldITR);
		textFieldITR.setEditable(false);
//		add(comboBoxITR);
		
//		JRadioButton rdbtnEstudiante = new JRadioButton("Estudiante");
//		JRadioButton rdbtnTutor = new JRadioButton("Tutor");
//		JRadioButton rdbtnAnalista = new JRadioButton("Analista");
		
//		comboBoxITR.setSelectedItem(getUsuarioIngresado().getItr().getNombre());
		cargarComboBox();
		comboBoxDepartamento.setSelectedItem(getUsuarioIngresado().getDepartamento().getNombre());
		
		if(usuarioIngresadoAct instanceof Estudiante) {
			lblGeneracion = new JLabel("Generacion");
			lblGeneracion.setBounds(224, 265, 150, 13);
			add(lblGeneracion);
			
			textFieldGeneracion = new JTextField();
			textFieldGeneracion.setText((String) null);
			textFieldGeneracion.setColumns(10);
			textFieldGeneracion.setBounds(224, 288, 150, 19);
			add(textFieldGeneracion);
			textFieldGeneracion.setText(((Estudiante)usuarioIngresadoAct).getGeneracion());
			textFieldGeneracion.setEditable(false);
		}
		
		if(usuarioIngresadoAct instanceof Tutor) {
			lblRol = new JLabel("Rol");
			lblRol.setBounds(224, 265, 150, 13);
			add(lblRol);
			
			textFieldRol = new JTextField();
			textFieldRol.setText((String) null);
			textFieldRol.setColumns(10);
			textFieldRol.setBounds(224, 288, 150, 19);
			add(textFieldRol);
			textFieldRol.setText(((Tutor)usuarioIngresadoAct).getTipo().getNombre());
			textFieldRol.setEditable(false);
			
			lblArea = new JLabel("Area");
			lblArea.setBounds(224, 316, 150, 13);
			add(lblArea);
			
			textFieldArea = new JTextField();
			textFieldArea.setText((String) null);
			textFieldArea.setBounds(224, 339, 150, 19);
			add(textFieldArea);
			textFieldArea.setText(((Tutor)usuarioIngresadoAct).getArea().getNombre());
			textFieldArea.setEditable(false);
		}
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de los cambios?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				    getUsuarioIngresado().setNombre1(textFieldNombre1.getText());
					getUsuarioIngresado().setNombre2(textFieldNombre2.getText());
					getUsuarioIngresado().setApellido1(textFieldApellido1.getText());
					getUsuarioIngresado().setApellido2(textFieldApellido2.getText());
					getUsuarioIngresado().setCedula(textFieldDocumento.getText());
					getUsuarioIngresado().setTelefono(textFieldCelular.getText());
					getUsuarioIngresado().setFechaNac(dateChooser.getDatoFecha());
					getUsuarioIngresado().setMail(textFieldEmailPersonal.getText());
					getUsuarioIngresado().setMailInstitucional(textFieldEmailUTEC.getText());
					getUsuarioIngresado().setContrasena(textFieldContraseña.getText());
					String usuario = textFieldEmailUTEC.getText().split("@")[0];
					getUsuarioIngresado().setUsuario(usuario);
					
					try {
						getUsuarioIngresado().setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
					getUsuarioIngresado().setLocalidad(textFieldLocalidad.getText());
//					getUsuarioIngresado().setValidado("0");
//					getUsuarioIngresado().setActivo("0");
					
					try {
						DAOGeneral.usuarioBean.actualizar(getUsuarioIngresado());
						JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito", null, JOptionPane.PLAIN_MESSAGE);
					} catch (ServiciosException e1) {
						e1.printStackTrace();
					}
				} else {
				    JOptionPane.showMessageDialog(null, "Cambios cancelados");
//				    System.exit(0);
				}
				
//				getUsuarioIngresado().setNombre1(textFieldNombre1.getText());
//				getUsuarioIngresado().setNombre2(textFieldNombre2.getText());
//				getUsuarioIngresado().setApellido1(textFieldApellido1.getText());
//				getUsuarioIngresado().setApellido2(textFieldApellido2.getText());
//				getUsuarioIngresado().setCedula(textFieldDocumento.getText());
//				getUsuarioIngresado().setTelefono(textFieldCelular.getText());
//				getUsuarioIngresado().setFechaNac(dateChooser.getDatoFecha());
//				getUsuarioIngresado().setMail(textFieldEmailPersonal.getText());
//				getUsuarioIngresado().setMailInstitucional(textFieldEmailUTEC.getText());
//				getUsuarioIngresado().setContrasena(textFieldContraseña.getText());
//				String usuario = textFieldEmailUTEC.getText().split("@")[0];
//				getUsuarioIngresado().setUsuario(usuario);
//				
//				try {
//					getUsuarioIngresado().setDepartamento(DAOGeneral.departamentoBean.obtenerPorNombre(comboBoxDepartamento.getSelectedItem().toString()));
//				} catch (ServiciosException e1) {
//					e1.printStackTrace();
//				}
//				getUsuarioIngresado().setLocalidad(textFieldLocalidad.getText());
//				getUsuarioIngresado().setValidado("0");
//				getUsuarioIngresado().setActivo("0");
//				try {
//					DAOGeneral.usuarioBean.actualizar(getUsuarioIngresado());
//					JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito", null, JOptionPane.PLAIN_MESSAGE);
//				} catch (ServiciosException e1) {
//					e1.printStackTrace();
//				}
			}
		});
		btnActualizar.setBounds(280, 494, 121, 21);
		add(btnActualizar);
		
	}
	
	public void cargarComboBox() {
<<<<<<< HEAD
//		modeloITR.removeAllElements();
//		modeloITR.addElement("");
//		for(ITR itr : DAOGeneral.itrBean.obtenerTodos()) {
//			if(itr.getActivo().equals("Y")) 
//				modeloITR.addElement(itr.getNombre());
//		}
=======
		modeloITR.removeAllElements();
		modeloITR.addElement("");
		for(ITR itr : DAOGeneral.itrBean.obtenerTodos()) {
			modeloITR.addElement(itr.getNombre());
		}
>>>>>>> 1b44277084aea376f7a33301a6fda361aa80f780
		
		modeloDepartamento.removeAllElements();
		modeloDepartamento.addElement("");
		for(Departamento departamento: DAOGeneral.departamentoBean.obtenerTodos()) {
			modeloDepartamento.addElement(departamento.getNombre());
		}
	}
	
//	public void cargarComboBoxTutor() throws Exception {
//		modeloRol.removeAllElements();
//		modeloRol.addElement("");
//		for(TipoTutor tipoTutor : DAOGeneral.tipoTutorBean.obtenerTodos()) {
//			modeloRol.addElement(tipoTutor.getNombre());
//		}
//		
//		modeloArea.removeAllElements();
//		modeloArea.addElement("");
//		for(Area area : DAOGeneral.areaBean.obtenerTodos()) {
//			modeloArea.addElement(area.getNombre());
//		}
//	}

	public Usuario getUsuarioIngresado() {
		return usuarioIngresadoAct;
		
	}

	public static void setUsuarioIngresado(Usuario usuarioIngresado) {
		usuarioIngresadoAct = usuarioIngresado;
		
	}
}

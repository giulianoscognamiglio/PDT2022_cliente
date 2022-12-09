package GUI;
	
import javax.swing.JPanel;
import rojeru_san.rsdate.RSYearDate;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.entities.Area;
import com.entities.TipoTutor;


import controlador.DAOGeneral;



public class PanelRegistroExtra extends JPanel {
	public static JComboBox comboBoxTipoTutor;
	public static JComboBox comboBoxAreaTutor;
	public static RSYearDate yearDate;

	
	private DefaultComboBoxModel modeloTipo;
	private DefaultComboBoxModel modeloArea;
	
	public PanelRegistroExtra() {
		
	}
	
	void registroExtraEstudiante() {
		removeAll();
		setLayout(null);
		setSize(315,79);
		
		yearDate = new RSYearDate();
		yearDate.setColorButtonHover(Color.BLACK);
		yearDate.setColorBackground(Color.BLACK);
		yearDate.setBounds(0, 39, 143, 30);
		add(yearDate);
		
		JLabel lblAñoIngreso = new JLabel("A\u00F1o Ingreso");
		lblAñoIngreso.setBounds(0, 16, 119, 13);
		add(lblAñoIngreso);
	}
	
	void registroExtraTutor() {
		removeAll();
		setLayout(null);
		setSize(315,79);
		
		JLabel lblTipoTutor = new JLabel("Tipo Tutor");
		lblTipoTutor.setBounds(0, 0, 78, 13);
		add(lblTipoTutor);
		modeloTipo=new DefaultComboBoxModel();
		comboBoxTipoTutor = new JComboBox();
		comboBoxTipoTutor.setModel(modeloTipo);
		comboBoxTipoTutor.setBounds(0, 12, 175, 21);
		add(comboBoxTipoTutor);
		
		JLabel lblAreaTutor = new JLabel("Area Tutor");
		lblAreaTutor.setBounds(0, 46, 78, 13);
		add(lblAreaTutor);
		modeloArea=new DefaultComboBoxModel();
		comboBoxAreaTutor = new JComboBox();
		comboBoxAreaTutor.setModel(modeloArea);
		comboBoxAreaTutor.setBounds(0, 58, 175, 21);
		add(comboBoxAreaTutor);
		
		cargarCombo();
	}
	
	void registroExtraAnalista() {
		removeAll();
		setSize(315,79);
	}
	
	public void cargarCombo(){
		modeloArea.removeAllElements();
		modeloArea.addElement("");
		for(Area a:DAOGeneral.areaBean.obtenerTodos()){
			modeloArea.addElement(a.getNombre());
		}
		modeloTipo.removeAllElements();
		modeloTipo.addElement("");
		for(TipoTutor t:DAOGeneral.tipoTutorBean.obtenerTodos()){
			modeloTipo.addElement(t.getNombre());
		}
	}
}

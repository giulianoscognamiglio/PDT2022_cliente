package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.exceptions.ServiciosException;
import com.entities.*;

import controlador.DAOGeneral;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAnaliticaReportes extends JPanel {

	private DefaultTableModel modeloTabla;

	public PanelAnaliticaReportes() {

		setBounds(0, 0, 684, 581);
		setLayout(null);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "ID", "Estudiante", "Estado" });

		JLabel lblReclamos = new JLabel("Analítica de reportes");
		lblReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReclamos.setBounds(235, 37, 198, 36);
		add(lblReclamos);


		
//------------------------------------------------------------------------------------
		JComboBox comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setBounds(261, 101, 145, 36);
		add(comboBoxFiltro);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
		
		modeloCombo.addElement("- Seleccione criterio -");

		modeloCombo.addElement("Mes");
		modeloCombo.addElement("Grupo");
		modeloCombo.addElement("ITR");
		modeloCombo.addElement("Generación");
		modeloCombo.addElement("Tipo");
		
		comboBoxFiltro.setModel(modeloCombo);
		
		JButton btnDescargar = new JButton("Descargar reporte");
		btnDescargar.setBounds(48, 499, 153, 36);
		add(btnDescargar);




	}		
////////////////////////////////////////////////////////////////////////////////////////




}

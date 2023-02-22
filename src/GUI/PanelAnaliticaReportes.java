package GUI;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.entities.*;

import controlador.DAOGeneral;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class PanelAnaliticaReportes extends JPanel {

	private JButton btnDescargar;
	private DefaultTableModel modeloTabla;
	private HashMap<String, Integer> hashMapPDF;
	public PanelAnaliticaReportes() {

		setBounds(0, 0, 625, 581);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 166, 409, 282);
		add(scrollPane);

		modeloTabla = new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Nombre", "Cantidad" });
		modeloTabla.setRowCount(0);

		JTable table = new JTable();
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);

		JLabel lblReclamos = new JLabel("Analítica de Reportes de Reclamos");
		lblReclamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReclamos.setBounds(107, 37, 409, 36);
		add(lblReclamos);

//------------------------------------------------------------------------------------
		JComboBox comboBoxFiltroCriterio = new JComboBox();
		comboBoxFiltroCriterio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Reclamo> reclamos = DAOGeneral.reclamoBean.obtenerTodos();
				btnDescargar.setVisible(true);


				if (comboBoxFiltroCriterio.getSelectedItem().equals("Generación")) {

					HashMap<String, Integer> hmGeneraciones = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = DAOGeneral.estudianteBean.obtenerPorId(r.getEstudiante()).getGeneracion();

						// si una generacion no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmGeneraciones.containsKey(key))) {

							hmGeneraciones.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmGeneraciones.put(key, hmGeneraciones.get(key) + 1);
						}

					}
					cargarTablaHM(hmGeneraciones);
					hashMapPDF = hmGeneraciones;


				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Mes")) {
					HashMap<String, Integer> hmMeses = new HashMap<>();

					// hacemos una lista con los meses
					List<String> meses = new ArrayList<String>();

					meses.add("Enero");
					meses.add("Febrero");
					meses.add("Marzo");
					meses.add("Abril");
					meses.add("Mayo");
					meses.add("Junio");
					meses.add("Julio");
					meses.add("Agosto");
					meses.add("Septiembre");
					meses.add("Octubre");
					meses.add("Noviembre");
					meses.add("Diciembre");

					for (Reclamo r : reclamos) {

//						seleccionamos el nombre del mes mediante el numero que nos da getFecha.getMonth
//						NOTA: Estos metodos devuelven el mes en entero menos uno. 
//						Ejemplo: Noviembre = =10
						String key = meses.get(r.getFecha().getMonth());

						// si un mes no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmMeses.containsKey(key))) {

							hmMeses.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
							// map.put(key, map.get(key) + 1);
							hmMeses.put(key, hmMeses.get(key) + 1);
						}

					}
					cargarTablaHM(hmMeses);
					hashMapPDF = hmMeses;
				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("ITR")) {

					HashMap<String, Integer> hmITR = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = DAOGeneral.estudianteBean.obtenerPorId(r.getEstudiante()).getItr().getNombre();

						// si un ITR no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmITR.containsKey(key))) {

							hmITR.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmITR.put(key, hmITR.get(key) + 1);
						}

					}
					cargarTablaHM(hmITR);
					hashMapPDF = hmITR;

				}

				if (comboBoxFiltroCriterio.getSelectedItem().equals("Tipo")) {

					HashMap<String, Integer> hmGeneraciones = new HashMap<>();

					for (Reclamo r : reclamos) {
						String key = r.getTipo();

						// si un Tipo de reclamo no está en el HM la agregamos como key, con un value
						// inicial de 1
						if (!(hmGeneraciones.containsKey(key))) {

							hmGeneraciones.put(key, 1);

						} else {
							// si está en el HM le sumamos uno al value
//			map.put(key, map.get(key) + 1);
							hmGeneraciones.put(key, hmGeneraciones.get(key) + 1);
						}

					}
					cargarTablaHM(hmGeneraciones);
					hashMapPDF = hmGeneraciones;

				}

			}
		});
		comboBoxFiltroCriterio.setBounds(124, 109, 145, 36);
		add(comboBoxFiltroCriterio);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();

		modeloCombo.addElement("- Seleccione criterio -");

		modeloCombo.addElement("Mes");
		modeloCombo.addElement("ITR");
		modeloCombo.addElement("Generación");
		modeloCombo.addElement("Tipo");

		comboBoxFiltroCriterio.setModel(modeloCombo);

		btnDescargar = new JButton("Descargar reporte");
		btnDescargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				
					String criterio = comboBoxFiltroCriterio.getSelectedItem().toString();
					descargarTabla(criterio);
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDescargar.setBounds(48, 499, 153, 36);
		add(btnDescargar);
		btnDescargar.setVisible(false);
	}

	public void cargarTabla(List<Reclamo> reclamos) {
		modeloTabla.setRowCount(0);
		for (Reclamo r : reclamos) {

			try {

				Vector v = new Vector();
				Usuario usuarioDB;
				usuarioDB = DAOGeneral.usuarioBean.obtenerPorId(r.getEstudiante());

				v.addElement(r.getId_reclamo());
				v.addElement(usuarioDB.getNombre1() + " " + usuarioDB.getApellido1());
//				v.addElement(r.getEstado());

				modeloTabla.addRow(v);

			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void cargarTablaHM(HashMap<String, Integer> hmGeneraciones) {
		modeloTabla.setRowCount(0);

		for (Map.Entry<String, Integer> r : hmGeneraciones.entrySet()) {

			String key = r.getKey();
			Integer value = r.getValue();

			Vector v = new Vector();
			v.addElement(key);
			v.addElement(value);

			modeloTabla.addRow(v);

		}
	}
	public void descargarTabla(String criterio) throws FileNotFoundException, DocumentException {
		
		LocalDate localDate = LocalDate.now();
		Paragraph titulo = new Paragraph("Analitica de reclamos por "+criterio);
		Document documento = new Document();
		FileOutputStream archivo = new FileOutputStream("Analitica de reclamos por "+criterio+localDate+".pdf");
		PdfWriter.getInstance(documento, archivo);

		documento.open();
		titulo.setAlignment(1);

		documento.add(titulo);

		documento.add(Chunk.NEWLINE);

		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(100);
		
		PdfPCell keys = new PdfPCell(new Phrase("Agrupacion"));
		PdfPCell values = new PdfPCell(new Phrase("Cantidad de reportes"));
		
		tabla.addCell(keys);
		tabla.addCell(values);
		
		
		for (Map.Entry<String, Integer> r : hashMapPDF.entrySet()) {
			
			tabla.addCell(r.getKey());
			tabla.addCell(r.getValue().toString());
				
		}

		documento.add(tabla);
		
		documento.close();
		
		JOptionPane.showMessageDialog(null, "Archivo PDF descargado con éxito", null,
				JOptionPane.PLAIN_MESSAGE);

	}
}

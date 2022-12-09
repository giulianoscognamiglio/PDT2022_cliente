package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FuncionalidadesAsignadasARol extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modeloTabla;
	public static Rol rolSeleccionado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionalidadesAsignadasARol frame = new FuncionalidadesAsignadasARol();
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
	public FuncionalidadesAsignadasARol() {
		
		ImageIcon img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 907, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 100, 680, 389);
		contentPane.add(scrollPane);
		
		JTable table = new JTable();
		modeloTabla=new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "Nombre", "Descripcion" });
		table.setModel(modeloTabla);
		scrollPane.setViewportView(table);
		
		JLabel lblFuncionalidadesRol = new JLabel("Funcionalidades Asignadas al Rol");
		lblFuncionalidadesRol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionalidadesRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionalidadesRol.setBounds(216, 37, 377, 39);
		contentPane.add(lblFuncionalidadesRol);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(364, 502, 85, 21);
		contentPane.add(btnVolver);
		setLocationRelativeTo(null);
		cargarTabla();
	}
	
	public void cargarTabla(){
		modeloTabla.setRowCount(0);
		for(Funcionalidad f:rolSeleccionado.getFuncionalidades()){
			Vector v=new Vector();
			v.addElement(f.getNombre());
			v.addElement(f.getDescripcion());
			modeloTabla.addRow(v);
		}
	}
}

package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.entities.Usuario;

public class PanelMenu extends JPanel {
	
	public static Usuario usuarioIngresado;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		tutorGUI();
	}

	public void estudianteGUI() {
		removeAll();
		setBounds(0, 0, 907, 581);

		setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 226, 581);
		add(panelMenu);
		panelMenu.setLayout(null);

		JPanel panelDinamico = new JPanel();
		panelDinamico.setBounds(223, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);

		JLabel lblTipoUsuario = new JLabel("Estudiante");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setBounds(54, 80, 121, 13);
		panelMenu.add(lblTipoUsuario);

		JButton btnFuncionalidades = new JButton("Funcionalidades");
		btnFuncionalidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelFuncionalidades funcionalidades = new PanelFuncionalidades();
				panelDinamico.removeAll();
				funcionalidades.setSize(684, 581);
				funcionalidades.setLocation(0, 0);
				panelDinamico.add(funcionalidades);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnFuncionalidades.setBounds(48, 364, 127, 21);
		panelMenu.add(btnFuncionalidades);

		JButton btnEditarPerfil = new JButton("Editar Perfil");
		btnEditarPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ActualizarUsuario actUsuario = new ActualizarUsuario();
				panelDinamico.removeAll();
				actUsuario.setSize(684, 581);
				actUsuario.setLocation(0, 0);
				panelDinamico.add(actUsuario);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnEditarPerfil.setBounds(48, 395, 127, 21);
		panelMenu.add(btnEditarPerfil);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login=new Login();
				MenuPrincipal.usuarioIngresado=null;
				MenuPrincipal.getInstancia().setVisible(false);
				login.setVisible(true);
			}
		});
		btnLogOut.setBounds(48, 475, 127, 21);
		panelMenu.add(btnLogOut);
		
		JLabel lblNombreUsuario = new JLabel("nombreUsuario");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(54, 59, 121, 13);
		lblNombreUsuario.setText(usuarioIngresado.getUsuario());
		panelMenu.add(lblNombreUsuario);

	}

	public void analistaGUI() {
		removeAll();
		setBounds(0, 0, 907, 581);

		setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 226, 581);
		add(panelMenu);
		panelMenu.setLayout(null);

		JPanel panelDinamico = new JPanel();
		panelDinamico.setBounds(223, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);

		JLabel lblTipoUsuario = new JLabel("Analista");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setBounds(54, 80, 121, 13);
		panelMenu.add(lblTipoUsuario);

		JButton btnRoles = new JButton("Roles");
		btnRoles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelFuncionalidadesAnalista funcionalidades = new PanelFuncionalidadesAnalista();
				panelDinamico.removeAll();
				funcionalidades.setSize(684, 581);
				funcionalidades.setLocation(0, 0);
				panelDinamico.add(funcionalidades);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnRoles.setBounds(48, 364, 127, 21);
		panelMenu.add(btnRoles);

		JButton btnEditarPerfil = new JButton("Editar Perfil");
		btnEditarPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ActualizarUsuario actUsuario = new ActualizarUsuario();
				panelDinamico.removeAll();
				actUsuario.setSize(684, 581);
				actUsuario.setLocation(0, 0);
				panelDinamico.add(actUsuario);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnEditarPerfil.setBounds(48, 395, 127, 21);
		panelMenu.add(btnEditarPerfil);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login=new Login();
				MenuPrincipal.usuarioIngresado=null;
				MenuPrincipal.getInstancia().setVisible(false);
				login.setVisible(true);
			}
		});
		btnLogOut.setBounds(48, 475, 127, 21);
		panelMenu.add(btnLogOut);
		
		JButton btnAltaUsuario = new JButton("Alta Usuario");
		btnAltaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				RegistroUsuario registro = new RegistroUsuario();
				registro.setVisible(true);
				
			}
		});
		btnAltaUsuario.setBounds(48, 303, 127, 21);
		panelMenu.add(btnAltaUsuario);
		
		JButton btnBajaUsuario = new JButton("Baja Usuario");
		btnBajaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PanelBajaUsuario bajaUsuario = new PanelBajaUsuario();
				panelDinamico.removeAll();
				bajaUsuario.setSize(684, 581);
				bajaUsuario.setLocation(0, 0);
				panelDinamico.add(bajaUsuario);
				panelDinamico.revalidate();
				panelDinamico.repaint();
				
			}
		});
		btnBajaUsuario.setBounds(48, 334, 127, 21);
		panelMenu.add(btnBajaUsuario);
		
		JLabel lblNombreUsuario = new JLabel("nombreUsuario");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(54, 57, 121, 13);
		
		lblNombreUsuario.setText(usuarioIngresado.getUsuario());
		
		panelMenu.add(lblNombreUsuario);
		
	}

	public void tutorGUI() {
		removeAll();
		setBounds(0, 0, 907, 581);

		setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setBounds(0, 0, 226, 581);
		add(panelMenu);
		panelMenu.setLayout(null);

		JPanel panelDinamico = new JPanel();
		panelDinamico.setBounds(223, 0, 684, 581);
		add(panelDinamico);
		panelDinamico.setLayout(null);

		JLabel lblTipoUsuario = new JLabel("Tutor");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setBounds(54, 80, 121, 13);
		panelMenu.add(lblTipoUsuario);

		JButton btnFuncionalidades = new JButton("Funcionalidades");
		btnFuncionalidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelFuncionalidades funcionalidades = new PanelFuncionalidades();
				panelDinamico.removeAll();
				funcionalidades.setSize(684, 581);
				funcionalidades.setLocation(0, 0);
				panelDinamico.add(funcionalidades);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnFuncionalidades.setBounds(48, 364, 127, 21);
		panelMenu.add(btnFuncionalidades);

		JButton btnEditarPerfil = new JButton("Editar Perfil");
		btnEditarPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ActualizarUsuario actUsuario = new ActualizarUsuario();
				panelDinamico.removeAll();
				actUsuario.setSize(684, 581);
				actUsuario.setLocation(0, 0);
				panelDinamico.add(actUsuario);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
		});
		btnEditarPerfil.setBounds(48, 395, 127, 21);
		panelMenu.add(btnEditarPerfil);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login=new Login();
				MenuPrincipal.usuarioIngresado=null;
				MenuPrincipal.getInstancia().setVisible(false);
				login.setVisible(true);
			}
		});
		btnLogOut.setBounds(48, 475, 127, 21);
		panelMenu.add(btnLogOut);
		
		JLabel lblNombreUsuario = new JLabel("nombreUsuario");
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(54, 57, 121, 13);
		lblNombreUsuario.setText(usuarioIngresado.getUsuario());
		panelMenu.add(lblNombreUsuario);
	}
}

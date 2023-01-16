package GUI;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import controlador.DAOGeneral;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldContraseña;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public Login() {
		DAOGeneral dao = new DAOGeneral();
		
		ImageIcon 
		img = new ImageIcon("uteclogo.png");
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(171, 55, 74, 13);
		contentPane.add(lblUsuario);

		JLabel lblContraseña = new JLabel("contrase\u00F1a");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setBounds(171, 97, 74, 13);
		contentPane.add(lblContraseña);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(161, 68, 96, 19);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JButton btnIngresar = new JButton("ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ingresar();
			}
		});
		btnIngresar.setBounds(161, 151, 96, 21);
		contentPane.add(btnIngresar);
		
		JButton btnRegistrar = new JButton("registrarse");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				RegistroUsuario registro = new RegistroUsuario();
				registro.setVisible(true);
				
			}
		});
		btnRegistrar.setBounds(161, 216, 96, 21);
		contentPane.add(btnRegistrar);
		
		textFieldContraseña = new JPasswordField();
		textFieldContraseña.setBounds(161, 110, 96, 19);
		contentPane.add(textFieldContraseña);
		
		textFieldContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //10 es codigo para el enter
                if(e.getKeyCode()==10) {
                    try {
                        ingresar();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Error...",JOptionPane.ERROR_MESSAGE);
                        textFieldContraseña.setText("");
                    }
                }
            }
        });
		
	}
	
	public void ingresar() {
		try {
			Usuario usuarioIngresado = DAOGeneral.usuarioBean.verificarUsuario(textFieldUsuario.getText(),textFieldContraseña.getText());
			
			PanelMenu.usuarioIngresado = usuarioIngresado;
			
			if(usuarioIngresado == null) {
				JOptionPane.showMessageDialog(null, "nombre de usuario o contraseña incorrecto", "Error",JOptionPane.ERROR_MESSAGE);
			}
			else if(usuarioIngresado.getValidado().equals("N")) {
				JOptionPane.showMessageDialog(null, "el usuario aún no ha sido validado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(usuarioIngresado.getActivo().equals("N")){
				JOptionPane.showMessageDialog(null, "el usuario no se encuentra activo en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (usuarioIngresado instanceof Estudiante) {
				MenuPrincipal menu = MenuPrincipal.getInstancia();
				MenuPrincipal.usuarioIngresado = usuarioIngresado;
				menu.panelMenu.estudianteGUI();
				menu.setVisible(true);
				setVisible(false);
			} else if (usuarioIngresado instanceof Analista) {
				MenuPrincipal menu = MenuPrincipal.getInstancia();
				MenuPrincipal.usuarioIngresado = usuarioIngresado;
				menu.panelMenu.analistaGUI();
				menu.setVisible(true);
				setVisible(false);
			} else if (usuarioIngresado instanceof Tutor) {
				MenuPrincipal menu = MenuPrincipal.getInstancia();
				MenuPrincipal.usuarioIngresado = usuarioIngresado;
				menu.panelMenu.tutorGUI();
				menu.setVisible(true);
				setVisible(false);
			}
		} catch (ServiciosException e1) {
			e1.printStackTrace();
		}
	}
}

package Formularios;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Clases.BaseDeDatos;
import Clases.CargarComboBox;
import Modelos.NuevoUsuario;
import Modelos.Producto;
import Modelos.TipoProducto;




import Modelos.TipoUsuario;





//import Modelos.RegistrarUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class FrmAgregarNuevoUsuario extends JInternalFrame {
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNomUsuario;
	private JPasswordField txtPass;
	private JPasswordField txtVPass;
	//private Vector <NuevoUsuario> categorias;
	private JComboBox cmbTipoUsuario;
	CargarComboBox comboBox = new CargarComboBox();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgregarNuevoUsuario frame = new FrmAgregarNuevoUsuario();
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
	public FrmAgregarNuevoUsuario() {
		setClosable(true);
		setTitle("Agregar Nuevo Usuario");
		setFrameIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/birosoft/liquid/icons/computericon.png")));
		//setIcon(true);
		setBounds(100, 100, 435, 419);
		getContentPane().setLayout(null);
		
		Dimension tamFrame=this.getSize();//para obtener las dimensiones del frame
        Dimension tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();      //para obtener el tamanio de la pantalla
        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);  //para posicionar
		
		JLabel lblAgregarUsuario = new JLabel("Agregar/Modificar Usuario");
		lblAgregarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarUsuario.setBounds(10, 11, 410, 22);
		getContentPane().add(lblAgregarUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setBounds(20, 115, 52, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellido.setBounds(20, 151, 52, 16);
		getContentPane().add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(84, 112, 222, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(84, 148, 222, 22);
		getContentPane().add(txtApellido);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeUsuario.setBounds(20, 203, 123, 16);
		getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeUsuario.setBounds(20, 232, 103, 16);
		getContentPane().add(lblTipoDeUsuario);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasena.setBounds(20, 261, 103, 16);
		getContentPane().add(lblContrasena);
		
		JLabel lblRepitaSuContrasena = new JLabel("Repita su Contrasena");
		lblRepitaSuContrasena.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRepitaSuContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitaSuContrasena.setBounds(10, 290, 121, 16);
		getContentPane().add(lblRepitaSuContrasena);
		
		txtNomUsuario = new JTextField();
		txtNomUsuario.setColumns(10);
		txtNomUsuario.setBounds(145, 200, 161, 22);
		getContentPane().add(txtNomUsuario);
		 
		cmbTipoUsuario = new JComboBox();
	    comboBox.cargarComboBoxTipoUsuario(cmbTipoUsuario);
		
		cmbTipoUsuario.setBounds(144, 229, 162, 22);
		getContentPane().add(cmbTipoUsuario);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(145, 260, 161, 20);
		getContentPane().add(txtPass);
		
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
																//BOTON AGREGAR PRODUCTOS
				
				
					String nombreUsuario = txtNomUsuario.getText();
					String contrasena = txtVPass.getText();
				    //String tipoUsuario = (String) cbTipoUsuario.getSelectedItem();
					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String contrasena1 = txtPass.getText();
					
					TipoUsuario ObjetoTipoUsuario = comboBox.categorias1.get(cmbTipoUsuario.getSelectedIndex());
					NuevoUsuario UsuarioOtenido = new NuevoUsuario(nombreUsuario, contrasena, ObjetoTipoUsuario, nombre, apellido );
					
					
					try {
							UsuarioOtenido.AgregarNuevoUsuario();
					} catch (ClassNotFoundException e) {
						// TODO manejar la exception 
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					//}
					
				}
					dispose();
				
			
			}
		});
		btnRegistrar.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/Aceptar (2).png")));
		btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnRegistrar.setBounds(20, 334, 123, 45);
		getContentPane().add(btnRegistrar);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 dispose(); 
			}
		});
		button_1.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(292, 334, 123, 45);
		getContentPane().add(button_1);
		
		txtVPass = new JPasswordField();
		txtVPass.setBounds(145, 289, 161, 20);
		getContentPane().add(txtVPass);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarUsuario modificar = new ModificarUsuario();
				getDesktopPane().add(modificar);
				modificar.setVisible(true);
			}
		});
		btnModificar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnModificar.setBounds(159, 334, 123, 45);
		getContentPane().add(btnModificar);
		
		JLabel lblNewLabel = new JLabel("Buscar Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 60, 103, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(123, 58, 186, 30);
		getContentPane().add(textField);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/search_icon.png")));
		button.setBounds(319, 53, 89, 45);
		getContentPane().add(button);
		
		

	}
}

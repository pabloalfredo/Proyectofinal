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

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Clases.BaseDeDatos;
import Clases.CargarComboBox;
import Clases.FondoFormulario;
import Clases.Validacion;
import Modelos.NuevoUsuario;
import Modelos.Producto;
import Modelos.TipoProducto;
import Formularios.FrmMainPrincipal;
import Modelos.TipoUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.omg.CORBA.INITIALIZE;

import java.awt.Color;

public class FrmAgregarNuevoUsuario extends JInternalFrame {
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNomUsuario;
	private JPasswordField txtPass;
	//private Vector <NuevoUsuario> categorias;
	private JComboBox cmbTipoUsuario;
	CargarComboBox comboBox = new CargarComboBox();
	private JTextField txtBuscarUsuario;
	private String usuario;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnRegistrar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnSalir;
	String nombreUsuario;
	String contrasena;
	String nombre;
	String apellido;
	String contrasena1;
	int idTipoUsuario;
	BaseDeDatos conn = new BaseDeDatos();                                           ////////////              para luego ser comparado con el usuario  a registrar
	ResultSet rs;
	

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
	public FrmAgregarNuevoUsuario(){
		initialize();
		bloquearControles(); // llamada a la funcion bloquear controles para que el formulario se inicie con todo los controles apagados 
	}
	
	
	private void initialize() {
		
		setClosable(true);
		
		setTitle("Agregar Nuevo Usuario");
		setFrameIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/birosoft/liquid/icons/computericon.png")));
		//setIcon(true);
		setBounds(100, 100, 716, 439);
		getContentPane().setLayout(null);
	
		((JComponent) getContentPane()).setBorder(new FondoFormulario("/Recursos/fondousuario.jpg"));
		Dimension tamFrame=this.getSize();//para obtener las dimensiones del frame
        Dimension tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();      //para obtener el tamanio de la pantalla
        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);  //para posicionar
		
		JLabel lblAgregarUsuario = new JLabel("Mantenimiento de Usuarios");
		lblAgregarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarUsuario.setBounds(0, 6, 702, 22);
		getContentPane().add(lblAgregarUsuario);
		
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
		
		/*JLabel lblRepitaSuContrasena = new JLabel("Repita su Contrasena");
		lblRepitaSuContrasena.setVisible(false);
		lblRepitaSuContrasena.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRepitaSuContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitaSuContrasena.setBounds(10, 290, 121, 16);
		getContentPane().add(lblRepitaSuContrasena);*/
		
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
		
		/*txtVPass = new JPasswordField();
		txtVPass.setVisible(false);
		txtVPass.setBounds(145, 289, 161, 20);
		getContentPane().add(txtVPass);*/
		
		JLabel lblNewLabel = new JLabel("Buscar Usuario:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 48, 168, 23);
		getContentPane().add(lblNewLabel);
		
		txtBuscarUsuario = new JTextField();
		txtBuscarUsuario.setColumns(10);
		txtBuscarUsuario.setBounds(178, 41, 276, 30);
		getContentPane().add(txtBuscarUsuario);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			    
				
			    ////////////////////////////////////////////////////////////////////////////////////////////////////////// consulta para obtener el nombre usuario 
			    BaseDeDatos conn = new BaseDeDatos();                                           ////////////              para luego ser comparado con el usuario  a registrar
    			ResultSet rs;
    			try {
    				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select usuario, clave, idtipo_usuario, Nombre, Apellido  from tblusuario where usuario = '"+txtBuscarUsuario.getText()+"' or Nombre ='"+txtBuscarUsuario.getText() +"' ");
    					while (rs.next()){
    					 nombreUsuario = rs.getString(1);
    					 contrasena = rs.getString(2);
    					 idTipoUsuario = rs.getInt(3);
    					 nombre = rs.getString(4);
    					 apellido = rs.getString(5);
    				   
    					}
    			
    			} catch (ClassNotFoundException e) {
    				e.printStackTrace();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    			
    			botonmodificar();
			    txtNombre.setText(nombre);
			    txtApellido.setText(apellido);
			    txtNomUsuario.setText(nombreUsuario);
			    cmbTipoUsuario.setSelectedItem(idTipoUsuario);
			    txtPass.setText(contrasena);
			    
			   
				
			}
		});
		btnBuscar.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/search_icon.png")));
		btnBuscar.setBounds(466, 38, 66, 37);
		getContentPane().add(btnBuscar);
		
		JPanel panel = new JPanel();
		panel.setName("alfredo");
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 346, 679, 57);
		getContentPane().add(panel);
		panel.setLayout(null);
		
///////////////////////////////////////////////////////////////////////////  REGISTRAR NUEVO USUARIO 
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(142, 6, 123, 45);
		panel.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
															
				
					 nombreUsuario = txtNomUsuario.getText();
					 contrasena = txtPass.getText();
				     nombre = txtNombre.getText();
					 apellido = txtApellido.getText();
					 String usuarioObtenido = "";
					
					TipoUsuario ObjetoTipoUsuario;  // creo variable tipo usuario
					NuevoUsuario UsuarioOtenido = null;  // creo objeto nuevo usuario
					if(nombre.length() == 0 ||nombreUsuario.length() == 0 || contrasena.length() == 0 || apellido.length() == 0) { // verifico que no los campos no esten vacios
						JOptionPane.showMessageDialog(null, "No Pude dejar los campos marcados con * en blanco");
						
					}
					else{
				    
							ObjetoTipoUsuario = comboBox.categorias1.get(cmbTipoUsuario.getSelectedIndex());
						    UsuarioOtenido = new NuevoUsuario(nombreUsuario, contrasena, ObjetoTipoUsuario, nombre, apellido );
						    ////////////////////////////////////////////////////////////////////////////////////////////////////////// consulta para obtener el nombre usuario 
						    BaseDeDatos conn = new BaseDeDatos();                                           ////////////              para luego ser comparado con el usuario  a registrar
                			ResultSet rs;
                			try {
                				 rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select usuario  from tblusuario where usuario = '"+nombreUsuario+"' ");
                					while (rs.next()){
                					usuarioObtenido = rs.getString(1);
                				   
                					}
                					if(usuarioObtenido.equals(nombreUsuario)){  // comparo que si el usuario obtenido y el usuario introducido son iguales 
                        				
                        				JOptionPane.showMessageDialog(null, "Se ha Encontrado un Usuario Registrado con este mismo nombre de Usuario. Intente con Otro Nombre de Usuario");
                        				txtNomUsuario.setText("");
                        				txtPass.setText("");
                        				txtNomUsuario.requestFocus();
                        		
                        			}
                					
                					else{
                						try {
            								UsuarioOtenido.AgregarNuevoUsuario();
            								JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
            								limpiarControles();
            								bloquearControles();
            						       } catch (ClassNotFoundException e) {
            							
            							  e.printStackTrace();
            						     } catch (SQLException e) {
            							
            							e.printStackTrace();
            					    }
                					}
                					
                			} catch (ClassNotFoundException e) {
                				e.printStackTrace();
                			} catch (SQLException e) {
                				e.printStackTrace();
                			}
                			
						    
					}
					
					
				
			
			}
		});
		btnRegistrar.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/Aceptar (2).png")));
		btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				avilitarControles();
				txtNombre.requestFocus();
			
			}
		});
		btnNuevo.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNuevo.setBounds(6, 6, 123, 45);
		panel.add(btnNuevo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/birosoft/liquid/icons/error.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarControles();
				bloquearControles();
			}
		});
		btnCancelar.setBounds(412, 6, 123, 45);
		panel.add(btnCancelar);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(277, 6, 123, 45);
		panel.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String usuarioObtenido = "";
					
					TipoUsuario ObjetoTipoUsuario;  // creo variable tipo usuario
					NuevoUsuario UsuarioOtenido = null;  // creo objeto nuevo usuario
					if(nombre.length() == 0 ||nombreUsuario.length() == 0 || contrasena.length() == 0 || apellido.length() == 0) { // verifico que no los campos no esten vacios
						JOptionPane.showMessageDialog(null, "No Pude dejar los campos marcados con * en blanco");
						
					}
					else{
				    
							ObjetoTipoUsuario = comboBox.categorias1.get(cmbTipoUsuario.getSelectedIndex());
						    UsuarioOtenido = new NuevoUsuario(nombreUsuario, contrasena, ObjetoTipoUsuario, nombre, apellido );
						    ////////////////////////////////////////////////////////////////////////////////////////////////////////// consulta para obtener el nombre usuario 
						    
	            			try {
	            				 rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select usuario  from tblusuario where usuario = '"+nombreUsuario+"' ");
	            					while (rs.next()){
	            					usuarioObtenido = rs.getString(1);
	            				   
	            					}
	            					if(usuarioObtenido.equals(nombreUsuario)){  // comparo que si el usuario obtenido y el usuario introducido son iguales 
	                    				
	                    				JOptionPane.showMessageDialog(null, "Se ha Encontrado un Usuario Registrado con este mismo nombre de Usuario. Intente con Otro Nombre de Usuario");
	                    				txtNomUsuario.setText("");
	                    				txtPass.setText("");
	                    				txtNomUsuario.requestFocus();
	                    		
	                    			}
	            					
	            					else{
	            						try {
	        								UsuarioOtenido.ModificarUsuario();
	        								JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
	        								limpiarControles();
	        								bloquearControles();
	        						       } catch (ClassNotFoundException e) {
	        							
	        							  e.printStackTrace();
	        						     } catch (SQLException e) {
	        							
	        							e.printStackTrace();
	        					    }
	            					}
	            					
	            			} catch (ClassNotFoundException e) {
	            				e.printStackTrace();
	            			} catch (SQLException e) {
	            				e.printStackTrace();
	            			}
	            			
						    
					}
				    
				    
			
			}
		});
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSalir.setBounds(547, 6, 123, 45);
		panel.add(btnSalir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 190, 324, 102);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("*");
		label.setForeground(new Color(153, 0, 0));
		label.setFont(new Font("Dialog", Font.PLAIN, 44));
		label.setBounds(300, 16, 18, 36);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(new Color(153, 0, 0));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 44));
		label_1.setBounds(300, 68, 18, 36);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setVisible(false);
		label_2.setForeground(new Color(153, 0, 0));
		label_2.setFont(new Font("Dialog", Font.PLAIN, 44));
		label_2.setBounds(300, 101, 18, 36);
		panel_1.add(label_2);
		
		JLabel lblUsuario = new JLabel("   Usuario");
		lblUsuario.setBounds(0, 179, 66, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblMantenimiento = new JLabel("  Mantenimiento");
		lblMantenimiento.setBounds(6, 334, 103, 14);
		getContentPane().add(lblMantenimiento);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(6, 87, 545, 80);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(58, 16, 193, 22);
		panel_2.add(txtNombre);
	
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(58, 52, 193, 22);
		panel_2.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(292, 19, 52, 16);
		panel_2.add(lblTelefono);
		lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(276, 55, 68, 16);
		panel_2.add(lblDireccion);
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblDireccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(346, 16, 193, 22);
		panel_2.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(346, 52, 193, 22);
		panel_2.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 19, 52, 16);
		panel_2.add(lblNombre);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(6, 55, 52, 16);
		panel_2.add(lblApellido);
		lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(new Color(153, 0, 0));
		label_3.setFont(new Font("Dialog", Font.PLAIN, 44));
		label_3.setBounds(254, 19, 18, 36);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(new Color(153, 0, 0));
		label_4.setFont(new Font("Dialog", Font.PLAIN, 44));
		label_4.setBounds(254, 55, 18, 36);
		panel_2.add(label_4);
		
		JLabel lblDatosPersonales = new JLabel("Datos Personales");
		lblDatosPersonales.setBounds(6, 74, 107, 16);
		getContentPane().add(lblDatosPersonales);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/loguin.png")));
		lblNewLabel_1.setBounds(563, 0, 142, 144);
		getContentPane().add(lblNewLabel_1);
		
		

	}

/*	public void usuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		usuario = nombreUsuario;
	    nUsuario.setText(usuario);
	}*/
	void bloquearControles(){
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtTelefono.setEnabled(false);
		txtDireccion.setEnabled(false);
		txtNomUsuario.setEnabled(false);
		cmbTipoUsuario.setEnabled(false);                     //desavilitar controles  y avilitar controles 
		txtPass.setEnabled(false);
	    btnRegistrar.setEnabled(false);
	    btnGuardar.setEnabled(false);
	    btnCancelar.setEnabled(false);
	    btnNuevo.setEnabled(true);
	    
	}
	void avilitarControles(){
		txtNombre.setEnabled(true);
		txtApellido.setEnabled(true);
		txtTelefono.setEnabled(true);
		txtDireccion.setEnabled(true); 
		txtNomUsuario.setEnabled(true);
		cmbTipoUsuario.setEnabled(true);
		txtPass.setEnabled(true);
	    btnRegistrar.setEnabled(true);
	    btnGuardar.setEnabled(false);
	    btnNuevo.setEnabled(false);
	    btnCancelar.setEnabled(true);
	    btnSalir.setEnabled(true);
	    
	}
	void limpiarControles(){
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNomUsuario.setText("");
		txtPass.setText("");
		cmbTipoUsuario.setEnabled(false);
	    btnRegistrar.setEnabled(false);
	    btnGuardar.setEnabled(false);
	    btnNuevo.setEnabled(true);
	}
	void botonmodificar(){
		txtNombre.setEnabled(true);
		txtApellido.setEnabled(true);
		txtTelefono.setEnabled(true);
		txtDireccion.setEnabled(true);
		txtNomUsuario.setEnabled(true);
		txtPass.setEnabled(true);
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNomUsuario.setText("");
		txtPass.setText("");
		cmbTipoUsuario.setEnabled(true);
	    btnRegistrar.setEnabled(false);
	    btnGuardar.setEnabled(true);
	    btnNuevo.setEnabled(false);
	    btnCancelar.setEnabled(true);
	    btnSalir.setEnabled(true);
	}
}


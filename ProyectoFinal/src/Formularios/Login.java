package Formularios;
import javax.swing.*;

import Clases.AplicarTemaVentana;
import Clases.BaseDeDatos;
import Clases.ValidarUsuarios;
import Modelos.TipoUsuario;

import java.io.*;
import java.sql.*;
import java.util.Vector;
import java.awt.Panel.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	        private JButton btnAceptar, btnCancelar;
	        String usuario, elPassword;
	        private JTextField txtUser;
	        private JPasswordField txtPass;
	   
	 
	    Login()
	    {
	    	setUndecorated(true);
	    	AplicarTemaVentana aplicar = new AplicarTemaVentana();
			aplicar.temaliquid();
	    	setFont(new Font("Dialog", Font.BOLD, 12));
	    	setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Recursos/Icon GrenSoft3.png")));
	    	getContentPane().setBackground(new Color(51, 153, 204));
	    	setBackground(Color.LIGHT_GRAY);
	 
	        Container contenedor = getContentPane();
	        getContentPane().setLayout(null);
	 
	        //Crear y agregar los botones 
	        btnAceptar = new JButton("Aceptar");
	        btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 12));
	        btnAceptar.setIcon(new ImageIcon(Login.class.getResource("/Recursos/Aceptar (2).png")));
	        btnAceptar.setBounds(85, 175, 129, 45);
	        //establecer Boton aceptar por defecto
	        getRootPane().setDefaultButton(btnAceptar);
	 
	        btnCancelar = new JButton("Cancelar");
	        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
	        btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
	        btnCancelar.setBounds(226, 175, 129, 45);
	        contenedor.add(btnAceptar);
	        contenedor.add(btnCancelar);
	        
	        JLabel lblLogin = new JLabel("Login");
	        lblLogin.setForeground(SystemColor.menu);
	        lblLogin.setFont(new Font("Arial Black", Font.BOLD, 20));
	        lblLogin.setBounds(6, 6, 75, 25);
	        getContentPane().add(lblLogin);
	        
	        JLabel lblUsuario = new JLabel("Usuario:");
	        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
	        lblUsuario.setBounds(133, 53, 55, 16);
	        getContentPane().add(lblUsuario);
	        
	        JLabel lblContrasena = new JLabel("Contrasena:");
	        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 12));
	        lblContrasena.setBounds(113, 105, 75, 16);
	        getContentPane().add(lblContrasena);
	        
	        txtUser = new JTextField();
	        txtUser.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtUser.setBounds(188, 47, 167, 28);
	        getContentPane().add(txtUser);
	        txtUser.setColumns(10);
	        
	        txtPass = new JPasswordField();
	        txtPass.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtPass.setBounds(188, 99, 167, 28);
	        getContentPane().add(txtPass);
	        
	        JLabel lblNewLabel = new JLabel("New label");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Recursos/loguin.png")));
	        lblNewLabel.setBounds(-5, 43, 126, 120);
	        getContentPane().add(lblNewLabel);
	 
	 
	 
	      // Crear un escuchador al boton Aceptar 
	        ActionListener escuchadorbtnAceptar = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent evt)
	            {
	            	int permiso=0;
	                try
	                {     
	                    ValidarUsuarios validar = new ValidarUsuarios();
	                    validar.validarUsuario(txtUser.getText(), txtPass.getText());
	                    //chekar si el usuario escrbio el nombre de usuario y pw
	                    if (txtUser.getText().length() > 0 && txtPass.getText().length() > 0 )
	                    {
	                        // Si el usuario si fue validado correctamente
	                        if( validar.validarUsuario( txtUser.getText(), txtPass.getText() ) )    //enviar datos a validar
	                        {
	                            // Codigo para mostrar la ventana principal
	                            setVisible(false);
	                            
	                            
	                            ///////////////////////
	                            BaseDeDatos conn = new BaseDeDatos();
	                			
	                			ResultSet rs;
	                			try {
	                				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select idtipo_usuario from tblusuario where usuario = '"+txtUser.getText()+"' and clave ='"+txtPass.getText()+"'");
	                					while (rs.next()){
	                					permiso = rs.getInt(1);
	                					}
	                			} catch (ClassNotFoundException e) {
	                				// TODO Auto-generated catch block
	                				e.printStackTrace();
	                			} catch (SQLException e) {
	                				// TODO Auto-generated catch block
	                				e.printStackTrace();
	                			}
	                	
	                            
	                            //////////////////////////
	                            
	                            
	                            FrmMainPrincipal ventana1 = new FrmMainPrincipal();
	                           ventana1.permisos(permiso);
	                          
	                            ventana1.mostrar();
	 
	 
	                        }
	                        else
	                        {
	                            JOptionPane.showMessageDialog(null, "Nombre de Usuario y/o Contrasena No Validos");
	                            txtUser.setText("");    //limpiar campos
	                            txtPass.setText("");        
	                             
	                            txtUser.requestFocusInWindow();
	                        }
	 
	                    }
	                    else
	                    {
	                        JOptionPane.showMessageDialog(null, "Debe escribir nombre de usuario y contrasenia.\n" +
	                            "NO puede dejar ningun campo vacio");
	                    }
	 
	                } catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	                 
	            }
	        };
	        btnAceptar.addActionListener(escuchadorbtnAceptar);      // Asociar escuchador para el boton Aceptar
	 
	 
	      // Agregar escuchador al boton Cancelar
	        ActionListener escuchadorbtnCancelar=new ActionListener()
	        {
	            public void actionPerformed(ActionEvent evt)
	            {
	                System.exit(0);         // terminar el programa
	            }
	        };
	        btnCancelar.addActionListener(escuchadorbtnCancelar);      // Asociar escuchador para el boton Cancelar
	        setTitle("Autentificacion de Usuarios");
	        setSize(371,238);           // Tamanio del Frame 
	        setResizable(false);       // que no se le pueda cambiar el tamanio 
	        //Centrar la ventana de autentificacion en la pantalla
	        Dimension tamFrame=this.getSize();//para obtener las dimensiones del frame
	        Dimension tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();      //para obtener el tamanio de la pantalla
	        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);  //para posicionar
	        setVisible(true);           // Hacer visible al frame 
	 
	    }   // fin de constructor
	 
	  
	    public static void main(String[] args)
	    {
	        Login prueba = new Login();
	        prueba.setDefaultCloseOperation(prueba.EXIT_ON_CLOSE);
	    }
}

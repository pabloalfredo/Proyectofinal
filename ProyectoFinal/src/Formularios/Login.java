package Formularios;
import javax.swing.*;

import Clases.AplicarTemaVentana;
import Clases.BaseDeDatos;
import Clases.FondoFormulario;
import Clases.ImagenFormularios;
import Clases.ValidarUsuarios;
import Modelos.TipoUsuario;

import java.io.*;
import java.sql.*;
import java.util.Vector;
import java.awt.Panel.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

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
	    	getContentPane().setBackground(new Color(0, 0, 102));
	    
	    	setBackground(Color.LIGHT_GRAY);
	 
	        Container contenedor = getContentPane();
	        getContentPane().setLayout(null);
	        
	        JLabel lblLogin = new JLabel("      LOGIN");
	        lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);
	        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
	        lblLogin.setForeground(SystemColor.menu);
	        lblLogin.setFont(new Font("Arial Black", Font.BOLD, 52));
	        lblLogin.setBounds(6, 6, 526, 48);
	        getContentPane().add(lblLogin);
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 102)));
	        panel.setBackground(new Color(51, 153, 204));
	        panel.setBounds(6, 6, 526, 268);
	        panel.setBorder(new FondoFormulario("/Recursos/fondo.jpg"));
	        getContentPane().add(panel);
	               panel.setLayout(null);
	        
	               btnCancelar = new JButton("Cancelar");
	               btnCancelar.addActionListener(new ActionListener() {
	               	public void actionPerformed(ActionEvent arg0) {
	               		System.exit(0);
	               	}
	               });
	               btnCancelar.setBounds(407, 221, 119, 41);
	               panel.add(btnCancelar);
	               btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
	               btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
	               
	                      //Crear y agregar los botones 
	                      btnAceptar = new JButton("Aceptar");
	                      btnAceptar.addActionListener(new ActionListener() {
	                      	public void actionPerformed(ActionEvent arg0) {

	        	            	int permiso=0;
	        	            	String tipoUsuario="";
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
	        	                				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select idtipo_usuario, usuario  from tblusuario where usuario = '"+txtUser.getText()+"' and clave ='"+txtPass.getText()+"'");
	        	                					while (rs.next()){
	        	                					permiso = rs.getInt(1);
	        	                					tipoUsuario = rs.getString(2);
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
	        	                            ventana1.tipoUsuario(tipoUsuario);
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
	                      });
	                      btnAceptar.setBounds(278, 221, 119, 41);
	                      panel.add(btnAceptar);
	                      btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 12));
	                      btnAceptar.setIcon(new ImageIcon(Login.class.getResource("/Recursos/Aceptar (2).png")));
	                      //establecer Boton aceptar por defecto
	                      getRootPane().setDefaultButton(btnAceptar);
	                      
	                      txtPass = new JPasswordField();
	                      txtPass.setBounds(327, 189, 178, 28);
	                      panel.add(txtPass);
	                      txtPass.setFont(new Font("SansSerif", Font.BOLD, 12));
	                      
	                      txtUser = new JTextField();
	                      txtUser.setBounds(327, 150, 178, 28);
	                      panel.add(txtUser);
	                      txtUser.setFont(new Font("SansSerif", Font.BOLD, 12));
	                      txtUser.setColumns(10);
	                      
	                      JLabel lblNewLabel = new JLabel("New label");
	                      lblNewLabel.setBounds(400, 0, 126, 120);
	                      panel.add(lblNewLabel);
	                      lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Recursos/loguin.png")));
	                      
	                      JLabel lblUsuario = new JLabel("Usuario:");
	                      lblUsuario.setForeground(new Color(0, 0, 102));
	                      lblUsuario.setBounds(258, 155, 68, 16);
	                      panel.add(lblUsuario);
	                      lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
	                      lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
	                      
	                      JLabel lblContrasena = new JLabel("Contrasena:");
	                      lblContrasena.setForeground(new Color(0, 0, 102));
	                      lblContrasena.setBounds(238, 194, 88, 16);
	                      panel.add(lblContrasena);
	                      lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
	                      lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 14));
	                      
	                      JLabel lblNewLabel_1 = new JLabel("New label");
	                      lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
	                      lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
	                      lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Recursos/Icon LOGIN GrenSoft.png")));
	                      lblNewLabel_1.setBounds(-45, -13, 290, 243);
	                      panel.add(lblNewLabel_1);
	                      
	                      JCheckBox chckbxNewCheckBox = new JCheckBox("");
	                      chckbxNewCheckBox.setBackground(new Color(51, 153, 204));
	                      chckbxNewCheckBox.setBounds(505, 191, 21, 23);
	                      panel.add(chckbxNewCheckBox);
	                   
	 
	 
	        setTitle("Autentificacion de Usuarios");
	        setSize(539,282);           // Tamanio del Frame 
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
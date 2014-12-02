package Formularios;
import javax.swing.*;

import java.io.*;
import java.sql.*;
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
	    	setFont(new Font("Dialog", Font.BOLD, 12));
	    	setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Recursos/Icon GrenSoft3.png")));
	    	getContentPane().setBackground(new Color(51, 153, 204));
	    	setBackground(new Color(51, 153, 204));
	 
	        Container contenedor = getContentPane();
	        getContentPane().setLayout(null);
	 
	        //Crear y agregar los botones 
	        btnAceptar = new JButton("Aceptar");
	        btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 12));
	        btnAceptar.setIcon(new ImageIcon(Login.class.getResource("/Recursos/Aceptar (2).png")));
	        btnAceptar.setBounds(100, 171, 117, 45);
	        //establecer Boton aceptar por defecto
	        getRootPane().setDefaultButton(btnAceptar);
	 
	        btnCancelar = new JButton("Cancelar");
	        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
	        btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
	        btnCancelar.setBounds(238, 171, 117, 45);
	        contenedor.add(btnAceptar);
	        contenedor.add(btnCancelar);
	        
	        JLabel lblLogin = new JLabel("Login");
	        lblLogin.setForeground(new Color(0, 0, 128));
	        lblLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
	        lblLogin.setBounds(154, 11, 75, 25);
	        getContentPane().add(lblLogin);
	        
	        JLabel lblUsuario = new JLabel("Usuario");
	        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
	        lblUsuario.setBounds(133, 53, 55, 16);
	        getContentPane().add(lblUsuario);
	        
	        JLabel lblContrasena = new JLabel("Contrasena");
	        lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 12));
	        lblContrasena.setBounds(113, 95, 75, 16);
	        getContentPane().add(lblContrasena);
	        
	        txtUser = new JTextField();
	        txtUser.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtUser.setBounds(188, 47, 167, 28);
	        getContentPane().add(txtUser);
	        txtUser.setColumns(10);
	        
	        txtPass = new JPasswordField();
	        txtPass.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtPass.setBounds(188, 89, 167, 28);
	        getContentPane().add(txtPass);
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(192, 192, 192));
	        panel.setBounds(10, 53, 99, 94);
	        getContentPane().add(panel);
	 
	 
	 
	      // Crear un escuchador al boton Aceptar 
	        ActionListener escuchadorbtnAceptar = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent evt)
	            {
	                try
	                {                    
	                    //chekar si el usuario escrbio el nombre de usuario y pw
	                    if (txtUser.getText().length() > 0 && txtPass.getText().length() > 0 )
	                    {
	                        // Si el usuario si fue validado correctamente
	                        if( validarUsuario( txtUser.getText(), txtPass.getText() ) )    //enviar datos a validar
	                        {
	                            // Codigo para mostrar la ventana principal
	                            setVisible(false);
	                            frmPrincipal ventana1 = new frmPrincipal();
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
	        setSize(371,255);           // Tamanio del Frame 
	        setResizable(false);       // que no se le pueda cambiar el tamanio 
	        //Centrar la ventana de autentificacion en la pantalla
	        Dimension tamFrame=this.getSize();//para obtener las dimensiones del frame
	        Dimension tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();      //para obtener el tamanio de la pantalla
	        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);  //para posicionar
	        setVisible(true);           // Hacer visible al frame 
	 
	    }   // fin de constructor
	 
	    // Metodo que conecta con el servidor MYSQL y valida los usuarios
	    boolean validarUsuario(String elUsr, String elPw)  throws IOException
	    {
	        try
	        {
	            //nombre de la BD: bdlogin
	             //nombre de la tabla: usuarios
	             //                             id      integer auto_increment not null     <--llave primaria
	             //                   campos:    usuario    char(25)
	             //                              password char(50)
	        	
	        	Class.forName("com.mysql.jdbc.Driver");
	            Connection unaConexion  = DriverManager.getConnection ("jdbc:mysql://localhost/dbproyecto","root", "curne00");
	            // Preparamos la consulta
	            Statement instruccionSQL = unaConexion.createStatement();
	            ResultSet resultadosConsulta = instruccionSQL.executeQuery ("SELECT * FROM tblusuario WHERE usuario='"+elUsr+"' AND clave='"+ elPw+"'");
	 
	            if( resultadosConsulta.first() )        // si es valido el primer reg. hay una fila, tons el usuario y su pw existen
	                return true;        //usuario validado correctamente
	            else
	                return false;        //usuario validado incorrectamente
	                 
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	 
	    }
	     
	    public static void main(String[] args)
	    {
	        Login prueba = new Login();
	        prueba.setDefaultCloseOperation(prueba.EXIT_ON_CLOSE);
	    }
}

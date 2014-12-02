package Modelos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValidarUsuarios {
	
    public  boolean validarUsuario(String usuario, String contrasena)  throws IOException
    {
        try
        {
            
             //nombre de la tabla: usuarios
             //                             id      integer auto_increment not null     <--llave primaria
             //                   campos:    usuario    char(25)
             //                              password char(50)
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection unaConexion  = DriverManager.getConnection ("jdbc:mysql://localhost/dbproyecto","root", "curne00");
          
           
            Statement instruccionSQL = unaConexion.createStatement();
            ResultSet resultadosConsulta = instruccionSQL.executeQuery ( "SELECT * FROM tblusuario WHERE usuario='"+usuario+"' AND clave='"+ contrasena+"'");
 
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

}



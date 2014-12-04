package Clases;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import Clases.BaseDeDatos;

public class ValidarUsuarios {
	
    public  boolean validarUsuario(String usuario, String contrasena)  throws IOException
    {
        try
        {

        	BaseDeDatos conectar = new BaseDeDatos();
        	String sql ="SELECT * FROM tblusuario WHERE usuario='"+usuario+"' AND clave='"+ contrasena+"'";
        	Statement instruccionSQL = conectar.getConexion().createStatement();
        	ResultSet resultadoConsulta = instruccionSQL.executeQuery(sql);
        	
 
            if( resultadoConsulta.first() )        // si es valido el primer reg. hay una fila, tons el usuario y su pw existen
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

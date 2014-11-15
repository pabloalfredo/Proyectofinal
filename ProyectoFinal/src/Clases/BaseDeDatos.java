package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BaseDeDatos {
	public Connection cn;
    private static String URL ="jdbc:mysql://localhost/dbproyecto";
    private static String USUARIO = "root";
    private static String PASSWORD ="curne00";
    
    public void  open(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(URL, USUARIO, PASSWORD);        
        }catch(Exception ex){
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public ResultSet query (String sql){
        ResultSet rs;
        try{
            rs =cn.createStatement().executeQuery(sql);
            return rs;
        }catch(SQLException e){}
            return null;
    }
    
    public void execute (String sql){
        try{
            cn.createStatement().execute(sql);
       }catch(SQLException e){}
    }
    
    public void close (){
        cn=null;
    }
}

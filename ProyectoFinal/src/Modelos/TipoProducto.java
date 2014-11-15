package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.BackingStoreException;

import Clases.BaseDeDatos;

public class TipoProducto {
	private int idTipoProducto;
	private String Descripcion;
	
	public TipoProducto(int idTipoProducto, String Descripcion){
	
		setIdTipoProducto(idTipoProducto);
		setDescripcion(Descripcion);
	}
	public TipoProducto( String Descripcion){
		
		
		setDescripcion(Descripcion);
	}


	public int getIdTipoProducto() {
		return idTipoProducto;
	}
	public String getDescripcion() {
		return Descripcion;
		
	}
	
	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	public void imprimir (){
		System.out.println(getDescripcion());
	}
	
public int AgregarTipoProducto() throws ClassNotFoundException, SQLException {
	//la que funciona
	/*
	String sql ="insert into tbltipoproducto (Descripcion) value ('"+getDescripcion()+"')";
	
	BaseDeDatos conn = new BaseDeDatos();
    conn.open();
    conn.execute(sql);
    conn.close();
	
	*/
	
	
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbproyecto", "root", "curne00");
	
	
	//BaseDeDatos conn = new BaseDeDatos();
	String sql = "insert into tbltipoproducto (Descripcion) values (?)";
	PreparedStatement instruccion = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	instruccion.setString(1, getDescripcion());
	
	
	instruccion.execute();
	
	ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
	
	int valorRetorno = 0;
	while(clavesGeneradas.next())
	{
		valorRetorno = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
	}	
	
	return valorRetorno;
	
	/*
	//////////////////////////////////////////////////
	String sql = "insert into tbltipoproducto  values (?)";
	PreparedStatement instruccion = conn.cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	instruccion.setString(1, getDescripcion());
	
	
   
    conn.open();
    conn.execute(instruccion.toString());
    conn.close();
	
    ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
    int valorRetorno = 0;
	while(clavesGeneradas.next())
	{
		valorRetorno = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
	}	
	
	return valorRetorno;
	
	
	
	
	
	
	////////////////////////////////////////////////
	
	
	/*
	
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate", "root", "1234");
		
		String sql = "insert into empleados (nombre, direccion, telefono, sueldo) values (?,?,?,?)";
		PreparedStatement instruccion = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		instruccion.setString(1, obtenerNombre());
		instruccion.setString(2, obtenerDireccion());
		instruccion.setString(3, obtenerTelefono());
		instruccion.setDouble(4, obtenerSueldo());
		
		instruccion.execute();
		
		ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
		
		int valorRetorno = 0;
		while(clavesGeneradas.next())
		{
			valorRetorno = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
		}	
		
		return valorRetorno;
		
		
		////////////////////////////////////////////
		
		
		if ((Descripcion != null) && (!Descripcion.equals(""))){
			String sql ="insert into tbltipoproducto (Descripcion) value ('"+Descripcion+"')";
			
	        conexion conn = new conexion();
	        conn.open();
	        conn.execute(sql);
	        conn.close();
	        
	        JOptionPane.showMessageDialog(null, "La categoria a sido agregada");
	        
		}
		
			else{
				JOptionPane.showMessageDialog(null, "La categoria no puede estar en blanco");
			}
		}
		
		*/
		 
	}
}

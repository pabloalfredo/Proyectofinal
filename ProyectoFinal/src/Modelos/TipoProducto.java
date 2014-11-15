package Modelos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.BackingStoreException;
import Clases.BaseDeDatos;

public class TipoProducto  {
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
	
public void AgregarTipoProducto() throws ClassNotFoundException, SQLException {
	//la que funciona
	/*
	String sql ="insert into tbltipoproducto (Descripcion) value ('"+getDescripcion()+"')";
	
	BaseDeDatos conn = new BaseDeDatos();
    conn.open();
    conn.execute(sql);
    conn.close();
	
	*/
	BaseDeDatos conn = new BaseDeDatos();
	String sql = "insert into tbltipoproducto (Descripcion) values (?)";
	PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
	instruccion.setString(1, getDescripcion());
	instruccion.execute();
	
	
	
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
		
		
		*/
		 
	}

public void ModificarTipoProducto() throws ClassNotFoundException, SQLException {
	
	BaseDeDatos conn = new BaseDeDatos();
	String sql = "update tbltipoproducto set Descripcion= ? where ID = ?";
	PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
	instruccion.setString(1, getDescripcion());
	instruccion.setInt(2, getIdTipoProducto());
	instruccion.executeUpdate();
	
	
	
	
		 
	}
}

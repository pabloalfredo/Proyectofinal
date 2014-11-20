package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

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
	
	BaseDeDatos conn = new BaseDeDatos();
	String sql = "insert into tbltipoproducto (Descripcion) values (?)";
	PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
	instruccion.setString(1, getDescripcion());
	instruccion.execute();
	
	
	/*
	BaseDeDatos conn = new BaseDeDatos();
	String sql = "insert into tbltipoproducto (Descripcion) values (?)";
	PreparedStatement instruccion = conn.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	instruccion.setString(1, getDescripcion());
	instruccion.execute();
	
	ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
	
	int valorRetorno = 0;
	while(clavesGeneradas.next())
	{
		valorRetorno = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
	}	
	JOptionPane.showMessageDialog(null, "El Codigo nuevo es"+ valorRetorno);
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

	public int metodoPrueba(){
		
		return 1;
	}
}

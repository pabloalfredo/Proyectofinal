package Modelos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Clases.BaseDeDatos;

public class NuevoUsuario {
	
	private String nombre;
	private String apellido;
	private String nonUsuario;
	private String tipoUsuario;
	private String contrasena;
	private String rContrasena;
	private TipoUsuario TipoUsuario;
	private int idTipoUsuario;
	
	public NuevoUsuario ( String Nombre, String Apellido, String NonUsuario, String TipoUsuario, String Contrasena, String RContrasena)
	{
		setNombre(Nombre);
		setApellido(Apellido);
		setNonUsuario(NonUsuario);
		setTipoUsuario(TipoUsuario);
		setContrasena(RContrasena);
		setrContrasena(RContrasena);
	
	}
	public NuevoUsuario ( String NonUsuario,  String Contrasena, TipoUsuario TipoUsuario, String Nombre, String Apellido)
	{
		
		setNonUsuario(NonUsuario);
		setContrasena(Contrasena);
		this.TipoUsuario = TipoUsuario;
		setNombre(Nombre);
		setApellido(Apellido);
	
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getNonUsuario() {
		return nonUsuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	public String getrContrasena() {
		return rContrasena;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setNonUsuario(String nonUsuario) {
		this.nonUsuario = nonUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getIdTipoUsuario1() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoProducto) {
		this.idTipoUsuario = idTipoProducto;
	}
	public void setrContrasena(String rContrasena) {
		this.rContrasena = rContrasena;
	}
	
	public TipoUsuario getTipoUsuario() {
		return TipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}
	public void AgregarNuevoUsuario() throws ClassNotFoundException, SQLException {
		
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "insert into tblusuario (usuario, clave, idtipo_usuario, Nombre, Apellido) "
				+ "values (?,?,?,?,?)";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setString(1, getNonUsuario());
		instruccion.setString(2, getContrasena());
		instruccion.setInt(3,getTipoUsuario().getIdTipoUsuario());
		instruccion.setString(4, getNombre());
		instruccion.setString(5, getApellido());
		instruccion.execute();
	}
/*	public void ModificarUsuario () throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "update tblproducto set Descripcion =?, Precio=?,Idtipoproducto=? where Codigo=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setString(1, getDescripcionProducto());
		instruccion.setFloat(2, getPrecioProducto());
		instruccion.setInt(3, getTipoProducto().getIdTipoProducto());
		instruccion.setInt(4, getCodigoProducto());
		instruccion.execute();
		//TODO:ARREGLAR MODIFICAR PRODUCTO YA QUE Codigo no es la llave principal
	}*/

}

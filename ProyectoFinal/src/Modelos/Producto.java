package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Clases.BaseDeDatos;

public class Producto {
	private int idProducto;
	private int CodigoProducto;
	private String DescripcionProducto;
	private float PrecioProducto;
	private TipoProducto TipoProducto;
	private int existencia;
	
	
	public Producto (int idProducto, int CodigoProducto, String DescripcionProducto, float PrecioProducto, TipoProducto TipoProducto)
	{
		setIdProducto(idProducto);
		setCodigoProducto(CodigoProducto);
		setDescripcionProducto(DescripcionProducto);
		setPrecioProducto(PrecioProducto);
		this.TipoProducto = TipoProducto;
		
	}
	public Producto ( int CodigoProducto, String DescripcionProducto, float PrecioProducto, TipoProducto TipoProducto)
	{
		setCodigoProducto(CodigoProducto);
		setDescripcionProducto(DescripcionProducto);
		setPrecioProducto(PrecioProducto);
		this.TipoProducto = TipoProducto;
		
	}
	public Producto ( int CodigoProducto, int existencia)
	{
		setCodigoProducto(CodigoProducto);
		setExistencia(existencia);
		
	}
	public Producto ( int CodigoProducto, String DescripcionProducto, float PrecioProducto)
	{
		setCodigoProducto(CodigoProducto);
		setDescripcionProducto(DescripcionProducto);
		setPrecioProducto(PrecioProducto);
	
	}
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCodigoProducto() {
		return CodigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		CodigoProducto = codigoProducto;
	}
	public String getDescripcionProducto() {
		return DescripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		DescripcionProducto = descripcionProducto;
	}
	public float getPrecioProducto() {
		return PrecioProducto;
	}
	public void setPrecioProducto(float precioProducto) {
		PrecioProducto = precioProducto;
	}
	public TipoProducto getTipoProducto() {
		return TipoProducto;
	}
	public void setTipoProducto(TipoProducto tipoProducto) {
		TipoProducto = tipoProducto;
	}
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
	
	public void AgregarProducto() throws ClassNotFoundException, SQLException {
		
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "insert into tblproducto (idproducto, Codigo, Descripcion, Precio, Idtipoproducto) "
				+ "values (?,?,?,?,?)";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, getIdProducto());
		instruccion.setInt(2, getCodigoProducto());
		instruccion.setString(3, getDescripcionProducto());
		instruccion.setFloat(4, getPrecioProducto());
		instruccion.setInt(5, getTipoProducto().getIdTipoProducto());
		instruccion.execute();
	}
	
	public void ModificarProducto () throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "update tblproducto set Descripcion =?, Precio=?,Idtipoproducto=? where Codigo=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setString(1, getDescripcionProducto());
		instruccion.setFloat(2, getPrecioProducto());
		instruccion.setInt(3, getTipoProducto().getIdTipoProducto());
		instruccion.setInt(4, getCodigoProducto());
		instruccion.execute();
		//TODO:ARREGLAR MODIFICAR PRODUCTO YA QUE Codigo no es la llave principal
	}
	public void AgregarExistenciaProducto() throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		int existenciaActual=0;
		int existenciaActualizada=0;
		ResultSet rs;
		try {
			rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select Existencia from tblproducto where Codigo ='"+getCodigoProducto() +"'");
				while (rs.next()){
					existenciaActual = rs.getInt(1);
				}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		existenciaActualizada= existenciaActual + getExistencia();
		
		String sql = "update tblproducto set Existencia =? where Codigo=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, existenciaActualizada);
		instruccion.setInt(2, getCodigoProducto());
		instruccion.execute();
		
		JOptionPane.showMessageDialog(null, "El producto No. " + getCodigoProducto() + "ha sido actualizado");
	}
	
	public void restarExistenciaInventario() throws ClassNotFoundException, SQLException{//SE OBTIENE LA CANTIDAD DE LA TABLA PARA RESTARAR DEL INVENTARIO
		BaseDeDatos conn = new BaseDeDatos();
		int existenciaActual=0;
		int existenciaActualizada=0;
		ResultSet rs;
		try {
			rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select Existencia from tblproducto where Codigo ='"+getCodigoProducto() +"'");
				while (rs.next()){
					existenciaActual = rs.getInt(1);
				}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		existenciaActualizada= existenciaActual - getExistencia();
		
		String sql = "update tblproducto set Existencia =? where Codigo=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, existenciaActualizada);
		instruccion.setInt(2, getCodigoProducto());
		instruccion.execute();
		
		//JOptionPane.showMessageDialog(null, "El producto No. " + getCodigoProducto() + "ha sido actualizado");
		
	}
	
		public boolean validarExistenciaInventario() throws ClassNotFoundException, SQLException{//SE OBTIENE LA CANTIDAD DE LA TABLA PARA RESTARAR DEL INVENTARIO
			BaseDeDatos conn = new BaseDeDatos();
			int existenciaActual=0;
			int existenciaActualizada=0;
			ResultSet rs;
			try {
				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select Existencia from tblproducto where Codigo ='"+getCodigoProducto() +"'");
					while (rs.next()){
						existenciaActual = rs.getInt(1);
					}
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			existenciaActualizada= existenciaActual - getExistencia();
			
			if (existenciaActualizada >=0){
			return true;
			}
			
			else {
				JOptionPane.showMessageDialog(null, "El producto No. " + getCodigoProducto() + " solo tiene " + existenciaActual+ " en el inventario" );
				return false;
			}
			
		
	}
}

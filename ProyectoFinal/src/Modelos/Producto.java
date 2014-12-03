package Modelos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void ModificarProducto (){
		
	}
	
}

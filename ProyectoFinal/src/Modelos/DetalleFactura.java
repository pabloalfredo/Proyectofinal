package Modelos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Clases.BaseDeDatos;

public class DetalleFactura {
	private int idDetalle;
	private Factura Factura;
	private int codigoProducto;
	private int cantidadProducto;
	private double precio;
	private double subTotal;
	private double descuento;
	
	public DetalleFactura(int idDetalle, Modelos.Factura factura,int codigoProducto, int cantidadProducto, double precio,double subTotal, double descuento) {
		super();
		this.idDetalle = idDetalle;
		Factura = factura;
		this.codigoProducto = codigoProducto;
		this.cantidadProducto = cantidadProducto;
		this.precio = precio;
		this.subTotal = subTotal;
		this.descuento = descuento;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Factura getFactura() {
		return Factura;
	}

	public void setFactura(Factura factura) {
		Factura = factura;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public void agregarDetalleFactura() throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "insert into tbldetallefactura  "
				+ "values (?,?,?,?,?,?,?)";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, getIdDetalle());
		instruccion.setInt(2, getFactura().getIdFactura());
		instruccion.setInt(3, getCodigoProducto());
		instruccion.setInt(4,getCantidadProducto());
		instruccion.setDouble(5, getPrecio());
		instruccion.setDouble(6, getSubTotal());
		instruccion.setDouble(7, getDescuento());
		
		
		instruccion.execute();
	}
}

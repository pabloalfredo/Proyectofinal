package Modelos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Clases.BaseDeDatos;

public class Factura {
	private int idFactura;
	private Date fecha;
	private int idUsuario;
	private int idCliente;
	private double totalFactura;
	
	public Factura(int idFactura, Date fecha, int idUsuario, int idCliente, double totalFactura) {
		
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.totalFactura = totalFactura;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public double getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}
	public void agregarFactura() throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "insert into tblfactura  "
				+ "values (?,?,?,?,?)";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, getIdFactura());
		instruccion.setDate(2, (java.sql.Date) getFecha());
		instruccion.setInt(3, getIdUsuario());
		instruccion.setInt(4, getIdCliente());
		instruccion.setDouble(5, getTotalFactura());
		
		
		instruccion.execute();
	}
}

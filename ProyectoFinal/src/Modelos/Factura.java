package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;









import javax.swing.JTextField;

import Clases.BaseDeDatos;

public class Factura {
	private int idFactura;
	private String fecha;
	private int idUsuario;
	private int idCliente;
	

	private double subtotal;
	private double itbis;
	private double totalFactura;
	private String comprobante;
	
	public Factura(int idFactura, String fecha, int idUsuario, int idCliente, double totalFactura) {
		
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.totalFactura = totalFactura;
	}
public Factura(int idFactura, String fecha, int idUsuario, int idCliente, double totalFactura,String comprobante) {
		
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.totalFactura = totalFactura;
		this.comprobante = comprobante;
	}
	public Factura(String fecha, int idUsuario, int idCliente, double subtotal, double itbis, double totalFactura, String comprobante) {
		
		this.fecha = fecha;
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.subtotal = subtotal;
		this.itbis = itbis;
		this.totalFactura = totalFactura;
		this.comprobante = comprobante;
	}
		public Factura(int idFactura) {
		
		this.idFactura = idFactura;
		
		
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
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
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getItbis() {
		return itbis;
	}
	public void setItbis(double itbis) {
		this.itbis = itbis;
	}
	public double getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public int agregarFactura() throws ClassNotFoundException, SQLException{
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "insert into tblfactura2  "
				+ "values (?,?,?,?,?,?,?,?)";
		
		
			Calendar fecha = Calendar.getInstance();
	      String fechaMysql = String.format("%d-%d-%d %d:%d:%d", fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH)+1, fecha.get(Calendar.DAY_OF_MONTH),
	    		  fecha.get(Calendar.HOUR), fecha.get(Calendar.MINUTE), fecha.get(Calendar.SECOND));
		
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		instruccion.setInt(1, getIdFactura());
		instruccion.setString(2, fechaMysql);
		instruccion.setInt(3, getIdUsuario());
		instruccion.setInt(4, getIdCliente());
		instruccion.setDouble(5, getSubtotal());
		instruccion.setDouble(6, getItbis());
		instruccion.setDouble(7, getTotalFactura());
		instruccion.setString(8, getComprobante());
		
		instruccion.execute();
		ResultSet clavesGeneradas = instruccion.getGeneratedKeys();
		
		int valorRetorno = 0;
		while(clavesGeneradas.next())
		{
			valorRetorno = Integer.parseInt( clavesGeneradas.getObject(1).toString() );
		}	
		JOptionPane.showMessageDialog(null, "La Factura ha sido guardada con el Numero "+ valorRetorno);
		return valorRetorno;
	}
	
	public String fechaFacturaBuscada (){
		String fechaMysql = null;
		BaseDeDatos conn = new BaseDeDatos();
		ResultSet rs;
		try {
			rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select fecha from tblfactura2 where idFactura = '"+getIdFactura() +"'");
				while (rs.next()){
				fechaMysql=rs.getString(1);

				}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El Codigo no existe en el registro");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(fechaMysql);
		
		return fechaMysql;
		
		
	}

}

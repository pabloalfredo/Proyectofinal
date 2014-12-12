package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Clases.BaseDeDatos;

public class Comprobante {
	private int idComprobante;
	private String descripcionComprobante;
	private int secuenciaComprobante;
	private int limiteComprobante;

	
	public Comprobante(int idComprobante, String descripcionComprobante,int secuenciaComprobante, int limiteComprobante) {
		this.idComprobante = idComprobante;
		this.descripcionComprobante = descripcionComprobante;
		this.secuenciaComprobante = secuenciaComprobante;
		this.limiteComprobante = limiteComprobante;
	}

	public Comprobante(int idComprobante, String descripcionComprobante ) {
		this.idComprobante = idComprobante;
		this.descripcionComprobante = descripcionComprobante;
	
	}

	public int getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}

	public String getDescripcionComprobante() {
		return descripcionComprobante;
	}

	public void setDescripcionComprobante(String descripcionComprobante) {
		this.descripcionComprobante = descripcionComprobante;
	}

	public int getSecuenciaComprobante() {
		return secuenciaComprobante;
	}

	public void setSecuenciaComprobante(int secuenciaComprobante) {
		this.secuenciaComprobante = secuenciaComprobante;
	}

	public int getLimiteComprobante() {
		return limiteComprobante;
	}

	public void setLimiteComprobante(int limiteComprobante) {
		this.limiteComprobante = limiteComprobante;
	}
	public int sumarSecuenciaComprobante() throws ClassNotFoundException, SQLException{//SE OBTIENE LA CANTIDAD DE LA TABLA PARA RESTARAR DEL INVENTARIO
		BaseDeDatos conn = new BaseDeDatos();
		int secuenciaActual=0;
		int secuenciaActualizada=0;
		int numeroComprobanteRetornado;
		ResultSet rs;
		try {
			rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select secuencia from tblcomprobante where idcomprobante ='"+getIdComprobante() +"'");
				while (rs.next()){
					secuenciaActual = rs.getInt(1);
				
					
				}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		secuenciaActualizada= secuenciaActual + 1;
		
		String sql = "update tblcomprobante set secuencia =? where idcomprobante=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, secuenciaActualizada);
		instruccion.setInt(2, getIdComprobante());
		instruccion.executeUpdate();
		
		return secuenciaActualizada;
		
	}
	/*public void secuenciaActualizada () throws ClassNotFoundException, SQLException {
		BaseDeDatos conn = new BaseDeDatos();
		String sql = "update tblcomprobante set secuencia =? where idcomprobante=? ";
		PreparedStatement instruccion = conn.getConexion().prepareStatement(sql);
		instruccion.setInt(1, secuenciaActualizada);
		instruccion.setInt(2, getIdComprobante());
		instruccion.executeUpdate();
	}*/
	public int retornarNumero() {
		
		return getIdComprobante();
	}
	
}

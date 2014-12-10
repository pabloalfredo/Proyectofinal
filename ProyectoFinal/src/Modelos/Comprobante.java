package Modelos;

public class Comprobante {
	private String idComprobante;
	private String descripcionComprobante;
	private int secuenciaComprobante;
	private int limiteComprobante;
	
	public Comprobante(String idComprobante, String descripcionComprobante,int secuenciaComprobante, int limiteComprobante) {
		this.idComprobante = idComprobante;
		this.descripcionComprobante = descripcionComprobante;
		this.secuenciaComprobante = secuenciaComprobante;
		this.limiteComprobante = limiteComprobante;
	}

	public String getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(String idComprobante) {
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
	
	
}

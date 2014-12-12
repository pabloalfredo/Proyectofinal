package Modelos;

public class TipoUsuario {
	
	private int idTipoUsuario;
	private String Descripcion;
	
	public TipoUsuario(int idTipoProudcto, String Descripcion){
	
		setIdTipoProducto(idTipoUsuario);
		setDescripcion(Descripcion);
	}
	public TipoUsuario( String Descripcion){
		
		
		setDescripcion(Descripcion);
	}


	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public String getDescripcion() {
		return Descripcion;
		
	}
	
	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoUsuario = idTipoProducto;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}

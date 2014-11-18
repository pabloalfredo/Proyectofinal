package Modelos;

public class Producto {
	private int idProducto;
	private int CodigoProducto;
	private String DescripcionProducto;
	private float PrecioProducto;
	private TipoProducto TipoProducto;
	
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
	
	public void AgregarProducto() {
		
		
	}
	
	public void ModificarProducto (){
		
	}
	
}

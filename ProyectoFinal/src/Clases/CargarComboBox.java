package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;

import Modelos.TipoProducto;

public class CargarComboBox {
	public Vector <TipoProducto> categorias;
	
	public void cargarComboBox(JComboBox cmbTipoAgregarProducto) {//para llenar el comboBox
		
		
			BaseDeDatos conn = new BaseDeDatos();
				categorias = new Vector<>();
				ResultSet rs;
				try {
					rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select ID, Descripcion from tbltipoproducto");
						while (rs.next()){
						TipoProducto categoria = new TipoProducto(rs.getInt(1), rs.getString(2));	
						
						categorias.add(categoria);
						cmbTipoAgregarProducto.addItem(categoria.getDescripcion());
						
						}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}

}
package Clases;

import javax.swing.JLabel;
import javax.swing.JTable;

public class Utilidades {

	public void sumarFilasTabla(JTable table, JLabel lblTotalFilas){
			int total=0;
	//		METODO PARA SUMAR TODOS LOS PRODUCTOS.
		        //recorrer todas las filas de la segunda columna y va sumando las cantidades
			        for( int i=0 ; i<table.getRowCount(); i++)
			        {
			           total++;  
			        }
		        //muestra en el componente
		        lblTotalFilas.setText( String.valueOf(total) );
		}
}

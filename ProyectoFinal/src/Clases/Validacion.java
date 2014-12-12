package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validacion {

	public void validarTxtSoloNumero(JTextField txtField)//VALIDA QUE SOLO SE INGRESEN NUMERO EN EL TEXTBOXT
	{
		txtField.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
	}
	
	public void validarTxtEnBlanco (JTextField txtField){
		if(txtField.getText().trim() == ""){
			
		}
	}
}

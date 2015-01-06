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
		      if(((caracter < '0') ||(caracter > '9')) &&(caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
	}
	
	public void validarTxtLetras(JTextField txtField)//VALIDA QUE SOLO SE INGRESEN NUMERO EN EL TEXTBOXT
	{
		txtField.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      int caracter = (int) e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if (caracter >= 97 && caracter <= 122 || caracter >= 65 && caracter <= 90)
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
	}
public void validarTxtSoloLetras(KeyEvent e){
		
		JTextField campo = ((JTextField)e.getSource());
		String texto = campo.getText();
		try
		{			
			Long.parseLong(texto);
		}
		catch (NumberFormatException excepcion)
		{
			if(texto.length() > 0)
			{
				campo.setText(texto.substring(0, texto.length() - 1));	
			}
			
		}
		
		
	}
	
	public void validarTxtEnBlanco (JTextField txtField){
		if(txtField.getText().trim() == ""){
			
		}
	}
}

















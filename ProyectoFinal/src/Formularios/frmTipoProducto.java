package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmTipoProducto {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTipoProducto window = new frmTipoProducto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmTipoProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 445, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Guardar");
		button.setFont(new Font("SansSerif", Font.BOLD, 12));
		button.setBounds(46, 200, 116, 38);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Modificar");
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(174, 200, 107, 38);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_2.setBounds(293, 200, 114, 38);
		frame.getContentPane().add(button_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 158, 122, 26);
		frame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("Tipo");
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(36, 163, 55, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Precio");
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_1.setBounds(36, 135, 55, 16);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(117, 129, 122, 28);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 90, 223, 28);
		frame.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Descripcion");
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_2.setBounds(36, 96, 79, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Codigo");
		label_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_3.setBounds(36, 60, 55, 16);
		frame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 54, 122, 28);
		frame.getContentPane().add(textField_2);
		
		JButton button_3 = new JButton("Generar ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		button_3.setToolTipText("Generar un Codigo si el Producto no tiene Codigo");
		button_3.setBounds(262, 54, 79, 28);
		frame.getContentPane().add(button_3);
		
		JLabel label_4 = new JLabel("Agregar Productos");
		label_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		label_4.setBounds(148, 11, 184, 31);
		frame.getContentPane().add(label_4);
	}

}

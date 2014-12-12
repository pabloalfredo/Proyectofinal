package Formularios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ModificarUsuario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificarUsuario() {
		setTitle("Agregaro Modificar Usuario");
		setBounds(100, 100, 460, 422);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Agregar/Modificar Usuario");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label.setBounds(24, 11, 410, 22);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_1.setBounds(34, 115, 52, 16);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Apellido");
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_2.setBounds(34, 151, 52, 16);
		getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(98, 112, 222, 22);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 148, 222, 22);
		getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("Nombre de Usuario:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_3.setBounds(34, 203, 123, 16);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Tipo de Usuario");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_4.setBounds(34, 232, 103, 16);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Contrasena");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_5.setBounds(34, 261, 103, 16);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Repita su Contrasena");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label_6.setBounds(24, 290, 121, 16);
		getContentPane().add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(159, 200, 161, 22);
		getContentPane().add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(158, 229, 162, 22);
		getContentPane().add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 260, 161, 20);
		getContentPane().add(passwordField);
		
		JButton button = new JButton("Registrar");
		button.setIcon(new ImageIcon(ModificarUsuario.class.getResource("/Recursos/Aceptar (2).png")));
		button.setSelectedIcon(new ImageIcon(ModificarUsuario.class.getResource("/Recursos/Aceptar (2).png")));
		button.setEnabled(false);
		button.setFont(new Font("SansSerif", Font.BOLD, 12));
		button.setBounds(34, 334, 123, 45);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setIcon(new ImageIcon(ModificarUsuario.class.getResource("/com/birosoft/liquid/icons/error.png")));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(306, 334, 123, 45);
		getContentPane().add(button_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(159, 289, 161, 20);
		getContentPane().add(passwordField_1);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnGuardar.setBounds(173, 334, 123, 45);
		getContentPane().add(btnGuardar);
		
		JLabel label_7 = new JLabel("Buscar Usuario");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(24, 60, 103, 14);
		getContentPane().add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(137, 58, 186, 30);
		getContentPane().add(textField_3);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(ModificarUsuario.class.getResource("/Recursos/search_icon.png")));
		button_3.setBounds(333, 53, 89, 45);
		getContentPane().add(button_3);

	}

}

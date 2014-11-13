package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class frmLoguin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLoguin window = new frmLoguin();
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
	public frmLoguin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 415, 262);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Loguin");
		label.setForeground(new Color(25, 25, 112));
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(159, 11, 89, 33);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground((Color) null);
		panel.setBounds(20, 56, 103, 113);
		frame.getContentPane().add(panel);
		
		JLabel label_1 = new JLabel("New label");
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Contrasena");
		label_3.setFont(new Font("SansSerif", Font.BOLD, 13));
		label_3.setBounds(135, 95, 79, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Usuario");
		label_4.setFont(new Font("SansSerif", Font.BOLD, 13));
		label_4.setBounds(135, 56, 55, 16);
		frame.getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setToolTipText("Ingrese su Nombre de Usuario");
		textField.setFont(new Font("SansSerif", Font.BOLD, 12));
		textField.setColumns(10);
		textField.setBounds(222, 50, 158, 28);
		frame.getContentPane().add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Ingrese su Contrasena");
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 12));
		passwordField.setBounds(222, 89, 158, 28);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("Aceptar");
		button.setIcon(new ImageIcon(frmLoguin.class.getResource("/Recursos/Aceptar (2).png")));
		button.setToolTipText("Hacer clic para entrar");
		button.setFont(new Font("SansSerif", Font.BOLD, 12));
		button.setBounds(134, 144, 114, 52);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setIcon(new ImageIcon(frmLoguin.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		button_1.setToolTipText("Hacer clic para cancelar");
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(266, 144, 114, 52);
		frame.getContentPane().add(button_1);
	}
}

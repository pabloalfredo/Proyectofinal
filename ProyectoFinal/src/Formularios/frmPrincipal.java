package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class frmPrincipal {

	private JFrame frmFormularioPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal window = new frmPrincipal();
					window.frmFormularioPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFormularioPrincipal = new JFrame();
		frmFormularioPrincipal.setTitle("Formulario Principal");
		frmFormularioPrincipal.setBounds(100, 100, 450, 300);
		frmFormularioPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

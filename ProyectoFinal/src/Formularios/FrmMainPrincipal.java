package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.Font;

import javax.swing.ImageIcon;

import Clases.ImagenJDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;

public class FrmMainPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMainPrincipal window = new FrmMainPrincipal();
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
	public FrmMainPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 539, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mnSistema.add(mntmLogin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
		
		JMenu mnMantenimientos = new JMenu("Mantenimientos");
		menuBar.add(mnMantenimientos);
		
		JMenuItem mntmCategorias = new JMenuItem("Categorias de Productos");
		mnMantenimientos.add(mntmCategorias);
		
		JMenuItem mntmInventariosDeProductos = new JMenuItem("Inventarios de Productos");
		mnMantenimientos.add(mntmInventariosDeProductos);
		
		
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		final JDesktopPane desktopPane = new JDesktopPane();
		//PARA ASIGNAR LA IMAGEN EN JDESKTOPPANE EN EL FORMULARIO PRINCIPAL.
		desktopPane.setBorder(new ImagenJDesktopPane());
		
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(0, 599, 1362, 28);
		desktopPane.add(toolBar_1);
		
		JButton btnFactura = new JButton("Factura");
		btnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFactura frm = new frmFactura();
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		btnFactura.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415496836_698568-icon-56-document-text-48.png")));
		btnFactura.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnFactura);
		
		JButton btnInventarios = new JButton("Inventarios");
		btnInventarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProducto frm = new frmProducto();
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		btnInventarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInventarios.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/inventario.png")));
		toolBar.add(btnInventarios);
		
		JButton btnDevoluciones = new JButton("Devoluciones");
		btnDevoluciones.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415499004_Rotation.png")));
		btnDevoluciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnDevoluciones);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415499313_Computer_Analysis-48.png")));
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnReportes);
		
		
	}

}

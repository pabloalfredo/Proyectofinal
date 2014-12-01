package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import Clases.ImagenJDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.SystemColor;
import javax.swing.UIManager;

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
		frmFormularioPrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmFormularioPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(frmPrincipal.class.getResource("/Recursos/Icon GrenSoft.png")));
		frmFormularioPrincipal.setTitle("Formulario Principal");
		frmFormularioPrincipal.setBounds(100, 100, 644, 440);
		frmFormularioPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//PARA ASIGNAR LA IMAGEN EN JDESKTOPPANE EN EL FORMULARIO PRINCIPAL.
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new ImagenJDesktopPane());
		
	
		
		
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frmFormularioPrincipal.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				frmLoguin frm = new frmLoguin();
				
				
				
				
				
				
			}
		});
		mnNewMenu.add(mntmLogin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir...");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmSalir);
		
		JMenu mnNewMenu_1 = new JMenu("Mantenimientos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmTipoDeProductos = new JMenuItem("Tipo de Productos");
		mntmTipoDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProducto o1 = new frmProducto();
				frmTipoProducto frm = new frmTipoProducto();
				frm.setVisible(true);
				
	
			}
		});
		mnNewMenu_1.add(mntmTipoDeProductos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Inventario de Productos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProducto frm = new frmProducto();
				frm.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOpaque(true);
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBackground(UIManager.getColor("Button.background"));
		toolBar_1.setForeground(SystemColor.inactiveCaptionText);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
				.addComponent(toolBar_1, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 630, Short.MAX_VALUE)
					.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
		);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JButton btnFactura = new JButton("Factura");
		btnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmFactura frm = new frmFactura();
				frm.setVisible(true);
				
				
			}
		});
		btnFactura.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Recursos/1415496836_698568-icon-56-document-text-48.png")));
		toolBar.add(btnFactura);
		
		JButton btnInventarios = new JButton("Inventarios");
		btnInventarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProducto frm = new frmProducto();
				frm.setVisible(true);
			}
		});
		btnInventarios.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Recursos/inventario.png")));
		toolBar.add(btnInventarios);
		
		JButton btnNewButton = new JButton("Devoluciones");
		btnNewButton.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Recursos/1415499004_Rotation.png")));
		toolBar.add(btnNewButton);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnReportes.setIcon(new ImageIcon(frmPrincipal.class.getResource("/Recursos/1415499313_Computer_Analysis-48.png")));
		toolBar.add(btnReportes);
		desktopPane.setLayout(gl_desktopPane);
		
	}

	public void mostrar() {
		frmFormularioPrincipal.setVisible(true);
		// TODO Auto-generated method stub
		
	}
}

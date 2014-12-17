package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.Font;

import javax.swing.ImageIcon;

import Clases.AplicarTemaVentana;
import Clases.ImagenJDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;

import javax.swing.JLabel;

import java.awt.Color;

public class FrmMainPrincipal {

	private JFrame frame;
	private JButton btnInventarios;
	private JButton btnFactura;
	private JButton btnDevoluciones;
	private JButton btnReportes;
	private JMenuItem mntmCategorias;
	private JMenuItem mntmInventariosDeProductos;
	private JMenuItem mntmAgregarUsuario;
	private JMenu mnMantenimientos;
	private JMenu mnBusqueda;
    private JLabel lbtipousuario;
    private String usuario;
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
		
		AplicarTemaVentana aplicar = new AplicarTemaVentana();
		aplicar.temaliquid();
		
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 539, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JDesktopPane desktopPane = new JDesktopPane();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmLogin = new JMenuItem("Cambiar  de  Usuario");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarFrame();
				Login frm = new Login();
				frm.setVisible(true);
				
			}
		});
		mnSistema.add(mntmLogin);
		
		mntmAgregarUsuario = new JMenuItem("Agregar Usuario");
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAgregarNuevoUsuario agregar = new FrmAgregarNuevoUsuario();
				desktopPane.add(agregar);
				agregar.setVisible(true);
			}
		});
		mntmAgregarUsuario.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/com/birosoft/liquid/icons/panther-maximize.png")));
		mnSistema.add(mntmAgregarUsuario);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
		
		mnMantenimientos = new JMenu("Mantenimientos");
		menuBar.add(mnMantenimientos);
		
		mntmCategorias = new JMenuItem("Categorias de Productos");
		mnMantenimientos.add(mntmCategorias);
		
		mntmInventariosDeProductos = new JMenuItem("Inventarios de Productos");
		mnMantenimientos.add(mntmInventariosDeProductos);
		
		JMenuItem mntmUnidades = new JMenuItem("Unidades");
		mnMantenimientos.add(mntmUnidades);
		
		JMenuItem mntmComprobantes = new JMenuItem("Comprobantes");
		mntmComprobantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmComprobante frm = new FrmComprobante();
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmComprobantes);
		
		
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mnBusqueda = new JMenu("Busqueda");
		menuBar.add(mnBusqueda);
		
		JMenuItem mntmDetalleDeFacturas = new JMenuItem("Detalle de Facturas");
		mntmDetalleDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFactura frm = new frmFactura();
				desktopPane.add(frm);
				frm.deshabilitarBotones();
				frm.setVisible(true);
				
				
			}
		});
		mnBusqueda.add(mntmDetalleDeFacturas);
		
		JMenuItem mntmFacturasEmitidas = new JMenuItem("Facturas Emitidas");
		mntmFacturasEmitidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmFacturasEmitidas frm = new FrmFacturasEmitidas();
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		mnBusqueda.add(mntmFacturasEmitidas);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//PARA ASIGNAR LA IMAGEN EN JDESKTOPPANE EN EL FORMULARIO PRINCIPAL.
		desktopPane.setBorder(new ImagenJDesktopPane());
		
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		btnFactura = new JButton("Factura");
		btnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFactura frm = new frmFactura();
				desktopPane.add(frm);
				frm.getTxtNumFactura().setEnabled(false);
				frm.setVisible(true);
			}
		});
		btnFactura.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415496836_698568-icon-56-document-text-48.png")));
		btnFactura.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnFactura);
		
		btnInventarios = new JButton("Inventarios");
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
		
		btnDevoluciones = new JButton("Devoluciones");
		btnDevoluciones.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415499004_Rotation.png")));
		btnDevoluciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnDevoluciones);
		
		btnReportes = new JButton("Reportes");
		btnReportes.setIcon(new ImageIcon(FrmMainPrincipal.class.getResource("/Recursos/1415499313_Computer_Analysis-48.png")));
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar.add(btnReportes);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setForeground(new Color(51, 153, 204));
		toolBar_1.setFloatable(false);
		frame.getContentPane().add(toolBar_1, BorderLayout.SOUTH);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		toolBar_1.add(lblUsuario);
		
		usuario = null;
		lbtipousuario = new JLabel(usuario);
		lbtipousuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		toolBar_1.add(lbtipousuario);
		
		
	}

	public void mostrar() {
		// TODO Auto-generated method stub
		frame.setVisible(true); // llamada al menu et
		
	}
	
	public void permisos (int permiso){
		
		if (permiso==2){
			btnInventarios.setEnabled(false);
			btnDevoluciones.setEnabled(false);
			btnInventarios.setEnabled(false);
			mnMantenimientos.setEnabled(false);
			mntmAgregarUsuario.setEnabled(false);
			btnReportes.setEnabled(false);
			
			                                                         /// permisos para desactivar menu y controles
		}
		if (permiso==3){
			
			btnDevoluciones.setEnabled(false);
			mnMantenimientos.setEnabled(false);
			mntmAgregarUsuario.setEnabled(false);
			btnFactura.setEnabled(false);
			btnReportes.setEnabled(false);
			mnBusqueda.setEnabled(false);
		}
		
	}
	public void ocultarFrame() {
		// TODO Auto-generated method stub
		frame.setVisible(false); // llamada al menu et
		
	}

	public void tipoUsuario(String tipoUsuario) {      // obtener el nombre del usuario para mostrar en la toolbar
		// TODO Auto-generated method stub
		
	    usuario = tipoUsuario;
	    lbtipousuario.setText(usuario);
	}
}

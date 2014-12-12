package Formularios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JTable;

import Clases.ModeloTabla;
import Clases.Utilidades;
import Modelos.Factura;
import Modelos.Producto;
import Modelos.TipoProducto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmFacturasEmitidas extends JInternalFrame {
	private JTextField textField;
	private JTable table;
	private ModeloTabla modeloTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFacturasEmitidas frame = new FrmFacturasEmitidas();
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
	public FrmFacturasEmitidas() {
		getContentPane().setBackground(new Color(51, 153, 204));
		getContentPane().setLayout(null);
		
		JLabel lblFacturasEmitidas = new JLabel("Facturas Emitidas");
		lblFacturasEmitidas.setForeground(new Color(204, 0, 0));
		lblFacturasEmitidas.setFont(new Font("Arial", Font.BOLD, 35));
		lblFacturasEmitidas.setBounds(199, 24, 341, 49);
		getContentPane().add(lblFacturasEmitidas);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 109, 46, 14);
		getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(127, 115, 321, 20);
		getContentPane().add(textField);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(FrmFacturasEmitidas.class.getResource("/Recursos/search_icon.png")));
		btnBuscar.setBounds(458, 84, 59, 51);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 154, 575, 236);
		getContentPane().add(scrollPane);
		
		JLabel lbltotal = new JLabel("0");
		
		try {
			modeloTabla = new ModeloTabla("idFactura, fecha, idUsuario,totalFactura, comprobante", "tblfactura2", "1");
			modeloTabla.realizarBusqueda();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table = new JTable(modeloTabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//ENVENTO CUANDO SE PRESIONE UNA LINEA EN LA TABLA Y LLAME A LA VENTANA MODIFICAR.
				// CUANDO SE PRESIONE UNA CELDA EN LA TABLA SE ABRIRA LA VENTANA MODIFICAR.
				
				int idFactura = Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 0).toString() );
				String Fecha =  modeloTabla.getValueAt(table.getSelectedRow(), 1).toString();
				int idUsuario =   Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 2).toString() );
				float total =  Float.parseFloat(modeloTabla.getValueAt(table.getSelectedRow(), 3).toString());
				String comprobante = modeloTabla.getValueAt(table.getSelectedRow(), 4).toString();
				Factura facturaEmitida = new Factura(idFactura, Fecha, idUsuario, 0, total,comprobante);
				frmFactura frm = new frmFactura();
				frm.cargarDatosDesdeFacturaEmitida(facturaEmitida); //ANTES DE QUE SE ABRE LA VENTANA SE ACCINA EL METODO CARGADATOS
				getDesktopPane().add(frm);
				frm.setVisible(true);
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		Utilidades util = new Utilidades();
		util.sumarFilasTabla(table, lbltotal);
		
			
		
		
		
		JLabel label = new JLabel("Total Productos:");
		label.setForeground(new Color(204, 0, 0));
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(58, 401, 94, 14);
		getContentPane().add(label);
		
		
		lbltotal.setForeground(new Color(0, 102, 0));
		lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltotal.setBounds(159, 401, 46, 14);
		getContentPane().add(lbltotal);
		setClosable(true);
		setBounds(100, 100, 611, 508);

	}
}

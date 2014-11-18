package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import Clases.ModeloTabla;
import Modelos.Producto;
import Modelos.TipoProducto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmProducto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarProductos;
	private ModeloTabla modeloTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmProducto frame = new frmProducto();
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
	public frmProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 651, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtBuscarProductos = new JTextField();
		txtBuscarProductos.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//BOTON BUSCAR
				
					try {
					
					String condicion = "1";
						//AQUI SE LE PASA EL QUERY Y EL TEXTO DEL TEXTFIELD
						condicion = "tblproducto.Descripcion like '%" + txtBuscarProductos.getText() + "%' and tbltipoproducto.ID = tblproducto.idtipoproducto";
					
					
					modeloTabla.establecerCondicion( condicion );
					modeloTabla.realizarBusqueda();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAgregarProducto frm = new frmAgregarProducto();
				
				frm.setVisible(true);
			}
		});
		
		JButton btnTipoProducto = new JButton("Tipo Producto");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBuscarProductos, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscar)
							.addGap(10)
							.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTipoProducto, GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE)))
					.addGap(6))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(269)
					.addComponent(lblProductos)
					.addContainerGap(275, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblProductos)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNombre)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtBuscarProductos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNuevo)
							.addComponent(btnBuscar)
							.addComponent(btnTipoProducto)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		try {
			modeloTabla = new ModeloTabla("tblproducto.Codigo, tblproducto.Descripcion, tblproducto.Precio, tbltipoproducto.Descripcion as 'Tipo Producto'", "tblproducto, "
					+ "tbltipoproducto", "tbltipoproducto.ID = tblproducto.idtipoproducto");
			
			modeloTabla.realizarBusqueda();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable(modeloTabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//ENVENTO CUANDO SE PRESIONE UNA LINEA EN LA TABLA Y LLAME A LA VENTANA MODIFICAR.
				// CUANDO SE PRESIONE UNA CELDA EN LA TABLA SE ABRIRA LA VENTANA MODIFICAR.
				
				int Codigo = Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 0).toString() );
				String Descripcion =  modeloTabla.getValueAt(table.getSelectedRow(), 1).toString();
				float Precio =  Float.parseFloat(modeloTabla.getValueAt(table.getSelectedRow(), 2).toString());
				String TipoProducto =  modeloTabla.getValueAt(table.getSelectedRow(), 3).toString();
				
				
				TipoProducto ObjetoTipoProducto = new TipoProducto(TipoProducto);
				Producto productoObtenido = new Producto(Codigo, Descripcion, Precio,ObjetoTipoProducto);
				
				frmModificarProducto frm = new frmModificarProducto();
				//frm.cargarDatos(nuevoTipoProducto);  //ANTES DE QUE SE ABRE LA VENTANA SE ACCINA EL METODO CARGADATOS
				frm.setVisible(true);

			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}

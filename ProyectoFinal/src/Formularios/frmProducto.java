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
import java.awt.Color;
import java.awt.Toolkit;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmProducto.class.getResource("/Recursos/Icon GrenSoft3.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblProductos = new JLabel("Inventario de Productos");
		lblProductos.setForeground(new Color(153, 0, 0));
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtBuscarProductos = new JTextField();
		txtBuscarProductos.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(0, 102, 0));
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
		btnNuevo.setForeground(new Color(153, 0, 0));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAgregarProducto frm = new frmAgregarProducto();
				
				frm.setVisible(true);
			}
		});
		
		JButton btnTipoProducto = new JButton("Crear Tipo Producto");
		btnTipoProducto.setForeground(new Color(153, 0, 0));
		btnTipoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmTipoProducto frm = new frmTipoProducto();
				frm.setVisible(true);
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(222, Short.MAX_VALUE)
					.addComponent(lblProductos)
					.addGap(207))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBuscarProductos, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTipoProducto, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductos)
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTipoProducto)
								.addComponent(btnBuscar)
								.addComponent(btnNuevo))
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(txtBuscarProductos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		try {
			modeloTabla = new ModeloTabla("tblproducto.Codigo, tblproducto.Descripcion, tblproducto.Precio, tbltipoproducto.Descripcion as 'Tipo Producto', tblproducto.Existencia", "tblproducto, "
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

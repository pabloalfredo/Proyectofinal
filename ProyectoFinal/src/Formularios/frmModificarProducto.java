package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import Modelos.Producto;
import Modelos.TipoProducto;

public class frmModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoModificarProducto;
	private JTextField txtDescripcionModificarProducto;
	private JTextField txtPrecioModificarProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmModificarProducto frame = new frmModificarProducto();
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
	public frmModificarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtCodigoModificarProducto = new JTextField();
		txtCodigoModificarProducto.setColumns(10);
		
		JLabel label = new JLabel("Codigo");
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JLabel label_1 = new JLabel("Descripcion");
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		txtDescripcionModificarProducto = new JTextField();
		txtDescripcionModificarProducto.setColumns(10);
		
		txtPrecioModificarProducto = new JTextField();
		txtPrecioModificarProducto.setColumns(10);
		
		JLabel label_2 = new JLabel("Precio");
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JComboBox cmbModificarProducto = new JComboBox();
		cmbModificarProducto.setToolTipText("");
		
		
		JLabel label_3 = new JLabel("Tipo");
		label_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton button = new JButton("Guardar");
		button.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JLabel lblModificarProductos = new JLabel("Modificar Productos");
		lblModificarProductos.setFont(new Font("SansSerif", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(txtPrecioModificarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(cmbModificarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(131)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(txtCodigoModificarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(txtDescripcionModificarProducto, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(140, Short.MAX_VALUE)
					.addComponent(lblModificarProductos, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(126))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModificarProductos, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtCodigoModificarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDescripcionModificarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPrecioModificarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(cmbModificarProducto, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void cargarDatos(Producto ProductoModificar)// CARGA LOS DATOS AL FORMULARIO MODIFICAR TIPO PRODUCTO DESDE EL FORMULARIO TIPO PRODUCTO
	{		
		//String ID = TipoProductoActual.getIdTipoProducto();
	
		String IDObtenido = Integer.toString(ProductoModificar.getCodigoProducto());
		String DescripcionObtenido = ProductoModificar.getDescripcionProducto();
		String PrecioObtenido = Float.toString(ProductoModificar.getPrecioProducto());
		String cmbObtenido = ProductoModificar.getTipoProducto().getDescripcion();
		
		txtCodigoModificarProducto.setText(IDObtenido);
		txtDescripcionModificarProducto.setText(DescripcionObtenido);
		txtPrecioModificarProducto.setText(PrecioObtenido);
		
		
		
		
		
		
		
	}
}

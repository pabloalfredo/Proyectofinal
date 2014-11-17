package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;





//import personas.Empleado;
//import presentacion.FormEmpleado;
import Clases.*;
import Clases.*;
import Modelos.TipoProducto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmTipoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcionTipoProducto;
	private JTable table;
	private ModeloTabla modeloTabla;
	//private JFrame ventanaPadre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTipoProducto frame = new frmTipoProducto();// se le agrego new JFrame()
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	//public frmTipoProducto() {
	public frmTipoProducto() {//se le indica cual es la ventana padre
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAgregarTipoDe = new JLabel(" Tipo de Producto");
		lblAgregarTipoDe.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDescripcion = new JLabel("Nombre");
		
		txtDescripcionTipoProducto = new JTextField();
		txtDescripcionTipoProducto.setColumns(10);
		
		JButton btnBuscarTipoProducto = new JButton("Buscar");
		btnBuscarTipoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//EN LA TABLA HAY UN METODO PARA REALIZAR LA BUSQUEDAD
				
					try {
					
					String condicion = "1";
						//AQUI SE LE PASA EL QUERY Y EL TEXTO DEL TEXTFIELD
						condicion = "Descripcion like '%" + txtDescripcionTipoProducto.getText() + "%'";
					
					
					modeloTabla.establecerCondicion( condicion );
					modeloTabla.realizarBusqueda();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNuevoTipoProducto = new JButton("Nuevo");
		btnNuevoTipoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmAgregarTipoProducto frm = new frmAgregarTipoProducto();
				frm.setVisible(true);
				
				
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {// CUANDO SE PRESIONE UNA CELDA EN LA TABLA SE ABRIRA LA VENTANA MODIFICAR.
			
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(106)
									.addComponent(lblAgregarTipoDe))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDescripcionTipoProducto, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(btnBuscarTipoProducto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNuevoTipoProducto))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAgregarTipoDe)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(txtDescripcionTipoProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarTipoProducto)
						.addComponent(btnNuevoTipoProducto))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		
//LE PASAMOS EL QUERY POR EL CONTSTUCTOR Y EL METODO REALIZARBUSQUEDA EJECUTA EL QUERY
		try {
			modeloTabla = new ModeloTabla("*", "tbltipoproducto", "1");
			modeloTabla.realizarBusqueda();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//EL RESULTADO DEL QUERY SE LE PASA A LA TABLA
		table = new JTable(modeloTabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// CUANDO SE PRESIONE UNA CELDA EN LA TABLA SE ABRIRA LA VENTANA MODIFICAR.
					int id = Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 0).toString() );
					String Descripcion =  modeloTabla.getValueAt(table.getSelectedRow(), 1).toString();
					
					
					TipoProducto nuevoTipoProducto = new TipoProducto(id,Descripcion);
					frmModificarTipoProducto frm = new frmModificarTipoProducto();
					frm.cargarDatos(nuevoTipoProducto);  //ANTES DE QUE SE ABRE LA VENTANA SE ACCINA EL METODO CARGADATOS
					frm.setVisible(true);
					
					
					
				
				
			}
		});
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}

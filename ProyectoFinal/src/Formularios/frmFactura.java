package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;














import java.util.Locale;

import javax.swing.ImageIcon;


























import Clases.BaseDeDatos;
import Clases.CargarComboBox;
import Clases.FondoFormulario;
import Clases.ModeloTabla;
import Clases.Utilidades;
import Clases.Validacion;
import Modelos.Comprobante;
import Modelos.DetalleFactura;
import Modelos.Factura;
import Modelos.Producto;
import Modelos.TipoProducto;

























//import com.mysql.jdbc.ResultSet;
import java.sql.ResultSet;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.SystemColor;

import javax.swing.JComboBox;

public class frmFactura extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtSubTotal;
	private JTable table;
	private int ID = 0;
	private JLabel lblTotalFilas = new JLabel("0");
	private JTextField txtNumFactura;
	private JButton btnAgregarFila;
	private JButton btnEliminarFila;
	private JButton btnBuscarProductos;
	private JButton btnFacturar;
	private JButton btnLimpiar;
	private ModeloTabla modeloTabla;
	private String validarFilaBlanco;
	private int total = 0;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JLabel lblFactura;
	Utilidades util = new Utilidades();
	boolean validar;
	private Producto validarExistencia;
	private JComboBox cmbComprobante;
	private CargarComboBox cargarComboBoxComprobante;
	private JTextField txtITBIS;
	private JTextField txtTotal;
	private JTextField txtNCF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFactura frame = new frmFactura();
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
	public frmFactura() {
		setMaximizable(true);
	
		setClosable(true);
		
		setResizable(false);
		//setDesktopIcon(Toolkit.getDefaultToolkit().getImage(frmFactura.class.getResource("/Recursos/Icon GrenSoft3.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 783, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBorder(new FondoFormulario("/Recursos/fondo.jpg"));
		
		lblFactura = new JLabel("Factura");
		lblFactura.setForeground(new Color(204, 0, 0));
		lblFactura.setFont(new Font("Arial", Font.BOLD, 35));
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnBuscarProductos = new JButton("Buscar Productos");
		btnBuscarProductos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//BUSQUEDA DE PRODUCTOS PARA INGRESARLO EN LA TABLA DETALLE DE FACTURA
				try {
					FormBuscar busqueda = new FormBuscar(frmFactura.this);
					busqueda.getModeloTabla().establecerAtributos("Codigo, Descripcion, Precio, Existencia");
					busqueda.getModeloTabla().establecerTabla("tblproducto");
					busqueda.getModeloTabla().establecerCondicion("1");
					
					busqueda.getModeloTabla().realizarBusqueda();
					busqueda.getModeloTabla().fireTableStructureChanged();
					getDesktopPane().add(busqueda);
					busqueda.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscarProductos.setForeground(new Color(153, 0, 0));
		
		btnEliminarFila = new JButton("Eliminar Fila");
		btnEliminarFila.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminarFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel(); 
					//METODO PARA ELIMINAR LA FILA
				int a = table.getSelectedRow(); 

				if (a<0){

				        JOptionPane.showMessageDialog(null,
				        "Debe seleccionar una fila de la tabla" );  

				}else {


				     int confirmar=JOptionPane.showConfirmDialog(null,  
				     "Esta seguro que desea Eliminar el registro? ");


				            if(JOptionPane.OK_OPTION==confirmar) {
				                     
				                    model.removeRow(a);
				                    ActualizarTotal();	
				                    sumarFilas();
				             
				             }
				}
			}
		});
		btnEliminarFila.setForeground(new Color(153, 0, 0));
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubTotal.setText("0.00");
		txtSubTotal.setForeground(Color.GREEN);
		txtSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSubTotal.setColumns(10);
		
		btnAgregarFila = new JButton("Agregar Fila");
		btnAgregarFila.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgregarFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarFila();
			}
		});
		btnAgregarFila.setForeground(new Color(153, 0, 0));
		
		/*try {
			modeloTabla = new ModeloTabla("tbldetallefactura2.codigoProducto, tblproducto.Descripcion, tbldetallefactura2.precio, tbldetallefactura2.precio, tbldetallefactura2.subTotal", 
					"tbldetallefactura2, tblproducto", 
					"tbldetallefactura2.idFactura ='"+7 +"' and tbldetallefactura2.codigoProducto = tblproducto.Codigo;");
			modeloTabla.realizarBusqueda();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		
		table = new JTable();
		table.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent arg0) {
			}
		});
		table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
				
				
			}
		});
		
		
		table.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent evt)  {
				
					int key = evt.getKeyCode();
				DefaultTableModel tabla = (DefaultTableModel) table.getModel();
			    	String Descripcion = null;
					double precio = 0;
					int cantidadTabla=0;
					 
					 
				if (key == KeyEvent.VK_TAB) {// CUANDO SE PRESIONE TAB SE IMPLEMENTARA ESTA CONDICION.
					    	/////////////////////////////////////////////////////////////////BUSQUEDA BASE DE DATOS
							//////////////// ESTO IRA EN LA CLASE FACTURA
							
							table.editCellAt(table.getSelectedRow(), 0);//LE INDICA A LA TABLA QUE LA CELDA A SIDO EDITADA.
							Object valor=table.getValueAt(table.getSelectedRow(), 0);
							  ID= (valor==null)?0:Integer.parseInt(valor.toString());//OPERADOR TERNARIO 
							  
							if (ID >0){
							    	BaseDeDatos conn = new BaseDeDatos();
									ResultSet rs;
									try {
										rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select Descripcion, Precio from tblproducto where Codigo = '"+ID +"'");
											while (rs.next()){
											Descripcion=rs.getString(1);
											precio=rs.getDouble(2);
											
											}
									} catch (ClassNotFoundException e) {
										JOptionPane.showMessageDialog(null, "El Codigo no existe en el registro");
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
					    	
					    	////////////////////////////////////////////////////////////////////////////////////////////////////
								
						        tabla.setValueAt(Descripcion, table.getSelectedRow(), 1);
						        tabla.setValueAt(precio, table.getSelectedRow(), 2);
						        
						         cantidadTabla = Integer.parseInt(tabla.getValueAt(table.getSelectedRow(), 3).toString());
						         validarExistencia = new Producto(ID, cantidadTabla);
						        try {
									validarExistencia.validarExistenciaInventario();
									
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						      
						          ValidarSiCodigoExiste();
							      ActualizarTabla();
							      ActualizarTotal();
							      sumarFilas();
						         
					}
				    if (key == KeyEvent.VK_ENTER) {// CUANDO SE PRESIONE ENTER SE IMPLEMENTARA ESTA CONDICION.
				    		ActualizarTabla();
					      ActualizarTotal();			    	
					      ValidarNoAgregarMasFilas ();
					      
							try {
								if (validarFilaBlanco!=null && validarExistencia.validarExistenciaInventario()==true){

								tabla.addRow(new Object[]{null, null, null, 1, null});
								table.changeSelection(table.getSelectedRow(), 0, false, false);
								table.requestFocus();
}
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				  
			       }
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, new Integer(1), null},
			},
			new String[] {
				"Codigo", "Descripcion", "Precio", "Cantidad", "Subtotal"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				
				return columnEditables[column];
			}
		
		});
		scrollPane.setViewportView(table);
////////////////////OBTENER LA FECHA ACTUAL Y ASIGNARLA AL FORMULARIO FACTURA //////////////////
			Date fechaActual = new Date( );
			Date horaActual = new Date( );
			SimpleDateFormat fecha = new SimpleDateFormat ("dd.MM.yyyy");
			SimpleDateFormat hora = new SimpleDateFormat ("hh:mm:ss");
			lblHora = new JLabel(hora.format(horaActual));
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		lblFecha = new JLabel(fecha.format(fechaActual));
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setForeground(new Color(153, 0, 0));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btnFacturar = new JButton("Facturar");
		btnFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//REALIZAR LA FACTURA.
					
			/////////////////////////////////////////////////////////////////DETALLE FACTURA
					String fechaEnviada = lblFecha + " " + lblHora;
					double subtotalFacturaEnviada = Double.parseDouble(txtSubTotal.getText());
					double itbisEnviada = Double.parseDouble(txtITBIS.getText());
					double totalFacturaEnviada = Double.parseDouble(txtTotal.getText());
					int numeroSecuencia = 0;
					
					//OBTIENE EL ID DEL COMBOBOX
					int  tipoComprobanteComboBox = cargarComboBoxComprobante.comprobante.get(cmbComprobante.getSelectedIndex()).getIdComprobante();
					String tipoComprobante = String.format("%02d", tipoComprobanteComboBox);
					
					try {
						Comprobante pruebaComprobante = new Comprobante(tipoComprobanteComboBox,null);//AUMENTA LA SECUENCIA DEL COMPROBANTE
						 numeroSecuencia = pruebaComprobante.sumarSecuenciaComprobante();
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					String cadena = null; // AGREGA LOS OTROS 0 QUE FALTAN AL NUMERO DE LA SECUENCIA
					cadena = String.format("%08d", numeroSecuencia);
					
					String comprobante ="A01001001" + tipoComprobante + cadena;
				
					
					Factura nuevaFactura = new Factura(fechaEnviada,0,0,subtotalFacturaEnviada, itbisEnviada, totalFacturaEnviada, comprobante);
					DefaultTableModel tabla = (DefaultTableModel) table.getModel();
			        int idfactura=0;// PARA ASIGNARLE EL NUMERO DE LA FACTURA A DETALLE FACTURA
			        //TODO: VALIDAR QUE NO PERMITA REALIZAR UNA FACTURA SIN QUE TENGA DETALLE
					try {
						idfactura = nuevaFactura.agregarFactura();//AQUI LE ASIGNA EL ID DE LA FACTURA QUE VAN A UTILIZAR CADA PRODUCTO
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			        int codigoProducto = 0;
			        int cantidadProducto =1;
			        double precio =0;
			        double subTotal=0;
			        double descuento=0.00;
			        //recorrer todas las filas de la segunda columna y va INSERTANDO A LA CLASE DETALLEFACTURA Y LUEGO LLAMA EL METODO AGREGARDETALLE FACTURA
			      
			        for( int i=0 ; i<tabla.getRowCount(); i++)
			        {
			            
			                //capturamos valor de celda
			             try {
			            	 
			            	 codigoProducto = Integer.parseInt( tabla.getValueAt(i, 0).toString() );
			            	 precio = Double.parseDouble(tabla.getValueAt(i, 2).toString() );
			            	 cantidadProducto = Integer.parseInt(tabla.getValueAt(i, 3).toString());
			            	 subTotal = Double.parseDouble(tabla.getValueAt(i, 4).toString() );
			            	 
			            	 Factura factura = new Factura(idfactura);
			            	 DetalleFactura detalle = new DetalleFactura(factura,codigoProducto,cantidadProducto,precio,subTotal,descuento);
			            	 Producto cantidadRestadaInventario = new Producto(codigoProducto, cantidadProducto);
			            	 try {
								detalle.agregarDetalleFactura();
								cantidadRestadaInventario.restarExistenciaInventario();
								
							} catch (ClassNotFoundException | SQLException e) {
								
								e.printStackTrace();
							}
			            	 
							
						} catch (NumberFormatException e) {
							
							e.printStackTrace();
						}
			            
			        }
			 limpiarTabla();		// LIMPIA LA TABLA
			}

			
		});
		btnFacturar.setForeground(new Color(0, 0, 204));
		btnFacturar.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla();
				 
			}
		});
		btnLimpiar.setForeground(new Color(0, 153, 0));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(204, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(frmFactura.class.getResource("/Recursos/Icon GrenSoft2.png")));
		
		JLabel label1 = new JLabel("Hora:");
		label1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTotalProductos = new JLabel("Total Productos:");
		lblTotalProductos.setForeground(new Color(204, 0, 0));
		lblTotalProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//JLabel lblTotalFilas = new JLabel("0");
		lblTotalFilas.setForeground(new Color(0, 102, 0));
		lblTotalFilas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNumeroFactura = new JLabel("Numero Factura");
		lblNumeroFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroFactura.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtNumFactura = new JTextField();
		txtNumFactura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				//DefaultTableModel tabla = (DefaultTableModel) table.getModel();
//DETALLE FACTURA
//PERMITE BUSCAR UNA FACTURA EN EL REGISTRO QUE HA SIDO FACTURA ANTERIORMENTE
				Validacion validarSoloNumero = new Validacion();
				validarSoloNumero.validarTxtSoloNumero(txtNumFactura); // VALIDA QUE SOLO SE INGRESEN NUMERO EN EL TEXTBOX
				
				if(key == KeyEvent.VK_ENTER){
				
							//String numFactura = txtNumFactura.getText();
					
							
							int numFactura = Integer.parseInt(txtNumFactura.getText());
							
							try {
								modeloTabla = new ModeloTabla("tbldetallefactura2.codigoProducto, tblproducto.Descripcion, tbldetallefactura2.precio, tbldetallefactura2.precio, tbldetallefactura2.subTotal", 
										"tbldetallefactura2, tblproducto", 
										"tbldetallefactura2.idFactura ='"+numFactura +"' and tbldetallefactura2.codigoProducto = tblproducto.Codigo;");
								modeloTabla.realizarBusqueda();
								table.setModel(modeloTabla);
								ActualizarTotal();
								util.sumarFilasTabla(table, lblTotalFilas);
								
								ValidarSiFacturaExiste();
								
								//Factura fechaObtenida = new Factura(Integer.parseInt(numFactura));
								Factura fechaObtenida = new Factura(numFactura);
								String fechaHoraFormulario =fechaObtenida.fechaFacturaBuscada();
								if(fechaHoraFormulario !=null){
									 String[] fechaDividida = fechaHoraFormulario.split(" ");
								    lblFecha.setText(fechaDividida[0]);
									 lblHora.setText(fechaDividida[1]);
								}
							} catch (ClassNotFoundException e) {
								
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						
				}
				
			}
		});
		txtNumFactura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumFactura.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblTipo = new JLabel("Tipo de Comprobante");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		cmbComprobante = new JComboBox();
		cargarComboBoxComprobante = new CargarComboBox();
		cargarComboBoxComprobante.cargarComboBoxComrobante(cmbComprobante);
		
		txtITBIS = new JTextField();
		txtITBIS.setText("0.00");
		txtITBIS.setHorizontalAlignment(SwingConstants.RIGHT);
		txtITBIS.setForeground(Color.GREEN);
		txtITBIS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtITBIS.setEditable(false);
		txtITBIS.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setText("0.00");
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setForeground(Color.GREEN);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		
		JLabel lblitbis = new JLabel("ITBIS");
		lblitbis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblitbis.setForeground(new Color(153, 0, 0));
		lblitbis.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setForeground(new Color(153, 0, 0));
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNcf = new JLabel("NCF");
		lblNcf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNcf.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtNCF = new JTextField();
		txtNCF.setEnabled(false);
		txtNCF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNCF.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(68)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFactura, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(143)
									.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(cmbComprobante, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(142)
									.addComponent(lblNumeroFactura, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(txtNumFactura, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(247)
									.addComponent(lblNcf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(txtNCF, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(422)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAgregarFila, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminarFila, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscarProductos, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(15)
								.addComponent(lblTotalProductos)
								.addGap(7)
								.addComponent(lblTotalFilas, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(349)
								.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addGap(2)
								.addComponent(txtSubTotal, 0, 0, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(544)
								.addComponent(lblitbis, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addGap(2)
								.addComponent(txtITBIS, 0, 0, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(50)
								.addComponent(btnFacturar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addGap(69)
								.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addGap(68)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addGap(21)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addGap(2)
								.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFactura, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addComponent(cmbComprobante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNumeroFactura, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNumFactura, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNcf, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNCF, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(btnAgregarFila, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnEliminarFila, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnBuscarProductos, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblTotalProductos, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblTotalFilas, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtSubTotal, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(lblitbis, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(txtITBIS, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFacturar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		table.changeSelection(0, 0, false, false);   //ESTO ES PARA CUANDO INICIE EL FORMULARIO EL TAB SE FOCALICE EN LA PRIMERA CELDA.
		table.requestFocus();
		
		
	}
	
	public JTextField getTxtNumFactura() {
		return txtNumFactura;
	}

	public void setTxtNumFactura(JTextField txtNumFactura) {
		this.txtNumFactura = txtNumFactura;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public JButton getBtnFacturar() {
		return btnFacturar;
	}

	public void setBtnFacturar(JButton btnFacturar) {
		this.btnFacturar = btnFacturar;
	}

	public JButton getBtnBuscarProductos() {
		return btnBuscarProductos;
	}

	public void setBtnBuscarProductos(JButton btnBuscarProductos) {
		this.btnBuscarProductos = btnBuscarProductos;
	}

	public JButton getBtnEliminarFila() {
		return btnEliminarFila;
	}

	public void setBtnEliminarFila(JButton btnEliminarFila) {
		this.btnEliminarFila = btnEliminarFila;
	}

	public JButton getBtnAgregarFila() {
		return btnAgregarFila;
	}

	public void setBtnAgregarFila(JButton btnAgregarFila) {
		this.btnAgregarFila = btnAgregarFila;
	}

	public void ActualizarTabla(){
		//ESTE METODO SE UTILIZA PARA REALIZAR EL CALCULO DE LAS CANTIDADES Y EL PRECIO Y OBTENER EL SUBTOTAL EN UNA FILA.
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		
		String validarCodigoExistente = null;
		validarCodigoExistente = (String) tabla.getValueAt(table.getSelectedRow(), 1);
		
			Object cantidadObject=tabla.getValueAt(table.getSelectedRow(), 2);
			Object precioObject=tabla.getValueAt(table.getSelectedRow(), 3);
			
        	float cantidad= (cantidadObject==null)?0:Float.parseFloat(cantidadObject.toString());
        	float precio= (precioObject==null)?0:Float.parseFloat(precioObject.toString());	
		
		float total = cantidad * precio;
		
		tabla.setValueAt(total, table.getSelectedRow(), 4);		
	}
	
	private void ActualizarTotal()
    {//	METODO PARA ACTUALIZAR LA SUMA DE LOS SUBTOTALES, ESTE METODO RECORRE LA TABLA Y ASIGNA AL TXTTOTAL, SE ASIGNARA DONDE QUIERA QUE SE HAGAN CAMBIOS A LA TABLA.
		//DefaultTableModel tabla = (DefaultTableModel) table.getModel();
        double total = 0;
        double numero =0;
       double ITBIS = 0;
        double totalFactura = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
   
	        for( int i=0 ; i<table.getRowCount(); i++)
	        {
	            
	                //capturamos valor de celda
	             try {
	            	
	            	Object numeroObject=table.getValueAt(i, 4);
	            	numero= (numeroObject==null)?0:Double.parseDouble(numeroObject.toString());
	            	 
	            	
					total += numero;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   
		}
	        ITBIS = total * 0.18;
		    totalFactura = total + ITBIS;
        //muestra en el componente
		    String subtotalConFormato = String.format(Locale.US, "%.2f", total);//LE DA FORMATO PARA QUE EN EL TEXTBOX APARENZCAN CON LOS DOS DIGITOS.
		    String ITBISConFormato = String.format(Locale.US, "%.2f", ITBIS);
		    String totalFacturaConFormato = String.format(Locale.US, "%.2f", totalFactura);
        this.txtSubTotal.setText( String.valueOf(subtotalConFormato) );
        this.txtITBIS.setText( String.valueOf(ITBISConFormato) );
        this.txtTotal.setText( String.valueOf(totalFacturaConFormato) );
    }

//CARGA LOS DATOS DESDE EL FORMULARIO BUSCAR Y LO AGREGA EN LA TABLA DETALLE
	public void cargarDatos(Producto NuevaFila) 
	{	DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		int codigoRecibido = NuevaFila.getCodigoProducto();
		String descripcionRecibida = NuevaFila.getDescripcionProducto();
		float precioRecibido = NuevaFila.getPrecioProducto();
		
		
		tabla.setValueAt(codigoRecibido, table.getSelectedRow(), 0);
        tabla.setValueAt(descripcionRecibida, table.getSelectedRow(), 1);
        tabla.setValueAt(precioRecibido, table.getSelectedRow(), 2);
		
		table.changeSelection(table.getSelectedRow(), 3, false, false);
		table.requestFocus();
		
	}
	public boolean noPermitirAgregarMasFilasEnBlanco(){
		String numero;
		for( int i=0 ; i<table.getRowCount(); i++)
        {
            
                //capturamos valor de celda
             try {
            	
            	Object numeroObject=table.getValueAt(i, 1);
            	//numero= (numeroObject==null)?0:Double.parseDouble(numeroObject.toString());
            	 numero = numeroObject.toString();
            	
            	 if (numero ==null){
            		 table.changeSelection(table.getSelectedRow(), 0, false, false);//VALIDA SI EL CODIGO EXISTE EN EL REGISTRO
         			table.requestFocus();
         			JOptionPane.showMessageDialog(null, "No puede tener mas de una fila en blanco (recorrido)");
         			return false;
            	 }
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
   
        }
		   return true;
	}
	private void agregarFila(){//METODO PARA AGREGAR UNA NUEVA FILA A LA TABLA
		ValidarNoAgregarMasFilas ();
			if (validarFilaBlanco!=null){
		//if (noPermitirAgregarMasFilasEnBlanco()==true){
			DefaultTableModel tabla= (DefaultTableModel) table.getModel();
			tabla.addRow(new Object[]{null, null, null, 1, null});
			table.changeSelection(table.getSelectedRow(), 0, false, false);
			table.requestFocus();
		}
	}
	
	private void sumarFilas(){//		METODO PARA SUMAR TODOS LOS PRODUCTOS.
		

			//DefaultTableModel tabla = (DefaultTableModel) table.getModel();
	        int total = 0;
	        
	        //recorrer todas las filas de la segunda columna y va sumando las cantidades
	   
		        for( int i=0 ; i<table.getRowCount(); i++)
		        {
		            
		           total++;  
		   
			}
		    if (total == 0)
		    {
		    	DefaultTableModel tabla = (DefaultTableModel) table.getModel();
				tabla.addRow(new Object[]{null, null, null, 1, null});
		    }
	     
	        //muestra en el componente
	        this.lblTotalFilas.setText( String.valueOf(total) );
	}

	public void deshabilitarBotones() {//DESABILITA LOS BOTONES CUANDO SE ESTA LLAMANDO DESDE DETALLE FACTURA
		getBtnAgregarFila().setEnabled(false);
		getBtnEliminarFila().setEnabled(false);
		getBtnFacturar().setEnabled(false);
		getTable().setEnabled(false);
		getBtnBuscarProductos().setEnabled(false);
		lblFecha.setText("");
		lblHora.setText("");
		lblFactura.setText("Detalle de Factura");
		cmbComprobante.setEnabled(false);
		
	}
	public void ValidarNoAgregarMasFilas (){// VALIDA QUE NO SE INGRESEN MAS FILAS SI YA EXISTE UNA FILA EN BLANCO
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		
		validarFilaBlanco = (String) tabla.getValueAt(table.getSelectedRow(), 1);
	
		if (validarFilaBlanco==null){
			table.changeSelection(table.getSelectedRow(), 0, false, false);//VALIDA SI EL CODIGO EXISTE EN EL REGISTRO
			table.requestFocus();
			JOptionPane.showMessageDialog(null, "No puede tener mas de una fila en blanco");
			
		}
		
	}
	
	public void ValidarSiCodigoExiste(){// VALIDA SI EL CODIGO EXISTE EN EL REGISTRO
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		
		String validarCodigoExistente = null;
		validarCodigoExistente = (String) tabla.getValueAt(table.getSelectedRow(), 1);
		if (validarCodigoExistente==null){
			table.changeSelection(table.getSelectedRow(), 0, false, false);//VALIDA SI EL CODIGO EXISTE EN EL REGISTRO
			table.requestFocus();
			JOptionPane.showMessageDialog(null, "Este codigo no existe en el registro");
			
		}
		
	}
	public void ValidarSiFacturaExiste(){// VALIDA SI LA FACTURA EXISTE EN EL REGISTRO
		double obtenerTotal = Double.parseDouble(txtTotal.getText());
		if (obtenerTotal == 0){
        	JOptionPane.showMessageDialog(null, "Esta Factura no existe en el Registro");
        }
	}
	public void limpiarTabla() {//METODO PARA LIMPIAR EL FORMULARIO COMPLETO
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, new Integer(1), null},
				},
				new String[] {
					"Codigo", "Descripcion", "Precio", "Cantidad", "Subtotal"
				}
				
			));
		
		txtSubTotal.setText("0.00");//LUEGO DE REALIZAR LA FACTURA SE ASIGNA A 0.00 EL TOTAL.
		txtTotal.setText("0.00");
		txtITBIS.setText("0.00");
	
		txtNumFactura.setText("");
		lblTotalFilas.setText("0");
		txtNCF.setText("");
		table.changeSelection(0, 0, false, false);
		table.requestFocus();
		
	}

	public void cargarDatosDesdeFacturaEmitida(Factura facturaEmitida) {//CARGA LOS DATOS DESDE EL FORMULARIO FACTURA EMITIDAS
		deshabilitarBotones();//LLAMA EL METODO DESABILITAR BOTONES
		txtNumFactura.setText(String.valueOf(facturaEmitida.getIdFactura())); //ASIGNA EL NUMERO DE LA FACTURA AL TEXTFIELD
		txtNumFactura.setEnabled(false);
		
		try {
			modeloTabla = new ModeloTabla("tbldetallefactura2.codigoProducto, tblproducto.Descripcion, tbldetallefactura2.precio, tbldetallefactura2.precio, tbldetallefactura2.subTotal", 
					"tbldetallefactura2, tblproducto", 
					"tbldetallefactura2.idFactura ='"+facturaEmitida.getIdFactura() +"' and tbldetallefactura2.codigoProducto = tblproducto.Codigo;");
			modeloTabla.realizarBusqueda();
			table.setModel(modeloTabla);
			ActualizarTotal();
			util.sumarFilasTabla(table, lblTotalFilas);
		
			//ValidarSiFacturaExiste();
			
			Factura fechaObtenida = new Factura(facturaEmitida.getIdFactura());
			String fechaHoraFormulario =fechaObtenida.fechaFacturaBuscada();
			
			 String[] fechaDividida = fechaHoraFormulario.split(" ");
		    lblFecha.setText(fechaDividida[0]);
			 lblHora.setText(fechaDividida[1]);
			 txtNCF.setText(facturaEmitida.getComprobante());
		   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

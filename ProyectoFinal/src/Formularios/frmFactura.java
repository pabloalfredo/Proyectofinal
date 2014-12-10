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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;








import javax.swing.ImageIcon;

















import Clases.BaseDeDatos;
import Clases.CargarComboBox;
import Clases.ModeloTabla;
import Clases.Utilidades;
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
	private JTextField txtTotal;
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
		
		lblFactura = new JLabel("Factura");
		lblFactura.setBounds(236, 22, 374, 30);
		lblFactura.setForeground(new Color(204, 0, 0));
		lblFactura.setFont(new Font("Arial", Font.BOLD, 35));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 155, 518, 151);
		
		btnBuscarProductos = new JButton("Buscar Productos");
		btnBuscarProductos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarProductos.setBounds(534, 226, 167, 30);
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
		btnEliminarFila.setBounds(534, 192, 167, 30);
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
		
		txtTotal = new JTextField();
		txtTotal.setBounds(427, 330, 89, 30);
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setText("0.00");
		txtTotal.setForeground(Color.GREEN);
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotal.setColumns(10);
		
		btnAgregarFila = new JButton("Agregar Fila");
		btnAgregarFila.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgregarFila.setBounds(534, 158, 167, 30);
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
		contentPane.setLayout(null);
		contentPane.add(lblFactura);
////////////////////OBTENER LA FECHA ACTUAL Y ASIGNARLA AL FORMULARIO FACTURA //////////////////
			Date fechaActual = new Date( );
			Date horaActual = new Date( );
			SimpleDateFormat fecha = new SimpleDateFormat ("dd.MM.yyyy");
			SimpleDateFormat hora = new SimpleDateFormat ("hh:mm:ss");
			lblHora = new JLabel(hora.format(horaActual));
			lblHora.setBounds(625, 106, 76, 14);
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblHora);
		
		
		lblFecha = new JLabel(fecha.format(fechaActual));
		lblFecha.setBounds(483, 106, 76, 14);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		contentPane.add(lblFecha);
		
		JLabel label = new JLabel("Total:");
		label.setBounds(368, 337, 61, 23);
		label.setForeground(new Color(153, 0, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		contentPane.add(label);
		contentPane.add(scrollPane);
		contentPane.add(btnEliminarFila);
		contentPane.add(btnAgregarFila);
		contentPane.add(txtTotal);
		contentPane.add(btnBuscarProductos);
		
		btnFacturar = new JButton("Facturar");
		btnFacturar.setBounds(55, 379, 112, 60);
		btnFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//REALIZAR LA FACTURA.
					
			/////////////////////////////////////////////////////////////////DETALLE FACTURA
					String fechaEnviada = lblFecha + " " + lblHora;
					double totalFacturaEnviada = Double.parseDouble(txtTotal.getText());
					Factura nuevaFactura = new Factura(fechaEnviada,0,0,totalFacturaEnviada);
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
		contentPane.add(btnFacturar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(236, 379, 112, 60);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla();
				 
			}
		});
		btnLimpiar.setForeground(new Color(0, 153, 0));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnLimpiar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(416, 379, 112, 60);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(204, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setBounds(427, 106, 46, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 22, 158, 105);
		lblNewLabel_1.setIcon(new ImageIcon(frmFactura.class.getResource("/Recursos/Icon GrenSoft2.png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel label1 = new JLabel("Hora:");
		label1.setBounds(569, 106, 46, 14);
		label1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(label1);
		
		JLabel lblTotalProductos = new JLabel("Total Productos:");
		lblTotalProductos.setBounds(20, 317, 94, 14);
		lblTotalProductos.setForeground(new Color(204, 0, 0));
		lblTotalProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblTotalProductos);
		lblTotalFilas.setBounds(121, 317, 46, 14);
		
		//JLabel lblTotalFilas = new JLabel("0");
		lblTotalFilas.setForeground(new Color(0, 102, 0));
		lblTotalFilas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTotalFilas);
		
		JLabel lblNumeroFactura = new JLabel("Numero Factura");
		lblNumeroFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroFactura.setBounds(378, 81, 156, 14);
		lblNumeroFactura.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNumeroFactura);
		
		txtNumFactura = new JTextField();
		txtNumFactura.setBounds(544, 78, 213, 20);
		txtNumFactura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				//DefaultTableModel tabla = (DefaultTableModel) table.getModel();
//DETALLE FACTURA
//PERMITE BUSCAR UNA FACTURA EN EL REGISTRO QUE HA SIDO FACTURA ANTERIORMENTE
				if(key == KeyEvent.VK_ENTER){
				
							String numFactura = txtNumFactura.getText();
							try {
								modeloTabla = new ModeloTabla("tbldetallefactura2.codigoProducto, tblproducto.Descripcion, tbldetallefactura2.precio, tbldetallefactura2.precio, tbldetallefactura2.subTotal", 
										"tbldetallefactura2, tblproducto", 
										"tbldetallefactura2.idFactura ='"+numFactura +"' and tbldetallefactura2.codigoProducto = tblproducto.Codigo;");
								modeloTabla.realizarBusqueda();
								table.setModel(modeloTabla);
								ActualizarTotal();
								util.sumarFilasTabla(table, lblTotalFilas);
								
								ValidarSiFacturaExiste();
								
								Factura fechaObtenida = new Factura(Integer.parseInt(numFactura));
								String fechaHoraFormulario =fechaObtenida.fechaFacturaBuscada();
								
								 String[] fechaDividida = fechaHoraFormulario.split(" ");
							    lblFecha.setText(fechaDividida[0]);
								 lblHora.setText(fechaDividida[1]);
							   
							} catch (ClassNotFoundException e) {
								
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						
				}
				
			}
		});
		txtNumFactura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(txtNumFactura);
		txtNumFactura.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(879, 437, -302, -136);
		contentPane.add(scrollPane_1);
		
		JLabel lblTipo = new JLabel("Tipo de Comprobante");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(379, 56, 158, 14);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTipo);
		
		JComboBox cmbComprobante = new JComboBox();
		cmbComprobante.setBounds(544, 55, 213, 20);
		contentPane.add(cmbComprobante);
		CargarComboBox cargarComboBoxComprobante = new CargarComboBox();
		cargarComboBoxComprobante.cargarComboBoxComrobante(cmbComprobante);
		
		
		
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
     
        //muestra en el componente
        this.txtTotal.setText( String.valueOf(total) );
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
	
	private void agregarFila(){//METODO PARA AGREGAR UNA NUEVA FILA A LA TABLA
		ValidarNoAgregarMasFilas ();
			if (validarFilaBlanco!=null){
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
		if (total==0){
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
		
		txtTotal.setText("0.00");//LUEGO DE REALIZAR LA FACTURA SE ASIGNA A 0.00 EL TOTAL.
		txtNumFactura.setText("");
		lblTotalFilas.setText("0");
		
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
		
			ValidarSiFacturaExiste();
			
			Factura fechaObtenida = new Factura(facturaEmitida.getIdFactura());
			String fechaHoraFormulario =fechaObtenida.fechaFacturaBuscada();
			
			 String[] fechaDividida = fechaHoraFormulario.split(" ");
		    lblFecha.setText(fechaDividida[0]);
			 lblHora.setText(fechaDividida[1]);
		   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

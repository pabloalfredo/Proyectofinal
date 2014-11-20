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
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import Clases.BaseDeDatos;

import com.mysql.jdbc.ResultSet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

public class frmFactura extends JFrame {

	private JPanel contentPane;
	private JTextField txtTotal;
	private JTable table;

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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmFactura.class.getResource("/Recursos/Icon GrenSoft3.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFactura = new JLabel("Factura");
		lblFactura.setBounds(259, 29, 112, 30);
		lblFactura.setForeground(new Color(204, 0, 0));
		lblFactura.setFont(new Font("Arial", Font.BOLD, 30));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 518, 151);
		
		JButton btnBuscarProductos = new JButton("Buscar Productos");
		btnBuscarProductos.setForeground(new Color(153, 0, 0));
		btnBuscarProductos.setBounds(534, 206, 139, 23);
		
		JButton button_1 = new JButton("Eliminar Fila");
		button_1.addActionListener(new ActionListener() {
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
				                    JOptionPane.showMessageDialog(null, 
				                    "Registro Eliminado" );
				             
				             }
				}
			}
		});
		button_1.setForeground(new Color(153, 0, 0));
		button_1.setBounds(534, 172, 139, 23);
		
		JLabel label = new JLabel("Total:");
		label.setForeground(new Color(153, 0, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(368, 317, 61, 23);
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setText("0.00");
		txtTotal.setForeground(Color.GREEN);
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotal.setBounds(427, 318, 89, 22);
		txtTotal.setColumns(10);
		
		JButton button_2 = new JButton("Agregar Fila");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tabla= (DefaultTableModel) table.getModel();
				tabla.addRow(new Object[]{null, null, null, 1, null});
			}
		});
		button_2.setForeground(new Color(153, 0, 0));
		button_2.setBounds(534, 138, 139, 23);
		
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
			public void keyPressed(KeyEvent evt) {
				
				int key = evt.getKeyCode();
				DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		    	String Descripcion = null;
				double precio = 0;
				
				
				if (key == KeyEvent.VK_TAB) {// CUANDO SE PRESIONE TAB SE IMPLEMENTARA ESTA CONDICION.
			    	
					
			    	/////////////////////////////////////////////////////////////////BUSQUEDA BASE DE DATOS
					//////////////// ESTO IRA EN LA CLASE FACTURA
					//tabla.fireTableStructureChanged();
					tabla.fireTableCellUpdated(table.getSelectedRow(), 0);
					System.out.println(table.getSelectedRow());
					// int ID= Integer.parseInt( tabla.getValueAt(table.getSelectedRow(), 0).toString());
					 int ID= Integer.parseInt( table.getValueAt(table.getSelectedRow(), 0).toString());
					 
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			    	
			    	////////////////////////////////////////////////////////////////////////////////////////////////////
						
				        tabla.setValueAt(Descripcion, table.getSelectedRow(), 1);
				        tabla.setValueAt(precio, table.getSelectedRow(), 2);
				      ActualizarTabla();
				      ActualizarTotal();
				      
			    }
			    if (key == KeyEvent.VK_ENTER) {// CUANDO SE PRESIONE ENTER SE IMPLEMENTARA ESTA CONDICION.
			        ActualizarTabla();
			    	ActualizarTotal();
			    // DefaultTableModel tabla= (DefaultTableModel) table.getModel();
				tabla.addRow(new Object[]{null, null, null, 1, null});
			 
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
		contentPane.add(scrollPane);
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(label);
		contentPane.add(txtTotal);
		contentPane.add(btnBuscarProductos);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setForeground(new Color(0, 0, 204));
		btnFacturar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFacturar.setBounds(55, 359, 112, 60);
		contentPane.add(btnFacturar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(new Color(0, 153, 0));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setBounds(236, 359, 112, 60);
		contentPane.add(btnLimpiar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(204, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(416, 359, 112, 60);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(452, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDdmmyy = new JLabel("dd/mm/yy");
		lblDdmmyy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDdmmyy.setBounds(508, 60, 76, 14);
		contentPane.add(lblDdmmyy);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(frmFactura.class.getResource("/Recursos/Icon GrenSoft2.png")));
		lblNewLabel_1.setBounds(10, 2, 158, 105);
		contentPane.add(lblNewLabel_1);
		//table.getValueAt(table.getSelectedRow(), 0).toString()
		//table.getcell(table.getModel().)
		
	}
	
	public void ActualizarTabla(){
		//ESTE METODO SE UTILIZA PARA REALIZAR EL CALCULO DE LAS CANTIDADES Y EL PRECIO Y OBTENER EL SUBTOTAL EN UNA FILA.
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		
		String validarFila = null;
		validarFila = (String) tabla.getValueAt(table.getSelectedRow(), 1);
		
		//JOptionPane.showMessageDialog(null,
		  //      validarFila ); 
		
		if (validarFila==null){
			JOptionPane.showMessageDialog(null, "Debe obtener los datos del producto primero, presione Tab");
		}
		else{
		float cantidad	 =  Float.parseFloat(tabla.getValueAt(table.getSelectedRow(), 2).toString());
		float precio	 =  Float.parseFloat(tabla.getValueAt(table.getSelectedRow(), 3).toString());
		float total = cantidad * precio;
		
		tabla.setValueAt(total, table.getSelectedRow(), 4);
		}
		
	}
	

	
	private void ActualizarTotal()
    {//	METODO PARA ACTUALIZAR LA SUMA DE LOS SUBTOTALES, ESTE METODO RECORRE LA TABLA Y ASIGNA AL TXTTOTAL, SE ASIGNARA DONDE QUIERA QUE SE HAGAN CAMBIOS A LA TABLA.
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
        double total = 0;
        double numero =0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
      
        for( int i=0 ; i<tabla.getRowCount(); i++)
        {
            
                //capturamos valor de celda
             try {
            	 //numero = Integer.valueOf( tabla.getValueAt(i, 4).toString() );
            	 numero = Double.parseDouble( tabla.getValueAt(i, 4).toString() );
				total += numero;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
     
        //muestra en el componente
        this.txtTotal.setText( String.valueOf(total) );
    }
}

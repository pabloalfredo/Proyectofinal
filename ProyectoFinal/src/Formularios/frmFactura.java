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
import javax.swing.ImageIcon;

public class frmFactura extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmFactura.class.getResource("/Recursos/Icon GrenSoft.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFactura = new JLabel("Factura");
		lblFactura.setBounds(259, 29, 93, 30);
		lblFactura.setForeground(new Color(204, 0, 0));
		lblFactura.setFont(new Font("Arial", Font.BOLD, 26));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 518, 151);
		
		JButton btnBuscarProductos = new JButton("Buscar Productos");
		btnBuscarProductos.setForeground(new Color(153, 0, 0));
		btnBuscarProductos.setBounds(534, 206, 139, 23);
		
		JButton button_1 = new JButton("Eliminar Fila");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel(); 

				int a = table.getSelectedRow(); 

				if (a<0){

				        JOptionPane.showMessageDialog(null,
				        "Debe seleccionar una fila de la tabla" );  

				}else {


				     int confirmar=JOptionPane.showConfirmDialog(null,  
				     "Esta seguro que desea Eliminar el registro? ");


				            if(JOptionPane.OK_OPTION==confirmar) {
				                     
				                    model.removeRow(a);

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
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0.00");
		textField.setForeground(Color.GREEN);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(427, 318, 86, 20);
		textField.setColumns(10);
		
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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_TAB) {
						DefaultTableModel tabla = (DefaultTableModel) table.getModel();
						String Descripcion = "Pablo";
						float precio = 250;
				        tabla.setValueAt(Descripcion, table.getSelectedRow(), 1);
				        tabla.setValueAt(precio, table.getSelectedRow(), 2);
				        ActualizarTabla();
				       
			    }
			    if (key == KeyEvent.VK_ENTER) {
			        ActualizarTabla();
			     DefaultTableModel tabla= (DefaultTableModel) table.getModel();
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
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		contentPane.add(lblFactura);
		contentPane.add(scrollPane);
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(label);
		contentPane.add(textField);
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
	}
	
	public void ActualizarTabla(){
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
	
	public void ActualizarTotal(){
	/*	
		
		DefaultTableModel tabla = (DefaultTableModel) table.getModel();
		
         table.getColumnModel().getColumn(0).setCellRenderer(null);
         getContentPane().add(new JScrollPane(table));
         
        double subTally = 0;
        double tally = runningTally;

        int rows = (int) (Math.round(Math.random() * 9) + 1);
        for (int row = 0; row < rows; row++) {

            double amount = (Math.random() * 99999) + 1;
          tabla.addRow(new Object[]{amount});
            tally += amount;
            subTally += amount;

        }
          txtTotal.setText(Double.toString(subTally));

        //System.out.println("subTally = " + subTally);
        //System.out.println("tally = " + tally);
*/
	}
}

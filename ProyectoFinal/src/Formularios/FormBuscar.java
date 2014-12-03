package Formularios;

import java.awt.EventQueue;
import java.sql.SQLException;

//import javassist.expr.Instanceof;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

import Clases.ModeloTabla;
import Modelos.Producto;



public class FormBuscar extends JInternalFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private ModeloTabla modeloTabla;
	private JInternalFrame ventanaPadre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBuscar frame = new FormBuscar(new JInternalFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FormBuscar(final JInternalFrame jInternalFrame) throws SQLException {
		this.ventanaPadre = jInternalFrame;
		setTitle("Buscar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 243);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Descripcion:");
		lblNombre.setBounds(10, 11, 59, 14);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(66, 8, 321, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {// AL MOMENTO DE REALIZAR LA BUSQUEDAD LA CONDICION SE MODIFICA POR LO QUE ESTA EN EL TEXTBOX
					
					String condicion = "1";
					
					if(jInternalFrame instanceof frmFactura)
					{
						condicion = "Descripcion like '%" + textField.getText() + "%'";
					}
					/*else if(ventanaPadre instanceof FormUsuarios)
					{
						condicion = "nombreUsuario like '%" + textField.getText() + "%'";
					}*/
					
					modeloTabla.establecerCondicion( condicion );
					modeloTabla.realizarBusqueda();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon("lupa.png"));
		button.setBounds(391, 5, 33, 23);
		contentPane.add(button);
				
		try {
			modeloTabla = new ModeloTabla("*", "tblproducto", "1");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		modeloTabla.realizarBusqueda();
		
		table = new JTable(modeloTabla);
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(jInternalFrame instanceof frmFactura)
				{
					int Codigo = Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 0).toString() );
					String Descripcion =  modeloTabla.getValueAt(table.getSelectedRow(), 1).toString();
					float precio =  Float.parseFloat(modeloTabla.getValueAt(table.getSelectedRow(), 2).toString());
					
					
					 Producto nuevoProducto = new Producto(Codigo,Descripcion, precio);
					((frmFactura)jInternalFrame).cargarDatos(nuevoProducto);
				}
				
				/*if(jInternalFrame instanceof FormUsuarios)
				{
					int id = Integer.parseInt( modeloTabla.getValueAt(table.getSelectedRow(), 0).toString() );
					String nombre =  modeloTabla.getValueAt(table.getSelectedRow(), 1).toString();
					String clave =  modeloTabla.getValueAt(table.getSelectedRow(), 2).toString();
					((FormUsuarios)jInternalFrame).cargarDatos(nombre, clave);
				}*/
				dispose();			
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 36, 568, 154);
		contentPane.add(scrollPane);		
	}

	public ModeloTabla getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(ModeloTabla modeloTabla) {
		this.modeloTabla = modeloTabla;
	}
}
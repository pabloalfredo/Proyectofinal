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

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.sql.*;

import Clases.BaseDeDatos;
import Clases.Categoria;
import Modelos.Producto;
import Modelos.TipoProducto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class frmAgregarProducto extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtCodigoAgregarProducto;
	private JTextField txtDescripcionAgregarProducto;
	private JTextField txtPrecioAgregarProducto;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbTipoAgregarProducto;
	private Vector <TipoProducto> categorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAgregarProducto frame = new frmAgregarProducto();
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
	public frmAgregarProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label = new JLabel("Agregar Productos");
		label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel label_1 = new JLabel("Codigo");
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		txtCodigoAgregarProducto = new JTextField();
		txtCodigoAgregarProducto.setColumns(10);
		
		JButton button = new JButton("Generar ");
		button.setToolTipText("Generar un Codigo si el Producto no tiene Codigo");
		
		JLabel label_2 = new JLabel("Descripcion");
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		txtDescripcionAgregarProducto = new JTextField();
		txtDescripcionAgregarProducto.setColumns(10);
		
		JLabel label_3 = new JLabel("Precio");
		label_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		txtPrecioAgregarProducto = new JTextField();
		txtPrecioAgregarProducto.setColumns(10);
		
		JLabel label_4 = new JLabel("Tipo");
		label_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		
	
		 cmbTipoAgregarProducto = new JComboBox();
		 cargarComboBox();
		
	
		
		JButton button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//BOTON AGREGAR PRODUCTOS
				
				//if (validarFormulario()==true)
				//{
					int Codigo = Integer.parseInt(txtCodigoAgregarProducto.getText());
					String descripcion = txtDescripcionAgregarProducto.getText();
					float precio = Float.parseFloat(txtPrecioAgregarProducto.getText());
					//String DescripcionCmb=categorias.get(cmbTipoAgregarProducto.getSelectedIndex()).getDescripcion();
					
					//int IdCmb= categorias.get(cmbTipoAgregarProducto.getSelectedIndex()).getIdTipoProducto();
					
					//TipoProducto ObjetoTipoProducto = new TipoProducto(IdCmb, DescripcionCmb);
					TipoProducto ObjetoTipoProducto = categorias.get(cmbTipoAgregarProducto.getSelectedIndex());
					Producto productoObtenido = new Producto(Codigo, descripcion, precio,ObjetoTipoProducto);
					try {
						productoObtenido.AgregarProducto();
					} catch (ClassNotFoundException e) {
						// TODO manejar la exception 
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				//	}
					dispose();
				}
				
				
			}
		});
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton button_3 = new JButton("Cancelar");
		GroupLayout gl_contentPane = agruparComponentes(panel, label, label_1,
				button, label_2, label_3, label_4, button_1, button_3);
		contentPane.setLayout(gl_contentPane);
	}

	private GroupLayout agruparComponentes(JPanel panel, JLabel label,
			JLabel label_1, JButton button, JLabel label_2, JLabel label_3,
			JLabel label_4, JButton button_1, JButton button_3) {
		button_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(112)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(txtCodigoAgregarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(txtDescripcionAgregarProducto, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(txtPrecioAgregarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(cmbTipoAgregarProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(131)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtCodigoAgregarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDescripcionAgregarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPrecioAgregarProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(cmbTipoAgregarProducto, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		return gl_contentPane;
	}
	public void cargarComboBox() {//para llenar el comboBox
		
		BaseDeDatos conn = new BaseDeDatos();
			categorias = new Vector<>();
			ResultSet rs;
			try {
				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select ID, Descripcion from tbltipoproducto");
					while (rs.next()){
					TipoProducto categoria = new TipoProducto(rs.getInt(1), rs.getString(2));	
					
					categorias.add(categoria);
					cmbTipoAgregarProducto.addItem(categoria.getDescripcion());
					
					}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	
	
	 Boolean validarFormulario() {
		
		if ((txtCodigoAgregarProducto.getText()==null) || (txtCodigoAgregarProducto.getText()=="")){
			JOptionPane.showMessageDialog(null, "El Codigo no puede estar en blanco");
			return false;
		}
			
		else {
		return true;
		}
	}
	
}

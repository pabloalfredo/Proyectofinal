package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import Modelos.TipoProducto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class frmModificarTipoProducto extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtDescripcionModificarTipoProducto;
	private JTextField txtIDModificarTipoProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmModificarTipoProducto frame = new frmModificarTipoProducto();
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
	public frmModificarTipoProducto() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblModificarTipoDe = new JLabel("Modificar Tipo de Producto");
		lblModificarTipoDe.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtDescripcionModificarTipoProducto = new JTextField();
		txtDescripcionModificarTipoProducto.setColumns(10);
		
		JLabel label_1 = new JLabel("Descripcion");
		
		JButton button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//AQUI LE PASAMOS EL TEXTO QUE ESTA EN MODIFICAR TIPO PRODUCTO Y SE LO ENVIAMOS AL METODO MODIFICAR EN LA CLASE TIPO PRODUCTO
				
				int ID = Integer.parseInt(txtIDModificarTipoProducto.getText());
				String descripcion = txtDescripcionModificarTipoProducto.getText();
				TipoProducto TipoProductoModificar = new TipoProducto(ID,descripcion);
				try {
					TipoProductoModificar.ModificarTipoProducto();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
			
		});
		
		JButton button_1 = new JButton("Cancelar");
		
		JLabel lblId = new JLabel("ID");
		
		txtIDModificarTipoProducto = new JTextField();
		txtIDModificarTipoProducto.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDescripcionModificarTipoProducto, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtIDModificarTipoProducto, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(28)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(30)
								.addComponent(lblModificarTipoDe, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblModificarTipoDe, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(txtIDModificarTipoProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(txtDescripcionModificarTipoProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void cargarDatos(TipoProducto TipoProductoActual)// CARGA LOS DATOS AL FORMULARIO MODIFICAR TIPO PRODUCTO DESDE EL FORMULARIO TIPO PRODUCTO
	{		
		//String ID = TipoProductoActual.getIdTipoProducto();
	
		String ID = Integer.toString(TipoProductoActual.getIdTipoProducto());
		
		
		txtIDModificarTipoProducto.setText(ID);
		txtDescripcionModificarTipoProducto.setText(TipoProductoActual.getDescripcion());
		
	}

}

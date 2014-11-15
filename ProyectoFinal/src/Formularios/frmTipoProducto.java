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

import Clases.*;
import Clases.*;

public class frmTipoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcionTipoProducto;
	private JTable table;
	private ModeloTabla modeloTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTipoProducto frame = new frmTipoProducto();
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
	public frmTipoProducto() throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnNuevoTipoProducto = new JButton("Nuevo");
		
		JScrollPane scrollPane = new JScrollPane();
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
		try {
			modeloTabla = new ModeloTabla("*", "tbltipoproducto", "1");
			modeloTabla.realizarBusqueda();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		table = new JTable(modeloTabla);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}

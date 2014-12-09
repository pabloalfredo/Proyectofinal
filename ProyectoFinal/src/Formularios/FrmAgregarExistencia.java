package Formularios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import Modelos.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class FrmAgregarExistencia extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtCantidadComprada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgregarExistencia frame = new FrmAgregarExistencia();
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
	public FrmAgregarExistencia() {
		setClosable(true);
		getContentPane().setBackground(new Color(51, 153, 204));
		getContentPane().setLayout(null);
		
		JLabel lblAgregarExistencia = new JLabel("Agregar Existencia");
		lblAgregarExistencia.setForeground(new Color(204, 0, 0));
		lblAgregarExistencia.setFont(new Font("Arial", Font.BOLD, 24));
		lblAgregarExistencia.setBounds(76, 11, 224, 29);
		getContentPane().add(lblAgregarExistencia);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(21, 56, 69, 14);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(167, 55, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cantidad Comprada");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(21, 81, 149, 14);
		getContentPane().add(lblNewLabel);
		
		txtCantidadComprada = new JTextField();
		txtCantidadComprada.setBounds(167, 80, 86, 20);
		getContentPane().add(txtCantidadComprada);
		txtCantidadComprada.setColumns(10);
		
		JButton btnAgregarAlInventario = new JButton("Agregar al Inventario");
		btnAgregarAlInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Codigo = Integer.parseInt(txtCodigo.getText());
				int CantidadComprada = Integer.parseInt(txtCantidadComprada.getText());
				
				Producto agregarExistencia = new Producto(Codigo,CantidadComprada);
				try {
					agregarExistencia.AgregarExistenciaProducto();
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
		btnAgregarAlInventario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgregarAlInventario.setBounds(30, 129, 181, 23);
		getContentPane().add(btnAgregarAlInventario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(232, 129, 95, 23);
		getContentPane().add(btnCancelar);
		setBounds(100, 100, 378, 210);

	}
}

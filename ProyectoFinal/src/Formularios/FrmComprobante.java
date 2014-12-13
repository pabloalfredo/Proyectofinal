package Formularios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import Clases.CargarComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmComprobante extends JInternalFrame {
	private JTextField txtSecuencia;
	private JTextField txtLimiteComprobante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobante frame = new FrmComprobante();
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
	public FrmComprobante() {
		getContentPane().setBackground(new Color(51, 153, 204));
		setClosable(true);
		setBounds(100, 100, 450, 253);
		getContentPane().setLayout(null);
		
		JLabel lblTipoDeComprobante = new JLabel("Tipo de Comprobante");
		lblTipoDeComprobante.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeComprobante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeComprobante.setBounds(10, 69, 144, 14);
		getContentPane().add(lblTipoDeComprobante);
		
		JLabel lblNewLabel = new JLabel("Comprobantes Fiscal ");
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(122, 24, 209, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSecuencia = new JLabel("Secuencia");
		lblSecuencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSecuencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecuencia.setBounds(10, 101, 144, 14);
		getContentPane().add(lblSecuencia);
		
		JLabel lblLimite = new JLabel("Limite");
		lblLimite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLimite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimite.setBounds(10, 135, 144, 14);
		getContentPane().add(lblLimite);
		
		JComboBox cmbTipoComprobante = new JComboBox();
		cmbTipoComprobante.setBounds(164, 67, 260, 20);
		getContentPane().add(cmbTipoComprobante);
		CargarComboBox cargarCmbComprobante = new CargarComboBox();
		cargarCmbComprobante.cargarComboBoxComrobante(cmbTipoComprobante);
		
		txtSecuencia = new JTextField();
		txtSecuencia.setBounds(164, 99, 260, 20);
		getContentPane().add(txtSecuencia);
		txtSecuencia.setColumns(10);
		
		txtLimiteComprobante = new JTextField();
		txtLimiteComprobante.setColumns(10);
		txtLimiteComprobante.setBounds(164, 133, 260, 20);
		getContentPane().add(txtLimiteComprobante);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnAceptar.setBounds(164, 170, 123, 45);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCancelar.setBounds(301, 170, 123, 45);
		getContentPane().add(btnCancelar);

	}
}

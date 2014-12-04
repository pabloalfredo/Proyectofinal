package Formularios;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import Clases.BaseDeDatos;
//import Modelos.RegistrarUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAgregarNuevoUsuario extends JInternalFrame {
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNomUsuario;
	private JPasswordField txtPass;
	private JPasswordField txtVPass;
	//private Vector <RegistrarUsuario> categorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgregarNuevoUsuario frame = new FrmAgregarNuevoUsuario();
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
	public FrmAgregarNuevoUsuario() {
		setTitle("Agregar Nuevo Usuario");
		setFrameIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/birosoft/liquid/icons/computericon.png")));
		//setIcon(true);
		setBounds(100, 100, 418, 386);
		getContentPane().setLayout(null);
		
		Dimension tamFrame=this.getSize();//para obtener las dimensiones del frame
        Dimension tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();      //para obtener el tamanio de la pantalla
        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);  //para posicionar
		
		JLabel lblAgregarUsuario = new JLabel("Agregar Usuario");
		lblAgregarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarUsuario.setBounds(0, 13, 410, 22);
		getContentPane().add(lblAgregarUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombre.setBounds(10, 59, 52, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellido.setBounds(10, 95, 52, 16);
		getContentPane().add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(74, 56, 222, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(74, 92, 222, 22);
		getContentPane().add(txtApellido);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeUsuario.setBounds(10, 147, 123, 16);
		getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeUsuario.setBounds(10, 176, 103, 16);
		getContentPane().add(lblTipoDeUsuario);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasena.setBounds(10, 205, 103, 16);
		getContentPane().add(lblContrasena);
		
		JLabel lblRepitaSuContrasena = new JLabel("Repita su Contrasena");
		lblRepitaSuContrasena.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRepitaSuContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitaSuContrasena.setBounds(10, 234, 111, 16);
		getContentPane().add(lblRepitaSuContrasena);
		
		txtNomUsuario = new JTextField();
		txtNomUsuario.setColumns(10);
		txtNomUsuario.setBounds(135, 144, 161, 22);
		getContentPane().add(txtNomUsuario);
		
		JComboBox cbTipoUsuario = new JComboBox();
		cbTipoUsuario.setBounds(134, 173, 162, 22);
		getContentPane().add(cbTipoUsuario);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(135, 204, 161, 20);
		getContentPane().add(txtPass);
		
		cbTipoUsuario = new JComboBox();
		cargarComboBox();
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*int Codigo = Integer.parseInt(txtCodigoAgregarProducto.getText());
				String descripcion = txtDescripcionAgregarProducto.getText();
				float precio = Float.parseFloat(txtPrecioAgregarProducto.getText());
				//String DescripcionCmb=categorias.get(cmbTipoAgregarProducto.getSelectedIndex()).getDescripcion();
				
				//int IdCmb= categorias.get(cmbTipoAgregarProducto.getSelectedIndex()).getIdTipoProducto();
				
				//TipoProducto ObjetoTipoProducto = new TipoProducto(IdCmb, DescripcionCmb);
				TipoProducto ObjetoTipoProducto = categorias.get(cmbTipoAgregarProducto.getSelectedIndex());
				Producto productoObtenido = new Producto(Codigo, descripcion, precio,ObjetoTipoProducto);*/
				
			}
		});
		btnRegistrar.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/Recursos/Aceptar (2).png")));
		btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnRegistrar.setBounds(135, 278, 123, 45);
		getContentPane().add(btnRegistrar);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setIcon(new ImageIcon(FrmAgregarNuevoUsuario.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(267, 278, 123, 45);
		getContentPane().add(button_1);
		
		txtVPass = new JPasswordField();
		txtVPass.setBounds(135, 233, 161, 20);
		getContentPane().add(txtVPass);
		
		

	}
	public void cargarComboBox() {//para llenar el comboBox
		
		    BaseDeDatos conn = new BaseDeDatos();
			//categorias = new Vector<>();
			ResultSet rs;
			try {
				rs = (ResultSet) conn.getConexion().createStatement().executeQuery("select ID, Descripcion from tbltipoproducto");
					while (rs.next()){
					//TipoProducto categoria = new TipoProducto(rs.getInt(1), rs.getString(2));	
					
					//categorias.add(categoria);
					//cmbTipoAgregarProducto.addItem(categoria.getDescripcion());
					
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

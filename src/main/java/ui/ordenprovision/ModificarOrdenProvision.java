package ui.ordenprovision;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dto.ProductoComboBoxDTO;
import dto.SucursalComboBoxDTO;

@SuppressWarnings("serial")
public class ModificarOrdenProvision extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JLabel lblSucursal;
	private JComboBox<SucursalComboBoxDTO> cbSucursal;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblTiempo;
	private JTextField txtTiempo;
	private JLabel lblProducto;
	private JComboBox<ProductoComboBoxDTO> cbProducto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JTable tabla;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancelar;
	
	public ModificarOrdenProvision(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		
		lblSucursal = new JLabel("SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblSucursal, gbc);
		
		cbSucursal = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbSucursal,gbc);
		
		lblFecha = new JLabel("FECHA:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblFecha, gbc);
		
		txtFecha = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtFecha,gbc);
		
		lblTiempo = new JLabel("TIEMPO:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblTiempo,gbc);
		
		txtTiempo = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtTiempo,gbc);
		
		lblProducto = new JLabel("PRODUCTO: ");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblProducto,gbc);
		
		cbProducto = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.20;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbProducto,gbc);
		
		lblCantidad = new JLabel("CANTIDAD: ");
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblCantidad,gbc);
		
		txtCantidad = new JTextField();
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtCantidad,gbc);
		
		btnAgregar = new JButton("Agregar Producto");
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnAgregar, gbc);
		btnAgregar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton guardar
		});
		
		
		DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Para que las celdas no sean editables
                return false;
            }
        };
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        for (int i = 0; i < 25; i++) {
            modelo.addRow(new Object[]{""});
        }
		
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JScrollPane(tabla), gbc);
		
		btnEliminar = new JButton("Eliminar Producto");
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton guardar
		});
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton guardar
		});
	
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la modificacion de Orden Provision?";
				int confirmado = JOptionPane.showOptionDialog(
						this, 
						mensaje, 
						"CONFIRMACION", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						new Object[] {"SI","NO"}, 
						"SI");
				if(confirmado == 0) {
					ventana.setTitle("TP DIEDE 2023 - Menú Orden Provision");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}
}
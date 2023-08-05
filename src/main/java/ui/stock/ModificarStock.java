package ui.stock;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import dto.BusquedaStockDTO;
import dto.BusquedaStockProductoDTO;
import dto.FilaTablaOrdenProvisionDTO;
import dto.ModificarStockDTO;
import dto.ProductoComboBoxDTO;
import excepciones.UpdateDBException;
import gestores.GestorProducto;
import gestores.GestorStock;
import gestores.GestorSucursal;

@SuppressWarnings("serial")
public class ModificarStock extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorStock gestorStock = GestorStock.getInstancia();
	private GestorSucursal gestorSucursal = GestorSucursal.getInstancia();
	private GestorProducto gestorProducto = GestorProducto.getInstancia();
	private BusquedaStockDTO dto;
	private JLabel lblSucursal;
	private JTextField txtSucursal;;
	private JLabel lblProducto;
	private JComboBox<ProductoComboBoxDTO> cbProducto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JTable tabla;
	DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancelar;
	
	public ModificarStock(JFrame ventana, JPanel panelPadre, BusquedaStockDTO dto) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.dto = dto;
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		lblSucursal = new JLabel("SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblSucursal, gbc);
		
		txtSucursal = new JTextField();
		txtSucursal.setEnabled(false);
		txtSucursal.setText(dto.getSucursal());
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtSucursal,gbc);
	
		lblProducto = new JLabel("PRODUCTO:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblProducto,gbc);
		
		cbProducto = new JComboBox<>();
		for(ProductoComboBoxDTO p : gestorStock.listaProductos()) cbProducto.addItem(p);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbProducto,gbc);
		
		lblCantidad = new JLabel("CANTIDAD:");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblCantidad,gbc);
		
		txtCantidad = new JTextField();
		txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
	    	@Override
	        public void insertUpdate(DocumentEvent e) {
	    		validateFormat();
	        }
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	validateFormat();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {}
	        private void validateFormat() {
	        	String text = txtCantidad.getText();
	            if (!text.matches("^\\d{1,4}")) {
	                txtCantidad.setForeground(Color.RED);
	             } else {
	            	 txtCantidad.setForeground(Color.BLACK);
	             }
	        }
	    });
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtCantidad,gbc);
		
		btnAgregar = new JButton("Agregar Producto");
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnAgregar, gbc);
		btnAgregar.addActionListener(e -> {
			if(txtCantidad.getText().isBlank() || txtCantidad.getText().equals("0")) {
				String mensaje = "El campo cantidad no puede ser vacío o cero";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else if(txtCantidad.getForeground() == Color.RED) {
				String mensaje = "El campo cantidad solo acepta numeros enteros";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				ProductoComboBoxDTO producto = (ProductoComboBoxDTO) cbProducto.getSelectedItem();
				String cantidad = txtCantidad.getText();
				Object[] dato = new Object[2];
				dato[0] = producto;
				dato[1] = cantidad;
				modelo.addRow(dato);
			}
		});
		
		modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Para que las celdas no sean editables
                return false;
            }
        };
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        for (BusquedaStockProductoDTO p : dto.getProductos()) {
        	Object[] dato = new Object[2];
			dato[0] = p.getProducto();
			dato[1] = p.getCantidad();
            modelo.addRow(dato);
        }
		
        tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);// Ordena por la primera columna
        // Agrega un oyente para habilitar el botón "Modificar" cuando se selecciona una fila con datos
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow >= 0 && !modelo.getValueAt(selectedRow, 0).toString().isEmpty()) {
                btnEliminar.setEnabled(true);
            } else {
                btnEliminar.setEnabled(false);
            }
        });
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JScrollPane(tabla), gbc);
		
		btnEliminar = new JButton("Eliminar Producto");
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar el producto?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				modelo.removeRow(tabla.getSelectedRow());
			}
		});
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			if(tabla.getRowCount() == 0) {
				String mensaje = "La tabla no puede estar vacía";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				List<FilaTablaOrdenProvisionDTO> filasTabla = new ArrayList<FilaTablaOrdenProvisionDTO>();
				int filas = tabla.getRowCount();
				for(int i = 0; i < filas; i++) {
					String producto = modelo.getValueAt(i, 0).toString();
					Integer idProducto = gestorProducto.getProducto(producto).getId();
					Integer cantidad = Integer.parseInt(modelo.getValueAt(i, 1).toString());
					FilaTablaOrdenProvisionDTO f = new FilaTablaOrdenProvisionDTO(idProducto, cantidad);
					filasTabla.add(f);
				}
				Integer idSucursal = gestorSucursal.getSucursal(dto.getSucursal()).getId();
				ModificarStockDTO dto = new ModificarStockDTO(idSucursal,filasTabla);
				try {
					gestorStock.modificarStock(dto);
					mostrarMensajeStockModificado();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido modificar el stock";
					JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la modificación de stock?";
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
					ventana.setTitle("TP DIEDE 2023 - Gestión Stock");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}
	
	private void mostrarMensajeStockModificado() {
		String mensaje = "El stock ha sido modificado correctamente.";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "INFORMACIÓN", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
		if (confirmado == 0) {
			ventana.setTitle("TP DIED 2023 - Gestión Stock");
			ventana.setContentPane(new GestionStock(ventana, panelPadre));
			ventana.setVisible(true);
		}
	}
}
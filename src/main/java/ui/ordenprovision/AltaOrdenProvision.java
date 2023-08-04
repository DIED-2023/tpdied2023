package ui.ordenprovision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
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

import com.toedter.calendar.JDateChooser;

import dto.AltaOrdenProvisionDTO;
import dto.FilaTablaOrdenProvisionDTO;
import dto.ProductoComboBoxDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;
import gestores.GestorOrdenProvision;

@SuppressWarnings("serial")
public class AltaOrdenProvision extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorOrdenProvision gestorOrdenProvision = GestorOrdenProvision.getInstancia();
	private JLabel lblSucursal;
	private JComboBox<SucursalComboBoxDTO> cbSucursal;
	private JLabel lblFecha;
	private JDateChooser fecha;
	private JLabel lblTiempo;
	private JTextField txtTiempo;
	private JLabel lblProducto;
	private JComboBox<ProductoComboBoxDTO> cbProducto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	public AltaOrdenProvision(JFrame ventana, JPanel panelPadre) {
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
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblSucursal, gbc);
		
		cbSucursal = new JComboBox<>();
		for(SucursalComboBoxDTO s : gestorOrdenProvision.listaSucursales()) cbSucursal.addItem(s);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbSucursal,gbc);
		
		lblFecha = new JLabel("FECHA:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblFecha, gbc);
		
		fecha = new JDateChooser("dd/MM/yyyy","##/##/####",'-');
		fecha.getJCalendar().setMinSelectableDate(new Date());
		//Falta que cuando ingresa una fecha menor a la de hoy no lo permita
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(fecha,gbc);
		
		lblTiempo = new JLabel("TIEMPO (Horas):");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblTiempo,gbc);
		
		txtTiempo = new JTextField();
		txtTiempo.getDocument().addDocumentListener(new DocumentListener() {
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
	        	String text = txtTiempo.getText();
	            if (!text.matches("\\d*(\\.\\d{0,2})?")) {
	                txtTiempo.setForeground(Color.RED);
	             } else {
	            	 txtTiempo.setForeground(Color.BLACK);
	             }
	        }
	    });
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtTiempo,gbc);
		
		lblProducto = new JLabel("PRODUCTO:");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblProducto,gbc);
		
		cbProducto = new JComboBox<>();
		for(ProductoComboBoxDTO p : gestorOrdenProvision.listaProductos()) cbProducto.addItem(p);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbProducto,gbc);
		
		lblCantidad = new JLabel("CANTIDAD: ");
		gbc.gridx = 2;
		gbc.gridy = 3;
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
		gbc.gridy = 3;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtCantidad,gbc);
		
		btnAgregar = new JButton("Agregar Producto");
		gbc.gridx = 4;
		gbc.gridy = 3;
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
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JScrollPane(tabla), gbc);
		
		btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.setEnabled(false);
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar el producto?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				modelo.removeRow(tabla.getSelectedRow());
			}
		});
		
		btnGuardar = new JButton("Guardar");
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnGuardar, gbc);
		btnGuardar.addActionListener(e -> {
			if(tabla.getRowCount() == 0) {
				String mensaje = "La tabla no puede estar vacía";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else if(txtTiempo.getBackground() == Color.RED) {
				String mensaje = "El formato para el campo TIEMPO es 'HH.MM'";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else if(!validarDatosObligatorios()) {
				String mensaje = "Todos los campos son obligatorios";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				List<FilaTablaOrdenProvisionDTO> filasTabla = new ArrayList<FilaTablaOrdenProvisionDTO>();
				int filas = tabla.getRowCount();
				for(int i = 0; i < filas; i++) {
					Integer idProducto =((ProductoComboBoxDTO) modelo.getValueAt(i, 0)).getId();
					Integer cantidad = Integer.parseInt(modelo.getValueAt(i, 1).toString());
					FilaTablaOrdenProvisionDTO f = new FilaTablaOrdenProvisionDTO(idProducto, cantidad);
					filasTabla.add(f);
				}
				Integer idSucursal = ((SucursalComboBoxDTO)cbSucursal.getSelectedItem()).getId();
				Date date = fecha.getDate();
				String tiempo = txtTiempo.getText();
				AltaOrdenProvisionDTO dto = new AltaOrdenProvisionDTO(idSucursal,date,tiempo, filasTabla);
				try {
					gestorOrdenProvision.altaOrdenProvision(dto);
					mostrarMensajeOrdenProvisionAgregada();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido guardar la orden de provisión";
					JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar el alta de Orden Provision?";
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
	
	private boolean validarDatosObligatorios() {
		if(txtTiempo.getText().isBlank() || fecha.getDate() == null) return false;
		return true;
	}
	
	private void mostrarMensajeOrdenProvisionAgregada() {
		String mensaje = "La orden de provisión ha sido agregada correctamente. ¿Desea cargar otra orden?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if (confirmado == 0) {
			ventana.setContentPane(new AltaOrdenProvision(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIED 2023 - Menú Orden Provisión");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
}
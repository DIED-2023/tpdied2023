package ui.stock;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dto.BusquedaStockDTO;
import dto.EliminarStockDTO;
import dto.StockDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;
import gestores.GestorStock;

@SuppressWarnings("serial")
public class GestionStock extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorStock gestorStock = GestorStock.getInstancia();
	private JLabel lblSucursal;
	private JComboBox<SucursalComboBoxDTO> cbSucursal;
	private JButton btnBuscar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	
	public GestionStock(JFrame ventana, JPanel panelPadre) {
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
		for(SucursalComboBoxDTO s : gestorStock.listaSucursales()) cbSucursal.addItem(s);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbSucursal,gbc);
		
		btnBuscar = new JButton("Buscar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnBuscar, gbc);
		btnBuscar.addActionListener(e -> {
			modelo.setRowCount(0);// Borra todas las filas actuales de la tabla
			List<StockDTO> stocks = gestorStock.buscarStocksPorNombreSucursal(cbSucursal.getSelectedItem().toString());
			if(stocks.isEmpty()) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "No existen stocks para la sucursal seleccionada";
				JOptionPane.showMessageDialog(this, mensaje, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				cbSucursal.requestFocus();
			}else {
				for(StockDTO s : stocks) {
					Object[] dato = new Object[2];
					dato[0] = s.getProducto();
					dato[1] = s.getCantidad();
					modelo.addRow(dato);
				}
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
        for (int i = 0; i < 100; i++) {
            modelo.addRow(new Object[]{""});
        }
		
        tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);// Ordena por la primera columna
        // Agrega un oyente para habilitar el botón "Modificar" cuando se selecciona una fila con datos
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow >= 0 && !modelo.getValueAt(selectedRow, 0).toString().isEmpty()) {
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
            } else {
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        });
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tabla), gbc);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			Integer idSucursal = ((SucursalComboBoxDTO) cbSucursal.getSelectedItem()).getId();
			BusquedaStockDTO dto = gestorStock.buscarStockById(idSucursal);
			ventana.setTitle("TP DIED 2023 - Modificar Stock");
			ventana.setContentPane(new ModificarStock(ventana, new GestionStock(ventana, panelPadre), dto));
			ventana.setVisible(true);
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar el stock?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				Integer idSucursal = ((SucursalComboBoxDTO) cbSucursal.getSelectedItem()).getId();
				String producto = modelo.getValueAt(tabla.getSelectedRow(), 0).toString();
				EliminarStockDTO dto = new EliminarStockDTO(idSucursal, producto);
				try {
					gestorStock.eliminarStock(dto);
					mostrarMensajeStockBorrado();
				} catch (UpdateDBException e1) {
					String msj = "No se ha podido borrar el stock";
					JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 2;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la gestión de stock?";
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
					ventana.setTitle("TP DIED 2023 - Menú Stock");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}
	
	private void mostrarMensajeStockBorrado() {
		String mensaje = "El stock ha sido borrado correctamente. ¿Desea seguir gestionando stock?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if(confirmado == 0) {
			ventana.setContentPane(new GestionStock(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIED 2023 - Menú Stock");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
}
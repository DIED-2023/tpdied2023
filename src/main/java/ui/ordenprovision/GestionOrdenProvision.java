package ui.ordenprovision;

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

import dto.BusquedaOrdenProvisionDTO;
import dto.OrdenProvisionDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;
import gestores.GestorOrdenProvision;

@SuppressWarnings("serial")
public class GestionOrdenProvision extends JPanel{
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorOrdenProvision gestorOrdenProvision = GestorOrdenProvision.getInstancia();
	private JLabel lblNombreSucursal;
	private JComboBox<SucursalComboBoxDTO> cbSucursal;
	private JButton btnBuscar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnAsignarRecorrido;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	
	public GestionOrdenProvision(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		lblNombreSucursal = new JLabel("SUCURSAL DESTINO:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombreSucursal, gbc);
		
		cbSucursal = new JComboBox<>();
		for(SucursalComboBoxDTO s : gestorOrdenProvision.listaSucursales()) cbSucursal.addItem(s);
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
			List<OrdenProvisionDTO> ordenes = gestorOrdenProvision.buscarOrdenesPorNombreSucursal(cbSucursal.getSelectedItem().toString());
			if(ordenes.isEmpty()) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "No existen ordenes según la sucursal seleccionada";
				JOptionPane.showMessageDialog(this, mensaje, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				cbSucursal.requestFocus();
			}else {
				for(OrdenProvisionDTO o : ordenes) {
					Object[] dato = new Object[4];
					dato[0] = o.getId();
					dato[1] = o.getNombre();
					dato[2] = o.getFecha();
					dato[3] = o.getTiempo();
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
        modelo.addColumn("ID Orden");
        modelo.addColumn("Sucursal Destino");
        modelo.addColumn("Fecha");
        modelo.addColumn("Tiempo (Horas)");
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
                btnAsignarRecorrido.setEnabled(true);
            } else {
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnAsignarRecorrido.setEnabled(false);
            }
        });
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 6;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tabla), gbc);
		
		btnAsignarRecorrido = new JButton("Asignar Recorrido");
		btnAsignarRecorrido.setEnabled(false);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnAsignarRecorrido, gbc);
		btnAsignarRecorrido.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton asignar recorrido
		});
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			Integer idOrden = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
			BusquedaOrdenProvisionDTO dto = gestorOrdenProvision.buscarOrdenById(idOrden);
			ventana.setTitle("TP DIED 2023 - Modificar Orden Provisión");
			ventana.setContentPane(new ModificarOrdenProvision(ventana, new GestionOrdenProvision(ventana, panelPadre), dto));
			ventana.setVisible(true);
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		gbc.gridx = 4;
		gbc.gridy = 2;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar la orden de provisión?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				Integer idOrden = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
				try {
					gestorOrdenProvision.eliminarOrdenProvision(idOrden);
					mostrarMensajeOrdenProvisionBorrado();
				} catch (UpdateDBException e1) {
					String msj = "No se ha podido borrar la orden de provisión";
					JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 5;
		gbc.gridy = 2;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la gestión de orden de provisión?";
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
					ventana.setTitle("TP DIEDE 2023 - Menú Orden Provisión");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}
	
	private void mostrarMensajeOrdenProvisionBorrado() {
		String mensaje = "La orden de provisión ha sido borrada correctamente. ¿Desea seguir gestionando ordenes de provisión?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if(confirmado == 0) {
			ventana.setContentPane(new GestionOrdenProvision(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIED 2023 - Menú Orden Provisión");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
}
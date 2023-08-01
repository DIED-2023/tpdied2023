package ui.sucursal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dto.BusquedaSucursalDTO;
import dto.SucursalDTO;
import excepciones.UpdateDBException;
import gestores.GestorSucursal;

@SuppressWarnings("serial")
public class GestionSucursal extends JPanel{
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorSucursal gestorSucursal = GestorSucursal.getInstancia();
	private JLabel lblNombre;
	private JTextField txtNombre;
	private DefaultTableModel modelo;
	private JTable tabla;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;

	public GestionSucursal(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		lblNombre = new JLabel("NOMBRE:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombre, gbc);
		
		txtNombre = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtNombre,gbc);
		
		btnBuscar = new JButton("Buscar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnBuscar, gbc);
		btnBuscar.addActionListener(e -> {
			modelo.setRowCount(0);// Borra todas las filas actuales de la tabla
			List<SucursalDTO> sucursales = gestorSucursal.buscarSucursalesPorNombre(txtNombre.getText());
			if(sucursales.isEmpty()) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "No existen sucursales según el nombre ingresado";
				JOptionPane.showMessageDialog(this, mensaje, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				txtNombre.requestFocus();
			}else {
				for(SucursalDTO s : sucursales) {
					Object[] dato = new Object[5];
					dato[0] = s.getNombre();
					dato[1] = s.getHorarioApertura();
					dato[2] = s.getHorarioCierre();
					dato[3] = s.getEstado();
					dato[4] = s.getTipo();
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
        modelo.addColumn("Nombre Sucursal");
        modelo.addColumn("Horario Apertura");
        modelo.addColumn("Horario Cierre");
        modelo.addColumn("Estado");
        modelo.addColumn("Tipo");
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
			String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);
			BusquedaSucursalDTO dto = gestorSucursal.getSucursal(nombre);
			ventana.setContentPane(new ModificarSucursal(ventana, new GestionSucursal(ventana, panelPadre), dto));
			ventana.setVisible(true);
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar la sucursal?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);
				try {
					gestorSucursal.eliminarSucursal(nombre);
					mostrarMensajeSucursalBorrada();
				} catch (UpdateDBException e1) {
					String msj = "No se ha podido borrar la sucursal";
					JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 2;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la gestión de sucursales?";
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
					ventana.setTitle("TP DIEDE 2023 - Menú Sucursal");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}

	private void mostrarMensajeSucursalBorrada() {
		String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);
		String mensaje = "La sucursal "+nombre+" ha sido borrada correctamente. ¿Desea seguir gestionando sucursales?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if(confirmado == 0) {
			ventana.setContentPane(new GestionSucursal(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIEDE 2023 - Menú Sucursal");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
}
package ui.ruta;

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

import dto.BusquedaRutaDTO;
import dto.GestionRutaDTO;
import excepciones.NoExisteDestinoRutaException;
import excepciones.NoExisteOrigenRutaException;
import excepciones.NoExisteRutaException;
import excepciones.UpdateDBException;
import gestores.GestorRuta;

@SuppressWarnings("serial")
public class GestionRuta extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorRuta gestorRuta = GestorRuta.getInstancia();
	private JLabel lblSucursalOrigen;
	private JTextField txtSucursalOrigen;
	private JLabel lblSucursalDestino;
	private JTextField txtSucursalDestino;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnModificar;
	private JButton btnEliminar;

	public GestionRuta(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}

	public void armarPanel() {
		lblSucursalOrigen = new JLabel("SUCURSAL ORIGEN:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblSucursalOrigen, gbc);

		txtSucursalOrigen = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtSucursalOrigen, gbc);
		
		lblSucursalDestino = new JLabel("SUCURSAL DESTINO:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblSucursalDestino, gbc);

		txtSucursalDestino = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtSucursalDestino, gbc);

		btnBuscar = new JButton("Buscar");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		this.add(btnBuscar, gbc);
		btnBuscar.addActionListener(e -> {
			modelo.setRowCount(0);// Borra todas las filas actuales de la tabla
			String origen = txtSucursalOrigen.getText().trim().replaceAll("\\s+", " "); //Elimina espacios en blanco al principio y final
			String destino = txtSucursalDestino.getText().trim().replaceAll("\\s+", " ");//Elimina espacios en blanco al principio y final
			try {
				List<GestionRutaDTO> rutas = gestorRuta.buscarRutasPorOrigenDestino(origen,destino);
				if(rutas.isEmpty()) {
					for (int i = 0; i < 100; i++) {
						 modelo.addRow(new Object[]{""});
				    }
					String mensaje = "No existen rutas según los criterios";
					JOptionPane.showMessageDialog(this, mensaje, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				}else {
					for(GestionRutaDTO r : rutas) {
						Object[] dato = new Object[6];
						dato[0] = r.getId();
						dato[1] = r.getSucursalOrigen();
						dato[2] = r.getSucursalDestino();
						dato[3] = r.getCapacidad();
						dato[4] = r.getDuracion();
						dato[5] = r.getEstado();
						modelo.addRow(dato);
					}
				}
			} catch (NoExisteRutaException e1) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "La ruta con origen y destino ingresado no existen";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}catch (NoExisteOrigenRutaException e1) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "El origen ingresado no existe";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}catch (NoExisteDestinoRutaException e1) {
				for (int i = 0; i < 100; i++) {
					 modelo.addRow(new Object[]{""});
			    }
				String mensaje = "El destino ingresado no existe";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});

		modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Para que las celdas no sean editables
				return false;
			}
		};
		modelo.addColumn("ID Ruta");
		modelo.addColumn("Sucursal Origen");
		modelo.addColumn("Sucursal Destino");
		modelo.addColumn("Capacidad (Kg)");
		modelo.addColumn("Duracion (Hs)");
		modelo.addColumn("Estado");
		for (int i = 0; i < 100; i++) {
			modelo.addRow(new Object[] { "" });
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
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tabla), gbc);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			Integer idRuta = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
			BusquedaRutaDTO dto = gestorRuta.buscarRutaById(idRuta);
			ventana.setTitle("TP DIED 2023 - Modificar Ruta");
			ventana.setContentPane(new ModificarRuta(ventana, new GestionRuta(ventana, panelPadre), dto));
			ventana.setVisible(true);
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar la ruta?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				Integer idRuta = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
				try {
					gestorRuta.eliminarRuta(idRuta);
					mostrarMensajeRutaBorrada();
				} catch (UpdateDBException e1) {
					String msj = "No se ha podido borrar la ruta";
					JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 3;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar la gestión de ruta?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIED 2023 - Menú Ruta");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
	
	private void mostrarMensajeRutaBorrada() {
		String mensaje = "La ruta ha sido borrada correctamente. ¿Desea seguir gestionando rutas?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if(confirmado == 0) {
			ventana.setContentPane(new GestionRuta(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIED 2023 - Menú Ruta");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
}
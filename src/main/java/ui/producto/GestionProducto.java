package ui.producto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

import excepciones.UpdateDBException;
import gestores.GestorProducto;

@SuppressWarnings("serial")
public class GestionProducto extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorProducto gestorProducto = GestorProducto.getInstancia();
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnBuscar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;

	public GestionProducto(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}

	public void armarPanel() {
		lblProducto = new JLabel("PRODUCTO:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblProducto, gbc);

		txtProducto = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtProducto, gbc);

		btnBuscar = new JButton("Buscar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnBuscar, gbc);
		btnBuscar.addActionListener(e -> {
		 // TODO: Agregar funcionamiento boton buscar
		});

		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Para que las celdas no sean editables
				return false;
			}
		};
		modelo.addColumn("Nombre");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Precio Unitario");
		modelo.addColumn("Peso(KG)");
		for (int i = 0; i < 100; i++) {
			modelo.addRow(new Object[] { "" });
		}

		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tabla), gbc);
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton modificar
			//Tiene que llevarlo a la ventna ModificarSucursal despues de haber
			//seleccionado una fila 
		});
		
		btnEliminar = new JButton("Eliminar");
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			String mensaje = "¿Está seguro que desea eliminar el producto?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,new Object[] {"SI","NO"}, "SI");
			if(confirmado == 0) {
				String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);
				try {
					gestorProducto.eliminarProducto(nombre);
					mostrarMensajeProductoBorrada();
				} catch (UpdateDBException e1) {
					String msj = "No se ha podido borrar el producto";
					JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar la gestión de producto?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Menú Producto");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
	
	private void mostrarMensajeProductoBorrada() {
		String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 0);
		String mensaje = "El producto "+nombre+" ha sido borrada correctamente. ¿Desea seguir gestionando productos?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if(confirmado == 0) {
			ventana.setContentPane(new GestionProducto(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIEDE 2023 - Menú Producto");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}
	
}
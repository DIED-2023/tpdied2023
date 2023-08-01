package ui.producto;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.BusquedaProductoDTO;
import dto.ModificarProductoDTO;
import excepciones.ExisteSucursalException;
import excepciones.UpdateDBException;
import gestores.GestorProducto;

@SuppressWarnings("serial")
public class ModificarProducto extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorProducto gestorProducto = GestorProducto.getInstancia();
	private BusquedaProductoDTO dto;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblPrecioUnitario;
	private JTextField txtPrecioUnitario;
	private JLabel lblPesoKg;
	private JTextField txtPesoKg;

	private JButton btnModificar;
	private JButton btnCancelar;
	
	public ModificarProducto(JFrame ventana, JPanel panelPadre) {
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
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtNombre,gbc);
		
		lblDescripcion = new JLabel("DESCRIPCION:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblDescripcion,gbc);
		
		txtDescripcion = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtDescripcion,gbc);
		
		lblPrecioUnitario = new JLabel("PRECIO UNITARIO:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblPrecioUnitario, gbc);
		
		txtPrecioUnitario = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtPrecioUnitario, gbc);
		
		lblPesoKg = new JLabel("PESO (KG):");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblPesoKg, gbc);
		
		txtPesoKg = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtPesoKg, gbc);
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			if(!validarDatosObligatorios()) {
				String mensaje = "Todos los campos son obligatorios";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				//contarle a exe que utilice Double.parseDouble para convertir de string a double
				Double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
				Double pesoKg = Double.parseDouble(txtPesoKg.getText());
				ModificarProductoDTO modificarProductoDto = new ModificarProductoDTO(dto.getId(),nombre,dto.getNombre(), descripcion, precioUnitario, pesoKg);
				try {
					gestorProducto.modificarProducto(modificarProductoDto);
					mostrarMensajeProductoModificado();
				} catch (ExisteSucursalException e1) {
					String mensaje = "Ya existe un producto con ese nombre";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) txtNombre.requestFocus();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido modificar el producto";
					JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la modificación de producto?";
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
					ventana.setTitle("TP DIEDE 2023 - Gestión Producto");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}

	private void mostrarMensajeProductoModificado() {
		String mensaje = "El producto "+txtNombre.getText()+" ha sido modificado correctamente.";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "INFORMACIÓN", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
		if (confirmado == 0) {
			ventana.setTitle("TP DIED 2023 - Gestión Producto");
			ventana.setContentPane(new GestionProducto(ventana, panelPadre));
			ventana.setVisible(true);
		}
	}

	private boolean validarDatosObligatorios() {
		if(txtNombre.getText().isBlank() || txtDescripcion.getText().isBlank() 
				|| txtPrecioUnitario.getText().isBlank()) return false;
		return true;
	}
	
}

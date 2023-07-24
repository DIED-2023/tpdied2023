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

@SuppressWarnings("serial")
public class ModificarProducto extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
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
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombre, gbc);
		
		txtNombre = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtNombre,gbc);
		
		lblDescripcion = new JLabel("DESCRIPCION:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblDescripcion,gbc);
		
		txtDescripcion = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtDescripcion,gbc);
		
		lblPrecioUnitario = new JLabel("PRECIO UNITARIO:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblPrecioUnitario, gbc);
		
		txtPrecioUnitario = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtPrecioUnitario, gbc);
		
		lblPesoKg = new JLabel("PESO (KG):");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblPesoKg, gbc);
		
		txtPesoKg = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtPesoKg, gbc);
		
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton guardar
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 1;
		gbc.gridy = 5;
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
					ventana.setTitle("TP DIEDE 2023 - Menú Producto");
					ventana.setContentPane(panelPadre);
					ventana.setVisible(true);
				}
		});
	}
}

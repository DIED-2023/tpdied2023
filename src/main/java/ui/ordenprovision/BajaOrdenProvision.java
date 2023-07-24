package ui.ordenprovision;

import java.awt.Dimension;
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


@SuppressWarnings("serial")
public class BajaOrdenProvision extends JPanel{
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JLabel lblNombreSucursal;
	private JTextField txtNombreSucursal;
	private JTable tabla;
	private JButton btnEliminar;
	private JButton btnCancelar;
	
	public BajaOrdenProvision(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		
		lblNombreSucursal = new JLabel("NOMBRE SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombreSucursal, gbc);
		
		txtNombreSucursal = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtNombreSucursal,gbc);
	
		
		DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Para que las celdas no sean editables
                return false;
            }
        };
        modelo.addColumn("Sucursal");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha");
        modelo.addColumn("Tiempo Maximo");
        for (int i = 0; i < 5; i++) {
            modelo.addRow(new Object[]{""});
        }
		
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setPreferredScrollableViewportSize(new Dimension(300, 80));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JScrollPane(tabla), gbc);
		
		btnEliminar = new JButton("Eliminar");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		this.add(btnEliminar, gbc);
		btnEliminar.addActionListener(e -> {
			//TODO: Agregar funcionamiento boton aceptar
		});
		
		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
				String mensaje = "¿Deseas cancelar la baja de orden de provision?";
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
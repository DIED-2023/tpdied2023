package ui.ruta;

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
public class ConsultaRuta extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JLabel lblSucursalOrigen;
	private JTextField txtSucursalOrigen;
	private JLabel lblSucursalDestino;
	private JTextField txtSucursalDestino;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTable tabla;

	public ConsultaRuta(JFrame ventana, JPanel panelPadre) {
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
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblSucursalOrigen, gbc);

		txtSucursalOrigen = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtSucursalOrigen, gbc);
		
		lblSucursalDestino = new JLabel("SUCURSAL DESTINO:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.3;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblSucursalDestino, gbc);

		txtSucursalDestino = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtSucursalDestino, gbc);

		btnBuscar = new JButton("Buscar");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.1;
		this.add(btnBuscar, gbc);
		btnBuscar.addActionListener(e -> {
			// TODO: Agregar funcionamiento boton buscar
		});

		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar la consulta de ruta?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Menú Ruta");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});

		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Para que las celdas no sean editables
				return false;
			}
		};
		modelo.addColumn("Sucursa Origen");
		modelo.addColumn("Sucursal Destino");
		modelo.addColumn("Capacidad");
		modelo.addColumn("Duracion");
		modelo.addColumn("Estado");
		for (int i = 0; i < 100; i++) {
			modelo.addRow(new Object[] { "" });
		}

		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new JScrollPane(tabla), gbc);
	}
}
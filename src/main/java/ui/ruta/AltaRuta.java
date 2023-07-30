package ui.ruta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.EstadoRuta;
import dto.SucursalComboBoxDTO;

@SuppressWarnings("serial")
public class AltaRuta extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JLabel lblSucursalOrigen;
	private JComboBox<SucursalComboBoxDTO> cbSucursalOrigen;
	private JLabel lblSucursalDestino;
	private JComboBox<SucursalComboBoxDTO> cbSucursalDestino;
	private JLabel lblCapacidad;
	private JTextField txtCapacidad;
	private JLabel lblDuracion;
	private JTextField txtDuracion;
	private JLabel lblEstado;
	private JComboBox<EstadoRuta> cbEstado;
	private JButton btnGuardar;
	private JButton btnCancelar;

	public AltaRuta(JFrame ventana, JPanel panelPadre) {
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

		cbSucursalOrigen = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbSucursalOrigen, gbc);

		lblSucursalDestino = new JLabel("SUCURSAL DESTINO:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblSucursalDestino, gbc);

		cbSucursalDestino = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbSucursalDestino, gbc);

		lblCapacidad = new JLabel("CAPACIDAD:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblCapacidad, gbc);

		txtCapacidad = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtCapacidad, gbc);

		lblDuracion = new JLabel("DURACION:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblDuracion, gbc);

		txtDuracion = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtDuracion, gbc);

		lblEstado = new JLabel("ESTADO RUTA");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblEstado, gbc);

		cbEstado = new JComboBox<>(EstadoRuta.values());
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbEstado, gbc);

		btnGuardar = new JButton("Guardar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnGuardar, gbc);
		btnGuardar.addActionListener(e -> {
			// TODO: Agregar funcionamiento boton guardar
		});

		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar el alta de ruta?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Menú Ruta");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
}
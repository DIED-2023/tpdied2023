package ui.sucursal;

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

import dominio.EstadoSucursal;
import dominio.TipoSucursal;

@SuppressWarnings("serial")
public class ModificarSucursal extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblHorarioApertura;
	private JTextField txtHorarioApertura;
	private JLabel lblHorarioCierre;
	private JTextField txtHorarioCierre;
	private JLabel lblEstado;
	private JComboBox<EstadoSucursal> cbEstado;
	private JLabel lblTipoSucursal;
	private JComboBox<TipoSucursal> cbTipo;
	private JButton btnModificar;
	private JButton btnCancelar;

	public ModificarSucursal(JFrame ventana, JPanel panelPadre) {
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
		this.add(txtNombre, gbc);

		lblHorarioApertura = new JLabel("HORARIO DE APERTURA:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblHorarioApertura, gbc);

		txtHorarioApertura = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtHorarioApertura, gbc);

		lblHorarioCierre = new JLabel("HORARIO DE CIERRE:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblHorarioCierre, gbc);

		txtHorarioCierre = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtHorarioCierre, gbc);

		lblEstado = new JLabel("ESTADO DE SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblEstado, gbc);

		cbEstado = new JComboBox<>(EstadoSucursal.values());
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbEstado, gbc);

		lblTipoSucursal = new JLabel("TIPO DE SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblTipoSucursal, gbc);

		cbTipo = new JComboBox<>(TipoSucursal.values());
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbTipo, gbc);

		btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			// TODO: Agregar funcionamiento boton modificar
		});

		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar la modificación de sucursal?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Menú Sucursal");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
}
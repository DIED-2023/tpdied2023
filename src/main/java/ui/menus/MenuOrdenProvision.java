package ui.menus;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.ordenprovision.AltaOrdenProvision;
import ui.ordenprovision.BajaOrdenProvision;
import ui.ordenprovision.ConsultaOrdenProvision;
import ui.ordenprovision.ModificarOrdenProvision;

@SuppressWarnings("serial")
public class MenuOrdenProvision extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnModificar;
	private JButton btnConsulta;
	private JButton btnAtras;
	
	public MenuOrdenProvision(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		btnAlta = new JButton("Alta");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(btnAlta, gbc);
		btnAlta.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Alta Orden Provision");
			ventana.setContentPane(new AltaOrdenProvision(ventana, this));
			ventana.setVisible(true);
		});
		
		btnBaja = new JButton("Baja");
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(btnBaja, gbc);
		btnBaja.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Baja Orden Provision");
			ventana.setContentPane(new BajaOrdenProvision(ventana, this));
			ventana.setVisible(true);
		});
		
		btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(btnModificar, gbc);
		btnModificar.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Modificar Orden Provision");
			ventana.setContentPane(new ModificarOrdenProvision(ventana, this));
			ventana.setVisible(true);
		});
		
		btnConsulta = new JButton("Consulta");
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(btnConsulta, gbc);
		btnConsulta.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Consulta Orden Provision");
			ventana.setContentPane(new ConsultaOrdenProvision(ventana, this));
			ventana.setVisible(true);
		});
		
		btnAtras = new JButton("Atras");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		this.add(btnAtras, gbc);
		btnAtras.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Menú Principal");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		});
	}
}
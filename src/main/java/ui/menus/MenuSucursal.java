package ui.menus;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.sucursal.AltaSucursal;
import ui.sucursal.GestionSucursal;

@SuppressWarnings("serial")
public class MenuSucursal extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JButton btnAlta;
	private JButton btnGestion;
	private JButton btnAtras;
	
	public MenuSucursal(JFrame ventana, JPanel panelPadre) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		btnAlta = new JButton("Alta");
		btnAlta.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(btnAlta, gbc);
		btnAlta.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Alta Sucursal");
			ventana.setContentPane(new AltaSucursal(ventana, this));
			ventana.setVisible(true);
		});
		
		btnGestion = new JButton("Gestion");
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnGestion.setPreferredSize(new Dimension(200,25));
		this.add(btnGestion, gbc);
		btnGestion.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Gestion Sucursal");
			ventana.setContentPane(new GestionSucursal(ventana,this));
			ventana.setVisible(true);
		});
		
		btnAtras = new JButton("Atras");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnAtras, gbc);
		btnAtras.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Menú Principal");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		});
	}
}
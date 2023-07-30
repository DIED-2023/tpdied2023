package ui.menus;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.stock.AltaStock;
import ui.stock.GestionStock;

@SuppressWarnings("serial")
public class MenuStock extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JButton btnAlta;
	private JButton btnGestion;
	private JButton btnAtras;
	
	public MenuStock(JFrame ventana, JPanel panelPadre) {
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
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(btnAlta, gbc);
		btnAlta.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Alta Stock");
			ventana.setContentPane(new AltaStock(ventana,this));
			ventana.setVisible(true);
		});
		
		btnGestion = new JButton("Gestion");
		btnGestion.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(btnGestion, gbc);
		btnGestion.addActionListener(e -> {
			ventana.setTitle("TP DIED 2023 - Gestion Stock");
			ventana.setContentPane(new GestionStock(ventana,this));
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
package ui.menus;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel {
	private JFrame ventana;
	private GridBagConstraints gbc;
	private JButton btnSucursal;
	private JButton btnRuta;
	private JButton btnProducto;
	private JButton btnOrdenProvision;
	private JButton btnStock;
	
	public MenuPrincipal(JFrame ventana) {
		this.ventana = ventana;
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	public void armarPanel() {
		ventana.setTitle("TP DIED 2023 - Menú Principal");
		
		btnSucursal = new JButton("Sucursales");
		btnSucursal.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(btnSucursal, gbc);
		btnSucursal.addActionListener( e -> {
			ventana.setTitle("TP DIED 2023 - Menú Sucursales");
			ventana.setContentPane(new MenuSucursal(ventana, this));
			ventana.setVisible(true);
		});
		
		btnRuta = new JButton("Rutas");
		btnRuta.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(btnRuta, gbc);
		btnRuta.addActionListener( e -> {
			ventana.setTitle("TP DIED 2023 - Menú Rutas");
			ventana.setContentPane(new MenuRuta(ventana, this));
			ventana.setVisible(true);
		});
		
		btnProducto = new JButton("Productos");
		btnProducto.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(btnProducto, gbc);
		btnProducto.addActionListener( e -> {
			ventana.setTitle("TP DIED 2023 - Menú Productos");
			ventana.setContentPane(new MenuProducto(ventana, this));
			ventana.setVisible(true);
		});
		
		btnOrdenProvision = new JButton("Ordenes de Provisión");
		btnOrdenProvision.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(btnOrdenProvision, gbc);
		btnOrdenProvision.addActionListener( e -> {
			ventana.setTitle("TP DIED 2023 - Menú Ordenes de Provisión");
			ventana.setContentPane(new MenuOrdenProvision(ventana, this));
			ventana.setVisible(true);
		});
		
		btnStock = new JButton("Stocks");
		btnStock.setPreferredSize(new Dimension(200,25));
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(btnStock, gbc);
		btnStock.addActionListener( e -> {
			ventana.setTitle("TP DIED 2023 - Menú Stocks");
			ventana.setContentPane(new MenuStock(ventana, this));
			ventana.setVisible(true);
		});
		
	}
}
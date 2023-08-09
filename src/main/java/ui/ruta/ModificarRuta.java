package ui.ruta;

import java.awt.Color;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dominio.EstadoRuta;
import dto.BusquedaRutaDTO;
import dto.ModificarRutaDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;
import gestores.GestorRuta;

@SuppressWarnings("serial")
public class ModificarRuta extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorRuta gestorRuta = GestorRuta.getInstancia();
	private BusquedaRutaDTO dto;
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
	private JButton btnModificar;
	private JButton btnCancelar;

	public ModificarRuta(JFrame ventana, JPanel panelPadre, BusquedaRutaDTO dto) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.dto = dto;
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
		for(SucursalComboBoxDTO s : gestorRuta.listaSucursales()) cbSucursalOrigen.addItem(s);
		cbSucursalOrigen.getModel().setSelectedItem(new SucursalComboBoxDTO(dto.getIdSucursalOrigen(), dto.getSucursalOrigen()));
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
		for(SucursalComboBoxDTO s : gestorRuta.listaSucursales()) cbSucursalDestino.addItem(s);
		cbSucursalDestino.getModel().setSelectedItem(new SucursalComboBoxDTO(dto.getIdSucursalDestino(), dto.getSucursalDestino()));
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
		txtCapacidad.setText(dto.getCapacidad().toString());
		txtCapacidad.getDocument().addDocumentListener(new DocumentListener() {
	    	@Override
	        public void insertUpdate(DocumentEvent e) {
	    		validateFormat();
	        }
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	validateFormat();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {}
	        private void validateFormat() {
	        	String text = txtCapacidad.getText();
	            if (!text.matches("\\d*(\\.\\d{0,2})?")) {
	                txtCapacidad.setForeground(Color.RED);
	             } else {
	            	 txtCapacidad.setForeground(Color.BLACK);
	             }
	        }
	    });
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
		txtDuracion.setText(dto.getDuracion().toString());
		txtDuracion.getDocument().addDocumentListener(new DocumentListener() {
	    	@Override
	        public void insertUpdate(DocumentEvent e) {
	    		validateFormat();
	        }
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	validateFormat();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {}
	        private void validateFormat() {
	        	String text = txtDuracion.getText();
	            if (!text.matches("\\d*(\\.\\d{0,2})?")) {
	                txtDuracion.setForeground(Color.RED);
	             } else {
	            	 txtDuracion.setForeground(Color.BLACK);
	             }
	        }
	    });
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
		cbEstado.setSelectedItem(EstadoRuta.valueOf(dto.getEstado()));
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbEstado, gbc);

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
			}else if(txtCapacidad.getForeground() == Color.RED || txtDuracion.getForeground() == Color.RED){
				String mensaje = "El formato para el campo Duracion es 'HH.MM' y para el campo Capacidad es '99.99'";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				Integer idSucursalOrigen = ((SucursalComboBoxDTO)cbSucursalOrigen.getSelectedItem()).getId();
				Integer idSucursalDestino = ((SucursalComboBoxDTO)cbSucursalDestino.getSelectedItem()).getId();
				Double capacidad = Double.parseDouble(txtCapacidad.getText());
				Double duracion = Double.parseDouble(txtDuracion.getText());
				String estado = cbEstado.getSelectedItem().toString();
				ModificarRutaDTO modificarRutaDto = new ModificarRutaDTO(dto.getIdRuta(),capacidad,duracion,estado,idSucursalOrigen,idSucursalDestino);
				try {
					gestorRuta.modificarRuta(modificarRutaDto);
					mostrarMensajeRutaModificada();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido modificar la ruta";
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
			String mensaje = "¿Deseas cancelar la modificación de ruta?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIED 2023 - Gestión Ruta");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
	
	private boolean validarDatosObligatorios() {
		if(txtCapacidad.getText().isBlank() || txtDuracion.getText().isBlank()) return false;
		return true;
	}
	
	private void mostrarMensajeRutaModificada() {
		String mensaje = "La ruta ha sido modificada correctamente.";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "INFORMACIÓN", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
		if (confirmado == 0) {
			ventana.setTitle("TP DIED 2023 - Gestión Ruta");
			ventana.setContentPane(new GestionRuta(ventana, panelPadre));
			ventana.setVisible(true);
		}
	}
}
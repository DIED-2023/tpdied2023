package ui.sucursal;

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

import dominio.EstadoSucursal;
import dominio.TipoSucursal;
import dto.BusquedaSucursalDTO;
import dto.ModificarSucursalDTO;
import excepciones.ExisteCentroException;
import excepciones.ExistePuertoException;
import excepciones.ExisteSucursalException;
import excepciones.UpdateDBException;
import gestores.GestorSucursal;

@SuppressWarnings("serial")
public class ModificarSucursal extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorSucursal gestorSucursal = GestorSucursal.getInstancia();
	private BusquedaSucursalDTO dto;
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

	public ModificarSucursal(JFrame ventana, JPanel panelPadre, BusquedaSucursalDTO dto) {
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		this.gbc = new GridBagConstraints();
		this.dto = dto;
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}

	public void armarPanel() {
		lblNombre = new JLabel("NOMBRE:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombre, gbc);

		txtNombre = new JTextField();
		txtNombre.setText(dto.getNombre());
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtNombre, gbc);

		lblHorarioApertura = new JLabel("HORARIO DE APERTURA:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblHorarioApertura, gbc);

		txtHorarioApertura = new JTextField(5);
	    // Agregar un DocumentListener para validar el formato mientras el usuario escribe
	    txtHorarioApertura.getDocument().addDocumentListener(new DocumentListener() {
	    	@Override
	        public void insertUpdate(DocumentEvent e) {
	    		validateTimeFormat();
	        }
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	validateTimeFormat();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {}
	        private void validateTimeFormat() {
	        	String text = txtHorarioApertura.getText();
	            if (!text.matches("^\\d{0,2}:\\d{0,2}$")) {
	            	// Si el formato no coincide con "hh:mm", mostrar un mensaje de error
	                txtHorarioApertura.setForeground(Color.RED);
	             } else {
	            	 txtHorarioApertura.setForeground(Color.BLACK);
	             }
	        }
	    });
	    txtHorarioApertura.setText(dto.getHorarioApertura());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtHorarioApertura, gbc);

		lblHorarioCierre = new JLabel("HORARIO DE CIERRE:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblHorarioCierre, gbc);

		txtHorarioCierre = new JTextField(5);
	    // Agregar un DocumentListener para validar el formato mientras el usuario escribe
	    txtHorarioCierre.getDocument().addDocumentListener(new DocumentListener() {
	    	@Override
	        public void insertUpdate(DocumentEvent e) {
	    		validateTimeFormat();
	        }
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	validateTimeFormat();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {}
	        private void validateTimeFormat() {
	        	String text = txtHorarioCierre.getText();
	            if (!text.matches("^\\d{0,2}:\\d{0,2}$")) {
	            	// Si el formato no coincide con "hh:mm", mostrar un mensaje de error
	                txtHorarioCierre.setForeground(Color.RED);
	             } else {
	            	 txtHorarioCierre.setForeground(Color.BLACK);
	             }
	        }
	    });
	    txtHorarioCierre.setText(dto.getHorarioCierre());
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtHorarioCierre, gbc);

		lblEstado = new JLabel("ESTADO DE SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblEstado, gbc);

		cbEstado = new JComboBox<>(EstadoSucursal.values());
		cbEstado.setSelectedItem(EstadoSucursal.valueOf(dto.getEstado()));
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbEstado, gbc);

		lblTipoSucursal = new JLabel("TIPO DE SUCURSAL:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(lblTipoSucursal, gbc);

		cbTipo = new JComboBox<>(TipoSucursal.values());
		cbTipo.setSelectedItem(TipoSucursal.valueOf(dto.getTipo()));
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbTipo, gbc);

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
			}else if(txtHorarioApertura.getForeground() == Color.RED || txtHorarioCierre.getForeground() == Color.RED) {
				String mensaje = "El formato para el horario es 'hh:mm'";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else if(txtHorarioApertura.getText().compareTo(txtHorarioCierre.getText()) > 0) {
				String mensaje = "El horario de apertura no puede ser mayor al horario de cierre";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				String nombre = txtNombre.getText();
				String horarioApertura = txtHorarioApertura.getText();
				String horarioCierre = txtHorarioCierre.getText();
				String estado = cbEstado.getSelectedItem().toString();
				String tipoSucursal = cbTipo.getSelectedItem().toString();
				ModificarSucursalDTO modificarSucursalDto = new ModificarSucursalDTO(dto.getId(),nombre,dto.getNombre(), horarioApertura, horarioCierre, estado, tipoSucursal, dto.getTipo());
				try {
					gestorSucursal.modificarSucursal(modificarSucursalDto);
					mostrarMensajeSucursalModificada();
				} catch (ExisteSucursalException e1) {
					String mensaje = "Ya existe una sucursal con ese nombre";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) txtNombre.requestFocus();
				} catch (ExistePuertoException e1) {
					String mensaje = "Solamente puede haber un único Puerto";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) cbTipo.requestFocus();
				} catch (ExisteCentroException e1) {
					String mensaje = "Solamente puede haber un único Centro";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) cbTipo.requestFocus();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido modificar la sucursal";
					JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.weightx = 0;
		this.add(btnCancelar, gbc);
		btnCancelar.addActionListener(e -> {
			String mensaje = "¿Deseas cancelar la modificación de sucursal?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Gestión Sucursal");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
	
	private void mostrarMensajeSucursalModificada() {
		String mensaje = "La sucursal "+txtNombre.getText()+" ha sido modificada correctamente.";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "INFORMACIÓN", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
		if (confirmado == 0) {
			ventana.setTitle("TP DIED 2023 - Gestión Sucursal");
			ventana.setContentPane(new GestionSucursal(ventana, panelPadre));
			ventana.setVisible(true);
		}
	}

	private boolean validarDatosObligatorios() {
		if(txtNombre.getText().isBlank() || txtHorarioApertura.getText().isBlank() 
				|| txtHorarioCierre.getText().isBlank()) return false;
		return true;
	}
}
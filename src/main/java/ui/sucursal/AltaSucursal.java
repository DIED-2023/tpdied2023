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
import dto.AltaSucursalDTO;
import excepciones.ExisteCentroException;
import excepciones.ExistePuertoException;
import excepciones.ExisteSucursalException;
import excepciones.UpdateDBException;
import gestores.GestorSucursal;

@SuppressWarnings("serial")
public class AltaSucursal extends JPanel {
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private GestorSucursal gestorSucursal = GestorSucursal.getInstancia();
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
	private JButton btnGuardar;
	private JButton btnCancelar;

	public AltaSucursal(JFrame ventana, JPanel panelPadre) {
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
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(lblNombre, gbc);

		txtNombre = new JTextField();
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
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(cbTipo, gbc);

		btnGuardar = new JButton("Guardar");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(btnGuardar, gbc);
		btnGuardar.addActionListener(e -> {
			if(!validarDatosObligatorios()) {
				String mensaje = "Todos los campos son obligatorios";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}if(txtHorarioApertura.getForeground() == Color.RED || txtHorarioCierre.getForeground() == Color.RED) {
				String mensaje = "El formato para el horario es 'hh:mm'";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}if(txtHorarioApertura.getText().compareTo(txtHorarioCierre.getText()) > 0) {
				String mensaje = "El horario de apertura no puede ser mayor al horario de cierre";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				String nombre = txtNombre.getText();
				String horarioApertura = txtHorarioApertura.getText();
				String horarioCierre = txtHorarioCierre.getText();
				EstadoSucursal estado = (EstadoSucursal) cbEstado.getSelectedItem();
				TipoSucursal tipoSucursal = (TipoSucursal) cbTipo.getSelectedItem();
				AltaSucursalDTO altaSucursalDto = new AltaSucursalDTO(nombre, horarioApertura, horarioCierre, estado, tipoSucursal);
				try {
					gestorSucursal.altaSucursal(altaSucursalDto);
					mostrarMensajeSucursalAgregada();
				} catch (ExisteSucursalException e1) {
					String mensaje = "Ya existe la sucursal";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) txtNombre.requestFocus();
				} catch (ExisteCentroException e1) {
					String mensaje = "Solamente puede haber un único Centro";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) cbTipo.requestFocus();
				}catch (ExistePuertoException e1) {
					String mensaje = "Solamente puede haber un único Puerto";
					int confirmado = JOptionPane.showOptionDialog(this, mensaje, "ERROR", JOptionPane.OK_OPTION,
							JOptionPane.ERROR_MESSAGE, null, new Object[] { "Aceptar"}, "Aceptar");
					if(confirmado == 0) cbTipo.requestFocus();
				} catch (UpdateDBException e1) {
					String mensaje = "No se ha podido guardar la sucursal";
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
			String mensaje = "¿Deseas cancelar el alta de sucursal?";
			int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
			if (confirmado == 0) {
				ventana.setTitle("TP DIEDE 2023 - Menú Sucursal");
				ventana.setContentPane(panelPadre);
				ventana.setVisible(true);
			}
		});
	}
	
	private void mostrarMensajeSucursalAgregada() {
		String mensaje = "La sucursal "+txtNombre.getText()+" ha sido agregada correctamente. ¿Desea cargar otra sucursal?";
		int confirmado = JOptionPane.showOptionDialog(this, mensaje, "CONFIRMACION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "SI");
		if (confirmado == 0) {
			ventana.setContentPane(new AltaSucursal(ventana, panelPadre));
			ventana.setVisible(true);
		}else {
			ventana.setTitle("TP DIEDE 2023 - Menú Sucursal");
			ventana.setContentPane(panelPadre);
			ventana.setVisible(true);
		}
	}

	private boolean validarDatosObligatorios() {
		if(txtNombre.getText().isBlank() || txtHorarioApertura.getText().isBlank() 
				|| txtHorarioCierre.getText().isBlank()) return false;
		return true;
	}
}
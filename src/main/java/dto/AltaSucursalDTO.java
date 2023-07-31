package dto;

import dominio.EstadoSucursal;
import dominio.TipoSucursal;

public class AltaSucursalDTO {
	private String nombre;
	private String horarioApertura;
	private String horarioCierre;
	private EstadoSucursal estado;
	private TipoSucursal tipoSucursal;
	
	public AltaSucursalDTO(String nombre, String horarioApertura, String horarioCierre, EstadoSucursal estado,
			TipoSucursal tipoSucursal) {
		super();
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
		this.tipoSucursal = tipoSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public String getHorarioApertura() {
		return horarioApertura;
	}

	public String getHorarioCierre() {
		return horarioCierre;
	}

	public EstadoSucursal getEstado() {
		return estado;
	}

	public TipoSucursal getTipoSucursal() {
		return tipoSucursal;
	}
}
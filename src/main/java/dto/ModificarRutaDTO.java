package dto;

public class ModificarRutaDTO {
	private Integer idRuta;
	private Double capacidad;
	private Double duracion;
	private String estado;
	private Integer idSucursalOrigen;
	private Integer idSucursalDestino;
	
	public ModificarRutaDTO(Integer idRuta, Double capacidad, Double duracion, String estado, Integer idSucursalOrigen,
			Integer idSucursalDestino) {
		super();
		this.idRuta = idRuta;
		this.capacidad = capacidad;
		this.duracion = duracion;
		this.estado = estado;
		this.idSucursalOrigen = idSucursalOrigen;
		this.idSucursalDestino = idSucursalDestino;
	}

	public Integer getIdRuta() {
		return idRuta;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public Double getDuracion() {
		return duracion;
	}

	public String getEstado() {
		return estado;
	}

	public Integer getIdSucursalOrigen() {
		return idSucursalOrigen;
	}

	public Integer getIdSucursalDestino() {
		return idSucursalDestino;
	}
}
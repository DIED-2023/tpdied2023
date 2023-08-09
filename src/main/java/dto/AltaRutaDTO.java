package dto;

public class AltaRutaDTO {
	private Integer idSucursalOrigen;
	private Integer idSucursalDestino;
	private Double capacidad;
	private Double duracion;
	private String estado;
	
	public AltaRutaDTO(Integer idSucursalOrigen, Integer idSucursalDestino, Double capacidad, Double duracion,
			String estado) {
		super();
		this.idSucursalOrigen = idSucursalOrigen;
		this.idSucursalDestino = idSucursalDestino;
		this.capacidad = capacidad;
		this.duracion = duracion;
		this.estado = estado;
	}

	public Integer getIdSucursalOrigen() {
		return idSucursalOrigen;
	}

	public Integer getIdSucursalDestino() {
		return idSucursalDestino;
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
}
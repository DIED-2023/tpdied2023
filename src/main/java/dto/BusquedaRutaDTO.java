package dto;

public class BusquedaRutaDTO {
	private Integer idRuta;
	private Double capacidad;
	private Double duracion;
	private String estado;
	private Integer idSucursalOrigen;
	private String sucursalOrigen;
	private Integer idSucursalDestino;
	private String sucursalDestino;

	public Integer getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Integer getIdSucursalOrigen() {
		return idSucursalOrigen;
	}

	public void setIdSucursalOrigen(Integer idSucursalOrigen) {
		this.idSucursalOrigen = idSucursalOrigen;
	}
	
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public Integer getIdSucursalDestino() {
		return idSucursalDestino;
	}
	
	public void setIdSucursalDestino(Integer idSucursalDestino) {
		this.idSucursalDestino = idSucursalDestino;
	}
	
	public String getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(String sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}
}
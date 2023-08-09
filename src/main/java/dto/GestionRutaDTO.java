package dto;

public class GestionRutaDTO {
	private Integer id;
	private String sucursalOrigen;
	private String sucursalDestino;
	private Double capacidad;
	private Double duracion;
	private String estado;
	
	public GestionRutaDTO(Integer id, String sucursalOrigen, String sucursalDestino, Double capacidad, Double duracion,
			String estado) {
		super();
		this.id = id;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.capacidad = capacidad;
		this.duracion = duracion;
		this.estado = estado;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}
	public String getSucursalDestino() {
		return sucursalDestino;
	}
	public void setSucursalDestino(String sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
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
}
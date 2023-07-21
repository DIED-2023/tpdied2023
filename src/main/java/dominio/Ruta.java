package dominio;

public class Ruta {
	private Integer id;
	private Sucursal origen;
	private Sucursal destino;
	private Double capacidad;
	private Double duracion;
	private EstadoRuta estado;

	public Ruta(Integer id, Sucursal origen, Sucursal destino, Double capacidad, Double duracion, EstadoRuta estado) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
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

	public Sucursal getOrigen() {
		return origen;
	}

	public void setOrigen(Sucursal origen) {
		this.origen = origen;
	}

	public Sucursal getDestino() {
		return destino;
	}

	public void setDestino(Sucursal destino) {
		this.destino = destino;
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

	public EstadoRuta getEstado() {
		return estado;
	}

	public void setEstado(EstadoRuta estado) {
		this.estado = estado;
	}

}

package dto;

public class BusquedaSucursalDTO {
	private Integer id;
	private String nombre;
	private String horarioApertura;
	private String horarioCierre;
	private String estado;
	private String tipo;

	public Integer getId() {
		return id;
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

	public String getEstado() {
		return estado;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorarioApertura(String horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public void setHorarioCierre(String horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
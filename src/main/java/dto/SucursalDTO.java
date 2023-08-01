package dto;

public class SucursalDTO {
	private String nombre;
	private String horarioApertura;
	private String horarioCierre;
	private String estado;

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
}
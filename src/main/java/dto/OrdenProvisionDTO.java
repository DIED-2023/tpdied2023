package dto;

public class OrdenProvisionDTO {
	private Integer id;
	private String nombre;
	private String fecha;
	private Double tiempo;
	
	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getTiempo() {
		return tiempo;
	}
	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}
}
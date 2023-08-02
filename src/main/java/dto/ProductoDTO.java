package dto;

public class ProductoDTO {
	private String nombre;
	private String descripcion;
	private String precioUnitario;
	private String pesoKg;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getPesoKg() {
		return pesoKg;
	}
	public void setPesoKg(String pesoKg) {
		this.pesoKg = pesoKg;
	}
}
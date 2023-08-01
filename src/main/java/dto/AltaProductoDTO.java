package dto;


public class AltaProductoDTO {
	private String nombre;
	private String descripcion;
	private Double precioUnitario;
	private Double pesoKg;
	public AltaProductoDTO(String nombre, String descripcion, Double precioUnitario, Double pesoKg) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.pesoKg = pesoKg;
	}
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
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Double getPesoKg() {
		return pesoKg;
	}
	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

	
	
}

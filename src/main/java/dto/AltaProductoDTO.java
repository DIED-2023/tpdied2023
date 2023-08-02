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
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public Double getPesoKg() {
		return pesoKg;
	}
}
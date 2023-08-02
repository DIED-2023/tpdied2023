package dto;

public class ModificarProductoDTO {
	private Integer id;
	private String nombre;
	private String nombreAnterior;
	private String descripcion;
	private Double precioUnitario;
	private Double pesoKg;
	
	public ModificarProductoDTO(Integer id, String nombre, String nombreAnterior, String descripcion,
			Double precioUnitario, Double pesoKg) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreAnterior = nombreAnterior;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.pesoKg = pesoKg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreAnterior() {
		return nombreAnterior;
	}
	public void setNombreAnterior(String nombreAnterior) {
		this.nombreAnterior = nombreAnterior;
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

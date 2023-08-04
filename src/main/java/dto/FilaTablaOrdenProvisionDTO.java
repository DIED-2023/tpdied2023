package dto;

public class FilaTablaOrdenProvisionDTO {
	private Integer idProducto;
	private Integer cantidad;
	
	public FilaTablaOrdenProvisionDTO(Integer idProducto, Integer cantidad) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}	
}
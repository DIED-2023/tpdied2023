package dto;

public class BusquedaOrdenProvisionProductoDTO {
	private String producto;
	private Integer cantidad;

	public String getProducto() {
		return producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
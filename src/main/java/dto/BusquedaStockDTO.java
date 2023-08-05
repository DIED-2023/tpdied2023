package dto;

import java.util.List;

public class BusquedaStockDTO {
	private String sucursal;
	private List<BusquedaStockProductoDTO> productos;
	
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public List<BusquedaStockProductoDTO> getProductos() {
		return productos;
	}
	public void setProductos(List<BusquedaStockProductoDTO> productos) {
		this.productos = productos;
	}
}
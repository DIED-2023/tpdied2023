package dto;

public class EliminarStockDTO {
	private Integer idSucursal;
	private String producto;

	public EliminarStockDTO(Integer idSucursal, String producto) {
		super();
		this.idSucursal = idSucursal;
		this.producto = producto;
	}

	public Integer getSucursal() {
		return idSucursal;
	}

	public String getProducto() {
		return producto;
	}
}
package dominio;

public class StockProducto {
	private Integer id;
	private Integer cantidad;
	private Producto producto;

	public StockProducto(Integer id, Integer cantidad, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}

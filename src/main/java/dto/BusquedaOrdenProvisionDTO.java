package dto;

import java.util.Date;
import java.util.List;

public class BusquedaOrdenProvisionDTO {
	private Integer idOrden;
	private Date fecha;
	private Double tiempo;
	private String sucursal;
	private List<BusquedaOrdenProvisionProductoDTO> productos;
	public Integer getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getTiempo() {
		return tiempo;
	}
	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public List<BusquedaOrdenProvisionProductoDTO> getProductos() {
		return productos;
	}
	public void setProductos(List<BusquedaOrdenProvisionProductoDTO> productos) {
		this.productos = productos;
	}
}
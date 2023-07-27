package dominio;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrdenProvision {
	private Integer id;
	private Instant fecha;
	private EstadoOrden estado;
	private Double tiempo;
	private Sucursal destino;
	private List<CantidadProducto> cantProductos = new ArrayList<CantidadProducto>();
	
	public OrdenProvision(Integer id, Instant fecha, EstadoOrden estado, Double tiempo,
			Sucursal destino,List<CantidadProducto> cantProductos) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.estado = estado;
		this.tiempo = tiempo;
		this.destino = destino;
		this.cantProductos = cantProductos;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Instant getFecha() {
		return fecha;
	}
	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}
	public EstadoOrden getEstado() {
		return estado;
	}
	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}
	public Double getTiempo() {
		return tiempo;
	}
	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}
	public void setDestino(Sucursal destino) {
		this.destino = destino;
	}
	public Sucursal getDestino() {
		return destino;
	}
	public List<CantidadProducto> getCantProductos() {
		return cantProductos;
	}
	public void setCantProductos(List<CantidadProducto> cantProductos) {
		this.cantProductos = cantProductos;
	}	
}
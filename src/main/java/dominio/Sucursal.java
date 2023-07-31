package dominio;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	private Integer id;
	private String nombre;
	private String horarioApertura;
	private String horarioCierre;
	private EstadoSucursal estado;
	private TipoSucursal tipo;
	private List<StockProducto> stock = new ArrayList<StockProducto>();
	
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
	public String getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(String horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	public String getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(String horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	public EstadoSucursal getEstado() {
		return estado;
	}
	public void setEstado(EstadoSucursal estado) {
		this.estado = estado;
	}
	public TipoSucursal getTipo() {
		return tipo;
	}
	public void setTipo(TipoSucursal tipo) {
		this.tipo = tipo;
	}
	public List<StockProducto> getStock() {
		return stock;
	}
	public void setStock(List<StockProducto> stock) {
		this.stock = stock;
	}	
}
package dominio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	private Integer id;
	private String nombre;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private EstadoSucursal estado;
	private TipoSucursal tipo;
	private List<StockProducto> stock = new ArrayList<StockProducto>();
	public Sucursal(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre, EstadoSucursal estado,
			TipoSucursal tipo, List<StockProducto> stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
		this.tipo = tipo;
		this.stock = stock;
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
	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(LocalTime horarioCierre) {
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

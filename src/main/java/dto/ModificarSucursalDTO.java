package dto;

public class ModificarSucursalDTO {
	private Integer id;
	private String nombre;
	private String nombreAnterior;
	private String horarioApertura;
	private String horarioCierre;
	private String estado;
	private String tipoSucursal;
	private String tipoSucursalAnterior;
	
	public ModificarSucursalDTO(Integer id,String nombre, String nombreAnterior,String horarioApertura, String horarioCierre, String estado,
			String tipoSucursal, String tipoSucursalAnterior) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreAnterior = nombreAnterior;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
		this.tipoSucursal = tipoSucursal;
		this.tipoSucursalAnterior = tipoSucursalAnterior;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getNombreAnterior() {
		return nombreAnterior;
	}
	
	public String getHorarioApertura() {
		return horarioApertura;
	}

	public String getHorarioCierre() {
		return horarioCierre;
	}

	public String getEstado() {
		return estado;
	}

	public String getTipoSucursal() {
		return tipoSucursal;
	}
	
	public String getTipoSucursalAnterior() {
		return tipoSucursalAnterior;
	}
}
package dto;

import java.util.Date;
import java.util.List;

public class AltaOrdenProvisionDTO {
	private Integer idSucursal;
	private Date fecha;
	private String tiempo;
	private List<FilaTablaOrdenProvisionDTO> filasTabla;
	
	public AltaOrdenProvisionDTO(Integer idSucursal, Date fecha, String tiempo,
			List<FilaTablaOrdenProvisionDTO> filasTabla) {
		super();
		this.idSucursal = idSucursal;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.filasTabla = filasTabla;
	}

	public Integer getSucursal() {
		return idSucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getTiempo() {
		return tiempo;
	}

	public List<FilaTablaOrdenProvisionDTO> getFilasTabla() {
		return filasTabla;
	}
}
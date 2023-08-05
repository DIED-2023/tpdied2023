package dto;

import java.util.List;

public class ModificarStockDTO {
	private Integer idSucursal;
	private List<FilaTablaOrdenProvisionDTO> filasTabla;
	
	public ModificarStockDTO(Integer idSucursal, List<FilaTablaOrdenProvisionDTO> filasTabla) {
		super();
		this.idSucursal = idSucursal;
		this.filasTabla = filasTabla;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public List<FilaTablaOrdenProvisionDTO> getFilasTabla() {
		return filasTabla;
	}
}
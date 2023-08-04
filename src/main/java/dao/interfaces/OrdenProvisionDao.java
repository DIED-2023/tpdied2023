package dao.interfaces;

import java.util.List;

import dto.AltaOrdenProvisionDTO;
import dto.BusquedaOrdenProvisionDTO;
import dto.ModificarOrdenProvisionDTO;
import dto.OrdenProvisionDTO;
import excepciones.UpdateDBException;

public interface OrdenProvisionDao {
	public void guardar(AltaOrdenProvisionDTO dto) throws UpdateDBException;
	public List<OrdenProvisionDTO> getOrdenesByIdSucursal(Integer idSucursal);
	public void deleteOrdenProvision(Integer idOrden)throws UpdateDBException;
	public void updateOrdenProvision(ModificarOrdenProvisionDTO dto) throws UpdateDBException;
	public BusquedaOrdenProvisionDTO getOrdenById(Integer idOrden);
}
package dao.interfaces;

import java.util.List;

import dto.AltaRutaDTO;
import dto.BusquedaRutaDTO;
import dto.GestionRutaDTO;
import dto.ModificarRutaDTO;
import excepciones.UpdateDBException;

public interface RutaDao {
	public void guardar(AltaRutaDTO dto) throws UpdateDBException;
	public List<GestionRutaDTO> getAllRutas();
	public List<GestionRutaDTO> getRutasBySucursalOrigen(String sucursal);
	public List<GestionRutaDTO> getRutasBySucursalDestino(String sucursal);
	public List<GestionRutaDTO> getRutasBySucursales(String origen, String destino);
	public void deleteRuta(Integer idRuta) throws UpdateDBException;
	public BusquedaRutaDTO getRutaById(Integer idRuta);
	public void updateRuta(ModificarRutaDTO dto) throws UpdateDBException;
}
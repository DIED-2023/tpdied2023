package gestores;

import java.util.ArrayList;
import java.util.List;

import dao.factory.FactoryDao;
import dao.interfaces.RutaDao;
import dao.interfaces.SucursalDao;
import dto.AltaRutaDTO;
import dto.BusquedaRutaDTO;
import dto.GestionRutaDTO;
import dto.ModificarRutaDTO;
import dto.SucursalComboBoxDTO;
import excepciones.NoExisteDestinoRutaException;
import excepciones.NoExisteOrigenRutaException;
import excepciones.NoExisteRutaException;
import excepciones.UpdateDBException;

public final class GestorRuta {
	private static GestorRuta instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private RutaDao rutaDao = factory.getRutaDao();
	private SucursalDao sucursalDao = factory.getSucursalDao();
	
	private GestorRuta() {
		
	}
	
	public synchronized static GestorRuta getInstancia() {
		if(instancia == null) instancia = new GestorRuta();
		return instancia;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public List<SucursalComboBoxDTO> listaSucursales(){
		List<SucursalComboBoxDTO> sucursales = sucursalDao.getSucursales();
		return sucursales;
	}
	
	public void altaRuta(AltaRutaDTO dto) throws UpdateDBException{
		rutaDao.guardar(dto);
	}

	public List<GestionRutaDTO> buscarRutasPorOrigenDestino(String origen, String destino) throws NoExisteOrigenRutaException,NoExisteDestinoRutaException,NoExisteRutaException{
		List<GestionRutaDTO> rutas = new ArrayList<GestionRutaDTO>();
		if(origen.isBlank() && destino.isBlank()) {
			rutas = rutaDao.getAllRutas();
		}else if(!origen.isBlank() && destino.isBlank()) {
			boolean existeSucursal = sucursalDao.existeSucursal(origen);
			if(existeSucursal) {
				rutas = rutaDao.getRutasBySucursalOrigen(origen);
			}else throw new NoExisteOrigenRutaException();
		}else if(!destino.isBlank() && origen.isBlank()) {
			boolean existeSucursal = sucursalDao.existeSucursal(destino);
			if(existeSucursal) {
				rutas = rutaDao.getRutasBySucursalDestino(destino);
			}else throw new NoExisteDestinoRutaException();
		}else {
			boolean existenOrigenDestino = sucursalDao.existeSucursal(origen) && sucursalDao.existeSucursal(destino);
			if(existenOrigenDestino) {
				rutas = rutaDao.getRutasBySucursales(origen,destino);
			}else throw new NoExisteRutaException();
		}
		return rutas;
	}
	
	public void eliminarRuta(Integer idRuta) throws UpdateDBException{
		rutaDao.deleteRuta(idRuta);
	}
	
	public BusquedaRutaDTO buscarRutaById(Integer idRuta) {
		return rutaDao.getRutaById(idRuta);
	}
	
	public void modificarRuta(ModificarRutaDTO dto) throws UpdateDBException{
		rutaDao.updateRuta(dto);
	}
}
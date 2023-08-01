package gestores;

import java.util.ArrayList;
import java.util.List;

import dao.factory.FactoryDao;
import dao.interfaces.SucursalDao;
import dominio.Sucursal;
import dominio.TipoSucursal;
import dto.AltaSucursalDTO;
import dto.BusquedaSucursalDTO;
import dto.ModificarSucursalDTO;
import dto.SucursalDTO;
import excepciones.ExisteCentroException;
import excepciones.ExistePuertoException;
import excepciones.ExisteSucursalException;
import excepciones.UpdateDBException;

public final class GestorSucursal {
	private static GestorSucursal instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private SucursalDao sucursalDao = factory.getSucursalDao();
	
	private GestorSucursal() {
		
	}
	
	public synchronized static GestorSucursal getInstancia() {
		if(instancia == null) instancia = new GestorSucursal();
		return instancia;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public void altaSucursal(AltaSucursalDTO altaSucursalDto) throws ExisteSucursalException, ExistePuertoException, ExisteCentroException, UpdateDBException{
		boolean existeSucursal = sucursalDao.existeSucursal(altaSucursalDto.getNombre());
		if(existeSucursal) throw new ExisteSucursalException();
		boolean existePuerto = sucursalDao.existePuerto();
		if(existePuerto && altaSucursalDto.getTipoSucursal() == TipoSucursal.PUERTO) throw new ExistePuertoException();
		boolean existeCentro = sucursalDao.existeCentro();
		if(existeCentro && altaSucursalDto.getTipoSucursal() == TipoSucursal.CENTRO) throw new ExisteCentroException();
		Sucursal s = new Sucursal();
		s.setNombre(altaSucursalDto.getNombre());
		s.setHorarioApertura(altaSucursalDto.getHorarioApertura());
		s.setHorarioCierre(altaSucursalDto.getHorarioCierre());
		s.setEstado(altaSucursalDto.getEstado());
		s.setTipo(altaSucursalDto.getTipoSucursal());
		sucursalDao.guardar(s);
	}
	
	//El nombre puede ser una parte o completo o vacio
	public List<SucursalDTO> buscarSucursalesPorNombre(String nombre) {
		List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
		sucursales = sucursalDao.getSucursalesPorNombre(nombre);
		return sucursales;
	}
	
	public void eliminarSucursal(String nombre) throws UpdateDBException{
		sucursalDao.deleteSucursal(nombre);
	}
	
	public BusquedaSucursalDTO getSucursal(String nombre) {
		return sucursalDao.getSucursalByNombre(nombre);
	}
	
	public void modificarSucursal(ModificarSucursalDTO dto) throws ExisteSucursalException, ExistePuertoException, ExisteCentroException, UpdateDBException {
		boolean existeSucursal = false;
		boolean existePuerto = false;
		boolean existeCentro = false;
		if(!dto.getNombreAnterior().equals(dto.getNombre())) {
			existeSucursal = sucursalDao.existeSucursal(dto.getNombre()); 
		}
		if(existeSucursal) throw new ExisteSucursalException();
		if(!dto.getTipoSucursalAnterior().equals(dto.getTipoSucursal())) {
			existePuerto = sucursalDao.existePuerto();
			existeCentro = sucursalDao.existeCentro();
		}
		if(existePuerto && TipoSucursal.valueOf(dto.getTipoSucursal()) == TipoSucursal.PUERTO) throw new ExistePuertoException();
		if(existeCentro && TipoSucursal.valueOf(dto.getTipoSucursal()) == TipoSucursal.CENTRO) throw new ExisteCentroException();
		sucursalDao.updateSucursal(dto);
	}
}
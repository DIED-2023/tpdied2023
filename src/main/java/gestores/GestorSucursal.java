package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.SucursalDao;
import dominio.Sucursal;
import dominio.TipoSucursal;
import dto.AltaSucursalDTO;
import excepciones.ExisteCentroException;
import excepciones.ExistePuertoException;
import excepciones.ExisteSucursalException;

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
	
	public void altaSucursal(AltaSucursalDTO altaSucursalDto) throws ExisteSucursalException, ExistePuertoException, ExisteCentroException{
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
}
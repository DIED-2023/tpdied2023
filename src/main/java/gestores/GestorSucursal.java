package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.SucursalDao;

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
}
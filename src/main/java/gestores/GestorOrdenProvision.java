package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.OrdenProvisionDao;

public final class GestorOrdenProvision {
	private static GestorOrdenProvision instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private OrdenProvisionDao ordenProvisionDao = factory.getOrdenProvisionDao();
	
	private GestorOrdenProvision() {
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
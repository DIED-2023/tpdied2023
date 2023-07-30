package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.RutaDao;

public final class GestorRuta {
	private static GestorRuta instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private RutaDao rutaDao = factory.getRutaDao();
	
	private GestorRuta() {
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.StockDao;

public final class GestorStock {
	private static GestorStock instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private StockDao stockDao = factory.getStockDao();
	
	private GestorStock() {
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
package dao.factory;

import dao.interfaces.OrdenProvisionDao;
import dao.interfaces.ProductoDao;
import dao.interfaces.RutaDao;
import dao.interfaces.StockDao;
import dao.interfaces.SucursalDao;

public abstract class FactoryDao {
	public static final int PG_FACTORY = 1; 

	public static FactoryDao getFactory(int claveFactory) {
		switch(claveFactory) {
		case PG_FACTORY:
			return new PGFactoryDao();
		default: 
			return null;
		}
	}
	
	public abstract SucursalDao getSucursalDao();
	public abstract ProductoDao getProductoDao();
	public abstract RutaDao getRutaDao();
	public abstract StockDao getStockDao();
	public abstract OrdenProvisionDao getOrdenProvisionDao();
}
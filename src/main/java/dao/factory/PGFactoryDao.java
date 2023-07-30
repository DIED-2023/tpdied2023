package dao.factory;

import dao.interfaces.OrdenProvisionDao;
import dao.interfaces.ProductoDao;
import dao.interfaces.RutaDao;
import dao.interfaces.StockDao;
import dao.interfaces.SucursalDao;
import dao.interfaces.implementaciones.OrdenProvisionPGDao;
import dao.interfaces.implementaciones.ProductoPGDao;
import dao.interfaces.implementaciones.RutaPGDao;
import dao.interfaces.implementaciones.StockPGDao;
import dao.interfaces.implementaciones.SucursalPGDao;

public class PGFactoryDao extends FactoryDao {

	@Override
	public SucursalDao getSucursalDao() {
		return new SucursalPGDao();
	}

	@Override
	public ProductoDao getProductoDao() {
		return new ProductoPGDao();
	}

	@Override
	public RutaDao getRutaDao() {
		return new RutaPGDao();
	}

	@Override
	public StockDao getStockDao() {
		return new StockPGDao();
	}

	@Override
	public OrdenProvisionDao getOrdenProvisionDao() {
		return new OrdenProvisionPGDao();
	}

}
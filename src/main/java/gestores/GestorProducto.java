package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.ProductoDao;

public final class GestorProducto {
	private static GestorProducto instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private ProductoDao productoDao = factory.getProductoDao();
	
	private GestorProducto() {
		
	}
	
	public static GestorProducto getInstancia() {
		if(instancia == null) instancia = new GestorProducto();
		return instancia;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
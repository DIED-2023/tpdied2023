package gestores;

import java.util.ArrayList;
import java.util.List;

import dao.factory.FactoryDao;
import dao.interfaces.ProductoDao;
import dao.interfaces.StockDao;
import dao.interfaces.SucursalDao;
import dto.AltaStockDTO;
import dto.BusquedaStockDTO;
import dto.EliminarStockDTO;
import dto.ModificarStockDTO;
import dto.ProductoComboBoxDTO;
import dto.StockDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;

public final class GestorStock {
	private static GestorStock instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private StockDao stockDao = factory.getStockDao();
	private SucursalDao sucursalDao = factory.getSucursalDao();
	private ProductoDao productoDao = factory.getProductoDao();
	
	private GestorStock() {
		
	}
	
	public synchronized static GestorStock getInstancia() {
		if(instancia == null) instancia = new GestorStock();
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
	
	public List<ProductoComboBoxDTO> listaProductos(){
		List<ProductoComboBoxDTO> productos = productoDao.getProductos();
		return productos;
	}
	
	public void altaStock(AltaStockDTO dto) throws UpdateDBException{
		stockDao.guardar(dto);
	}
	
	public List<StockDTO> buscarStocksPorNombreSucursal(String nombre) {
		List<StockDTO> stocks = new ArrayList<StockDTO>();
		Integer idSucursal = null;
		if(sucursalDao.getSucursalByNombre(nombre).getId() != null) {
			idSucursal = sucursalDao.getSucursalByNombre(nombre).getId();
		}
		stocks = stockDao.getStockByIdSucursal(idSucursal);
		return stocks;
	}
	
	public void eliminarStock(EliminarStockDTO dto)throws UpdateDBException{
		stockDao.deleteStock(dto);
	}
	
	public BusquedaStockDTO buscarStockById(Integer idSucursal) {
		return stockDao.getStockById(idSucursal);
	}
	
	public void modificarStock(ModificarStockDTO dto) throws UpdateDBException{
		stockDao.updateStock(dto);
	}
}
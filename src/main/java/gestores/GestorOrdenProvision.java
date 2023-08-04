package gestores;

import java.util.ArrayList;
import java.util.List;

import dao.factory.FactoryDao;
import dao.interfaces.OrdenProvisionDao;
import dao.interfaces.ProductoDao;
import dao.interfaces.SucursalDao;
import dto.AltaOrdenProvisionDTO;
import dto.BusquedaOrdenProvisionDTO;
import dto.ModificarOrdenProvisionDTO;
import dto.OrdenProvisionDTO;
import dto.ProductoComboBoxDTO;
import dto.SucursalComboBoxDTO;
import excepciones.UpdateDBException;

public final class GestorOrdenProvision {
	private static GestorOrdenProvision instancia;
	private FactoryDao factory = FactoryDao.getFactory(FactoryDao.PG_FACTORY);
	private OrdenProvisionDao ordenProvisionDao = factory.getOrdenProvisionDao();
	private SucursalDao sucursalDao = factory.getSucursalDao();
	private ProductoDao productoDao = factory.getProductoDao();
	
	private GestorOrdenProvision() {
		
	}
	
	public synchronized static GestorOrdenProvision getInstancia() {
		if(instancia == null) instancia = new GestorOrdenProvision();
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
	
	public void altaOrdenProvision(AltaOrdenProvisionDTO dto) throws UpdateDBException{
		ordenProvisionDao.guardar(dto);
	}
	
	public List<OrdenProvisionDTO> buscarOrdenesPorNombreSucursal(String nombre){
		List<OrdenProvisionDTO> ordenes = new ArrayList<OrdenProvisionDTO>();
		Integer idSucursal = null;
		if(sucursalDao.getSucursalByNombre(nombre).getId() != null) {
			idSucursal = sucursalDao.getSucursalByNombre(nombre).getId();
		}
		ordenes = ordenProvisionDao.getOrdenesByIdSucursal(idSucursal);
		return ordenes;
	}
	
	public void eliminarOrdenProvision(Integer idOrden) throws UpdateDBException{
		ordenProvisionDao.deleteOrdenProvision(idOrden);
	}
	
	public BusquedaOrdenProvisionDTO buscarOrdenById(Integer idOrden) {
		return ordenProvisionDao.getOrdenById(idOrden);
	}
	
	public void modificarOrdenProvision(ModificarOrdenProvisionDTO dto) throws UpdateDBException{
		ordenProvisionDao.updateOrdenProvision(dto);
	}
}
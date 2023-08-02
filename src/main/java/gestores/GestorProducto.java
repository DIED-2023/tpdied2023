package gestores;

import dao.factory.FactoryDao;
import dao.interfaces.ProductoDao;
import dominio.Producto;
import dto.AltaProductoDTO;
import dto.ModificarProductoDTO;
import excepciones.ExisteProductoException;
import excepciones.ExisteSucursalException;
import excepciones.UpdateDBException;

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
	
	public void altaProducto(AltaProductoDTO altaProductoDto) throws ExisteProductoException, UpdateDBException{
		boolean existeProducto = productoDao.existeProducto(altaProductoDto.getNombre());
		if(existeProducto) throw new ExisteProductoException();
		Producto p = new Producto();
		p.setNombre(altaProductoDto.getNombre());
		p.setDescripcion(altaProductoDto.getDescripcion());
		p.setPrecioUnitario(altaProductoDto.getPrecioUnitario());
		p.setPesoKg(altaProductoDto.getPesoKg());
		productoDao.guardar(p);
	}
	
	public void modificarProducto(ModificarProductoDTO dto) throws ExisteSucursalException, UpdateDBException {
		boolean existeProducto = false;
		if(existeProducto) throw new ExisteSucursalException();
		if(!dto.getNombreAnterior().equals(dto.getNombre())) {
			existeProducto = productoDao.existeProducto(dto.getNombre()); 
		}
		productoDao.updateProducto(dto);
	}

	public void eliminarProducto(String nombre) throws UpdateDBException {
		productoDao.deleteProducto(nombre);
	}
}
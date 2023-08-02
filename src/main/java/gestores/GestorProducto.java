package gestores;

import java.util.ArrayList;
import java.util.List;

import dao.factory.FactoryDao;
import dao.interfaces.ProductoDao;
import dominio.Producto;
import dto.AltaProductoDTO;
import dto.BusquedaProductoDTO;
import dto.ModificarProductoDTO;
import dto.ProductoDTO;
import excepciones.ExisteProductoException;
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
	
	public void modificarProducto(ModificarProductoDTO dto) throws ExisteProductoException, UpdateDBException {
		boolean existeProducto = false;
		if(!dto.getNombreAnterior().equals(dto.getNombre())) {
			existeProducto = productoDao.existeProducto(dto.getNombre()); 
		}
		if(existeProducto) throw new ExisteProductoException();
		productoDao.updateProducto(dto);
	}

	public void eliminarProducto(String nombre) throws UpdateDBException {
		productoDao.deleteProducto(nombre);
	}
	
	//El nombre puede ser una parte o completo o vacio
	public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		productos = productoDao.getProductosPorNombre(nombre);
		return productos;
	}
	
	public BusquedaProductoDTO getProducto(String nombre) {
		return productoDao.getProductoByNombre(nombre);
	}
}
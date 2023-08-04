package dao.interfaces;

import java.util.List;

import dominio.Producto;
import dto.BusquedaProductoDTO;
import dto.ModificarProductoDTO;
import dto.ProductoComboBoxDTO;
import dto.ProductoDTO;
import excepciones.UpdateDBException;

public interface ProductoDao {
	public List<ProductoDTO> getProductosPorNombre(String nombre);
	public BusquedaProductoDTO getProductoByNombre(String nombre);
	public List<ProductoComboBoxDTO> getProductos();
	public boolean existeProducto(String nombre);
	public void guardar(Producto producto) throws UpdateDBException;
	public void updateProducto(ModificarProductoDTO dto) throws UpdateDBException;
	public void deleteProducto(String nombre) throws UpdateDBException;
}
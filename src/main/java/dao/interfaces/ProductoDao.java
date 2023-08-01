package dao.interfaces;

import dominio.Producto;
import dto.ModificarProductoDTO;
import excepciones.UpdateDBException;

public interface ProductoDao {
	public boolean existeProducto(String nombre);
	public void guardar(Producto producto) throws UpdateDBException;
	public void updateProducto(ModificarProductoDTO dto) throws UpdateDBException;
	public void deleteProducto(String nombre) throws UpdateDBException;
	
}
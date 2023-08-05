package dao.interfaces;

import java.util.List;

import dto.AltaStockDTO;
import dto.BusquedaStockDTO;
import dto.EliminarStockDTO;
import dto.ModificarStockDTO;
import dto.StockDTO;
import excepciones.UpdateDBException;

public interface StockDao {
	public void guardar(AltaStockDTO dto) throws UpdateDBException;
	public List<StockDTO> getStockByIdSucursal(Integer idSucursal);
	public void deleteStock(EliminarStockDTO dto) throws UpdateDBException;
	public BusquedaStockDTO getStockById(Integer idSucursal);
	public void updateStock(ModificarStockDTO dto) throws UpdateDBException;
}
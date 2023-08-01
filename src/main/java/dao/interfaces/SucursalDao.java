package dao.interfaces;

import java.util.List;

import dominio.Sucursal;
import dto.SucursalDTO;
import excepciones.UpdateDBException;

public interface SucursalDao {
	public List<SucursalDTO> getSucursalesPorNombre(String nombre);
	public void deleteSucursal(String nombre) throws UpdateDBException;
	public boolean existeSucursal(String nombre);
	public boolean existePuerto();
	public boolean existeCentro();
	public void guardar(Sucursal sucursal) throws UpdateDBException;
}
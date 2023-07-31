package dao.interfaces;

import dominio.Sucursal;

public interface SucursalDao {
	public boolean existeSucursal(String nombre);
	public boolean existePuerto();
	public boolean existeCentro();
	public void guardar(Sucursal sucursal);
}
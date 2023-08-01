package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.ProductoDao;
import dominio.Producto;
import dto.ModificarProductoDTO;
import excepciones.PGException;
import excepciones.UpdateDBException;
import utils.ConexionDB;

public class ProductoPGDao implements ProductoDao {

	@Override
	public boolean existeProducto(String nombre) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM producto WHERE nombre = ?");
			pstm.setString(1,nombre);
			rs = pstm.executeQuery();
			if(rs.next()) existe = true;
		}catch (PGException | SQLException e) {} 
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		return existe;
	}

	@Override
	public void guardar(Producto producto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO producto (nombre,descripcion,precio_unitario,peso_kg) VALUES (?,?,?,?)");
			pstm.setString(1, producto.getNombre());
			pstm.setString(2, producto.getDescripcion());
			pstm.setString(3, producto.getPrecioUnitario().toString());
			pstm.setString(4, producto.getPesoKg().toString());
			pstm.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	@Override
	public void updateProducto(ModificarProductoDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio_unitario = ?, peso_kg = ? WHERE id = ?");
			pstm.setString(1, dto.getNombre());
			pstm.setString(2, dto.getDescripcion());
			pstm.setString(3, dto.getPrecioUnitario().toString());
			pstm.setString(4, dto.getPesoKg().toString());
			pstm.setInt(6, dto.getId());
			pstm.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	@Override
	public void deleteProducto(String nombre) throws UpdateDBException {
	
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("DELETE FROM producto WHERE nombre = ?");
			pstm.setString(1, nombre);
			pstm.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		
	}
		
	}


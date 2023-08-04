package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.ProductoDao;
import dominio.Producto;
import dto.BusquedaProductoDTO;
import dto.ModificarProductoDTO;
import dto.ProductoComboBoxDTO;
import dto.ProductoDTO;
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
			pstm.setDouble(3, producto.getPrecioUnitario());
			pstm.setDouble(4, producto.getPesoKg());
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
			pstm.setDouble(3, dto.getPrecioUnitario());
			pstm.setDouble(4, dto.getPesoKg());
			pstm.setInt(5, dto.getId());
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

	@Override
	public List<ProductoDTO> getProductosPorNombre(String nombre) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM producto WHERE nombre like ?");
			pstm.setString(1, nombre+"%"); //El % actua como comodin, si no se ingresó nada, devuelve todas las sucursales
			rs = pstm.executeQuery();
			while(rs.next()) {
				ProductoDTO p = new ProductoDTO();
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecioUnitario(String.valueOf(rs.getDouble("precio_unitario")));
				p.setPesoKg(String.valueOf(rs.getDouble("peso_kg")));
				productos.add(p);
			}
		} catch (SQLException | PGException e) {}
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
		return productos;
	}

	@Override
	public BusquedaProductoDTO getProductoByNombre(String nombre) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BusquedaProductoDTO producto = new BusquedaProductoDTO();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM producto WHERE nombre = ?");
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			while(rs.next()) {
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecioUnitario(rs.getDouble("precio_unitario"));
				producto.setPesoKg(rs.getDouble("peso_kg"));
			}
		} catch (SQLException | PGException e) {}
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
		return producto;
	}

	@Override
	public List<ProductoComboBoxDTO> getProductos() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ProductoComboBoxDTO> productos = new ArrayList<ProductoComboBoxDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT id,nombre FROM producto");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				ProductoComboBoxDTO p = new ProductoComboBoxDTO(id,nombre);
				productos.add(p);
			}
		} catch (SQLException | PGException e) {}
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
		return productos;
	}		
}
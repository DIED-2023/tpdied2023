package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.StockDao;
import dto.AltaStockDTO;
import dto.BusquedaStockDTO;
import dto.BusquedaStockProductoDTO;
import dto.EliminarStockDTO;
import dto.FilaTablaOrdenProvisionDTO;
import dto.ModificarStockDTO;
import dto.StockDTO;
import excepciones.PGException;
import excepciones.UpdateDBException;
import utils.ConexionDB;

public class StockPGDao implements StockDao {

	@Override
	public void guardar(AltaStockDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO stock (id_sucursal,id_producto,cantidad) VALUES (?,?,?)");
			for(FilaTablaOrdenProvisionDTO f : dto.getFilasTabla()) {
				pstm.setInt(1,dto.getIdSucursal());
				pstm.setInt(2, f.getIdProducto());
				pstm.setInt(3, f.getCantidad());
				pstm.executeUpdate();
			}
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
	public List<StockDTO> getStockByIdSucursal(Integer idSucursal) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<StockDTO> stocks = new ArrayList<StockDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT p.nombre,s.cantidad FROM stock s,producto p WHERE s.id_producto = p.id and s.id_sucursal = ?");
			pstm.setInt(1, idSucursal);
			rs = pstm.executeQuery();
			while(rs.next()) {
				StockDTO s = new StockDTO();
				s.setProducto(rs.getString("nombre"));
				s.setCantidad(rs.getInt("cantidad"));
				stocks.add(s);
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
		return stocks;
	}

	@Override
	public void deleteStock(EliminarStockDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("SELECT s.id_producto FROM stock s,producto p WHERE s.id_producto = p.id and s.id_sucursal = ? and p.nombre = ?");
			pstm.setInt(1, dto.getSucursal());
			pstm.setString(2, dto.getProducto());
			rs = pstm.executeQuery();
			rs.next();
			pstm1 = conn.prepareStatement("DELETE FROM stock WHERE id_sucursal = ? and id_producto = ?");
			pstm1.setInt(1, dto.getSucursal());
			pstm1.setInt(2, rs.getInt("id_producto"));
			pstm1.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstm != null && pstm1 != null) {
				try {
					pstm.close();
					pstm1.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	@Override
	public BusquedaStockDTO getStockById(Integer idSucursal) {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		BusquedaStockDTO stock = new BusquedaStockDTO();
		List<BusquedaStockProductoDTO> productos = new ArrayList<BusquedaStockProductoDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT nombre FROM sucursal WHERE id = ?");
			pstm.setInt(1, idSucursal);
			rs = pstm.executeQuery();
			rs.next();
			stock.setSucursal(rs.getString("nombre"));
			pstm1 = conn.prepareStatement("SELECT p.nombre,s.cantidad FROM stock s,producto p WHERE s.id_sucursal = ? and p.id = s.id_producto");
			pstm1.setInt(1, idSucursal);
			rs1 = pstm1.executeQuery();
			while(rs1.next()) {
				BusquedaStockProductoDTO p = new BusquedaStockProductoDTO();
				p.setProducto(rs1.getString("nombre"));
				p.setCantidad(rs1.getInt("cantidad"));
				productos.add(p);
			}
			stock.setProductos(productos);
		} catch (SQLException | PGException e) {}
		finally {
			if(rs != null && rs1 != null) {
				try {
					rs.close();
					rs1.close();
				}catch(SQLException e) {}
			}
			if(pstm != null && pstm1 != null) {
				try {
					pstm.close();
					pstm.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		return stock;
	}

	@Override
	public void updateStock(ModificarStockDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm1 = conn.prepareStatement("DELETE FROM stock WHERE id_sucursal = ?");
			pstm1.setInt(1, dto.getIdSucursal());
			pstm1.executeUpdate();
			pstm2 = conn.prepareStatement("INSERT INTO stock (id_sucursal,id_producto,cantidad) VALUES (?,?,?)");
			for(FilaTablaOrdenProvisionDTO f : dto.getFilasTabla()) {
				pstm2.setInt(1,dto.getIdSucursal());
				pstm2.setInt(2, f.getIdProducto());
				pstm2.setInt(3, f.getCantidad());
				pstm2.executeUpdate();
			}
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(pstm1 != null && pstm2 != null) {
				try {
					pstm1.close();
					pstm2.close();
				}catch(SQLException e) {}
			}if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}
}
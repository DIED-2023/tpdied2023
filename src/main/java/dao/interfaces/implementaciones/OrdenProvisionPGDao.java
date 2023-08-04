package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.OrdenProvisionDao;
import dto.AltaOrdenProvisionDTO;
import dto.BusquedaOrdenProvisionDTO;
import dto.BusquedaOrdenProvisionProductoDTO;
import dto.FilaTablaOrdenProvisionDTO;
import dto.ModificarOrdenProvisionDTO;
import dto.OrdenProvisionDTO;
import excepciones.PGException;
import excepciones.UpdateDBException;
import utils.ConexionDB;

public class OrdenProvisionPGDao implements OrdenProvisionDao {

	@Override
	public void guardar(AltaOrdenProvisionDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO orden_provision (fecha,estado,tiempo,id_sucursal) VALUES (?,?,?,?)");
			pstm.setDate(1,new Date(dto.getFecha().getTime()));
			pstm.setString(2, "PENDIENTE");
			pstm.setDouble(3, Double.parseDouble(dto.getTiempo()));
			pstm.setInt(4, dto.getSucursal());
			pstm.executeUpdate();
			pstm1 = conn.prepareStatement("SELECT id FROM orden_provision WHERE fecha = ? and estado = 'PENDIENTE' and tiempo = ? and id_sucursal = ?");
			pstm1.setDate(1, new Date(dto.getFecha().getTime()));
			pstm1.setDouble(2, Double.parseDouble(dto.getTiempo()));
			pstm1.setInt(3, dto.getSucursal());
			rs = pstm1.executeQuery();
			rs.next();
			int idOrden = rs.getInt("id");
			pstm2 = conn.prepareStatement("INSERT INTO cantidad_producto (id_orden_provision,id_producto,cantidad) VALUES (?,?,?)");
			for(FilaTablaOrdenProvisionDTO f : dto.getFilasTabla()) {
				pstm2.setInt(1,idOrden);
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
			if(pstm != null && pstm1 != null && pstm2 != null) {
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
	public List<OrdenProvisionDTO> getOrdenesByIdSucursal(Integer idSucursal) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<OrdenProvisionDTO> ordenes = new ArrayList<OrdenProvisionDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT o.id,o.fecha,o.tiempo,o.id_sucursal,s.nombre FROM orden_provision o,sucursal s WHERE o.id_sucursal=s.id and o.estado = 'PENDIENTE' and s.id=?");
			pstm.setInt(1, idSucursal);
			rs = pstm.executeQuery();
			while(rs.next()) {
				OrdenProvisionDTO o = new OrdenProvisionDTO();
				o.setId(rs.getInt("id"));
				o.setNombre(rs.getString("nombre"));
				o.setFecha(rs.getDate("fecha").toString());
				o.setTiempo(rs.getDouble("tiempo"));
				ordenes.add(o);
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
		return ordenes;
	}

	@Override
	public void deleteOrdenProvision(Integer idOrden) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("DELETE FROM cantidad_producto WHERE id_orden_provision = ?");
			pstm.setInt(1, idOrden);
			pstm.executeUpdate();
			pstm1 = conn.prepareStatement("DELETE FROM orden_provision WHERE id = ?");
			pstm1.setInt(1, idOrden);
			pstm1.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
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
	public BusquedaOrdenProvisionDTO getOrdenById(Integer idOrden) {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		BusquedaOrdenProvisionDTO orden = new BusquedaOrdenProvisionDTO();
		List<BusquedaOrdenProvisionProductoDTO> productos = new ArrayList<BusquedaOrdenProvisionProductoDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT o.id,o.fecha,o.tiempo,s.nombre FROM orden_provision o,sucursal s WHERE o.id = ? and o.id_sucursal = s.id");
			pstm.setInt(1, idOrden);
			rs = pstm.executeQuery();
			rs.next();
			orden.setIdOrden(rs.getInt("id"));
			orden.setSucursal(rs.getString("nombre"));
			orden.setFecha(rs.getDate("fecha"));
			orden.setTiempo(rs.getDouble("tiempo"));
			pstm1 = conn.prepareStatement("SELECT p.nombre,c.cantidad FROM cantidad_producto c,producto p WHERE c.id_orden_provision = ? and p.id = c.id_producto");
			pstm1.setInt(1, idOrden);
			rs1 = pstm1.executeQuery();
			while(rs1.next()) {
				BusquedaOrdenProvisionProductoDTO p = new BusquedaOrdenProvisionProductoDTO();
				p.setProducto(rs1.getString("nombre"));
				p.setCantidad(rs1.getInt("cantidad"));
				productos.add(p);
			}
			orden.setProductos(productos);
		} catch (SQLException | PGException e) {}
		finally {
			if(rs != null) {
				try {
					rs.close();
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
		return orden;
	}

	@Override
	public void updateOrdenProvision(ModificarOrdenProvisionDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("UPDATE orden_provision SET fecha = ?, tiempo = ? WHERE id = ?");
			pstm.setDate(1, new Date(dto.getFecha().getTime()));
			pstm.setDouble(2, Double.parseDouble(dto.getTiempo()));
			pstm.setInt(3, dto.getIdOrden());
			pstm.executeUpdate();
			pstm1 = conn.prepareStatement("DELETE FROM cantidad_producto WHERE id_orden_provision = ?");
			pstm1.setInt(1, dto.getIdOrden());
			pstm1.executeUpdate();
			pstm2 = conn.prepareStatement("INSERT INTO cantidad_producto (id_orden_provision,id_producto,cantidad) VALUES (?,?,?)");
			for(FilaTablaOrdenProvisionDTO f : dto.getFilasTabla()) {
				pstm2.setInt(1,dto.getIdOrden());
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
			if(pstm != null && pstm1 != null && pstm2 != null) {
				try {
					pstm.close();
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
package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.RutaDao;
import dto.AltaRutaDTO;
import dto.BusquedaRutaDTO;
import dto.GestionRutaDTO;
import dto.ModificarRutaDTO;
import excepciones.PGException;
import excepciones.UpdateDBException;
import utils.ConexionDB;

public class RutaPGDao implements RutaDao {

	@Override
	public void guardar(AltaRutaDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("SELECT id FROM sucursal WHERE id = ?");
			pstm.setInt(1, dto.getIdSucursalOrigen());
			rs = pstm.executeQuery();
			rs.next();
			Integer idSucursalOrigen = rs.getInt("id");
			pstm1 = conn.prepareStatement("SELECT id FROM sucursal WHERE id = ?");
			pstm1.setInt(1, dto.getIdSucursalDestino());
			rs1= pstm1.executeQuery();
			rs1.next();
			Integer idSucursalDestino = rs1.getInt("id");
			pstm2 = conn.prepareStatement("INSERT INTO ruta (capacidad,duracion,estado,id_sucursal_origen,id_sucursal_destino) VALUES (?,?,?,?,?)");
			pstm2.setDouble(1, dto.getCapacidad());
			pstm2.setDouble(2, dto.getDuracion());
			pstm2.setString(3, dto.getEstado());
			pstm2.setInt(4, idSucursalOrigen);
			pstm2.setInt(5, idSucursalDestino);
			pstm2.executeUpdate();
			conn.commit();
		}catch (PGException | SQLException e) {
			try {
				if(conn != null) conn.rollback();
				throw new UpdateDBException();
			} catch (SQLException e1) {}
		}
		finally {
			if(rs != null && rs1 != null) {
				try {
					rs.close();
					rs1.close();
				} catch (SQLException e) {}
			}
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
	public List<GestionRutaDTO> getAllRutas() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<GestionRutaDTO> rutas = new ArrayList<GestionRutaDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT r.id,r.capacidad,r.duracion,r.estado,s1.nombre as sucursal_origen,s2.nombre as sucursal_destino FROM ruta r,sucursal s1, sucursal s2 WHERE r.id_sucursal_origen = s1.id and r.id_sucursal_destino = s2.id");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String sucursalOrigen = rs.getString("sucursal_origen");
				String sucursalDestino = rs.getString("sucursal_destino");
				Double capacidad = rs.getDouble("capacidad");
				Double duracion = rs.getDouble("duracion");
				String estado = rs.getString("estado");
				GestionRutaDTO r = new GestionRutaDTO(id,sucursalOrigen,sucursalDestino,capacidad,duracion,estado);
				rutas.add(r);
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
		return rutas;
	}

	@Override
	public List<GestionRutaDTO> getRutasBySucursalOrigen(String sucursal) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<GestionRutaDTO> rutas = new ArrayList<GestionRutaDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT r.id,r.capacidad,r.duracion,r.estado,s1.nombre as sucursal_origen,s2.nombre as sucursal_destino FROM ruta r,sucursal s1, sucursal s2 WHERE r.id_sucursal_origen = s1.id and r.id_sucursal_destino = s2.id and s1.nombre = ?");
			pstm.setString(1, sucursal);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String sucursalOrigen = rs.getString("sucursal_origen");
				String sucursalDestino = rs.getString("sucursal_destino");
				Double capacidad = rs.getDouble("capacidad");
				Double duracion = rs.getDouble("duracion");
				String estado = rs.getString("estado");
				GestionRutaDTO r = new GestionRutaDTO(id,sucursalOrigen,sucursalDestino,capacidad,duracion,estado);
				rutas.add(r);
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
		return rutas;
	}
	
	@Override
	public List<GestionRutaDTO> getRutasBySucursalDestino(String sucursal) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<GestionRutaDTO> rutas = new ArrayList<GestionRutaDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT r.id,r.capacidad,r.duracion,r.estado,s1.nombre as sucursal_origen,s2.nombre as sucursal_destino FROM ruta r,sucursal s1, sucursal s2 WHERE r.id_sucursal_origen = s1.id and r.id_sucursal_destino = s2.id and s2.nombre = ?");
			pstm.setString(1, sucursal);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String sucursalOrigen = rs.getString("sucursal_origen");
				String sucursalDestino = rs.getString("sucursal_destino");
				Double capacidad = rs.getDouble("capacidad");
				Double duracion = rs.getDouble("duracion");
				String estado = rs.getString("estado");
				GestionRutaDTO r = new GestionRutaDTO(id,sucursalOrigen,sucursalDestino,capacidad,duracion,estado);
				rutas.add(r);
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
		return rutas;
	}

	@Override
	public List<GestionRutaDTO> getRutasBySucursales(String origen, String destino) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<GestionRutaDTO> rutas = new ArrayList<GestionRutaDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT r.id,r.capacidad,r.duracion,r.estado,s1.nombre as sucursal_origen,s2.nombre as sucursal_destino FROM ruta r,sucursal s1, sucursal s2 WHERE r.id_sucursal_origen = s1.id and r.id_sucursal_destino = s2.id and s1.nombre = ? and s2.nombre = ?");
			pstm.setString(1, origen);
			pstm.setString(2, destino);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String sucursalOrigen = rs.getString("sucursal_origen");
				String sucursalDestino = rs.getString("sucursal_destino");
				Double capacidad = rs.getDouble("capacidad");
				Double duracion = rs.getDouble("duracion");
				String estado = rs.getString("estado");
				GestionRutaDTO r = new GestionRutaDTO(id,sucursalOrigen,sucursalDestino,capacidad,duracion,estado);
				rutas.add(r);
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
		return rutas;
	}

	@Override
	public void deleteRuta(Integer idRuta) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("DELETE FROM ruta WHERE id = ?");
			pstm.setInt(1, idRuta);
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
	public BusquedaRutaDTO getRutaById(Integer idRuta) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BusquedaRutaDTO ruta = new BusquedaRutaDTO();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT r.id,r.capacidad,r.duracion,r.estado,r.id_sucursal_origen,s1.nombre as sucursal_origen,r.id_sucursal_destino,s2.nombre as sucursal_destino FROM ruta r,sucursal s1, sucursal s2 WHERE r.id_sucursal_origen = s1.id and r.id_sucursal_destino = s2.id and r.id = ?");
			pstm.setInt(1, idRuta);
			rs = pstm.executeQuery();
			rs.next();
			ruta.setIdRuta(rs.getInt("id"));
			ruta.setCapacidad(rs.getDouble("capacidad"));
			ruta.setDuracion(rs.getDouble("duracion"));
			ruta.setEstado(rs.getString("estado"));
			ruta.setIdSucursalOrigen(rs.getInt("id_sucursal_origen"));
			ruta.setSucursalOrigen(rs.getString("sucursal_origen"));
			ruta.setIdSucursalDestino(rs.getInt("id_sucursal_destino"));
			ruta.setSucursalDestino(rs.getString("sucursal_destino"));
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
		return ruta;
	}

	@Override
	public void updateRuta(ModificarRutaDTO dto) throws UpdateDBException {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("UPDATE ruta SET capacidad = ?, duracion = ?, estado = ?, id_sucursal_origen = ?, id_sucursal_destino = ? WHERE id = ?");
			pstm.setDouble(1, dto.getCapacidad());
			pstm.setDouble(2, dto.getDuracion());
			pstm.setString(3, dto.getEstado());
			pstm.setInt(4, dto.getIdSucursalOrigen());
			pstm.setInt(5, dto.getIdSucursalDestino());
			pstm.setInt(6, dto.getIdRuta());
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
package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.SucursalDao;
import dominio.Sucursal;
import dto.SucursalDTO;
import excepciones.PGException;
import excepciones.UpdateDBException;
import utils.ConexionDB;

public class SucursalPGDao implements SucursalDao {

	@Override
	public boolean existeSucursal(String nombre) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM sucursal WHERE nombre = ?");
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
	public boolean existePuerto() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM sucursal WHERE tipo = ?");
			pstm.setString(1, "PUERTO");
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
	public boolean existeCentro() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM sucursal WHERE tipo = ?");
			pstm.setString(1, "CENTRO");
			rs = pstm.executeQuery();
			if(rs.next()) existe = true;
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
		return existe;
	}
	
	@Override
	public void guardar(Sucursal sucursal) throws UpdateDBException{
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("INSERT INTO sucursal (nombre,horario_apertura,horario_cierre,estado,tipo) VALUES (?,?,?,?,?)");
			pstm.setString(1, sucursal.getNombre());
			pstm.setString(2, sucursal.getHorarioApertura());
			pstm.setString(3, sucursal.getHorarioCierre());
			pstm.setString(4, sucursal.getEstado().toString());
			pstm.setString(5, sucursal.getTipo().toString());
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
	public List<SucursalDTO> getSucursalesPorNombre(String nombre) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
		try {
			conn = ConexionDB.getConexion();
			pstm = conn.prepareStatement("SELECT * FROM sucursal WHERE nombre like ?");
			pstm.setString(1, nombre+"%"); //El % actua como comodin, si no se ingresó nada, devuelve todas las sucursales
			rs = pstm.executeQuery();
			while(rs.next()) {
				SucursalDTO s = new SucursalDTO();
				s.setNombre(rs.getString("nombre"));
				s.setHorarioApertura(rs.getString("horario_apertura"));
				s.setHorarioCierre(rs.getString("horario_cierre"));
				s.setEstado(rs.getString("estado"));
				sucursales.add(s);
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
		return sucursales;
	}

	@Override
	public void deleteSucursal(String nombre) throws UpdateDBException{
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement("DELETE FROM SUCURSAL WHERE nombre = ?");
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
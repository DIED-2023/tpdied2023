package dao.interfaces.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaces.SucursalDao;
import dominio.Sucursal;
import excepciones.PGException;
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
		}catch (PGException e) {} 
		catch (SQLException e) {}
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
		}catch (PGException e) {} 
		catch (SQLException e) {}
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
		} catch (SQLException e) {}
		catch (PGException e) {}
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
	public void guardar(Sucursal sucursal) {
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
		}catch (PGException e) {}
		catch (SQLException e) { 
			try {
				conn.rollback();
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
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import excepciones.PGException;

public class ConexionDB {
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static final String usr = "postgres";
	private static final String pass = "postgres";
	
	private ConexionDB() {
		
	}
	
	public static Connection getConexion() throws PGException{
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, usr, pass);
		}catch (ClassNotFoundException e) {
			throw new PGException();
		}catch (SQLException e) {
			throw new PGException();
		}
		return conn;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
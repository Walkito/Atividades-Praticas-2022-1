package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionAPS {		
	public Connection getConnetion() throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("root");
		ds.setPassword("root");
		ds.setServerName("localhost");
		ds.setDatabaseName("APS");
		ds.setPortNumber(3306);
		try {
			Connection conn = ds.getConnection();
			return conn;
		} catch (MySQLDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 
}
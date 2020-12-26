package robert.bermudez.rodriguez.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {

		Connection con = null;

		Class.forName("com.mysql.jdbc.Driver"); // Comprobar que exista el .jar de MySQL.

		InitialContext initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup("jdbc/super");
		
		con = dataSource.getConnection(); // Establecer la conexión del pool.
		
		return con;
	};

} // class

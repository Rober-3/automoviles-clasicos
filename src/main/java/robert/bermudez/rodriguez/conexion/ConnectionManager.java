package robert.bermudez.rodriguez.conexion;

// TODO Notas: Clase que se usará con todas aquellas clases que quieran establecer una conexión con una BBDD.

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	// Si se copia esta clase en otro proyecto, es necesario modificar la URL para indicar la BBDD que se usará en dicho proyecto.
	// USUARIO y PASSWORD son las credenciales que se usan en Ubuntu para acceder a la BBDD. Se pueden encontrar en el archivo de
	// texto leeme.txt, situado en el escritorio de Ubuntu.
	// jdbc: API de Java para realizar conexiones.
//	private final static String URL = "jdbc:mysql://localhost/automoviles_clasicos";
//	private final static String USUARIO = "debian-sys-maint";
//	private final static String PASSWORD = "o8lAkaNtX91xMUcV";
	
	/**
	 * Configuración a través de un DataSource
	 * @see src/main/webapp/META-INF/context.xml
	 */

	public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {

		Connection con = null;

		// Comprobar que exista el .jar de MySQL.
		Class.forName("com.mysql.jdbc.Driver");

		// Establecer la conexión del DriverManager. 
		// con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		
		InitialContext initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup("jdbc/super");
		
		// Establecer la conexión del pool.
		con = dataSource.getConnection();
		
		return con;
	};

} // class

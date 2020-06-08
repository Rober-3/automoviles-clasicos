package robert.bermudez.rodriguez.conexion;

// TODO Notas: // Clase que se usará con todas aquellas clases que quieran establecer una conexión con una BBDD.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	// Si se copia esta clase en otro proyecto, es necesario modificar la URL para indicarle la BBDD que se use en dicho proyecto.
	// USUARIO y PASSWORD son las credenciales que se usan en Ubuntu para acceder a la BBDD. Se pueden encontrar en el archivo de
	// texto leeme.txt, situado en el escritorio de Ubuntu.
	private final static String URL = "jdbc:mysql://localhost/automoviles_clasicos";
	private final static String USUARIO = "debian-sys-maint";
	private final static String PASSWORD = "o8lAkaNtX91xMUcV";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;

		// Comprueba que exista el .jar de MySQL.
		Class.forName("com.mysql.jdbc.Driver");

		// Establece conexión.
		con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		
		return con;

	};

} // class

package robert.bermudez.rodriguez.controller.backoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.connection.ConnectionManager;

/**
 * Realiza la migración de un fichero de texto a la base de datos. Lee un .txt con 100 registros y los inserta.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 */
@WebServlet("/views/backoffice/migracion")
public class MigracionBackOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MigracionBackOfficeController.class);

	/**
	 * <ol>
	 *     <li>Abre una conexión con la base de datos.</li>
	 *     <li>Lee el fichero línea a línea, lanzando una excepción si hay alguna nula.</li>
	 *     <li>Almacena en un array cada línea y si no tiene todos los campos la cuenta como errónea.</li>
	 *     <li>Sustituye en la query todas las interrogaciones por los campos correspondientes de cada línea.</li>
	 *     <li>Ejecuta la query para guardar la línea.</li>
	 *     <li>Después guardar todas las líneas envía los datos y dirige a la vista.</li>
	 * </ol>  
	 * <dt>Variables
	 * 	   <dd><b>String SQL</b> 
	 *     <dd><b>String PATH_FICHERO</b> Ruta del fichero.
	 *     <dd><b>String mensaje</b> Muestra mensajes de error en los catch.
	 *     <dd><b>int numLeidas</b> Total de líneas leídas.
	 *     <dd><b>int numInsertadas</b> Total de líneas insertadas.
	 *     <dd><b>int numErroneas</b> Total de líneas erróneas.
	 *     <dd><b>long tiempoInicio</b> Hora de inicio de la migración.
	 *     <dd><b>long tiempoFin</b> Hora de fin de la migración.
	 *     <dd><b>{@code ArrayList<String>}lineasErroneas</b> guarda las líneas erróneas.
	 *     <dd><b>String linea</b> lee cada una de las líneas del fichero.
	 *     <dd><b>{@code String[]}campos</b>
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.trace("Inicio.");

		final String SQL = "INSERT INTO usuarios (nombre,contrasena,imagen,id_rol) VALUES (?,'111','aaa',1);";
		
		// El problema de establecer esta ruta para el nombre del fichero es que si el proyecto se cuelga en un servidor o
		// se lleva a otro ordenador no se encontrará: /home/javaee/eclipse-workspace es una ruta dentro de una carpeta de
		// mi ordenador. Para resolverlo hay que incluir el fichero en src/main/resources.
		// String fichero = "/home/javaee/eclipse-workspace/automoviles-clasicos/personas.txt";
		final String PATH_FICHERO = "personas.txt"; // Busca el fichero en la carpeta src/main/resources.
		
		String mensaje = "";
		int numLeidas = 0;
		int numInsertadas = 0;
		int numErroneas = 0;
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = 0;
		
		ArrayList<String>lineasErroneas = new ArrayList<String>();
		
		// Lógica de programación.
		
		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL);
				
				// Para encontrar el fichero en la carpeta src/main/resources hay que sustituir las dos líneas de abajo.
				// FileReader fr = new FileReader(PATH_FICHERO);
				// BufferedReader br = new BufferedReader(fr);
				BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(PATH_FICHERO).getFile()));

				){

			// Al establecer una conexión en Java por defecto es autocomitable. Con esta línea se evita. Para guardar los
			// cambios temporales es necesario hacer un commit.
			con.setAutoCommit(false);

			String linea = br.readLine(); // Lee la primera línea del fichero. Al no estar dentro del while se obvia.
			LOG.trace("Recorrer linea a linea el fichero después de saltar la primera.");

			while (null != (linea = br.readLine())) {

				try { // En un principio no incluí este try-catch, pero sólo se leían 85 de las 100 líneas del fichero.

					numLeidas++;

					String[] campos = linea.split(";"); // Separar la linea leída con ";".

					if (campos.length == 6) { // Si la línea tiene seis campos es correcta.

						pst.setString(1, campos[0]);
						LOG.debug(pst);
						
						int affectedRows = pst.executeUpdate();
						
						if (affectedRows == 1) {
							numInsertadas++;
							LOG.trace("Registro insertado.");
							
						} else {
							LOG.trace("No se ha podido insertar el registro.");
						}

					} else {
						lineasErroneas.add(linea);
						numErroneas ++;
					}

				} catch (Exception e) {
					LOG.error(e);
					mensaje = e.getMessage();
				}

			} // while

			con.commit();
			LOG.trace("Al finalizar, realizar un commit para guardar en la BBDD.");

		} catch(Exception e) {
			LOG.error(e);
			mensaje = e.getMessage();
			e.printStackTrace();

		} finally {
			request.setAttribute("fichero", PATH_FICHERO);
			request.setAttribute("leidas", numLeidas);
			request.setAttribute("insertadas",numInsertadas);
			request.setAttribute("erroneas", numErroneas);
			request.setAttribute("lineasErroneas", lineasErroneas);
			request.setAttribute("mensaje", mensaje);
			
			tiempoFin = System.currentTimeMillis();
			request.setAttribute("tiempo", tiempoFin - tiempoInicio);

			request.getRequestDispatcher("resumen-migracion.jsp").forward(request, response);

		} // try-catch-finally

		LOG.trace("Fin");

	} // doGet

} // class 
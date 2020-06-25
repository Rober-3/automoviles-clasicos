package robert.bermudez.rodriguez.listener;

// TODO Notas: Ejemplo de listener. Un listener se ejecuta antes de que una aplicación se conecte con una BBDD,
//			   antes que cualquier controlador y que cualquier método. Dentro de un listener se puede guardar
//			   cualquier atributo y será accesible desde cualquier punto de la aplicación.

//			   En este caso, con el listener ya no será necesario llamar desde los controladores al DAO de las
//			   marcas para obtenerlas y mostrarlas en pantalla, con lo que se reducen las líneas de código y la 
//			   cantidad de variables.

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import robert.bermudez.rodriguez.controller.Alerta;
import robert.bermudez.rodriguez.interfaces.MarcaDAOImpl;

/**
 * Application Lifecycle Listener implementation class InicioAppListener
 *
 */
@WebListener
public class InicioAppListener implements ServletContextListener {
	
	private static final MarcaDAOImpl marcaDao = MarcaDAOImpl.getInstance();


	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	
	// Método que se ejecuta cuando se para la aplicación.
	public void contextDestroyed(ServletContextEvent sce)  { 
		
	}

	
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce)  {
		
		// Cuando se ejecuta la aplicación en el servidor, al arrancar la primera vez.
		System.out.println("Soy un evento, está arrancando la aplicación.");
		
		// Este contexto es para toda la aplicación y es accesible desde cualquier JSP o servlet.
		// Por convención se usa ctx en lugar de contextoAplicación.
		ServletContext contextoAplicación = sce.getServletContext();
		
		try {
			contextoAplicación.setAttribute("marcas", marcaDao.getAll());
			
		} catch (Exception e) {
			contextoAplicación.setAttribute("alerta", new Alerta("danger","Hay un problema sin determinar."));
		}
	}

}

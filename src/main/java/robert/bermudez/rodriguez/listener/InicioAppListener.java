package robert.bermudez.rodriguez.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;

/**
 * Application Lifecycle Listener implementation class InicioAppListener
 *
 */
@WebListener
public class InicioAppListener implements ServletContextListener {
	
	private static final MarcaDAOImpl marcaDao = MarcaDAOImpl.getInstance();
	private static final Logger LOG = Logger.getLogger(InicioAppListener.class);

	public void contextDestroyed(ServletContextEvent sce)  {
		
		LOG.info("Apagando el servidor...");
	}

	
	public void contextInitialized(ServletContextEvent sce)  {
		
		LOG.info("Arrancando la aplicación...");
		
		ServletContext contextoAplicación = sce.getServletContext();
		
		try {
			contextoAplicación.setAttribute("marcas", marcaDao.getAll());
			LOG.info("Cargadas las marcas.");
			
		} catch (Exception e) {
			LOG.fatal(e);
			contextoAplicación.setAttribute("alerta", new Alerta("danger","Hay un problema sin determinar."));
		}
		
	} // contextInitialized

} // class

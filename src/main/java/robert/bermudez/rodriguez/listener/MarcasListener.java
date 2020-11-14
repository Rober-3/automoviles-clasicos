package robert.bermudez.rodriguez.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;

@WebListener
public class MarcasListener implements ServletRequestAttributeListener {
	
	private static final MarcaDAOImpl dao = MarcaDAOImpl.getInstance();
	private static Logger LOG = Logger.getLogger(UsuariosLogueadosListener.class);

	
    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
         getBrands(srae);
    }

    
	public void attributeAdded(ServletRequestAttributeEvent srae)  { 
		getBrands(srae);
	}

	
	public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
		getBrands(srae);
    }
	
	
	public void getBrands(ServletRequestAttributeEvent srae) {
		
		ServletContext ctx = srae.getServletContext();

		try {
			ctx.setAttribute("marcas", dao.getAll());
			LOG.info("Cargadas las marcas.");

		} catch (Exception e) {
			LOG.fatal(e);
			ctx.setAttribute("alerta", new Alerta("danger","Hay un problema sin determinar."));
		}
		
	} // getBrands
	
} // class

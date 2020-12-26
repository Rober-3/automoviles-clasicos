package robert.bermudez.rodriguez.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;

// TODO Notas. Otro ejemplo de listener.

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Listener para contar todos los usuarios que han iniciado y cerrado sesión en la aplicación.<br><br>
 * Application Lifecycle Listener implementation class UsuariosLogueadosListener
 */
@WebListener
public class UsuariosLogueadosListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	private static HashMap<String, Usuario> hm = null;
	
	private static Logger LOG = Logger.getLogger(UsuariosLogueadosListener.class);

	
	public UsuariosLogueadosListener() {
    	
    	LOG.trace("Constructor.");
    	hm = new HashMap<String, Usuario>();
    }

    
    public void sessionCreated(HttpSessionEvent se)  {
    	
    	LOG.trace("Sesión creada.");
    }
    
    
    public void sessionDestroyed(HttpSessionEvent se)  {
    	
    	LOG.trace("Sesión eliminada.");
    }
    
    
    @SuppressWarnings("unchecked")
	public void attributeAdded(HttpSessionBindingEvent event)  {
    	
    	ServletContext ctx = event.getSession().getServletContext();
    	
    	String idSesion = event.getSession().getId();
    	
    	String nombreAtributo = event.getName();
    	LOG.trace("Nuevo atributo en sesión:"  + nombreAtributo);
    	
    	
    	if ("usuario".equals(nombreAtributo)) {
    		
    		Usuario usuario = (Usuario) event.getValue();
    		LOG.trace("Usuario que ha iniciado sesión:"  + usuario);
    		
    		hm = (HashMap<String, Usuario>)ctx.getAttribute("usuariosLogueados");
    		
    		if (null == hm) {
    			hm = new HashMap<String, Usuario>();
    		}
    		
    		hm.put(idSesion, usuario);
    		LOG.trace("Usuario guardado en el HashMap.");
    		
    		ctx.setAttribute("usuariosLogueados", hm);
    		
    	} // if
    	
    } // attributeAdded
    
    
    @SuppressWarnings({ "unchecked" })
	public void attributeRemoved(HttpSessionBindingEvent event)  {
    	
    	ServletContext ctx = event.getSession().getServletContext();
    	String idSesion = event.getSession().getId();
    	
    	String nombreAtributo = event.getName();
    	LOG.trace("Eliminado atributo de sesión:" + nombreAtributo);
    	
    	if ("usuario".equals(nombreAtributo)) {
    		
    		hm = (HashMap<String, Usuario>)ctx.getAttribute("usuariosLogueados");
    		
    		Usuario usuario = (Usuario) event.getValue();
    		LOG.trace("Usuario que ha cerrado sesión:"  + usuario);
    		
    		if (null == hm) {
    			hm = new HashMap<String, Usuario>();
    		}
    		
    		hm.remove(idSesion);
    		LOG.trace("Usuario eliminado del HashMap.");
    		
    		ctx.setAttribute("usuariosLogueados", hm);
    		
    	} // if externo
    	
    } // attributeRemoved
    
    
    public void attributeReplaced(HttpSessionBindingEvent event)  {
    	
    	LOG.trace("Modificado atributo en sesión.");
    }
	
} // class

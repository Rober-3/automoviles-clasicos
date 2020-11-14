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

// Al crear este listener en concreto se han marcado las casillas del apartado HTTP session events:

// - Lifecycle (ciclo de vida de las sesiones).
// - Changes to attributes (cada vez que se cambia el atributo dentro de la sesión el listener está atento).

@WebListener
public class UsuariosLogueadosListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	// HashMap para guardar los usuarios que inician y cierran sesión y mostrarlos en el backoffice.
	
	// HashMap para guardar usuarios por medio de su id de usuario.
	// private static HashMap<Integer, Usuario> hm = null;
	
	// HashMap para guardar usuarios por medio del id de sus sesiones. Útil en los casos en que uno o más usuarios
	// inicien sesión desde dispositivos diferentes a la vez y que quede registrado.
	private static HashMap<String, Usuario> hm = null;
	
	private static Logger LOG = Logger.getLogger(UsuariosLogueadosListener.class);

	
	
    /**
     * Default constructor. 
     */
    public UsuariosLogueadosListener() {
    	
    	LOG.trace("Constructor.");
    	hm = new HashMap<String, Usuario>();
    }

    
    
    // Método que se ejecuta cada vez que se crea una sesión. No es útil para contar un usuario registrado que inicia
    // sesión porque cualquier visitante no registrado hace que se ejecute.
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
    	
    	LOG.trace("Sesión creada.");
    }
    
    

    // Método que se ejecuta cada vez que se destruye una sesión.
	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
    	
    	LOG.trace("Sesión eliminada.");
    }
    
    

    // Método que se ejecuta cada vez que se añade un atributo en una sesión. Para contar los usuarios registrados que
    // inician sesión este es el que debe usarse.
	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @SuppressWarnings("unchecked") // Evita un warning.
	public void attributeAdded(HttpSessionBindingEvent event)  {
    	
    	// Obtiene el contexto para guardar los usuarios que han iniciado sesión y mostrarlos en el backoffice.
    	ServletContext ctx = event.getSession().getServletContext();
    	
    	String idSesion = event.getSession().getId(); // Obtiene el id de la sesión para guardar los usuarios.
    	
    	String nombreAtributo = event.getName(); // getName obtiene el nombre del parámetro event.
    	LOG.trace("Nuevo atributo en sesión:"  + nombreAtributo);
    	
    	
    	// Se acaba de hacer el login en LoginController y "usuario" lo proporciona este controlador.
    	if ("usuario".equals(nombreAtributo)) {
    		
    		Usuario usuario = (Usuario) event.getValue();
    		LOG.trace("Usuario que ha iniciado sesión:"  + usuario);
    		
    		hm = (HashMap<String, Usuario>)ctx.getAttribute("usuariosLogueados");
    		
    		if (null == hm) {
    			hm = new HashMap<String, Usuario>();
    		}
    		
    		// hm.put(usuario.getId(), usuario); // Guarda el usuario en el HashMap usando su id de usuario.
    		hm.put(idSesion, usuario); // Guarda el usuario en el HashMap usando su id de sesión
    		LOG.trace("Usuario guardado en el HashMap.");
    		
    		ctx.setAttribute("usuariosLogueados", hm);  // Muestra el HashMap en views/backoffice/index.jsp
    		
    	} // if
    	
    } // attributeAdded
    
    
    
    // Método que se ejecuta cada vez que se quita un atributo de una sesión.
	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    @SuppressWarnings({ "unchecked" }) // Evita un warning.
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
    		
    		hm.remove(idSesion); // Elimina el usuario del HashMap. La key será el id del usuario.
    		LOG.trace("Usuario eliminado del HashMap.");
    		
    		ctx.setAttribute("usuariosLogueados", hm); // Muestra el HashMap en el backoffice.
    		
    	} // if externo
    	
    } // attributeRemoved
    
    
    
    // Método que se ejecuta cada vez que se reemplaza un atributo en una sesión.
	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  {
    	
    	LOG.trace("Modificado atributo en sesión.");
    }
	
} // class

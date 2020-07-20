package robert.bermudez.rodriguez.filtros;

// TODO Notas: filtro para el backoffice

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.Rol;
import robert.bermudez.rodriguez.modelo.Usuario;

/**
 * Servlet Filter implementation class BackOfficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
// Cada vez que la URL coincide con este patrón se ejecuta el filtro. Esto evita accesos no autorizados al backoffice y al frontoffice
// conociendo las URL e introduciéndolas en el navegador.
					, urlPatterns = { "/views/backoffice/*" })
public class BackOfficeFilter implements Filter {
	
	private final static Logger LOG = Logger.getLogger(BackOfficeFilter.class);
	
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
		LOG.trace("Se destruye el filtro.");
	}
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	// El filtro se ejecuta en doFilter.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// Como el controlador LoginController trabaja con HttpServletRequest request y BackOfficeFilter con ServletRequest request,
		// hay que castear de ServletRequest a HttpServletRequest, y lo mismo con ServletResponse response. De esta forma se puede
		// acceder al método getRequestURI a la hora de hacer la traza.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		
		// Se necesita la URL donde comienza la aplicación para construir una ruta ABSOLUTA, no relativa, y evitar el bucle de abajo.
		String urlInicioApp = req.getContextPath(); // Recoge el nombre del servidor mas el nombre de la aplicación.
		
		
		LOG.trace("Filtrando URI " + req.getRequestURI());
		
		
		// Se recuperal el usuario de la session.
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		// Se recupera el usuario de la request
		// Usuario usuario = (Usuario) req.getAttribute("usuario");
		
		
		if (usuario == null) { // Aquí se comprueba que el usuario está autentificado.
			
			System.out.println("Usuario null, no ha pasado por el inicio de sesión. Sin autentificar.");
			LOG.warn("Usuario null, no ha pasado por el inicio de sesión. Sin autentificar.");
			// res.sendRedirect("login.jsp"); -> Si se especifica la ruta relativa se mete en un bucle (ver minuto 2:42:51 del vídeo
			// 130720·2-2 para explicación).
			res.sendRedirect (urlInicioApp + "/views/login.jsp"); // Ruta absoluta.
			
		} else if (usuario.getRol().getId() != Rol.ADMINISTRADOR) { // Aquí se comprueba que el usuario está autorizado.
			
			LOG.warn("Usuario sin privilegios de ADMINISTRADOR. Sin autorización para acceder al backoffice.");
			// Si el usuario no es administrador se le envía a login.
			res.sendRedirect (urlInicioApp + "/views/login.jsp");
			
			// Si un usuario intentara entrar sin autorización, con esta línea se podría enviar un código 403. Con dicho código, el
			// navegador no renderizaría el HTML del inicio y mostraría el 403 en una página.
			// res.setStatus(403); 
			
		} else {
			
			// Si el usuario es administrador se le deja pasar y continúa la request.
			chain.doFilter(request, response);
			
		} // if-else-if-else
		
	} // doFilter
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		
		
		LOG.trace("Se inicia el filtro.");
	}

} // class

package robert.bermudez.rodriguez.filtros;

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

import robert.bermudez.rodriguez.modelo.pojo.Rol;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class FrontOfficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/views/frontoffice/*" })
public class FrontOfficeFilter implements Filter {
	
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// Casteo de request y response.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		
		// URL donde comienza la aplicación para construir la ruta absoluta.
		String urlInicioApp = req.getContextPath();
		
		
		LOG.trace("Filtrando URI " + req.getRequestURI());
		
		
		// Recuperar el usuario.
		// Hubo inicios de sesión fallidos debido a esta línea, en la que se recuperaba el usuario de la request, no de la sessión.
		// Usuario usuario = (Usuario) req.getAttribute("usuario");
		
		// HttpSession session = req.getSession();
		// Usuario usuario = (Usuario) session.getAttribute("usuario");
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		
		if (usuario == null) {
			
			LOG.warn("Usuario null, no ha pasado por el inicio de sesión. Sin autentificar.");
			res.sendRedirect (urlInicioApp + "/views/login.jsp"); // Ruta absoluta.
			
		} else if (usuario.getRol().getId() != Rol.USUARIO) {
			
			LOG.warn("Usuario sin privilegios de USUARIO. Sin autorización para acceder al frontoffice.");
			// Si el usuario no es de tipo usuario se le envía a login.
			res.sendRedirect (urlInicioApp + "/views/login.jsp");
			
		} else {
			
			// Si el usuario es de tipo usuario se le deja pasar.
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

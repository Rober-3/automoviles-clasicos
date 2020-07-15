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

import org.apache.log4j.Logger;

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
// conociendo las URL.
					, urlPatterns = { "/views/backoffice/*" })
public class BackOfficeFilter implements Filter {
	
	//private final static logger LOG = Logger.getLogger(BackOfficeFilter.class);


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		//LOG.trace("Se destruye el filtro.");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	// El filtro se ejecuta en doFilter.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//LOG.trace("Filtrando
		
		// Si el usuario no es administrador se le envía a login.
		

		// Si el usuario es administrador se le deja pasar y continúa la request.
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//LOG.trace("Se inicia el filtro.");

}

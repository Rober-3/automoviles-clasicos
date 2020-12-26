package robert.bermudez.rodriguez.filters;

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

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/views/frontoffice/*" })
public class FrontOfficeFilter implements Filter {

	private final static Logger LOG = Logger.getLogger(BackOfficeFilter.class);

	public void destroy() {
		LOG.trace("Se destruye el filtro.");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String urlInicioApp = req.getContextPath();

		LOG.trace("Filtrando URI " + req.getRequestURI());

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		if (usuario == null) {
			LOG.warn("Usuario null, no ha pasado por el inicio de sesión. Sin autentificar.");
			res.sendRedirect(urlInicioApp + "/views/login.jsp");

		} else if (usuario.getRol().getId() != Rol.USUARIO) {
			LOG.warn("Usuario sin privilegios de USUARIO. Sin autorización para acceder al frontoffice.");
			res.sendRedirect(urlInicioApp + "/views/login.jsp");

		} else {
			chain.doFilter(request, response);

		} // if-else-if-else

	} // doFilter

	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("Se inicia el filtro.");
	}

} // class

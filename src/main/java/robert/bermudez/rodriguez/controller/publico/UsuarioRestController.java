package robert.bermudez.rodriguez.controller.publico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;

/**
 * Servlet implementation class UsuarioRestController
 */
@WebServlet("/api/usuario")
public class UsuarioRestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(UsuarioRestController.class);  
	private static UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("url pathInfo:" + request.getPathInfo() );

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String nombre = request.getParameter("nombre");
		boolean nombreExiste;

		nombreExiste = dao.searchByName(nombre);

		if (nombreExiste) {
			response.setStatus( HttpServletResponse.SC_OK );

		} else {
			response.setStatus( HttpServletResponse.SC_NO_CONTENT );
		}

	} // doGet

} //class

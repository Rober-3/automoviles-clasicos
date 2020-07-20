package robert.bermudez.rodriguez.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class InicioFrontOfficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class InicioFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
    
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Recuperar los datos del usuario (clásicos validados y clásicos por validar).
		
		// Estos atributos se envían a index.jsp del frontoffice.
		request.setAttribute("clasicos_publicados", 4);
		request.setAttribute("clasicos_pendientes", 4);
		
		
		// Cuidado con la URL del servlet ("/views/frontoffice/inicio") ya que al hacer forward se sustituye la última
		// parte (inicio) por la variable pagina (ver más abajo).
		
		// El forward resuelve la URL de la siguiente manera:
		// "/views/frontoffice/inicio" + "index.jsp"  =  "/views/frontoffice/index.jsp"
		
		String pagina = "index.jsp"; // index.jsp de frontoffice, no el index.jsp principal.
		LOG.debug("forward: " + pagina);
		
		request.getRequestDispatcher(pagina).forward(request, response);
		
	} // doGet
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

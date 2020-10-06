package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dirige a la pantalla de inicio del backoffice.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 */
@WebServlet("/views/backoffice/inicio")
public class InicioBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * Dirige a la pantalla de inicio del backoffice.
	 * 
	 * <dt>Variables
	 * 	   <dd><b>String pagina</b> Ruta de acceso a la página de inicio del backoffice.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Cuidado con la URL del servlet ("/views/frontoffice/inicio") ya que al hacer forward se sustitituye la última parte
		// parte (inicio) por la variable pagina (ver más abajo).

		// El forward resuelve la URL de la siguiente manera:
		// "/views/backOffice/inicio" + "index.jsp"  =  "/views/backOffice/index.jsp"
		String pagina = "index.jsp"; // index.jsp de backoffice, no el index.jsp general.

		request.getRequestDispatcher(pagina).forward(request, response);

	} // doGet

	// Si se elimina este método, al iniciar sesión en el backoffice dará un error 405.
	// HTTP Status 405 – Method Not Allowed HTTP method POST is not supported by this URL
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

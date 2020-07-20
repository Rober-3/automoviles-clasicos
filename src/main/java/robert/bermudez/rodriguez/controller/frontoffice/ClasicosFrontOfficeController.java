package robert.bermudez.rodriguez.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ClasicosFrontOfficeController
 */
@WebServlet("/views/frontoffice/clasicos")
public class ClasicosFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String validados = request.getParameter("validados");
		String encabezado = "";
		
		if (validados == null) {
			encabezado = "";
			
		} else {
			encabezado = "";
		}
		
		request.setAttribute("encabezado", encabezado);
		request.getRequestDispatcher("clasicos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

} // class

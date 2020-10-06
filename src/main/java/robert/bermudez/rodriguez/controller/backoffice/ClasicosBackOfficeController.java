package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.frontoffice.ClasicosFrontOfficeController;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;

/**
 * Servlet implementation class ClasicosBackOfficeController
 */
@WebServlet("/views/backoffice/clasicos")
public class ClasicosBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClasicosFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();

       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String encabezado = "";
		
		String validados = request.getParameter("validados"); // Recogida de parámetros de office-navbar-admin.jsp
		
		try {
			
			if (validados == null ) {
				encabezado = "Clásicos publicados";
				clasicos = dao.getAll();
				
			} else {
				encabezado = "Clásicos pendientes de aprobación";
				clasicos = dao.getAllSinValidar();
			}
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasicos", clasicos);
			request.setAttribute("encabezado", encabezado);
			request.getRequestDispatcher("clasicos.jsp").forward(request, response);
		}
		
	} // doGet

} // class

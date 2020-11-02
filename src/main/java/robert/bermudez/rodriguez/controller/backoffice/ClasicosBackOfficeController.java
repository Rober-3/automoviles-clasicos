package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/backoffice/clasicos")
public class ClasicosBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClasicosBackOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();

      
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String encabezado = "";
		
		// Recogida de parámetros de office-navbar-admin.jsp
		String validados = request.getParameter("validados");
		String total = request.getParameter("total");
		
		try {
			
			if (validados != null && total != null) {
				encabezado = "Clásicos sin validar";
				clasicos = dao.getAllValidation(false);
				request.setAttribute("validados", validados);
					
			} else if (validados == null && total != null) {
				encabezado = "Clásicos publicados";
				clasicos = dao.getAllValidation(true);
				
			} else {
				encabezado = "Lista";
				clasicos = dao.getAll();
			}
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasicos", clasicos);
			request.setAttribute("encabezado", encabezado);
			request.getRequestDispatcher("clasicos.jsp").forward(request, response); // backoffice/clasicos.jsp
		}
		
	} // doGet

} // class

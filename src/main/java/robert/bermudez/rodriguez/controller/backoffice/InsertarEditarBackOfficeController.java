package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.Alerta;
import robert.bermudez.rodriguez.controller.frontoffice.InsertarFrontOfficeController;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;

/**
 * Servlet implementation class InsertarEditarBackOfficeController
 */
@WebServlet("/views/backoffice/insertar-editar")
public class InsertarEditarBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsertarFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
	
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Alerta alerta = new Alerta();
    	
    	String id = request.getParameter("id"); // Recogida de parámetros de backoffice/clasicos.jsp.
    	
    	try {
    		
    		int idModelo = Integer.parseInt(id);
    		
    		if (idModelo != 0) {
				dao.validar(idModelo);
				alerta = new Alerta("success", "Clásico validado con éxito.");
			}
    		
		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("index.jsp").forward(request, response);	
		}
    	
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

} // class

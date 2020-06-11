package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.interfaces.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;

/**
 * Servlet implementation class EliminarController
 */
@WebServlet("/eliminar")
public class EliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Clasico clasico = new Clasico();
		String alerta = "";
		
		// Recogida de parámetros enviados desde la vista.
		String paramId = request.getParameter("id");
		
		try {
			
			int id = Integer.parseInt(paramId);
			ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
			clasico = dao.delete(id);
			alerta = "Clásico borrado con éxito";
			
		} catch (Exception e) {
			alerta =  "Ha habido un problema al intentar borrar el clásico: " + e.getMessage();
			e.printStackTrace();
		}
		
		request.setAttribute("clasico", clasico);
		request.setAttribute("alerta", alerta);
		request.getRequestDispatcher("clasicos").forward(request, response);
	
	} // doGet

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	
	} // doPost

} // class

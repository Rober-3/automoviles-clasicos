package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;

/**
 * Servlet implementation class EliminarController
 */
@WebServlet("/eliminar")
public class EliminarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
	

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Clasico clasico = new Clasico();
		Alerta alerta = new Alerta();
		
		
		// Recogida de parámetros enviados desde la vista.
		String paramId = request.getParameter("id");
		
		
		try {
			
			int id = Integer.parseInt(paramId);
			clasico = dao.delete(id);
			alerta = new Alerta("success","Clásico borrado con éxito.");

			
		} catch (Exception e) {
			alerta = new Alerta("danger","Ha habido un problema al intentar borrar el clásico: " + e.getMessage());
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

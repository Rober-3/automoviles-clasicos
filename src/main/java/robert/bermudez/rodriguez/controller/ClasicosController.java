package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.interfaces.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;

/**
 * Servlet implementation class ClasicosController
 */
@WebServlet("/clasicos")
public class ClasicosController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getAll
		
		ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
		
		ArrayList<Clasico> clasicosAmericanos = new ArrayList<Clasico>();
		
		try {
			clasicosAmericanos = dao.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} // try-catch
		
		request.setAttribute("clasicosAmericanos", clasicosAmericanos);
		
		request.getRequestDispatcher("clasicos-americanos.jsp").forward(request, response);
		
	} // doGet

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	} // doPost

} // class

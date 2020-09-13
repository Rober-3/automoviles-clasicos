package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.modelo.pojo.Marca;

/**
 * Servlet implementation class MarcasController
 */
@WebServlet("/marcas")
public class MarcasController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Marca marca = new Marca();
				
		request.setAttribute("marca", marca);
		
		request.getRequestDispatcher("views/formulario-marcas.jsp").forward(request, response);
		
	} // doGet

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramId = request.getParameter("id");
		String paramMarca = request.getParameter("marca");
		Marca marca = new Marca();
		Alerta alerta = null;
		
		try {
			int id = Integer.parseInt(paramId);
			marca.setId(id);
			marca.setMarca(paramMarca);
			alerta =  new Alerta ("success", "Nueva marca insertada con éxito.");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("marca", marca);
			request.setAttribute("alerta", alerta);
			
			request.getRequestDispatcher("views/formulario-marcas").forward(request, response);
			
		} // try-catch-finally
		
	} // doPost

} // class

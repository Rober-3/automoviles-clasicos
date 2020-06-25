package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.interfaces.ClasicoDAOImpl;
import robert.bermudez.rodriguez.interfaces.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

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

		
		ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
		// Con InicioAppListener ya no es necesario llamar al DAO de las marcas.
		// MarcaDAOImpl daoMarca = MarcaDAOImpl.getInstance();
		
		ArrayList<Clasico> clasicosAmericanos = new ArrayList<Clasico>();
		// ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try {
			clasicosAmericanos = daoClasico.getAll();
			// marcas = daoMarca.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} // try-catch
		
		request.setAttribute("clasicosAmericanos", clasicosAmericanos);
		// request.setAttribute("marcas", marcas);
		
		request.getRequestDispatcher("clasicos-americanos.jsp").forward(request, response);
		
	} // doGet

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	} // doPost

} // class

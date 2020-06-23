package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import robert.bermudez.rodriguez.interfaces.ClasicoDAOImpl;
import robert.bermudez.rodriguez.interfaces.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final ClasicoDAOImpl clasicoDao = ClasicoDAOImpl.getInstance();
	private static final MarcaDAOImpl marcaDao = MarcaDAOImpl.getInstance();



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// idMarca se recibe desde el dropdown de header.jsp para seleccionar una marca.
		String id = request.getParameter("idMarca");
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		ArrayList<Marca> marcas = new ArrayList<Marca>();

		try {
			
			marcas = marcaDao.getAll();
			
			if (id == null) {
				clasicos = clasicoDao.getAll();
				
			} else {
				int idMarca = Integer.parseInt(id);
				clasicos = clasicoDao.getByMarca(idMarca);

			}

		} catch (Exception e) {
			Alerta alerta = new Alerta("danger", "Ha habido un problema: " + e.getMessage());
			request.setAttribute("alerta", alerta);
			e.printStackTrace();

		} finally {
			request.setAttribute("clasicos", clasicos);
			request.setAttribute("marcas", marcas);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} // try-catch-finally

	} // doGet



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	} // doPost



	// Para obtener los clásicos en la vista da lo mismo emplear el doGet que el doPost, con puentear uno de los dos es suficiente.
	// También se puede crear el método doProcess y llamarlo tanto desde el doGet como del doPost.

	// private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// request.setAttribute("clasicos", clasicoDao.getAll() );		
	// request.getRequestDispatcher("index.jsp").forward(request, response);

	// } // doProcess

} // class

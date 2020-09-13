package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioController.class);
	private static final ClasicoDAOImpl clasicoDao = ClasicoDAOImpl.getInstance();
	
	// El DAO de las marcas sólo es necesario para llamar al método getAllWithClassics(). Para obtenerlas pinchando en el
	// dropdown de la cabecera, es suficiente con el listener InicioAppListener.
	private static final MarcaDAOImpl marcaDao = MarcaDAOImpl.getInstance();



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @Parámetros idMarca: se recibe desde el dropdown de header.jsp <br>
	 * 			   marca: se recibe como parámetro para mostrar luego la marca en pantalla
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramId = request.getParameter("idMarca");
		String marca = request.getParameter("marca");
		
		ArrayList<Marca> marcasConClasicos = new ArrayList<Marca>(); // HashMap de marcas.

		try {
			
			// marcas = marcaDao.getAll(); Para llamar a este método no sería necesario el DAO de las marcas.
			
			if (paramId == null) { // Si no se reciben parámetros, se muestran todas las marcas ordenadas alfabéticamente.
				
				marcasConClasicos = marcaDao.getAllWithClassics();
				
			} else { // Si se reciben parámetros, muestra la marca correspondiente al idMarca.
				
				int idMarca = Integer.parseInt(paramId);
				ArrayList<Clasico> clasicos = clasicoDao.getAllByMarca(idMarca, 10);
				
				request.setAttribute("clasicos", clasicos);
				request.setAttribute("marca", marca); // La envía para mostrarla en pantalla.
				
			} // if-else

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.setAttribute("marcasConClasicos", marcasConClasicos);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} // try-catch-finally

	} // doGet



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	} // doPost



	// Para obtener los clásicos en la vista da lo mismo emplear doGet que doPost, con puentear uno de los dos es suficiente.
	// También se puede crear el método doProcess y llamarlo tanto desde el doGet como del doPost.

	// private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// request.setAttribute("clasicos", clasicoDao.getAll() );		
	// request.getRequestDispatcher("index.jsp").forward(request, response);

	// } // doProcess

} // class

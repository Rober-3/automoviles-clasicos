package robert.bermudez.rodriguez.controller.publico;

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

@WebServlet("/marcas")
public class MarcasController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MarcasController.class);
	private static final ClasicoDAOImpl clasicoDao = ClasicoDAOImpl.getInstance();
	private static final MarcaDAOImpl marcaDao = MarcaDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("idMarca");
		String marca = request.getParameter("marca");
		
		ArrayList<Marca> marcasConClasicos = new ArrayList<Marca>(); // HashMap de marcas.

		try {
			
			// marcas = marcaDao.getAll(); Para llamar a este método no sería necesario el DAO de las marcas.
			
			if (paramId == null) { // Si no se reciben parámetros, se muestran todas las marcas ordenadas alfabéticamente.
				
				marcasConClasicos = marcaDao.getAllWithClassics();
				request.setAttribute("marcasConClasicos", marcasConClasicos);
				
			} else { // Si se reciben parámetros, muestra la marca correspondiente al idMarca.
				
				int idMarca = Integer.parseInt(paramId);
				ArrayList<Clasico> clasicos = clasicoDao.getAllByBrand(idMarca, 10);
				
				request.setAttribute("clasicos", clasicos);
				request.setAttribute("marca", marca); // La envía para mostrarla en pantalla.
				
			} // if-else

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.getRequestDispatcher("views/public/marcas.jsp").forward(request, response);
		}

	} // doGet
	
} // class

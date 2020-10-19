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
import robert.bermudez.rodriguez.modelo.pojo.Clasico;

@WebServlet("/clasicos")
public class ClasicosController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClasicosController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
		// Con InicioAppListener ya no es necesario llamar al DAO de las marcas.
		// MarcaDAOImpl daoMarca = MarcaDAOImpl.getInstance();
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		// ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try {
			clasicos = daoClasico.getAll();
			// marcas = daoMarca.getAll();
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasicos", clasicos);
			// request.setAttribute("marcas", marcas);
			request.getRequestDispatcher("views/public/clasicos.jsp").forward(request, response);
		}
		
	} // doGet

} // class

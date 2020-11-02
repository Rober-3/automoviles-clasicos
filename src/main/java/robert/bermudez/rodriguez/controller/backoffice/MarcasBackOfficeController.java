package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

@WebServlet("/views/backoffice/marcas")
public class MarcasBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MarcasBackOfficeController.class);
	private static final MarcaDAOImpl dao = MarcaDAOImpl.getInstance();
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try {
			marcas = dao.getAll();
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("marcas", marcas);
			request.setAttribute("encabezado", "Lista");
			request.getRequestDispatcher("marcas.jsp").forward(request, response); // backoffice/marcas.jsp
		}
		
	} // doGet

} // class

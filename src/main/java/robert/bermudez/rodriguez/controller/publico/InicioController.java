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

@WebServlet("/inicio")
public class InicioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try {
			
			clasicos = dao.getAleatory();
			Clasico clasico1 = clasicos.get(0);
			Clasico clasico2 = clasicos.get(1);
			Clasico clasico3 = clasicos.get(2);
			
			request.setAttribute("clasico1", clasico1);
			request.setAttribute("clasico2", clasico2);
			request.setAttribute("clasico3", clasico3);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			LOG.error(e);
		} 

	} // doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

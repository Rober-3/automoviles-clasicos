package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;

@WebServlet("/views/backoffice/eliminar-clasico")
public class ElimClasBackOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ElimClasBackOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Alerta alerta = null;

		String id = request.getParameter("id"); // views/backoffice/clasicos.jsp

		try {

			int idModelo = Integer.parseInt(id);
			clasico = dao.delete(idModelo);	
			alerta = new Alerta("success", "<b>" + clasico.getMarca().getMarca() + " " + clasico.getModelo()
			+ "</b> borrado correctamente.");

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.getSession().setAttribute("alerta", alerta);
			response.sendRedirect(request.getContextPath() + "/views/backoffice/clasicos");
		}

	} // doGet

} // class

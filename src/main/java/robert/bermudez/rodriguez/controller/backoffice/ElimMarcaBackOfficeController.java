package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

@WebServlet("/views/backoffice/eliminar-marca")
public class ElimMarcaBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ElimMarcaBackOfficeController.class);
	private static final MarcaDAOImpl dao = MarcaDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Marca marca = new Marca();
		Alerta alerta = new Alerta();

		String id = request.getParameter("id"); // backoffice/marcas.jsp

		try {

			int idMarca = Integer.parseInt(id);

			marca = dao.delete(idMarca);	
			alerta = new Alerta("success",  "<b>" + marca + "</b> borrada correctamente.");

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.getSession().setAttribute("alerta", alerta);
			response.sendRedirect(request.getContextPath() + "/views/backoffice/marcas");
		}
		
	} // doGet

} // class

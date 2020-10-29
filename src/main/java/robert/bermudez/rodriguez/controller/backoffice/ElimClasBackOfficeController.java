package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.dao.SeguridadException;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/backoffice/eliminar-clasico")
public class ElimClasBackOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ElimClasBackOfficeController.class);
	private static final ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
	private static final UsuarioDAOImpl daoUsuario = UsuarioDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = null;

		String id = request.getParameter("id"); // views/backoffice/clasicos.jsp

		try {

			int idModelo = Integer.parseInt(id);
			usuario = (Usuario)request.getSession().getAttribute("usuario");
			
			// Comprueba que sea el administrador quien efectúa el borrado.
			if (daoUsuario.checkRole(usuario)) {
				clasico = daoClasico.delete(idModelo);	
				alerta = new Alerta("success",  clasico.getMarca().getMarca() + " " + clasico.getModelo() + " borrado con éxito.");
				
			} else {
				throw new SeguridadException();
			}

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.getSession().setAttribute("alerta", alerta);
			response.sendRedirect(request.getContextPath() + "/views/backoffice/clasicos");
			
			// Si se hace getRequestDispatcher no mostrará la lista con todos los clásicos. Hay
			// que realizar una nueva petición para pasar por el controlador y recuperarlos.
			// request.setAttribute("alerta", alerta);
			// request.getRequestDispatcher("clasicos.jsp").forward(request, response);
		}

	} // doGet

} // class

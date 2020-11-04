package robert.bermudez.rodriguez.controller.frontoffice;

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
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/frontoffice/eliminar-clasico")
public class ElimClasFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ElimClasFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
       
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = new Alerta();
		
		String id = request.getParameter("id"); // Recogida de parámetros de clasicos.jsp del frontoffice.
		
		try {
			
			int idModelo = Integer.parseInt(id);
			
			// Recupera el usuario de la sesión y obtiene su id para verificar que ha registrado el clásico que eliminará.
			usuario = (Usuario)request.getSession().getAttribute("usuario");
			int idUsuario = usuario.getId();
			
			if (idModelo != 0) {
				clasico = dao.getByIdByUser(idUsuario, idModelo);
			}
			
			clasico = dao.delete(idModelo);	
			alerta = new Alerta("success",  "<b>" + clasico.getMarca().getMarca() + " " + clasico.getModelo()
			+ "</b> borrado correctamente.");
					
		} catch (SeguridadException e) {
			LOG.error("Un usuario ha intentado eliminar un clásico que no ha registrado: " + usuario);
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario-clasicos.jsp").forward(request, response);	
		}
		
	} // doGet

} // class

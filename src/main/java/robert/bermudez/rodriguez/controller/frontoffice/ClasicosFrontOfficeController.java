package robert.bermudez.rodriguez.controller.frontoffice;

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
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/frontoffice/clasicos")
public class ClasicosFrontOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ClasicosFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String encabezado = "";

		// Recogida de parámetros de office-navbar-usuario.jsp
		String validados = request.getParameter("validados"); 
		String total = request.getParameter("total");
		
		// LOG.trace("Leer fichero de texto.");
		// LOG.trace("Abrir una conexión con la BBDDD.");
		// LOG.trace("Recorrer línea a línea.");
		// LOG.trace("Comprobar datos correctos en las líneas.");
		// LOG.trace("Insertar persona.");
		// LOG.trace("Al finalizar, realizar un commit para guardar en la BBDD.");
		// LOG.trace("Fin");
		
//		for (int i = 0; i < 5; i++) {
//
//			try {
//				numLeidas++;
//
//				// Obviar la primera línea, que es la cabecera.
//
//				String linea = "";
//				String[] campos = linea.split(";");
//
//				// Si la línea tiene seis campos es correcta.
//
//				pst.setString(1, "persona" + i);
//				LOG.debug(pst);
//
//				int affectedRows = pst.executeUpdate();
//
//				if (affectedRows == 1) {
//					numInsertadas++;
//					LOG.trace("Insertada persona.");
//
//				} else {
//					numErroneas ++;
//					LOG.trace("No se puede insertar persona.");
//				}
//
//			} catch (Exception e){
//				// Capturar posibles excepciones para poder seguir dentro del for.
//				numErroneas++;
//			}

//		} // for

		
		
		try {
			
			// Estas dos líneas
			// 		HttpSession session = request.getSession();
			// 		Usuario usuario = (Usuario)session.getAttribute("usuario");
			
			// equivalen a
			// 		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");

			// Como cada usuario tiene sus propios clásicos registrados y se accede a éstos desde el panel correspondiente a
			// cada usuario, se invoca el método getAllByUser, el cual necesita como uno de sus parámetros el id de usuario.
			// Para obtener dicho id hay que recuperar el usuario de session. El atributo se recupera desde LoginController.
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			int idUsuario = usuario.getId();

			if (validados != null && total != null) {
				encabezado = "Clásicos sin validar";
				clasicos = dao.getAllByUserValidation(idUsuario, false);
				request.setAttribute("validados", validados);
					
			} else if (validados == null && total != null) {
				encabezado = "Clásicos publicados";
				clasicos = dao.getAllByUserValidation(idUsuario, true);
				
			} else {
				encabezado = "Total de clásicos";
				clasicos = dao.getAllByUser(idUsuario);
			}
			
			
//			if (validados == null) {
//				encabezado = "Clásicos publicados";
//				clasicos = dao.getAllByUser(idUsuario, true);
//
//			} else {
//				encabezado = "Clásicos pendientes de aprobación";
//				clasicos = dao.getAllByUser(idUsuario, false);
//			}
			
			

		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasicos", clasicos);
			request.setAttribute("encabezado", encabezado);
			request.getRequestDispatcher("clasicos.jsp").forward(request, response); // frontoffice/clasicos.jsp
		}

	} // doGet

} // class

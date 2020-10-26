package robert.bermudez.rodriguez.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.ClasicosUsuario;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Servlet implementation class InicioFrontOfficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class InicioFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
    private static final ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.trace("Panel de inicio.");
		
		// Recuperar el usuario de la sesión.
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		int idUsuario = usuario.getId();
		
		
		// Si no se utilizara una vista en la BBDD, para recuperar los clásicos aprobados y los clásicos pendientes sería
		// necesario usar dos querys diferentes en ClasicoDAOImpl, y que el método getAllByUser empleara como parámetros
		// el id de usuario y un booleano que determinaría si el clásico está aprobado o no, para posteriormente obtener
		// los clásicos en dos ArrayList diferentes y luego determinar su tamaño para conseguir los resultados buscados.
		
		// ArrayList<Producto> aprobados = daoProducto.getAllByUser( idUsuario, true);	
		// ArrayList<Producto> pendientes = daoProducto.getAllByUser( idUsuario, false);
		// request.setAttribute("productos_aprobados",  aprobados.size() );
		// request.setAttribute("productos_pendientes", pendientes.size() );
		
		
		// Recuperar los datos del usuario (clásicos totales, validados y pendientes de aprobación) usando vistas.
		ClasicosUsuario resumenUsuario = daoClasico.getUserSummary(idUsuario);
		
		// Establecer el atributo de index.jsp del frontoffice.
		request.setAttribute("resumenUsuario", resumenUsuario);
		
		
		// Cuidado con la URL del servlet ("/views/frontoffice/inicio") ya que al hacer forward se sustituye la última
		// parte (inicio) por la variable pagina (ver más abajo).
		
		// El forward resuelve la URL de la siguiente manera:
		// "/views/frontoffice/inicio" + "index.jsp"  =  "/views/frontoffice/index.jsp"
		
		String pagina = "index.jsp"; // index.jsp de frontoffice, no index.jsp público.
		LOG.debug("forward: " + pagina);
		
		request.getRequestDispatcher(pagina).forward(request, response);
		
	} // doGet
	

	// Si se elimina este método, al iniciar sesión en el backoffice dará un error 405.
	// HTTP Status 405 – Method Not Allowed HTTP method POST is not supported by this URL
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

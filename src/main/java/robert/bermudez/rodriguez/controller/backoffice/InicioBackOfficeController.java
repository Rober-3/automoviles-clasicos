package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.controller.frontoffice.InicioFrontOfficeController;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasClasico;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasMarca;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasUsuario;

/**
 * Dirige a la pantalla de inicio del backoffice.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 */
@WebServlet("/views/backoffice/inicio")
public class InicioBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
    private static final ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
    private static final MarcaDAOImpl daoMarca = MarcaDAOImpl.getInstance();
    private static final UsuarioDAOImpl daoUsuario = UsuarioDAOImpl.getInstance();

	/**
	 * Dirige a la pantalla de inicio del backoffice.
	 * 
	 * <dt>Variables
	 * 	   <dd><b>String pagina</b> Ruta de acceso a la página de inicio del backoffice.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.trace("Panel de inicio.");
		
		EstadisticasClasico estadisticasClasicos = daoClasico.getClassicStatistics();
		EstadisticasMarca estadisticasMarcas = daoMarca.getBrandStatistics();
		EstadisticasUsuario estadisticasUsuarios = daoUsuario.getUserStatistics();
		String encabezado = "Mi panel";
		
		String pagina = "index.jsp"; // backoffice/index.jsp
		LOG.debug("forward: " + pagina);

		request.setAttribute("estadisticasClasicos", estadisticasClasicos);
		request.setAttribute("estadisticasMarcas", estadisticasMarcas);
		request.setAttribute("estadisticasUsuarios", estadisticasUsuarios);
		request.setAttribute("encabezado", encabezado );
		request.getRequestDispatcher(pagina).forward(request, response);

	} // doGet

	// Si se elimina este método, al iniciar sesión en el backoffice dará un error 405.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

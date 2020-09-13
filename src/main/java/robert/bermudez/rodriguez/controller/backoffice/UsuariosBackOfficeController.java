package robert.bermudez.rodriguez.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Muestra en el backoffice una lista con todos los usuarios registrados en la base de datos para que el administrador
 * pueda eliminarlos o actualizar sus datos.
 */
@WebServlet("/views/backoffice/usuarios")
public class UsuariosBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UsuariosBackOfficeController.class);
	private static final UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			usuarios = dao.getAll();
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("usuarios.jsp").forward(request, response);
		
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

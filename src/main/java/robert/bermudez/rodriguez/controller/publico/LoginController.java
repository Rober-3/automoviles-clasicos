package robert.bermudez.rodriguez.controller.publico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Rol;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Realiza el inicio de sesión en la aplicación.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Proceso para el inicio de sesión.
	 * <ol>
	 *     <li>Recibe parámetros del usuario.</li>
	 *     <li>En base a esos parámetros comprueba que ese usuario esté registrado, lanzando una excepción si no lo está.</li>
	 *     <li>Evalúa que los parámetros se hayan introducido, mostrando un mensaje de advertencia en caso de estar vacío(s) alguno(s).</li>
	 *     <li>Comprueba que el nombre de usuario y la contraseña sean correctos, mostrando una advertencia si tienen errores.</li>
	 *     <li>Si el nombre y la contraeña son correctos usa el atributo Rol del usuario para redirigir a la vista correspondiente.</li>
	 * </ol>
	 * <dl>
	 * 	   <dt>Variables
	 * 		   <dd><b>UsuarioDAOImpl dao</b> Instancia de UsuarioDAOImpl para aplicar métodos a la clase Usuario.
	 * 		   <dd><b>Alerta alerta</b> Muestra mensajes en pantalla al usuario.
	 * 		   <dd><b>String ruta</b> Almacena la ruta para que el controlador redirija a la vista correspondiente.
	 * 		   <dd><b>HttpSession session</b> Guarda en la sesión los atributos del usuario.
	 * 	   <dt>
	 *	   <dt>
	 *	   <dt>Parámetros del formulario.
	 *		   <dd><b>nombre (String</b> Nombre.
	 *		   <dd><b>contrasena (String)</b> Contraseña.
	 * </dl>
	 * @see robert.bermudez.rodriguez.controller.publico.Alerta
	 * @see robert.bermudez.rodriguez.modelo.pojo.Usuario
	 * @see robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Alerta alerta = new Alerta();
		String ruta = "";

		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena"); 
		HttpSession session = request.getSession();

		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		Usuario usuario = dao.exists(nombre, contrasena);

		if (usuario != null) {

			// request.setAttribute("usuario", usuario);

			// Se invalida la sesión del usuario si está 5 minutos sin hacer peticiones.
			// session.setMaxInactiveInterval( 60 * 5 );

			// Aquí se ejecutará automáticamente el método attributeAdded del listener UsuariosLogueadosListener. Este
			// atributo permitirá contar los usuarios registrados que han iniciado sesión.
			session.setAttribute("usuario", usuario); // @see UsuarioLogueadosListener => attributeAdded

			alerta = new Alerta("success", "Has iniciado sesión correctamente.");

			// En función del valor del atributo de Rol(ADMINISTRADOR o USUARIO) redirige al backoffice o al frontoffice.
			if (usuario.getRol().getId() == Rol.ADMINISTRADOR) {
				ruta = "views/backoffice/inicio"; // InicioBackOfficeController

			} else {
				// ruta = "views/frontoffice/index.jsp"; // Si inicia sesión un usuario normal tiene que pasar por un controlador
				ruta = "views/frontoffice/inicio"; // InicioFrontOfficeController
			}

			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(ruta).forward(request, response);

		} else {
			alerta = new Alerta ("warning", "Credenciales incorrectas.");
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("views/public/login.jsp").forward(request, response);
		}

	} // doPost

} // class

package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Rol;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		Alerta alerta = null;
		String ruta = "";

		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena"); 
		
		HttpSession session = request.getSession();

		try {
			
			Usuario usuario = dao.exists(nombre, contrasena);

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

			if (violations.isEmpty()) {

				if (nombre.equals(usuario.getNombre()) && contrasena.equals(usuario.getContrasena())) {
					
					//request.setAttribute("usuario", usuario);
					
					// Se invalida la sesión del usuario si está 5 minutos sin hacer peticiones.
					// session.setMaxInactiveInterval( 60 * 5 );
					
					// Aquí se ejecutará automáticamente el método attributeAdded del listener UsuariosLogueadosListener. Este
					// atributo permitirá contar los usuarios registrados que han iniciado sesión.
					session.setAttribute("usuario", usuario); // @see UsuarioLogueadosListener => attributeAdded
					
					alerta = new Alerta("success", "Has iniciado sesión correctamente.");
					
					// En función del valor del atributo (ADMINISTRADOR o USUARIO) de Rol redirige al backoffice o al frontoffice.
					if (usuario.getRol().getId() == Rol.ADMINISTRADOR) {
						ruta = "views/backoffice/inicio";
						
					} else {
						// ruta = "views/frontoffice/index.jsp";
						// Si inicia sesión un usuario normal tiene que pasar por un controlador
						ruta = "views/frontoffice/inicio";
					}
					
					//ruta = "clasicos";

				} else {
					
					alerta = new Alerta("danger","El usuario y/o la contraseña son erróneos.");
					ruta = "views/login.jsp";

				} // if-else interno


			} else {

				String errores = "";

				for (ConstraintViolation<Usuario> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";

				} // for

				alerta = new Alerta ("warning", errores);
				ruta = "views/login.jsp";

			} // if-else externo


		} catch (Exception e) {
			alerta = new Alerta ("danger", "Hay un problema: " + e.getMessage());


		} finally {
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(ruta).forward(request, response);

		}  // try-catch-finally

	} // doPost
	
} // class

package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import robert.bermudez.rodriguez.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.Rol;
import robert.bermudez.rodriguez.modelo.Usuario;

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

	} // doGet



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = new Usuario();
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		Alerta alerta = null;
		String ruta = "";


		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena");


		try {

			usuario.setNombre(nombre);
			usuario.setContrasena(contrasena);


			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);


			if (violations.isEmpty()) {

				usuario = dao.exists(nombre, contrasena);

				if (nombre.equals(usuario.getNombre()) && contrasena.equals(usuario.getContrasena())) {
					
					alerta = new Alerta("success", "Has iniciado sesión correctamente.");
					request.setAttribute("usuario", usuario);
					
					// En función del valor del atributo (ADMINISTRADOR o USUARIO) de Rol redirige al backoffice o al frontoffice.
					if (usuario.getRol().getId() == Rol.ADMINISTRADOR) {
						ruta = "views/backoffice/index.jsp";
						
					} else {
						ruta = "views/frontoffice/index.jsp";
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

				alerta = new Alerta ("danger", errores);
				ruta = "views/login.jsp";

			} // if-else externo


		} catch (Exception e) {
			alerta = new Alerta ("danger", "No existen en la base de datos usuarios con ese nombre y esa contraseña.");


		} finally {
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(ruta).forward(request, response);

		}  // try-catch-finally

	} // doPost

} // class

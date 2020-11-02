package robert.bermudez.rodriguez.controller.publico;

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

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.daoimpl.UsuarioDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Rol;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/registro")
public class RegistrarUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistrarUsuarioController.class);
    private static UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.trace("iniciando registro");
		
		Alerta alerta = new Alerta();
		boolean isError = true;
		
		String nombre = request.getParameter("nombre");
		// String fecha = request.getParameter("fecha");
		String contrasena = request.getParameter("contrasena");
		String confirmar = request.getParameter("confirmar");
		
		try {
			
			if (!contrasena.equals(confirmar)) {
				alerta = new Alerta("warning", "La contraseña y su confirmación deben coincidir.");
				
			} else {

				Rol rol = new Rol();
				rol.setId(Rol.USUARIO);
				
				Usuario usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setContrasena(contrasena);
				usuario.setRol(rol);
				
				Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
				
				if (violations.isEmpty()) {
					
					try {
						dao.insert(usuario);
						isError = false;
						alerta = new Alerta("success", "Registro realizado con éxito.");
						LOG.info("Nuevo usuario registrado: " + usuario);
						
					} catch (Exception e) {
						alerta = new Alerta("warning", "Nombre de usuario ya registrado. Escoge otro.");
						LOG.error(e);
					}
					
				} else {
					String errores = "";

					for (ConstraintViolation<Usuario> v : violations) {
						errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";
					}
					alerta = new Alerta ("warning", errores);
					
				} // if-else externo
				
			} // if-else externo
			
		} catch (Exception e) {
			alerta = new Alerta("danger", e.getMessage());
			LOG.error(e);
			
		} finally {
			
			// Si se guarda una alerta en una request, al hacer sendRedirect se perderá. La solución está en guardarla en la sesión.
			request.getSession().setAttribute("alerta", alerta);
			
			if (isError) {
				// Envia de nuevo el nombre para evitar que el usuario tenga que escribirlo de nuevo.
				request.setAttribute("nombre", nombre);
				request.getRequestDispatcher("views/public/registro.jsp").forward(request, response);
				
			} else {
				response.sendRedirect(request.getContextPath() + "views/public/login.jsp");
			}
			
		} // try-catch-finally
		
	} // doPost

} // class

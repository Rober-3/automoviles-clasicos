package robert.bermudez.rodriguez.controller.frontoffice;

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

import robert.bermudez.rodriguez.modelo.dao.SeguridadException;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;
import robert.bermudez.rodriguez.controller.Alerta;

@WebServlet("/views/frontoffice/insertar")
public class InsertarFrontOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsertarFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id"); // Recogida de parámetros de clasicos.jsp del frontoffice.
		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = new Alerta();
		
		try {
			
			int idModelo = Integer.parseInt(id);
			
			usuario = (Usuario) request.getSession().getAttribute("usuario"); // Recuperar el usuario de la sesión.
			
			// Para evitar que usuarios no autorizados manipulen clásicos (a través de las URL o los enlaces para editar o 
			// eliminar) que no han registrado, se comprueba por medio del método getByIdByUser, pasándole como parámetros
			// el id del clásico y el id de usuario, que el clásico al que se intenta acceder pertenece a quien lo registró.
			// Cuidado porque cuando un clásico se inicializa no pertenece a ningún usuario, y como su id = 0 si se ejecuta
			// getByIdByUser se generará una excepción. Por tanto, sólo hay que recuperar un clásico si ya está en la BBDD.
			
			int idUsuario = usuario.getId();
			
			if (idModelo != 0) {
				clasico = dao.getByIdByUser(idUsuario, idModelo);
				alerta = new Alerta("warning", "Modifica los datos del clásico.");
			}
			
			
		} catch (SeguridadException e) {
			LOG.error("Un usuario ha intentado modificar un clásico que no ha registrado: " + usuario);
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario-clasicos.jsp").forward(request, response);
		}
		
	} // doGet

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = new Alerta();
		
		// Recogida de parámetros de formulario-clasicos.jsp del frontoffice.
		String id = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String paramIdMarca = request.getParameter("id_marca");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");

		try {

			int idModelo = Integer.parseInt(id);
			int idMarca = Integer.parseInt(paramIdMarca);

			Marca marca = new Marca(idMarca);

			// Recuperar el usuario de la sesión.
			usuario = (Usuario) request.getSession().getAttribute("usuario");

			// Comprobar que el clásico pertenece al usuario que lo registró.
			int idUsuario = usuario.getId();
			
			if (idModelo != 0) {
				clasico = dao.getByIdByUser(idUsuario, idModelo);
				alerta = new Alerta("warning", "Introduce los datos del clásico.");
			}
			
			clasico.setId(idModelo);
			clasico.setModelo(modelo);
			clasico.setMarca(marca);
			clasico.setAnio(anio);
			clasico.setFoto(foto);
			clasico.setUsuario(usuario);

			// Comprueba si se han rellenado los campos requeridos. En caso contrario muestra mensajes de advertencia.
			Set<ConstraintViolation<Clasico>> violations = validator.validate(clasico);

			if (violations.isEmpty()) {

				if (idModelo == 0) {
					dao.insert(clasico);
					alerta = new Alerta("success", "Clásico registrado en espera de aprobación por parte del administrador.");

				} else {
					dao.updateByUser(clasico);
					alerta = new Alerta("success", "Clásico actualizado en espera de aprobación por parte del administrador.");	
				}

			} else {
				String errores = "";

				for (ConstraintViolation<Clasico> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";
				}
				alerta = new Alerta ("warning", errores);

			} // if-else externo

		} catch (SeguridadException e) {
			LOG.error("Un usuario ha intentado modificar un clásico que no ha registrado: " + usuario);
			
		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta ("danger", e.getMessage());

		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario-clasicos.jsp").forward(request, response);
		}

	} // doPost

} // class

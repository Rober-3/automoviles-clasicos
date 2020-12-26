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

import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.dao.SeguridadException;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/frontoffice/insertar-editar-clasico")
public class InsEditClasFrontOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsEditClasFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = null;
		String encabezado = "Nuevo clásico";

		String id = request.getParameter("id"); // frontoffice/clasicos.jsp

		try {

			usuario = (Usuario) request.getSession().getAttribute("usuario");

			int idUsuario = usuario.getId();
			int idModelo = Integer.parseInt(id);
			
			if (idModelo != 0) {
				clasico = dao.getByIdByUser(idUsuario, idModelo);
				alerta = new Alerta("warning", "Modifica los datos del clásico.");
				encabezado = "Editar clásico";
			}

		} catch (SeguridadException e) {
			LOG.error("Un usuario ha intentado modificar un clásico que no ha registrado: " + usuario);

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.setAttribute("encabezado", encabezado);
			request.getRequestDispatcher("formulario-clasicos.jsp").forward(request, response);
		}

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = new Alerta();

		// frontoffice/formulario-clasicos.jsp
		String id = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String paramIdMarca = request.getParameter("id_marca");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");

		try {

			int idModelo = Integer.parseInt(id);
			int idMarca = Integer.parseInt(paramIdMarca);

			Marca marca = new Marca(idMarca);

			usuario = (Usuario) request.getSession().getAttribute("usuario");

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

			Set<ConstraintViolation<Clasico>> violations = validator.validate(clasico);

			if (violations.isEmpty()) {

				if (idModelo == 0) {
					dao.insert(clasico);
					alerta = new Alerta("success", "<b>" + clasico.getMarca().getMarca() + " " + clasico.getModelo()
							+ "</b> guardado en espera de la aprobación por parte del administrador.");

				} else {
					dao.updateByUser(clasico);
					alerta = new Alerta("success", "<b>" + clasico.getMarca().getMarca() + " " + clasico.getModelo()
							+ "</b> actualizado en espera de aprobación por parte del administrador.");
				}

			} else {
				String errores = "";

				for (ConstraintViolation<Clasico> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: " + v.getMessage() + "</p>";
				}
				alerta = new Alerta("warning", errores);

			} // if-else externo

		} catch (SeguridadException e) {
			LOG.error("Un usuario ha intentado modificar un clásico que no ha registrado: " + usuario);

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta("danger", "Ha surgido un problema al intentar guardar <b>"
					+ clasico.getMarca().getMarca() + " " + clasico.getModelo() + "</b>");

		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario-clasicos.jsp").forward(request, response);
		}

	} // doPost

} // class

package robert.bermudez.rodriguez.controller.backoffice;

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
import robert.bermudez.rodriguez.modelo.daoimpl.MarcaDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

@WebServlet("/views/backoffice/insertar-editar-marca")
public class InsEditMarcaBackOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsEditMarcaBackOfficeController.class);
	private static final MarcaDAOImpl dao = MarcaDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Marca marca = new Marca();
		Alerta alerta = null;
		String encabezado = "Nueva marca";

		String id = request.getParameter("id"); // backoffice/marcas.jsp

		try {

			int idMarca = Integer.parseInt(id);

			if (idMarca != 0) {
				marca = dao.getById(idMarca);
				alerta = new Alerta("warning", "Modifica los datos de la marca.");
				encabezado = "Editar marca";
			}

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.setAttribute("marca", marca);
			request.setAttribute("alerta", alerta);
			request.setAttribute("encabezado", encabezado);
			request.getRequestDispatcher("formulario-marcas.jsp").forward(request, response);
		}

	} // doGet
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Marca marca = new Marca();
		Alerta alerta = null;
		
		// views/backoffice/formulario-marcas.jsp
		String paramIdMarca = request.getParameter("id");
		String paramMarca = request.getParameter("marca");
		
		try {

			int idMarca = Integer.parseInt(paramIdMarca);

			marca = new Marca(idMarca);

			if (idMarca != 0) {
				marca = dao.getById(idMarca);
				alerta = new Alerta("warning", "Introduce los nuevos datos de la marca.");
			}
			
			marca.setId(idMarca);
			marca.setMarca(paramMarca);

			Set<ConstraintViolation<Marca>> violations = validator.validate(marca);

			if (violations.isEmpty()) {

				if (idMarca == 0) {
					dao.insert(marca);
					alerta = new Alerta("success", "<b>" + marca + "</b> guardada correctamente.");

				} else {
					dao.update(marca);
					alerta = new Alerta("success", "<b>" + marca + "</b> actualizada correctamente.");
				}

			} else {
				
				String errores = "";

				for (ConstraintViolation<Marca> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";
				}
				
				alerta = new Alerta ("warning", errores);

			} // if-else externo

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta ("danger", "Ha surgido un problema al intentar guardar <b>" + marca + "</  b>");

		} finally {
			request.setAttribute("marca", marca);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario-marcas.jsp").forward(request, response);
		}

	} // doPost

} // class

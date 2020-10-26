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

import robert.bermudez.rodriguez.controller.frontoffice.InsEditClasFrontOfficeController;
import robert.bermudez.rodriguez.controller.publico.Alerta;
import robert.bermudez.rodriguez.modelo.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

@WebServlet("/views/backoffice/insertar-editar-clasico")
public class InsEditClasBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsEditClasFrontOfficeController.class);
	private static final ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Clasico clasico = new Clasico();
    	Alerta alerta = new Alerta();
    	String ruta = "";
    	
    	// views/backoffice/clasicos.jsp
    	String id = request.getParameter("id");
    	String validado = request.getParameter("validado");
    	
    	try {
    		
    		int idModelo = Integer.parseInt(id);
    		
    		if (idModelo != 0) {
    			clasico = dao.getById(idModelo);
				alerta = new Alerta("warning", "Modifica los datos del clásico.");
				ruta = "formulario-clasicos.jsp";
				
			} else if (idModelo != 0  && validado != null) {
				dao.validate(idModelo);
				alerta = new Alerta("success", "Clásico validado con éxito.");
				ruta = "index.jsp";
			}
    		
		} catch (Exception e) {
			LOG.error(e);

		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(ruta).forward(request, response);
		}
    	
	} // doGet

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		Usuario usuario = new Usuario();
		Alerta alerta = new Alerta();
		
		// views/backoffice/formulario-clasicos.jsp
		String paramIdModelo = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String paramIdMarca = request.getParameter("id_marca");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");

		try {

			int idModelo = Integer.parseInt(paramIdModelo);
			int idMarca = Integer.parseInt(paramIdMarca);

			Marca marca = new Marca(idMarca);

			usuario = (Usuario) request.getSession().getAttribute("usuario"); // Recuperar el usuario de la sesión.

			if (idModelo != 0) {
				clasico = dao.getById(idModelo);
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
					alerta = new Alerta("success", "Clásico registrado.");

				} else {
					dao.update(clasico);
					alerta = new Alerta("success", "Clásico actualizado.");	
				}

			} else {
				String errores = "";

				for (ConstraintViolation<Clasico> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";
				}
				alerta = new Alerta ("warning", errores);

			} // if-else externo

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

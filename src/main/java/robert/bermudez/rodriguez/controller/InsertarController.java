 package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.sql.SQLException;
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

import robert.bermudez.rodriguez.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

/**
 * Servlet implementation class InsertarController
 */
@WebServlet("/insertar-editar")
public class InsertarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
	// Con InicioAppListener ya no es necesario llamar al DAO de las marcas.
	// private static MarcaDAOImpl daoMarca = MarcaDAOImpl.getInstance();
	
	// Objeto Validator  al que se le pasa el POJO para ver si cumple las validaciones.
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Haciendo un new de alerta se mostrará su div sin un mensaje, ya que al inicializarla se establecen como vacíos
		// sus atributos y se cumple la condición <c:if test="${not empty alerta}">
		Alerta alerta = null;
		
		Clasico clasico = new Clasico(); // Inicializa un clásico para obtener un id == 0.
		// ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try {

			// marcas = daoMarca.getAll();
			
			String paramId = request.getParameter("id");

			
			if (paramId != null) { // Si id no es null obtiene un clásico de la BBDD para actualizarlo.

				int id = Integer.parseInt(paramId);
				// ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
				clasico = daoClasico.getById(id);
				alerta = new Alerta("primary","Modifica los campos.");

			} // if

			
		} catch (Exception e) {
			alerta = new Alerta("danger","Ha habido un problema: " + e.getMessage());
			e.printStackTrace();

			
		} finally {
			request.setAttribute("clasico", clasico);
			// request.setAttribute("marcas", marcas);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("views/formulario.jsp").forward(request, response);

		} // try-catch-finally

	} // doGet



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
		Clasico clasico = new Clasico();
		Alerta alerta = new Alerta();


		// Recogida de parámetros enviados desde la vista.
		String paramId = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");
		String paramIdMarca = request.getParameter("id_marca");


		try {

			int id = Integer.parseInt(paramId);
			int id_marca = Integer.parseInt(paramIdMarca);
			
			// Para setear la marca no es necesario usar el getById, puede emplearse un constructor personalizado al que se
			// le pase como único parámetro el id de la marca. De esta forma se evita utilizar el dao.
			// marca = daoMarca.getById(id_marca);
			Marca marca = new Marca(id_marca);

			clasico.setId(id);
			clasico.setModelo(modelo);
			clasico.setMarca(marca);
			clasico.setAnio(anio);
			clasico.setFoto(foto);
			
			// Devuelve un set con todas las validaciones.
			Set<ConstraintViolation<Clasico>> violations = validator.validate(clasico);

			
			if (violations.isEmpty()) { // Sin errores de validación pueden guardarse registros en la BBDD.

				
				// Estas validaciones ya no son necesarias ya que se hacen con Bean Validation.
				// insert si el id es 0 (no existe un id 0 en una BBDD), si no update.
				// if (modelo.length()<3 || modelo.length()>50) {
				// alerta = "El modelo debe tener entre 3 y 50 caracteres y no estar repetido. Inténtalo de nuevo.";

				// } else if (id == 0 && modelo.length()>=3 && modelo.length()<=50) {

				
				
				if (id == 0) {
					daoClasico.insert(clasico);
					alerta = new Alerta("success","Modelo guardado con éxito.");
					
				} else {
					daoClasico.update(clasico);
					alerta = new Alerta("success","Dato(s) actualizado(s) con éxito.");
					
				} // if-else interno

				
			} else { // Si alguna de las propiedades del POJO no cumple con las validaciones habrá errores de validación.
				
				String errores = "";
				
				// Recorre el set de violaciones y almacena el resultado para mostrarlo en pantalla. getPropertyPath() es
				// opcional y devuelve la propiedad del POJO que no cumple la validación correspondiente.
				for (ConstraintViolation<Clasico> v : violations) {
					errores += "<p> <b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";
					
				} // for
				
				alerta = new Alerta("danger",errores);

			} // if-else externo
			
			
		} catch (SQLException e) {
			// TODO CORREGIR ESTE ERROR: 
			// AL INTRODUCIR UN NUEVO CLÁSICO E INTENTAR GUARDARLO EN LA BBDD ENTRA POR ESTE CATCH Y EN APARIENCIA NO SE
			// GUARDA, PERO YENDO A LA TABLA DE CLÁSICOS APARECERÁ.
			
			// Captura esta excepción en caso de tratar de introducir un modelo duplicado: dentro de la tabla clasicos hay
			// una constraint en el campo modelo que indica que es único.
			alerta = new Alerta("danger","Modelo duplicado.");
			e.printStackTrace();

			

		} catch (Exception e) {
			alerta = new Alerta("danger","Ha habido un problema: " + e.getMessage());
			e.printStackTrace();

			
		} finally {

			// Enviar atributos a la vista.
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);

			// Ir a la vista.
			request.getRequestDispatcher("views/formulario.jsp").forward(request, response);

		} // try-catch-finally

	} // doPost

} // class

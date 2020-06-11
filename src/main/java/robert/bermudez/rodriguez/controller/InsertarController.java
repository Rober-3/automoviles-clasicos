package robert.bermudez.rodriguez.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import robert.bermudez.rodriguez.interfaces.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;

/**
 * Servlet implementation class InsertarController
 */
@WebServlet("/insertar-editar")
public class InsertarController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico(); // Inicializa un clásico para obtener un id == 0.
		String alerta = "";

		try {

			String paramId = request.getParameter("id");
			
			if (paramId != null) { // Si id no es null obtiene un clásico de la BBDD para actualizarlo.

				int id = Integer.parseInt(paramId);
				ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
				clasico = dao.getById(id);
				alerta = "Modifica los campos.";

			} // if

		} catch (Exception e) {
			alerta = "Ha habido un problema." + e.getMessage();
			e.printStackTrace();

		} finally {
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("formulario.jsp").forward(request, response);

		} // try-catch-finally

	} // doGet



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Clasico clasico = new Clasico();
		ClasicoDAOImpl dao = ClasicoDAOImpl.getInstance();
		String alerta = "";

		// Recogida de parámetros enviados desde la vista.
		String paramId = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String marca = request.getParameter("marca");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");

		try {

			int id = Integer.parseInt(paramId);

			clasico.setId(id);
			clasico.setModelo(modelo);
			clasico.setMarca(marca);
			clasico.setAnio(anio);
			clasico.setFoto(foto);

			// insert si el id es 0 (no existe un id 0 en una BBDD), si no update.

			if (modelo.length()<3 || modelo.length()>50) {
				alerta = "El modelo debe tener entre 3 y 50 caracteres y no estar repetido. Inténtalo de nuevo.";

			} else if (id == 0 && modelo.length()>=3 && modelo.length()<=50) {
				dao.insert(clasico);
				alerta = "Modelo guardado con éxito.";

			} else {
				dao.update(clasico);
				alerta = "Dato(s) actualizado(s) con éxito.";
			}

		} catch (SQLException e) {
			// Captura esta excepción en caso de tratar de introducir un modelo duplicado: dentro de la tabla clasicos hay
			// una constraint en el campo modelo que indica que es único.
			alerta = ("Modelo duplicado.");
			e.printStackTrace();


		} catch (Exception e) {
			alerta = "Ha habido un problema: " + e.getMessage();
			e.printStackTrace();

		} finally {

			// Enviar atributos a la vista.
			request.setAttribute("clasico", clasico);
			request.setAttribute("alerta", alerta);

			// Ir a la vista.
			request.getRequestDispatcher("formulario.jsp").forward(request, response);

		} // try-catch

	} // doPost

} // class

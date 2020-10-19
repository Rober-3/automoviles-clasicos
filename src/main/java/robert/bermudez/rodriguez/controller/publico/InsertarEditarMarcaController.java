package robert.bermudez.rodriguez.controller.publico;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.modelo.pojo.Marca;

@WebServlet("/insertar-editar-marca")
public class InsertarEditarMarcaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InsertarEditarMarcaController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Marca marca = new Marca();	
		request.setAttribute("marca", marca);
		request.getRequestDispatcher("views/public/formulario-marcas.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		String paramMarca = request.getParameter("marca");
		Marca marca = new Marca();
		Alerta alerta = null;
		
		try {
			int id = Integer.parseInt(paramId);
			marca.setId(id);
			marca.setMarca(paramMarca);
			alerta =  new Alerta ("success", "Nueva marca insertada con éxito.");
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("marca", marca);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher("views/public/formulario-marcas").forward(request, response);
		}
		
	} // doPost

} // class

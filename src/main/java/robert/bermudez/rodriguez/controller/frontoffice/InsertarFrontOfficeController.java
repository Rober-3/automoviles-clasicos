package robert.bermudez.rodriguez.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.daoimpl.ClasicoDAOImpl;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

/**
 * Servlet implementation class InsertarFrontOfficeController
 */
@WebServlet("/views/frontoffice/insertar")
public class InsertarFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Clasico clasico = new Clasico();
		
		request.setAttribute("clasico", clasico);
		request.getRequestDispatcher("views/frontoffice/formulario-clasicos.jsp").forward(request, response);
		
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClasicoDAOImpl daoClasico = ClasicoDAOImpl.getInstance();
		Clasico clasico = new Clasico();
		
		String paramId = request.getParameter("id");
		String modelo = request.getParameter("modelo");
		String paramIdMarca = request.getParameter("id_marca");
		String anio = request.getParameter("anio");
		String foto = request.getParameter("foto");
		
		try {
			
			int id = Integer.parseInt(paramId);
			int id_marca = Integer.parseInt(paramIdMarca);
			
			Marca marca = new Marca(id_marca);
			
			clasico.setId(id);
			clasico.setModelo(modelo);
			clasico.setMarca(marca);
			clasico.setAnio(anio);
			clasico.setFoto(foto);
			
			if (id == 0) {
				daoClasico.insert(clasico);
				
			} else {
				daoClasico.update(clasico);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("clasico", clasico);
			request.getRequestDispatcher("views/frontoffice/clasicos.jsp").forward(request, response);
		}
		
	} // doPost

} // class

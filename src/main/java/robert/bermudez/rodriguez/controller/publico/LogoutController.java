package robert.bermudez.rodriguez.controller.publico;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// Cuando un usuario cierra sesión se ejecuta el método attributeRemoved en UsuariosLogueadosListener.
		session.invalidate(); // @see UsuariosLogueadosListener => attributeRemoved
		
		session = null;
		
		request.setAttribute("alerta", new Alerta("success","Has cerrado sesión correctamente."));
		
		request.getRequestDispatcher("inicio").forward(request, response);
		
	} // doGet

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	} // doPost

} // class

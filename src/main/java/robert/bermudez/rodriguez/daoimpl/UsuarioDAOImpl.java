package robert.bermudez.rodriguez.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.dao.UsuarioDAO;
import robert.bermudez.rodriguez.modelo.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {

	// Singleton

	private static UsuarioDAOImpl INSTANCE = null;

	private UsuarioDAOImpl() {
		super();
	}

	public static UsuarioDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}

		return INSTANCE;

	} // getInstance


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	// DAO

	// Querys
	String SQL_GET_BY_ID = "SELECT id, nombre, contrasena, imagen FROM usuarios WHERE id = ?:";
	String SQL_GET_ALL = "SELECT id, nombre, contrasena, imagen FROM usuarios ORDER BY id DESC;";
	String SQL_EXISTS = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?;";


	// Métodos

	@Override
	public Usuario getById(int id) throws Exception {

		Usuario usuario = new Usuario();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);

				) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setContrasena("contrasena");
				usuario.setImagen("imagen");
			}

		} catch (Exception e) {
			throw new Exception("No existen usuarios en la base de datos con el id " + id);

		} // try-catch

		return usuario;
	}



	@Override
	public ArrayList<Usuario> getAll() throws Exception {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();

				) {

			while (rs.next()) {

				Usuario usuario = new Usuario();

				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setImagen(rs.getString("imagen"));

				usuarios.add(usuario);

			} // while

		} catch (Exception e) {
			e.printStackTrace();

		} // try-catch

		return usuarios;

	} // getAll



	@Override
	public Usuario insert(Usuario pojo) throws Exception {
		return null;
	} // insert

	
	
	@Override
	public Usuario update(Usuario pojo) throws Exception {
		return null;
	} // update

	
	
	@Override
	public Usuario delete(int id) throws Exception {
		return null;
	} // delete



	@Override
	public Usuario exists(String nombre, String contrasena) throws Exception {
		
		Usuario usuario = new Usuario();
		
		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_EXISTS);
				
				) {
			
			pst.setString(1, nombre);
			pst.setString(2, contrasena);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setImagen(rs.getString("imagen"));
				
			} // if
			
		} catch (Exception e) {
			throw new Exception("No existen en la base de datos usuarios con el nombre " + nombre + " y la contrasena "
					+ contrasena + ".");

		} // try-catch
		
		
		return usuario;

		// Acceder al método getAll() de esta manera podría ocasionar que, en el hipotético
		// caso de que la BBDD tuviese millones de registros, se desbordase la variable.
		// ArrayList<Usuario> usuarios = getAll();
		
		// Usuario usuario = new Usuario();

		// for (Usuario u : usuarios) {

			// if (u.getNombre().equals(nombre) && u.getContrasena().equals(contrasenia)) {

				// usuario.setId(u.getId());
				// usuario.setNombre(u.getNombre());
				// usuario.setContrasena(u.getContrasena());
				// usuario.setImagen(u.getImagen());

			// } // if

		// } // for

	} // exists

} // class

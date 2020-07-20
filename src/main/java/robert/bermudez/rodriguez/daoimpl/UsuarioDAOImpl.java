package robert.bermudez.rodriguez.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.dao.UsuarioDAO;
import robert.bermudez.rodriguez.modelo.Rol;
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
	
	private static final String SQL_GET_BY_ID = "SELECT u.id, nombre, contrasena, imagen, id_rol, rol "
											  + "FROM usuarios u, roles r "
											  + "WHERE id_rol = r.id AND u.id = ?:";
	private static final String SQL_GET_ALL = 	"SELECT u.id, nombre, contrasena, imagen, id_rol, rol "
											  + "FROM usuarios u, roles r "
											  + "WHERE id_rol = r.id "
											  + "ORDER BY u.id LIMIT 500;";
	private static final String SQL_EXISTS = 	"SELECT u.id, nombre, contrasena, imagen, id_rol, rol "
											  + "FROM usuarios u, roles r "
											  + "WHERE id_rol = r.id AND nombre = ? AND contrasena = ?;";

	// Métodos

	@Override
	public Usuario getById(int id) throws Exception {

		Usuario usuario = new Usuario();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);

				) {

			pst.setInt(1, id);
			
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					usuario = mapper(rs);
				
				} else {
					throw new Exception("No existen usuarios en la base de datos con el id " + id);
					
				} // if-else
				
			} // try
			
		} catch (Exception e) {
			e.printStackTrace();

		} // try-catch

		return usuario;
		
	} // getById



	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		
		// Acceder así a getAll() podría ocasionar un desbordamiento de la variable si la BBDD tuviese millones de registros.
		// ArrayList<Usuario> usuarios = getAll();
				

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario;

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();

				) {

			while (rs.next()) {

				usuario = mapper(rs);
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
	public Usuario exists(String nombre, String contrasena) {
		
		Usuario usuario = new Usuario();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_EXISTS);

				) {

			pst.setString(1, nombre);
			pst.setString(2, contrasena);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					usuario = mapper(rs);
				}

			} // try

		} catch (Exception e) {
			e.printStackTrace();

		} // try-catch

		return usuario;
		
		
		
//		Método antes de modificarlo. Lanzaba excepciones que debía recoger LoginController en el doPost.
			
//		Usuario usuario = new Usuario();
//
//		try(
//				Connection con = ConnectionManager.getConnection();
//				PreparedStatement pst = con.prepareStatement(SQL_EXISTS);
//
//				) {
//
//			pst.setString(1, nombre);
//			pst.setString(2, contrasena);
//
//			try (ResultSet rs = pst.executeQuery()) {
//
//				if (rs.next()) {
//
//					usuario = mapper(rs);
//
//				} else {
//					throw new Exception("No existen en la base de datos usuarios con el nombre " + nombre + " y la contrasena "
//								+ contrasena + ".");
//				} // if-else
//					
//			} // try
//
//
//		} catch (Exception e) {
//					e.printStackTrace();
//
//		} // try-catch
//
//		return usuario;
		

	} // exists
	
	private Usuario mapper(ResultSet rs) throws SQLException {
		
		Usuario usuario = new Usuario();
		Rol rol = new Rol();
		
		rol.setId(rs.getInt("id_rol"));
		rol.setRol(rs.getString("rol"));

		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setContrasena(rs.getString("contrasena"));
		usuario.setImagen(rs.getString("imagen"));
		usuario.setRol(rol);
		
		return usuario;
		
	} // mapper

} // class

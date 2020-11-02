package robert.bermudez.rodriguez.modelo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.connection.ConnectionManager;
import robert.bermudez.rodriguez.modelo.dao.UsuarioDAO;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasUsuario;
import robert.bermudez.rodriguez.modelo.pojo.Rol;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {

	private static final Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);

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


	// DAO

	// QUERYS

	private static final String SQL_SELECT_FROM_WHERE =	  "SELECT u.id, nombre, contrasena, imagen, id_rol, rol " +
														  "FROM usuarios u, roles r " +
														  "WHERE id_rol = r.id ";

	private static final String SQL_GET_BY_ID =			  SQL_SELECT_FROM_WHERE + "AND u.id = ?:";
	private static final String SQL_GET_ALL =			  SQL_SELECT_FROM_WHERE + "ORDER BY u.id LIMIT 500;";
	private static final String SQL_EXISTS =			  SQL_SELECT_FROM_WHERE + "AND nombre = ? AND contrasena = ?;";
	private static final String SQL_SEARCH_BY_NAME =	  "SELECT id FROM usuarios WHERE nombre = ?;";
	private static final String SQL_GET_USER_STATISTICS = "SELECT total FROM v_estadisticas_usuarios;";

	private static final String SQL_INSERT =			  "INSERT INTO usuarios (nombre, contrasena, imagen, id_rol) VALUES (?,?,?,?);";
	private static final String SQL_UPDATE =			  "UPDATE usuarios SET nombre = ?, contrasena = ?, imagen = ?, id_rol = ? WHERE id = ?";
	private static final String SQL_DELETE =			  "DELETE FROM usuarios WHERE id = ?;";



	// MÉTODOS

	@Override
	public Usuario getById (int id) throws Exception {

		Usuario usuario = new Usuario();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);

				) {

			pst.setInt(1, id);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					usuario = mapper(rs);

				} else {
					throw new Exception("No existen usuarios en la base de datos con el id " + id);
				}

			} // try

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuario;

	} // getById



	@Override
	public ArrayList<Usuario> getAll() throws Exception {

		// Acceder a getAll() de esta manera podría ocasionar un desbordamiento de la variable si la BBDD tuviese millones de registros.
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
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuarios;

	} // getAll



	@Override
	public boolean searchByName (String nombre) {

		boolean nombreExiste = false;

		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SEARCH_BY_NAME);

				) {

			pst.setString(1, nombre);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					nombreExiste = true;
				}
				
			} // try interno
			
		} catch (Exception e) {
			LOG.error(e);
		} 

		return nombreExiste;

	} // searchByName

	
	@Override
	public boolean checkRole (Usuario usuario) {
		
		if (usuario.getRol().getId() == Rol.ADMINISTRADOR) {
			return true;
			
		} else {
			return false;
		}
		
	} // checkRole
	
	
	@Override
	public EstadisticasUsuario getUserStatistics() {

		EstadisticasUsuario estadisticasUsuarios = new EstadisticasUsuario();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_USER_STATISTICS);
				ResultSet rs = pst.executeQuery()
				) {

			LOG.debug(pst);

			while (rs.next()) { 
				estadisticasUsuarios.setTotal(rs.getInt("total"));
			}

		} catch (Exception e) {
			LOG.error(e);	
		}

		return estadisticasUsuarios;

	} // getAllEstadisticasMarcas


	@Override
	public Usuario insert (Usuario pojo) throws Exception {

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

				){

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getContrasena());
			pst.setString(3, pojo.getImagen());
			pst.setInt(4, pojo.getRol().getId());
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {

				try (ResultSet rsKeys = pst.getGeneratedKeys()) {

					if (rsKeys.next()) {
						int id = rsKeys.getInt(1);
						pojo.setId(id);
					}

				} // try interno

			} else {
				throw new Exception("Ha habido un problema al intentar guardar el usuario " + pojo + ".");
			}

		} // try externo

		return pojo;

	} // insert



	@Override
	public Usuario update (Usuario pojo) throws Exception {

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);

				){

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getContrasena());
			pst.setString(3, pojo.getImagen());
			pst.setInt(4, pojo.getRol().getId());
			pst.setInt(5, pojo.getId());
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos del usuario " + pojo + ".");
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return pojo;

	} // update



	@Override
	public Usuario delete (int idUsuario) throws Exception {

		Usuario usuario = getById(idUsuario);

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);

				){

			pst.setInt(1, idUsuario);
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("Ha habido un problema al tratar de eliminar el usuario con el id " + idUsuario + ".");
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuario;

	} // delete



	@Override
	public Usuario exists (String nombre, String contrasena) {

		Usuario usuario = null;

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_EXISTS);

				) {

			pst.setString(1, nombre);
			pst.setString(2, contrasena);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					usuario = mapper(rs);
				}

			} // try

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuario;

//		Método antes de modificarlo. Lanzaba excepciones que debía recoger LoginController en el doPost.

//		Usuario usuario = new Usuario();

//		try(
//				Connection con = ConnectionManager.getConnection();
//				PreparedStatement pst = con.prepareStatement(SQL_EXISTS);
//
//				) {

//			pst.setString(1, nombre);
//			pst.setString(2, contrasena);

//			try (ResultSet rs = pst.executeQuery()) {

//				if (rs.next()) {
//					usuario = mapper(rs);
//
//				} else {
//					throw new Exception("No existen en la base de datos usuarios con el nombre " + nombre + " y la contrasena "
//								+ contrasena + ".");
//				}

//			} // try

//		} catch (Exception e) {
//					e.printStackTrace();
//		}

//		return usuario;

	} // exists



	private Usuario mapper (ResultSet rs) throws SQLException {

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

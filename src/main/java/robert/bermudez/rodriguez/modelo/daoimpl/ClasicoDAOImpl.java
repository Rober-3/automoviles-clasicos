package robert.bermudez.rodriguez.modelo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO Notas. Clase que emplea el patrón singleton y el patrón DAO. 
//			   - Singleton consta de un constructor privado y un método getInstance().
//			   - DAO implementa los métodos CRUD de de la interfaz (getByid, getAll, insert, update y delete).

import java.util.ArrayList;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.modelo.dao.ClasicoDAO;
import robert.bermudez.rodriguez.modelo.dao.SeguridadException;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;
import robert.bermudez.rodriguez.modelo.pojo.ResumenUsuario;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;


/**
 * Clase que emplea el patrón singleton y el patrón DAO. 
 * 
 * Dentro del singleton se declara un constructor privado que evita que se creen instancias de la clase,
 * y un método getInstance para obtener una única instancia.
 * 
 * El DAO implementa los métodos CRUD básicos: getAll, getById, insert, update y delete.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @see https://github.com/Rober-3/supermercado-java/tree/master/src/main/java/com/ipartek/formacion
 * 
 */
public class ClasicoDAOImpl implements ClasicoDAO {

	private static final Logger LOG = Logger.getLogger(ClasicoDAOImpl.class);
	
	// TODO Notas. Patrón singleton

	private static ClasicoDAOImpl INSTANCE = null;

	private ClasicoDAOImpl() { // Se declara el constructor como privado para evitar crear instancias con new.
		super();
	}

	// getInstance() obtiene una instancia ÚNICA de la clase. Las siguientes ocasiones en las que se traten de crear otras instancias,
	// como INSTANCE ya no será null el método devolverá la clase única creada anteriormente. La palabra synchronized evita que varios
	// hilos o ejecuciones puedan entrar a la vez.
	public static synchronized ClasicoDAOImpl getInstance() {

		if (INSTANCE == null) {

			INSTANCE = new ClasicoDAOImpl();
		}

		return INSTANCE;

	} // getInstance()


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	// TODO Notas. Patrón DAO

	// QUERYS

	// executeQuery devuelve un ResultSet.
	
	private static final String SQL_SELECT_FROM_WHERE =	"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto, " +
														"u.id 'id_usuario', u.nombre 'nombre_usuario' " +
														"FROM clasicos c, marcas m, usuarios u " +
														"WHERE id_marca = m.id AND id_usuario = u.id ";
	
	private static final String SQL_AND_IS_NOT_NULL =	"AND fecha_validacion IS NOT NULL ";
	
	private static final String SQL_GET_BY_ID = SQL_SELECT_FROM_WHERE + "AND c.id = ?;";

	private static final String SQL_GET_ALL
								= SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "ORDER BY c.id DESC LIMIT 500;";

	private static final String SQL_GET_ALL_BY_MODELO
								= SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND modelo LIKE ? ORDER BY c.id DESC LIMIT 500;";
	
	private static final String SQL_GET_ALL_BY_MARCA
								= SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND m.id = ? ORDER BY c.id DESC LIMIT ?;";
	
	private static final String SQL_GET_BY_ID_BY_USER = SQL_SELECT_FROM_WHERE + "AND c.id = ? AND id_usuario = ?;";
	
	private static final String SQL_GET_ALL_BY_USER_CLASICO_VALIDADO
								= SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND id_usuario = ? ORDER BY c.id DESC LIMIT 500;";
	
	private static final String SQL_GET_ALL_BY_USER_CLASICO_SIN_VALIDAR
								= SQL_SELECT_FROM_WHERE + "AND fecha_validacion IS NULL AND id_usuario = ? ORDER BY c.id DESC LIMIT 500;";

	private static final String SQL_GET_RESUMEN_USUARIO
								= "SELECT id_usuario, total, aprobados, pendientes FROM v_clasicos_usuario WHERE id_usuario = ?;";
	
	private static final String SQL_GET_LAST = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "ORDER BY c.id DESC LIMIT ?;";
	
	
	// executeUpdate devuelve un int que representa el número de filas afectadas.
	private static final String SQL_INSERT =	     "INSERT INTO clasicos (modelo, id_marca, anio, foto, id_usuario) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE =		 "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ? WHERE id = ?;";
	private static final String SQL_UPDATE_BY_USER = "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ? WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_DELETE =		 "DELETE FROM clasicos WHERE id = ?;";
	private static final String SQL_DELETE_BY_USER = "DELETE FROM clasicos WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_VALIDAR =		 "UPDATE clasicos SET fecha_validacion = NOW() WHERE id = ?;";
	

	
	// MÉTODOS.

	@Override
	public Clasico getById(int idModelo) throws Exception {

		Clasico clasico = new Clasico();

		try (
				Connection con = ConnectionManager.getConnection(); // Obtener la conexión.
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID); // Preparar el Statement.

			) {

			// Antes de ejecutar la query es imprescindible sustituir todas las ? que contenga.
			pst.setInt(1, idModelo);
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) { // Si encuentra un resultado en el ResultSet...
					clasico = mapper(rs);

				} else {
					throw new Exception("No existen en la base de datos clásicos con el id " + idModelo + ".");

				} // if-else
				
			} // try interno
			
		} // try externo

		return clasico;

	} // getById



	@Override
	public ArrayList<Clasico> getAll() throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();

				) {

			LOG.debug(pst);

			while (rs.next()) { // Mientras encuentre resultados en el ResultSet...
				clasicos.add(mapper(rs));	
			}

		} // try

		return clasicos;

	} // getAll()
	
	

	@Override
	public ArrayList<Clasico> getAllByModelo(String modelo) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_MODELO);

				) {

			pst.setString(1, "%" + modelo + "%");
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				
				while (rs.next()) {
					clasicos.add(mapper(rs));
				}
				
			} // try

		} catch (Exception e) {
			// e.printStackTrace();
			LOG.error(e);
			
		} // try-catch

		return clasicos;
		
	} // getAllByModelo



	@Override
	public ArrayList<Clasico> getAllByMarca(int idMarca, int numReg) {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_MARCA);

				) {

			pst.setInt(1, idMarca);
			pst.setInt(2, numReg);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				
				while (rs.next()) {
					clasicos.add(mapper(rs));
				}
				
			} // try

		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch

		return clasicos;

	} // getAllByMarca

	
	
	@Override
	public Clasico getByIdByUser(int idModelo, int idUsuario) throws SeguridadException, Exception {

		Clasico clasico = new Clasico();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID_BY_USER);

			) {

			// Antes de ejecutar la query es imprescindible sustituir todas las ? que contenga.
			pst.setInt(1, idModelo);
			pst.setInt(2, idUsuario);
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) { // Si encuentra un resultado en el ResultSet...
					clasico = mapper(rs);

				} else {
					throw new SeguridadException();

				} // if-else
				
			} // try interno
			
		} // try externo

		return clasico;
		
	} // getByIdByUser
	
	
	
	@Override
	public ArrayList<Clasico> getAllByUser(int idUsuario, boolean validado) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String query = validado ? SQL_GET_ALL_BY_USER_CLASICO_VALIDADO : SQL_GET_ALL_BY_USER_CLASICO_SIN_VALIDAR;

		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				
				) {

			pst.setInt(1, idUsuario);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				
				while (rs.next()) {
					clasicos.add(mapper(rs));
				}
				
			} // try

		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch

		return clasicos;

	} // getAllByUser
	
	
	
	@Override
	public ResumenUsuario getResumenUsuario(int idUsuario) {
		
		ResumenUsuario resumenUsuario = new ResumenUsuario();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_RESUMEN_USUARIO);

				) {

			pst.setInt(1, idUsuario);
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery()) {
				
				if (rs.next()) { 
					
					resumenUsuario.setIdUsuario(rs.getInt("id_Usuario"));
					resumenUsuario.setClasicosTotal(rs.getInt("total"));
					resumenUsuario.setClasicosAprobados(rs.getInt("aprobados"));
					resumenUsuario.setClasicosPendientes(rs.getInt("pendientes"));
					
				} // if
				
			} // try interno

		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch

		return resumenUsuario;
		
	} // getResumenUsuario

	
	
	@Override
	public ArrayList<Clasico> getLast(int numReg) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_LAST);

				) {

			pst.setInt(1, numReg);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				
				while (rs.next()) {
					clasicos.add(mapper(rs));
				}
				
			} // try

		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch

		return clasicos;
		
	} // getLast
	
	
	
	@Override
	public Clasico insert(Clasico pojo) throws Exception {

		// No es necesario declarar un clásico, con devolver el pojo que admite como argumento es suficiente.
		// Clasico clasico = new Clasico();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				// RETURN_GENERATED_KEYS es una constante de una clase de Java que sirve para devolver la clave generada por la bbdd.

				) {

			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId()); // Marca es un objeto, por tanto hay que acceder a su atributo id.
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getUsuario().getId());
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {

				try (ResultSet rsKeys = pst.getGeneratedKeys()) { // Obtiene el id generado por la bbdd.

					if (rsKeys.next()) {
						int id = rsKeys.getInt(1); // Obtener el id generado automáticamente por la BBDD.
						pojo.setId(id);
					}
					
				} // try interno

			} else {
				throw new Exception("Ha habido un problema al intentar guardar el clásico " + pojo + ".");

			} // if-else

		} // try externo

		return pojo;

	} // insert
	
	
	
	@Override
	public Clasico update(Clasico pojo) throws Exception {

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);

				) {
			
			
			//TODO antes de modificar comprobar el ROL del usuario
			// si es ADMIN hacer la update que tenemos abajo
			// si es USER comprobar que le pertenezca ??
			
			
			
			// throw new SeguridadException();

			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId()); // Marca es un objeto, por tanto hay que acceder a su atributo id.
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getId());
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos del clásico " + pojo + ".");
			}

		} // try

		return pojo;

	} // update



	@Override
	public Clasico delete(int idModelo) throws Exception {

		// Clasico clasico = new Clasico(); -> Erróneo: hay que obtener el clásico antes de eliminarlo.
		Clasico clasico = getById(idModelo);

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);

				) {

			pst.setInt(1, idModelo);
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("No existen en la base de datos clásicos con el id " + idModelo + ".");
			}

		} catch (Exception e) {
			throw new Exception("Ha habido un problema al tratar de borrar " + clasico + ".");

		} // try-catch

		return clasico;

	} // delete	
	
	
	
	@Override
	public Clasico deleteByUser(int idModelo, int idUsuario) throws SeguridadException, Exception {
		
		Clasico clasico = getByIdByUser(idModelo, idUsuario);

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_USER);

				) {

			pst.setInt(1, idModelo);
			pst.setInt(2, idUsuario);
			LOG.debug(pst);

			// Como getById lanza una excepción si se intenta acceder a un clásico no autorizado, con hacer executeUpdate es suficiente.
			pst.executeUpdate();
			
			// int affectedRows = pst.executeUpdate(); 
			// if (affectedRows != 1) {
			//	 throw new SeguridadException();
			// }
			
		} // try
		
		return clasico;

	} // delete
	
	
	
	@Override
	public void validar(int idModelo) {
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_VALIDAR);

				) {

			pst.setInt(1, idModelo);
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos del clásico con el id  " + idModelo + ".");

			} // if

		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch
		
	} // validar
	
	
	
	private Clasico mapper(ResultSet rs) throws SQLException {
		
		Marca marca = new Marca();

		marca.setId(rs.getInt("id_marca"));
		marca.setMarca(rs.getString("marca"));
		
		
		// Para mostrar los clásicos de un usuario en una tabla de su panel su nombre de usuario es irrelevante. Sin embargo, de cara al
		// backoffice será útil mostrar sus datos, o si en la parte pública se quiere mostrar al propietario de un clásico registrado.
		Usuario usuario = new Usuario();
		
		usuario.setId(rs.getInt("id_usuario"));
		usuario.setNombre(rs.getString("nombre_usuario"));

		
		Clasico clasico = new Clasico();

		clasico.setId(rs.getInt("id_modelo"));
		clasico.setModelo( rs.getString("modelo"));
		clasico.setMarca(marca);
		clasico.setAnio( rs.getString("anio"));
		clasico.setFoto( rs.getString("foto"));
		clasico.setUsuario(usuario);

		return clasico;

	} // mapper
	
	
	
	// Método para obtener los clásicos sin utilizar vistas.
//	@Override
//	public ArrayList<Clasico> getAllByUser(int idUsuario, boolean isValidado) {
//		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
//
//		String sql = ( isValidado ) ? SQL_GET_ALL_BY_USER_CLASICO_VALIDADO :  SQL_GET_ALL_BY_USER_CLASICO_SIN_VALIDAR;
//		
//		try (
//				Connection conexion = ConnectionManager.getConnection();
//				PreparedStatement pst = conexion.prepareStatement(sql);
//			) {
//			
//			// pst.setBoolean(1, isValidado); // Sustituye isValidado con un 1 o un 0 dependiendo de la query usada.
//			pst.setNull(1, java.sql.Types.NULL);
//			pst.setInt(1, idUsuario);
//			LOG.debug(pst);
//			
//			try( ResultSet rs = pst.executeQuery() ){				
//				while (rs.next()) {	
//					clasicos.add(mapper(rs));	
//				}
//			}	
//
//		} catch (Exception e) {
//			LOG.error(e);
//		}
//
//		return clasicos;
//		
//	} //  getAllByUser

} // class
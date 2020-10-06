package robert.bermudez.rodriguez.modelo.daoimpl;

// TODO Notas. Clase que emplea el patrón singleton y el patrón DAO. 
//			   - Singleton consta de un constructor privado y un método getInstance().
//			   - DAO implementa los métodos CRUD de de la interfaz (getByid, getAll, insert, update y delete).

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @see 
 * 
 */
public class ClasicoDAOImpl implements ClasicoDAO {

	private static final Logger LOG = Logger.getLogger(ClasicoDAOImpl.class);
	
	// TODO Notas. Patrón singleton

	private static ClasicoDAOImpl INSTANCE = null;

	private ClasicoDAOImpl() { // Se declara el constructor como privado para evitar crear instancias con new.
		super();
	}

	// getInstance() obtiene una instancia ÚNICA de la clase. Las siguientes ocasiones en las que se intente
	// crear instancias, como INSTANCE ya no será null el método devolverá la clase única creada anteriormente.
	// synchronized evita que varios hilos o ejecuciones puedan entrar a la vez.
	public static synchronized ClasicoDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ClasicoDAOImpl();
		}
		return INSTANCE;

	} // getInstance()



	// TODO Notas. Patrón DAO

	// QUERYS

	// executeQuery devuelve un ResultSet.
	
	private static final String SQL_SELECT_FROM_WHERE =	"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto, " +
														"u.id 'id_usuario', u.nombre 'nombre_usuario' " +
														"FROM clasicos c, marcas m, usuarios u " +
														"WHERE id_marca = m.id AND id_usuario = u.id ";
	private static final String SQL_AND_IS_NOT_NULL =	"AND fecha_validacion IS NOT NULL ";
	private static final String SQL_AND_IS_NULL =		"AND fecha_validacion IS NULL ";
	private static final String SQL_AND_ID_USUARIO =	"AND id_usuario = ? ";
	private static final String SQL_AND_C_ID =			"AND c.id = ?;";
	private static final String SQL_ORDER_BY =			"ORDER BY c.id DESC LIMIT 500;";
	
	private static final String SQL_GET_BY_ID = SQL_SELECT_FROM_WHERE + SQL_AND_C_ID;
	private static final String SQL_GET_BY_ID_BY_USER = SQL_SELECT_FROM_WHERE + SQL_AND_ID_USUARIO + SQL_AND_C_ID;
	
	private static final String SQL_GET_ALL = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_SIN_VALIDAR = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NULL + SQL_ORDER_BY;
	
	private static final String SQL_GET_ALL_BY_USER_CLASICO_VALIDADO = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + SQL_AND_ID_USUARIO + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_BY_USER_CLASICO_SIN_VALIDAR = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NULL + SQL_AND_ID_USUARIO + SQL_ORDER_BY;
	
	private static final String SQL_GET_ALL_BY_MODELO = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND modelo LIKE ? " + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_BY_MARCA = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND m.id = ? " + SQL_ORDER_BY;
	
	private static final String SQL_GET_RESUMEN_USUARIO = "SELECT id_usuario, total, aprobados, pendientes FROM v_clasicos_usuario WHERE id_usuario = ?;";
	private static final String SQL_GET_LAST = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "ORDER BY c.id DESC LIMIT ?;";
	
	
	// executeUpdate devuelve un int que representa el número de filas afectadas.
	private static final String SQL_INSERT =	     "INSERT INTO clasicos (modelo, id_marca, anio, foto, id_usuario) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE =		 "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ? WHERE id = ?;";
	private static final String SQL_UPDATE_BY_USER = "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ?, fecha_validacion = NULL WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_DELETE =		 "DELETE FROM clasicos WHERE id = ?;";
	private static final String SQL_DELETE_BY_USER = "DELETE FROM clasicos WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_VALIDAR =		 "UPDATE clasicos SET fecha_validacion = NOW() WHERE id = ?;";
	

	
	// MÉTODOS.

	/**
	 * Obtiene de la base de datos, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id.
	 * 
	 * @param idModelo (int) Id del modelo a mostrar.
	 * @return Clasico (Objeto Clasico) Modelo a mostrar.
	 */
	@Override
	public Clasico getById(int idModelo) throws Exception {

		Clasico clasico = new Clasico();

		try (	Connection con = ConnectionManager.getConnection(); // Obtener la conexión.
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID); // Preparar el Statement.
				) {

			pst.setInt(1, idModelo); // Antes de ejecutar la query hay que sustituir todas las ? que contenga.
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) { // Si encuentra un resultado en el ResultSet...
					clasico = mapper(rs);

				} else {
					throw new Exception("No existen en la base de datos clásicos con el id " + idModelo + ".");
				}
				
			} // try interno
			
		} // try externo

		return clasico;

	} // getById
	
	
	@Override
	public Clasico getByIdByUser(int idModelo, int idUsuario) throws SeguridadException, Exception {

		Clasico clasico = new Clasico();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID_BY_USER);
			) {

			pst.setInt(1, idModelo);
			pst.setInt(2, idUsuario);
			LOG.debug(pst);
			
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					clasico = mapper(rs);

				} else {
					throw new SeguridadException();
				}
				
			} // try interno
			
		} // try externo

		return clasico;
		
	} // getByIdByUser

	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) validados.
	 * 
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	@Override
	public ArrayList<Clasico> getAll() throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
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
	
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) validados.
	 * 
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	@Override
	public ArrayList<Clasico> getAllSinValidar() throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_SIN_VALIDAR);
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
	public ArrayList<Clasico> getAllByUser(int idUsuario, boolean validado) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String query = validado ? SQL_GET_ALL_BY_USER_CLASICO_VALIDADO : SQL_GET_ALL_BY_USER_CLASICO_SIN_VALIDAR;

		try (	Connection con = ConnectionManager.getConnection();
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
		}
		
		return clasicos;

	} // getAllByUser
	

	@Override
	public ArrayList<Clasico> getAllByModelo(String modelo) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
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
		}

		return clasicos;
		
	} // getAllByModelo


	@Override
	public ArrayList<Clasico> getAllByMarca(int idMarca, int numReg) {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
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
		}

		return clasicos;

	} // getAllByMarca
	
	
	@Override
	public ResumenUsuario getResumenUsuario(int idUsuario) {
		
		ResumenUsuario resumenUsuario = new ResumenUsuario();

		try (	Connection con = ConnectionManager.getConnection();
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
				}
				
			} // try interno

		} catch (Exception e) {
			LOG.error(e);	
		}

		return resumenUsuario;
		
	} // getResumenUsuario

	
	@Override
	public ArrayList<Clasico> getLast(int numReg) {
		
		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
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
		}

		return clasicos;
		
	} // getLast
	
	
	/**
	 * Inserta en la base de datos, tabla clasicos, un modelo (objeto de tipo Clasico). Es necesario introducir el nombre del modelo,
	 * la marca, el año y la foto.
	 * 
	 * @param pojo (Objeto Clasico) Modelo a insertar.
	 * @return Clasico (Objeto Clasico) Modelo insertado.
	 * @see package robert.bermudez.rodriguez.modelo.pojo.Clasico
	 */
	@Override
	public Clasico insert(Clasico pojo) throws Exception {

		// En la bbdd dentro de la tabla clasicos hay dos campos que no están en el pojo Clasico: fecha_creacion y fecha_validacion.
		// fecha_creacion se añade automáticamente al insertar un clásico, ya que el valor por defecto de ese campo es CURRENT_TIMESTAMP.
		// fecha_validacion se añade con el método validar y se anula con updateByUser.
		
		// Clasico clasico = new Clasico(); // No es necesario declarar un clásico, con devolver el pojo que admite como argumento es suficiente.

		try (	Connection con = ConnectionManager.getConnection();
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
			}

		} // try externo

		return pojo;

	} // insert
	
	
	/**
	 * Actualiza en la base de datos, tabla clasicos, un modelo (objeto de tipo Clasico). Es opcional introducir uno
	 * o varios de los siguientes campos: nombre del modelo, marca, año y foto.
	 * 
	 * @param pojo (Objeto Clasico) Modelo a actualizar.
	 * @return Clasico (Objeto Clasico) Modelo actualizado.
	 * @see package robert.bermudez.rodriguez.modelo.pojo.Clasico.java
	 */
	@Override
	public Clasico update(Clasico pojo) throws Exception {

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
				) {
			

			
			//TODO antes de modificar comprobar el ROL del usuario
			// si es ADMIN hacer la update que tenemos abajo
			// si es USER comprobar que le pertenezca ??
			
			// throw new SeguridadException();
			
			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId());
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
	public Clasico updateByUser(Clasico pojo) throws SeguridadException, Exception {
		
		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE_BY_USER);
				) {
			
			int idModelo = pojo.getId();
			int idUsuario = pojo.getUsuario().getId();
			
			getByIdByUser(idModelo, idUsuario);

			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId());
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
		
	} // updateByUser


	/**
	 * Elimina de la bbdd, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id.
	 * 
	 * @param idModelo (int) Id del modelo a borrar.
	 * @return Clasico (Objeto Clasico) Modelo borrado.
	 */
	@Override
	public Clasico delete(int idModelo) throws Exception {

		// Clasico clasico = new Clasico(); -> Erróneo: hay que obtener el clásico antes de eliminarlo.
		Clasico clasico = getById(idModelo);

		try (	Connection con = ConnectionManager.getConnection();
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
		}

		return clasico;

	} // delete	
	
	
	@Override
	public Clasico deleteByUser(int idModelo, int idUsuario) throws SeguridadException, Exception {
		
		Clasico clasico = getByIdByUser(idModelo, idUsuario);

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_USER);
				) {

			pst.setInt(1, idModelo);
			pst.setInt(2, idUsuario);
			LOG.debug(pst);

			pst.executeUpdate();
			
			// Como getById lanza una excepción si se intenta acceder a un clásico no autorizado, basta con hacer executeUpdate.
			// int affectedRows = pst.executeUpdate(); 
			// if (affectedRows != 1) {
			//	 throw new SeguridadException();
			// }
			
		} // try
		
		return clasico;

	} // delete
	
	
	@Override
	public void validar(int idModelo) {
		
		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_VALIDAR);
				) {

			pst.setInt(1, idModelo);
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos del clásico con el id  " + idModelo + ".");
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
	} // validar
	
	
	/**
	 * Mapea los campos de un modelo (objeto de tipo Clasico) para usarlo en el resto de métodos de la clase ClasicoDAOImpl.
	 * 
	 * @param rs (ResultSet)
	 * @return Clasico (Objeto Clasico) Modelo mapeado.
	 * @throws SQLException
	 */
	private Clasico mapper(ResultSet rs) throws SQLException {
		
		Marca marca = new Marca();
		marca.setId(rs.getInt("id_marca"));
		marca.setMarca(rs.getString("marca"));
		
		// Mostrar el nombre del usuario en una tabla de clásicos de su propio panel carece de utilidad. Sin embargo, en el
		// backoffice o en la parte pública será útil mostrar los datos de este usuario.
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
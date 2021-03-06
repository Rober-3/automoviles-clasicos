package robert.bermudez.rodriguez.modelo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.connection.ConnectionManager;
import robert.bermudez.rodriguez.modelo.dao.ClasicoDAO;
import robert.bermudez.rodriguez.modelo.dao.SeguridadException;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasClasico;
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
 * 
 */
public class ClasicoDAOImpl implements ClasicoDAO {

	private static final Logger LOG = Logger.getLogger(ClasicoDAOImpl.class);

	private static ClasicoDAOImpl INSTANCE = null;

	private ClasicoDAOImpl() {
		super();
	}

	public static synchronized ClasicoDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ClasicoDAOImpl();
		}
		return INSTANCE;

	} // getInstance()


	private static final String SQL_SELECT_FROM_WHERE =	"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto, " +
			"u.id 'id_usuario', u.nombre 'nombre_usuario' " +
			"FROM clasicos c, marcas m, usuarios u " +
			"WHERE id_marca = m.id AND c.id_usuario = u.id ";
	private static final String SQL_AND_IS_NOT_NULL =	"AND fecha_validacion IS NOT NULL ";
	private static final String SQL_AND_IS_NULL =	  	"AND fecha_validacion IS NULL ";
	private static final String SQL_AND_U_ID =		 	"AND u.id = ? ";
	private static final String SQL_AND_C_ID =		  	"AND c.id = ?;";
	private static final String SQL_ORDER_BY =		  	"ORDER BY c.id DESC LIMIT 500;";

	private static final String SQL_GET_BY_ID = SQL_SELECT_FROM_WHERE + SQL_AND_C_ID;
	private static final String SQL_GET_BY_ID_BY_USER = SQL_SELECT_FROM_WHERE + SQL_AND_U_ID + SQL_AND_C_ID;

	private static final String SQL_GET_ALL = SQL_SELECT_FROM_WHERE + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_VALIDATED = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_NOT_VALIDATED = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NULL + SQL_ORDER_BY;

	private static final String SQL_GET_ALL_BY_USER = SQL_SELECT_FROM_WHERE + SQL_AND_U_ID + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_BY_USER_VALIDATED = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + SQL_AND_U_ID + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_BY_USER_NOT_VALIDATED = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NULL + SQL_AND_U_ID + SQL_ORDER_BY;

	private static final String SQL_GET_ALL_BY_MODELO = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND modelo LIKE ? " + SQL_ORDER_BY;
	private static final String SQL_GET_ALL_BY_MARCA = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "AND m.id = ? " + SQL_ORDER_BY;

	private static final String SQL_GET_LAST = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "ORDER BY c.id DESC LIMIT ?;";
	private static final String SQL_GET_ALEATORY = SQL_SELECT_FROM_WHERE + SQL_AND_IS_NOT_NULL + "ORDER BY RAND() LIMIT 3;";

	private static final String SQL_GET_USER_SUMMARY = "SELECT id_usuario, total, aprobados, pendientes FROM v_clasicos_usuario WHERE id_usuario = ?;";
	private static final String SQL_GET_CLASSIC_STATISTICS = "SELECT total, aprobados, pendientes FROM v_estadisticas_clasicos;";

	
	private static final String SQL_INSERT =	     "INSERT INTO clasicos (modelo, id_marca, anio, foto, id_usuario) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE =		 "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ? WHERE id = ?;";
	private static final String SQL_UPDATE_BY_USER = "UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ?, fecha_validacion = NULL WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_DELETE =		 "DELETE FROM clasicos WHERE id = ?;";
	private static final String SQL_DELETE_BY_USER = "DELETE FROM clasicos WHERE id = ? AND id_usuario = ?;";
	private static final String SQL_VALIDATE =		 "UPDATE clasicos SET fecha_validacion = NOW() WHERE id = ?;";



	/**
	 * Obtiene de la base de datos, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id.
	 * 
	 * @param idModelo (int) Id del modelo a mostrar.
	 * @return Clasico (Objeto Clasico) Modelo a mostrar.
	 */
	@Override
	public Clasico getById(int idModelo) throws Exception {

		Clasico clasico = new Clasico();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);
				) {

			pst.setInt(1, idModelo);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					clasico = mapper(rs);

				} else {
					throw new Exception("No existen en la base de datos clásicos con el id " + idModelo + ".");
				}

			} // try interno

		} // try externo

		return clasico;

	} // getById


	@Override
	public Clasico getByIdByUser(int idUsuario, int idModelo) throws SeguridadException, Exception {

		Clasico clasico = new Clasico();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID_BY_USER);
				) {

			pst.setInt(1, idUsuario);
			pst.setInt(2, idModelo);
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
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico), estén o no validados.
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

			while (rs.next()) {
				clasicos.add(mapper(rs));	
			}

		} // try

		return clasicos;

	} // getAll()


	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico)
	 * en función de si han sido aprobados o no por el administrador.
	 * 
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	@Override
	public ArrayList<Clasico> getAllValidation(boolean validados) throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String query = validados ? SQL_GET_ALL_VALIDATED : SQL_GET_ALL_NOT_VALIDATED;

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				) {

			LOG.debug(pst);

			while (rs.next()) {
				clasicos.add(mapper(rs));	
			}

		} // try

		return clasicos;

	} // getAll()

	@Override
	public ArrayList<Clasico> getAllByUser (int idUsuario) throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_USER);
				) {

			pst.setInt(1, idUsuario);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					clasicos.add(mapper(rs));	
				}
			}

		} // try

		return clasicos;

	} // getAllByUser


	@Override
	public ArrayList<Clasico> getAllByUserValidation(int idUsuario, boolean validados) {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();
		String query = validados ? SQL_GET_ALL_BY_USER_VALIDATED : SQL_GET_ALL_BY_USER_NOT_VALIDATED;

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				) {

			pst.setInt(1, idUsuario);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					clasicos.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return clasicos;

	} // getAllByUser


	@Override
	public ArrayList<Clasico> getAllByModel(String modelo) {

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
	public ArrayList<Clasico> getAllByBrand(int idMarca, int numReg) {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_MARCA);
				) {

			pst.setInt(1, idMarca);
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


	@Override
	public ArrayList<Clasico> getAleatory() throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALEATORY);
				ResultSet rs = pst.executeQuery();
				) {

			LOG.debug(pst);

			while (rs.next()) {
				clasicos.add(mapper(rs));
			}

		} // try

		return clasicos;

	} // getLast


	@Override
	public ResumenUsuario getUserSummary(int idUsuario) {

		ResumenUsuario resumenUsuario = new ResumenUsuario();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_USER_SUMMARY);
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
	public EstadisticasClasico getClassicStatistics() {

		EstadisticasClasico estadisticasClasicos = new EstadisticasClasico();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_CLASSIC_STATISTICS);
				ResultSet rs = pst.executeQuery()
				) {

			LOG.debug(pst);

			while (rs.next()) { 
				estadisticasClasicos.setTotal(rs.getInt("total"));
				estadisticasClasicos.setAprobados(rs.getInt("aprobados"));
				estadisticasClasicos.setPendientes(rs.getInt("pendientes"));
			}

		} catch (Exception e) {
			LOG.error(e);	
		}

		return estadisticasClasicos;

	} // getAllEstadisticasClasicos


	/**
	 * Inserta en la base de datos, tabla clasicos, un modelo (objeto de tipo Clasico). Es necesario introducir el nombre del modelo,
	 * la marca, el año y la foto.
	 * 
	 * @param pojo (Objeto Clasico) Modelo a insertar.
	 * @return Clasico (Objeto Clasico) Modelo insertado.
	 */
	@Override
	public Clasico insert(Clasico pojo) throws Exception {

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				) {

			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId());
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getUsuario().getId());
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
	 */
	@Override
	public Clasico update(Clasico pojo) throws Exception {

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
				) {

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

			getByIdByUser(idUsuario, idModelo);
			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId());
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getId());
			pst.setInt(6, pojo.getUsuario().getId());
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
	public Clasico delete(int idModelo) throws SeguridadException, Exception {

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

		} // try

		return clasico;

	} // delete


	@Override
	public void validate(int idModelo) {

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_VALIDATE);
				) {

			pst.setInt(1, idModelo);
			LOG.debug(pst);

			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos del clásico con el id " + idModelo + ".");
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

} // class
package robert.bermudez.rodriguez.modelo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.modelo.dao.MarcaDAO;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.Marca;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

public class MarcaDAOImpl implements MarcaDAO {
	
	private static final Logger LOG = Logger.getLogger(MarcaDAOImpl.class);
	
	// Singleton
	
	private static MarcaDAOImpl INSTANCE = null;

	private MarcaDAOImpl() {
		super();
	}
	
	public static MarcaDAOImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new MarcaDAOImpl();
		}
		
		return INSTANCE;
	}
	
	
	// DAO
	
	// QUERYS
	
	// executeQuery devuelve un ResultSet.
	private static final String SQL_GET_BY_ID =				"SELECT id, marca FROM marcas WHERE id = ?;";
	private static final String SQL_GET_ALL =				"SELECT id, marca FROM marcas ORDER BY marca ASC;";
	private static final String SQL_GET_ALL_VALIDADAS =		"SELECT id, marca FROM marcas WHERE fecha_aprobacion IS NOT NULL ORDER BY marca ASC;";
	private static final String SQL_GET_ALL_SIN_VALIDAR =	"SELECT id, marca FROM marcas WHERE fecha_aprobacion IS NULL ORDER BY marca ASC;";
	private static final String SQL_GET_ALL_WITH_CLASSICS = "SELECT m.id 'id_marca', marca, c.id 'id_modelo', modelo, anio, foto, u.id 'id_usuario', u.nombre 'nombre_usuario' " +
															"FROM marcas m, clasicos c, usuarios u " + 
															"WHERE m.id = id_marca " +
															"AND u.id = m.id_usuario " +
															"AND c.fecha_validacion IS NOT NULL " +
															"ORDER BY marca ASC";
	
	// executeUpdate devuelve un int que representa el número de filas afectadas.
	private static final String SQL_INSERT =				"INSERT INTO marcas (id, marca) VALUES (?,?);";
	private static final String SQL_UPDATE =				"UPDATE marcas SET marca = ? WHERE id = ?;";
	
			
	// MÉTODOS
	
	@Override
	public Marca getById(int id) throws Exception {
		
		Marca marca = new Marca();
		
		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);	) {
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				marca = mapper(rs);
			}
			
		} catch (Exception e) {
			throw new Exception("No existen en la base de datos marcas con el id " + id + ".");

		} // try-catch
		
		return marca;
		
	} // getById

	
	
	@Override
	public ArrayList<Marca> getAll() throws Exception {
		
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();
				
				){
			
			System.out.println(pst);
			
			while (rs.next()) {
				marcas.add(mapper(rs));
			}
			
		} // try
		
		return marcas;
		
	} // getAll
	
	
	public ArrayList<Marca> getAllValidadas() throws Exception {
		
		ArrayList<Marca> marcas = new ArrayList<Marca>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_VALIDADAS);
				ResultSet rs = pst.executeQuery();
				) {

			LOG.debug(pst);

			while (rs.next()) {
				marcas.add(mapper(rs));	
			}

		} // try

		return marcas;
		
	} // getAllValidadas

	
	public ArrayList<Marca> getAllSinValidar() throws Exception {
		
		ArrayList<Marca> marcas = new ArrayList<Marca>();

		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_SIN_VALIDAR);
				ResultSet rs = pst.executeQuery();
				) {

			LOG.debug(pst);

			while (rs.next()) {
				marcas.add(mapper(rs));	
			}

		} // try

		return marcas;
	} // getAllSinValidar

	
	@Override
	public ArrayList<Marca> getAllWithClassics() {

		// Si en while se usa ArrayList y se añaden los resultados uno a uno, se repetirán las marcas. Para solucionar el problema y
		// mostrar las marcas con sus clásicos asociados sin que se repita ninguna, hay que usar un HashMap, que es una colección de
		// datos sin duplicados con una clave y un valor. En este caso, la clave es de tipo Integer y el valor el id de la marca.
		// ArrayList<Marca> marcas = new ArrayList<Marca>();
		HashMap<Integer, Marca> marcas = new HashMap<Integer, Marca>();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_WITH_CLASSICS);
				ResultSet rs = pst.executeQuery();
				
				) {
			
			while (rs.next()) {
				
				int idMarca = rs.getInt("id_marca"); // Key del HashMap.
				
				Marca marca = marcas.get(idMarca); // Si a get se le pasa un key devuelve un objeto Marca.
				
				// Si no existe la marca en el HashMap se crea, si no se recupera. De esta forma se evitan duplicados.
				if (marca == null) {
					marca = new Marca();
					marca.setId(idMarca);
					marca.setMarca(rs.getString("marca"));
					
				} // if
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNombre(rs.getString("nombre_usuario"));
				
				Clasico clasico = new Clasico();
				clasico.setId(rs.getInt("id_modelo"));
				clasico.setModelo(rs.getString("modelo"));
				clasico.setAnio(rs.getString("anio"));
				clasico.setFoto(rs.getString("foto"));
				clasico.setUsuario(usuario);
				
				// Recuperar los clásicos y añadir uno nuevo dentro de la marca.
				marca.getClasicos().add(clasico);
				
				// Guardar en el HashMap la marca.
				marcas.put(idMarca, marca);
				
			} // while
			
		} catch (Exception e) {
			LOG.error(e);
			
		} // try-catch
		
		// Como hay que devolver un ArrayList y se tiene un HashMap hay que hacer una conversión al devolver marcas.
		// Si no se hacer dará un error en el return. De un HashMap se pueden conseguir sus keys y sus values.
		return new ArrayList<Marca>(marcas.values());
		
	} // getAllWithClassics

	
	
	@Override
	public Marca insert(Marca pojo) throws Exception {
		
		try (	Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
				){
			
			pst.setInt(1, pojo.getId());
			pst.setString(2, pojo.getMarca());
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
				throw new Exception("Ha habido un problema al tratar de guardar la marca " + pojo + ".");
			}
			
		} // try externo
		
		return pojo;
		
	} // insert
	
	

	@Override
	public Marca update(Marca pojo) throws Exception {
		//"UPDATE marcas SET marca = ? WHERE id = ?;";
		return pojo;
		
	} // update
	
	

	@Override
	public Marca delete(int id) throws Exception {
		// Las marcas no deben borrarse, porque aunque se eliminen todos sus
		// clásicos deben estar siempre disponibles para nuevos modelos.
		return null;
	} // delete
	
	
	
	public Marca mapper(ResultSet rs) throws SQLException {
		
		Marca marca = new Marca();
		marca.setId(rs.getInt("id"));
		marca.setMarca(rs.getString("marca"));
		return marca;
		
	} // mapper

} // class

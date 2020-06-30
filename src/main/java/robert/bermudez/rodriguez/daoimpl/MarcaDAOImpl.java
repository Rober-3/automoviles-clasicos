package robert.bermudez.rodriguez.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.dao.MarcaDAO;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

public class MarcaDAOImpl implements MarcaDAO{
	
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
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// DAO
	
	// Querys
	
	// executeQuery devuelve un ResultSet.
	private static final String SQL_GET_BY_ID =				"SELECT id, marca FROM marcas WHERE id = ?;";
	private static final String SQL_GET_ALL =				"SELECT id, marca FROM marcas ORDER BY marca ASC;";
	private static final String SQL_GET_ALL_WITH_CLASSICS = "SELECT m.id 'id_marca', marca, c.id 'id_modelo', modelo, anio, foto " +
															"FROM marcas m, clasicos c " + 
															"WHERE m.id = id_marca " +
															"ORDER BY modelo ASC";
	
	// executeUpdate devuelve un int que representa el número de filas afectadas.
	// private static final String SQL_INSERT =		"INSERT ;";
	// private static final String SQL_UPDATE =		"UPDATE ;";
	// private static final String SQL_DELETE =		"DELETE ;";
	
	
			
	// Métodos
	
	// TODO Implementar métodos que faltan.
	
	@Override
	public Marca getById(int id) throws Exception {
		
		Marca marca = new Marca();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);
				
				) {
			
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
				
				Clasico clasico = new Clasico();
				clasico.setId(rs.getInt("id_modelo"));
				clasico.setModelo(rs.getString("modelo"));
				clasico.setAnio(rs.getString("anio"));
				clasico.setFoto(rs.getString("foto"));
				
				// Recuperar los clásicos y añadir uno nuevo dentro de la marca.
				marca.getClasicos().add(clasico);
				
				// Guardar en el HashMap la marca.
				marcas.put(idMarca, marca);
				
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} // try-catch
		
		// Como hay que devolver un ArrayList y se tiene un HashMap hay que hacer una conversión al devolver marcas (de
		// no hacerlo dará un error en el return). De un HashMap se pueden conseguir sus keys y sus values.
		return new ArrayList<Marca>(marcas.values());
	}

	

	@Override
	public Marca insert(Marca pojo) throws Exception {
		throw new Exception("Método sin implementar.");
	} // insert
	
	

	@Override
	public Marca update(Marca pojo) throws Exception {
		throw new Exception("Método sin implementar.");
	} // update
	
	

	@Override
	public Marca delete(int id) throws Exception {
		throw new Exception("Método sin implementar.");
		
	} // delete
	
	
	
	public Marca mapper(ResultSet rs) throws SQLException {
		
		Marca marca = new Marca();
		
		marca.setId(rs.getInt("id"));
		marca.setMarca(rs.getString("marca"));
		
		return marca;
		
	} // mapper
	
	
	
	private Marca mapperWithClassics(ResultSet rs) {
		
		return null;
		
	} // mapperWithClassics

} // class

package robert.bermudez.rodriguez.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// DAO
	
	// Querys
	
	// executeQuery devuelve un ResultSet.
	private static final String SQL_GET_BY_ID =		"SELECT id, marca FROM marcas WHERE id = ?;";
	private static final String SQL_GET_ALL =		"SELECT id, marca FROM marcas ORDER BY marca ASC;";
	
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

} // class

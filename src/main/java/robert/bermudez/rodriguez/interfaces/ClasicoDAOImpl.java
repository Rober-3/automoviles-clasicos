package robert.bermudez.rodriguez.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// TODO Notas: clase que emplea el patrón singleton y el patrón DAO. 
//			   - Singleton consta de un constructor privado y un método getInstance().
//			   - DAO implementa los métodos CRUD de de la interfaz (getByid, getAll, insert, update y delete).

import java.util.ArrayList;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.modelo.Clasico;

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

	// TODO Notas: patrón singleton

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
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	// TODO Notas: patrón DAO

	// Querys

	// executeQuery devuelve un ResultSet.
	private static final String SQL_GET_BY_ID =			"SELECT id, modelo, marca, anio, foto FROM clasicos WHERE id = ?;";
	private static final String SQL_GET_ALL =			"SELECT id, modelo, marca, anio, foto FROM clasicos ORDER BY id DESC;";
	private static final String SQL_GET_ALL_BY_MODELO = "SELECT id, modelo, marca, anio, foto FROM clasicos WHERE modelo LIKE ?;";
	private static final String SQL_GET_ALL_BY_MARCA =	"SELECT id, modelo, marca, anio, foto FROM clasicos WHERE marca LIKE ?;";

	// executeUpdate devuelve un int que representa el número de filas afectadas.
	private static final String SQL_INSERT =			"INSERT INTO clasicos (modelo, marca, anio, foto) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE =			"UPDATE clasicos SET modelo = ?, marca = ?, anio = ?, foto = ? WHERE id = ?;";
	private static final String SQL_DELETE =			"DELETE FROM clasicos WHERE id = ?;";



	// Métodos.

	@Override
	public Clasico getById(int id) throws Exception {

		Clasico clasico = new Clasico();

		try (
				Connection con = ConnectionManager.getConnection(); // Obtener la conexión.
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID); // Preparar el Statement.

				) {

			// Antes de ejecutar la query es imprescindible sustituir todas las ? que contenga.
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) { // Si encuentra un resultado en el ResultSet...

				clasico.setId(rs.getInt("id"));
				clasico.setModelo( rs.getString("modelo") );
				clasico.setMarca( rs.getString("marca") );
				clasico.setAnio( rs.getInt("anio") );
				clasico.setFoto( rs.getString("foto") );

			} // if

		} catch (Exception e) {
			throw new Exception("No existen en la base de datos clásicos con el id " + id + ".");

		} // try-catch

		return clasico;

	} //  getById

	// ¿Por qué los métodos de CrudAble arrojan excepciones y los de DAO no?

	@Override
	public ArrayList<Clasico> getAll() throws Exception {

		ArrayList<Clasico> listaClasicos = new ArrayList<Clasico>();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery(SQL_GET_ALL);

				) {

			while (rs.next()) { // Mientras encuentre resultados en el ResultSet...
				
				Clasico clasico = new Clasico();
				
				clasico.setId(rs.getInt("id"));
				clasico.setModelo(rs.getString("modelo"));
				clasico.setMarca(rs.getString("marca"));
				clasico.setAnio(rs.getInt("anio"));
				clasico.setFoto(rs.getString("foto"));
				
				listaClasicos.add(clasico);
				
			} // while

		} catch (Exception e) {
			e.printStackTrace();

		} // try-catch

		return listaClasicos;

	} // getAll()



	@Override
	public Clasico insert(Clasico pojo) throws Exception {
		
		Clasico clasico = new Clasico();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);
				
				) {
			
			pst.setString(1, pojo.getModelo());
			pst.setString(2, pojo.getMarca());
			pst.setInt(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			
			int affectedRows = pst.executeUpdate(SQL_INSERT);
			
			if (affectedRows != 1) {
				throw new Exception("No se ha podido guardar debido a un problema.");
				
			} // if
			
		} // try
		
		return clasico;

	} // insert



	@Override
	public Clasico update(Clasico pojo) throws Exception {
		
		Clasico clasico = new Clasico();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
				
				) {
			
			pst.setString(1, pojo.getModelo());
			pst.setString(2, pojo.getMarca());
			pst.setInt(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los nuevos datos del clásico.");
				
			} // if
			
		} // try
		
		return clasico;

	} // update



	@Override
	public Clasico delete(int id) throws Exception {
		
		Clasico clasico = new Clasico();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);
				
				) {
			
			pst.setInt(1, id);
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No existen en la base de datos clásicos con el id " + id + ".");
				
			} else {
				
			}
			
		} // try
		
		return clasico;

	} // delete



	@Override
	public ArrayList<Clasico> getAllByModelo(String modelo) {
		// TODO Auto-generated method stub
		return null;

	} // getAllByModelo



	@Override
	public ArrayList<Clasico> getAllByMarca(String marca) {
		// TODO Auto-generated method stub
		return null;

	} // getAllByMarca

} // class

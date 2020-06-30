package robert.bermudez.rodriguez.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO Notas: clase que emplea el patrón singleton y el patrón DAO. 
//			   - Singleton consta de un constructor privado y un método getInstance().
//			   - DAO implementa los métodos CRUD de de la interfaz (getByid, getAll, insert, update y delete).

import java.util.ArrayList;

import robert.bermudez.rodriguez.conexion.ConnectionManager;
import robert.bermudez.rodriguez.dao.ClasicoDAO;
import robert.bermudez.rodriguez.modelo.Clasico;
import robert.bermudez.rodriguez.modelo.Marca;

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
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	// TODO Notas: patrón DAO

	// Querys

	// executeQuery devuelve un ResultSet.
	private static final String SQL_GET_BY_ID =			"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto " + 
														"FROM clasicos c, marcas m " + 
														"WHERE id_marca = m.id AND c.id = ?;";
	
	private static final String SQL_GET_ALL =			"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto " + 
														"FROM clasicos c, marcas m " + 
														"WHERE id_marca = m.id " + 
														"ORDER BY c.id DESC;";
	
	// private static final String SQL_GET_BY_MODELO =	"SELECT id, modelo, marca, anio, foto FROM clasicos WHERE modelo LIKE ?;";
	private static final String SQL_GET_BY_MARCA =		"SELECT c.id 'id_modelo', modelo, m.id 'id_marca', marca, anio, foto " + 
														"FROM clasicos c, marcas m " + 
														"WHERE id_marca = ? AND c.id_marca = m.id;";

	// executeUpdate devuelve un int que representa el número de filas afectadas.
	private static final String SQL_INSERT =			"INSERT INTO clasicos (modelo, id_marca, anio, foto) VALUES (?, ?, ?, ?);";
	private static final String SQL_UPDATE =			"UPDATE clasicos SET modelo = ?, id_marca = ?, anio = ?, foto = ? WHERE id = ?;";
	private static final String SQL_DELETE =			"DELETE FROM clasicos WHERE id = ?;";



	// Métodos.
	
	// TODO Implementar métodos que faltan.

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
				clasico = mapper(rs);

			}

		} catch (Exception e) {
			throw new Exception("No existen en la base de datos clásicos con el id " + id + ".");

		} // try-catch

		return clasico;

	} //  getById

	

	@Override
	public ArrayList<Clasico> getAll() throws Exception {

		ArrayList<Clasico> clasicos = new ArrayList<Clasico>();

		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();

				) {

			System.out.println(pst);
			while (rs.next()) { // Mientras encuentre resultados en el ResultSet...
				clasicos.add(mapper(rs));
				
			} // while

		} // try

		return clasicos;

	} // getAll()
	
	
	
	@Override
	public ArrayList<Clasico> getByMarca(int idMarca) throws Exception {
		
		ArrayList<Clasico> listaClasicos = new ArrayList<Clasico>();
		
		try (	
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_MARCA);
				
				) {
			
			pst.setInt(1, idMarca);
			ResultSet rs = pst.executeQuery();
			System.out.println(pst);
			while (rs.next()) {
				listaClasicos.add(mapper(rs));
				
			} // while
			
		} // try
		
		return listaClasicos;

	} // getByMarca
	
	
	
	@Override
	public Clasico insert(Clasico pojo) throws Exception {
		
		Clasico clasico = new Clasico();
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				// RETURN_GENERATED_KEYS es una constante de una clase de Java que sirve para devolver la clave generada por la BBDD.

				) {
			
			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId()); // Marca es un objeto, por tanto hay que acceder a su atributo id.
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 1) {
				
				ResultSet rsKeys = pst.getGeneratedKeys();
				
				int id = rsKeys.getInt(1); // Obtener el id generado automáticamente por la BBDD.
				
				clasico.setId(id);
				
			} else {
				throw new Exception("Ha habido un problema al tratar de guardar " + clasico + ".");
				
			} // if-else
			
		} // try externo
		
		return clasico;

	} // insert



	@Override
	public Clasico update(Clasico pojo) throws Exception {
		
		try (
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
				
				) {
			
			pst.setString(1, pojo.getModelo());
			pst.setInt(2, pojo.getMarca().getId()); // Marca es un objeto, por tanto hay que acceder a su atributo id.
			pst.setString(3, pojo.getAnio());
			pst.setString(4, pojo.getFoto());
			pst.setInt(5, pojo.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No se han actualizado correctamente los datos de " + pojo + ".");
				
			} // if
			
		} catch (Exception e) {
			throw new Exception("Ha habido un problema al tratar de actualizar " + pojo + ".");
			
		} // try-catch
		
		return pojo;

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
				
			} // if
			
		} catch (Exception e) {
			throw new Exception("Ha habido un problema al tratar de borrar " + clasico + ".");
			
		} // try-catch
		
		return clasico;

	} // delete



	@Override
	public ArrayList<Clasico> getByModelo(int idModelo) {
		return null;

	} // getByModelo
	
	
	
	public Clasico mapper(ResultSet rs) throws SQLException {
		
		Marca marca = new Marca();
		
		marca.setId(rs.getInt("id_marca"));
		marca.setMarca(rs.getString("marca"));
		
		
		Clasico clasico = new Clasico();
		
		clasico.setId(rs.getInt("id_modelo"));
		clasico.setModelo( rs.getString("modelo") );
		clasico.setMarca(marca);
		clasico.setAnio( rs.getString("anio") );
		clasico.setFoto( rs.getString("foto") );
		
		return clasico;
		
	} // mapper
	
} // class

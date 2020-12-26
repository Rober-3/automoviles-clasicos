package robert.bermudez.rodriguez.modelo.dao;

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasClasico;
import robert.bermudez.rodriguez.modelo.pojo.ResumenUsuario;


/**
 * Interfaz que hereda los métodos básicos de la interfaz CrudAble. Además define nuevos métodos.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 * 
 */
public interface ClasicoDAO extends CrudAble<Clasico>  {
	
	ArrayList<Clasico> getAllValidation(boolean validados) throws Exception;
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) con un nombre determinado.
	 * 
	 * @param modelo (objeto Modelo) Nombre del modelo que se quiere buscar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByModel (String modelo);
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) pertenecientes a una marca,
	 * por medio del id de dicha marca y mostrando un determinado número de resultados.
	 * 
	 * @param idMarca (int) Id de la marca.
	 * @param numReg (int) Número de registros a mostrar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByBrand (int idMarca, int numReg);
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, los últimos modelos (objetos de tipo Clasico) registrados, ordenados por
	 * su id de mayor a menor y mostrando un determinado número de resultados.
	 * 
	 * @param numReg (int) Número de registros a mostrar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getLast (int numReg);
	
	ArrayList<Clasico> getAleatory() throws Exception;
	
	/**
	 * Obtiene de la base de datos datos estadisticos de un usuario determinado.
	 * 
	 * @param idUsuario (int) Id del usuario.
	 * @return ResumenUsuario (Objeto ResumenUsuario) Datos del usuario.
	 */
	ResumenUsuario getUserSummary(int idUsuario);
	
	EstadisticasClasico getClassicStatistics ();
	
	/**
	 * Permite al administrador aprobar un modelo (objeto de tipo Clasico) registrado por un usuario, añadiendo
	 * una fecha de validación en el registro de dicho modelo en la tabla clasicos de la base de datos.
	 * 
	 * @param idModelo (int) Id del modelo a aprobar.
	 */
	void validate (int idModelo);
	
	
	/**
	 * Obtiene de la bbdd, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id y comprobando que
	 * pertenece al usuario que lo ha registrado.
	 * 
	 * @param idModelo (int) Id del modelo que se solicita.
	 * @param idUsuario (int) Id del usuario que solicita el modelo.
	 * @return Clasico (Objeto Clasico) Modelo solicitado.
	 * @throws SeguridadException Si el usuario no puede visualizar el modelo porque no lo registró previamente.
	 */
	Clasico getByIdByUser (int idModelo, int idUsuario) throws SeguridadException, Exception;
	
	ArrayList<Clasico> getAllByUser (int idUsuario) throws Exception;
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) que han sido registrados por
	 * un usuario, por medio del id de dicho usuario y estén aprobados o no por el administrador.
	 * 
	 * @param idUsuario (int) Id del usuario que ha registrado los clásicos.
	 * @param validado (boolean) true para mostrar los clásicos aprobados por el administrador, false para mostrar 
	 * los pendientes de aprobación.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByUserValidation (int idUsuario, boolean validados);
	
	Clasico updateByUser (Clasico pojo) throws SeguridadException, Exception;
	
	/**
	 * Elimina de la bbdd, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id y comprobando que
	 * pertenece al usuario que lo ha registrado.
	 * 
	 * @param idModelo (int) Id del modelo a eliminar.
	 * @param idUsuario (int) Id del usuario que solicita la eliminación.
	 * @return Clasico (Objeto Clasico) Modelo eliminado.
	 * @throws SeguridadException Si el usuario no puede eliminar el modelo porque no lo registró previamente.
	 */
	Clasico deleteByUser (int idModelo, int idUsuario) throws SeguridadException, Exception;
	
} // interface

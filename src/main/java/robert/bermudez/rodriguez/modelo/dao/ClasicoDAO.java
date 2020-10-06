package robert.bermudez.rodriguez.modelo.dao;

// TODO Notas: interface que extiende de CrudAble y que contiene métodos adicionales a los métodos CRUD básicos.
//			   En esta interface la C de CrudAble, que es una clase genérica, se ha sustituido por la clase Clasico.

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.ResumenUsuario;


/**
 * Interfaz que hereda los métodos básicos de la interfaz CrudAble. Además define nuevos métodos.
 * 
 * @author Roberto Bermúdez Rodríguez
 * @version 1.0
 * 
 * @see package robert.bermudez.rodriguez.interfaces.CrudAble.java
 */
public interface ClasicoDAO extends CrudAble<Clasico>  {
	
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) con un nombre determinado.
	 * 
	 * @param modelo (objeto Modelo) Nombre del modelo que se quiere buscar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByModelo (String modelo);
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) pertenecientes a una marca,
	 * por medio del id de dicha marca y mostrando un determinado número de resultados.
	 * 
	 * @param idMarca (int) Id de la marca.
	 * @param numReg (int) Número de registros a mostrar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByMarca (int idMarca, int numReg);
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, los últimos modelos (objetos de tipo Clasico) registrados, ordenados por
	 * su id de mayor a menor y mostrando un determinado número de resultados.
	 * 
	 * @param numReg (int) Número de registros a mostrar.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getLast (int numReg);
	
	/**
	 * Obtiene de la base de datos datos estadisticos de un usuario determinado.
	 * 
	 * @param idUsuario (int) Id del usuario.
	 * @return ResumenUsuario (Objeto ResumenUsuario) Datos del usuario.
	 * @see package robert.bermudez.rodriguez.modelo.pojo.ResumenUsuario.java
	 */
	ResumenUsuario getResumenUsuario (int idUsuario);
	
	/**
	 * Permite al administrador aprobar un modelo (objeto de tipo Clasico) registrado por un usuario, añadiendo
	 * una fecha de validación en el registro de dicho modelo en la tabla clasicos de la base de datos.
	 * 
	 * @param idModelo (int) Id del modelo a aprobar.
	 */
	void validar (int idModelo);
	
	
	
	// Estos métodos se declaran por seguridad, para evitar accesos por parte de un usuario a clásicos que no le
	// pertenecen. Estos accesos no autorizados podrían realizarse introduciendo en las URL id de clásicos que no
	// corresponden al usuario, o inspeccionando el código fuente y en los enlaces de editar o eliminar registros
	// introducir esos mismos id. Para ello, antes de manipular registros se solicita el id del usuario, y en caso
	// de ser un usuario no autorizado se lanza una excepción personalizada. Los métodos básicos getById y delete
	// se mantienen para que un administrador, al tener privilegios totales, pueda hacer cualquier operación con
	// cualquier registro.
	
	
	/**
	 * Obtiene de la bbdd, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id y comprobando que
	 * pertenece al usuario que lo ha registrado.
	 * 
	 * @param idModelo (int) Id del modelo que se solicita.
	 * @param idUsuario (int) Id del usuario que solicita el modelo.
	 * @return Clasico (Objeto Clasico) Modelo solicitado.
	 * @throws SeguridadException Si el usuario no puede visualizar el modelo porque no lo registró previamente.
	 * @throws Exception 
	 */
	Clasico getByIdByUser (int idModelo, int idUsuario) throws SeguridadException, Exception;
	
	/**
	 * Obtiene de la base de datos, tabla clasicos, todos los modelos (objetos de tipo Clasico) que han sido registrados por
	 * un usuario, por medio del id de dicho usuario y estén aprobados o no por el administrador.
	 * 
	 * @param idUsuario (int) Id del usuario que ha registrado los clásicos.
	 * @param isValidado (boolean) true para mostrar los clásicos aprobados por el administrador, false para mostrar 
	 * los pendientes de aprobación.
	 * @return {@code ArrayList<Clasicos>} Lista con los modelos.
	 */
	ArrayList<Clasico> getAllByUser (int idUsuario, boolean validado);
	
	Clasico updateByUser (Clasico pojo);
	
	/**
	 * Elimina de la bbdd, tabla clasicos, un modelo (objeto de tipo Clasico) por medio de su id y comprobando que
	 * pertenece al usuario que lo ha registrado.
	 * 
	 * @param idModelo (int) Id del modelo a eliminar.
	 * @param idUsuario (int) Id del usuario que solicita la eliminación.
	 * @return Clasico (Objeto Clasico) Modelo eliminado.
	 * @throws SeguridadException Si el usuario no puede eliminar el modelo porque no lo registró previamente.
	 * @throws Exception 
	 */
	Clasico deleteByUser (int idModelo, int idUsuario) throws SeguridadException, Exception;
	
} // interface

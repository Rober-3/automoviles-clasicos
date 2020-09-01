package robert.bermudez.rodriguez.modelo.dao;

// TODO Notas: interface que extiende de CrudAble y que contiene métodos adicionales a los métodos CRUD básicos.
//			   En esta interface la C de CrudAble, que es una clase genérica, se ha sustituido por la clase Clasico.

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.Clasico;
import robert.bermudez.rodriguez.modelo.pojo.ResumenUsuario;


/**
 * Hereda los métodos básicos de la interfaz Crudable. Además, define nuevos métodos.
 */
public interface ClasicoDAO  extends CrudAble<Clasico>  {
	
	
	/**
	 * Obtiene todos los clásicos por el nombre del modelo.
	 * @param modelo
	 * @return
	 */
	ArrayList<Clasico> getAllByModelo(String modelo);
	
	
	/**
	 * Obtiene todos los clásicos de una marca
	 * @param idMarca int identificador de la marca.
	 * @param numReg int número de registros a mostrar.
	 * @return
	 */
	ArrayList<Clasico> getAllByMarca(int idMarca, int numReg);
	
	
	/**
	 * Obtiene todos los clásicos de un usuario, estén validados o no.
	 * @param idUsuario int identificador del usuario.
	 * @param isValidado boolean true para mostrar los clásicos con fecha_validacion, false para mostrar los pendientes de validar.
	 * @return
	 */
	ArrayList<Clasico> getAllByUser(int idUsuario, boolean validado);
	
	
	/**
	 * Obtiene los ultimos registros por id descendente.
	 * @param numReg int numero de registros a recuperar
	 * @return ArrayList<Producto>
	 */
	ArrayList<Clasico> getLast(int numReg);
	
	
	/**
	 * Obtiene datos estadisticos del usuario.
	 * @see ResumenUsuario
	 * @return ResumenUsuario
	 */
	ResumenUsuario getResumenUsuario(int idUsuario);
	
	
	/**
	 * Validación del clásico para que sea visible en la parte pública.
	 * @param id identificador del cĺásico
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
	 * Obtiene un registro comprobando que pertenezca al usuario que lo solicita.
	 * @param idModelo
	 * @param idUsuario
	 * @return clásico solicitado
	 * @throws SeguridadException si el usuario no puede visualizar el registro porque no le pertenece
	 * @throws Exception 
	 */
	Clasico getByIdByUser(int idModelo, int idUsuario) throws SeguridadException, Exception;
	
	
	/**
	 * Elimina un registro comprobando que pertenezca al usuario que lo solicita.
	 * @param idModelo
	 * @param idUsuario
	 * @return clásico eliminado
	 * @throws SeguridadException si el usuario no puede eliminar el registro porque no le pertenece
	 * @throws Exception 
	 */
	Clasico delete(int idModelo, int idUsuario) throws SeguridadException, Exception;
	
}

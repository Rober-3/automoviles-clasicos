package robert.bermudez.rodriguez.modelo.dao;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.Usuario;

public interface UsuarioDAO extends CrudAble<Usuario> {
	
	/**
	 * Comprueba si un usuario existe en la base de datos.
	 * En caso de fallar, devuelve una excepción
	 * @param nombre nombre del usuario
	 * @param contrasena contraseña del usuario
	 * @return Usuario objeto Usuario
	 * @throws Exception si no se encuentra el usuario
	 */
	Usuario exists (String nombre, String contrasena) throws Exception;
	
	
	/**
	 * Busca un nombre para ver si existe en la base de datos. Se usa conjuntamente 
	 * con la función de JavaScript buscarUsuario(event) para hacer llamadas AJAX y 
	 * comprobar en tiempo real si existe el nombre de usuario.
	 * 
	 * @param nombre (String) Nombre a buscar.
	 * @return boolean true si encuentra el usuario, false si no lo encuentra.
	 */
	boolean searchByName (String nombre);

}

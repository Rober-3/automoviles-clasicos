package robert.bermudez.rodriguez.interfaces;

import robert.bermudez.rodriguez.modelo.Usuario;

public interface UsuarioDAO extends CrudAble<Usuario> {
	
	/**
	 * Método que comprueba si un usuario existe en la base de datos.
	 * En caso de fallar, devuelve una excepción
	 * @param nombre
	 * @param contrasena
	 * @return Usuario
	 * @throws Exception
	 */
	Usuario exists (String nombre, String contrasena) throws Exception;

}

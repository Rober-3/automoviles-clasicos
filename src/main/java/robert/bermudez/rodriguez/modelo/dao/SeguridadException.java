package robert.bermudez.rodriguez.modelo.dao;

/**
 * Clase que se usa para lanzar una excepción personalizada cuando un usuario intenta manipular un registro que no le pertenece.
 */
public class SeguridadException extends Exception{

	private static final long serialVersionUID = 1L;
	public static final String MENSAJE = "No tienes privilegios para manipular este registro.";

	public SeguridadException() {
		super(MENSAJE);
		
	}
}

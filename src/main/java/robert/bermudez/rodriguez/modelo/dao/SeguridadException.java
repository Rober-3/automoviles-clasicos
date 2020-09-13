package robert.bermudez.rodriguez.modelo.dao;

// TODO Notas. Ejemplo de excepción personalizada.

//			   Hay ocasiones en las que puede ser conveniente lanzar excepciones con mensajes personalizados.
//			   En este caso, cuando un usuario intente manipular un clásico que pertenece a otro usuario se
//			   lanzará una excepción con el mensaje "No tienes privilegios para manipular este registro".


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

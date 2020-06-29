package robert.bermudez.rodriguez.interfaces;

import java.util.ArrayList;

import robert.bermudez.rodriguez.modelo.Marca;

public interface MarcaDAO extends CrudAble<Marca>{
	
	/**
	 * Obtiene todas las marcas con sus clásicos asociados.
	 * @return ArrayList de marcas ordenadas alfabéticamente.
	 */
	
	// Método para hacer el hashmap de clásicos.
	public ArrayList<Marca> getAllWithClassics();

}

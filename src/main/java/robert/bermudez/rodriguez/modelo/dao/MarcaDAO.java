package robert.bermudez.rodriguez.modelo.dao;

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

public interface MarcaDAO extends CrudAble<Marca>{
	
	public ArrayList<Marca> getAllValidadas() throws Exception;
	
	public ArrayList<Marca> getAllSinValidar() throws Exception;
	
	/**
	 * Obtiene todas las marcas con sus clásicos asociados.
	 * @return ArrayList de marcas ordenadas alfabéticamente.
	 */
	public ArrayList<Marca> getAllWithClassics(); // Método para hacer el hashmap de clásicos.

}

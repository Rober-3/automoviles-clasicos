package robert.bermudez.rodriguez.modelo.dao;

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.pojo.EstadisticasMarca;
import robert.bermudez.rodriguez.modelo.pojo.Marca;

public interface MarcaDAO extends CrudAble<Marca>{
	
	/**
	 * Obtiene todas las marcas con sus clásicos asociados.
	 * @return ArrayList de marcas ordenadas alfabéticamente.
	 */
	ArrayList<Marca> getAllWithClassics();
	
	EstadisticasMarca getBrandStatistics();
}

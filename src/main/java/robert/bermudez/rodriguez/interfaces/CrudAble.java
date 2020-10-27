package robert.bermudez.rodriguez.interfaces;

// TODO Notas: Crudable, interface que contiene los métodos CRUD básicos.
// P es una clase genérica que puede ser sustituída por cualquier otra.

import java.util.ArrayList;

public interface CrudAble<P> {
	
	P getById(int idPojo) throws Exception;
	
	ArrayList<P> getAll() throws Exception;
	
	P insert(P pojo) throws Exception;
	
	P update(P pojo) throws Exception;
	
	P delete(int idPojo) throws Exception;

}

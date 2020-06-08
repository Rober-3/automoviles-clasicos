package robert.bermudez.rodriguez.interfaces;

// TODO Notas: Crudable, interface que contiene los métodos CRUD básicos.
// C es una clase genérica que puede ser sustituída por cualquier otra.

import java.util.ArrayList;

public interface CrudAble<C> {
	
	C getById(int id) throws Exception;
	
	ArrayList<C> getAll() throws Exception;
	
	C insert(C pojo) throws Exception;
	
	C update(C pojo) throws Exception;
	
	C delete(int id) throws Exception;

}

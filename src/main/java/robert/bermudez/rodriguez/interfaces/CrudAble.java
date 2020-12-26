package robert.bermudez.rodriguez.interfaces;

import java.util.ArrayList;

public interface CrudAble<P> {
	
	P getById(int idPojo) throws Exception;
	
	ArrayList<P> getAll() throws Exception;
	
	P insert(P pojo) throws Exception;
	
	P update(P pojo) throws Exception;
	
	P delete(int idPojo) throws Exception;

}

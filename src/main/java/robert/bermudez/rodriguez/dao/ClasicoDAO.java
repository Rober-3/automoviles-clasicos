package robert.bermudez.rodriguez.dao;

// TODO Notas: interface que extiende de CrudAble y que contiene métodos adicionales a los métodos CRUD básicos.
//			   En esta interface la C de CrudAble, que es una clase genérica, se ha sustituido por la clase Clasico.

import java.util.ArrayList;

import robert.bermudez.rodriguez.interfaces.CrudAble;
import robert.bermudez.rodriguez.modelo.Clasico;

public interface ClasicoDAO  extends CrudAble<Clasico>  {

	ArrayList<Clasico> getByModelo(int idModelo);

	ArrayList<Clasico> getByMarca(int idMarca) throws Exception;
}

package robert.bermudez.rodriguez.modelo.pojo;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;

public class Marca {
	
	private int id;
	
	@NotEmpty(message = ": este campo no puede quedar vacío")
	private String marca;
	
	private ArrayList<Clasico> clasicos; 
	
	public Marca() {
		super();
		this.id = 0;
		this.marca = "";
		this.clasicos = new ArrayList<Clasico>();
	}
	
	public Marca(int id) {
		this();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public ArrayList<Clasico> getClasicos() {
		return clasicos;
	}

	public void setClasicos(ArrayList<Clasico> clasicos) {
		this.clasicos = clasicos;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", marca=" + marca + "]";
	}

} // class

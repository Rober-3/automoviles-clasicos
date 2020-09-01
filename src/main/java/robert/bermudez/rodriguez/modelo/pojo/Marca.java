package robert.bermudez.rodriguez.modelo.pojo;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;

public class Marca {
	
	private int id;
	
	@NotEmpty(message = ": este campo no puede quedar vacío")
	private String marca;
	
	// Atributo necesario para hacer un hashmap de clásicos. No puede ponerse en el toString porque no arrancaría la
	// aplicación: como en el POJO Clasico hay un atributo que es un objeto de tipo Marca, si se hiciera un toString
	// del ArrayList<Clasico> clasicos cuando recorriera el primero de los clásicos entraría en su atributo Marca, lo
	// recorrería y a su vez entraría de nuevo en clasicos, haciendo constantemente el mismo proceso y entrando en un
	// bucle que originaría una redundancia cíclica que dejaría sin memoria el ordenador.
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

}

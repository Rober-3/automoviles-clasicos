package robert.bermudez.rodriguez.modelo;

public class Marca {
	
	private int id;
	private String marca;
	
	public Marca() {
		super();
		this.id = 0;
		this.marca ="";
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

	@Override
	public String toString() {
		return "Marca [id=" + id + ", marca=" + marca + "]";
	}

}

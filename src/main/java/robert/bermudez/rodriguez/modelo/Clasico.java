package robert.bermudez.rodriguez.modelo;

public class Clasico {

	private int id;
	private String modelo;
	private String marca;
	private int anio;
	private String foto;
	
	public Clasico() {
		super();
		this.id = 0;
		this.modelo = "";
		this.marca = "";
		this.anio = 0;
		this.foto = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Clasico [id=" + id + ", modelo=" + modelo + ", marca=" + marca + ", anio=" + anio + ", foto=" + foto + "]";
	}
	
}

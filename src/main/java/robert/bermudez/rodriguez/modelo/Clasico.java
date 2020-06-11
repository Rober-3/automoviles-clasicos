package robert.bermudez.rodriguez.modelo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Clasico {

	private int id;
	@Size(min = 3, max = 50, message  = "debe tener entre 3 y 50 caracteres y no estar repetido.")
	@NotEmpty(message = "no puede quedar vacío.")
	private String modelo;
	@NotEmpty(message = "no puede quedar vacío.")
	private String marca;
	@NotEmpty(message = "no puede quedar vacío.")
	private String anio;
	@NotEmpty(message = "no puede quedar vacío.")
	private String foto;
	
	public Clasico() {
		super();
		this.id = 0;
		this.modelo = "";
		this.marca = "";
		this.anio = "";
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

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
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

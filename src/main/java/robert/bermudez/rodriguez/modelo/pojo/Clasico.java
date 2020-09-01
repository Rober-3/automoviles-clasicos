package robert.bermudez.rodriguez.modelo.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Clasico {

	private int id;
	
	@Size(min = 3, max = 50, message  = "debe tener entre 3 y 50 caracteres y no estar repetido.")
	@NotEmpty(message = ": este campo no puede quedar vacío")
	private String modelo;
	
	private Marca marca;
	
	@NotEmpty(message = ": este campo no puede quedar vacío")
	private String anio;
	
	@NotEmpty(message = ": este campo no puede quedar vacío")
	private String foto;
	
	// Usuario que ha registrado el clásico.
	private Usuario usuario;

	public Clasico() {
		this.id = 0;
		this.modelo = "";
		this.marca = new Marca();
		this.anio = "";
		this.foto = "";
		this.usuario = new Usuario();
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Clasico [id=" + id + ", modelo=" + modelo + ", marca=" + marca + ", anio=" + anio + ", foto=" + foto
				+ ", usuario=" + usuario + "]";
	}
	
} // class

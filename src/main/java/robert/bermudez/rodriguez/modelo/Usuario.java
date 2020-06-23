package robert.bermudez.rodriguez.modelo;

import javax.validation.constraints.NotEmpty;

public class Usuario {
	
	private int id;
	@NotEmpty(message = "no puede quedar vacío.")
	private String nombre;
	@NotEmpty(message = "no puede quedar vacía.")
	private String contrasena;
	//@NotEmpty(message = "no puede quedar vacía.")
	private String imagen;
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.contrasena = "";
		this.imagen = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasena=" + contrasena + ", imagen=" + imagen + "]";
	}
	
}

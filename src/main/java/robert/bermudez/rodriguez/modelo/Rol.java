package robert.bermudez.rodriguez.modelo;

public class Rol {
	
	// Constantes necesarias para acceder al backoffice o frontoffice desde LoginController.
	public static final int ADMINISTRADOR = 2;
	public static final int USUARIO = 1;
	
	private int id;
	private String rol;
	
	public Rol() {
		super();
		this.id = 1;
		this.rol = "";
	}
	
	public Rol( int id ) {
		this();
		this.id = id;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}

}

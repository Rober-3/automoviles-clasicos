package robert.bermudez.rodriguez.modelo.pojo;

/**
 * Pojo para guardar datos estadísticos del usuario:<br><br>
 * - clásicos totales del usuario<br>
 * - clásicos aprobados por el administrador<br>
 * - clásicos pendientes de aprobación<br><br>
 * Los consigue mediante una consulta a la base de datos.
 */
public class ResumenUsuario {
	
	private int idUsuario;
	private int clasicosTotal;
	private int clasicosAprobados;
	private int clasicosPendientes;
	
	public ResumenUsuario() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getClasicosTotal() {
		return clasicosTotal;
	}

	public void setClasicosTotal(int clasicosTotal) {
		this.clasicosTotal = clasicosTotal;
	}

	public int getClasicosAprobados() {
		return clasicosAprobados;
	}

	public void setClasicosAprobados(int clasicosAprobados) {
		this.clasicosAprobados = clasicosAprobados;
	}

	public int getClasicosPendientes() {
		return clasicosPendientes;
	}

	public void setClasicosPendientes(int clasicosPendientes) {
		this.clasicosPendientes = clasicosPendientes;
	}

	@Override
	public String toString() {
		return "ClasicosUsuario [idUsuario=" + idUsuario + ", clasicosTotal=" + clasicosTotal + ", clasicosAprobados="
				+ clasicosAprobados + ", clasicosPendientes=" + clasicosPendientes + "]";
	}

} // class

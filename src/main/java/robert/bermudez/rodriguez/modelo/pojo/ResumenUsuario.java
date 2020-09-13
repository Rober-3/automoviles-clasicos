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
	private int clasicosTotales;
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

	public int getClasicosTotales() {
		return clasicosTotales;
	}

	public void setClasicosTotal(int clasicosTotales) {
		this.clasicosTotales = clasicosTotales;
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
		return "ResumenUsuario [idUsuario=" + idUsuario + ", clasicosTotales=" + clasicosTotales + ", clasicosAprobados="
				+ clasicosAprobados + ", clasicosPendientes=" + clasicosPendientes + "]";
	}

} // class

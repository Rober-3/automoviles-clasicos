package robert.bermudez.rodriguez.modelo.pojo;

public class EstadisticasClasico {
	
	private int total;
	private int aprobados;
	private int pendientes;
	
	public EstadisticasClasico() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAprobados() {
		return aprobados;
	}

	public void setAprobados(int aprobados) {
		this.aprobados = aprobados;
	}

	public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}

	@Override
	public String toString() {
		return "EstadisticasClasico [total=" + total + ", aprobados=" + aprobados + ", pendientes=" + pendientes + "]";
	}

} // class

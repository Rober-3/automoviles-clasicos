package robert.bermudez.rodriguez.modelo.pojo;

public class EstadisticasMarca {
	
	private int total;
	private int aprobadas;
	private int pendientes;
	
	public EstadisticasMarca() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAprobadas() {
		return aprobadas;
	}

	public void setAprobadas(int aprobadas) {
		this.aprobadas = aprobadas;
	}

	public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}

	@Override
	public String toString() {
		return "EstadisticasMarca [total=" + total + ", aprobadas=" + aprobadas + ", pendientes=" + pendientes + "]";
	}
	
} // class

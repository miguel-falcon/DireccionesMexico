package model;

public class Municipio {
	private String clave;
	private String estado;
	private String descripcion;

	public Municipio(String clave, String estado, String descripcion) {
		this.clave = clave;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public String getClave() { 
		return clave; 
	}
	
	public String getEstado() { 
		return estado; 
	}
	
	public String getDescripcion() { 
		return descripcion; 
	}
}

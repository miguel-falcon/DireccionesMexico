package model;


public class Localidad {
	private String clave;
	private String estado;
	private String descripcion;

	public Localidad(String clave, String estado, String descripcion) {
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

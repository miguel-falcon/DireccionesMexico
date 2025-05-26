package model;


public class Colonia {
	private String clave;
	private String cp;
	private String descripcion;

	public Colonia(String clave, String cp, String descripcion) {
		this.clave = clave;
		this.cp = cp;
		this.descripcion = descripcion;
	}

	public String getClave() { 
		return clave; 
	}
	
	public String getCp() { 
		return cp; 
	}
	
	public String getDescripcion() { 
		return descripcion; 
	}
	
}

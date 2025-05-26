package model;


public class Estado {
	private String clave;
	private String pais;
	private String nombre_estado;

	public Estado(String clave,String pais, String nombre_estado) {
		this.clave = clave;
		this.pais = pais;
		this.nombre_estado = nombre_estado;
	}

	public String getClave() { 
		return clave; 
	}

	public String getPais() { 
		return pais; 
	}

	public String getNombre() { 
		return nombre_estado; 
	}
}

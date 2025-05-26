package model;

public class CodigoPostal {
	private String cp;
	private String estado;
	private String municipio;
	private String localidad;

	public CodigoPostal(String cp, String estado, String municipio, String localidad) {
		this.cp = cp;
		this.estado = estado;
		this.municipio = municipio;
		this.localidad = localidad;
	}

	public String getCp() { 
		return cp; 
	}

	public String getEstado() { 
		return estado; 
	}
	
	public String getMunicipio() { 
		return municipio; 
	}
	
	public String getLocalidad() { 
		return localidad; 
	}
	
}

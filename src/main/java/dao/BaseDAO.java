package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CodigoPostal;
import model.Colonia;
import model.Estado;
import model.Localidad;
import model.Municipio;
import util.DBConnection;

public class BaseDAO {

	private ResultSet getDatosConsulta(String table, String where) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + where);
			System.out.println(stmt.toString());
			return stmt.executeQuery(); 

		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}


	public ArrayList<Estado> getEstados(String where){
		ArrayList<Estado> listaEstados = new ArrayList<>();
		ResultSet rs = getDatosConsulta("estado", where);
		try {
			while (rs.next()) {
				listaEstados.add(new Estado(rs.getString("clave"), rs.getString("pais"), rs.getString("nombre_estado")));
			}
			return listaEstados;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}



	public ArrayList<Colonia> getColonias(String where) {
		ArrayList<Colonia> listaColonias = new ArrayList<>();
		ResultSet rs = getDatosConsulta("colonia", where);
		try {
			while (rs.next()) {
				listaColonias.add(new Colonia(rs.getString("clave"), rs.getString("cp"), rs.getString("descripcion")));
			}
			return listaColonias;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<CodigoPostal> getCodigosPostales(String where) {
		ArrayList<CodigoPostal> listaCP = new ArrayList<>();
		ResultSet rs = getDatosConsulta("codigo_postal", where);
		try  {

			while (rs.next()) {
				listaCP.add(new CodigoPostal(rs.getString("cp"), rs.getString("estado"), rs.getString("municipio"), rs.getString("localidad")));
			}
			return listaCP;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Localidad> getLocalidades(String where) {
		ArrayList<Localidad> listaLocalidades = new ArrayList<>();
		ResultSet rs = getDatosConsulta("localidad", where);
		try {

			while (rs.next()) {
				listaLocalidades.add(new Localidad(rs.getString("clave"), rs.getString("estado"), rs.getString("descripcion")));
			}
			return listaLocalidades;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Municipio> getMunicipios(String where) {
		ArrayList<Municipio> listaMunicipios = new ArrayList<>();
		ResultSet rs = getDatosConsulta("municipio", where);
		try  {

			while (rs.next()) {
				listaMunicipios.add(new Municipio(rs.getString("clave"), rs.getString("estado"), rs.getString("descripcion")));
			}
			return listaMunicipios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

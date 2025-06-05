package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BaseDAO;
import model.CodigoPostal;
import model.Colonia;
import model.Estado;
import model.Localidad;
import model.Municipio;


/**
 * Servlet implementation class DireccionController
 */
@WebServlet("/direcciones_mexico_servlet")
public class direcciones_mexico_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BaseDAO baseDao = new BaseDAO();   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public direcciones_mexico_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String modo = request.getParameter("modo");
		StringBuilder respuesta = new StringBuilder();

		switch (modo) {
		case "municipioLocalidad":
			String clave_estado = request.getParameter("clave_estado");
			ArrayList<Municipio> municipios = baseDao.getMunicipios("estado='" + clave_estado + "'");
			ArrayList<Localidad> localidades = baseDao.getLocalidades("estado='" + clave_estado + "'");

			for (Municipio municipio : municipios) {
				respuesta.append(municipio.getClave()).append("=").append(municipio.getDescripcion()).append(";");
			}
			respuesta.append("|");

			for (Localidad localidad : localidades) {
				respuesta.append(localidad.getClave()).append("=").append(localidad.getDescripcion()).append(";");
			}
			response.getWriter().write(respuesta.toString());
			break;

		case "estadoMunicipioLocalidad":
			String codigo_postal_value = request.getParameter("codigo_postal");
			ArrayList<CodigoPostal> codigo_Postal = baseDao.getCodigosPostales("cp='" + codigo_postal_value + "'");
			System.out.println("RESPUESTA: "+ codigo_Postal.toArray());
			if (codigo_Postal != null && !codigo_Postal.isEmpty()) { 
				String estadoClave = codigo_Postal.get(0).getEstado(); 

				ArrayList<Estado> estados_X_CP = baseDao.getEstados("clave='" + estadoClave + "'");
				if (estados_X_CP != null && !estados_X_CP.isEmpty()) {
					for (Estado estado : estados_X_CP) {
						respuesta.append(estado.getClave()).append("=").append(estado.getNombre()).append(";");
					}

					respuesta.append("|"); 
					String municipioClave = codigo_Postal.get(0).getMunicipio();
					ArrayList<Municipio> municipios_X_CP = baseDao.getMunicipios("clave='" + municipioClave + "' and estado='" + estadoClave + "'" );
					if (municipios_X_CP != null && !municipios_X_CP.isEmpty()) {
						for (Municipio municipio : municipios_X_CP) {
							respuesta.append(municipio.getClave()).append("=").append(municipio.getDescripcion()).append(";");
						}
						respuesta.append("|"); 
						String localidadClave = codigo_Postal.get(0).getLocalidad();
						ArrayList<Localidad> localidades_X_CP = baseDao.getLocalidades("clave='" + localidadClave  + "' and estado='" + estadoClave + "'" );
						if (localidades_X_CP != null && !localidades_X_CP.isEmpty()) {
							for (Localidad localidad : localidades_X_CP) {
								respuesta.append(localidad.getClave()).append("=").append(localidad.getDescripcion()).append(";");
							}
							respuesta.append("|"); 
						}

						ArrayList<Colonia> colonias_X_CP = baseDao.getColonias("cp='" + codigo_postal_value + "'");
						if (colonias_X_CP != null && !colonias_X_CP.isEmpty()) {
							for (Colonia colonia : colonias_X_CP) {
								respuesta.append(colonia.getClave()).append("=").append(colonia.getDescripcion()).append(";");
							}
						}
					}
				}else {
					return;
				}
			} else {
				response.getWriter().write("ERROR: Código postal no encontrado.");
				return;
			}

			response.getWriter().write(respuesta.toString());
			break;
		case "cargarEstados":
			response.setContentType("text/plain;charset=UTF-8");

			ArrayList<Estado> listaEstados = baseDao.getEstados("1=1");

			if (listaEstados == null || listaEstados.isEmpty()) {
				response.getWriter().write("ERROR: No se encontraron estados.");
			}

			for (Estado estado : listaEstados) {
				respuesta.append(estado.getClave()).append("=").append(estado.getNombre()).append(";");
			}

			response.getWriter().write(respuesta.toString()); 
			break;
		}

	}

}

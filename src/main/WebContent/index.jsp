<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.Estado"%>
<%@page import="java.util.ArrayList"%>


<%
ArrayList<Estado> listaEstados = (ArrayList<Estado>) request.getAttribute("listaEstados");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dirección</title>

<link rel="stylesheet" href="media/bootstrap-5.3.6/css/bootstrap.min.css">
</head>
	<body style="background-color: #E6F7FF;">
		<div class="container mt-5 ">
			<h2 class="text-center">Dirección</h2>
	
			<form id="formDireccion">
				<div class="row">
					<div class="col-6 mb-3">
						<label for="cp" class="form-label">Código Postal</label> 
						<input type="text" class="form-control" id="codigo_postal" name="codigo_postal"onblur="CargarEstadoMunicipioLocalidad('estadoMunicipioLocalidad', this)">
					</div>
					<div class="col-6">
					</div>
					<div class="col-6 mb-3">
					    <label for="estado" class="form-label">Estado</label>
					    <select class="form-select" id="clave_estado" name="clave_estado" onchange="CargarMunicipiosLocalidades('municipioLocalidad', this)">
					        <option value="">Seleccione un estado</option>
		<%
					            if (listaEstados != null) {
					                for (Estado estado : listaEstados) {
		%>
					            <option value="<%= estado.getClave() %>" ><%= estado.getNombre() %></option>
		<% 
					                }
					            } 
		%>
					    </select>
					</div>
	
					<div class="col-6 mb-3">
						<label for="municipio" class="form-label">Municipio</label> 
						<select class="form-select" id="clave_municipio" name="clave_municipio">
							<option value="0">Seleccione un Municipio</option>
						</select>
					</div>
					<div class="col-6 mb-3">
						<label for="colonia" class="form-label">Localidad</label> 
						<select class="form-select" id="clave_localidad" name="clave_localidad">
							<option value="0">Seleccione una Localidad</option>
						</select>
					</div>
					<div class="col-6 mb-3">
						<label for="colonia" class="form-label">Colonia</label> 
						<select class="form-select" id="clave_colonia" name="clave_colonia">
							<option value="0">Seleccione una Colonia</option>
						</select>
						</div>
					</div>
					<div class="col-12 mb-3">
							<label for="colonia" class="form-label">Calle y Numero</label> 
							<input class="form-control" name="calleNumero" id="calleNumero" >
					</div>
				<button type="button" class="btn btn-primary form-control" onclick="ValidarFormulario()">Continuar</button>
			</form>
		</div>
	
		<script src="media/js/jquery-3.7.1.min.js"></script>
		<script src="media/sweetalert2/sweetalert2.all.min.js"></script>
		<script src="media/bootstrap-5.3.6/js/bootstrap.min.js"></script>
		<script src="media/js/script_direcciones.js"></script>
	</body>
</html>
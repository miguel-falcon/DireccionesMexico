/**
 * Función que se encarga de cargar los municipios y localidades con forme el estado seleccionado
 */
function CargarMunicipiosLocalidades(modo, clave_estado){
	let clave_estado_value = clave_estado.value;
	let servlet = "direcciones_mexico_servlet";
	let modo_value = modo; 
	let datos = { "modo": modo_value, "clave_estado": clave_estado_value};

	$.ajax({
	    url: servlet,
	    type: "GET",
	    data: datos,
	    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    success: function(response) {
			
	        let partes = response.split("|");
	        let municipios = partes[0].split(";");
	        let localidades = partes[1].split(";");
	
	        let municipioSelect = $("#clave_municipio").empty().append(`<option value="0">Seleccione un municipio</option>`);
	        let localidadSelect = $("#clave_localidad").empty().append(`<option value="0">Seleccione una localidad</option>`);
	
	        municipios.forEach(m => {
	            let datos = m.split("=");
	            if (datos.length == 2) {
	                municipioSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
	            }
	        });
	
	        localidades.forEach(l => {
	            let datos = l.split("=");
	            if (datos.length == 2) {
	                localidadSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
	            }
	        });
	    },
	    error: function() {
	        console.error("Error al Obtener Municipios y Localidades.");
	    }
	});
}

/**
 * Funcion que recupera los estados para ingresarlos a la lista, al igualque restaura los demas campos a vacío
 */
function cargarEstados() {
    let codigoPostal = $("#cp").val();
    let servlet = "direcciones_mexico_servlet";
    let datos = { "modo": "cargarEstados" };

    if (!codigoPostal) {
        $.ajax({
            url: servlet,
            type: "GET",
            data: datos,
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            success: function(response) {
                console.log("Respuesta del servidor:", response);

                if (!response) {
                    ShowMensaje("Error", "No se encontrarón estados en la base de datos.", "error");
                    return; 
                }

                let estados = response.split(";");

                let estadosSelect = $("#clave_estado").empty().append(`<option value="0">Seleccione un Estado</option>`);
               	$("#clave_municipio").empty().append(`<option value="0">Seleccione un Municipio</option>`);
           		$("#clave_localidad").empty().append(`<option value="0">Seleccione una Localidad</option>`);
                $("#clave_colonia").empty().append(`<option value="0">Seleccione un colonia</option>`);

                if (estados.length > 0) {
                    estados.forEach(e => {
                        let datos = e.split("=");
                        if (datos.length == 2) {
                            estadosSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
                        }
                    });
                }
            },
            error: function() {
                ShowMensaje("Error de conexión", "No se pudo conectar con el servidor.", "error");
            }
        });
    }
}


/**
 * Función que carga los estados, municipios, localidades y colonias con forme el codigo postal ingresado
 */
function CargarEstadoMunicipioLocalidad(modo, codigo_postal){
    let codigo_postal_value = codigo_postal.value;
    let servlet = "direcciones_mexico_servlet";
    let modo_value = modo; 
    let datos = { "modo": modo_value, "codigo_postal": codigo_postal_value };

if(codigo_postal_value!=""){
	

    $.ajax({
        url: servlet,
        type: "GET",
        data: datos,
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        success: function(response) {
            console.log("Respuesta del servidor:", response); 

            if (response.includes("ERROR")) {
				cargarEstados();
                ShowMensaje("Código Postal no encontrado", "El código postal ingresado no existe en la base de datos.", "error");
                cargarEstados();
                return;
            }

            let partes = response.split("|"); 
            
            if (partes.length < 3) {
                ShowMensaje("Error de datos", "No se pudierón cargar los datos correctamente.", "error");
                cargarEstados();
                return;
            }

            let estados = partes[0] ? partes[0].split(";") : [];
            let municipios = partes[1] ? partes[1].split(";") : [];
            let localidades = partes[2] ? partes[2].split(";") : [];
            let colonias = partes[3] ? partes[3].split(";") : [];

            let estadosSelect = $("#clave_estado").empty().append(`<option value="0">Seleccione un Estado</option>`);
            let municipioSelect = $("#clave_municipio").empty().append(`<option value="0">Seleccione un Municipio</option>`);
            let localidadSelect = $("#clave_localidad").empty().append(`<option value="0">Seleccione una Localidad</option>`);
            let coloniaSelect = $("#clave_colonia").empty().append(`<option value="0">Seleccione una Colonia</option>`);

            if (estados.length > 0) {
                estados.forEach(e => {
                    let datos = e.split("=");
                    if (datos.length == 2) {
                        estadosSelect.append(`<option value="${datos[0]}" selected>${datos[1]}</option>`);
                    }
                });
            }

            if (municipios.length > 0) {
                municipios.forEach(m => {
                    let datos = m.split("=");
                    if (datos.length == 2) {
                        municipioSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
                    }
                });
            }

            if (localidades.length > 0) {
                localidades.forEach(l => {
                    let datos = l.split("=");
                    if (datos.length == 2) {
                        localidadSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
                    }
                });
            }
            
            if (colonias.length > 0 && colonias[0] !== "") {
                colonias.forEach(c => {
                    let datos = c.split("=");
                    if (datos.length == 2) {
                        coloniaSelect.append(`<option value="${datos[0]}">${datos[1]}</option>`);
                    }
                });
            }

        },
        error: function() {
            ShowMensaje("Error de conexión", "No se pudo conectar con el servidor.", "error");
            cargarEstados();
        }
    });
    }else{
		cargarEstados();
	}
}

function ValidarFormulario() {
    var codigo_postal = document.getElementById('codigo_postal').value;
    var estado = document.getElementById('clave_estado').value;
    var municipio = document.getElementById('clave_municipio').value;
    var localidad = document.getElementById('clave_localidad').value;
    var colonia = document.getElementById('clave_colonia').value;
    var calleNumero = document.getElementById('calleNumero').value;

    var mensajesError = "";

    if (codigo_postal === "") {
        mensajesError += "* Ingrese el código postal.";
    }
    if (estado === "" || estado === "0") {
        mensajesError += "* Seleccione un estado.";
    }
    if (municipio === "" || municipio === "0") {
        mensajesError += "* Seleccione un municipio.";
    }
    if (localidad === "" || localidad === "0") {
        mensajesError += "* Seleccione una localidad.";
    }
    if (colonia === "" || colonia === "0") {
        mensajesError += "* Seleccione una colonia.";
    }
    if (calleNumero.trim() === "") {
        mensajesError += "* Ingrese la calle y número.";
    }

    if (mensajesError !== "") {
        ShowMensaje("Error de validación", mensajesError, "error");
        return false;
    }

    ShowMensaje("Éxito", "Todos los datos fueron completados correctamente.", "success");
    return true; 
}


function ShowMensaje(tittle, message, icon){
	Swal.fire({
  title: tittle,
  text: message,
  icon: icon
});
}


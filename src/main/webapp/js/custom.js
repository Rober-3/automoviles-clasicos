$(document).ready(function() {
	$('.tabla').DataTable({
		language: {
			"sProcessing":     "Procesando...",
			"sLengthMenu":     "Mostrar _MENU_ registros",
			"sZeroRecords":    "No se han encontrado resultados",
			"sEmptyTable":     "No hay datos disponibles",
			"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
			"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
			"sInfoPostFix":    "",
			"sSearch":         "Buscar:",
			"sUrl":            "",
			"sInfoThousands":  ",",
			"sLoadingRecords": "Cargando...",
			"oPaginate": {
				"sFirst":    "Primero",
				"sLast":     "Último",
				"sNext":     "Siguiente",
				"sPrevious": "Anterior"
			},
			"oAria": {
				"sSortAscending":  ": Ordenar de manera ascendente",
				"sSortDescending": ": Ordenar de manera descendente"
			},
			"buttons": {
				"copy": "Copiar",
				"colvis": "Visibilidad"
			}
		}
	});
});

function init() {
	console.log('Documento cargado y listo.');
}


/**
 * Función asociada al evento keyenter para el id:input#nombre. Llama mediante AJAX a
 * u servicio rest pAra comprobar si existe un nombre de usuario en la base de datos.   
 */
function buscarUsuario(event) {
	
	const nombre = event.target.value;
	console.debug(`Valor del input: ${nombre}`);
	
	const url = `http://localhost:8080/automoviles-clasicos/api/usuario?nombre=${nombre}`;
	
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url );   
	xhttp.send();
	xhttp.onreadystatechange = function() { 
	
		let elNombreHelp = document.getElementById('nombreHelp');
		
		if (this.readyState == 4 && this.status == 200) {
			elNombreHelp.innerHTML = 'Nombre de usuario no disponible';
			elNombreHelp.classList.add('text-danger');
			elNombreHelp.classList.remove('text-success');
			
		} else {
			elNombreHelp.innerHTML = 'Nombre de usuario disponible';
			elNombreHelp.classList.add('text-success');
			elNombreHelp.classList.remove('text-danger');
		}
	
	} // onreadystatechange
	
} // buscarUsuario


/**
 * Función para mostrar u ocultar la contraseña. Cambia
 * el tipo de un input para que sea 'text' o 'password'.
 * 
 * @param idElemnt parámetro a cambiar.
 */
function showHidePass(idElement) {
	let elInput = document.getElementById(idElement);	
	elInput.type = (elInput.type == 'password' ) ? 'text' : 'password';
}


// Script para confirmar la eliminación de un registro.
function confirmar(modelo) {
	
	if ( confirm('¿Estás seguro de querer eliminar ' + modelo + '?') ){
		console.debug('Continúa el evento por defecto del ancla.');
		
	}else {
		console.debug('Previene o detiene el evento del ancla.');
		event.preventDefault();
	}
}

$('.carousel').carousel()

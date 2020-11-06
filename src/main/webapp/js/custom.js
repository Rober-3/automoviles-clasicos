// Script necesario para ejecutar el plugin DataTables.
$(document).ready(function() { // Ejecuta la funcion cuando todo el documento de HTML DOM esté listo y cargado.
	// $('#example').DataTable: Selecciona el id de la tabla y ejecuta el plugin .DataTable().
	// "#" puede ser id (#) o class(.) y example cualquier nombre. Hace referencia a la tabla.
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
// Función que hace una llamada AJAX para comprobar que un nombre de usuario está disponible en la bbdd.
function buscarUsuario(event) {
	
	// console.debug(event);
	
	const nombre = event.target.value;
	console.debug(`Valor del input: ${nombre}`);
	
	const url = `http://localhost:8080/automoviles-clasicos/api/usuario?nombre=${nombre}`;
	
	// Llamada AJAX.
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


// Script para encriptar la contraseña de usuario.

/*
 * function cifrar() {
		
		console.log('cifrar y conseguir hash');
		
		//recupero el valor de la contraseña del input a traves de su ID
		var contrasenia = document.getElementById('pass').value;		
		
		//consigo el hash mediante la libreria incluida en el pie.jsp
		var hash = md5(contrasenia);
		
		//guardo en el atributo value del input el codigo hash
		document.getElementById('pass').value = hash;		
		
		// comprobar si hay que confirmar la contraseña
		var inputRePass = document.getElementById('repass');
		
		// comprobar que exista el input#repass, si no existe tiene valor undefined
		if ( inputRePass ){                        
			
			var rehash = md5(inputRePass.value);
			inputRePass.value = rehash;
		}	
				
		//enviar formulario
		return true; // si ponemos false no se envia el formulario		
}
 */


// Script para confirmar la eliminación de un registro.
function confirmar(modelo) {
	
	// The confirm() method returns true if the user clicked "OK", and false otherwise.
	// modelo es el parámetro que se le pasa al script desde la vista ('${a.modelo}).
	if ( confirm('¿Estás seguro de querer eliminar ' + modelo + '?') ){
		console.debug('Continúa el evento por defecto del ancla.');
		
	}else {
		console.debug('Previene o detiene el evento del ancla.');
		event.preventDefault();
	}
}

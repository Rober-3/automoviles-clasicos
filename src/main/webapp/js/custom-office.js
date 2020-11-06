//Script necesario para ejecutar el plugin DataTables.

// Ejecuta la funcion cuando todo el documento de HTML DOM esté listo y cargado.
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


//Script para encriptar la contraseña de usuario.
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


//Script para confirmar la eliminación de un clásico o una marca.
function confirmar(parametro) {

	// The confirm() method returns true if the user clicked "OK", and false otherwise.
	// modelo es el parámetro que se le pasa al script desde la vista ('${a.modelo}).
	if ( confirm('¿Estás seguro de querer eliminar ' + parametro + '?') ){

		console.debug('Continúa el evento por defecto del ancla.');

	}else {

		console.debug('Previene o detiene el evento del ancla.');
		event.preventDefault();
	}
}


//Script de Bootstrap para desplegar la barra lateral.
(function($) {
	"use strict";

	// Add active state to sidbar nav links
	var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
	$("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
		if (this.href === path) {
			$(this).addClass("active");
		}
	});

	// Toggle the side navigation
	$("#sidebarToggle").on("click", function(e) {
		e.preventDefault();
		$("body").toggleClass("sb-sidenav-toggled");
	});
})(jQuery);

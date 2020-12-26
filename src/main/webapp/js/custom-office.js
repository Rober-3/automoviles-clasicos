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

//Script para confirmar la eliminación de un clásico o una marca.
function confirmar(parametro) {

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

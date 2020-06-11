// Script necesario para ejecutar el plugin DataTables.

 // Ejecuta la funcion cuando todo el documento de HTML DOM esté listo y cargado.
$(document).ready(function() {
	// Selecciona el id de la tabla y ejecuta el plugin .DataTable().
    // "#" puede ser id (#) o class(.) y tabla cualquier nombre. Hace referencia a la tabla.
    $('.tabla').DataTable();
});



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
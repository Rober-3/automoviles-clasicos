// Script necesario para ejecutar el plugin DataTables.

 // Ejecuta la funcion cuando todo el documento de HTML DOM esté listo y cargado.
$(document).ready(function() {
	// Selecciona el id de la tabla y ejecuta el plugin .DataTable().
    // "#" puede ser id (#) o class(.) y tabla cualquier nombre. Hace referencia a la tabla.
    $('.tabla').DataTable();
});

function init() {
	console.log('Documento cargado y listo.');
	
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
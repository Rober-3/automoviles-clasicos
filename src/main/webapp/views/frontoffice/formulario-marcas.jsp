<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Formulario marcas" />
	<jsp:param name="title" value="Formulario marcas" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-usuario.jsp" />
<main class="container mt-2">
	<div class="formulario container shadow-lg p-3 mb-5 bg-white rounded">
		<h3 class="my-3 text-center">${encabezado}</h3> <!-- InsEditMarcaFrontOfficeController -->
		<!-- Formulario -->
		<form action="views/frontoffice/insertar-editar-marca" method="post" class="mt-3 px-3 font-weight-bold"> <!-- InsEditMarcaFrontOfficeController -->
			<div class="form-group">
				<label for="id">Id:</label>
				<input type="text" 
					   id="id"
					   name="id"
					   value="${marca.id}"
					   class="form-control"
					   readonly>
			</div>
			<div class="form-group">
				<label for="marca">Marca:</label>
				<input type="text"
					   id="marca"
					   name="marca"
					   value="${marca.marca}" 
					   class="form-control"
					   placeholder="Introduce el nombre de la marca.">
			</div>
			<div class="form-group d-flex align-items-center justify-content-end pt-3">
				<button type="submit" class="btn btn-success">Guardar</button>
			</div>
		</form>
	</div>
</main>
<jsp:include page="../../includes/office-footer.jsp" />
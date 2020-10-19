<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/header.jsp">
	<jsp:param name="pagina" value="Formulario marcas" />
	<jsp:param name="title" value="Formulario marcas" />
</jsp:include>
<main>
	<div class="container my-5 bg-light formulariomarcas">
		<h3 class="my-5 text-center">Nueva marca / Editar marca</h3>
		
		<!-- Formulario InsertarEditarMarcaController -->
		<form action="insertar-editar-marca" method="post" class="mt-3 font-weight-bold">
			<div class="form-group">
				<label for="id">Id:</label>
				<input type="text" 
					   id="id"
					   name="id"
					   value="${marca.id}"
					   class="form-control form-control-sm"
					   readonly>
			</div>
			<div class="form-group">
				<label for="marca">Marca:</label>
				<input type="text"
					   id="marca"
					   name="marca"
					   value="${marca.marca}" 
					   class="form-control form-control-sm"
					   placeholder="Introduce el nombre de la marca.">
			</div>
			<div >
				<button type="submit" class="btn btn-primary btn-sm">Guardar</button>
			</div>
		</form>
	</div>
</main>
<%@include file="../../includes/footer.jsp"%>
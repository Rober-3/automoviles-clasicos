<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="Formulario" />
	<jsp:param name="title" value="Formulario" />
</jsp:include>


	<main>
		
		<div class="container my-5 formulario">
		
			<h3 class="text-center">Nuevo registro / Editar registro</h3>

			<div class="my-5"> <a href="index.jsp">Página principal</a> </div>

			<!-- Formulario -->

			<form action="insertar-editar" method="post" class="mt-3">

				<div class="form-group">
					<label for="id">Id</label>
					<input type="text"
						   id="id"
						   name="id"
						   value="${clasico.id}"
						   class="form-control form-control-sm" 
						   readonly>
				</div>
				<div class="form-group">
					<label for="modelo">Modelo</label>
					<input type="text" 
						   id="modelo"
						   name="modelo"
						   value="${clasico.modelo}"
						   class="form-control form-control-sm"
						   
						   placeholder="El modelo debe tener entre 3 y 50 caracteres y no estar repetido">
				</div>
				<div class="form-group">
					<label for="marca">Marca</label>
					<input type="text"
						   id="marca"
						   name="marca"
						   value="${clasico.marca}"
						   class="form-control form-control-sm"
						   >
				</div>
				<div class="form-group">
					<label for="anio">Año</label>
					<input type="text"
						   id="anio"
						   name="anio"
						   value="${clasico.anio}"
						   class="form-control form-control-sm"
						   >
				</div>
				<div class="form-group">
					<label for="foto">Foto</label>
					<input type="text"
						   id="foto"
						   name="foto"
						   value="${clasico.foto}"
						   class="form-control form-control-sm"
						   
						   placeholder="Introduce la URL de la foto">
				</div>
				<button type="submit" class="btn btn-primary">Guardar</button>

			</form>
			
		</div>
		
	</main>

	<%@include file="includes/footer.jsp"%>
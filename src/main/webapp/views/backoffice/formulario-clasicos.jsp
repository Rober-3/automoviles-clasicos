<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Formulario clasicos" />
	<jsp:param name="title" value="Formulario clasicos" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />
<main class="container mt-2">
	<div class="formulario container shadow-lg p-3 mb-5 bg-white rounded">
		<h3 class="my-3 text-center">${encabezado}</h3>
		<form action="views/backoffice/insertar-editar-clasico" method="post" class="mt-3 px-3 font-weight-bold">
			<div class="form-group">
				<label for="id">Id:</label>
				<input type="text" 
					   id="id"
					   name="id"
					   value="${clasico.id}"
					   class="form-control"
					   readonly>
			</div>	
			<div class="form-group">
				<label for="modelo">Modelo:</label>
				<input type="text"
					   id="modelo"
					   name="modelo"
					   value="${clasico.modelo}" 
					   class="form-control"
					   placeholder="El modelo debe tener entre 3 y 50 caracteres y no estar repetido.">
			</div>
			<div class="form-group">
				<label for="marca">Marca:</label>
				<select id="marca"
						name="id_marca"
						class="custom-select">
					<option>Selecciona una marca</option>
					<c:forEach items="${marcas}" var="m">
						<option value="${m.id}" ${ (m.id eq clasico.marca.id) ?"selected" :"" }>${m.marca}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="anio">Año:</label>
				<input type="text"
					   id="anio"
					   name="anio"
					   value="${clasico.anio}"
					   class="form-control">
			</div>
			<div class="form-group">
				<label for="foto">Foto:</label>
				<input type="text"
					   id="foto"
					   name="foto"
					   value="${clasico.foto}"
					   class="form-control"
					   placeholder="Introduce la URL de la foto.">
			</div>
			<div class="form-group d-flex align-items-center justify-content-end pt-3">
				<button type="submit" class="btn btn-success">Guardar</button>
			</div>
		</form>
	</div>
</main>
<jsp:include page="../../includes/office-footer.jsp" />

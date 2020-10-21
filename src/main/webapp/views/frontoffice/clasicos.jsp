<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Clasicos" />
	<jsp:param name="title" value="Clasicos" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-usuario.jsp" />

<main class="container">
	<div class="container my-5">
		
		<h1 class="text-center mt-5">${encabezado}</h1> <!-- ClasicosFrontOfficeController -->
		
		<img src="img/encabezado-americanos.png" class="mx-auto my-5 d-block">

		<!-- Tabla -->
		<table class="tabla table table-striped mt-3">
			<thead class="thead-light">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Año</th>
					<th scope="col">Foto</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clasicos}" var="c"> <!-- ClasicosFrontOfficeController. -->
					<tr>
						<td>${c.id}</td>
						<td>${c.modelo}</td>
						<td>${c.marca.marca}</td> <!-- Accede al atributo marca del objeto Marca. -->
						<td>${c.anio}</td>
						<td> <img src="${c.foto}" class="miniatura img-thumbnail" alt="foto"></td>

						<td class="align-middle">
						
							<% // Se pasa el id del clásico a editar o eliminar como parámetro en la URL. %>
							<a href="views/frontoffice/insertar?id=${c.id}">
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							
							<!-- EliminarFrontOfficeController custom-office.js -->
							<!-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un modelo. -->
							<a href="views/frontoffice/eliminar?id=${c.id}" onclick="confirmar('${c.modelo}')">
							<i class="fas fa-trash fa-1x mx-2" title="Eliminar"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>

<jsp:include page="../../includes/office-footer.jsp" />

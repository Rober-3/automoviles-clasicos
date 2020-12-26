<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Clasicos" />
	<jsp:param name="title" value="Clasicos" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />
<main class="container">
	<div class="container my-5">
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
				<c:forEach items="${clasicos}" var="c">
					<tr>
						<td class="align-middle">${c.id}</td>
						<td class="align-middle">${c.modelo}</td>
						<td class="align-middle">${c.marca.marca}</td>
						<td class="align-middle">${c.anio}</td>
						<td class="align-middle">
							<img src="${c.foto}"class="miniatura img-thumbnail" alt="foto">
							<div><a href="${c.foto}" target="_blank">Ver  el clásico</a></div>
						</td>
						<td class="align-middle">
							<% // Se pasa el id del clásico a editar, eliminar o validar como parámetro en la URL. %>
							<a href="views/backoffice/insertar-editar-clasico?id=${c.id}">
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							<a href="views/backoffice/eliminar-clasico?id=${c.id}" onclick="confirmar('${c.modelo}')">
							<i class="fas fa-trash fa-1x mx-2" title="Eliminar"></i></a>
							<c:if test="${validados=='no'}">
								<a href="views/backoffice/insertar-editar-clasico?id=${c.id}&validado=no">
								<i class="fas fa-check-square" title="Validar"></i></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
<jsp:include page="../../includes/office-footer.jsp" />

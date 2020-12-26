<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Usuarios" />
	<jsp:param name="title" value="Usuarios" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />
<main class="container">
	<div class="container my-5">
		<h3 class="text-center mt-3">USUARIOS</h3>
		<table class="table table-striped mt-3 tabla">
			<thead class="thead-light">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nombre</th>
					<th scope="col">Rol</th>
					<th scope="col">Imagen</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="u">
					<tr>
						<td class="align-middle">${u.id}</td>
						<td class="align-middle">${u.nombre}</td>
						<td class="align-middle">${u.rol.rol}</td>
						<td class="align-middle"><img src="${u.imagen}" class="img-thumbnail" alt="imagen"></td>
						<td class="align-middle">
							<a href="insertar-editar?id=${u.id}">
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							<a href="eliminar?id=${u.id}" onclick="confirmar('${a.modelo}')">
							<i class="fas fa-trash fa-1x mx-2" title="Eliminar"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
<%@include file="../../includes/office-footer.jsp"%>

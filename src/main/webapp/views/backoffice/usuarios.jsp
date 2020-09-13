<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp">
	<jsp:param name="pagina" value="Usuarios" />
	<jsp:param name="title" value="Usuarios" />
</jsp:include>

<main>
	<div class="container my-5">
		<h3 class="text-center">USUARIOS</h3>

		<!-- Tabla -->

		<table class="table table-striped mt-3 tabla">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nombre</th>
					<th scope="col">Rol</th>
					<th scope="col">Imagen</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="u"> <!-- UsuariosBackOfficeController -->
					<tr>

						<td class="align-middle">${u.id}</td>
						<td class="align-middle">${u.nombre}</td>
						<td class="align-middle">${u.rol.rol}</td> <!-- Accede al atributo rol del objeto Rol. -->
						<td class="align-middle"><img src="${u.imagen}" class="img-thumbnail" alt="imagen"></td>
						
						<td class="align-middle">
							<% // Se pasa el id del usuario a editar o eliminar como parámetro en la URL. %>
							
							<a href="insertar-editar?id=${u.id}">
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							
							<!-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un usuario. -->
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

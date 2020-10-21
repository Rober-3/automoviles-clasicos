<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Marcas" />
	<jsp:param name="title" value="Marcas" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />

<main class="container">
	<div class="container my-5">
	
		<h1 class="text-center mt-5">${encabezado}</h1> <!-- MarcasBackOfficeController -->
		
		<!-- Tabla -->
		<table class="tabla table table-striped mt-3">
			<thead class="thead-light">
				<tr>
					<th scope="col" class="text-center">Id</th>
					<th scope="col" class="text-center">Marca</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${marcas}" var="m"> <!-- MarcasBackOfficeController. -->
					<tr>
						<td class="align-middle">${m.id}</td>
						<td class="align-middle">${m.marca}</td>
						<td class="align-middle">
							
							<a href="/views/backoffice/insertar-editar?id=${m.id}"> <!-- InsertarEditarBackOfficeController -->
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							
							<c:if test="${validadas=='no'}"> <!-- MarcasBackOfficeController -->
								<a href="views/backoffice/insertar-editar?id=${m.id}"> <!-- InsertarEditarBackOfficeController -->
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

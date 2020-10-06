<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp">
	<jsp:param name="pagina" value="Clasicos americanos" />
	<jsp:param name="title" value="Clasicos americanos" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />

<main class="container">
	<div class="container my-5">
		<!-- ClasicosBackOfficeController -->
		<h1 class="text-center mt-5">${encabezado}</h1>
		
		<img src="img/encabezado-americanos.png" class="mx-auto my-5 d-block">

		<!-- Tabla -->
		<table class="table table-striped mt-3 tabla">
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

				<!-- ClasicosFrontOfficeController. -->
				<c:forEach items="${clasicos}" var="c">
					<tr>
						<td class="align-middle">${c.id}</td>
						<td class="align-middle">${c.modelo}</td>
						<td class="align-middle">${c.marca.marca}</td><!-- Accede al atributo marca del objeto Marca. -->
						<td class="align-middle">${c.anio}</td>
						<td class="align-middle">
							<img src="${c.foto}"class="img-thumbnail" alt="foto">
						</td>

						<td class="align-middle">
							<% // Se pasa el id del clásico a editar o eliminar como parámetro en la URL. %>
							
							<a href="views/frontoffice/insertar?id=${c.id}">
							<i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a> 
							
							<!-- EliminarFrontOfficeController -->
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

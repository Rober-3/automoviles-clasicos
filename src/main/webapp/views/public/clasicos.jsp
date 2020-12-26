<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="pagina" value="Clasicos" />
	<jsp:param name="title" value="Clasicos" />
</jsp:include>
<main>
	<div class="container my-5">
		<img src="img/encabezado-americanos.png" class="mx-auto my-5 d-block">
		<table class="tabla table table-striped mt-3">
			<thead class="thead-light">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Año</th>
					<th scope="col">Foto</th>
					<!-- <th scope="col"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clasicos}" var="c">
					<tr>

						<td class="align-middle">${c.id}</td>
						<td class="align-middle">${c.modelo}</td>
						<td class="align-middle">${c.marca.marca}</td>
						<td class="align-middle">${c.anio}</td>
						<td>
							<img src="${c.foto}" class="img-thumbnail" alt="foto">
							<div><a href="${c.foto}" target="_blank">Ver  el clásico</a></div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
<%@include file="../../includes/footer.jsp"%>

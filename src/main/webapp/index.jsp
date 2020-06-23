<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="Pagina principal" />
	<jsp:param name="title" value="Pagina principal" />
</jsp:include>

<main>

	<div class="container mt-5">

		<div class="row">

			<c:forEach items="${clasicos}" var="c">

				<div class="card bg-light mb-5 mx-auto" style="max-width: 18rem;">

					<img src="${c.foto}" class="card-img-top" alt="Foto del clásico">

					<div class="card-body">

						<h5 class="card-title font-weight-bold text-center">${c.modelo}</h5>
						<p class="card-text">Marca: <b>${c.marca.marca}</b> </p>
						<p class="card-text">Año: <b>${c.anio}</b> </p>
						<a href="${c.foto}" target="_blank" class="card-link foto">Ver el clásico</a>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</main>

<%@include file="includes/footer.jsp"%>
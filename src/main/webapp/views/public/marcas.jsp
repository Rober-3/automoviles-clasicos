<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="pagina" value="Marcas" />
	<jsp:param name="title" value="Marcas" />
</jsp:include>

<main>
	<div class="container mt-5">
		<c:if test="${empty marca}">
			<h2 class="text-center">Todas A-Z</h2>
			<div>
				<c:forEach items="${marcasConClasicos}" var="m">
					<h4 class="mt-5">${m.marca}</h4>
					<div class="row mt-5">
						<c:forEach items="${m.clasicos}" var="c">
							<div class="card bg-light mb-5 mx-auto" style="max-width: 18rem;">
								<img src="${c.foto}" class="card-img-top" alt="Foto del clásico">
								<div class="card-body">
									<h5 class="card-title font-weight-bold text-center">${c.modelo}</h5>
									<!-- <p class="card-text">Marca: <b>${c.marca.marca}</b></p> -->
									<p class="card-text">Año: <b>${c.anio}</b></p>
									<p class="card-text">Subido por: <b>${c.usuario.nombre}</b></p>
									<a href="${c.foto}" target="_blank" class="card-link foto">Ver el clásico</a>
								</div>
							</div>
						</c:forEach>
					</div>
					<hr>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${not empty marca}">
			<h2 class="text-center">${marca}</h2>
			<div class="row mt-5">
				<c:forEach items="${clasicos}" var="c">
					<div class="card bg-light mb-5 mx-auto" style="max-width: 18rem;">
						<img src="${c.foto}" class="card-img-top" alt="Foto del clásico">
						<div class="card-body">
							<h5 class="card-title font-weight-bold">${c.modelo}</h5>
							<!-- <p class="card-text">Marca: <b>${c.marca.marca}</b></p> -->
							<p class="card-text">Año: <b>${c.anio}</b></p>
							<a href="${c.foto}" target="_blank" class="card-link foto">Ver el clásico</a></b>
							<p class="card-text">Subido por: <b>${c.usuario.nombre}</b></p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</main>
<%@include file="../../includes/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="includes/head.jsp">
	<jsp:param name="pagina" value="Pagina principal" />
	<jsp:param name="title" value="Pagina principal" />
</jsp:include>
<main>
	<div class="principal container mt-5">
		<div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="${clasico1.foto}" class="d-block w-100" 
						 alt="${clasico1.marca.marca} ${clasico1.modelo} ${clasico1.anio}">
				</div>
				<div class="carousel-item">
					<img src="${clasico2.foto}" class="d-block w-100"
						 alt="${clasico2.marca.marca} ${clasico2.modelo} ${clasico2.anio}">
				</div>
				<div class="carousel-item">
					<img src="${clasico3.foto}" class="d-block w-100"
						 alt="${clasico3.marca.marca} ${clasico3.modelo} ${clasico3.anio}">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleFade" role="button" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Anterior</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleFade" role="button" data-slide="next"> 
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Siguiente</span>
			</a>
		</div>
	</div>
</main>
<%@include file="includes/footer.jsp"%>

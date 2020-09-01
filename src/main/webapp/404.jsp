<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%> <!-- Directiva para indicar que es una página de error. -->

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="404" />
	<jsp:param name="title" value="404" />
</jsp:include>

<div class="jumbotron">
	<h1 class="text-center display-1">404</h1>
	<p class="lead">No existe la página indicada.</p>
	<hr class="my-4">
	<a class="btn btn-primary btn-lg" href="inicio" role="button">Volver a la pantalla principal</a> <!-- InicioController -->
</div>

<%@include file="includes/footer.jsp"%>

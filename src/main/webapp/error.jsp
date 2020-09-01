<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!-- Directiva para indicar que es una página de error. -->

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="error" />
	<jsp:param name="title" value="error" />
</jsp:include>

<div class="jumbotron container mt-5">
	<h1 class="display-4">Error</h1>
	<p class="lead">Ha habido un error. Por favor, ponte en contacto con el administrador.</p>
	<hr class="my-4">
	<!-- <a class="btn btn-primary btn-lg"
	   href="mailto:auraga@ipartek.com?Subject=Aquí%20el%20asunto%20del%20mail&body=Motivo:<%=exception.getMessage()%>"
	   role="button">Enviar correo</a> -->
	<a class="btn btn-primary btn-lg" href="inicio" role="button">Volver a la pantalla principal</a> <!-- InicioController -->
</div>

<%@include file="includes/footer.jsp"%>

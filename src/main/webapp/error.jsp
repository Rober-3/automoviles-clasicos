<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%> <!-- Directiva para indicar que es una página de error. -->

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="error" />
	<jsp:param name="title" value="error" />
</jsp:include>

<div class="error container mt-3">
	<h1 class="text-center font-weight-bold pt-5">Error</h1>
	<h3 class="text-center font-weight-bold mt-4">Ha habido un error. Por favor, ponte en contacto con el administrador.</h3>
	<!-- <a class="btn btn-primary btn-lg" href="mailto:auraga@ipartek.com?Subject=Aquí%20el%20
	asunto%20del%20mail&body=Motivo:<=exception.getMessage()%>"role="button">Enviar correo</a> -->
</div>

<%@include file="includes/footer.jsp"%>

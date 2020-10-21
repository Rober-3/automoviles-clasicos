<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%> <!-- Directiva para indicar que es una página de error. -->

<jsp:include page="includes/head.jsp">
	<jsp:param name="pagina" value="404" />
	<jsp:param name="title" value="404" />
</jsp:include>

<div class="noencontrada container mt-3">
	<h1 class="text-center font-weight-bold">404</h1>
	<p class="text-center font-weight-bold mt-1">No existe la página indicada.</p>
</div>

<%@include file="includes/footer.jsp"%>

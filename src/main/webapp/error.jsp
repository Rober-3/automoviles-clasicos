<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<jsp:include page="includes/head.jsp">
	<jsp:param name="pagina" value="error" />
	<jsp:param name="title" value="error" />
</jsp:include>
<div class="error container mt-3">
	<h1 class="text-center font-weight-bold pt-5">Error</h1>
	<h3 class="text-center font-weight-bold mt-4">Ha habido un error. Por favor, ponte en contacto con el administrador.</h3>
</div>
<%@include file="includes/footer.jsp"%>

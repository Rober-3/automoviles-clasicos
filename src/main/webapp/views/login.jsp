<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="title" value="login" />
</jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<main>

		<div class="container my-5 formulario">

			<h3 class="text-center">Iniciar sesión</h3>

			<form action="login" method="post" class="mt-3"> <!-- LoginController -->

				<div class="form-group">
					<label for="nombre">Nombre de usuario</label>
					<input type="text"
						   id="nombre"
						   name="nombre"
						   value="${sessionScope.usuario.nombre}"
						   class="form-control form-control-sm"
						   placeholder="Introduce tu nombre de usuario">
				</div>
				
				<div class="form-group">
					<label for="contrasena">Contraseña</label>
					<input type="password"
						   id="contrasena"
						   name="contrasena"
						   value="${sessionScope.usuario.contrasena}"
						   class="form-control form-control-sm"
						   placeholder="Introduce tu contraseña">
				</div>
				
				<button type="submit" class="btn btn-primary btn-sm">Iniciar sesión</button>

			</form>

		</div>

	</main>

	<%@include file="../includes/footer.jsp"%>
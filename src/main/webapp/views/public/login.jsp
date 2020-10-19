<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<base href="${pageContext.request.contextPath}/" />

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<!-- CSS personalizado -->
<link rel="stylesheet" href="css/styles.css?d=<%=System.currentTimeMillis()%>">

<title>Inicio de sesión</title>
</head>
<body>
	<jsp:include page="/includes/alerta.jsp"></jsp:include>
	<main>
		<div class="container my-5 formulario">
			<h3 class="text-center">Inicio de sesión</h3>

			<form action="login" method="post" class="mt-3"> <!-- LoginController -->

				<!-- value="${sessionScope.usuario.nombre}" -->
				<div class="form-group">
					<label for="nombre">Nombre de usuario</label>
					<input type="text"
						   id="nombre"
						   name="nombre"
						   value="admin"
						   class="form-control"
						   placeholder="Introduce tu nombre de usuario">
				</div>
				<!-- value="${sessionScope.usuario.contrasena}" -->
				<div class="form-group">
					<label for="contrasena">Contraseña</label>
					<input type="password"
						   id="contrasena"
						   name="contrasena"
						   class="form-control"
						   placeholder="Introduce tu contraseña">
						   
					<!-- Muestra u oculta la contraseña al pulsar el icono del ojo en el formulario. -->
					<i class="fas fa-eye" onclick="showHidePass('contrasena')"></i>
				</div>
				<button type="submit" class="btn btn-primary">Iniciar sesión</button>
				<!-- onClick sacado de internet. -->
				<button type="button" class="btn btn-danger" onClick="window.parent.location='inicio'">Cancelar</button>
			</form>
			<p class="mt-3"><a href="views/public/registro.jsp" class="mt-3">Registro de usuario</a></p>
			<!-- <a href="usuario?id=0">Registro de usuario</a> -->
		</div>
	</main>
<%@include file="../../includes/footer.jsp"%>
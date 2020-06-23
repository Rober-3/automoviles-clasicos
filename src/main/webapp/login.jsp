<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<!-- Datatables CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<!-- CSS personalizado -->
<link rel="stylesheet" href="css/styles.css">

<title>${param.title}</title>

</head>

<body>

	<jsp:include page="includes/alerta.jsp"></jsp:include>

	<main>

		<div class="container my-5 formulario">

			<h3 class="text-center">Iniciar sesión</h3>

			<form action="login" method="post" class="mt-3">

				<div class="form-group">
					<label for="nombre">Nombre de usuario</label> <input type="text"
						id="nombre" name="nombre" value="${usuario.nombre}"
						class="form-control form-control-sm"
						placeholder="Introduce tu nombre de usuario">
				</div>
				<div class="form-group">
					<label for="contrasena">Contraseña</label> <input type="text"
						id="contrasena" name="contrasena" value="${usuario.contrasena}"
						class="form-control form-control-sm"
						placeholder="Introduce tu contraseña">
				</div>
				<button type="submit" class="btn btn-primary btn-sm">Iniciar
					sesión</button>

			</form>

		</div>

	</main>

	<%@include file="includes/footer.jsp"%>
<%@page import="robert.bermudez.rodriguez.modelo.Clasico"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

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

<title>Formulario</title>

</head>

<body>

	<header>

		<!-- Barra de navegación -->

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarTogglerDemo02"
				aria-controls="navbarTogglerDemo02" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active"><a class="nav-link"
						href="index.jsp">Inicio <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="clasicos">Clásicos
							americanos</a></li>
					<li class="nav-item"><a class="nav-link disabled"
						href="insertar" tabindex="-1" aria-disabled="true">Nuevo
							registro</a></li>
				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>

	<main>
	
		<!-- c:if evita que se muestren el área y el botón de la alerta estando vacía-->

		
			<div class="alert alert-warning alert-dismissible fade show text-center" role="alert">${alerta}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

		
		<div class="container my-5 formulario">
		
			<h3 class="text-center">Nuevo registro / Editar registro</h3>

			<div class="my-5"> <a href="index.jsp">Página principal</a> </div>

			<!-- Formulario -->

			<form action="insertar-editar" method="post" class="mt-3">

				<div class="form-group">
					<label for="id">Id</label>
					<input type="text"
						   id="id"
						   name="id"
						   value="${clasico.id}"
						   class="form-control form-control-sm" 
						   readonly>
				</div>
				<div class="form-group">
					<label for="modelo">Modelo</label>
					<input type="text" 
						   id="modelo"
						   name="modelo"
						   value="${clasico.modelo}"
						   class="form-control form-control-sm"
						   required
						   placeholder="El modelo debe tener entre 3 y 50 caracteres y no estar repetido">
				</div>
				<div class="form-group">
					<label for="marca">Marca</label>
					<input type="text"
						   id="marca"
						   name="marca"
						   value="${clasico.marca}"
						   class="form-control form-control-sm"
						   required>
				</div>
				<div class="form-group">
					<label for="anio">Año</label>
					<input type="text"
						   id="anio"
						   name="anio"
						   value="${clasico.anio}"
						   class="form-control form-control-sm"
						   required>
				</div>
				<div class="form-group">
					<label for="foto">Foto</label>
					<input type="text"
						   id="foto"
						   name="foto"
						   value="${clasico.foto}"
						   class="form-control form-control-sm"
						   required
						   placeholder="Introduce la URL de la foto">
				</div>
				<button type="submit" class="btn btn-primary">Guardar</button>

			</form>
		</div>
	</main>

	<!-- Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

	<!-- DataTables JS -->
	<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	
	<!-- Javascript personalizado -->
    <script src="js/custom.js"></script>

</body>
</html>
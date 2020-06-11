<%@page import="robert.bermudez.rodriguez.modelo.Clasico"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Clásicos americanos</title>

</head>

<body>

	<header>

		<!-- Barra de navegación -->

		<nav class="navbar navbar-expand-lg navbar-light bg-light purple-gradient">
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
					<li class="nav-item"><a class="nav-link disabled"
						href="clasicos" tabindex="-1" aria-disabled="true">Clásicos
							americanos</a></li>
					<li class="nav-item"><a class="nav-link" href="insertar-editar">Nuevo
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
		<div class="container my-5">

			<h3 class="text-center">Clásicos americanos</h3>

			<div class="my-5"> <a href="index.jsp">Página principal</a> </div>

			<!-- Tabla -->

			<table class="table table-striped mt-3 tabla">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Modelo</th>
						<th scope="col">Marca</th>
						<th scope="col">Año</th>
						<th scope="col">Foto</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>

					<!-- Recoger la información o atributo enviado desde el controlador. -->
					<c:forEach items="${clasicosAmericanos}" var="a">

						<tr>

							<td class="align-middle">${a.id}</td>
							<td class="align-middle">${a.modelo}</td>
							<td class="align-middle">${a.marca}</td>
							<td class="align-middle">${a.anio}</td>
							<td class="align-middle"><img src="${a.foto}" class="img-thumbnail" alt="foto"></td>
							
							<td class="align-middle">
								<% // Se pasa el id del clásico a editar o eliminar como parámetro en la URL. %>
								<a href="insertar-editar?id=${a.id}"> <i class="fas fa-edit fa-1x mx-2 align-middle"> </i> </a>
								<a href="eliminar?id=${a.id}" onclick="confirmar('${a.modelo}')"> <i class="fas fa-trash fa-1x mx-2"> </i> </a>
								<!-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un modelo. -->
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
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